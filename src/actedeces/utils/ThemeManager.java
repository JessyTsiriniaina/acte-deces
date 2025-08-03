package actedeces.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

public class ThemeManager {
	
    public static final Color BACKGROUND_PRINCIPAL = new Color(222, 229, 237);
    public static final Color LIGHT_PALLETTE = new Color(242, 242, 242);
    public static final Color DARK_PALLETTE = new Color(38, 38, 38);
    public static final Color PALLETTE2 = new Color(86, 109, 126);
    public static final Color PALLETTE1 = new Color(43, 84, 126);


    public static void appliquerTheme() {
        try {      
        	UIManager.put("Panel.background", BACKGROUND_PRINCIPAL);
        	
            UIManager.put("Button.background", PALLETTE1); 
            UIManager.put("Button.foreground", LIGHT_PALLETTE);

            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 13));
            
            UIManager.put("TextField.background", LIGHT_PALLETTE);
            UIManager.put("TextField.foreground", DARK_PALLETTE);
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 13));
            
            UIManager.put("ComboBox.background", LIGHT_PALLETTE);
            UIManager.put("ComboBox.foreground", DARK_PALLETTE);
            UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 13));
            
            UIManager.put("CheckBox.background", BACKGROUND_PRINCIPAL);
            
            UIManager.put("ScrollPane.background", BACKGROUND_PRINCIPAL);
            UIManager.put("Viewport.background", BACKGROUND_PRINCIPAL);
            
            UIManager.put("Table.background", BACKGROUND_PRINCIPAL);
            UIManager.put("Table.foreground", DARK_PALLETTE);
            UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("Table.selectionBackground", new Color(156, 177, 201));
            UIManager.put("Table.selectionForeground", LIGHT_PALLETTE);
            UIManager.put("Table.gridColor", new Color(150, 150, 150));
            UIManager.put("TableHeader.background", PALLETTE1);
            UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 13));
            UIManager.put("TableHeader.foreground", LIGHT_PALLETTE);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'application du thème : " + e.getMessage());
        }
    }
}

