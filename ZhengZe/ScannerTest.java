package ZhengZe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
/**
 * 主要介绍Scanner类
 * 具有正则表达式的功能，即对字符串的处理
 */
import java.util.Scanner;
/**
 * 
 * @ClassName:  ScannerTest   
 * @Description:用Scanner类进行字符串中的浮点数求和   
 * @author: 申梦杰 
 * @date:   2018年10月12日 下午11:21:49
 * @version 1.8.0   
 * @param    
 *
 */
public class ScannerTest {
	public static void main(String []args){
		//测试，结果会输出25.0
		String string = "12.0你好13.0中国";
        System.out.print(ScannerTest.getTotalScore(string));
        
	}
	/**
	 * 
	 * @Title: getTotalScore   
	 * @Description: 对string的正则操作   
	 * @param: @param s
	 * @param: @return      
	 * @return: double      
	 * @throws
	 */
    public static double getTotalScore(String s){     //利用Scanner类获取总分
        Scanner  scanner = new Scanner(s);
        scanner.useDelimiter("[^0123456789.]+");      //利用Scanner类中的正则处理字符串（这里相当于分割符），
                                                      //用非数字和点进行分割字符串
        double totalScore = 0;
        while(scanner.hasNext()){                     //遍历匹配到的结果
          double score = scanner.nextDouble();
            totalScore = totalScore + score;
        }
        
        return totalScore;
    }
    public static double getTotalScore(File file){     //利用Scanner类获取总分
    	BufferedReader bReader = null;
    	double allLineScore = 0;
    	try {
			bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		    String string = null;
		    while((string = bReader.readLine())!=null){
		    	double totalScore = ScannerTest.getTotalScore(string);  //这里对每一行进行分析
		    	System.out.print("总分"+string.valueOf(totalScore));
		    	allLineScore+=totalScore;
		    }
		    
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return allLineScore;    //将所有的file中所有的double数据相加返回
    
    }
}
