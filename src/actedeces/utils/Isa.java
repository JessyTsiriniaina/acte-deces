package actedeces.utils;

public class Isa {
	private final String[] unites = {"", "iray", "roa", "telo", "efatra", "dimy", "enina", "fito", "valo", "sivy"};
    private final String[] entre_vingt_et_dix = {"folo", "iraika ambin'ny folo", "roa ambin'ny folo", "telo ambin'ny folo",
                   							"efatra ambin'ny folo", "dimy ambin'ny folo", "enina ambin'ny folo",
                   							"fito ambin'ny folo", "valo ambin'ny folo", "sivy ambin'ny folo"};
    
    private final String[] dizaines = {"", "", "roapolo", "telopolo", "efapolo", "dimampolo",
                						"enimpolo", "fitopolo", "valopolo", "sivifolo"};
    
    private final String[] centaines = {"", "zato", "roanjato", "telonjato", "efa-jato",
                 						"dimanjato", "eninjato", "fitonjato", "valonjato", "sivinjato"};
    
    private final String[] milliers = {"", "arivo", "roa arivo", "telo arivo", "efatra arivo",
                						"dimy arivo", "enina arivo", "fito arivo", "valo arivo", "sivy arivo"};
    
    private final String[] dizaines_de_milliers = {"", "iray alina", "roa alina", "telo alina", "efatra alina",
                            						"dimy alina", "enina alina", "fito alina", "valo alina", "sivy alina"};
    
    private final String[] centaines_de_milliers = {"", "iray hetsy", "roa hetsy", "telo hetsy", "efatra hetsy",
                             						"dimy hetsy", "enina hetsy", "fito hetsy", "valo hetsy", "sivy hetsy"};
    


    public String convertirEnLettre(double n) {
        String nombre = String.format("%.3f", n);
        String[] nombreSplitted = nombre.split("\\.");
        int entier = Integer.parseInt(nombreSplitted[0]);
    	int decimal = Integer.parseInt(nombreSplitted[1]);
       
        if(decimal != 0)
        	return nombreEnLettre(entier).concat(" faingo ").concat(nombreEnLettre(decimal));
         else 
        	return nombreEnLettre((int)n);
    }
    

    private String nombreEnLettre(int n) {
    	if(n == 0)
    		return "aotra";
    	if(n == 1)
    		return "iray";
    	
    	return traiterMilliards(n, false);
    }
    

    private String traiterMilliards(int n, boolean resteUn) {
    	if(n < 1000000000)
    		return traiterMillions(n, resteUn);
    	else {
    		int milliard = Math.floorDiv(n, 100000000);
    		int reste = n % 1000000000;
    		resteUn = reste == 1;
    		if(reste == 0)
    			return nombreEnLettre(milliard).concat(" lavitrisa");
    		else 
    			return traiterMillions(reste, resteUn).concat(" sy ").concat(nombreEnLettre(milliard)).concat(" lavitrisa");
    	}
    }
    
    
    private String traiterMillions(int n, boolean resteUn) {
    	if (n < 1000000) 
    		return traiterCentainesDeMilliers(n, resteUn);
    	else {
    		int million = Math.floorDiv(n, 1000000);
    		int reste = n % 1000000;
    		resteUn = reste == 1;
    		if(reste == 0) 
    			return nombreEnLettre(million).concat(" tapitrisa");
    		else
    			return traiterCentainesDeMilliers(reste, resteUn).concat(" sy ").concat(nombreEnLettre(million)).concat(" tapitrisa");
    	}
    }
    

    private String traiterCentainesDeMilliers(int n, boolean resteUn) {
    	if(n < 100000)
    		return traiterDizainesDeMilliers(n, resteUn);
    	else {
    		int centaineDeMillier = Math.floorDiv(n, 100000);
    		int reste = n % 100000;
    		resteUn = reste == 1;
    		if(reste == 0)
    			return centaines_de_milliers[centaineDeMillier];
    		else if(reste < 10000)
    			return traiterMilliers(reste, resteUn).concat(" sy ").concat(centaines_de_milliers[centaineDeMillier]);
    		else 
    			return traiterDizainesDeMilliers(reste, resteUn).concat(" sy ").concat(centaines_de_milliers[centaineDeMillier]);
    	}
    }
    

    private String traiterDizainesDeMilliers(int n, boolean resteUn) {
    	if(n < 10000)
    		return traiterMilliers(n, resteUn);
    	else {
    		int dizaineDeMillier = Math.floorDiv(n, 10000);
    		int reste = n % 10000;
    		resteUn = reste == 1;
    		if(reste == 0)
    			return dizaines_de_milliers[dizaineDeMillier];
    		else if(reste < 1000) 
    			return traiterCentaines(reste, resteUn).concat(" sy ").concat(dizaines_de_milliers[dizaineDeMillier]);
    		else 
    			return traiterMilliers(reste, resteUn).concat(" sy ").concat(dizaines_de_milliers[dizaineDeMillier]);
    	}
    }
    

    private String traiterMilliers(int n, boolean resteUn) {
    	if (n < 1000)
    		return traiterCentaines(n, resteUn);
    	else {
    		int millier = Math.floorDiv(n, 1000);
    		int reste = n % 1000;
    		resteUn = reste == 1;
    		if(reste == 0)
    			return milliers[millier];
    		else if (reste < 100)
    			return traiterDizaines(reste, resteUn).concat(" sy ").concat(milliers[millier]);
    		else 
    			return traiterCentaines(reste, resteUn).concat(" sy ").concat(milliers[millier]);
    	}
    }
    

    private String traiterCentaines(int n, boolean resteUn) {
    	if(n < 100)
    		return traiterDizaines(n, resteUn);
    	else if (n < 200) {
    		int reste = n % 100;
    		return reste == 0 ? "zato" : traiterDizaines(reste, resteUn).concat(" amby zato");
    	} else {
    		int centaine = Math.floorDiv(n, 100);
    		int reste = n % 100;
    		resteUn = reste == 1;
    		String unite = traiterDizaines(reste, resteUn);
    		return reste == 0 ? centaines[centaine] : unite.concat(" sy ").concat(centaines[centaine]);
    	}
    	
    }
    

    private String traiterDizaines(int n, boolean resteUn) {
    	if(n < 10) 
    		return n == 1 && !resteUn ? "iraika" : unites[n];
    	else if (n < 20)
    		return entre_vingt_et_dix[n - 10];
    	else if (n % 10 == 0)
    		return dizaines[Math.floorDiv(n, 10)];
    	else {
    		String unite = n == 1 && !resteUn ? "iraika" : unites[n % 10];
    		return unite.concat(" amby ").concat(dizaines[Math.floorDiv(n, 10)]);
    	}
    }
}
