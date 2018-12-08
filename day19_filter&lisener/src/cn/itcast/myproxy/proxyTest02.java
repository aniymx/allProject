package cn.itcast.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxyTest02 {
    public static void main(String[] args) {
        Lenovo lenovo = new Lenovo();

        SaleComputer o = (SaleComputer)Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("sale")) {
                    double m = (double) args[0];
                    m = m * 0.6;
                    System.out.println("买电脑优惠送手机");
                    String invoke = (String) method.invoke(lenovo, m);
                    return invoke;

                } else {
                    Object invoke = method.invoke(proxy, args);
                    return invoke;
                }


            }
        });
        String sale = o.sale(8000);
        System.out.println("---------------");
        System.out.println(sale);

    }
}
