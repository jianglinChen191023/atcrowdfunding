/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:TestSmrz03.java 
 * Package Name:junit.test 
 * Date:2019年7月9日下午4:49:35 
 * 
 */

package junit.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.atguigu.arcrowdfunding.util.HttpUtils;

/**
 * @ClassName: TestSmrz03
 * @Description: 测试实名认证
 * @author Chen Jiang Lin
 * @date 2019-07-09 16:49
 * 
 */
public class TestSmrz03 {

	public static void main(String[] args) {
		String host = "https://idcert.market.alicloudapi.com";
		String path = "/idcard";
		String method = "GET";
		String appcode = "1948fa6afc674ea3bc2fea47f36e1108";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("idCard", "43062620010327581X");
		querys.put("name", "陈江林");	

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);

			//System.out.println(response.toString());
			// 获取response的body
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
