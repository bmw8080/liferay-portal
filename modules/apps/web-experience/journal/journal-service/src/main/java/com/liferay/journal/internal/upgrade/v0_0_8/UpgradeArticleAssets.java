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

package com.liferay.journal.internal.upgrade.v0_0_8;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Preston Crary
 * @author Alberto Chaparro
 */
public class UpgradeArticleAssets extends UpgradeProcess {

	public UpgradeArticleAssets(AssetEntryLocalService assetEntryLocalService) {
		_assetEntryLocalService = assetEntryLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateArticleAssets();
	}

	protected void updateArticleAssets() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			try (PreparedStatement ps = connection.prepareStatement(
					StringBundler.concat(
						"select resourcePrimKey, indexable from ",
						"JournalArticle where version = ",
						String.valueOf(JournalArticleConstants.VERSION_DEFAULT),
						" and status = ",
						String.valueOf(WorkflowConstants.STATUS_DRAFT)));
				ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					long resourcePrimKey = rs.getLong("resourcePrimKey");
					boolean indexable = rs.getBoolean("indexable");

					AssetEntry assetEntry = _assetEntryLocalService.getEntry(
						JournalArticle.class.getName(), resourcePrimKey);

					_assetEntryLocalService.updateEntry(
						assetEntry.getClassName(), assetEntry.getClassPK(),
						null, null, indexable, assetEntry.isVisible());
				}
			}
		}
	}

	private final AssetEntryLocalService _assetEntryLocalService;

}