package com.qijianguo.springcloud;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Hystrix通过HystrixCommand对调用进行隔离
 * @author qijianguo
 */
public class MyHystrixCommandSamphore extends HystrixCommand<String> {

    private final String name;

    /**
     *
     * @param name
     */
    protected MyHystrixCommandSamphore(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                // 信号量策略配置
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.name = name;
    }

    /**
     * 执行业务逻辑
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        Thread.sleep(1000 * 10);
        return this.name + ": " + Thread.currentThread().getName();
    }

    /**
     * 失败回调
     * @return
     */
    @Override
    protected String getFallback() {
        return "请求失败...";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步调用
        MyHystrixCommandSamphore command = new MyHystrixCommandSamphore("aaa");
        String execute = command.execute();
        System.out.println(execute);

        Future<String> bbb = new MyHystrixCommandSamphore("bbb").queue();
        String s = bbb.get();
        System.out.println(s);
    }
}
