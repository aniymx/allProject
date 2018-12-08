package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void routeQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 接收前台传过来的值,
         * 分类cid
         * 当前页码数 currentPage
         * 页面条数pageSize
         */

        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String rname = request.getParameter("rname");

        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        if ("null".equals(rname)) {
            rname = null;
        }
        //rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        System.out.println(rname);
        /**
         * 对传过来的值进行转换
         * 并提供默认值
         */
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        //调用service进行数据处理
        PageBean<Route> pageBean = routeService.pageBeanQuery(cid, pageSize, currentPage, rname);
        writeValue(pageBean, response);


    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        String cid = request.getParameter("cid");
        String sid = request.getParameter("sid");
        Route route = routeService.findOne(Integer.parseInt(rid), Integer.parseInt(cid), Integer.parseInt(sid));
        //System.out.println(route.getRname());

        writeValue(route, response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user != null) {
            //不空,说明已经登录
            uid = user.getUid();
        } else {
            uid = 0;
        }
        boolean flag = favoriteService.isFavorite(Integer.parseInt(rid), uid);
        writeValue(flag, response);
    }

    public void insertFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(rid + user.getUid());
        boolean flag = favoriteService.insertFavorite(Integer.parseInt(rid), user.getUid());
    }

    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");

        boolean flag;
        if (user != null) {
            flag = true;
        } else {
            flag = false;
        }
        writeValue(flag, response);
    }

    public void countFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        int i = favoriteService.countF(Integer.parseInt(rid));
        writeValue(i, response);
    }

    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        List<Route> list = favoriteService.findMyFavorite(uid);
        writeValue(list, response);
    }
       /*protected void countFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }*/
}
