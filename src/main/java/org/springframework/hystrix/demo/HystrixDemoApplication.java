package org.springframework.hystrix.demo;

import java.util.concurrent.Future;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hystrix.demo.command.FallbackHystrixCommand;
import org.springframework.hystrix.demo.collapser.MyHystrixCollapser;
import org.springframework.hystrix.demo.command.BaseHystrixCommand;
import org.springframework.hystrix.demo.command.ClearCacheHystrixCommand;
import org.springframework.hystrix.demo.command.SemaphoreHystrixCommand;
import org.springframework.hystrix.demo.command.ThreadHystrixCommand;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@SpringBootApplication
public class HystrixDemoApplication {

	public static void main(String[] args) {
		try {
			
			// 请求缓存时需要初始化上下文
			HystrixRequestContext context = HystrixRequestContext.initializeContext();
			
//			// 同步调用
//			String result = new BaseHystrixCommand("zhangsan").execute();
//			System.out.println(result);
//			
//			// 异步调用
//			Future<String> future = new BaseHystrixCommand("zhangsan").queue();
//			System.out.println(future.get());
//			
//			// 回退
//			String fallbackResult = new FallbackHystrixCommand("zhangsan").execute();
//			System.out.println(fallbackResult);
//			
//			// Semaphore信号量隔离策略
//			String semaphoreResult = new SemaphoreHystrixCommand("semaphore").execute();
//			System.out.println(semaphoreResult);
//			
//			// 线程隔离策略
//			String threadResult = new ThreadHystrixCommand("thread").execute();
//			System.out.println(threadResult);
			
//			// 清除缓存
//			HystrixRequestContext context = HystrixRequestContext.initializeContext();
//			String result = new ClearCacheHystrixCommand("zhangsan").execute();
//			System.out.println(result);
//			ClearCacheHystrixCommand.flushCache("zhangsan");
//			Future<String> future = new ClearCacheHystrixCommand("zhangsan").queue();
//			System.out.println(future.get());   
			
			// 合并请求
			Future<String> f1 = new MyHystrixCollapser("zhangsan").queue();
			Future<String> f2 = new MyHystrixCollapser("zhangsan333").queue();
			System.out.println(f1.get() + " || " + f2.get());
			
			context.shutdown();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
