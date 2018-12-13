package Queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @ClassName:  DelayQueue_Demo1   
 * @Description:DelayQueue类测试
 * @author: 申梦杰 
 * @date:   2018年10月27日 下午10:15:15
 * @version 1.8.0   
 * @param    
 *
 */
public class DelayQueue_Demo1 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DelayQueue<DelayedElement> dq = new DelayQueue<DelayedElement>();
		long now = System.currentTimeMillis();
		System.out.println("current time in ms: " + now);
		DelayedElement ob1 = new DelayedElement("e1", now + 5000);
		DelayedElement ob2 = new DelayedElement("e2", now + 6000);
		DelayedElement ob3 = new DelayedElement("e3", now + 7500);

		dq.add(ob1);
		dq.add(ob2);
		dq.add(ob3);

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		while (dq.size() > 0) {
			try {
				DelayedElement e = dq.take();
				System.out.println("current time in ms: " + System.currentTimeMillis() + ", element:" + e.name);

			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}

	}
}
/**
 * 
 * @ClassName:  DelayedElement   
 * @Description:实现Delayed接口类  
 * @author: 申梦杰 
 * @date:   2018年10月27日 下午10:14:42
 * @version 1.8.0   
 * @param    
 *
 */

class DelayedElement implements Delayed {
		public long time;
		public String name;

		public DelayedElement(String name, long time) {
			this.name = name;
			this.time = time;
		}
        /**
         * 
         * <p>Title: compareTo</p>   
         * <p>Description: DelayQueue时间time到的时候才能从queue中拿出来</p>   
         * @param o
         * @return   
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
		@Override
		public int compareTo(Delayed o) {
			// TODO Auto-generated method stub
			if (this.time < ((DelayedElement) o).time)
				return -1;
			else if (this.time > ((DelayedElement) o).time)
				return 1;
			else
				return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			// TODO Auto-generated method stub
			long r = unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
			 System.out.println("delay:" + r);
			return r;
		}

	}
