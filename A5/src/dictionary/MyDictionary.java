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
	public void delete(T v, U w)
	{
		dictionary.remove(v, w);
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
	public boolean isDefinedV(U w)
	{
		for(U val: dictionary.values())
		{
			if(w == val)
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
	public U lookup(T v) throws MyStmtException
	{
		if (isDefined(v))
		{
			return dictionary.get(v);
		}
		else
		{
			throw new MyStmtException("Undefined variable");
		}
	}
	public String toString()
	{
		String result = "\n";
		for (T key: dictionary.keySet())
		{
			result +=  key.toString() + "=>" + dictionary.get(key).toString() +"\n";
		}
		return result;
	}
	public Collection<T> keySet()
	{
		return dictionary.keySet();
	}
	public Collection<U> values()
	{
		return dictionary.values();
	}
	public boolean isEmpty()
	{
		return dictionary.isEmpty();
	}
	public Map<T, U> getContent()
	{
		Map<T, U> map = new HashMap<T, U>(dictionary);
		return map;
	}
	public void setContent(Map<T, U> map)
	{
		dictionary = (HashMap<T, U>) map;
	}
}
