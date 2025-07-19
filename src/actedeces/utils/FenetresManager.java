package actedeces.utils;

import javax.swing.JFrame;

public class FenetresManager {
	
	public static void changerFenetre(JFrame aFermer, JFrame aOuvrir) {
    	aOuvrir.setBounds(aFermer.getBounds());
    	aOuvrir.setVisible(true);
        aFermer.dispose();
    }
}
