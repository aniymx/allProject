package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(req, args);
                   if (value != null) {
                        for (String s : list) {
                            if (value.contains(s)) {
                                value = value.replaceAll(value, "***");
//                                System.out.println("啦啦啦");
                            }
                        }
                    }
                    System.out.println(value);
                    return value;
                }
                return method.invoke(req,args);
            }
        });

        chain.doFilter(proxy_req, resp);
    }

    List<String> list = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {

        try {
            String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/敏感词汇.txt");
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(list);
    }

}
