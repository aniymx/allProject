package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;

public interface RouteDao {
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    int findTotalCount(int cid, String rname);
//查单个route对象
    Route findOne(int rid);

}
