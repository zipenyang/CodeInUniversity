package com.qfnu.spring.test;

import com.qfnu.spring.pojo.Clazz;
import com.qfnu.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCByXMLTest {
   @Test
   public void testIOC(){
       //获取IOC容器
       ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContest.xml");
       Student studentOne = ioc.getBean("studentOne", Student.class);
       System.out.println(studentOne);
   }

    @Test
    public void testDI(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContest.xml");
        Student studentTwo = ioc.getBean("studentTwo", Student.class);
        System.out.println(studentTwo);
    }

    @Test
    public void testDITwo(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContest.xml");
        Student studentThree = ioc.getBean("studentThree", Student.class);
        System.out.println(studentThree);
    }

    @Test
    public void testDIS(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContest.xml");
        Student student = ioc.getBean("studentTen",Student.class);
        System.out.println(student);
    }

    @Test
    public void testDISe(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContest.xml");
        Clazz clazz = ioc.getBean("clazzTwo", Clazz.class);
        System.out.println(clazz);
    }




}
