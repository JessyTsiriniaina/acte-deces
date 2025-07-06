package actedeces.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
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

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import actedeces.model.ActeDeces;

import actedeces.model.DatabaseManager;
import actedeces.utils.Isa;
import actedeces.view.FenetreApercuActe;
import actedeces.view.FenetreConsultation;
import actedeces.view.FenetreModification;
import actedeces.view.FenetreSaisie;
import actedeces.view.FenetreStatistiques;

public class ActeDecesController {
	
	private FenetreConsultation fenetreConsultation;
    private FenetreSaisie fenetreSaisie;
    private FenetreModification fenetreModification;
    private FenetreApercuActe fenetreApercuActe;
    private FenetreStatistiques fenetreStatistiques;

    private DatabaseManager dbManager;
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
	private final int AUCUNE_COLONNE_SELECTIONNE = -1;


    public ActeDecesController(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.fenetreConsultation = new FenetreConsultation();
        initConsultationListeners();
        actualiserListeActes();
        this.fenetreConsultation.setVisible(true);
    }
    
    
    private void initConsultationListeners() {
        fenetreConsultation.addAjouterListener(e -> ouvrirFenetreSaisie());
        fenetreConsultation.addModifierListener(e -> ouvrirFenetreModification());
        fenetreConsultation.addSupprimerListener(e -> supprimerActeSelectionne());
        fenetreConsultation.addApercuListener(e -> ouvrirFenetreApercu());
        fenetreConsultation.addStatistiquesListener(e -> ouvrirFenetreStatistiques());
    }
    
    
    private void actualiserListeActes() {
        try {
            List<ActeDeces> actes = dbManager.getAllActeDeces();
            fenetreConsultation.afficherActes(actes);
        } catch (SQLException ex) {
            fenetreConsultation.afficherMessage("Erreur lors du chargement des actes: " + ex.getMessage(),
                    "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void ouvrirFenetreSaisie() {
        if (fenetreSaisie == null || !fenetreSaisie.isDisplayable()) {
            fenetreSaisie = new FenetreSaisie();
        }
        fenetreSaisie.reinitialiserChamps();
        fenetreSaisie.addEnregistrerListener(e -> enregistrerNouvelActe());
        fenetreSaisie.addAnnulerListener(e -> changerFenetre(fenetreSaisie, fenetreConsultation));
        changerFenetre(fenetreConsultation, fenetreSaisie);
    }
    
    
    private void ouvrirFenetreModification() {
        int acteId = fenetreConsultation.getSelectedActeId();
        if (acteId == AUCUNE_COLONNE_SELECTIONNE) {
            fenetreConsultation.afficherMessage("Veuillez sélectionner un acte à modifier.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ActeDeces acteAModifier = dbManager.getActeDecesById(acteId);
            if (acteAModifier == null) {
                fenetreConsultation.afficherMessage("Impossible de charger les données de l'acte sélectionné (ID: " + acteId + "). Il a peut-être été supprimé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                actualiserListeActes();
                return;
            }

            if (fenetreModification == null || !fenetreModification.isDisplayable()) {
                fenetreModification = new FenetreModification();
            }
            fenetreModification.chargerActeDeces(acteAModifier);
            fenetreModification.addEnregistrerModificationsListener(e -> enregistrerModificationsActe());
            fenetreModification.addAnnulerListener(e -> changerFenetre(fenetreModification, fenetreConsultation));
            changerFenetre(fenetreConsultation, fenetreModification);
        } catch (SQLException ex) {
            fenetreConsultation.afficherMessage("Erreur de base de données lors du chargement de l'acte: " + ex.getMessage(), "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void ouvrirFenetreApercu() {
        int acteId = fenetreConsultation.getSelectedActeId();
        if (acteId == AUCUNE_COLONNE_SELECTIONNE) {
            fenetreConsultation.afficherMessage("Veuillez sélectionner un acte pour l'aperçu.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            ActeDeces actePourApercu = dbManager.getActeDecesById(acteId);
            if (actePourApercu == null) {
                fenetreConsultation.afficherMessage("Impossible de charger les données de l'acte pour l'aperçu (ID: " + acteId + ").", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (fenetreApercuActe == null || !fenetreApercuActe.isDisplayable()) {
                 fenetreApercuActe = new FenetreApercuActe();
                 fenetreApercuActe.addFermerListener(e -> changerFenetre(fenetreApercuActe, fenetreConsultation));
                 fenetreApercuActe.addExporterListener(e -> exporterPdf(fenetreApercuActe.getEditorPane().getText()));
            }
            
            afficherActe();
            changerFenetre(fenetreConsultation, fenetreApercuActe);
        } catch (SQLException ex) {
             fenetreConsultation.afficherMessage("Erreur de base de données lors du chargement de l'aperçu: " + ex.getMessage(), "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    public void ouvrirFenetreStatistiques() {
        if (fenetreStatistiques == null || !fenetreStatistiques.isDisplayable()) {
            fenetreStatistiques = new FenetreStatistiques();
            fenetreStatistiques.addActualiserListener(e -> chargerEtAfficherStatistiques());
            fenetreStatistiques.addFermerListener(e -> changerFenetre(fenetreStatistiques, fenetreConsultation));
        }
        chargerEtAfficherStatistiques(); 
        changerFenetre(fenetreConsultation, fenetreStatistiques);
    }
    
    
    private void supprimerActeSelectionne() {
        int acteId = fenetreConsultation.getSelectedActeId();
        if (acteId == AUCUNE_COLONNE_SELECTIONNE) {
            fenetreConsultation.afficherMessage("Veuillez sélectionner un acte à supprimer.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(fenetreConsultation,
                "Êtes-vous sûr de vouloir supprimer cet acte (ID: " + acteId + ") ?",
                "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                boolean succes = dbManager.supprimerActeDeces(acteId);
                if (succes) {
                    fenetreConsultation.afficherMessage("Acte supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    actualiserListeActes();
                } else {
                    fenetreConsultation.afficherMessage("Erreur lors de la suppression de l'acte. Il est possible qu'il ait déjà été supprimé.", "Erreur de suppression", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                fenetreConsultation.afficherMessage("Erreur de base de données: " + ex.getMessage(), "Erreur base de données", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
    private void enregistrerNouvelActe() {
        ActeDeces nouvelActe = creerActeDepuisFormulaireSaisie(fenetreSaisie);
        if (nouvelActe == null) return;
        
        try {
            boolean succes = dbManager.ajouterActeDeces(nouvelActe);
            if (succes) {
                fenetreSaisie.afficherMessage("Acte de décès enregistré avec succès (ID: " + nouvelActe.getId() + ").", "Succès", JOptionPane.INFORMATION_MESSAGE);
                fenetreSaisie.reinitialiserChamps();
                changerFenetre(fenetreSaisie, fenetreConsultation);
                actualiserListeActes();
            } else {
                fenetreSaisie.afficherMessage("Erreur lors de l'enregistrement de l'acte.", "Erreur d'enregistrement", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            fenetreSaisie.afficherMessage("Erreur de base de données: " + ex.getMessage(), "Erreur base de bonnées", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private ActeDeces creerActeDepuisFormulaireSaisie(FenetreSaisie form) {
        ActeDeces acte = new ActeDeces();
        
        acte.setNomDefunt(form.getNomDefunt().trim());
        acte.setSexeDefunt(form.getSexeDefunt());
        
        try {
            if (!form.getDateNaissanceDefunt().isEmpty()) acte.setDateNaissanceDefunt(new Date(dateFormat.parse(form.getDateNaissanceDefunt()).getTime()));
            if (!form.getDateDeces().isEmpty()) acte.setDateDeces(new Date(dateFormat.parse(form.getDateDeces()).getTime()));
            if (!form.getHeureDeces().isEmpty()) acte.setHeureDeces(new Time(timeFormat.parse(form.getHeureDeces()).getTime()));
            if (!form.getDateDeclaration().isEmpty()) acte.setDateDeclaration(new Date(dateFormat.parse(form.getDateDeclaration()).getTime()));
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

        String nomDeclarant = form.getNomDeclarant().trim() ;
        acte.setNomDeclarant(nomDeclarant);
        acte.setFonctionDeclarant(form.getFonctionDeclarant());
        acte.setVilleResidenceDeclarant(form.getVilleResidenceDeclarant());
        
        acte.setVilleDeces(form.getVilleDeces());
        acte.setLieuDeces(form.getLieuDeces());
        acte.setRegionDeclaration(form.getRegionDeclaration());
        acte.setCommuneDeclaration(form.getCommuneDeclaration());
        acte.setOfficierEtatCivil(form.getOfficierEtatCivil());
        
        if (acte.getNomDefunt().isEmpty() || acte.getSexeDefunt().isEmpty() ||
            acte.getNomDeclarant().isEmpty() || acte.getRegionDeclaration().isEmpty() ||
            acte.getCommuneDeclaration().isEmpty() || acte.getOfficierEtatCivil().isEmpty() ||
            acte.getDateDeclaration() == null) {
            form.afficherMessage("Veuillez remplir tous les champs obligatoires (*).", "Champs manquants", JOptionPane.WARNING_MESSAGE);
            
            return null; 
        }
        
        System.out.println("Acte créé: " + acte.toString());
        return acte;
    }
    
    
    private void enregistrerModificationsActe() {
        ActeDeces acteModifie = creerActeDepuisFormulaireModification(fenetreModification);
        if (acteModifie == null) return; 

        try {
            boolean succes = dbManager.modifierActeDeces(acteModifie);
            if (succes) {
                fenetreModification.afficherMessage("Modifications enregistrées avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                changerFenetre(fenetreModification, fenetreConsultation);
                actualiserListeActes();
            } else {
                fenetreModification.afficherMessage("Erreur lors de l'enregistrement des modifications.", "Erreur d'enregistrement", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            fenetreModification.afficherMessage("Erreur de base de données: " + ex.getMessage(), "Erreur base de bonnées", JOptionPane.ERROR_MESSAGE);
        }
    }
    
 
    
    private ActeDeces creerActeDepuisFormulaireModification(FenetreModification form) {
        ActeDeces acte = new ActeDeces();
        acte.setId(form.getActeIdEnCoursDeModification());
        
        acte.setNomDefunt(form.getNomDefunt().trim());
        acte.setSexeDefunt(form.getSexeDefunt());
        
        try {
            if (!form.getDateNaissanceDefunt().isEmpty()) acte.setDateNaissanceDefunt(new Date(dateFormat.parse(form.getDateNaissanceDefunt()).getTime()));
            if (!form.getDateDeces().isEmpty()) acte.setDateDeces(new Date(dateFormat.parse(form.getDateDeces()).getTime()));
            if (!form.getHeureDeces().isEmpty()) acte.setHeureDeces(new Time(timeFormat.parse(form.getHeureDeces()).getTime()));
            if (!form.getDateDeclaration().isEmpty()) acte.setDateDeclaration(new Date(dateFormat.parse(form.getDateDeclaration()).getTime()));
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
        
        if (acte.getNomDefunt().isEmpty() || acte.getSexeDefunt().isEmpty() ||
            acte.getNomDeclarant().isEmpty() || acte.getRegionDeclaration().isEmpty() ||
            acte.getCommuneDeclaration().isEmpty() || acte.getOfficierEtatCivil().isEmpty() ||
            acte.getDateDeclaration() == null) {
            form.afficherMessage("Veuillez remplir tous les champs obligatoires (*).", "Champs manquants", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return acte;
    }
    
    
    
    private void chargerEtAfficherStatistiques() {
        String typeStat = fenetreStatistiques.getSelectedStatistiqueType();
        try {
            List<ActeDeces> tousLesActes = dbManager.getAllActeDeces();
            if (tousLesActes.isEmpty()) {
                fenetreStatistiques.afficherMessage("Aucune donnée disponible pour générer des statistiques.", "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
                fenetreStatistiques.afficherGraphique(null); 
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
                fenetreStatistiques.afficherGraphique(chartPanel);
            } else {
                 fenetreStatistiques.afficherGraphique(null);
            }

        } catch (SQLException ex) {
            fenetreStatistiques.afficherMessage("Erreur de base de données lors du chargement des données pour les statistiques: " + ex.getMessage(), "Erreur Base de Données", JOptionPane.ERROR_MESSAGE);
            fenetreStatistiques.afficherGraphique(null);
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
    
    
    
    
    private void afficherActe() {
    	fenetreApercuActe.getEditorPane().setContentType("text/html");
    	Isa isa = new Isa();
    	
    	fenetreApercuActe.getEditorPane().setText(
    		"<!DOCTYPE html>\n"
    		+ "<html lang=\"en\">\n"
    		+ "<head>\n"
    		+ "</head>\n"
    		+ "<body>\n"
    		+ "    <table>\n"
    		+ "        <tr>\n"
    		+ "            <td style=\"width: 25%;\">\n"
    		+ "                <center><b>\n"
    		+ "                    FARITRA ANTSINANANA\n"
    		+ "                    <p></p>\n"
    		+ "                    COMMUNE URBAINE DE TOAMASINA\n"
    		+ "                    <p></p>\n"
    		+ "                    ETAT-CIVIL\n"
    		+ "                    <p></p>\n"
    		+ "                    12 OCTOBRRA 2009\n"
    		+ "                    <p></p>\n"
    		+ "                    FAHAFATESANA\n"
    		+ "                    <p></p>\n"
    		+ "                    <p></p>\n"
    		+ "                    <div>VELONA</div>\n"
    		+ "                    <div>-------------</div>\n"
    		+ "                    <div>KJ</div>\n"
    		+ "                </b></center>\n"
    		+ "            </td>\n"
    		+ "\n"
    		+ "            <td style=\"padding-left: 20px\">\n"
    		+ "                <b><center>\n"
    		+ "                    <div>REPOBLIKAN'I MADAGASIKARA</div>\n"
    		+ "                    <div>Fitiavana - Tanindrazana - Fandrosoana</div>\n"
    		+ "                    <div>-----------------------------</div>\n"
    		+ "                    <p></p>\n"
    		+ "                    <div> =  KOPIAN'NY SORA-PIANKOHONANA  = </div>\n"
    		+ "                    <div> -------------------</div>\n"
    		+ "                    <p></p>\n"
    		+ "                </center></b>\n"
    		+ "\n"
    		+ "                <p>\n"
    		+ "                    ----- Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto quas sit reiciendis doloribus voluptatibus, unde incidunt, error repellendus, consectetur aliquid id voluptate assumenda nemo quod libero obcaecati! Perspiciatis atque quos fugiat laborum optio, sequi nam aliquam numquam corrupti totam necessitatibus quidem recusandae illo quas commodi facere! Aperiam saepe veritatis nobis molestias inventore, cum enim omnis eos sit eum. Facere deleniti quia eos? Ex repudiandae sequi labore ullam nulla, accusamus dolore itaque perspiciatis illum minus enim fuga, aperiam temporibus corporis? Quisquam assumenda repellat sapiente sunt perferendis consequuntur dolores sit repellendus. Eius autem aliquam asperiores voluptatibus iusto inventore sunt vitae eligendi obcaecati!\n"
    		+ "                </p>\n"
    		+ "                <p></p>\n"
    		+ "                <p>\n"
    		+ "                    ----- Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptate ut aspernatur corporis, provident maiores ab! Optio esse perferendis veniam minima quisquam. Iure quaerat eaque illum aut atque! Inventore explicabo eaque similique officia consequuntur aperiam tenetur. Similique accusamus voluptates exercitationem nemo quasi nesciunt! Impedit voluptatum ullam, quis facere natus minima doloribus quasi ducimus maiores, alias id deleniti excepturi cupiditate libero. Distinctio ab enim dicta, sit ad non quibusdam repellat consequuntur totam esse beatae quos aut dignissimos. Voluptatem placeat enim quibusdam omnis illo dolores cum iste mollitia expedita exercitationem modi deleniti esse nostrum porro nihil eaque ipsa minima amet sapiente, laudantium quia.\n"
    		+ "                </p>\n"
    		+ "                <p></p>\n"
    		+ "                <p><center>(SONIA MANARAKA)</center></p>\n"
    		+ "                <p></p>\n"
    		+ "                <p>\n"
    		+ "                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente sed beatae dolores aut, quos, nobis odit deserunt repudiandae, numquam perspiciatis cumque nam velit quo blanditiis delectus esse. Soluta itaque sint sequi a voluptas commodi vel aut minus. Illo, sit ut.\n"
    		+ "                </p>\n"
    		+ "                <p></p>\n"
    		+ "                <p>\n"
    		+ "                    <center>NY MPIANDRAIKITRA NY SORA-PIANKOHONANA</center>\n"
    		+ "                </p>\n"
    		+ "\n"
    		+ "            </td>\n"
    		+ "        </tr>\n"
    		+ "    </table>  \n"
    		+ "</body>\n"
    		+ "</html>"
		);
    	
    	fenetreApercuActe.getEditorPane().setEditable(false);
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
				fenetreConsultation.afficherMessage("Une erreur s'est produit lors la de création du pdf: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
    
    
    private void changerFenetre(JFrame aFermer, JFrame aOuvrir) {
    	aOuvrir.setBounds(aFermer.getBounds());
    	aOuvrir.setVisible(true);
        aFermer.dispose();
    }
}
