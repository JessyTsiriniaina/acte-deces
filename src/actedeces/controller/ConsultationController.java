package actedeces.controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import actedeces.model.ActeDeces;

import actedeces.model.ActeDecesDAO;
import actedeces.utils.FenetresManager;
import actedeces.view.FenetreApercuActe;
import actedeces.view.FenetreConsultation;
import actedeces.view.FenetreModification;
import actedeces.view.FenetreSaisie;
import actedeces.view.FenetreStatistiques;

public class ConsultationController {
	
	private FenetreConsultation fenetreConsultation;
    private FenetreSaisie fenetreSaisie;
    private FenetreModification fenetreModification;
    private FenetreApercuActe fenetreApercuActe;
    private FenetreStatistiques fenetreStatistiques;
    private ActeDecesDAO acteDecesDAO;;
    
	private final int AUCUN_ACTE_SELECTIONNE = -1;


    public ConsultationController(ActeDecesDAO acteDecesDAO) {
    	this.acteDecesDAO = acteDecesDAO;
        this.fenetreConsultation = new FenetreConsultation();
        initConsultationListeners();
        actualiserListeActes();
        this.fenetreConsultation.setVisible(true);
    }
    
    
    private void initConsultationListeners() {
    	this.fenetreConsultation.addAjouterListener(e -> ouvrirFenetreSaisie());
    	this.fenetreConsultation.addModifierListener(e -> ouvrirFenetreModification());
    	this.fenetreConsultation.addSupprimerListener(e -> supprimerActeSelectionne());
    	this.fenetreConsultation.addApercuListener(e -> ouvrirFenetreApercu());
    	this.fenetreConsultation.addStatistiquesListener(e -> ouvrirFenetreStatistiques());
    	this.fenetreConsultation.addMotCleChangeListener(
    			new DocumentListener() {
    				@Override
    				public void insertUpdate(DocumentEvent e) {
    					actualiserListeActes(fenetreConsultation.getMotCle());
    				}

					@Override
					public void removeUpdate(DocumentEvent e) {
						actualiserListeActes(fenetreConsultation.getMotCle());
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						actualiserListeActes(fenetreConsultation.getMotCle());
					}
    			}
    	);
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
    
    
    private void actualiserListeActes(String motCle) {
        try {
        	if(motCle.isBlank()) {
        		actualiserListeActes();
        		return;
        	}
            List<ActeDeces> actes = acteDecesDAO.getAllActeDeces();
            this.fenetreConsultation.afficherActes(actes, motCle);
        } catch (SQLException ex) {
        	this.fenetreConsultation.afficherMessage("Erreur lors du chargement des actes: " + ex.getMessage(),
                    "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void ouvrirFenetreSaisie() {
        if (this.fenetreSaisie == null || !this.fenetreSaisie.isDisplayable()) {
        	this.fenetreSaisie = new FenetreSaisie();
        }
        new SaisieController(this.fenetreSaisie, this.fenetreConsultation, this.acteDecesDAO);
        FenetresManager.changerFenetre(this.fenetreConsultation, this.fenetreSaisie);
    }
    
    
    private void ouvrirFenetreModification() {
        int acteId = this.fenetreConsultation.getSelectedActeId();
        if (acteId == AUCUN_ACTE_SELECTIONNE) {
        	this.fenetreConsultation.afficherMessage("Veuillez sélectionner un acte à modifier.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (fenetreModification == null || !fenetreModification.isDisplayable()) {
            fenetreModification = new FenetreModification();
        }
        new ModificationController(acteId, this.fenetreModification, this.fenetreConsultation, this.acteDecesDAO);
        FenetresManager.changerFenetre(this.fenetreConsultation, this.fenetreModification);
    }
    
    
    private void ouvrirFenetreApercu() {
        int acteId = this.fenetreConsultation.getSelectedActeId();
        if (acteId == AUCUN_ACTE_SELECTIONNE) {
        	this.fenetreConsultation.afficherMessage("Veuillez sélectionner un acte pour l'aperçu.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (fenetreApercuActe == null || !fenetreApercuActe.isDisplayable()) {
            fenetreApercuActe = new FenetreApercuActe();
        }
        new ApercuController(acteId, this.fenetreApercuActe, this.fenetreConsultation, this.acteDecesDAO);
        FenetresManager.changerFenetre(this.fenetreConsultation, this.fenetreApercuActe);
    }
    
    
    public void ouvrirFenetreStatistiques() {
    	if (fenetreStatistiques == null || !fenetreStatistiques.isDisplayable()) {
            fenetreStatistiques = new FenetreStatistiques();
        }
        new StatistiqueController(this.fenetreStatistiques, this.fenetreConsultation, this.acteDecesDAO);
        FenetresManager.changerFenetre(this.fenetreConsultation, this.fenetreStatistiques);
    }
    
    
    private void supprimerActeSelectionne() {
        int acteId = this.fenetreConsultation.getSelectedActeId();
        if (acteId == AUCUN_ACTE_SELECTIONNE) {
        	this.fenetreConsultation.afficherMessage("Veuillez sélectionner un acte à supprimer.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this.fenetreConsultation,
                "Êtes-vous sûr de vouloir supprimer cet acte (ID: " + acteId + ") ?",
                "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                boolean succes = this.acteDecesDAO.supprimerActeDeces(acteId);
                if (succes) {
                	this.fenetreConsultation.afficherMessage("Acte supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    actualiserListeActes();
                } else {
                	this.fenetreConsultation.afficherMessage("Erreur lors de la suppression de l'acte. Il est possible qu'il ait déjà été supprimé.", "Erreur de suppression", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
            	this.fenetreConsultation.afficherMessage("Erreur de base de données: " + ex.getMessage(), "Erreur base de données", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
