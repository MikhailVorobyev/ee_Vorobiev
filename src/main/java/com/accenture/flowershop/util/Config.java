package com.accenture.flowershop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File(getHomeDir(), "config\\h2.properties");
    private final ConnectionFactory connectionFactory;
    private static final Config INSTANCE = new Config();

    private Config() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        try(InputStream is = new FileInputStream(PROPS)){
            Properties props = new Properties();
            props.load(is);
            connectionFactory = () ->
                    DriverManager.getConnection(props.getProperty("db.url"),
                            props.getProperty("db.username"),
                            props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static Config get() {
        return INSTANCE;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    private static File getHomeDir() {
        String prop = System.getProperty("homeDir");
        File homeDir = new File(prop == null ? "." : prop);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + " is not directory");
        }
        return homeDir;
    }
}
