package actedeces.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class FenetreApercuActe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane editorPane;
	private JButton btnFermer;
	private JButton btnExporterEnPdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreApercuActe frame = new FenetreApercuActe();
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
	public FenetreApercuActe() {
		setTitle("Aperçu acte de décès");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnExporterEnPdf = new JButton("Exporter en PDF");
		panel.add(btnExporterEnPdf);
		
		btnFermer = new JButton("Fermer");
		panel.add(btnFermer);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
	}
	
	public JEditorPane getEditorPane() {
		return editorPane;
	}
	
	public void addExporterListener(ActionListener listener) { btnExporterEnPdf.addActionListener(listener); }
    public void addFermerListener(ActionListener listener) { btnFermer.addActionListener(listener); }

}
