package util;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.jcas.cas.TOP;

public class TypeUtils {
	
	/**
	 * Converts a List type to an FSList holding the same data
	 * 
	 * @param list the list to convert
	 * @param jcas the jcas with which to associate the resulting FSList
	 * @return the resulting FSList
	 */
	public static <T> FSList ListToFSListInCas(List<T> list, JCas jcas)
	{
		FSList next = new EmptyFSList(jcas);
		for(T el : new ListReverser<T>(list))
		{
			NonEmptyFSList node = new NonEmptyFSList(jcas);
			node.setTail(next);
			node.setHead((TOP) el);
			next = node;
		}
		return next;
	}

	/**
	 * Auxiliary method to simplify the process of instantiating a reflection method instance.
	 * @param <T>
	 *  
	 * @param className the name of the class whose method should be instantiated.
	 * @param methodName the name of the method to be instantiated.
	 * @param params the classes of the respective parameters to the method to be instantiated.
	 * @return the instantiated method.
	 */
	public static <T> Method instantiateMethod(Class<T> cls, String methodName, Class<?>... params)
	{
		try 
		{
			return cls.getMethod(methodName, params);
		} 
		catch(Throwable e)
		{
			throw new IllegalArgumentException("Error: " + e.getMessage() +
						"\nCould not instantiate method " + cls.getName() + "." + methodName);
		}
	}
	
	/**
	 * Adds TOP el to an FSList list and returns the updated FSList, associated with JCas jcas.
	 * 
	 * @param list the FSList to which to add el.
	 * @param el the TOP to add to list.
	 * @param jcas the JCas with which to associate created FSList objects.
	 * @return the updated FSList.
	 */
	public static FSList addToFSList(FSList list, TOP el, JCas jcas)
	{
		NonEmptyFSList next = new NonEmptyFSList(jcas);
		next.setHead(el);
		next.setTail(list);
		return next;
	}
	
	/**
	 * Finds the first TOP in the FSList list that checks out under the checkMethods.
	 * If no such element is matched, returns null.
	 * 
	 * @param list the FSList to search for a matching TOP
	 * @param checkMethods the CheckMethod conditions with which to identify matching instances
	 * @return the matched element (if any), else null
	 */
	public static <T> TOP getFromFSList(FSList list, Class<?> cls, CheckMethod... checkMethods)
	{
		//System.out.println("        Calling getAllFromFSList...");
		List<TOP> matched = getAllFromFSList(list, cls, checkMethods);
		if(matched.size() > 0)
		{
			return matched.get(0);
		}
		return null;
	}
	
	/**
	 * Finds all TOP in the FSList list that check out under each of the RTMethod checkMethods.
	 * If no such elements are matched, returns an empty List.
	 * 
	 * @param list the FSList to search for matching TOP
	 * @param checkMethods the CheckMethod conditions required to identify matching instances
	 * @return a List<TOP> of the matched elements (if any)
	 */
	public static <T> List<TOP> getAllFromFSList(FSList list, Class<?> type, CheckMethod... checkMethods)
	{
		List<TOP> results = new LinkedList<TOP>();
		while(list instanceof NonEmptyFSList)
		{
			//System.out.println("          Identified head.");
			TOP el = (TOP) ((NonEmptyFSList) list).getHead();
			try
			{
				//See if the element checks out okay
				for(CheckMethod checkMethod : checkMethods)
				{
					if(((Integer) checkMethod.invoke(type.cast(el)))!=0)
					{
						//System.out.println("          Head did not meet check requirements.");
						continue;
					}
				}
				results.add(el);
			}
			catch(Throwable e)
			{
				//Whatever happened, this element obviously did not match
				//System.out.println("            Element could not be thoroughly checked by " + checkMethods + ".");
			}
			//Move on to next element
			list = ((NonEmptyFSList) list).getTail();
		}
		return results;
	}
}