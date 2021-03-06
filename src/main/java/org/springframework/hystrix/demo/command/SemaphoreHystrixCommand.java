package org.springframework.hystrix.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SemaphoreHystrixCommand extends HystrixCommand<String>{
	
	private final String name;
	public SemaphoreHystrixCommand(String name) {
		super(
				HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup_Semaphore"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
		this.name = name;
	}
	
	@Override
	protected String run() {
		return this.name + ": " + Thread.currentThread().getName();
	}
}
