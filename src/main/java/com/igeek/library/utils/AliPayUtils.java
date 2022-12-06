package com.igeek.library.utils;

import com.alipay.api.*;

public class AliPayUtils {
    public static AlipayClient setParams() throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        //设置网关地址
        alipayConfig.setServerUrl(PropertiesUtil.get("alipay.gateway-url"));
        //设置应用ID
        alipayConfig.setAppId(PropertiesUtil.get("alipay.app-id"));
        //设置应用私钥
        alipayConfig.setPrivateKey(PropertiesUtil.get("alipay.merchant-private-key"));
        //设置请求格式，固定值json
        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
        //设置字符集
        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
        //设置签名类型
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
        //设置支付宝公钥
        alipayConfig.setAlipayPublicKey(PropertiesUtil.get("alipay.alipay-public-key"));
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        return alipayClient;
    }
}
