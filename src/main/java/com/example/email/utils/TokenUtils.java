package com.example.email.utils;

import com.example.email.entity.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.ibatis.executor.result.ResultMapException;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * @Declaration: Token 生成
 * @Author: Mr.zhao_nan
 */
public class TokenUtils {

    /**
     *  创建密匙
     */
    private static final byte[] SECRET = "MRZHAONAN521LOVEFWJLTLONGINENG8R".getBytes();
    /**
     * 设置过期时间
     */
    private static final long EXPIRE_TIME = 5*1000;

    public static String buildJWT(String username){
        try{
            // 1.创建一个32-byte的密匙
            MACSigner macSigner = new MACSigner(SECRET);
            // 2.建立 payload载体
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("ToKen")
                    .issuer("Mr.zhaon")
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("ACCOUNT",username)
                    .build();
            // 3.建立签名
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),claimsSet);
            signedJWT.sign(macSigner);
            // 4.生成 token
            String token = signedJWT.serialize();

            return token;
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验 Token
     */
    public static String verifyToken(String token){
        try{
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier jwsVerifier = new MACVerifier(SECRET);

            // 1.校验是否有效
            if(!jwt.verify(jwsVerifier)){
                throw new RuntimeException("Token无效");
            }
            // 2.校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)){
                throw new RuntimeException("Token超时");
            }

            // 3.获取载体中的数据
            Object username = jwt.getJWTClaimsSet().getClaim("ACCOUNT");
            // 是否有openUid
            if (Objects.isNull(username)){
                throw new RuntimeException("用户信息为空");
            }
            return username.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
