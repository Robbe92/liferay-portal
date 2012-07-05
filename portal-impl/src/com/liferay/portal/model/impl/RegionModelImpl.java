/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.RegionModel;
import com.liferay.portal.model.RegionSoap;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Region service. Represents a row in the &quot;Region&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.model.RegionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RegionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegionImpl
 * @see com.liferay.portal.model.Region
 * @see com.liferay.portal.model.RegionModel
 * @generated
 */
@JSON(strict = true)
public class RegionModelImpl extends BaseModelImpl<Region>
	implements RegionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a region model instance should use the {@link com.liferay.portal.model.Region} interface instead.
	 */
	public static final String TABLE_NAME = "Region";
	public static final Object[][] TABLE_COLUMNS = {
			{ "regionId", Types.BIGINT },
			{ "countryId", Types.BIGINT },
			{ "regionCode", Types.VARCHAR },
			{ "name", Types.VARCHAR },
			{ "active_", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table Region (regionId LONG not null primary key,countryId LONG,regionCode VARCHAR(75) null,name VARCHAR(75) null,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Region";
	public static final String ORDER_BY_JPQL = " ORDER BY region.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Region.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.Region"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.Region"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.Region"),
			true);
	public static long ACTIVE_COLUMN_BITMASK = 1L;
	public static long COUNTRYID_COLUMN_BITMASK = 2L;
	public static long REGIONCODE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Region toModel(RegionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Region model = new RegionImpl();

		model.setRegionId(soapModel.getRegionId());
		model.setCountryId(soapModel.getCountryId());
		model.setRegionCode(soapModel.getRegionCode());
		model.setName(soapModel.getName());
		model.setActive(soapModel.getActive());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Region> toModels(RegionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Region> models = new ArrayList<Region>(soapModels.length);

		for (RegionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.Region"));

	public RegionModelImpl() {
	}

	public long getPrimaryKey() {
		return _regionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setRegionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_regionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Region.class;
	}

	public String getModelClassName() {
		return Region.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("regionId", getRegionId());
		attributes.put("countryId", getCountryId());
		attributes.put("regionCode", getRegionCode());
		attributes.put("name", getName());
		attributes.put("active", getActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long regionId = (Long)attributes.get("regionId");

		if (regionId != null) {
			setRegionId(regionId);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String regionCode = (String)attributes.get("regionCode");

		if (regionCode != null) {
			setRegionCode(regionCode);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@JSON
	public long getRegionId() {
		return _regionId;
	}

	public void setRegionId(long regionId) {
		_regionId = regionId;
	}

	@JSON
	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_columnBitmask |= COUNTRYID_COLUMN_BITMASK;

		if (!_setOriginalCountryId) {
			_setOriginalCountryId = true;

			_originalCountryId = _countryId;
		}

		_countryId = countryId;
	}

	public long getOriginalCountryId() {
		return _originalCountryId;
	}

	@JSON
	public String getRegionCode() {
		if (_regionCode == null) {
			return StringPool.BLANK;
		}
		else {
			return _regionCode;
		}
	}

	public void setRegionCode(String regionCode) {
		_columnBitmask |= REGIONCODE_COLUMN_BITMASK;

		if (_originalRegionCode == null) {
			_originalRegionCode = _regionCode;
		}

		_regionCode = regionCode;
	}

	public String getOriginalRegionCode() {
		return GetterUtil.getString(_originalRegionCode);
	}

	@JSON
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public Region toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Region)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Region.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		RegionImpl regionImpl = new RegionImpl();

		regionImpl.setRegionId(getRegionId());
		regionImpl.setCountryId(getCountryId());
		regionImpl.setRegionCode(getRegionCode());
		regionImpl.setName(getName());
		regionImpl.setActive(getActive());

		regionImpl.resetOriginalValues();

		return regionImpl;
	}

	public int compareTo(Region region) {
		int value = 0;

		value = getName().compareTo(region.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Region region = null;

		try {
			region = (Region)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = region.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		RegionModelImpl regionModelImpl = this;

		regionModelImpl._originalCountryId = regionModelImpl._countryId;

		regionModelImpl._setOriginalCountryId = false;

		regionModelImpl._originalRegionCode = regionModelImpl._regionCode;

		regionModelImpl._originalActive = regionModelImpl._active;

		regionModelImpl._setOriginalActive = false;

		regionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Region> toCacheModel() {
		RegionCacheModel regionCacheModel = new RegionCacheModel();

		regionCacheModel.regionId = getRegionId();

		regionCacheModel.countryId = getCountryId();

		regionCacheModel.regionCode = getRegionCode();

		String regionCode = regionCacheModel.regionCode;

		if ((regionCode != null) && (regionCode.length() == 0)) {
			regionCacheModel.regionCode = null;
		}

		regionCacheModel.name = getName();

		String name = regionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			regionCacheModel.name = null;
		}

		regionCacheModel.active = getActive();

		return regionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{regionId=");
		sb.append(getRegionId());
		sb.append(", countryId=");
		sb.append(getCountryId());
		sb.append(", regionCode=");
		sb.append(getRegionCode());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.Region");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>regionId</column-name><column-value><![CDATA[");
		sb.append(getRegionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>regionCode</column-name><column-value><![CDATA[");
		sb.append(getRegionCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Region.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Region.class
		};
	private long _regionId;
	private long _countryId;
	private long _originalCountryId;
	private boolean _setOriginalCountryId;
	private String _regionCode;
	private String _originalRegionCode;
	private String _name;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private long _columnBitmask;
	private Region _escapedModelProxy;
}