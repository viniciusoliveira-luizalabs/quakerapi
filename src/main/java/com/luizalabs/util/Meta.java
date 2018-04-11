package com.luizalabs.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Meta {

	@JsonIgnore
	private final Log LOGGER = LogFactory.getLog(Meta.class);

	private String version;

	private String server;

	private Integer size;

	private Integer page;

	private Integer recordCount;

	private Integer totalRecords;

	public Meta(String server, Integer size, Integer page, Integer recordCount, Integer totalRecords) {
		super();
		this.server = server;
		this.size = size;
		this.page = page;
		this.recordCount = recordCount;
		this.totalRecords = totalRecords;
	}

	public Meta(String version, String server, Integer limit, Integer page, Integer recordCount, Integer totalRecords) {
		this(server, limit, page, recordCount, totalRecords);
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

}