/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:SpringJavaMailTet.java 
 * Package Name:junit.test 
 * Date:2019年6月12日上午1:10:28 
 * 
 */  
      
package junit.test;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.atguigu.atcrowdfunding.util.DesUtil;

/** 
* @ClassName: SpringJavaMailTet 
* @Description: 测试mail
* @author Chen Jiang Lin 
* @date 2019-06-12 01:10
*  
*/
public class SpringJavaMailTet {

	public static void main(String[] args) throws Exception {
		// 使用JAVA程序发送邮件
		
		ApplicationContext application = new ClassPathXmlApplicationContext("spring/spring-*.xml");
		
		// 邮件发送器，由Spring框架提供的
		JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)application.getBean("sendMail");
		
		        javaMailSender.setDefaultEncoding("UTF-8");
		        MimeMessage mail = javaMailSender.createMimeMessage();	//这是一封邮件 - 一封情书
		        MimeMessageHelper helper = new MimeMessageHelper(mail);
		        helper.setSubject("告白书");	//邮件标题
		        StringBuilder content = new StringBuilder();
		        
		        String param = "ILY";
		        param = DesUtil.encrypt(param, "abcdefghijklmnopquvwxyz");
		        
		        content.append("<a href='http://www.atcrowdfunding.com/test/act.do?p="+param+"'>激活链接</a>");
		        helper.setText(content.toString(), true);
		        helper.setFrom("admin@atguigu.com");
		        helper.setTo("test@atguigu.com");
		        javaMailSender.send(mail);
		}
	
}
  
