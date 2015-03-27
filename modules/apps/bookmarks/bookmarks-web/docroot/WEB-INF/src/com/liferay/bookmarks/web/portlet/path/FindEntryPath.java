/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.bookmarks.web.portlet.path;

import com.liferay.portal.kernel.struts.path.AuthPublicPath;
import com.liferay.portal.kernel.struts.path.BaseAuthPublicPath;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Miguel Pastor
 */
@Component(
	immediate = true,
	property = BaseAuthPublicPath.AUTH_PUBLIC_PATH + "=/bookmarks/find_entry",
	service = AuthPublicPath.class
)
public class FindEntryPath extends BaseAuthPublicPath {

	@Activate
	protected void activate(Map<String, String> properties) {
		updatePath(properties);
	}

	@Modified
	protected void modified(Map<String, String> properties) {
		updatePath(properties);
	}

}