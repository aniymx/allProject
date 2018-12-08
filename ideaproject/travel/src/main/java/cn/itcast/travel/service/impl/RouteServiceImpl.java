package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
    RouteImgDao routeImgDao = new RouteImgDaoImpl();
    SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public PageBean<Route> pageBeanQuery(int cid, int pageSize, int currentPage, String rname) {
        PageBean<Route> pb = new PageBean<>();
        //总条数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);
        //计算,总页码数
        int totalpage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalpage);
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页的条数
        pb.setPageSize(pageSize);
        //设置查出的数据list
        int start = (currentPage - 1) * pageSize;
        // System.out.println(rname);

        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);
        return pb;
    }

    /**
     * 查询单个route对象
     *
     * @param cid
     * @param sid
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid, int cid, int sid) {
        Route route = routeDao.findOne(rid);
        //查图
        List<RouteImg> img = routeImgDao.findRouteIMG(rid);
        route.setRouteImgList(img);
        //查商家
        Seller seller = sellerDao.findSeller(sid);
        route.setSeller(seller);

        return route;
    }
}
