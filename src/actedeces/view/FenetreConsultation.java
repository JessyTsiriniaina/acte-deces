package actedeces.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import actedeces.model.ActeDeces;
import actedeces.utils.FormatDateHeure;

public class FenetreConsultation extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int INDEX_COLONNE_ID = 0;
	private final int AUCUN_ACTE_SELECTIONNE = -1;
	private JPanel contentPane;
	private JTable table;
	private JTextField champDeRecherche;
	private JButton btnAjouterActe;
	private JButton btnModifierActeSelection;
	private JButton btnSupprimerActeSelection;
	private JButton btnAperuActeSelection;
	private JButton btnStatistique;
	
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreConsultation frame = new FenetreConsultation();
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
	public FenetreConsultation() {
		setTitle("Consultation des actes de décès");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAjouterActe = new JButton("Ajouter acte");
		panel.add(btnAjouterActe);
		
		btnModifierActeSelection = new JButton("Modifier acte selectionné");
		panel.add(btnModifierActeSelection);
		
		btnSupprimerActeSelection = new JButton("Supprimer acte selectionné");
		panel.add(btnSupprimerActeSelection);
		
		btnAperuActeSelection = new JButton("Aperçu acte selectionné");
		panel.add(btnAperuActeSelection);
		
		btnStatistique = new JButton("Statistique");
		panel.add(btnStatistique);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		tableModel = new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"ID", "Nom d\u00E9funt", "Date de d\u00E9claration"
							}
						) {
							private static final long serialVersionUID = 1L;
							boolean[] columnEditables = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};
						
		table.setModel(tableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel_1.setLayout(fl_panel_1);
		
		JLabel lblRechercher = new JLabel("Rechercher: ");
		panel_1.add(lblRechercher);
		
		champDeRecherche = new JTextField();
		panel_1.add(champDeRecherche);
		champDeRecherche.setColumns(50);
	}
	
	
	public void afficherActes(List<ActeDeces> actes) {
        tableModel.setRowCount(0);
        
        if (actes != null) {
        	SimpleDateFormat dateFormat = FormatDateHeure.getDateFormat();
            for (ActeDeces acte : actes) {
                tableModel.addRow(new Object[]{
                        acte.getId(),
                        acte.getNomDefunt(),
                        dateFormat.format(acte.getDateDeclaration())
                }); 
            }
        }
    }
	
	
	public void afficherActes(List<ActeDeces> actes, String motCle) {
        if (actes != null) {
        	SimpleDateFormat dateFormat = FormatDateHeure.getDateFormat();
        	tableModel.setRowCount(0);
            for (ActeDeces acte : actes) {
            	if(acte.getNomDefunt().contains(motCle))
	                tableModel.addRow(new Object[]{
	                        acte.getId(),
	                        acte.getNomDefunt(),
	                        dateFormat.format(acte.getDateDeclaration())
	                });
            }
        }
    }
	
	
	public int getSelectedActeId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            return (int) tableModel.getValueAt(selectedRow, INDEX_COLONNE_ID); 
        }
        return AUCUN_ACTE_SELECTIONNE; 
    }
	
	
	public void addAjouterListener(ActionListener listener) { btnAjouterActe.addActionListener(listener); }
    public void addModifierListener(ActionListener listener) { btnModifierActeSelection.addActionListener(listener); }
    public void addSupprimerListener(ActionListener listener) { btnSupprimerActeSelection.addActionListener(listener); }
    public void addApercuListener(ActionListener listener) { btnAperuActeSelection.addActionListener(listener); }
    public void addStatistiquesListener(ActionListener listener) { btnStatistique.addActionListener(listener); }
    public void addMotCleChangeListener(DocumentListener listener) { champDeRecherche.getDocument().addDocumentListener(listener); }
    
    public void afficherMessage(String message, String titre, int typeMessage) {
        JOptionPane.showMessageDialog(this, message, titre, typeMessage);
    }
    
    public String getMotCle() { return champDeRecherche.getText().trim(); }


}
