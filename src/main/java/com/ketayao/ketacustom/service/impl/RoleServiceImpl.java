/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.service.impl.RoleServiceImpl.java
 * Class:			RoleServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.ketacustom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ketayao.ketacustom.dao.RoleDAO;
import com.ketayao.ketacustom.entity.main.Role;
import com.ketayao.ketacustom.service.RoleService;
import com.ketayao.ketacustom.shiro.ShiroDbRealm;
import com.ketayao.ketacustom.util.dwz.Page;
import com.ketayao.ketacustom.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:04:52 
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired(required = false)
	private ShiroDbRealm shiroRealm;
	
	@Override
	public void save(Role role) {
		roleDAO.save(role);
	}

	@Override
	public Role get(Long id) {
		return roleDAO.findOne(id);
	}
	
	@Override
	public List<Role> findAll(Page page) {
		org.springframework.data.domain.Page<Role> springDataPage = roleDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param role  
	 * @see com.ketayao.ketacustom.service.RoleService#update(com.ketayao.ketacustom.entity.main.Role)  
	 */
	public void update(Role role) {
		roleDAO.save(role);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param id  
	 * @see com.ketayao.ketacustom.service.RoleService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		roleDAO.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.ketayao.ketacustom.service.RoleService#find(com.ketayao.ketacustom.util.dwz.Page, java.lang.String)  
	 */
	public List<Role> find(Page page, String name) {
		org.springframework.data.domain.Page<Role> springDataPage = 
				(org.springframework.data.domain.Page<Role>)roleDAO.findByNameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

}
