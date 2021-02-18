package com.zfans.shenlan.service.oss.service;

import java.io.InputStream;

/**
 * @Author Zfans
 * @DateTime 2021/02/01 13:46
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     *
     * @param inputStream      输入流
     * @param module           文件夹名称
     * @param originalFilename 原始文件名
     * @return 文件在 oss 服务器上的 url 地址
     */
    String upload(InputStream inputStream, String module, String originalFilename);

    /**
     * 阿里云oss 文件删除
     * @param url 文件的url地址
     */
    void removeFile(String url);

}
