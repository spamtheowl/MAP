package dictionary;
import java.util.*;
import java.util.Map.Entry;
import model.*;

public interface MyIDictionary<T, U>
{
	void add(T v, U w);
	void delete(T v, U w);
	boolean isDefined(T v);
	void update(T v, U w);
	U lookup(T v) throws MyStmtException;
	boolean isDefinedV(U w);
	Collection<T> keySet();
	Collection<U> values();
	boolean isEmpty();
	Map<T, U> getContent();
	void setContent(Map<T, U> map);
	MyIDictionary<T, U> clone();
	Set<Entry<T, U>> entrySet();
}
