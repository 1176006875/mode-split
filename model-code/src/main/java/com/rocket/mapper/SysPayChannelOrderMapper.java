package com.rocket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rocket.entity.SysPayChannelOrder;
import com.rocket.vo.SysPayChannelOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPayChannelOrderMapper extends BaseMapper<SysPayChannelOrder> {

    Integer updateStatus(@Param("query") SysPayChannelOrder order);

    Integer updateStatusByOrderId(@Param("query") SysPayChannelOrder order);

    List<SysPayChannelOrderVO> getAllByMerchantId(@Param("query") SysPayChannelOrder order);
}
