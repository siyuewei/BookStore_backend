package com.example.bookstores.serviceImpl;

import com.example.bookstores.service.StopWatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Scope(value = "session")
public class StopWatchServiceImpl implements StopWatchService {
    private final StopWatch stopWatch = new StopWatch();
    private static final Logger log = LoggerFactory.getLogger(StopWatchServiceImpl.class);

    @Override
    public boolean startStopWatch() {
//        log.info("service调用开始计时函数" + this.hashCode() + "\n");
        if (stopWatch.isRunning()) {
            return false;
        }
        stopWatch.start();
        return true;
    }

    @Override
    //返回总时间
    public double stopStopWatch() {
//        log.info("service调用结束计时函数" + this.hashCode() + "\n");
        if (!stopWatch.isRunning()) {
            return -1;
        }
        stopWatch.stop();
        return stopWatch.getTotalTimeSeconds();
    }
}
