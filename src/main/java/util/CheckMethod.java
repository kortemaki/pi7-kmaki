package util;

import java.lang.reflect.Method;


public class CheckMethod<T extends Comparable<T>> {
	/**
	 * Bundler class holding a method and its parameters
	 * Can be invoked in the same way as a usual Method
	 * 
	 * @author maki
	 */
	private Method method;
	@SuppressWarnings("rawtypes")
	private Comparable value;
	private Class<? extends Comparable<T>> clazz;

	@SuppressWarnings("rawtypes")
	public CheckMethod(Method method, Comparable value, Class<? extends Comparable<T>> clazz)
	{
		this.method = method;
		this.value = value;
		this.clazz = clazz;
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> Object invoke(T t)
	{
		//if(DEBUG)
		//	System.out.println("Invoking "+this.method+".");
		try {
			Object comparison = (this.value).compareTo(this.clazz.cast(this.method.invoke(t)));
			//System.out.println(this.value + ".compareTo( " + this.method.invoke(t) + " ) = " + comparison);
			return comparison;
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
