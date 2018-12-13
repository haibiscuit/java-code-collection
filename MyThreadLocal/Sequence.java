package MyThreadLocal;
/**
 * 
 * @ClassName:  Sequence   
 * @Description:实现自己的ThreadLocal类   
 * @author: 申梦杰 
 * @date:   2018年10月14日 下午2:15:43
 * @version 1.8.0   
 * @param    
 *
 */
public interface Sequence {
  int getNumber();
  void remove();
}
