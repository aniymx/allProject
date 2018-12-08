package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    PageBean<Route> pageBeanQuery(int cid,int pageSize,int currentPage,String rname);
    Route findOne(int cid,int sid,int rid);

}
