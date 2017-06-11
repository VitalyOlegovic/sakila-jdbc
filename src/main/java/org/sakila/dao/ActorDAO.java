package org.sakila.dao;

import org.sakila.bean.ActorBean;
import org.sakila.util.PersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

    private final static Logger logger = LoggerFactory.getLogger(ActorDAO.class);

    public List<ActorBean> getList(){

        List<ActorBean> actorBeanList = new ArrayList<ActorBean>();

        try {

            PreparedStatement preparedStatement = 
            		PersistenceProvider.getInstance().prepareStatement("select * from actor");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            	ActorBean actorBean = new ActorBean();
                actorBean.setActorId(resultSet.getInt("actor_id"));
                actorBean.setFirstName(resultSet.getString("first_name"));
                actorBean.setLastName(resultSet.getString("last_name"));
                actorBean.setLastUpdate(resultSet.getTimestamp("last_update"));
            	
                actorBeanList.add(actorBean);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return actorBeanList;
    }

    public boolean save(ActorBean bean){
		boolean result;

    	try {
        	PreparedStatement preparedStatement =
        			PersistenceProvider.getInstance().prepareStatement(
        					"insert into actor(first_name,last_name) values (?,?)");
			preparedStatement.setString(1, bean.getFirstName());
			preparedStatement.setString(2, bean.getLastName());
			preparedStatement.execute();
			result = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			result = false;
		}
    	return result;
    }
    
    public boolean update(ActorBean bean){
        boolean result;

    	try {
        	PreparedStatement preparedStatement =
        			PersistenceProvider.getInstance().prepareStatement(
        					"update actor set first_name = ?, last_name = ? where actor_id = ?");
			preparedStatement.setString(1, bean.getFirstName());
			preparedStatement.setString(2, bean.getLastName());
			preparedStatement.setInt(3, bean.getActorId());
			preparedStatement.execute();
			result = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			result = false;
		}
		return result;
    }
    
    public boolean delete(int id){
        boolean result;

    	try {
        	PreparedStatement preparedStatement =
        			PersistenceProvider.getInstance().prepareStatement(
        					"delete from actor where actor_id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			result = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			result = false;
		}
		return result;
    }
    
}
