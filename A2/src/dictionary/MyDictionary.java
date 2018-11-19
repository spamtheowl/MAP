package dictionary;
import java.util.*;
import model.*;

public class MyDictionary<T, U> implements MyIDictionary<T, U>
{
	HashMap<T, U> dictionary;
	
	public MyDictionary()
	{
		dictionary = new HashMap<T, U>();
	}
	
	public void add(T v, U w)
	{
		dictionary.put(v, w);
	}
	public boolean isDefined(T v)
	{
		for(T key: dictionary.keySet())
		{
			if (key == v)
			{
				return true;
			}
		}
		return false;
	}
	public void update(T v, U w)
	{
		//try catch here
		dictionary.put(v, w);
	}
	public U lookup(T v) //throws MyStmtException
	{
//		if (isDefined(v))
//		{
			return dictionary.get(v);
		//}
//		else
//		{
//			throw new MyStmtException("Undefined variable");
//		}
	}
	public String toString()
	{
		String result = "";
		for (T key: dictionary.keySet())
		{
			result +=  key.toString() + "=>" + dictionary.get(key).toString() +"; ";
		}
		return result;
	}
}
