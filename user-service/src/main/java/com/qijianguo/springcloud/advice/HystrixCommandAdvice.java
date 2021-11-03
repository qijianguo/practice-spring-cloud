package com.qijianguo.springcloud.advice;

import com.netflix.hystrix.HystrixCommand;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Hystrix拦截器
 * https://www.infoq.cn/article/vCwcKAoqBdCAX1WyWPHR
 */
@Component
@Aspect
public class HystrixCommandAdvice {

   private static final Logger logger = LoggerFactory.getLogger(HystrixCommandAdvice.class);

   // 服务降级方法配置前缀
   private static String HYSTRIX_METHOD_PREFIX = "hystrix.method.";

   // 服务降级方法超时时间配置前缀
   private static String HYSTRIX_TIMEOUT_PREFIX = "hystrix.timeout.";

   // 服务降级方法并发数配置前缀
   private static String HYSTIRX_REQUEST_SIZE_PREFIX = "hystrix.requestSize.";

   // 服务降级方法策略配置前缀 使用信号量还是线程池
   private static String HYSTRIX_ISOLATION_STRATEGE_PREFIX = "hystrix.isolationStrategy.";

   // 服务降级方法返回类型配置前缀
   private static String HYSTRIX_DEFAULT_RETURN_PREFIX = "hystrix.default.return.";

   // iSystemSettingService获取业务中的配置，可以来自数据库、redis以及配置中心
   @Autowired
   private ISystemSettingService iSystemSettingService;

   // 定义切点，拦截某些特定第三方服务
   @Pointcut("execution(* com.qijianguo.springcloud.interfaces.*.*(..))")
   public void hystrixPointcut() {
   }

   /**
   * 切面
   */
   @Around("hystrixPointcut()")
   public Object runCommand(final ProceedingJoinPoint pJoinPoint) throws Throwable {
   // 此处省略，下面有具体代码说明
    return null;
   }

   /**
   * Hystrix参数设置
   */
   private HystrixCommand.Setter setter(String commonGroupKey, String commandKey,
                                        int timeout, int requestSize,
                                        String strategy) {
   // 此处省略，下面有具体代码说明
      return null;
   }

   /**
   * 生成降级后的返回内容
   */
   public static Object generateClass(String clazzPackage, String defaultValue)
          throws Exception {
      // 此处省略，下面有具体代码说明
    return null;
   }

}