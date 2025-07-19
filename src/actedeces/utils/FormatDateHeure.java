package actedeces.utils;

import java.text.SimpleDateFormat;

public class FormatDateHeure {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	
	public static SimpleDateFormat getHeureFormat() {
		return timeFormat;
	}
}
