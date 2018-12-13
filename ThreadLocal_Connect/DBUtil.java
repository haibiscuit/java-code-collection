package ThreadLocal_Connect;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *     (
 *     注意一下几点：
 *         1.关闭数据库连接
 *         2.清楚ThreadLocalMap中的数据
 *     )
 * @ClassName:  DBUtil   
 * @Description:ThreadLocal测试类(记得在ThreadLocal中获取值之后，在线程的最后将该线程对应的从ThreadLocalMap中移除掉
 *                                                                             即调用ThreadLocal::remove()方法 )   
 * @author: 申梦杰 
 * @date:   2018年10月14日 下午4:50:33
 * @version 1.8.0   
 * @param    
 *
 */
public class DBUtil {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String username = "haibiscuit";
    private static final String password = "a635941266";
    //定义一个用于放置数据库连接的      局部线程变量(一个ThreadLocal维护一个ThreadLocalMap集合，
    //                                 也就是说一个Thread为键值，对应一个Connection，存放在TheadLocalMap中)
    private static ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();
    
    //获取连接(数据get完之后记得使用ThreadLocal::remove方法)
    public static Connection getConnection(){
    	Connection connection = connContainer.get();
    	try{
    		if(connection == null){
    			Class.forName(driver);
    			//这里可以从连接池中获取连接
    			connection = DriverManager.getConnection(url, username, password);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		connContainer.set(connection);
    	}
    	return null;
    }
    //关闭连接(这里要记住，在哪个线程里创建的连接就在哪个线程里关闭，也就是说ThreadLocalMap中以当前的线程为键值，
    //                                                Map中只会存放一个以该线程为键的值)
    public static void closeConnection(){
    	Connection connection = connContainer.get();
        try {
            if(connection != null){
            	connection.close();
            }			
		} catch (Exception e) {
			//这里也非常的重要，在关闭线程时，记得从ThreadLocalMap中删除掉该线程对应的Connection值，
			//                        防止在线程池使用时出现数据库事务上的问题
			connContainer.remove();
		}	
    }
}
