package org.home.accounts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.home.accounts.ConnectionFactory;
import org.home.accounts.model.User;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existUsuario(User user) {

        if(user == null) {
            throw new IllegalArgumentException("User must be not null");
        }

        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from users where login = ? and password = ?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();

            boolean find = rs.next();
            rs.close();
            stmt.close();

            return find;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User must be not null");
        }

        try {
            PreparedStatement stmt = this.connection.prepareStatement("insert into users (login,password) values (?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

