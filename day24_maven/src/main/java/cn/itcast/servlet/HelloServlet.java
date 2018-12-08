package cn.itcast.servlet;

import cn.itcast.domain.Province;
import cn.itcast.service.Service;
import cn.itcast.service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/hello.jsp").forward(request, response);
        Service service = new ServiceImpl();
        List<Province> province = null;
        try {
            province = service.findProvince();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(province);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
