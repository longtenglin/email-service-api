package com.example.email.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @Declaration: Token 生成
 * @Author: Mr.zhao_nan
 */
@Slf4j
public class TokenUtils {

    /**
     *  创建密匙
     */
    private static final byte[] SECRET = "MRZHAONAN521LOVEFWJLTLONGINENG8R".getBytes();
    /**
     * 设置过期时间
     */
    private static final long EXPIRE_TIME = 1000*60*5;

    public static Map<String,Object> buildJWT(String AuthorizationToken){
        try{
            // 1.创建一个32-byte的密匙
            MACSigner macSigner = new MACSigner(SECRET);
            // 2.建立 payload载体
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("ToKen")
                    .issuer("Mr.zhaon")
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("AuthorizationToken",AuthorizationToken)
                    .build();
            // 3.建立签名
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),claimsSet);
            signedJWT.sign(macSigner);
            // 4.生成 token
            return  ResponseMap.sendMessage("Token obtained successfully",signedJWT.serialize());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseMap.sendMessage(2000,ErrorMessage.CreateTokenFail.getValue());
    }

    /**
     * 校验 Token
     */
    public static Map<String,Object> verifyToken(String token){
        try{
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier jwsVerifier = new MACVerifier(SECRET);

            // 1.校验是否有效
            if(!jwt.verify(jwsVerifier)){
                return ResponseMap.sendMessage(2001,ErrorMessage.CheckTokenInvalid.getValue());
            }
            // 2.校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)){
                return ResponseMap.sendMessage(2002,ErrorMessage.CheckTokenOverTime.getValue());
            }

            // 3.获取载体中的数据
            Object AuthorizationToken = jwt.getJWTClaimsSet().getClaim("AuthorizationToken");
            // 是否有openUid
            if (Objects.isNull(AuthorizationToken)){
                return ResponseMap.sendMessage(2003,ErrorMessage.CheckTokenNull.getValue());
            }
            return ResponseMap.sendMessage("Token validation successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseMap.sendMessage(2004,"Token permission verification failed");
    }
}
