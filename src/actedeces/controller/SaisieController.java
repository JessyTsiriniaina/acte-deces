package actedeces.controller;


import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import actedeces.model.ActeDeces;
import actedeces.model.ActeDecesDAO;
import actedeces.utils.ConstructeurObjetDepuisFormulaires;
import actedeces.utils.FenetresManager;
import actedeces.view.FenetreConsultation;
import actedeces.view.FenetreSaisie;


public class SaisieController {
	private FenetreSaisie fenetreSaisie;
	private FenetreConsultation fenetreConsultation;
    private ActeDecesDAO  acteDecesDAO;
	
	public SaisieController(FenetreSaisie fenetreSaisie, FenetreConsultation fenetreConsultation,
			ActeDecesDAO acteDecesDAO) {
		this. acteDecesDAO = acteDecesDAO;
		this.fenetreSaisie = fenetreSaisie;
		this.fenetreConsultation = fenetreConsultation;
		this.fenetreSaisie.reinitialiserChamps();
		this.fenetreSaisie.remplirDateEtHeure();
		initSaisieListener();
	}
	
	private void initSaisieListener() {
		this.fenetreSaisie.addAnnulerListener(e -> FenetresManager.changerFenetre(this.fenetreSaisie, this.fenetreConsultation));
		this.fenetreSaisie.addEnregistrerListener(e -> enregistrerNouvelActe());
	}


	private void enregistrerNouvelActe() {
        ActeDeces nouvelActe = ConstructeurObjetDepuisFormulaires.creerActeDeces(this.fenetreSaisie);
        if (nouvelActe == null) return;
        
        try {
            boolean succes = this.acteDecesDAO.ajouterActeDeces(nouvelActe);
            if (succes) {
            	this.fenetreSaisie.afficherMessage("Acte de décès enregistré avec succès (ID: " + nouvelActe.getId() + ").", "Succès", JOptionPane.INFORMATION_MESSAGE);
            	this.fenetreSaisie.reinitialiserChamps();
                FenetresManager.changerFenetre(fenetreSaisie, fenetreConsultation);
                actualiserListeActes();
            } else {
            	this.fenetreSaisie.afficherMessage("Erreur lors de l'enregistrement de l'acte.", "Erreur d'enregistrement", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
        	this.fenetreSaisie.afficherMessage("Erreur de base de données: " + ex.getMessage(), "Erreur base de bonnées", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	
	private void actualiserListeActes() {
        try {
            List<ActeDeces> actes = acteDecesDAO.getAllActeDeces();
            this.fenetreConsultation.afficherActes(actes);
        } catch (SQLException ex) {
        	this.fenetreConsultation.afficherMessage("Erreur lors du chargement des actes: " + ex.getMessage(),
                    "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
