package artoria.cache;

import artoria.exception.ExceptionUtils;
import artoria.util.Assert;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * No cache.
 * @author Kahle
 */
public class NoCache implements Cache {
    private final String name;

    public NoCache(String name) {
        Assert.notBlank(name, "Parameter \"name\" must not blank. ");
        this.name = name;
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public Object getNativeCache() {

        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> callable) {
        try {
            return callable.call();
        }
        catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    @Override
    public <T> T get(Object key, Class<T> type) {

        return null;
    }

    @Override
    public Object get(Object key) {

        return null;
    }

    @Override
    public boolean containsKey(Object key) {

        return false;
    }

    @Override
    public int size() {

        return 0;
    }

    @Override
    public Object put(Object key, Object value) {

        return null;
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {

        return null;
    }

    @Override
    public void putAll(Map<?, ?> map) {

    }

    @Override
    public boolean expire(Object key, long timeToLive, TimeUnit timeUnit) {

        return false;
    }

    @Override
    public boolean expireAt(Object key, Date date) {

        return false;
    }

    @Override
    public boolean persist(Object key) {

        return false;
    }

    @Override
    public Object remove(Object key) {

        return null;
    }

    @Override
    public void removeAll(Collection<?> keys) {

    }

    @Override
    public void clear() {

    }

    @Override
    public int prune() {

        return 0;
    }

    @Override
    public Collection<Object> keys() {

        return null;
    }

    @Override
    public Map<Object, Object> entries() {

        return null;
    }

}
