package org.home.accounts.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.home.accounts.ConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfraController {

    @RequestMapping("/tables")
    public String buildTables() throws SQLException {
        Connection c = new ConnectionFactory().getConnection();
        PreparedStatement st1 = c.prepareStatement("drop table accounts if exists");
        st1.execute();

        PreparedStatement st11 = c.prepareStatement("create table accounts (id int identity, description varchar(255), value double, pay boolean, payDay datetime, type varchar(20))");
        st11.execute();

        PreparedStatement st2 = c.prepareStatement("drop table users if exists");
        st2.execute();

        PreparedStatement st22 = c.prepareStatement("create table users (login VARCHAR(255),password VARCHAR(255));");
        st22.execute();

        PreparedStatement st3 = c.prepareStatement("insert into users (login, password) values ('home', 'online');");
        st3.execute();

        c.close();

        return "infra-ok";

    }
}
