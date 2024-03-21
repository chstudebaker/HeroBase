package persistance;
import java.util.List;

public interface GenericDao<T> {
    List<T> getAll();
    T getById(int id);
    int insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}
