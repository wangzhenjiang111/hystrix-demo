package org.springframework.hystrix.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class ThreadHystrixCommand extends HystrixCommand<String>{

	private String name;
	
	public ThreadHystrixCommand(String name) {
		super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup_Thread"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)
						.withMaxQueueSize(100).withMaximumSize(100)));
		this.name = name;
	}
	
	@Override
	protected String run() {
		return this.name + ": " + Thread.currentThread().getName();
	}
}
