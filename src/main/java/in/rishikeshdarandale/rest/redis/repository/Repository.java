package in.rishikeshdarandale.rest.redis.repository;

import java.util.List;

import in.rishikeshdarandale.rest.model.BaseModel;

public interface Repository<K, V extends BaseModel> {
    void put(V obj);
    V get(K key);
    void delete(K key);
    List<V> getAll();
}
