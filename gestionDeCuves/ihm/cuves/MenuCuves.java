package gestiondecuves.ihm.cuves;
import gestiondecuves.Controleur;

import javax.swing.*;
import java.io.File;

import java.awt.event.*;

public class MenuCuves extends JMenuBar implements ActionListener 
{
	private Controleur ctrl;

	private JMenuItem menuiFichierNouveau;
	private JMenuItem menuiFichierOuvrir;
	private JMenuItem menuiFichierExporter;
	private JMenuItem menuiFichierExporterSous;
	private JMenuItem menuiFichierQuitter;

	public MenuCuves(Controleur ctrl) 
	{
		this.ctrl = ctrl;
		
		// Création des composants
		JMenu menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic('F');

		this.menuiFichierNouveau = new JMenuItem("Nouveau");
		this.menuiFichierNouveau.setMnemonic('N');

		this.menuiFichierOuvrir = new JMenuItem("Ouvrir");
		this.menuiFichierOuvrir.setMnemonic('O');

		this.menuiFichierExporter = new JMenuItem("Exporter");
		this.menuiFichierExporter.setMnemonic('S');

		this.menuiFichierExporterSous = new JMenuItem("ExporterSous");
		this.menuiFichierExporterSous.setMnemonic('M');

		this.menuiFichierQuitter = new JMenuItem("Quitter");
		this.menuiFichierQuitter.setMnemonic('Q');

		// Positionnement des composants
		menuFichier.add(this.menuiFichierNouveau);
		menuFichier.add(this.menuiFichierOuvrir);
		menuFichier.addSeparator();
		menuFichier.add(this.menuiFichierExporter);
		menuFichier.add(this.menuiFichierExporterSous);
		menuFichier.addSeparator();
		menuFichier.add(this.menuiFichierQuitter);
		this.add(menuFichier);

		// Intégration des raccourcis
		this.menuiFichierNouveau.setAccelerator(
			KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)); // pour CTRL+N
		this.menuiFichierOuvrir.setAccelerator(
			KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK)); // pour CTRL+O
		this.menuiFichierExporter.setAccelerator(
			KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)); // pour CTRL+S
		this.menuiFichierExporterSous.setAccelerator(
			KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + 
			                                      InputEvent.SHIFT_DOWN_MASK )); // pour CTRL+SHIFT+S
		this.menuiFichierQuitter.setAccelerator(
			KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK)); // pour ALT+F4

		/*-------------------------------*/
		/* Activation des composants     */
		/*-------------------------------*/
		this.menuiFichierNouveau     .addActionListener(this);
		this.menuiFichierOuvrir      .addActionListener(this);
		this.menuiFichierExporter    .addActionListener(this);
		this.menuiFichierExporterSous.addActionListener(this);
		this.menuiFichierQuitter     .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem) 
		{
			if (e.getSource() == this.menuiFichierNouveau) 
				this.ctrl.nouveau();

			if (e.getSource() == this.menuiFichierOuvrir) 
			{
				JFileChooser chooser = new JFileChooser(".");

				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getPath() != null)
					this.ctrl.ouvrir(new File(chooser.getSelectedFile().getPath()));
			}

			if (e.getSource() == this.menuiFichierExporter) 
				this.ctrl.exporter();

			if (e.getSource() == this.menuiFichierExporterSous) 
				this.ctrl.exporterSous();

			if (e.getSource() == this.menuiFichierQuitter) 
				this.ctrl.frameDispose();
		}
	}
}