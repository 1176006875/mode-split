package com.rocket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rocket.common.CommonEnum;
import com.rocket.common.CustomIdGenerator;
import com.rocket.domain.BasicResult;
import com.rocket.entity.PayOrder;
import com.rocket.entity.SysPayChannelOrder;
import com.rocket.exception.BizException;
import com.rocket.mapper.SysPayChannelOrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/payChannelOrder")
public class SysPayChannelOrderController {

    @Resource
    private SysPayChannelOrderMapper sysPayChannelOrderMapper;
    @Resource
    private CustomIdGenerator customIdGenerator;

    @GetMapping("/test")
    public BasicResult test(Integer id) {
        PayOrder payOrder = new PayOrder();
        payOrder.setId(id);
        return BasicResult.success(payOrder);
    }

    @PostMapping("/save")
    public BasicResult save(@RequestBody SysPayChannelOrder order) {
        isNull(order);
        Long orderId= customIdGenerator.nextId(new SysPayChannelOrder());
//        order.setOrderId(orderId.toString());
        order.setId(orderId.toString());

        isNull(order.getOrderId());
        isNull(order.getMerchantId());
        isNull(order.getCurrency());
        isNull(order.getNcltValue());
        isNull(order.getNet());
        isNull(order.getSymbol());
        isNull(order.getAmount());
        isNull(order.getToAddress());
        order.setStatus(1);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        order.setUllaAmount(BigDecimal.ZERO);
        order.setChannel(2);
        order.setUllaAmount(BigDecimal.ZERO);
        if (sysPayChannelOrderMapper.insert(order) == 0){
            return BasicResult.success("订单创建失败");
        }
        return BasicResult.success(order.getOrderId());
    }

    @PostMapping("/updateStatus")
    public BasicResult updateStatus(@RequestBody SysPayChannelOrder order) {
        isNull(order);
        isNull(order.getOrderId());
        LambdaQueryWrapper<SysPayChannelOrder> wrapper = new LambdaQueryWrapper<>(SysPayChannelOrder.class);
        wrapper.eq(SysPayChannelOrder::getOrderId, order.getOrderId());
        SysPayChannelOrder channelOrder = sysPayChannelOrderMapper.selectOne(wrapper);
        if (Objects.isNull(channelOrder)){
            return BasicResult.fail("数据不存在，请检查orderId参数是否正确");
        }
        if (Objects.equals(channelOrder.getStatus(), 9)) {
            return BasicResult.success("订单状态已完成，无需修改");
        }
        order.setStatus(9);
        if (sysPayChannelOrderMapper.updateStatusByOrderId(order) == 0) {
            return BasicResult.fail("修改失败");
        }
        return BasicResult.success("成功");
    }

    @PostMapping("/getInfo")
    public BasicResult getInfo(@RequestBody SysPayChannelOrder order) {
        isNull(order);
        isNull(order.getOrderId());
        LambdaQueryWrapper<SysPayChannelOrder> wrapper = new LambdaQueryWrapper<>(SysPayChannelOrder.class);
        wrapper.eq(SysPayChannelOrder::getOrderId,order.getOrderId());
        return BasicResult.success(sysPayChannelOrderMapper.selectOne(wrapper));
    }

    @PostMapping("/getAllByMerchantId")
    public BasicResult getAllByMerchantId(@RequestBody SysPayChannelOrder order) {
        isNull(order);
        isNull(order.getMerchantId());
        return BasicResult.success(sysPayChannelOrderMapper.getAllByMerchantId(order));
    }


    private void isNull(Object object){
        if (Objects.isNull(object)){
            throw new BizException(CommonEnum.BODY_NOT_MATCH.getResultCode(),CommonEnum.BODY_NOT_MATCH.getResultMsg());
        }
    }
}
