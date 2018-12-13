package String;
/**
 *StringTokenizer类相当于高级的Split()方法，进行字符串的分割
 *
 */

import java.util.StringTokenizer;

public class StringTokenizer_demo1 {
    public static void main(String []ars){
    	//测试一：进行字符串中数字的求和
    	 String  s1 = "苹果：56.9圆，香蕉：12圆，芒果：19.8圆";
         ComputePrice jisuan = new ComputePrice();
         String regex = "[^123456789.]+";   //匹配字符串中的数字的正则表达式
         
         String  s1_number = s1.replaceAll(regex, "*");   //求出S1中商品的价格总和
         double priceSum = jisuan.compute(s1_number, "*");
          System.out.printf("\" %s \"价格总和： %f 圆\n",s1,priceSum);
          
          //测试二：更高级的测试
          StringTokenizer fenxi = new StringTokenizer("you  ,are;welcome,", ",;");
          StringBuilder strb = new StringBuilder();
          while(fenxi.hasMoreTokens()){
              strb.append(fenxi.nextToken());
          }
          System.out.println(strb.toString());
    }
}
/**
 * 
 * @ClassName:  ComputePrice   
 * @Description:封装对字符串中的数字进行求和  
 * @author: 申梦杰 
 * @date:   2018年9月25日 下午4:42:51
 * @version 1.8.0   
 * @param    
 *
 */
class ComputePrice{
   double  compute(String s,String fenge){
       StringTokenizer  fenxiOne = new StringTokenizer(s,fenge);
       double sum = 0;
       double digitItem = 0;
       while(fenxiOne.hasMoreTokens()){
           String str = fenxiOne.nextToken();
           digitItem = Double.parseDouble(str);
           sum = sum+digitItem;
       }
       return sum;
   }
}