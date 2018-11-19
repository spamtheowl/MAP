package list;
import java.util.*;

public class MyList<T> implements MyIList <T>
{
	ArrayList<T> list;

	public MyList()
	{
		list = new ArrayList<T>();
	}
	@Override
	public void add(T v)
	{
		list.add(v);
	}
	
	public String toString()
	{
		String result = "";
		
		for(int i = 0; i < list.size(); i++)
		{
			result += list.get(i).toString() + "; ";
		}
		return result;
	}
}
