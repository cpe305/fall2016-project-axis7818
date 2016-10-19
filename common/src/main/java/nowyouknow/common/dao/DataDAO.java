package nowyouknow.common.dao;

public abstract class DataDAO<T> {
	public abstract void save(T item);
	public abstract Queryset<T> objects();
}
