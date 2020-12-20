package artoria.common;

import java.io.Serializable;

/**
 * Uniform data input object.
 * @param <T> Data Type
 * @author Kahle
 */
public class Input<T> implements Serializable {
    private Object currentUser;
    private Object targetUser;
    private Paging paging;
    private T data;

    public Input() {
    }

    public Input(T data) {

        this.data = data;
    }

    public Input(Paging paging, T data) {
        this.paging = paging;
        this.data = data;
    }

    public Object getCurrentUser() {

        return currentUser;
    }

    public void setCurrentUser(Object currentUser) {

        this.currentUser = currentUser;
    }

    public Object getTargetUser() {

        return targetUser;
    }

    public void setTargetUser(Object targetUser) {

        this.targetUser = targetUser;
    }

    public Paging getPaging() {

        return paging;
    }

    public void setPaging(Paging paging) {

        this.paging = paging;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {

        this.data = data;
    }

}
