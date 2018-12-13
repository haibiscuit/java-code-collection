package MyThreadLocal;

/**
 * 
 * @ClassName:  SequenceImpl   
 * @Description:自己实现ThreadLocal(一般使用threadlocal是独自放在一个类中为了
 *                                1.实现获取数据
 *                                2.删除数据
 *                                )   
 * @author: 申梦杰 
 * @date:   2018年10月14日 下午2:26:36
 * @version 1.8.0   
 * @param    
 *
 */
public class SequenceImpl implements Sequence{
    private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>(){
    	@Override
    	protected Integer initialValue(){
    		return 0;
    	}
    };
    
	
	@Override
	public int getNumber() {
		numberContainer.set(numberContainer.get()+1);
		return numberContainer.get();
	}
	/**
	 * 
	 * <p>Title: remove</p>   
	 * <p>Description: </p>      
	 * @see MyThreadLocal.Sequence#remove()
	 * 在线程中执行完之后，必须要执行这句话，在线程没有结束之前，将ThreadLocalMap中当前线程对应的value值消除掉，否则线程结束，将在map中找不到对应的键值，因为线程已经死亡
	 */
	public void remove(){
		numberContainer.remove();
	}
}
