package com.tudor.sdm.dao;

import org.apache.log4j.Logger;

public abstract class DAO<T, ID> implements IDAO<T, ID> {
	protected final Logger log = Logger.getLogger(this.getClass());
}
