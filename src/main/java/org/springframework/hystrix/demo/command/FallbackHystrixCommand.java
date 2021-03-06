package org.springframework.hystrix.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FallbackHystrixCommand extends HystrixCommand<String>{
	
	private final String name;
	public FallbackHystrixCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
		this.name = name;
	}
	
	@Override
	protected String run() {
		try { 
			Thread.sleep(1000 * 10); 
			} catch (InterruptedException e) {
				e.printStackTrace(); 
			}
		return this.name + ": " + Thread.currentThread().getName();
	}
	
	/**
	 * 回退
	 */
	 @Override 
	 protected String getFallback() { 
		 return "失败了"; 
	 }
	
}
