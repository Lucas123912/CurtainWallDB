package com.wall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wall.domain.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
    @Select("select * from device t ${ew.customSqlSegment}")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 10)
    @ResultType(Device.class)
    void dynamicSelectLargeDataByDeviceNo(@Param(Constants.WRAPPER) QueryWrapper<Device> wrapper, ResultHandler<Device> handler);


}
