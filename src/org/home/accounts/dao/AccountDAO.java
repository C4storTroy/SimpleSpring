package org.home.accounts.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.home.accounts.model.Account;
import org.home.accounts.model.TypeAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {

    private Connection connection;

    @Autowired
    public AccountDAO(BasicDataSource ds) {
        try{
            this.connection = ds.getConnection();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void add(Account account) {
        String sql = "insert into accounts (description, pay, value, type) values (?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getDescription());
            stmt.setBoolean(2, account.isPaid());
            stmt.setDouble(3, account.getValue());
            stmt.setString(4, account.getType().name());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(Account account) {

        if (account.getId() == null) {
            throw new IllegalStateException("Account Id must be not null.");
        }

        String sql = "delete from accounts where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, account.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void change(Account account) {
        String sql = "update accounts set description = ?, pay = ?, payDay = ?, type = ?, value = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getDescription());
            stmt.setBoolean(2, account.isPaid());
            stmt.setDate(3, account.getPayDay() != null ? new Date(account
                    .getPayDay().getTimeInMillis()) : null);
            stmt.setString(4, account.getType().name());
            stmt.setDouble(5, account.getValue());
            stmt.setLong(6, account.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account> list() {
        try {
            List<Account> account = new ArrayList<Account>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from accounts");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // add account on the list
                account.add(fillAccount(rs));
            }

            rs.close();
            stmt.close();

            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account searchForId(Long id) {


        if (id == null) {
            throw new IllegalStateException("Account Id must be not null.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from accounts where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return fillAccount(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pay(Long id) {

        if (id == null) {
            throw new IllegalStateException("Account Id must be not null.");
        }

        String sql = "update accounts set pay = ?, payDay = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
            stmt.setLong(3, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Account fillAccount(ResultSet rs) throws SQLException {
        Account account = new Account();

        account.setId(rs.getLong("id"));
        account.setDescription(rs.getString("description"));
        account.setPaid(rs.getBoolean("pay"));
        account.setValue(rs.getDouble("value"));

        Date data = rs.getDate("payDay");
        if (data != null) {
            Calendar payDay = Calendar.getInstance();
            payDay.setTime(data);
            account.setPayDay(payDay);
        }

        account.setType(Enum.valueOf(TypeAccount.class, rs.getString("type")));

        return account;
    }
}
