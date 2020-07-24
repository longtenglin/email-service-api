package com.example.email.service.impl;

import com.example.email.service.MailService;
import com.example.email.utils.ResponseMap;
import com.example.email.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

/**
 * @author Mr.zhaon
 * @date 2020/06/23
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    private static final String SENDER = "474845394@qq.com";

    @Override
    public void sendSimpleMailMessge(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常!", e);
        }
    }

    @Override
    public void sendMimeMessge(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("发送MimeMessge时发生异常！", e);
        }
    }

    @Override
    public void sendMimeMessge(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);

            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("发送带附件的MimeMessge时发生异常！", e);
        }
    }

    @Override
    public void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
                FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
                helper.addInline(entry.getKey(), file);
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("发送带静态文件的MimeMessge时发生异常！", e);
        }
    }

    @Override
    public Map<String, Object> getMsg(HttpServletRequest request, String to) {
        MimeMessage message = mailSender.createMimeMessage();
        String[] letters = new String[] {
                "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
                "Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
                "0","1","2","3","4","5","6","7","8","9"};
        String stringBuilder ="";
        for (int i = 0; i < 6; i++) {
            stringBuilder = stringBuilder + letters[(int)Math.floor(Math.random()*letters.length)];
        }
        String content = "<div>" +
                "<p>登录注册验证码：<br>\n" +
                "欢迎注册登录【Mr.ZhaoNan科技网络有限公司】：验证码：<span style='color: blue;'><b><strong>"+ stringBuilder +"</strong></b></span> <br>" +
                "验证码30分钟内有效，请在<span style=\"border-bottom:1px dashed #ccc;\">30分钟内</span>注册登录。<br>" +
                "请根据《龙腾麟网络科技运营规范》的规定使用本程序。<br>" +
                "如果您对本公司感兴趣，欢迎您积极投递简历，简历发送至下面邮箱。我们将在一周内给您回复信息。<br>" +
                "简历邮箱：<a href=\"mailto:474845394@qq.com\" rel=\"noopener\" target=\"_blank\">474845394@qq<wbr>.com</a> <br>" +
                "</div>";

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(to);
            helper.setSubject("验证码");
            helper.setText(content, true);
            mailSender.send(message);
            System.out.println("验证码准备向session中存储，saving in session");
            SessionUtils.setVerifyCode(request,stringBuilder);
            System.out.println("验证码完成向session中存储，saved in session");
            System.out.println("请求session中的数据：——————"+SessionUtils.getVerifyCode(request));
            return ResponseMap.sendMessage("验证码发送成功",stringBuilder);
        } catch (MessagingException e) {
            logger.error("发送MimeMessge时发生异常！", e);
            return ResponseMap.sendMessage("发送MimeMessge时发生异常！");
        }
    }
}
