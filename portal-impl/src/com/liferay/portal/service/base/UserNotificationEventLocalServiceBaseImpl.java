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

package com.liferay.portal.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserNotificationEventPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the user notification event local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.UserNotificationEventLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.UserNotificationEventLocalServiceImpl
 * @see com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class UserNotificationEventLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements UserNotificationEventLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil} to access the user notification event local service.
	 */

	/**
	 * Adds the user notification event to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationEvent the user notification event
	 * @return the user notification event that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserNotificationEvent addUserNotificationEvent(
		UserNotificationEvent userNotificationEvent) {
		userNotificationEvent.setNew(true);

		return userNotificationEventPersistence.update(userNotificationEvent);
	}

	/**
	 * Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	 *
	 * @param userNotificationEventId the primary key for the new user notification event
	 * @return the new user notification event
	 */
	@Override
	public UserNotificationEvent createUserNotificationEvent(
		long userNotificationEventId) {
		return userNotificationEventPersistence.create(userNotificationEventId);
	}

	/**
	 * Deletes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationEventId the primary key of the user notification event
	 * @return the user notification event that was removed
	 * @throws PortalException if a user notification event with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserNotificationEvent deleteUserNotificationEvent(
		long userNotificationEventId) throws PortalException {
		return userNotificationEventPersistence.remove(userNotificationEventId);
	}

	/**
	 * Deletes the user notification event from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationEvent the user notification event
	 * @return the user notification event that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserNotificationEvent deleteUserNotificationEvent(
		UserNotificationEvent userNotificationEvent) {
		return userNotificationEventPersistence.remove(userNotificationEvent);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(UserNotificationEvent.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return userNotificationEventPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return userNotificationEventPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return userNotificationEventPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return userNotificationEventPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return userNotificationEventPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public UserNotificationEvent fetchUserNotificationEvent(
		long userNotificationEventId) {
		return userNotificationEventPersistence.fetchByPrimaryKey(userNotificationEventId);
	}

	/**
	 * Returns the user notification event with the matching UUID and company.
	 *
	 * @param uuid the user notification event's UUID
	 * @param companyId the primary key of the company
	 * @return the matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 */
	@Override
	public UserNotificationEvent fetchUserNotificationEventByUuidAndCompanyId(
		String uuid, long companyId) {
		return userNotificationEventPersistence.fetchByUuid_C_First(uuid,
			companyId, null);
	}

	/**
	 * Returns the user notification event with the primary key.
	 *
	 * @param userNotificationEventId the primary key of the user notification event
	 * @return the user notification event
	 * @throws PortalException if a user notification event with the primary key could not be found
	 */
	@Override
	public UserNotificationEvent getUserNotificationEvent(
		long userNotificationEventId) throws PortalException {
		return userNotificationEventPersistence.findByPrimaryKey(userNotificationEventId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(UserNotificationEvent.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"userNotificationEventId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(UserNotificationEvent.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"userNotificationEventId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(UserNotificationEvent.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"userNotificationEventId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return userNotificationEventLocalService.deleteUserNotificationEvent((UserNotificationEvent)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return userNotificationEventPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the user notification event with the matching UUID and company.
	 *
	 * @param uuid the user notification event's UUID
	 * @param companyId the primary key of the company
	 * @return the matching user notification event
	 * @throws PortalException if a matching user notification event could not be found
	 */
	@Override
	public UserNotificationEvent getUserNotificationEventByUuidAndCompanyId(
		String uuid, long companyId) throws PortalException {
		return userNotificationEventPersistence.findByUuid_C_First(uuid,
			companyId, null);
	}

	/**
	 * Returns a range of all the user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @return the range of user notification events
	 */
	@Override
	public List<UserNotificationEvent> getUserNotificationEvents(int start,
		int end) {
		return userNotificationEventPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of user notification events.
	 *
	 * @return the number of user notification events
	 */
	@Override
	public int getUserNotificationEventsCount() {
		return userNotificationEventPersistence.countAll();
	}

	/**
	 * Updates the user notification event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationEvent the user notification event
	 * @return the user notification event that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserNotificationEvent updateUserNotificationEvent(
		UserNotificationEvent userNotificationEvent) {
		return userNotificationEventPersistence.update(userNotificationEvent);
	}

	/**
	 * Returns the user notification event local service.
	 *
	 * @return the user notification event local service
	 */
	public UserNotificationEventLocalService getUserNotificationEventLocalService() {
		return userNotificationEventLocalService;
	}

	/**
	 * Sets the user notification event local service.
	 *
	 * @param userNotificationEventLocalService the user notification event local service
	 */
	public void setUserNotificationEventLocalService(
		UserNotificationEventLocalService userNotificationEventLocalService) {
		this.userNotificationEventLocalService = userNotificationEventLocalService;
	}

	/**
	 * Returns the user notification event persistence.
	 *
	 * @return the user notification event persistence
	 */
	public UserNotificationEventPersistence getUserNotificationEventPersistence() {
		return userNotificationEventPersistence;
	}

	/**
	 * Sets the user notification event persistence.
	 *
	 * @param userNotificationEventPersistence the user notification event persistence
	 */
	public void setUserNotificationEventPersistence(
		UserNotificationEventPersistence userNotificationEventPersistence) {
		this.userNotificationEventPersistence = userNotificationEventPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.kernel.model.UserNotificationEvent",
			userNotificationEventLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.UserNotificationEvent");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return UserNotificationEventLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return UserNotificationEvent.class;
	}

	protected String getModelClassName() {
		return UserNotificationEvent.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = userNotificationEventPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.portal.kernel.service.UserNotificationEventLocalService.class)
	protected UserNotificationEventLocalService userNotificationEventLocalService;
	@BeanReference(type = UserNotificationEventPersistence.class)
	protected UserNotificationEventPersistence userNotificationEventPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}