package com.igeek.library.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.igeek.library.entity.Order;
import com.igeek.library.mapper.ReaderInfoMapper;
import com.igeek.library.service.AliPayService;
import com.igeek.library.service.IOrderService;
import com.igeek.library.service.IReaderInfoService;
import com.igeek.library.utils.AliPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Transactional
@Service
public class AliPayServiceImp implements AliPayService {
    @Autowired
    IOrderService orderService;
    @Autowired
    ReaderInfoMapper readerInfoMapper;
//    @Resource
//    AlipayConfig alipayConfig;
    @Override
    public String tradeCreate(Long readerId,Integer price) {
        try {
            AlipayClient alipayClient = AliPayUtils.setParams();
            Order order = orderService.add(readerId, price);
            //调用支付宝接口
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setReturnUrl("/readerCard/list?pageNum=1");
            //组装当前业务方法的请求参数
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", order.getId());
            bizContent.put("total_amount", new BigDecimal(order.getPrice().toString()));
            bizContent.put("subject",order.getTitle());
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
            request.setBizContent(bizContent.toString());
            request.setReturnUrl("http://localhost:8080/library/api/ali-pay/success");
            //执行请求，调用支付宝接口
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()){
                System.out.println("支付成功");
                readerInfoMapper.updateBalance(order.getPrice(), Math.toIntExact(order.getReaderId()));
                return response.getBody();
            }else {
                throw  new RuntimeException("订单支付失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw  new RuntimeException("订单支付失败");
        }

    }
}
