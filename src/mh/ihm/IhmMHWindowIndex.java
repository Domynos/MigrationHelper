package mh.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import mh.tools.Ca7Parser;

public class IhmMHWindowIndex extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String project_name;
	String project_contactDSA;
	String project_description;
	String project_structPath;
	
	private void build(){
		setTitle("Migration Helper"); //On donne un titre à l'application
		setResizable(false); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setIconImage(new ImageIcon("cuteftp_icon_png.png").getImage());
		setContentPane(startPanel());
		pack();
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
	}
	
	private void submit()
	{	
		this.dispose();
		new Ca7Parser(project_structPath,project_name,project_contactDSA,project_description);
	}
	
	public JPanel startPanel()
	{
		JPanel start_panel = new JPanel();
		start_panel.setLayout(new GridBagLayout());
		
		JLabel lbl_nameProject = new JLabel("Nom du projet :");
		JLabel lbl_contactDSA = new JLabel("Contact DSA :");
		JLabel lbl_description = new JLabel("Description du besoin :");
		JLabel lbl_title = new JLabel("Migration Helper");
		lbl_title.setFont(new Font("Serif", Font.PLAIN, 24));
		JLabel lbl_structFile = new JLabel("Fichier structure :");
		JLabel lbl_output = new JLabel("Destination :");
		
		final JFileChooser chooser = new JFileChooser();//création dun nouveau filechosser
		chooser.setApproveButtonText("Choisir"); //intitulé du bouton
		
		final JTextArea jta_description = new JTextArea();
		jta_description.setLineWrap(true);
		final JTextField jtf_contactDSA = new JTextField();
		final JTextField jtf_nameProject = new JTextField();
		final JTextField jtf_structPath = new JTextField();
		
		jtf_nameProject.setPreferredSize( new Dimension( 200, 20 ) );
		Border blackBorder = BorderFactory.createLineBorder(Color.black);
		jta_description.setBorder(blackBorder);
		jta_description.setPreferredSize( new Dimension( 200, 200 ) );
		jtf_contactDSA.setPreferredSize( new Dimension( 200, 20 ) );
		
		JButton btn_help = new JButton("Aide");
		btn_help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				StringBuffer sb = new StringBuffer();
				sb.append("Aide\n");
				sb.append("- Nom du projet : nom de l'application par défault\n");
				sb.append("- Contact DSA : personne à contacter en cas de besoin\n");
				sb.append("- Fichier structure : fichier texte de l'extraction de la structure de votre chaine, exportable via \"Demande de structure\" dans IDEAL \n");
				sb.append("- Description du besoin : expliquer en français l'usage fonctionnel de la chaine");
				JOptionPane.showMessageDialog(null, sb.toString(), "Aide", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btn_parcourir = new JButton("Parcourir");
		btn_parcourir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					jtf_structPath.setText(chooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});
		
		JButton btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				project_name = jtf_nameProject.getText();
				project_contactDSA = jtf_contactDSA.getText();
				project_description = jta_description.getText();
				project_structPath = jtf_structPath.getText();
				submit();	
				
			}
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
		
		gbc.gridwidth = GridBagConstraints.REMAINDER; // seul composant de sa colonne, il est donc le dernier.
		gbc.gridheight = 2;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 15, 10, 15); // Marges
		gbc.fill = GridBagConstraints.CENTER;
		
		
		start_panel.add(lbl_title,gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		start_panel.add(lbl_nameProject,gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		start_panel.add(jtf_nameProject,gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		start_panel.add(lbl_contactDSA,gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		start_panel.add(jtf_contactDSA,gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		
		start_panel.add(lbl_structFile,gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		
		start_panel.add(jtf_structPath,gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		start_panel.add(btn_parcourir,gbc);
		
		gbc.gridy = 7;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		
		start_panel.add(lbl_description,gbc);
		
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		
		start_panel.add(jta_description,gbc);
		
		gbc.gridy = 9;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		start_panel.add(btn_help,gbc);
		
		gbc.gridy = 9;
		gbc.gridx = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		start_panel.add(btn_ok,gbc);
		
		return start_panel;
	}
	
	public IhmMHWindowIndex(){
		// TODO Auto-generated method stub
		super();
		build();
	}

}
