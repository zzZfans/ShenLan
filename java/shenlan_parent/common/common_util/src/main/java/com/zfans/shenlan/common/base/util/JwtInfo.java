package com.zfans.shenlan.common.base.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zfans
 * @since 2021/3/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtInfo {
    // 权限、角色等
    // 不要存敏感信息
    private String id;
    private String nickname;
    private String avatar;
}
