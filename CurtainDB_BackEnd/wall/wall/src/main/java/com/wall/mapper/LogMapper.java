package com.wall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wall.domain.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface LogMapper extends BaseMapper<Log> {
}
