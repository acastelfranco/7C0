package dao;

import java.util.List;

public interface Dao<T,K>
{
	    T get(K key);
	    
	    List<T> getAll();
	    
	    int save(T t);
	    
	    int update(T t);
	    
	    int delete(K key);
}
