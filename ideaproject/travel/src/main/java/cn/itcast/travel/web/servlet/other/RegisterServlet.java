package cn.itcast.travel.web.servlet.other;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.UuidUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //   System.out.println("收到请求");
        // Map<String, String[]> map = request.getParameterMap();
        response.setContentType("application/json;charset=utf-8");
        String check = request.getParameter("check");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //确保用户失败后,对验证码进行刷新
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //只有验证码通过,才会对下面的逻辑进行操作
        if (checkcode_server!=null || checkcode_server.equals(check)) {
            String username = request.getParameter("username");
            UserService userService = new UserServiceImpl();
            ResultInfo resultInfo = userService.findUser(username);
            boolean flag = resultInfo.isFlag();
            System.out.println(flag);
            //flag是true说明数据库中存在数据
            if (resultInfo.isFlag()) {
                //格式化为json数据,写回前台
                ObjectMapper objectMapper = new ObjectMapper();
                String s = objectMapper.writeValueAsString(resultInfo);
                System.out.println(s);
                response.getWriter().write(s);
            } else {
                //没有数据,插入数据到数据库
                Map<String, String[]> parameterMap = request.getParameterMap();
                //封装数据
                User user = new User();
                try {
                    BeanUtils.populate(user, parameterMap);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                ResultInfo saveUserInfo = userService.saveUser(user);
                boolean flag1 = saveUserInfo.isFlag();
                if (flag1) {
                    // response.sendRedirect("register_ok.html");
                    System.out.println("插入成功!!!!!");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String s = objectMapper.writeValueAsString(resultInfo);
                    System.out.println(s);
                    response.getWriter().write(s);


                } else {
                    System.out.println(saveUserInfo.getErrorMsg());
                }


            }
        }else {
            System.out.println("验证码有问题");
        }
        //System.out.println(username);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
