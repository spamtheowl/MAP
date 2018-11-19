package dictionary;
import model.MyStmtException;

public interface MyIDictionary<T, U>
{
	void add(T v, U w);
	boolean isDefined(T v);
	void update(T v, U w);
	U lookup(T v) throws MyStmtException;
}
