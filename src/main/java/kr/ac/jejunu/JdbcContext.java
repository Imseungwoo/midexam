package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    Product jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product Product = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Product = new Product();
                Product.setId(resultSet.getLong("id"));
                Product.setTitle(resultSet.getString("title"));
                Product.setPrice(resultSet.getInt("price"));
            }
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return Product;
    }

    Long jdbcContextForInsert(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            id = resultSet.getLong(1);
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return id;
    }

    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}