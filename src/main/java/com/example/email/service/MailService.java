package com.example.email.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Mr.zhaon
 * @date 2020/06/23
 */
public interface MailService {

    /**
     * 发送普通邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMailMessge(String to, String subject, String content);

     /**
     * 发送 HTML 邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendMimeMessge(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件路径
     */
    void sendMimeMessge(String to, String subject, String content, String filePath);

    /**
     * 发送带静态文件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param rscIdMap 需要替换的静态文件
     */
    void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap);

    Map<String, Object> getMsg(HttpServletRequest request, String mail);
}
