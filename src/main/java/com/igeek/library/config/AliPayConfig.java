package com.igeek.library.config;


import com.alipay.api.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:alipay.properties")
public class AliPayConfig {
    @Resource
    Environment config;

//    @Bean
//    public AlipayClient alipayClient() throws AlipayApiException {
//        AlipayConfig alipayConfig = new AlipayConfig();
//        //设置网关地址
//        alipayConfig.setServerUrl(config.getProperty("alipay.gateway-url"));
//        //设置应用ID
//        alipayConfig.setAppId(config.getProperty("alipay.app-id"));
//        //设置应用私钥
//        alipayConfig.setPrivateKey(config.getProperty("alipay.merchant-private-key"));
//        //设置请求格式，固定值json
//        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
//        //设置字符集
//        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
//        //设置签名类型
//        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
//        //设置支付宝公钥
//        alipayConfig.setAlipayPublicKey(config.getProperty("alipay.alipay-public-key"));
//        //实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
//        return alipayClient;
//    }

//    @Value("2021000121677252")
    public static String appId = "2021000121677252";
//
//    @Value("2088621993663490")
//    public String sellerId;
//
//    @Value("https://openapi.alipaydev.com/gateway.do")
//    public String gatewayUrl;
//
//    @Value("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1oi8QkOJ3rogWE0nzjDj5a0YWbSH3oEAjFyJTX7hf0VXGqLxJkjJpuqmxlBmEzC/SqicS1qCKKADV18hTcFg6aOMOhmEoscMUmgThcIB7w4NV8GxEXsEIhLKXVRrbgBmKFdV5UakiZihxkpe0E2RVJjqO3OPm9cK5z0+P9BIz/QDXigT5aCdZEB+XNIzwa4xQArIeGM83B0mJIbk8QjFSIHKIggJexuvq+ikAS6Qrm1F5px4V/QdAuuJtbG9e5CkjkM8VpxigcqmIESyGGAdLwObYbiKkY6Z+Hrz+7HvBgBWdlDe5s+ynAOm9ZBpEMiL4wxVqHaRNR+t5m+WsHlctAgMBAAECggEBAKnJzTMwLPNx7HauW0/WwZ+uftsR81G2tJ3Yz1aewQyY9BT7wN8JUiTxwBVm+x/fe8jzFAmRJotfb8B2sSYGfANF7xKx3XscCN9mRq+y6fhHwKsCT0LuTIY4/XVnse6EZdtmI6MAChpE4wOf5D4Kbp0GWnEX3dcV34ywVZCcuLNs9nc5ndbAWbyceSbvZjsfVJsarLdsyoR9l2oDpGaMYikBt867Yf34c85tCMfzaI7AR7rdUEZcjahL9e75hMJYQHzFoFZgQ/JNgckK0VvrZ9SCvMeLnDTWTreHHnn/iuOnH83BSEoLEiF+oi1mu7sQ43+xm5Ry9vSa5bRaGIO2DAUCgYEA7R9Bw2kB0Rzsu2kgGt3uX3G3nuRDboJX2IPO4pvpED2w/xqQzfzXg5dPtdGYMovG4GhsndTrKzWeHmuJOz/XvpTbmPloOdCsn70x8DLkIUV9OITisWhCKaJix22yoHA4Mz1B+D4d6WF8O0NnNRFZcAS3U3vdjeVNPC5brW5ob5sCgYEAxBgGPq3hNaqPRQ855x6nrqPvvNsYkUC4vD5JltRLZ0SVORWiAbw0tUYk6hRFFQYIheXFEgipgkO6IFlCFy8vxeD3FpPr0kkhMB8WTQp6P4Hyjv0y0CK1qHnx4ZjFzVBYPwXX9lEDB8DA4rGHBIXlSYBSB5kJMcHxdPVLtR9ZlNcCgYEAocGX2Bjy/1bO+03Mc+d/bCakD0VB5soN6QqJgDbBd63N50iVDZ/02LTI0IxpWdnWUlGJ/uU3g+NwuOm81OlJYJ4la6Z//iinCdVDksr0qum/cWXdSw6HS+WVotvnZeEHxZR9Qz4Eo6c32ymw5xetvmfJM68kxV5uIKazLY1Kuw8CgYAcEmxicLX63s3fzww0//h1UdayLJNZx/AqUqhqRT3+LJ1ILMaihPTutnn2YKx7FAAIWxpZOJA7q8JrexuxAfpO06PLwaVS11E++FRS9xsFGG9G9QxfK3jiA4MTDrHScJwReZ6vDm3moDt8uHlhtmFaolob6FaenusYGT2bUj+axQKBgF5JYPzEgFLUQv6mPeA42IB8CK9wYfo/eiLT/5jzNfubUNvYaPj9PyBWLjUmkPH1KRX2GAFKfMfXr1BNHFsrw6emZGIAI4E1tZJV6gtVix723o1tHKp96itlD8L08Oy4uNKIpMzPRPlv2rtMpnP4XHFUzvyFW57REdR7y+jDP3DI")
//    public String privateKey;
//
//    @Value("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxMczzCudZ8wq9NGvCAO7kKQpbMcu8rudxgarS7PZfWrjJc31xKF14l5SOYF+woVsEZtPG30iVeXmrtHaVnxpTGZqkVNbpyTdBVLJCITjkrj2denKiegQGoAB+611TT7vZqvJpYXd9FxpkZfnTFFi3eIgZRimwVGXIlT3GYe0zH8QSqeQUa2cXKNIosQdQjzlL7jTEWsUIbI6gvwVr/MU+GvwSu/Tghll5SgKfgLHvJZK4tzyJkbh0nDS5Xrvx/BCm6AE+g6vmhHuelRq+fDQV2INlaCfS2UPV/qYk/Vv4i27zFqzP8czVlpjsnTuZKlW2u/gUDAQ8uCuY/ZDxfwanQIDAQAB")
//    public String publicKey;
//
//    @Value("D8entyfafkkFwtMbUqj3Mw==VavIsObozHeAMIDATJCBDg==")
//    public String contentKey;
//
//    public AlipayClient alipayClient() throws AlipayApiException {
//        AlipayConfig alipayConfig = new AlipayConfig();
//        //设置网关地址
//        alipayConfig.setServerUrl(gatewayUrl);
//        //设置应用ID
//        alipayConfig.setAppId(appId);
//        //设置应用私钥
//        alipayConfig.setPrivateKey(privateKey);
//        //设置请求格式，固定值json
//        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
//        //设置字符集
//        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
//        //设置签名类型
//        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
//        //设置支付宝公钥
//        alipayConfig.setAlipayPublicKey(publicKey);
//        //实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
//        return alipayClient;
//    }
}
