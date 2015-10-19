package util;

import java.lang.reflect.Method;


public class CheckMethod {
	/**
	 * Bundler class holding a method and its parameters
	 * Can be invoked in the same way as a usual Method
	 * 
	 * @author maki
	 */
	private Method method;
	@SuppressWarnings("rawtypes")
	private Comparable value;
		
	@SuppressWarnings("rawtypes")
	public CheckMethod(Method method, Comparable value)
	{
		this.method = method;
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Object invoke(T t)
	{
		//if(DEBUG)
		//	System.out.println("Invoking "+this.method+".");
		try {
			return (this.value).compareTo(this.method.invoke(t));
		} catch (Throwable e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public String toString()
	{
		return this.method.toString();
	}

	public Class<?> getReturnType() {
		return this.method.getReturnType();
	}
}
