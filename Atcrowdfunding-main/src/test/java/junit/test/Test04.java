/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:Test4.java 
 * Package Name:junit.test 
 * Date:2019年5月25日上午11:04:08 
 * 
 */  
      
package junit.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.manager.dao.AdvertisementMapper;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.dao.TypeMapper;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;

/** 
* @ClassName: Test4 
* @Description: 生成测试数据
* @author Chen Jiang Lin 
* @date 2019-05-25 11:04
*  
*/
public class Test04 {

	public static void main(String[] args) {
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*.xml");
//		
//		UserMapper userMapper = ioc.getBean(UserMapper.class);
//		
//		for (int i = 1; i < 100; i++) {
//			User user = new User();
//			user.setLoginacct("test"+i);
//			user.setUserpswd(MD5Util.digest("123"));
//			user.setUsername("test"+i);
//			user.setEmail("test"+i+"@atauigu.com");
//			user.setCreatetime("2019-05-25 11:25:00");
//			userMapper.insert(user);
//		}
		
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*.xml");
//		
//		RoleMapper roleMapper = ioc.getBean(RoleMapper.class);
//		
//		for (int i = 1; i < 100; i++) {
//			Role role = new Role();
//			role.setName("test"+i);
//			roleMapper.insert(role);
//		}
		
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*.xml");
//		
//		AdvertisementMapper advertisementMapper = ioc.getBean(AdvertisementMapper.class);
//		
//		for (int i = 1; i < 100; i++) {
//			Advertisement advertisement = new Advertisement();
//			advertisement.setName("Test"+i+"商品广告");
//			advertisement.setStatus("3");
//			advertisementMapper.insert(advertisement);
//		}
//		
//	}	
		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*.xml");
		
		TypeMapper typeMapper = ioc.getBean(TypeMapper.class);
		
		for (int i = 1; i < 100; i++) {
			Type type = new Type();
			type.setName("test"+i);
			type.setRemark("test"+i+"简介");
			typeMapper.insert(type);
		}
		

	}
	
}
  
