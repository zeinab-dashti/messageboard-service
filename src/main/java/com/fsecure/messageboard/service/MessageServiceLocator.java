package com.fsecure.messageboard.service;

import com.fsecure.messageboard.exception.InvalidVersionException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceLocator {

    /*@Autowired
    private BeanFactory beanFactory;*/

    @Autowired
    private ApplicationContext beanFactory;

    public MessageService getMessageService(String version) throws InvalidVersionException {

        MessageService messageService;
        try { messageService = beanFactory.getBean(version, MessageService.class); }
        catch (NoSuchBeanDefinitionException e) { throw new InvalidVersionException(); }

        return messageService;
    }

    public MessageService getMessageService() {
        return beanFactory.getBean("v1", MessageService.class);
    }
}