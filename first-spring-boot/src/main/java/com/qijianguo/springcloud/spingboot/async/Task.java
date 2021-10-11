package com.qijianguo.springcloud.spingboot.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class Task {

    /**
     * 传统多线程的方式
     */
    public void test() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.execute(() -> {
            // ...

        });
    }

    /**
     * 异步方法
     */
    @Async
    public void async() {

    }


}
