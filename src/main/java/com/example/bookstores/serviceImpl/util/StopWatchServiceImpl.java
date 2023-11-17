package com.example.bookstores.serviceImpl.util;

import com.example.bookstores.service.util.StopWatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session")
public class StopWatchServiceImpl implements StopWatchService {
//    private final StopWatch stopWatch = new StopWatch();
    private long startTime = 0;
    private static final Logger log = LoggerFactory.getLogger(StopWatchServiceImpl.class);

    @Override
    public void startStopWatch() {
//        log.info("service调用开始计时函数" + this.hashCode() + "\n");
//        if (stopWatch.isRunning()) {
//            return false;
//        }
//        stopWatch.start();
//        return true;
        startTime = System.currentTimeMillis();
    }

    @Override
    //返回总时间
    public long stopStopWatch() {
//        log.info("service调用结束计时函数" + this.hashCode() + "\n");
//        if (!stopWatch.isRunning()) {
//            return -1;
//        }
//        stopWatch.stop();
//        return stopWatch.getTotalTimeSeconds();
//    }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        startTime = 0;
        return time;
    }
}
