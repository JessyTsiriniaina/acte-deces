package actedeces;

import actedeces.model.ActeDecesDAO;

public class Main {
	public static void main(String[] args) {
		try {
			ActeDecesDAO acteDecesDAO = new ActeDecesDAO();
            new actedeces.controller.ConsultationController(acteDecesDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
