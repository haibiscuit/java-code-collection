package ZhengZe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 主要介绍了正则的四种方法：
 * 1.匹配方法        matches（）方法
 * 2.切割方法        split（）方法
 * 3.替换方法        replaceAll()方法
 * 4.获取                matcher()方法
 */

/**
 * 
 * @ClassName:  ZhengZe_demo1   
 * @Description:介绍java正则表达式中的常用的方法   
 * @author: 申梦杰 
 * @date:   2018年9月25日 下午7:23:25
 * @version 1.8.0   
 * @param    
 *
 */
public class ZhengZe_demo1 {
	public static void main(String args[]){
		//1.匹配方法      matches（）方法
		   String qq = "123a45664";

	        String regex = "[1-9]\\d{4,14}";

	        boolean flag = qq.matches(regex);
	        if(flag)
	            System.out.println(qq+"...is ok");
	        else
	            System.out.println(qq+"... 不合法");
	        
	   //2.切割方法         split（）方法
	        String str = "avg   bb   geig   glsd   abc";
	        String reg = " +";        //按照多个空格来进行切割
	        String[] arr = str.split(reg);  
	        System.out.println(arr.length);
	        for(String s : arr)
	        {
	            System.out.println(s);
	        }
	   //3.替换方法        replaceAll()方法

	        String str1 = "erkktyqqquizzzzzo";//将叠词替换成$.  //将重叠的字符替换成单个字母。zzzz->z
	 
	        str = str1.replaceAll("(.)\\1+","$1");

	        System.out.println(str);
	        // erktyquizo
	   //4.获取      matcher()方法
	        String str2 = "yin yu shi wo zui cai de yu yan";
	        System.out.println(str);
	        String reg2 = "\\b[a-z]{3}\\b";//匹配只有三个字母的单词

	        //将规则封装成对象。
	        Pattern p = Pattern.compile(reg2);

	        //让正则对象和要作用的字符串相关联。获取匹配器对象。
	        Matcher m  = p.matcher(str2);
	        
	        //m.find()和m.group()放在一起用
	        while(m.find())                      //遍历匹配到的结果
	        {
	            System.out.println(m.group());   //输出匹配到的子字符串
	            System.out.println(m.start()+"...."+m.end());
	                // start()  字符的开始下标（包含）
	                //end()  字符的结束下标（不包含）
	        }
	}
}
