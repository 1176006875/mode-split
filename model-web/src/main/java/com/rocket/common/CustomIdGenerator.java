package com.rocket.common;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Long nextId(Object entity) {
        //生成ID
        long id = SnowFlakeUtils.nextId();
        return id;
    }
}