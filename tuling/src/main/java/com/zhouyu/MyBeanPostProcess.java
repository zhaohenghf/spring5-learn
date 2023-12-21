package com.zhouyu;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhaoheng
 * @date 2023/12/13 13:45
 * @description:
 * @version: 1.0
 */
@Component
public class MyBeanPostProcess implements BeanPostProcessor {


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equalsIgnoreCase("userService")){
			System.out.println(beanName+"=============:初始化前");
		}
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName+"===============:初始化后");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
