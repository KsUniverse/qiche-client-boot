/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.auth.granter;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springblade.core.jwt.JwtUtil;
import org.springblade.core.jwt.props.JwtProperties;
import org.springblade.core.launch.constant.TokenConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.auth.provider.ITokenGranter;
import org.springblade.modules.auth.provider.TokenParameter;
import org.springblade.modules.auth.utils.TokenUtil;
import org.springblade.modules.system.entity.Tenant;
import org.springblade.modules.system.entity.UserInfo;
import org.springblade.modules.system.service.IRoleService;
import org.springblade.modules.system.service.ITenantService;
import org.springblade.modules.system.service.IUserService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RefreshTokenGranter
 *
 * @author Chill
 */
@Component
@AllArgsConstructor
public class RefreshTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "refresh_token";

	private final IUserService userService;
	private final IRoleService roleService;
	private final ITenantService tenantService;
	private final JwtProperties jwtProperties;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {
		String tenantId = tokenParameter.getArgs().getStr("tenantId");
		String grantType = tokenParameter.getArgs().getStr("grantType");
		String refreshToken = tokenParameter.getArgs().getStr("refreshToken");
		String deptId = tokenParameter.getArgs().getStr("deptId");
		String roleId = tokenParameter.getArgs().getStr("roleId");
		UserInfo userInfo = null;
		if (Func.isNoneBlank(grantType, refreshToken) && grantType.equals(TokenConstant.REFRESH_TOKEN)) {
			// 判断令牌合法性
			if (!judgeRefreshToken(grantType, refreshToken)) {
				throw new ServiceException(TokenUtil.TOKEN_NOT_PERMISSION);
			}
			Claims claims = AuthUtil.parseJWT(refreshToken);
			if (claims != null) {
				String tokenType = Func.toStr(claims.get(TokenConstant.TOKEN_TYPE));
				if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
					// 获取租户信息
					Tenant tenant = tenantService.getByTenantId(tenantId);
					if (TokenUtil.judgeTenant(tenant)) {
						throw new ServiceException(TokenUtil.USER_HAS_NO_TENANT_PERMISSION);
					}
					// 获取用户信息
					userInfo = userService.userInfo(Func.toLong(claims.get(TokenConstant.USER_ID)));
					// 设置多部门信息
					if (Func.isNotEmpty(deptId) && userInfo.getUser().getDeptId().contains(deptId)) {
						userInfo.getUser().setDeptId(deptId);
					}
					// 设置多角色信息
					if (Func.isNotEmpty(roleId) && userInfo.getUser().getRoleId().contains(roleId)) {
						userInfo.getUser().setRoleId(roleId);
						List<String> roleAliases = roleService.getRoleAliases(roleId);
						userInfo.setRoles(roleAliases);
					}
				}
			}
		}
		return userInfo;
	}

	/**
	 * 校验refreshToken合法性
	 *
	 * @param grantType    认证类型
	 * @param refreshToken refreshToken
	 */
	private boolean judgeRefreshToken(String grantType, String refreshToken) {
		if (jwtProperties.getState() && jwtProperties.getSingle()) {
			Claims claims = JwtUtil.parseJWT(refreshToken);
			String tenantId = String.valueOf(claims.get("tenant_id"));
			String userId = String.valueOf(claims.get("user_id"));
			String token = JwtUtil.getRefreshToken(tenantId, userId, refreshToken);
			return StringUtil.equalsIgnoreCase(token, refreshToken);
		}
		return true;
	}


}
