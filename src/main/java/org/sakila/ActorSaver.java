package org.sakila;

import java.util.Scanner;

import org.sakila.bean.ActorBean;
import org.sakila.dao.ActorDAO;

public class ActorSaver {
	
	public static void main(String[] args) {
		ActorBean bean = getActorData();
		ActorDAO dao = new ActorDAO();
		dao.save(bean);
	}
	
	public static ActorBean getActorData(){
		Scanner scanner = new Scanner(System.in);
		ActorBean bean = new ActorBean();
		System.out.println("Inserire il nome dell'attore: ");
		String firstName = scanner.next();
		bean.setFirstName(firstName);
		System.out.println("Inserire il cognome dell'attore: ");
		String lastName = scanner.next();
		bean.setLastName(lastName);
		scanner.close();
		return bean;
	}
	
}
