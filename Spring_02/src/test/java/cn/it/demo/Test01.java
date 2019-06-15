package cn.it.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Test01 {

    @Test
    public void show() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("984683");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }
    @Test
    public void tt() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("984683");
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    public void t1() throws Exception {
        //获取配置文件信息
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");

        //druid 加载配置文件
       /* DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();*/

       //c3p0c 加载配置文件
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

    @Test
    public void druidDemo() throws Exception {
       ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource dds = app.getBean(DruidDataSource.class);
        DruidPooledConnection connection = dds.getConnection();
        System.out.println(connection);
        connection.close();

    }

    @Test
    public void c3p0Demo() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ComboPooledDataSource cpd = app.getBean(ComboPooledDataSource.class);
        Connection connection = cpd.getConnection();
        System.out.println(connection);
        connection.close();

    }
}
