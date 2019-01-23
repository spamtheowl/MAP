package tuple;
import java.util.*;
public class MyTuple <F, S> implements MyITuple <F, S>
{
	Tuple<F, S> tuple;

	public MyTuple(F f, S s)
	{
		this.tuple = new Tuple<F, S>(f, s);
	}
	@Override
	public F getFirst()
	{
		return tuple.getLeft();
	}

	@Override
	public S getSecond() {
		return tuple.getRight();
	}
	public String toString()
	{
		return tuple.getLeft().toString() + " => " + tuple.getRight().toString();
	}
}
