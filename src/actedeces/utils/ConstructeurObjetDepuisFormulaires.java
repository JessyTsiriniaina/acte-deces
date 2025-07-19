package actedeces.utils;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import actedeces.model.ActeDeces;

public class ConstructeurObjetDepuisFormulaires {
	
	public static ActeDeces creerActeDeces(Formulaire form) {
		SimpleDateFormat dateFormat = FormatDateHeure.getDateFormat();
	    SimpleDateFormat timeFormat = FormatDateHeure.getHeureFormat();
	    
        ActeDeces acte = new ActeDeces();
        
        acte.setNomDefunt(form.getNomDefunt().trim());
        acte.setSexeDefunt(form.getSexeDefunt());
        
        try {
            if (!form.getDateNaissanceDefunt().isBlank()) acte.setDateNaissanceDefunt(new Date(dateFormat.parse(form.getDateNaissanceDefunt()).getTime()));
            if (!form.getDateDeces().isBlank()) acte.setDateDeces(new Date(dateFormat.parse(form.getDateDeces()).getTime()));
            if (!form.getHeureDeces().isBlank()) acte.setHeureDeces(new Time(timeFormat.parse(form.getHeureDeces()).getTime()));
            if (!form.getDateDeclaration().isBlank()) acte.setDateDeclaration(new Date(dateFormat.parse(form.getDateDeclaration()).getTime()));
        } catch (ParseException ex) {
            form.afficherMessage("Format de date ou d'heure invalide (JJ/MM/AAAA ou HH:MM). " + ex.getMessage(), "Erreur de Format", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        acte.setLieuNaissanceDefunt(form.getLieuNaissanceDefunt());
        acte.setVilleResidenceDefunt(form.getVilleResidenceDefunt());
        acte.setFonctionDefunt(form.getFonctionDefunt());
        acte.setNomPereDefunt(form.getNomPereDefunt());
        acte.setPereDefuntVivant(form.isPereDefuntVivant());
        acte.setNomMereDefunt(form.getNomMereDefunt());
        acte.setMereDefuntVivante(form.isMereDefuntVivante());

        acte.setNomDeclarant(form.getNomDeclarant().trim());
        acte.setFonctionDeclarant(form.getFonctionDeclarant());
        acte.setVilleResidenceDeclarant(form.getVilleResidenceDeclarant());
        
        acte.setVilleDeces(form.getVilleDeces());
        acte.setLieuDeces(form.getLieuDeces());
        acte.setRegionDeclaration(form.getRegionDeclaration());
        acte.setCommuneDeclaration(form.getCommuneDeclaration());
        acte.setOfficierEtatCivil(form.getOfficierEtatCivil());
        
        if (acte.getNomDefunt().isBlank() || acte.getSexeDefunt().isBlank() ||
        	acte.getDateDeces() == null || acte.getVilleDeces().isBlank() ||
            acte.getNomDeclarant().isBlank() || acte.getRegionDeclaration().isBlank() ||
            acte.getCommuneDeclaration().isBlank() || acte.getOfficierEtatCivil().isBlank() ||
            acte.getDateDeclaration() == null) {
            form.afficherMessage("Veuillez remplir tous les champs obligatoires (*).", "Champs manquants", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return acte;
    }
}
