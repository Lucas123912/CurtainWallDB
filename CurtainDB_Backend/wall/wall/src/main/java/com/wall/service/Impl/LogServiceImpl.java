package com.wall.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wall.domain.Log;
import com.wall.mapper.LogMapper;
import com.wall.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}
