package actedeces.view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.FlowLayout;

import actedeces.model.ActeDeces;
import actedeces.utils.FormatDateHeure;
import actedeces.utils.Formulaire;

public class FenetreModification extends JFrame implements Formulaire {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDateNaissanceDefunt;
	private JTextField txtLieuNaissanceDefunt;
	private JTextField txtFonctionDefunt;
	private JTextField txtNomDefunt;
	private JTextField txtVilleResidenceDefunt;
	private JTextField txtNomPereDefunt;
	private JTextField txtNomMereDefunt;
	private JTextField txtDateDeces;
	private JTextField txtHeureDeces;
	private JTextField txtVilleDeces;
	private JTextField txtLieuDeces;
	private JTextField txtNomDeclarant;
	private JTextField txtFonctionDeclarant;
	private JTextField txtVilleResidenceDeclarant;
	private JTextField txtCommuneDeclaration;
	private JTextField txtOfficierEtatCivil;
	private JTextField txtDateDeclaration;
	private JComboBox<String> comboSexeDefunt;
	private JCheckBox chkPereDefuntVivant;
	private JCheckBox chkMereDefuntVivante;
	private JComboBox<String> comboRegionDeclaration;
	
	private JButton btnEnregistrerModifications;
    private JButton btnAnnuler;

    private int acteIdEnCoursDeModification;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreModification frame = new FenetreModification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetreModification() {
		setTitle("Modification d'un acte de décès");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		setMinimumSize(new Dimension(1200, 650));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50, 0, 0, 0, 200, 50, 0};
		gbl_contentPane.rowHeights = new int[]{20, 0, 30, 0, 30, 20, 0, 30, 30, 20, 0, 30, 30, 20, 0, 30, 30, 20, 0, 30, 30, 20, 0, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblInformationsSurLe = new JLabel("Informations sur le défunt");
		lblInformationsSurLe.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblInformationsSurLe = new GridBagConstraints();
		gbc_lblInformationsSurLe.anchor = GridBagConstraints.WEST;
		gbc_lblInformationsSurLe.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformationsSurLe.gridx = 1;
		gbc_lblInformationsSurLe.gridy = 1;
		contentPane.add(lblInformationsSurLe, gbc_lblInformationsSurLe);
		
		JLabel lblNomDfunt = new JLabel("Nom défunt:*");
		GridBagConstraints gbc_lblNomDfunt = new GridBagConstraints();
		gbc_lblNomDfunt.anchor = GridBagConstraints.EAST;
		gbc_lblNomDfunt.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomDfunt.gridx = 1;
		gbc_lblNomDfunt.gridy = 2;
		contentPane.add(lblNomDfunt, gbc_lblNomDfunt);
		
		txtNomDefunt = new JTextField();
		txtNomDefunt.setColumns(10);
		GridBagConstraints gbc_txtNomDefunt = new GridBagConstraints();
		gbc_txtNomDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_txtNomDefunt.fill = GridBagConstraints.BOTH;
		gbc_txtNomDefunt.gridx = 2;
		gbc_txtNomDefunt.gridy = 2;
		contentPane.add(txtNomDefunt, gbc_txtNomDefunt);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance: ");
		GridBagConstraints gbc_lblDateDeNaissance = new GridBagConstraints();
		gbc_lblDateDeNaissance.anchor = GridBagConstraints.EAST;
		gbc_lblDateDeNaissance.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDeNaissance.gridx = 3;
		gbc_lblDateDeNaissance.gridy = 2;
		contentPane.add(lblDateDeNaissance, gbc_lblDateDeNaissance);
		
		txtDateNaissanceDefunt = new JTextField();
		GridBagConstraints gbc_txtDateNaissanceDefunt = new GridBagConstraints();
		gbc_txtDateNaissanceDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_txtDateNaissanceDefunt.fill = GridBagConstraints.BOTH;
		gbc_txtDateNaissanceDefunt.gridx = 4;
		gbc_txtDateNaissanceDefunt.gridy = 2;
		contentPane.add(txtDateNaissanceDefunt, gbc_txtDateNaissanceDefunt);
		txtDateNaissanceDefunt.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe:*");
		GridBagConstraints gbc_lblSexe = new GridBagConstraints();
		gbc_lblSexe.anchor = GridBagConstraints.EAST;
		gbc_lblSexe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSexe.gridx = 1;
		gbc_lblSexe.gridy = 3;
		contentPane.add(lblSexe, gbc_lblSexe);
		
		comboSexeDefunt = new JComboBox<String>(new String[]{"Masculin","Féminin"});
		comboSexeDefunt.setSelectedIndex(0);
		comboSexeDefunt.setMaximumRowCount(2);
		GridBagConstraints gbc_comboSexeDefunt = new GridBagConstraints();
		gbc_comboSexeDefunt.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboSexeDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_comboSexeDefunt.gridx = 2;
		gbc_comboSexeDefunt.gridy = 3;
		contentPane.add(comboSexeDefunt, gbc_comboSexeDefunt);
		
		JLabel lblLieuDeNaissance = new JLabel("Lieu de naissance: ");
		GridBagConstraints gbc_lblLieuDeNaissance = new GridBagConstraints();
		gbc_lblLieuDeNaissance.anchor = GridBagConstraints.EAST;
		gbc_lblLieuDeNaissance.insets = new Insets(0, 0, 5, 5);
		gbc_lblLieuDeNaissance.gridx = 3;
		gbc_lblLieuDeNaissance.gridy = 3;
		contentPane.add(lblLieuDeNaissance, gbc_lblLieuDeNaissance);
		
		txtLieuNaissanceDefunt = new JTextField();
		GridBagConstraints gbc_txtLieuNaissanceDefunt = new GridBagConstraints();
		gbc_txtLieuNaissanceDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_txtLieuNaissanceDefunt.fill = GridBagConstraints.BOTH;
		gbc_txtLieuNaissanceDefunt.gridx = 4;
		gbc_txtLieuNaissanceDefunt.gridy = 3;
		contentPane.add(txtLieuNaissanceDefunt, gbc_txtLieuNaissanceDefunt);
		txtLieuNaissanceDefunt.setColumns(10);
		
		JLabel lblVilleDeResidence = new JLabel("Ville de residence: ");
		GridBagConstraints gbc_lblVilleDeResidence = new GridBagConstraints();
		gbc_lblVilleDeResidence.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDeResidence.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDeResidence.gridx = 1;
		gbc_lblVilleDeResidence.gridy = 4;
		contentPane.add(lblVilleDeResidence, gbc_lblVilleDeResidence);
		
		txtVilleResidenceDefunt = new JTextField();
		txtVilleResidenceDefunt.setColumns(10);
		GridBagConstraints gbc_txtVilleResidenceDefunt = new GridBagConstraints();
		gbc_txtVilleResidenceDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_txtVilleResidenceDefunt.fill = GridBagConstraints.BOTH;
		gbc_txtVilleResidenceDefunt.gridx = 2;
		gbc_txtVilleResidenceDefunt.gridy = 4;
		contentPane.add(txtVilleResidenceDefunt, gbc_txtVilleResidenceDefunt);
		
		JLabel lblFonctionprofession = new JLabel("Fonction/Profession: ");
		GridBagConstraints gbc_lblFonctionprofession = new GridBagConstraints();
		gbc_lblFonctionprofession.anchor = GridBagConstraints.EAST;
		gbc_lblFonctionprofession.insets = new Insets(0, 0, 5, 5);
		gbc_lblFonctionprofession.gridx = 3;
		gbc_lblFonctionprofession.gridy = 4;
		contentPane.add(lblFonctionprofession, gbc_lblFonctionprofession);
		
		txtFonctionDefunt = new JTextField();
		txtFonctionDefunt.setColumns(10);
		GridBagConstraints gbc_txtFonctionDefunt = new GridBagConstraints();
		gbc_txtFonctionDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_txtFonctionDefunt.fill = GridBagConstraints.BOTH;
		gbc_txtFonctionDefunt.gridx = 4;
		gbc_txtFonctionDefunt.gridy = 4;
		contentPane.add(txtFonctionDefunt, gbc_txtFonctionDefunt);
		
		JLabel lblFiliationDuDfunt = new JLabel("Filiation du défunt");
		lblFiliationDuDfunt.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblFiliationDuDfunt = new GridBagConstraints();
		gbc_lblFiliationDuDfunt.anchor = GridBagConstraints.WEST;
		gbc_lblFiliationDuDfunt.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiliationDuDfunt.gridx = 1;
		gbc_lblFiliationDuDfunt.gridy = 6;
		contentPane.add(lblFiliationDuDfunt, gbc_lblFiliationDuDfunt);
		
		JLabel lblNomDuPre = new JLabel("Nom du père: ");
		GridBagConstraints gbc_lblNomDuPre = new GridBagConstraints();
		gbc_lblNomDuPre.anchor = GridBagConstraints.EAST;
		gbc_lblNomDuPre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomDuPre.gridx = 1;
		gbc_lblNomDuPre.gridy = 7;
		contentPane.add(lblNomDuPre, gbc_lblNomDuPre);
		
		txtNomPereDefunt = new JTextField();
		GridBagConstraints gbc_txtNomPereDefunt = new GridBagConstraints();
		gbc_txtNomPereDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_txtNomPereDefunt.fill = GridBagConstraints.BOTH;
		gbc_txtNomPereDefunt.gridx = 2;
		gbc_txtNomPereDefunt.gridy = 7;
		contentPane.add(txtNomPereDefunt, gbc_txtNomPereDefunt);
		txtNomPereDefunt.setColumns(10);
		
		chkPereDefuntVivant = new JCheckBox("Vivant");
		GridBagConstraints gbc_chkPereDefuntVivant = new GridBagConstraints();
		gbc_chkPereDefuntVivant.anchor = GridBagConstraints.WEST;
		gbc_chkPereDefuntVivant.insets = new Insets(0, 0, 5, 5);
		gbc_chkPereDefuntVivant.gridx = 3;
		gbc_chkPereDefuntVivant.gridy = 7;
		contentPane.add(chkPereDefuntVivant, gbc_chkPereDefuntVivant);
		
		JLabel lblNomDeLa = new JLabel("Nom de la mère: ");
		GridBagConstraints gbc_lblNomDeLa = new GridBagConstraints();
		gbc_lblNomDeLa.anchor = GridBagConstraints.EAST;
		gbc_lblNomDeLa.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomDeLa.gridx = 1;
		gbc_lblNomDeLa.gridy = 8;
		contentPane.add(lblNomDeLa, gbc_lblNomDeLa);
		
		txtNomMereDefunt = new JTextField();
		GridBagConstraints gbc_txtNomMereDefunt = new GridBagConstraints();
		gbc_txtNomMereDefunt.insets = new Insets(0, 0, 5, 5);
		gbc_txtNomMereDefunt.fill = GridBagConstraints.BOTH;
		gbc_txtNomMereDefunt.gridx = 2;
		gbc_txtNomMereDefunt.gridy = 8;
		contentPane.add(txtNomMereDefunt, gbc_txtNomMereDefunt);
		txtNomMereDefunt.setColumns(10);
		
		chkMereDefuntVivante = new JCheckBox("Vivante");
		GridBagConstraints gbc_chkMereDefuntVivante = new GridBagConstraints();
		gbc_chkMereDefuntVivante.anchor = GridBagConstraints.WEST;
		gbc_chkMereDefuntVivante.insets = new Insets(0, 0, 5, 5);
		gbc_chkMereDefuntVivante.gridx = 3;
		gbc_chkMereDefuntVivante.gridy = 8;
		contentPane.add(chkMereDefuntVivante, gbc_chkMereDefuntVivante);
		
		JLabel lblInformationsSurLe_1 = new JLabel("Informations sur le décès");
		lblInformationsSurLe_1.setBackground(UIManager.getColor("Label.background"));
		lblInformationsSurLe_1.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblInformationsSurLe_1 = new GridBagConstraints();
		gbc_lblInformationsSurLe_1.anchor = GridBagConstraints.WEST;
		gbc_lblInformationsSurLe_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformationsSurLe_1.gridx = 1;
		gbc_lblInformationsSurLe_1.gridy = 10;
		contentPane.add(lblInformationsSurLe_1, gbc_lblInformationsSurLe_1);
		
		JLabel lblDateDuDcs = new JLabel("Date du décès:");
		GridBagConstraints gbc_lblDateDuDcs = new GridBagConstraints();
		gbc_lblDateDuDcs.anchor = GridBagConstraints.EAST;
		gbc_lblDateDuDcs.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDuDcs.gridx = 1;
		gbc_lblDateDuDcs.gridy = 11;
		contentPane.add(lblDateDuDcs, gbc_lblDateDuDcs);
		
		txtDateDeces = new JTextField();
		GridBagConstraints gbc_txtDateDeces = new GridBagConstraints();
		gbc_txtDateDeces.insets = new Insets(0, 0, 5, 5);
		gbc_txtDateDeces.fill = GridBagConstraints.BOTH;
		gbc_txtDateDeces.gridx = 2;
		gbc_txtDateDeces.gridy = 11;
		contentPane.add(txtDateDeces, gbc_txtDateDeces);
		txtDateDeces.setColumns(10);
		
		JLabel lblVilleDuDcs = new JLabel("Ville du décès: ");
		GridBagConstraints gbc_lblVilleDuDcs = new GridBagConstraints();
		gbc_lblVilleDuDcs.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDuDcs.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDuDcs.gridx = 3;
		gbc_lblVilleDuDcs.gridy = 11;
		contentPane.add(lblVilleDuDcs, gbc_lblVilleDuDcs);
		
		txtVilleDeces = new JTextField();
		GridBagConstraints gbc_txtVilleDeces = new GridBagConstraints();
		gbc_txtVilleDeces.insets = new Insets(0, 0, 5, 5);
		gbc_txtVilleDeces.fill = GridBagConstraints.BOTH;
		gbc_txtVilleDeces.gridx = 4;
		gbc_txtVilleDeces.gridy = 11;
		contentPane.add(txtVilleDeces, gbc_txtVilleDeces);
		txtVilleDeces.setColumns(10);
		
		JLabel lblHeureDuDcs = new JLabel("Heure du décès: ");
		GridBagConstraints gbc_lblHeureDuDcs = new GridBagConstraints();
		gbc_lblHeureDuDcs.anchor = GridBagConstraints.EAST;
		gbc_lblHeureDuDcs.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeureDuDcs.gridx = 1;
		gbc_lblHeureDuDcs.gridy = 12;
		contentPane.add(lblHeureDuDcs, gbc_lblHeureDuDcs);
		
		txtHeureDeces = new JTextField();
		GridBagConstraints gbc_txtHeureDeces = new GridBagConstraints();
		gbc_txtHeureDeces.insets = new Insets(0, 0, 5, 5);
		gbc_txtHeureDeces.fill = GridBagConstraints.BOTH;
		gbc_txtHeureDeces.gridx = 2;
		gbc_txtHeureDeces.gridy = 12;
		contentPane.add(txtHeureDeces, gbc_txtHeureDeces);
		txtHeureDeces.setColumns(10);
		
		JLabel lblLieuDuDcs = new JLabel("Lieu du décès: ");
		GridBagConstraints gbc_lblLieuDuDcs = new GridBagConstraints();
		gbc_lblLieuDuDcs.anchor = GridBagConstraints.EAST;
		gbc_lblLieuDuDcs.insets = new Insets(0, 0, 5, 5);
		gbc_lblLieuDuDcs.gridx = 3;
		gbc_lblLieuDuDcs.gridy = 12;
		contentPane.add(lblLieuDuDcs, gbc_lblLieuDuDcs);
		
		txtLieuDeces = new JTextField();
		GridBagConstraints gbc_txtLieuDeces = new GridBagConstraints();
		gbc_txtLieuDeces.insets = new Insets(0, 0, 5, 5);
		gbc_txtLieuDeces.fill = GridBagConstraints.BOTH;
		gbc_txtLieuDeces.gridx = 4;
		gbc_txtLieuDeces.gridy = 12;
		contentPane.add(txtLieuDeces, gbc_txtLieuDeces);
		txtLieuDeces.setColumns(10);
		
		JLabel lblInformationSurLe = new JLabel("Information sur le déclarant");
		lblInformationSurLe.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblInformationSurLe = new GridBagConstraints();
		gbc_lblInformationSurLe.anchor = GridBagConstraints.WEST;
		gbc_lblInformationSurLe.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformationSurLe.gridx = 1;
		gbc_lblInformationSurLe.gridy = 14;
		contentPane.add(lblInformationSurLe, gbc_lblInformationSurLe);
		
		JLabel lblNomDuDclarant = new JLabel("Nom du déclarant:*");
		GridBagConstraints gbc_lblNomDuDclarant = new GridBagConstraints();
		gbc_lblNomDuDclarant.anchor = GridBagConstraints.EAST;
		gbc_lblNomDuDclarant.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomDuDclarant.gridx = 1;
		gbc_lblNomDuDclarant.gridy = 15;
		contentPane.add(lblNomDuDclarant, gbc_lblNomDuDclarant);
		
		txtNomDeclarant = new JTextField();
		GridBagConstraints gbc_txtNomDeclarant = new GridBagConstraints();
		gbc_txtNomDeclarant.insets = new Insets(0, 0, 5, 5);
		gbc_txtNomDeclarant.fill = GridBagConstraints.BOTH;
		gbc_txtNomDeclarant.gridx = 2;
		gbc_txtNomDeclarant.gridy = 15;
		contentPane.add(txtNomDeclarant, gbc_txtNomDeclarant);
		txtNomDeclarant.setColumns(10);
		
		JLabel lblVilleDeRsidence = new JLabel("Ville de résidence:");
		GridBagConstraints gbc_lblVilleDeRsidence = new GridBagConstraints();
		gbc_lblVilleDeRsidence.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDeRsidence.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDeRsidence.gridx = 3;
		gbc_lblVilleDeRsidence.gridy = 15;
		contentPane.add(lblVilleDeRsidence, gbc_lblVilleDeRsidence);
		
		txtVilleResidenceDeclarant = new JTextField();
		GridBagConstraints gbc_txtVilleResidenceDeclarant = new GridBagConstraints();
		gbc_txtVilleResidenceDeclarant.insets = new Insets(0, 0, 5, 5);
		gbc_txtVilleResidenceDeclarant.fill = GridBagConstraints.BOTH;
		gbc_txtVilleResidenceDeclarant.gridx = 4;
		gbc_txtVilleResidenceDeclarant.gridy = 15;
		contentPane.add(txtVilleResidenceDeclarant, gbc_txtVilleResidenceDeclarant);
		txtVilleResidenceDeclarant.setColumns(10);
		
		JLabel lblFonctionDclarant = new JLabel("Fonction déclarant: ");
		GridBagConstraints gbc_lblFonctionDclarant = new GridBagConstraints();
		gbc_lblFonctionDclarant.anchor = GridBagConstraints.EAST;
		gbc_lblFonctionDclarant.insets = new Insets(0, 0, 5, 5);
		gbc_lblFonctionDclarant.gridx = 1;
		gbc_lblFonctionDclarant.gridy = 16;
		contentPane.add(lblFonctionDclarant, gbc_lblFonctionDclarant);
		
		txtFonctionDeclarant = new JTextField();
		GridBagConstraints gbc_txtFonctionDeclarant = new GridBagConstraints();
		gbc_txtFonctionDeclarant.insets = new Insets(0, 0, 5, 5);
		gbc_txtFonctionDeclarant.fill = GridBagConstraints.BOTH;
		gbc_txtFonctionDeclarant.gridx = 2;
		gbc_txtFonctionDeclarant.gridy = 16;
		contentPane.add(txtFonctionDeclarant, gbc_txtFonctionDeclarant);
		txtFonctionDeclarant.setColumns(10);
		
		JLabel lblDclarationOfficielle = new JLabel("Déclaration officielle");
		lblDclarationOfficielle.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblDclarationOfficielle = new GridBagConstraints();
		gbc_lblDclarationOfficielle.anchor = GridBagConstraints.WEST;
		gbc_lblDclarationOfficielle.insets = new Insets(0, 0, 5, 5);
		gbc_lblDclarationOfficielle.gridx = 1;
		gbc_lblDclarationOfficielle.gridy = 18;
		contentPane.add(lblDclarationOfficielle, gbc_lblDclarationOfficielle);
		
		JLabel lblRgionDclaration = new JLabel("Région déclaration:*");
		GridBagConstraints gbc_lblRgionDclaration = new GridBagConstraints();
		gbc_lblRgionDclaration.anchor = GridBagConstraints.EAST;
		gbc_lblRgionDclaration.insets = new Insets(0, 0, 5, 5);
		gbc_lblRgionDclaration.gridx = 1;
		gbc_lblRgionDclaration.gridy = 19;
		contentPane.add(lblRgionDclaration, gbc_lblRgionDclaration);
		
		comboRegionDeclaration = new JComboBox<String>(new String[]{"Alaotra-Mangoro", "Amoron'i Mania", "Analamanga", "Analanjirofo", "Androy", "Anosy", "Atsimo-Andrefana", "Atsimo-Antsinanana", "Atsinanana", "Betsiboka", "Boeny", "Bongolava", "Diana", "Haute-Matsiatra", "Ihorombe", "Itasy", "Melaky", "Menabe", "Sava", "Sofia", "Vakinakaratra", "Vatovavy", "Fitovinany"});
		comboRegionDeclaration.setMaximumRowCount(23);
		GridBagConstraints gbc_comboRegionDeclaration = new GridBagConstraints();
		gbc_comboRegionDeclaration.insets = new Insets(0, 0, 5, 5);
		gbc_comboRegionDeclaration.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboRegionDeclaration.gridx = 2;
		gbc_comboRegionDeclaration.gridy = 19;
		contentPane.add(comboRegionDeclaration, gbc_comboRegionDeclaration);
		
		JLabel lblOfficierDtatCivil = new JLabel("Officier d'état civil:*");
		GridBagConstraints gbc_lblOfficierDtatCivil = new GridBagConstraints();
		gbc_lblOfficierDtatCivil.anchor = GridBagConstraints.EAST;
		gbc_lblOfficierDtatCivil.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfficierDtatCivil.gridx = 3;
		gbc_lblOfficierDtatCivil.gridy = 19;
		contentPane.add(lblOfficierDtatCivil, gbc_lblOfficierDtatCivil);
		
		txtOfficierEtatCivil = new JTextField();
		GridBagConstraints gbc_txtOfficierEtatCivil = new GridBagConstraints();
		gbc_txtOfficierEtatCivil.insets = new Insets(0, 0, 5, 5);
		gbc_txtOfficierEtatCivil.fill = GridBagConstraints.BOTH;
		gbc_txtOfficierEtatCivil.gridx = 4;
		gbc_txtOfficierEtatCivil.gridy = 19;
		contentPane.add(txtOfficierEtatCivil, gbc_txtOfficierEtatCivil);
		txtOfficierEtatCivil.setColumns(10);
		
		JLabel lblCommuneDclaration = new JLabel("Commune déclaration:*");
		GridBagConstraints gbc_lblCommuneDclaration = new GridBagConstraints();
		gbc_lblCommuneDclaration.anchor = GridBagConstraints.EAST;
		gbc_lblCommuneDclaration.insets = new Insets(0, 0, 5, 5);
		gbc_lblCommuneDclaration.gridx = 1;
		gbc_lblCommuneDclaration.gridy = 20;
		contentPane.add(lblCommuneDclaration, gbc_lblCommuneDclaration);
		
		txtCommuneDeclaration = new JTextField();
		GridBagConstraints gbc_txtCommuneDeclaration = new GridBagConstraints();
		gbc_txtCommuneDeclaration.insets = new Insets(0, 0, 5, 5);
		gbc_txtCommuneDeclaration.fill = GridBagConstraints.BOTH;
		gbc_txtCommuneDeclaration.gridx = 2;
		gbc_txtCommuneDeclaration.gridy = 20;
		contentPane.add(txtCommuneDeclaration, gbc_txtCommuneDeclaration);
		txtCommuneDeclaration.setColumns(10);
		
		JLabel lblDateDclaration = new JLabel("Date déclaration:*");
		GridBagConstraints gbc_lblDateDclaration = new GridBagConstraints();
		gbc_lblDateDclaration.anchor = GridBagConstraints.EAST;
		gbc_lblDateDclaration.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDclaration.gridx = 3;
		gbc_lblDateDclaration.gridy = 20;
		contentPane.add(lblDateDclaration, gbc_lblDateDclaration);
		
		txtDateDeclaration = new JTextField();
		GridBagConstraints gbc_txtDateDeclaration = new GridBagConstraints();
		gbc_txtDateDeclaration.insets = new Insets(0, 0, 5, 5);
		gbc_txtDateDeclaration.fill = GridBagConstraints.BOTH;
		gbc_txtDateDeclaration.gridx = 4;
		gbc_txtDateDeclaration.gridy = 20;
		contentPane.add(txtDateDeclaration, gbc_txtDateDeclaration);
		txtDateDeclaration.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 22;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAnnuler = new JButton("Annuler");
		panel.add(btnAnnuler);
		
		btnEnregistrerModifications = new JButton("Enregistrer les modifications");
		panel.add(btnEnregistrerModifications);
	}
	
	
	public void chargerActeDeces(ActeDeces acte) {
        if (acte == null) {
            JOptionPane.showMessageDialog(this, "Aucun acte à modifier ou données corrompues.", "Erreur", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        
        this.acteIdEnCoursDeModification = acte.getId();

        txtNomDefunt.setText(acte.getNomDefunt());
        comboSexeDefunt.setSelectedItem(acte.getSexeDefunt());
        
        SimpleDateFormat dateFormat = FormatDateHeure.getDateFormat();
        SimpleDateFormat timeFormat = FormatDateHeure.getHeureFormat();

        if (acte.getDateNaissanceDefunt() != null) txtDateNaissanceDefunt.setText(dateFormat.format(acte.getDateNaissanceDefunt())); else txtDateNaissanceDefunt.setText("");
        txtLieuNaissanceDefunt.setText(acte.getLieuNaissanceDefunt() != null ? acte.getLieuNaissanceDefunt() : "");
        txtVilleResidenceDefunt.setText(acte.getVilleResidenceDefunt() != null ? acte.getVilleResidenceDefunt() : "");
        txtFonctionDefunt.setText(acte.getFonctionDefunt() != null ? acte.getFonctionDefunt() : "");
        txtNomPereDefunt.setText(acte.getNomPereDefunt() != null ? acte.getNomPereDefunt() : "");
        chkPereDefuntVivant.setSelected(acte.isPereDefuntVivant());
        txtNomMereDefunt.setText(acte.getNomMereDefunt() != null ? acte.getNomMereDefunt() : "");
        chkMereDefuntVivante.setSelected(acte.isMereDefuntVivante());
        txtNomDeclarant.setText(acte.getNomDeclarant());
        txtFonctionDeclarant.setText(acte.getFonctionDeclarant() != null ? acte.getFonctionDeclarant() : "");
        txtVilleResidenceDeclarant.setText(acte.getVilleResidenceDeclarant() != null ? acte.getVilleResidenceDeclarant() : "");
        
        if (acte.getDateDeces() != null) txtDateDeces.setText(dateFormat.format(acte.getDateDeces())); else txtDateDeces.setText("");
        if (acte.getHeureDeces() != null) txtHeureDeces.setText(timeFormat.format(acte.getHeureDeces())); else txtHeureDeces.setText("");
        
        txtVilleDeces.setText(acte.getVilleDeces() != null ? acte.getVilleDeces() : "");
        txtLieuDeces.setText(acte.getLieuDeces() != null ? acte.getLieuDeces() : "");
        comboRegionDeclaration.setSelectedItem(acte.getRegionDeclaration());
        txtCommuneDeclaration.setText(acte.getCommuneDeclaration());
        txtOfficierEtatCivil.setText(acte.getOfficierEtatCivil());
        txtDateDeclaration.setText(dateFormat.format(acte.getDateDeclaration()));
    }
	
	
	public int getActeIdEnCoursDeModification() { return acteIdEnCoursDeModification; }
    public String getNomDefunt() { return txtNomDefunt.getText().trim(); }
    public String getSexeDefunt() { return (String) comboSexeDefunt.getSelectedItem(); }
    public String getDateNaissanceDefunt() { return txtDateNaissanceDefunt.getText().trim(); }
    public String getLieuNaissanceDefunt() { return txtLieuNaissanceDefunt.getText().trim(); }
    public String getVilleResidenceDefunt() { return txtVilleResidenceDefunt.getText().trim(); }
    public String getFonctionDefunt() { return txtFonctionDefunt.getText().trim(); }
    public String getNomPereDefunt() { return txtNomPereDefunt.getText().trim(); }
    public boolean isPereDefuntVivant() { return chkPereDefuntVivant.isSelected(); }
    public String getNomMereDefunt() { return txtNomMereDefunt.getText().trim(); }
    public boolean isMereDefuntVivante() { return chkMereDefuntVivante.isSelected(); }
    public String getNomDeclarant() { return txtNomDeclarant.getText().trim(); }
    public String getFonctionDeclarant() { return txtFonctionDeclarant.getText().trim(); }
    public String getVilleResidenceDeclarant() { return txtVilleResidenceDeclarant.getText().trim(); }
    public String getDateDeces() { return txtDateDeces.getText().trim(); }
    public String getHeureDeces() { return txtHeureDeces.getText().trim(); }
    public String getVilleDeces() { return txtVilleDeces.getText().trim(); }
    public String getLieuDeces() { return txtLieuDeces.getText().trim(); }
    public String getRegionDeclaration() { return (String) comboRegionDeclaration.getSelectedItem(); }
    public String getCommuneDeclaration() { return txtCommuneDeclaration.getText().trim(); }
    public String getOfficierEtatCivil() { return txtOfficierEtatCivil.getText().trim(); }
    public String getDateDeclaration() { return txtDateDeclaration.getText().trim(); }


    public void addEnregistrerModificationsListener(ActionListener listener) { btnEnregistrerModifications.addActionListener(listener); }
    public void addAnnulerListener(ActionListener listener) { btnAnnuler.addActionListener(listener); }
    
    
    public void afficherMessage(String message, String titre, int typeMessage) {
        JOptionPane.showMessageDialog(this, message, titre, typeMessage);
    }

}
