package com.yl.annotationtest;

import java.lang.reflect.Method;

@TestAnnotation(name = "王五")
public class TestAnnotationClass {

    @TestAnnotation(name = "lisi",age=50,school={"usa","peking"})
    public void test(){

    }

    @TestAnnotatin1("null string")
    public void test2(){

    }

    public static void main(String[] args) throws ClassNotFoundException {

   /*
   先获取类对象
   类对象调用 isAnnotationPresent(Class<? extends Annotation> annotationClass)判断是否应用了某个注解
   通过 getAnnotation() 方法来获取 Annotation 对象，或者getAnnotations() 方法获取所有应用在该类上的注解
    */
        Class hclass=Class.forName("com.example.annotationtest.TestAnnotationClass");
        boolean annotationPresent = hclass.isAnnotationPresent(TestAnnotation.class);
        boolean annotationPresent1 = hclass.isAnnotationPresent(TestAnnotatin1.class);
        //获取类上的注解
        if (annotationPresent){
            TestAnnotation t=(TestAnnotation) hclass.getAnnotation(TestAnnotation.class);
            System.out.println(t.name());
        }
        //获取方法上的注解
        Method [] ms=hclass.getMethods();
        for (Method m:
             ms) {
            boolean annotationPresent2 = m.isAnnotationPresent(TestAnnotation.class);
            if (annotationPresent2){
                TestAnnotation annotation = m.getAnnotation(TestAnnotation.class);
                System.out.println(annotation.age());
                String[] school = annotation.school();
                System.out.println(school[0]);
                System.out.println(annotation.name());
            }
        }
    }


}
