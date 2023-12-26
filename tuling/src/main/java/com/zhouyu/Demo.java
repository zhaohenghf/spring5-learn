package com.zhouyu;

import com.zhouyu.service.User;
import com.zhouyu.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * @author zhaoheng
 * @date 2023/12/13 10:14
 * @description:
 * @version: 1.0
 */
public class Demo {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService bean = applicationContext.getBean(UserService.class);
		System.out.println(bean);


		//BeanDefinition
		// 表示Bean定义，BeanDefinition中存在很多属性用来描述一个Bean的特点。
		// 比如：
		//● class，表示Bean类型
		//scope，表示Bean作用域，单例或原型等

		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(User.class);
		beanDefinition.setScope("prototype");
		beanDefinition.setInitMethodName("init");
		applicationContext.registerBeanDefinition("user", beanDefinition);
		Object user = applicationContext.getBean("user");
		System.out.println("user============:" + user);
		applicationContext.close();



		//BeanDefinitionReader 可以直接把某个类转换为BeanDefinition，并且会解析该类上的注解，它能解析的注解是：@Conditional，@Scope、@Lazy、@Primary、@DependsOn、@Role、@Description
		AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext();
		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(applicationContext1);
		//
		annotatedBeanDefinitionReader.register(User.class);
		applicationContext1.refresh();
		Object bean1 = applicationContext1.getBean("user");
		System.out.println("user1==========:" + bean1);

		//XmlBeanDefinitionReader 可以直接把某个XML文件转换为BeanDefinition，并且会解析该XML文件中的标签，它能解析的标签是：bean、property、ref、import、alias、value、p

		AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext2);
		xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
		applicationContext2.refresh();
		Object bean2 = applicationContext2.getBean("user1");
		System.out.println("user2===============:" + bean2);


		//ClassPathBeanDefinitionScanner 可以扫描某个包下面的类，并且把这些类转换为BeanDefinition，并且会解析这些类上的注解，扫描到的类上如果存在@Component注解，那么就会把这个类解析为一个BeanDefinition
		AnnotationConfigApplicationContext applicationContext3 = new AnnotationConfigApplicationContext();
		applicationContext3.refresh();
		ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(applicationContext3);
		int scan = classPathBeanDefinitionScanner.scan("com.zhouyu.service");
		Object bean3 = applicationContext3.getBean("userService");
		System.out.println("bean3================:"+bean3);


		//BeanFactory表示Bean工厂，所以很明显，BeanFactory会负责创建Bean，并且提供获取Bean的API。
		//ApplicationContext是BeanFactory的一种
		//public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
		//		MessageSource, ApplicationEventPublisher, ResourcePatternResolver {


		//国际化
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("messages");
		String message = resourceBundleMessageSource.getMessage("test", null, new Locale("en_CN"));
		System.out.println(message);

		//资源加载
		AnnotationConfigApplicationContext applicationContext4 = new AnnotationConfigApplicationContext();
		Resource resource = applicationContext4.getResource("/Users/zhaoheng/develop/project/spring-framework-5.3.10/tuling/src/main/java/com/zhouyu/service/UserService.java");
		String filename = resource.getFilename();
		System.out.println(filename);

		//获取运行时候环境
		AnnotationConfigApplicationContext applicationContext5 = new AnnotationConfigApplicationContext();
		Map<String, Object> systemEnvironment = applicationContext5.getEnvironment().getSystemEnvironment();
		System.out.println("系统==============="+systemEnvironment);


		//BeanPostProcessor
		AnnotationConfigApplicationContext applicationContext6 = new AnnotationConfigApplicationContext(AppConfig.class);


		//BeanFactoryPostProcessor
		AnnotationConfigApplicationContext applicationContext7 = new AnnotationConfigApplicationContext(AppConfig.class);

		//原数据读取器
		//MetadataReader、ClassMetadata、AnnotationMetadata
		//在Spring中需要去解析类的信息，比如类名、类中的方法、类上的注解，这些都可以称之为类的元数据，所以Spring中对类的元数据做了抽象，并提供了一些工具类。
		//MetadataReader表示类的元数据读取器，默认实现类为SimpleMetadataReader。比如：
		SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();
		MetadataReader metadataReader = simpleMetadataReaderFactory.getMetadataReader("com.zhouyu.service.UserService");
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		String className = classMetadata.getClassName();
		System.out.println("类名============"+className);
		// 获取一个AnnotationMetadata，并获取类上的注解信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		for (String annotationType : annotationMetadata.getAnnotationTypes()) {
			System.out.println(annotationType);
		}
	}
}
