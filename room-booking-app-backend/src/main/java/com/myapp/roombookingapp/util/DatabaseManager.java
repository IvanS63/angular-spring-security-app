package com.myapp.roombookingapp.util;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Util class for running H2 console-UI for debugging purposes.
 *
 * @author Ivan_Semenov
 */
@Component
public class DatabaseManager {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void startDBManager() throws SQLException {
        //get url from generated DB name
        String url = dataSource.getConnection().getMetaData().getURL();
        //hsqldb
        DatabaseManagerSwing.main(new String[]{"--url", url, "--user", "sa", "--password", ""});
    }
}