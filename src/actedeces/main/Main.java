package actedeces.main;

public class Main {
	public static void main(String[] args) {
		try {
			actedeces.model.DatabaseManager dbm = new actedeces.model.DatabaseManager();
            new actedeces.controller.ActeDecesController(dbm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
