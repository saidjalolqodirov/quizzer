package uz.test.generic;

import java.io.Serializable;

public interface GenericEntity<T> extends Serializable {
    T getId();

    boolean isDeleted();

}
