package com.itheima.test;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.Test;

import java.sql.*;

public class TestJdbc {
    @Test
    public void test01() throws Exception {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.80.88:1521:orcl";
        String username = "scott";
        String password = "tiger";
        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "select * from emp where empno = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 7788);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String ename = resultSet.getString("ename");
            System.out.println(ename);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void test02() throws Exception {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.80.88:1521:orcl";
        String username = "scott";
        String password = "tiger";
        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, username, password);

        CallableStatement pstm = connection.prepareCall("{call proc_countyearsal(?, ?)}");
        //给参数赋值
        pstm.setObject(1, 7788);
        pstm.registerOutParameter(2, OracleTypes.VARCHAR);
        //执行数据库查询操作
        pstm.execute();
        //输出结果[第二个参数]
        System.out.println(pstm.getObject(2));
        //释放资源
        pstm.close();
        connection.close();
       /* CallableStatement callableStatement = connection.prepareCall("{call proc_countyearsal(?,?)");
        callableStatement.setInt(1,7788);
        callableStatement.registerOutParameter(2, OracleTypes.NUMBER);
        callableStatement.execute();
        System.out.println(callableStatement.getObject(2).toString());*/
    }
}
