/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:TestDx.java 
 * Package Name:junit.test 
 * Date:2019年7月9日下午5:39:00 
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
* @ClassName: TestDx 
* @Description: 测试短信
* @author Chen Jiang Lin 
* @date 2019-07-09 17:39
*  
*/
public class TestDx {
	
	public static void main(String[] args) {
	    String host = "https://cxkjsms.market.alicloudapi.com";
	    String path = "/chuangxinsms/dxjk";
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
	    querys.put("content", "【创信】你的验证码是："+authcode+"，3分钟内有效！");
        //测试可用短信模板：【创信】你的验证码是：#code#，3分钟内有效！，如需自定义模板，请联系旺旺或QQ：726980650进行自定义模板的申请。
	    querys.put("mobile", "18528055080");
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
  
