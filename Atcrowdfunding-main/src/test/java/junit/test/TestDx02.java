/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:TestDx02.java 
 * Package Name:junit.test 
 * Date:2019年7月9日下午9:36:24 
 * 
 */  
      
package junit.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.atguigu.arcrowdfunding.util.HttpUtils;

/** 
* @ClassName: TestDx02 
* @Description: 测试短信
* @author Chen Jiang Lin 
* @date 2019-07-09 21:36
*  
*/
public class TestDx02 {

	public static void main(String[] args) {
	    String host = "http://dingxin.market.alicloudapi.com";
	    String path = "/dx/sendSms";
	    String method = "POST";
	    String appcode = "1948fa6afc674ea3bc2fea47f36e1108";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    
	    StringBuilder authcode = new StringBuilder();
	    for (int i = 0; i < 4; i++) {
			authcode.append(new Random().nextInt(10));
		}
	    System.out.println(authcode);
	    
	    querys.put("mobile", "17324216282");
	    querys.put("param", "code:"+authcode);
	    querys.put("tpl_id", "TP1711063");
	    Map<String, String> bodys = new HashMap<String, String>();


	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
}
  
