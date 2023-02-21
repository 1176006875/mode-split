package com.rocket.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rocket.entity.SysPayChannelOrder;
import com.rocket.mapper.SysPayChannelOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class OrderUpdateJob {

    @Resource
    private SysPayChannelOrderMapper sysPayChannelOrderMapper;

//    @Scheduled(fixedDelay = 1000 * 60, initialDelay = 1000 * 1)
    public void fixedDelay() {
        log.info("fixedDelay");
        LambdaQueryWrapper<SysPayChannelOrder> wrapper = new LambdaQueryWrapper<>(SysPayChannelOrder.class);
        wrapper.eq(SysPayChannelOrder::getStatus, 1);
        wrapper.last("limit 100");
        List<SysPayChannelOrder> list = sysPayChannelOrderMapper.selectList(wrapper);
    }

}
