package MyThreadLocal;

import javax.annotation.Resource;

/**
 * 
 * @ClassName:  ClientThread   
 * @Description:MyThreadLocal的线程测试类   
 * @author: 申梦杰 
 * @date:   2018年10月14日 下午3:29:38
 * @version 1.8.0   
 * @param    
 *
 */
public class ClientThread extends Thread{
	@Resource
    private Sequence sequence;
    public ClientThread(Sequence sequence){
    	this.sequence = sequence;
    }
    /**
     * 
     * @Title: run   
     * @Description: 当前线程循环测试值 
     * @param:       
     * @return: void      
     * @throws
     */
    @Override
    public void run(){
        for(int i = 0;i < 3;i++){
        	System.out.println(Thread.currentThread().getName()+"=>"+sequence.getNumber());
        	//如果添加这句话，就会全部输出1
        	//sequence.remove();
        }	
        //在线程中执行完之后，必须要执行这句话，在线程没有结束之前，将ThreadLocalMap中当前线程对应的value值消除掉，否则线程结束，将在map中找不到对应的键值，因为线程已经死亡
        sequence.remove();
    }
}
