package MyThreadLocal;

/**
 * 
 * @ClassName:  MainTest   
 * @Description:MyThreadLocal测试类   
 * @author: 申梦杰 
 * @date:   2018年10月14日 下午3:28:26
 * @version 1.8.0   
 * @param    
 *
 */
public class MainTest {
    public static void main(String []args){
    	SequenceImpl sequence = new SequenceImpl();
    	ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);
        /**
         * 启动线程，如果没有使用ThreadLocal得到数据会输出1-9
         */
        thread1.start();
        thread2.start();
        thread3.start();
       
    }
}
