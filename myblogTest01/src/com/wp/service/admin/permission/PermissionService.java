package com.wp.service.admin.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wp.bean.Permission;
import com.wp.bean.WpParams;
import com.wp.dao.admin.permission.IPermissionDao;


/**
 * 
 * 包名:com.wp.service.admin.permission.impl
 * 类名:IPermissionService  
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月05日-下午11:51:38
 */
@Service
public class PermissionService implements IPermissionService {
	@Autowired
	private IPermissionDao permissionDao;

	@Override
	public List<Map<String, Object>> findPermissionRoot(WpParams params) {
		List<Map<String, Object>> lists = new ArrayList<>();
		List<Permission> rootlList = permissionDao.findPermissionRoot(params);
		if (rootlList!=null && rootlList.size()>0) {
			for (Permission root : rootlList) {
				Map<String, Object> maps = new HashMap<>();
				maps.put("id", root.getId());
				maps.put("name", root.getName());
				if (maps.get("pId") == null) {
					maps.put("open", true);
				}
				lists.add(maps);
				getChildNodes(root,lists);
			}
		}
		return lists;
	}
	private void getChildNodes(Permission root,List<Map<String, Object>> lists){
		List<Permission> childrenList = permissionDao.findPermissionChildren(root.getId());
		if (childrenList!=null && childrenList.size()>0) {
			for (Permission children : childrenList) {
				Map<String, Object> maps2 = new HashMap<>();
				children.setParentId(root.getId());
				maps2.put("id", children.getId());
				maps2.put("pId", root.getId());
				maps2.put("name", children.getName());
				lists.add(maps2);
				getChildNodes(children,lists);
			}
		}
	}

	@Override
	public List<Permission> findPermissionChildren(Integer id) {
		return permissionDao.findPermissionChildren(id);
	}

	@Override
	public int count(WpParams params) {
		return permissionDao.count(params);
	}

	@Override
	public List<HashMap<String, Object>> findUserPermission(Integer userId) {
		return permissionDao.findUserPermission(userId);
	}
	
}

