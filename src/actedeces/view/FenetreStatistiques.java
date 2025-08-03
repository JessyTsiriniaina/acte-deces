package actedeces.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;


public class FenetreStatistiques extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboTypeStatistique;
	private JPanel panelGraphique;
	private JButton btnActualiser;
	private JButton btnFermer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreStatistiques frame = new FenetreStatistiques();
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
	public FenetreStatistiques() {
		setTitle("Statistiques");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		setMinimumSize(new Dimension(1200, 650));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblTypeDeStatistique = new JLabel("Type de statistique: ");
		panel.add(lblTypeDeStatistique);
		
		comboTypeStatistique = new JComboBox<String>(new String[] {"Par sexe", "Par classe d'âge"});
		comboTypeStatistique.setModel(new DefaultComboBoxModel<String>(new String[] {"Par classe d'âge", "Par sexe"}));
		comboTypeStatistique.setPreferredSize(new Dimension(400, 24));
		comboTypeStatistique.setMinimumSize(new Dimension(100, 24));
		panel.add(comboTypeStatistique);
		
		btnActualiser = new JButton("Actualiser");
		panel.add(btnActualiser);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnFermer = new JButton("Fermer");
		panel_1.add(btnFermer);
		
		panelGraphique = new JPanel();
		contentPane.add(panelGraphique, BorderLayout.CENTER);
	}
	
	
	public void afficherGraphique(JPanel chartPanel) { 
        panelGraphique.removeAll();
        
        if (chartPanel != null) {
            panelGraphique.add(chartPanel, BorderLayout.CENTER); 
        } else {
            panelGraphique.add(new JLabel("Aucune donnée statistique à afficher ou erreur de génération."), BorderLayout.CENTER);
        }
        panelGraphique.revalidate();
        panelGraphique.repaint();
    }
	
	
	public String getSelectedStatistiqueType() {
        return (String) comboTypeStatistique.getSelectedItem();
    }

    
    public void addActualiserListener(ActionListener listener) {
        btnActualiser.addActionListener(listener);
    }
    
    public void addFermerListener(ActionListener listener) {
        btnFermer.addActionListener(listener);
    }
    
    
    public void afficherMessage(String message, String titre, int typeMessage) {
        JOptionPane.showMessageDialog(this, message, titre, typeMessage);
    }

}
