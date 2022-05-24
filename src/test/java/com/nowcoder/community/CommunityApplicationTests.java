package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//测试代码以CommunityApplication为配置类
class CommunityApplicationTests implements ApplicationContextAware {

	@Test
	void contextLoads() {
	}

	private ApplicationContext applicationContext;//记录Spring容器

	@Override
	//自动创建String容器接口（通过实现ApplicationContextAware接口，实现setApplicationContext方法，获取到了ApplicationContext对象，全程没有new过容器对象）
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {//ApplicationContextAware类（接口）的setApplicationContext方法
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);//检测容器是否可用
		//从容器中获取自动装配的Bean，此处是按照类型（接口）获取Bean。当接口AlphaDao被两个类实现时就会报错，由于调用同一接口，此时可在想要起作用的类前加“@Primary”即可（StringBoot的优势体现）
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());//调用Bean的查询方法并输出

		alphaDao = (AlphaDao) applicationContext.getBean("alphaDaoHibernateImpl");//通过Bean的名字（默认为类名首字母小写）强制容器返回指定Bean
		System.out.println(alphaDao.select());//调用Bean的查询方法并输出
	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);//通过类获取Bean
		System.out.println(alphaService);
	}

	@Test
	//String中容器的主动获取与使用(比下面的方法稍显笨拙)
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);//通过增加的Bean"SimpleDateFormat"获得Bean
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired//用于给当前的Bean注入AlphaDao，通过容器管理，降低了Bean之间的耦合度
    //Qualifier("name");//使用调用同一接口的另一Bean
	private AlphaDao alphaDao;//Spring容器把AlphaDao注入给属性alphaDao，就可以直接使用属性了
	@Autowired
	private AlphaService alphaService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){//测试依赖注入
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}
}
