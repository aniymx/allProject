package cn.itcast.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Lenovo lenovo = new Lenovo();

        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("sale")) {
                    double money = (double) args[0];
                    money = money * 0.8;
                    System.out.println("车接车送....");
                    String saleMoney = (String) method.invoke(lenovo, money);
                    return saleMoney + "鼠标垫";
                } else {
                    Object invoke = method.invoke(lenovo, args);
                    return invoke;
                }
            }
        });

        String sale = proxy_lenovo.sale(10000);
        System.out.println(sale);


    }
}
