package com.yl.annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotatin1 {
    /* 成员变量
     * 1. 注解的定义中以“无形参的方法”形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型
     *
     * 2. 注解中定义属性时它的类型必须是 8 种基本数据类型外加 类、接口、注解及它们的数组
     *
     * 3. 如果注解只有一个成员，则成员名必须取名为 value() ,在使用时可以忽略成员名和赋值号（=）
     *
     * 4. 注解类可以没有成员，没有成员的注解称为标识注解
     * */

    String value();
}
