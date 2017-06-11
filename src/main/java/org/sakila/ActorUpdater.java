package org.sakila;

import java.util.Scanner;

import org.sakila.bean.ActorBean;
import org.sakila.dao.ActorDAO;

public class ActorUpdater {

	public static void main(String[] args) {
		ActorBean bean = getUpdateData();
		ActorDAO dao = new ActorDAO();
		boolean result = dao.update(bean);
		if(result){
			System.out.println("Aggiornamento effettuato con successo");
		}else{
			System.out.println("Errore durante l'aggiornamento");
		}
	}
	
	private static ActorBean getUpdateData(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserire l'identificativo dell'attore da aggiornare: ");
		int id = scanner.nextInt();
		ActorBean bean = ActorSaver.getActorData();
		scanner.close();
		bean.setActorId(id);
		return bean;
	}
	
}
