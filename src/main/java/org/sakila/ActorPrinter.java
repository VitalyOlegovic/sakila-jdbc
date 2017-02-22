package org.sakila;

import java.util.List;

import org.sakila.bean.ActorBean;
import org.sakila.dao.ActorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorPrinter {

	private final static Logger logger = LoggerFactory.getLogger(ActorPrinter.class);
	
	public static void main(String[] args) {
		ActorDAO actorDAO = new ActorDAO();
		List<ActorBean> beans = actorDAO.getList();
		
		for( ActorBean bean : beans ){
			logger.info(bean.toString());
		}
	}

}
