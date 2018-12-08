package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //多出查询的需求,对sql进行改造
        // String sql = "select * from tab_route where cid = ? limit ? , ? ";
       // System.out.println(rname);
        List params = new ArrayList();
        String sql = "select * FROM tab_route where 1= 1 ";
        StringBuilder sb = new StringBuilder(sql);
        if (cid != 0) {
            sb.append("and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append("and rname like ? ");
            params.add("%" + rname + "%");
        }
        sb.append("limit ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);
        System.out.println("sql-->"+sql);
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<>(Route.class), params.toArray());
        return list;
    }

    @Override
    public int findTotalCount(int cid,String rname) {
       // String sql = "select count(*) from tab_route where cid = ? ";
        List params = new ArrayList();
        String sql = "select count(*) FROM tab_route where 1= 1 ";
        StringBuilder sb = new StringBuilder(sql);
        if (cid != 0) {
            sb.append("and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append("and rname like ? ");
            params.add("%" + rname + "%");
        }

        sql = sb.toString();

        Integer count = template.queryForObject(sql, Integer.class, params.toArray());
        return count;
    }
//查一个route对象
    @Override
    public Route findOne( int rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        return route;
    }
}
