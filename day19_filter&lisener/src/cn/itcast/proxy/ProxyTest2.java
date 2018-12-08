package cn.itcast.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest2 {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();

        //2.动态代理增强lenovo对象
        /*
            三个参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()
         */
        ClassLoader classLoader = lenovo.getClass().getClassLoader();
        Class<?>[] interfaces = lenovo.getClass().getInterfaces();
        InvocationHandler sale1 = new InvocationHandler() {

            /*
                代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法执行
                    参数：
                        1. proxy:代理对象
                        2. method：代理对象调用的方法，被封装为的对象
                        3. args:代理对象调用的方法时，传递的实际参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*System.out.println("该方法执行了....");
                System.out.println(method.getName());
                System.out.println(args[0]);




*/
                //判断是否是sale方法
                if (method.getName().equals("sale")) {
                    //1.增强参数
                    double money = (double) args[0];
                    money = money * 0.85;
                    System.out.println("专车接你....");
                    //使用真实对象调用该方法
                    String obj = (String) method.invoke(lenovo, money);
                    System.out.println("免费送货...");
                    //2.增强返回值
                    return obj + "_鼠标垫";
                } else {
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }


            }
        };




        InvocationHandler sale2 = new InvocationHandler() {


//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("--------------------------------------------------------------");
////                System.out.println(proxy);
//                System.out.println("--------------------------------------------------------------");
//                //判断是否是sale方法
//                if (method.getName().equals("sale")) {
//                    //1.增强参数
//                    double money = (double) args[0];
//                    money = money * 0.85;
//                    System.out.println("专车接你....");
//                    //使用真实对象调用该方法
//                    String obj = (String) method.invoke(lenovo, money);
//                    System.out.println("免费送货...");
//                    //2.增强返回值
//                    return obj + "_鼠标垫";
//                } else {
//                    Object obj = method.invoke(lenovo, args);
//                    return obj;
//                }
//
//            }


            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                System.out.println(method);
                System.out.println("=================================================");
//                String name = proxy.getClass().getName();
//                System.out.println(name);
//                Method[] declaredMethods = proxy.getClass().getDeclaredMethods();
//                for (Method declaredMethod : declaredMethods) {
//                    System.out.println("------------"+declaredMethod);
////                    String name = declaredMethod.getName();
////                    try {
////                        if("show".equals(name)){
////                            declaredMethod.invoke(proxy);
////                        }
////                    } catch (IllegalAccessException e) {
////                        e.printStackTrace();
////                    } catch (InvocationTargetException e) {
////                        e.printStackTrace();
////                    }
//                }
//                proxy = null;
//                method.invoke(lenovo,args);
                return proxy;
            }
        };

        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(classLoader, interfaces, sale2);

        //3.调用方法

//        String computer = proxy_lenovo.sale(8000);
//        System.out.println(computer);

//        proxy_lenovo.show();
//        String sale = proxy_lenovo.sale(8000);

        Object sel = proxy_lenovo.getSel(2).getSel(2).getSel(3);
        System.out.println(sel instanceof Proxy);
    }
}
