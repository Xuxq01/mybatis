package com.powernode.javassist;

import com.powernode.bank.dao.AccountDao;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION:
 * @USER: 11240
 * @DATE: 2022/10/19 14:55
 */
public class JavassistTest {

    @Test
    public void testGenerateImpl() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.powernode.bank.dao.impl.AccountDaoImpl");
        CtClass ctInterface = pool.makeInterface("com.powernode.bank.dao.AccountDao");
        ctClass.addInterface(ctInterface);
        CtMethod ctMethod = CtMethod.make("public void delete(){System.out.println(\"hello delete!\");}", ctClass);
        ctClass.addMethod(ctMethod);
        Class<?> clazz = ctClass.toClass();
        AccountDao accountDao = (AccountDao) clazz.newInstance();
        accountDao.delete();
    }

    @Test
    public void testGenerateFirstClass() throws Exception {
        //获取类池,这个类池就是用来生成class的
        ClassPool pool = ClassPool.getDefault();
        //制造类(需要告诉javassist类名)
        CtClass ctClass = pool.makeClass("com.powernode.bank.dao.impl.AccountDaoImpl");
        //制造方法
        String methodCode = "public void insert(){System.out.println(123);}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        //将方法添加到类中
        ctClass.addMethod(ctMethod);
        //在内存中生成class
        ctClass.toClass();
        //类加载,返回AccountDaoImpl类的字节码
        Class<?> clazz = Class.forName("com.powernode.bank.dao.impl.AccountDaoImpl");
        //创建对象
        Object obj = clazz.newInstance();
        Method insert = clazz.getDeclaredMethod("insert");

        insert.invoke(obj);
    }
}
