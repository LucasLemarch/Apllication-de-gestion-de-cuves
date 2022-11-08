package gestiondecuves.ihm.cuves;
import gestiondecuves.Controleur;

import javax.swing.*;
import java.awt.event.*;

public class PanelBarre extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton  		  btnPlay;
	private boolean           bPause;
	private JButton  		  btnSuivant;

	public PanelBarre(Controleur ctrl)
	{
		this.ctrl   = ctrl;
		this.bPause = true;

		this.setOpaque(false);

		// Création des composants
		this.btnPlay    = new JButton(new ImageIcon("images/play.png" ));
		this.btnSuivant = new JButton(new ImageIcon("images/suivant.png" ));

		// Positionnement des composants
		this.add(this.btnPlay   );
		this.add(this.btnSuivant);

		// Activation des composants
		this.btnPlay   .addActionListener(this);
		this.btnSuivant.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
        if(e.getSource() == this.btnPlay )
		{
			this.bPause = !this.bPause;

			this.ctrl.pause(this.bPause);

			if (this.bPause)
				this.btnPlay.setIcon(new ImageIcon("images/play.png"));
			else
				this.btnPlay.setIcon(new ImageIcon("images/pause.png"));
		}

        if(e.getSource() == this.btnSuivant)
        {
            this.ctrl.transvaser();
        }
	}
}