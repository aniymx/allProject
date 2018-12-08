package com.itheima.test;

import java.lang.reflect.Array;

public class testMain {

    static void test() {
        new A().equals("q");
    }

    void test01() {
        System.out.println("test01");
    }




    /* static  int array[] = new int[10];
     */



  /*  public static void main(String[] args) {
      int c= 1;
      int a = 1;
      do {
          --c;
          a=a-1;
      }while (c>a);
        System.out.println(c);
    }*/

}

class A {
    A() {
        test();
    }

    private void test() {
        System.out.println("A");
    }
}


class B extends A {
    B() {
        test();
    }

    private void test() {
        System.out.println("B");
    }

    public static void main(String[] args) {
        A a = new B();
    }
}

class Single {
    private static Single single = new Single();

    private Single() {

    }

    public static Single get() {
        return single;
    }
}

class Singleton {

    // 将自身实例化对象设置为一个属性，并用static、final修饰
    private static final Singleton instance = new Singleton();

    // 构造方法私有化
    private Singleton() {
    }

    // 静态方法返回该实例
    public static Singleton getInstance() {
        return instance;
    }
}

class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 singleton2;

    public static Singleton2 getSingleton2() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
            return singleton2;
        }
        return singleton2;

    }
}

class Singleton3{
    private Singleton3(){}
    private Singleton3 singleton3 ;

}