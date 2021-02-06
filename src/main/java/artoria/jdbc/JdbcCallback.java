package artoria.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Jdbc callback.
 * @param <T> Call return type
 * @author Kahle
 */
public interface JdbcCallback<T> {

    /**
     * Callback will invoke.
     * @param connection Jdbc connection
     * @return Result you write
     * @throws SQLException Sql run error
     */
    T call(Connection connection) throws SQLException;

}
