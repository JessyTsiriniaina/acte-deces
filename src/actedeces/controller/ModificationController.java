package actedeces.controller;

//La liste des acte de deces dans la fenetre consultation doit être mise à jour après enregistrement

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import actedeces.model.ActeDeces;
import actedeces.model.ActeDecesDAO;
import actedeces.utils.ConstructeurObjetDepuisFormulaires;
import actedeces.utils.FenetresManager;
import actedeces.view.FenetreConsultation;
import actedeces.view.FenetreModification;

public class ModificationController {
	private FenetreModification fenetreModification;
	private FenetreConsultation fenetreConsultation;
    private ActeDecesDAO acteDecesDAO;
    private int acteId;
    
    public ModificationController(int acteId, FenetreModification fenetreModification, FenetreConsultation fenetreConsultation,
    		ActeDecesDAO acteDecesDAO) {
    	this.acteDecesDAO = acteDecesDAO;
    	this.fenetreModification = fenetreModification;
		this.fenetreConsultation = fenetreConsultation;
		this.acteId = acteId;
        initModificationListener();
        chargerActeDeces();
    }
    
    
    private void initModificationListener() {
    	this.fenetreModification.addEnregistrerModificationsListener(e -> enregistrerModificationsActe());
        this.fenetreModification.addAnnulerListener(e -> FenetresManager.changerFenetre(this.fenetreModification, this.fenetreConsultation));
    }
    
    
    private void chargerActeDeces() {
    	try {
            ActeDeces acteAModifier = this.acteDecesDAO.getActeDecesById(acteId);
            if (acteAModifier == null) {
            	this.fenetreConsultation.afficherMessage("Impossible de charger les données de l'acte sélectionné (ID: " + acteId + "). Il a peut-être été supprimé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.fenetreModification.chargerActeDeces(acteAModifier);
        } catch (SQLException ex) {
        	this.fenetreConsultation.afficherMessage("Erreur de base de données lors du chargement de l'acte: " + ex.getMessage(), "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	
	
	private void enregistrerModificationsActe() {
        ActeDeces acteModifie = ConstructeurObjetDepuisFormulaires.creerActeDeces(this.fenetreModification);
        if (acteModifie == null) return; 
        acteModifie.setId(this.fenetreConsultation.getSelectedActeId());

        try {
            boolean succes = this.acteDecesDAO.modifierActeDeces(acteModifie);
            if (succes) {
            	this.fenetreModification.afficherMessage("Modifications enregistrées avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            	FenetresManager.changerFenetre(this.fenetreModification, this.fenetreConsultation);
            	actualiserListeActes();
            } else {
            	this.fenetreModification.afficherMessage("Erreur lors de l'enregistrement des modifications.", "Erreur d'enregistrement", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
        	this.fenetreModification.afficherMessage("Erreur de base de données: " + ex.getMessage(), "Erreur base de bonnées", JOptionPane.ERROR_MESSAGE);
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
