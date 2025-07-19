package actedeces.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import actedeces.model.ActeDeces;
import actedeces.model.ActeDecesDAO;
import actedeces.utils.FenetresManager;
import actedeces.utils.Isa;
import actedeces.view.FenetreApercuActe;
import actedeces.view.FenetreConsultation;

public class ApercuController {
	private FenetreApercuActe fenetreApercuActe;
	private FenetreConsultation fenetreConsultation;
	private ActeDecesDAO acteDecesDAO;
	private int acteId;
	
	public ApercuController(int acteId, FenetreApercuActe fenetreApercuActe, FenetreConsultation fenetreConsultation,
			ActeDecesDAO acteDecesDAO) {
		this.fenetreApercuActe = fenetreApercuActe;
		this.fenetreConsultation = fenetreConsultation;
		this.acteDecesDAO = acteDecesDAO;
		this.acteId = acteId;
		
		afficherActe();
		initApercuListener();
	}
	
	
	private void initApercuListener() {
		this.fenetreApercuActe.addFermerListener(e -> FenetresManager.changerFenetre(this.fenetreApercuActe, this.fenetreConsultation));
		this.fenetreApercuActe.addExporterListener(e -> exporterPdf(this.fenetreApercuActe.getEditorPane().getText()));
	}
	
	
	private void afficherActe() {
		try {
            ActeDeces actePourApercu = this.acteDecesDAO.getActeDecesById(this.acteId);
            if (actePourApercu == null) {
            	this.fenetreConsultation.afficherMessage("Impossible de charger les données de l'acte pour l'aperçu (ID: " + this.acteId + ").", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            afficherActe(actePourApercu);
        } catch (SQLException ex) {
        	this.fenetreConsultation.afficherMessage("Erreur de base de données lors du chargement de l'aperçu: " + ex.getMessage(), "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	
	private void afficherActe(ActeDeces acte) {
		this.fenetreApercuActe.getEditorPane().setContentType("text/html");
    	
    	String nomDefunt = acte.getNomDefunt();
    	String regionDeclaration = acte.getRegionDeclaration().toUpperCase();
    	String communeDeclaration = acte.getCommuneDeclaration().toUpperCase();
    	String dateDeces = dateMalagasy(acte.getDateDeces(), false);
    	String heureMinuteDeces = heureMalagasy(acte.getHeureDeces());
    	String villeDeces = acte.getVilleDeces();
    	String lieuDeces = acte.getLieuDeces();
    	String sexeDefunt = sexeMalagasy(acte.getSexeDefunt());
    	String lieuNaissanceDefunt = acte.getLieuNaissanceDefunt();
    	String dateNaissanceDefunt = dateMalagasy(acte.getDateNaissanceDefunt(), false);
    	String fonctionDefunt = acte.getFonctionDefunt();
    	String villeResidenceDefunt = acte.getVilleResidenceDefunt();
    	
    	String pereDefunt = acte.getNomPereDefunt();
    	String mereDefunt = acte.getNomMereDefunt();
    	boolean pereDefuntVivant = acte.isPereDefuntVivant();
    	boolean mereDefuntVivante = acte.isMereDefuntVivante();
    	String informationParents = infoParents(pereDefunt, mereDefunt, pereDefuntVivant, mereDefuntVivante);
    	String dateDeclaration = dateMalagasy(acte.getDateDeclaration(), false);
    	// String heureDeclaration;
    	// String minuteDeclaration;
    	String  nomDeclarant = acte.getNomDeclarant();
    	String fonctionDeclarant = acte.getFonctionDeclarant();
    	String villeResidenceDeclarant =  acte.getVilleResidenceDeclarant();
    	String officierEtatCivil = acte.getOfficierEtatCivil();
    	String dateCopie = dateMalagasy(LocalDate.now(), false);
    	
    	this.fenetreApercuActe.getEditorPane().setText(
    		"<!DOCTYPE html>\n"
    		+ "<html lang=\"en\">\n"
    		+ "<head>\n"
    		+ "</head>\n"
    		+ "<body>\n"
    		+ "    <table>\n"
    		+ "        <tr>\n"
    		+ "            <td style=\"width: 30%;\">\n"
    		+ "                <center><b>\n"
    		+ "                    FARITRA " + regionDeclaration + "\n"
    		+ "                    <p></p>\n"
    		+ "                    COMMUNE " + communeDeclaration + "\n"
    		+ "                    <p></p>\n"
    		+ "                    ETAT-CIVIL\n"
    		+ "                    <p></p>\n"
    		+ "                    "+ dateMalagasy(acte.getDateDeces(), true)+"\n"
    		+ "                    <p></p>\n"
    		+ "                    FAHAFATESANA\n"
    		+ "                    <p></p>\n"
    		+ "                    <div>"+ nomDefunt +"</div>\n"
    		+ "                    <div>-------------</div>\n"
    		+ "                    <div>KJ</div>\n"
    		+ "                </b></center>\n"
    		+ "            </td>\n"
    		+ "\n"
    		+ "            <td style=\"padding-left: 20px\">\n"
    		+ "                <b><center>\n"
    		+ "                    <div>REPOBLIKAN'I MADAGASIKARA</div>\n"
    		+ "                    <div>Fitiavana - Tanindrazana - Fandrosoana</div>\n"
    		+ "                    <div>--------------</div>\n"
    		+ "                    <p></p>\n"
    		+ "                    <div> =  KOPIAN'NY SORA-PIANKOHONANA  = </div>\n"
    		+ "                    <div> -------------------</div>\n"
    		+ "                    <p></p>\n"
    		+ "                </center></b>\n"
    		+ "\n"
    		+ "                <p>\n"
    		+ "                    ----- Tamin'ny " + dateDeces +"\n"
    		+ (heureMinuteDeces.isBlank() ? "" : (", tamin'ny " + heureMinuteDeces ) + " \n")
    		+ "                    , no maty tao " + villeDeces +"\n"
    		+ (lieuDeces.isBlank() ? "" : (", tao amin'ny " + lieuDeces )+ ", \n")
    		+ "                    "+ nomDefunt + ", " + sexeDefunt + "\n"
    		+ (!lieuNaissanceDefunt.isBlank() || !dateNaissanceDefunt.isBlank() ? ", teraka" : "\n")
    		+ (lieuNaissanceDefunt.isBlank() ? "" : (" tao " + lieuNaissanceDefunt) + ",\n")
    		+ (dateNaissanceDefunt.isBlank() ? "" : (" tamin'ny " + dateNaissanceDefunt) + "\n")
    		+ (fonctionDefunt.isBlank() ? "" : (", " + fonctionDefunt) + "\n")
    		+ (villeResidenceDefunt.isBlank() ? "" : (", nonina tao " + villeResidenceDefunt ))
    		+ (villeResidenceDefunt.isBlank() ? "" : (", zanak'i " + informationParents) + "\n")
    		+ "								.\n"
    		+ "                </p>\n"
    		+ "                <p></p>\n"
    		+ "                <p>\n"
    		+ "                    ----- Nosoratana androany " + dateDeclaration +" \n"
    		+ "                    , araky ny fanambarana nataon'i \n"
    		+ "                    " + nomDeclarant 
    		+ (fonctionDeclarant.isBlank() ? "" :(", " + fonctionDeclarant ) + "\n")
    		+ (villeResidenceDeclarant.isBlank() ? "" : (", monina ao " + villeResidenceDeclarant) + "\n")
    		+ "						, izay miara-manao sonia aminay \n"
    		+ "                    " + officierEtatCivil + ", mpiandraikitra ny Sora-piankohonana.\n"
    		+ "                </p>\n"
    		+ "                <p> </p>\n"
    		+ "                <p><center>(SONIA MANARAKA)</center></p>\n"
    		+ "                <p> </p>\n"
    		+ "                <p>\n"
    		+ "                    Kopia manontolo nadika tamin'ny boky ary nomena\n"
    		+ "                    androany " + dateCopie + "\n"
    		+ "                </p>\n"
    		+ "                <p></p>\n"
    		+ "                <p>\n"
    		+ "                    <center>NY MPIANDRAIKITRA NY SORA-PIANKOHONANA</center>\n"
    		+ "                </p>\n"
    		+ "            </td>\n"
    		+ "        </tr>\n"
    		+ "    </table>  \n"
    		+ "</body>\n"
    		+ "</html>"
		);
    	
    	this.fenetreApercuActe.getEditorPane().setEditable(false);
    }
    
    
    private String infoParents(String pere, String mere, boolean isPereVivant, boolean isMereVivante) {
    	String information = "";
    	boolean pereConnu = !Objects.isNull(pere) && !pere.trim().isEmpty();
    	boolean mereConnu = !Objects.isNull(mere) && !mere.trim().isEmpty();
    	boolean pereVivant = isPereVivant && pereConnu;
    	boolean mereVivante = isMereVivante && mereConnu;
    	
    	if(pereConnu) {
    		information = information.concat(pere);
    		if(!pereVivant) {
    			information = information.concat(", efa maty,");
    		}
    	}
    	
    	if(mereConnu) {
    		if (pereConnu) {
    			information = information.concat(" sy ");
    		}
    		information = information.concat(mere);
    		if(!mereVivante) {
    			information = information.concat(", efa maty");
    		}
    	}
    	
    	return information;
    }
    
    
    private String sexeMalagasy(String sexe) {
    	if(sexe.equals("Masculin"))
    		return "lehilahy";
    	else return "vehivavy";
    }
    
    
    private String dateMalagasy(Date date, boolean isFormatCourt) {
    	if(Objects.isNull(date)) {
    		return "";
    	}
    	
    	Isa isa = new Isa();
    	String mois[] = {"", "Janoary", "Febroary", "Martsa", "Aprily", "May", "Jona", 
				"Jolay", "Aogositra", "Septambra", "Oktobra", "Novambra", "Desambra"};
    	
    	
    	int jourNombre = date.toLocalDate().getDayOfMonth();
    	String andro = isa.convertirEnLettre(jourNombre);
    	String volana = mois[date.toLocalDate().getMonthValue()];
    	int anneeNombre = date.toLocalDate().getYear();
    	String taona = isa.convertirEnLettre(anneeNombre);
    	
    	if(!isFormatCourt) {
    		return andro + " " + volana +", taona "+ taona;
    	}
    	else {
    		return jourNombre + " " + volana.toUpperCase() + " " + anneeNombre;
    	}
    }
    
    private String dateMalagasy(LocalDate date, boolean isFormatCourt) {
    	Isa isa = new Isa();
    	
    	String mois[] = {"", "Janoary", "Febroary", "Martsa", "Aprily", "May", "Jona", 
				"Jolay", "Aogositra", "Septambra", "Oktobra", "Novambra", "Desambra"};
    	
    	if(Objects.isNull(date)) {
    		return "";
    	}
    	
    	int jourNombre = date.getDayOfMonth();
    	String andro = isa.convertirEnLettre(jourNombre);
    	String volana = mois[date.getMonthValue()];
    	int anneeNombre = date.getYear();
    	String taona = isa.convertirEnLettre(anneeNombre);
    	
    	if(!isFormatCourt) {
    		return andro + " " + volana +", taona "+ taona;
    	}
    	else {
    		return jourNombre + " " + volana.toUpperCase() + " " + anneeNombre;
    	}
    }
    
    
    private String heureMalagasy(Time heureEtMinute) {
    	
    	if(Objects.isNull(heureEtMinute)) {
    		return "";
    	}
    	
    	int heureEnFormat24  = heureEtMinute.toLocalTime().getHour();
    	int minute = heureEtMinute.toLocalTime().getMinute();
    	Isa isa = new Isa();
    	
    	if(heureEnFormat24 > 12) {
    		int heureMalagasy = heureEnFormat24 - 12;
    		return isa.convertirEnLettre(heureMalagasy) + " ora sy " + isa.convertirEnLettre(minute) + " minitra, hariva";
    	}
    	else if (heureEnFormat24 == 0) {
    		return "roa ambin'ny folo ora sy " + isa.convertirEnLettre(minute) + " minitra, alina";
    	} else {
    		return isa.convertirEnLettre(heureEnFormat24) + " ora sy " + isa.convertirEnLettre(minute) + " minitra, maraina";	}
    }
    
    
    private void exporterPdf(String contenu){
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setDialogTitle("Enregistrer le PDF");

    	int userSelection = fileChooser.showSaveDialog(null);
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    File fileToSave = fileChooser.getSelectedFile();
    	    String outputPath = fileToSave.getAbsolutePath();
    	    try {
				genererPdf(contenu, outputPath);
			} catch (IOException e) {
				this.fenetreConsultation.afficherMessage("Une erreur s'est produit lors la de création du pdf: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
    	}
    }
    
    
    private void genererPdf(String contenu, String outputPath) throws IOException {
    	try (OutputStream os = new FileOutputStream(outputPath)) {
    		PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(contenu, null);
            builder.toStream(os);
            builder.run();
        }
    }
}
