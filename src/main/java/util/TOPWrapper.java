package util;

import org.apache.uima.jcas.cas.TOP;

import type.PAtN;

/**
 * Wrapper class implementing useful methods for interfacing with uima TOP objects
 * 
 * @author maki
 */

public class TOPWrapper{
	
	TOP obj;
	Class<?> cls;
	
	public TOPWrapper(TOP top)
	{
		this.obj = top;
		this.cls = top.getClass();
	}
	
	public Class<?> getTop()
	{
		return (Class<?>) cls.cast(this.obj);
	}
	
	/**
	 * Assert that this TOP is an instance of the given Class<?> cls
	 * 
	 * @param cls
	 * @return 
	 */
	public boolean assertClass(Class<?> cls)
	{
		if(cls.isInstance(this.obj))
		{
			System.out.println(((PAtN) cls.cast(this.obj)).getN());
		}
		return cls.isInstance(this.obj);
	}
	
	public Object getCast(Class<?> cls)
	{
		if(assertClass(cls))
		{
			return cls.cast(this.obj);
		}
		else
		{
			throw new ClassCastException("Cannot cast " + this.obj.getClass() + " to " + cls + ".");
		}
	}
	
	/**
	 * Compares a field of this TOP with the Comparable value passed in
	 * @param <T>
	 * 
	 * @param fieldGetter
	 * @param value
	 * @return
	 */
	public <T> boolean compareField(CheckMethod fieldGetter, CheckMethod comparison)
	{
		Class<?> value = null;
		try {
			value = (Class<?>) fieldGetter.invoke(this.cls.cast(this.obj));
			System.out.print(value.toString());
			return (Integer) comparison.invoke(fieldGetter.getReturnType().cast(value)) == 0;
		} catch (Throwable e) {
			throw new IllegalArgumentException("Error: " + e.getMessage());
		}
	}
		
	/**
	 * Allows the logical combination of several boolean query methods evaluated on this TOP. 
	 * @param obj
	 * @param methods
	 * @return
	 */
	public <T> boolean logicalAnd(CheckMethod... methods)
	{
		boolean result = true;
		for(CheckMethod method : methods)
		{
			System.out.println(this.obj.getClass());
			if(this.cls.isAssignableFrom(this.getClass())){
				result &= (Boolean) method.invoke(this.cls.cast(this));
			}
			else
			{
				result &= (Boolean) method.invoke(getCast(this.cls));
			}
			if(!result)
				break;
		}
		return result;
	}
	
	public String toString()
	{
		return this.cls.cast(this.obj).toString();
	}
	
}
