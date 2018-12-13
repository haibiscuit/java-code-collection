package MyThreadLocal;
/**
 * 
 * @ClassName:  MyThreadLocal   
 * @Description:自己实现ThreadLocal   
 * @author: 申梦杰 
 * @date:   2018年10月14日 下午2:26:03
 * @version 1.8.0   
 * @param    
 *
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal<T> {
	/**
	 * ThreadLocal里维护一个Map数据集合(记住在一个线程中如果使用ThreadLocal得到数据后一定要在当前的线程调用remove方法，
	 *                                                           否则线程结束，该线程对应的value值讲无法获得)
	 */
	private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread,T>());
	
	/**
	 * 
	 * @Title: set   
	 * @Description: 向map里添加值  (Thread为当前线程，value为当前线程的值)
	 * @param: @param value      
	 * @return: void      
	 * @throws
	 */
	public void set(T value){
		container.put(Thread.currentThread(), value);		
	}
	/**
	 * 
	 * @Title: get   
	 * @Description: 以当前的线程为键值，拿出value值，如果当前map中没有当前thread的键值，则初始化调用initValue的方法。   
	 * @param: @return      
	 * @return: T      
	 * @throws
	 */
	public T get(){
		Thread thread = Thread.currentThread();
		T value = container.get(thread);
		if(value == null && !container.containsKey(thread)){
			System.out.println("我是空值，我被调用了！");
			value = initialValue();
			container.put(thread, value);
		}
		return value;
	}
	/**
	 * 
	 * @Title: remove   
	 * @Description: 以当前线程为键值从map中删除掉当前线程对应的值   
	 * @param:       
	 * @return: void      
	 * @throws
	 */
    public void remove(){
    	System.out.println("当前线程的数据被清除了！");
    	container.remove(Thread.currentThread());
    }
    /**
     * 
     * @Title: initialValue   
     * @Description: 使用protected方法可以在创建实例的时候重写方法   
     * @param: @return      
     * @return: T      
     * @throws
     */
    protected T initialValue(){
    	return null;
    }
}
