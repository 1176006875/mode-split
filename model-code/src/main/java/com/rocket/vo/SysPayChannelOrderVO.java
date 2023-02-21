package com.rocket.vo;

import lombok.Data;

@Data
public class SysPayChannelOrderVO {

    //订单号
    private String orderId;
    //订单状态 1：待支付 9已完成
    private Integer status;
}
