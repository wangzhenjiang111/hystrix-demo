package org.springframework.hystrix.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class BaseHystrixCommand extends HystrixCommand<String>{
	
	private final String name;
	public BaseHystrixCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
		this.name = name;
	}
	
	@Override
	protected String run() {
		System.err.println("get data");
		return this.name + ": " + Thread.currentThread().getName();
	}
	
	@Override
	protected String getCacheKey() {
		return String.valueOf(this.name);
	}
}
