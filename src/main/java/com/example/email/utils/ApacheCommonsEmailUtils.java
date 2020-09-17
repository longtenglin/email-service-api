package com.example.email.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class ApacheCommonsEmailUtils {
    // 发件人的邮箱-修改为自己的
    public static String emailAccount = "474845394@qq.com";
    // 发件人邮箱授权码-修改为自己的
    public static String emailPassword = "";
    // 发件人邮箱服务地址-修改为自己的
    public static String emailSMTPHost = "smtp.qq.com";
    //  收件人邮箱
    public static String receiveMailAccount = "";


    /**
     *  创建一封邮件(发件人、收件人、邮件内容)
     * @param session
     * @param sendMail
     * @param receiveMail
     * @param html
     * @return
     * @throws MessagingException
     * @throws IOException
     * cc:抄送
     * Bcc:密送
     * To:发送
     */
    public static MimeMessage creatMimeMessage(Session session, String sendMail, String receiveMail, String html) throws MessagingException, IOException {
        // 1、创建一封邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2、From：发件人
        message.setFrom(new InternetAddress(sendMail, "发件人昵称", "UTF-8"));
        // 3、To:收件人（可以增加多个收件人：抄送或者密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "张三", "UTF-8"));
        // 4、Subject:邮件主题
        message.setSubject("邮箱验证","UTF-8");
        // 5、Content:邮件正文（可以使用Html标签）
        message.setContent(html,"text/html;charset=UTF-8");
        // 6、设置发送时间
        message.setSentDate(new Date());
        // 7、保存设置
        message.saveChanges();
        // 8、将该邮件保存在本地
        //OutputStream out = new FileOutputStream("D://MyEmail" + UUID.randomUUID().toString() + ".eml");
        //message.writeTo(out);
        //out.flush();
        //out.close();
        return message;
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com
        //hpzcfionhlebbjdd
        //wukvrwdtyarecagg
        ts.connect("smtp.qq.com", "474845394@qq.com", "");
        // 创建邮件
        Message message = createSimpleMail(session);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMail(Session session) throws Exception {
        //  获取6为随机验证码
        String[] letters = new String[] {
                "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
                "Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
                "0","1","2","3","4","5","6","7","8","9"};
        String stringBuilder ="";
        for (int i = 0; i < 6; i++) {
            stringBuilder = stringBuilder + letters[(int)Math.floor(Math.random()*letters.length)];
        }

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("474845394@qq.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("474845394@qq.com"));
        // 邮件的标题
        message.setSubject("JavaMail测试");
        // 邮件的文本内容
        message.setContent("欢迎您注册【Mr_赵楠_网络科技】,账号注册验证码为(一分钟有效):"+stringBuilder+",请勿回复此邮箱", "text/html;charset=UTF-8");

        // 返回创建好的邮件对象
        return message;
    }
}
