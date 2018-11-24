package stack;
import java.util.Stack;

public class MyStack <T> implements MyIStack <T>
{
	Stack<T> stack;
	
	public MyStack ()
	{
		this.stack = new Stack<T>();
	}
	@Override
	public T pop()
	{
		return stack.pop();
	}

	@Override
	public void push(T v)
	{
		stack.push(v);
	}

	@Override
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}
	
	public String toString()
	{
		String result = "\n";
		for (int i = stack.size() - 1; i >= 0; i--)
			result += stack.get(i).toString() + "\n";
		return result;
	}
}
