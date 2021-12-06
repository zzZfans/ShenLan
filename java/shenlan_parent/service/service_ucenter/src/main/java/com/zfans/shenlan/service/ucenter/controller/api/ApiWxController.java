package com.zfans.shenlan.service.ucenter.controller.api;

import com.google.gson.Gson;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.common.base.util.HttpClientUtils;
import com.zfans.shenlan.common.base.util.JwtInfo;
import com.zfans.shenlan.common.base.util.JwtUtils;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.ucenter.entity.Member;
import com.zfans.shenlan.service.ucenter.service.MemberService;
import com.zfans.shenlan.service.ucenter.util.UcenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Zfans
 * @DateTime 2021/11/26 19:42
 */
@CrossOrigin
@Controller // 这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
@Slf4j
public class ApiWxController {

    @Autowired
    private UcenterProperties ucenterProperties;

    @Autowired
    private MemberService memberService;

    @GetMapping("login")
    public String genQrConnect(HttpSession session) {

        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 处理回调 url
        String redirectUri = "";
        try {
            redirectUri = URLEncoder.encode(ucenterProperties.getRedirectUri(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ShenlanException(ResultCodeEnum.URL_ENCODE_ERROR);
        }

        //处理 state：生成随机数，存入 session
        String state = UUID.randomUUID().toString();
        log.info("生成 state = " + state);
        session.setAttribute("wx_open_state", state);

        String qrcodeUrl = String.format(
                baseUrl,
                ucenterProperties.getAppId(),
                redirectUri,
                state);

        return "redirect:" + qrcodeUrl;
    }

    @GetMapping("callback")
    public String callback(String code, String state, HttpSession session) {

        // 回调被拉起，并获得 code 和 state 参数
        log.info("callback 被调用");
        log.info("code = " + code);
        log.info("state = " + state);

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(state)) {
            log.error("非法回调请求");
            throw new ShenlanException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }

        String sessionState = (String) session.getAttribute("wx_open_state");
        if (!state.equals(sessionState)) {
            log.error("非法回调请求");
            throw new ShenlanException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }

        // 携带授权临时票据 code，和 appid 以及 appsecret 请求 access_token
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String, String> accessTokenParam = new HashMap();
        accessTokenParam.put("appid", ucenterProperties.getAppId());
        accessTokenParam.put("secret", ucenterProperties.getAppSecret());
        accessTokenParam.put("code", code);
        accessTokenParam.put("grant_type", "authorization_code");
        HttpClientUtils client = new HttpClientUtils(accessTokenUrl, accessTokenParam);

        String result = "";
        try {
            // 发送请求
            client.get();
            result = client.getContent();
            System.out.println("result = " + result);
        } catch (Exception e) {
            log.error("获取 access_token 失败");
            throw new ShenlanException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }

        Gson gson = new Gson();
        HashMap<String, Object> resultMap = gson.fromJson(result, HashMap.class);

        // 判断微信获取 access_token 失败的响应
        Object errcodeObj = resultMap.get("errcode");
        if (errcodeObj != null) {
            String errmsg = (String) resultMap.get("errmsg");
            Double errcode = (Double) errcodeObj;
            log.error("获取 access_token 失败 - " + "message: " + errmsg + ", errcode: " + errcode);
            throw new ShenlanException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }

        // 微信获取 access_token 响应成功
        String accessToken = (String) resultMap.get("access_token");
        String openid = (String) resultMap.get("openid");

        log.info("accessToken = " + accessToken);
        log.info("openid = " + openid);

        // 根据 access_token 获取微信用户的基本信息
        // 根据 openid 查询当前用户是否已经使用微信登录过该系统
        Member member = memberService.getByOpenid(openid);
        if (member == null) {

            // 向微信的资源服务器发起请求，获取当前用户的用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
            Map<String, String> baseUserInfoParam = new HashMap();
            baseUserInfoParam.put("access_token", accessToken);
            baseUserInfoParam.put("openid", openid);
            client = new HttpClientUtils(baseUserInfoUrl, baseUserInfoParam);

            String resultUserInfo;
            try {
                client.get();
                resultUserInfo = client.getContent();
            } catch (Exception e) {
                log.error(ExceptionUtils.getMessage(e));
                throw new ShenlanException(ResultCodeEnum.FETCH_USERINFO_ERROR);
            }

            HashMap<String, Object> resultUserInfoMap = gson.fromJson(resultUserInfo, HashMap.class);
            if (resultUserInfoMap.get("errcode") != null) {
                log.error("获取用户信息失败" + "，message：" + resultMap.get("errmsg"));
                throw new ShenlanException(ResultCodeEnum.FETCH_USERINFO_ERROR);
            }

            String nickname = (String) resultUserInfoMap.get("nickname");
            String headimgurl = (String) resultUserInfoMap.get("headimgurl");
            Double sex = (Double) resultUserInfoMap.get("sex");

            // 用户注册
            member = new Member();
            member.setOpenid(openid);
            member.setNickname(nickname);
            member.setAvatar(headimgurl);
            member.setSex(sex.intValue());
            memberService.save(member);
        }

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, 1800);

        // 携带 token 跳转
        return "redirect:http://localhost:3000?token=" + jwtToken;
    }
}
