package com.rocket.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SysPayChannelOrder {

    //id
    private String id;
    //订单号
    private String orderId;
    //商户id  必填
    private Integer merchantId;
    //appid 必填
    private String appid;
    //2 开发平台(现在只有2,写死)
    private Integer channel;
    //0 支付渠道 1 信用卡 选填
    private Integer paymentType;
    //订单创建时间
    private LocalDateTime createTime;
    //订单创建时间
    private LocalDateTime updateTime;
    //法币单位（USD,GBP） 选填
    private String currency;
    //法币金额 必填
    private String ncltValue;
    //渠道收取的费用 选填
    private BigDecimal channelFee;
    //订单状态 1：待支付 9已完成
    private Integer status;
    //历史订单 选填
    private String history;
    //收货地址 必填
    private String toAddress;
    //公司收币的时间 选填
    private LocalDateTime ullaReceivedTime;
    //收币的公链symbol 必填
    private String net;
    //币种的symbol 必填
    private String symbol;
    //收币的数量 必填  创建的时候默认的0，对接支付通道的时候给值（没有对接支付通过，先不处理）
    private BigDecimal amount;
    //公司字段收币数量 创建的时候默认的0，对接支付通道的时候给值（没有对接支付通过，先不处理）
    private BigDecimal ullaAmount;
    //用户地址币到账的时间 选填
    private LocalDateTime userReceivedTime;
    //链上手续费 选填
    private BigDecimal transactionFee;
    //收取的手续费 选填
    private BigDecimal serviceFee;
    //服务费比例 选填
    private BigDecimal serviceFeePer;
    //用户相关信息 选填
    private String customer;
    //交易hash 转账之后通过接口返回更新字段
    private String txHash;

}
