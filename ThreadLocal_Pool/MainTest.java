package ThreadLocal_Pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.omg.CORBA.PRIVATE_MEMBER;

public class MainTest {
	public static void main(String[] args) {
	    ExecutorService executor = Executors.newCachedThreadPool();
	    for (int i = 0; i < 100; i++) {
	        executor.execute(() -> {
	        	//这里也可以新建ThreadLocalVariableHolder变量
	        	//但最好使用static调用的方式，因为，防止在一个线程中重复的创建一个对象
	            try {
	                long threadId = Thread.currentThread().getId();
	                int before = ThreadLocalVariableHolder.getValue();
	                ThreadLocalVariableHolder.increment();
	                int after = ThreadLocalVariableHolder.getValue();
	                System.out.println("threadId: " + threadId + ", before: " + before + ", after: " + after);
	            } finally {
	                // 清理线程本地存储(这一步在线程中一定要做)
	            	ThreadLocalVariableHolder.remove();
	            }
	        });
	    }

	    executor.shutdown();
	}
}
