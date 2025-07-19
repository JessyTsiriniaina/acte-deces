package actedeces.controller;

import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import actedeces.model.ActeDeces;
import actedeces.model.ActeDecesDAO;
import actedeces.utils.FenetresManager;
import actedeces.view.FenetreConsultation;
import actedeces.view.FenetreStatistiques;

public class StatistiqueController {
	
	private FenetreStatistiques fenetreStatistiques;
	private FenetreConsultation fenetreConsultation;
    private ActeDecesDAO acteDecesDAO;
    
    public StatistiqueController(FenetreStatistiques fenetreStatistiques, FenetreConsultation fenetreConsultation,
    		ActeDecesDAO acteDecesDAO) {
    	this.fenetreStatistiques = fenetreStatistiques;
		this.fenetreConsultation = fenetreConsultation;
		this.acteDecesDAO = acteDecesDAO;
		initStatistiquesListener();
		chargerEtAfficherStatistiques();
    }
    
    
    private void initStatistiquesListener() {
    	this.fenetreStatistiques.addActualiserListener(e -> chargerEtAfficherStatistiques());
		this.fenetreStatistiques.addFermerListener(e -> FenetresManager.changerFenetre(this.fenetreStatistiques, this.fenetreConsultation));
    }
	
	
	private void chargerEtAfficherStatistiques() {
        String typeStat = this.fenetreStatistiques.getSelectedStatistiqueType();
        try {
            List<ActeDeces> tousLesActes = this.acteDecesDAO.getAllActeDeces();
            if (tousLesActes.isEmpty()) {
            	this.fenetreStatistiques.afficherMessage("Aucune donnée disponible pour générer des statistiques.", "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
            	this.fenetreStatistiques.afficherGraphique(null); 
                return;
            }
            
            JFreeChart graphique = null;
            String titreGraphique = "";
            Color palette1 = new Color(0, 200, 0, 255);
            Color palette2 = new Color(0, 51, 153);
            Color backgroundColor = new Color(238, 238, 238);
            
            if ("Par sexe".equals(typeStat)) {
                titreGraphique = "Répartition des décès par sexe";
                DefaultPieDataset dataset = new DefaultPieDataset();
                
                Map<String, Integer> comptesSexe = new HashMap<>();
                
                for (ActeDeces acte : tousLesActes) {
                    comptesSexe.put(acte.getSexeDefunt(), comptesSexe.getOrDefault(acte.getSexeDefunt(), 0) + 1);
                }
                for (Map.Entry<String, Integer> entry : comptesSexe.entrySet()) {
                    dataset.setValue(entry.getKey()/* + " (" + entry.getValue() + ")"*/, entry.getValue());
                }
                graphique = ChartFactory.createPieChart(titreGraphique, dataset, true, true, false);
                
                PiePlot plot = (PiePlot) graphique.getPlot();
                plot.setSectionPaint("Masculin" , palette1);
                plot.setSectionPaint("Féminin", palette2);
                graphique.setBackgroundPaint(backgroundColor);
                plot.setBackgroundPaint(backgroundColor);
            } 
            
            
            else if ("Par classe d'âge".equals(typeStat)) {
                titreGraphique = "Répartition des décès par classe d'âge";
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                
                Map<String, Integer> comptesAge = new HashMap<>();
                comptesAge.put("1-10 ans", 0);
                comptesAge.put("11-20 ans", 0);
                comptesAge.put("21-50 ans", 0);
                comptesAge.put("51+ ans", 0);
                comptesAge.put("Âge inconnu", 0);

                for (ActeDeces acte : tousLesActes) {
                    if (acte.getDateNaissanceDefunt() != null && acte.getDateDeces() != null) {
                        int age = calculerAge(acte.getDateNaissanceDefunt(), acte.getDateDeces());
                        if (age >= 1 && age <= 10) comptesAge.put("1-10 ans", comptesAge.get("1-10 ans") + 1);
                        else if (age >= 11 && age <= 20) comptesAge.put("11-20 ans", comptesAge.get("11-20 ans") + 1);
                        else if (age >= 21 && age <= 50) comptesAge.put("21-50 ans", comptesAge.get("21-50 ans") + 1);
                        else if (age >= 51) comptesAge.put("51+ ans", comptesAge.get("51+ ans") + 1);
                        else comptesAge.put("Âge inconnu", comptesAge.get("Âge inconnu") + 1); // Moins de 1 an ou erreur
                    } else {
                        comptesAge.put("Âge inconnu", comptesAge.get("Âge inconnu") + 1);
                    }
                }
                
                dataset.addValue(comptesAge.get("1-10 ans"), "Décès", "1-10 ans");
                dataset.addValue(comptesAge.get("11-20 ans"), "Décès", "11-20 ans");
                dataset.addValue(comptesAge.get("21-50 ans"), "Décès", "21-50 ans");
                dataset.addValue(comptesAge.get("51+ ans"), "Décès", "51+ ans");
                dataset.addValue(comptesAge.get("Âge inconnu"), "Décès", "Âge inconnu");
                
                graphique = ChartFactory.createBarChart(titreGraphique, "Classe d'Âge", "Nombre de décès",
                                                    dataset, PlotOrientation.VERTICAL, true, true, false);
                
                
                CategoryPlot plot = (CategoryPlot) graphique.getPlot();
                BarRenderer renderer = (BarRenderer) plot.getRenderer();
                renderer.setSeriesPaint(0, palette1);
                graphique.setBackgroundPaint(backgroundColor);
                plot.setBackgroundPaint(backgroundColor);
            }

            if (graphique != null) {
                ChartPanel chartPanel = new ChartPanel(graphique);
                chartPanel.setPreferredSize(new java.awt.Dimension(750, 500)); 
                this.fenetreStatistiques.afficherGraphique(chartPanel);
            } else {
            	this.fenetreStatistiques.afficherGraphique(null);
            }

        } catch (SQLException ex) {
        	this.fenetreStatistiques.afficherMessage("Erreur de base de données lors du chargement des données pour les statistiques: " + ex.getMessage(), "Erreur Base de Données", JOptionPane.ERROR_MESSAGE);
        	this.fenetreStatistiques.afficherGraphique(null);
        }
    }
    

    private int calculerAge(java.sql.Date dateNaissance, java.sql.Date dateDeces) {
        if (dateNaissance == null || dateDeces == null) {
            return -1; // Impossible de calculer
        }
        LocalDate naissance = dateNaissance.toLocalDate();
        LocalDate deces = dateDeces.toLocalDate();
        if (naissance.isAfter(deces)) {
            return -1; // Date de naissance après date de décès
        }
        return Period.between(naissance, deces).getYears();
    }
}
