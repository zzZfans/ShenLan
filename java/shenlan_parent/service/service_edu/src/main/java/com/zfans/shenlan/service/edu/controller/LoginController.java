package com.zfans.shenlan.service.edu.controller;

import com.zfans.shenlan.common.base.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description Todo
 * @Author Zfans
 * @DateTime 2021/01/28 22:20
 */
// @CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok()
                .data("name", "Zfans")
                .data("roles", "[admin]")
                .data("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
