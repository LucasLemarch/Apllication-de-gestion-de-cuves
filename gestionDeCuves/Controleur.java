package gestiondecuves;
import gestiondecuves.metier.Structure;
import gestiondecuves.ihm.cuves.AutoPlay;
import gestiondecuves.ihm.cuves.FrameCuves;
import gestiondecuves.ihm.nouveau.FrameNouveau;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Controleur 
{
	private FrameCuves ihm;
	private Structure  metier;
	private boolean    pause;
	private Timer      timer;

	public Controleur() 
	{
		this.metier = new Structure(0);
		this.ihm    = new FrameCuves(this);
		this.pause  = false;
		this.timer  = null;
	}


	// Méthodes pour la création de structure

	public void creerMetier(int nbCuves) 
	{
		this.metier = new Structure(nbCuves);
	}

	public void creerCuve(int indice, int capacite)
	{
		this.metier.creerCuve(indice, capacite);
	}

	public boolean ajouterRelation(char c1, char c2, int section)
	{
		return this.metier.ajouterRelation(c1, c2, section);
	}

	public void genererTxt(String typeStructure, String nomFichier)
	{
		this.metier.genererTxt(typeStructure, nomFichier);
	}


	// méthodes pour l'affichage de la structure

	public void nouveau() 
	{
		this.ihm.setEnabled(false);
		new FrameNouveau(this);		
	}

	public void finNouveau()
	{
		this.ihm.setEnabled(true);
		this.ihm.setState(this.ihm.NORMAL);
	}

	public void ouvrir(File fichier) 
	{
		this.metier = new Structure(fichier);
		this.metier.optimiserPositions();

		this.ihm.dispose();
		this.ihm = new FrameCuves(this);
	}

	public void exporter    () { this.ihm.exporter    (); }
	public void exporterSous() { this.ihm.exporterSous(); }
	public void majIHM      () { this.ihm.majIHM      (); }
	public void frameDispose() { this.ihm.dispose     (); }

	public void transvaser()
	{
		this.metier.transvaser();
		this.ihm   .majIHM();
	}

	public void pause(boolean bPause) 
    {   
        if (this.pause)
        {
            this.pause=false;
            this.timer.cancel();
        }
        else
        {
            this.pause=true;
        }
        this.timer = new Timer();
        TimerTask task = new AutoPlay(this);   
        this.timer.schedule(task, 0, 1000);
	}

    public void playAction()
    {
        if(pause)
        {
            if(! this.metier.transvaser())
                pause = false;

            this.ihm.majIHM();
        }
    }

	// Accesseurs
	public int    getNbCuves ()               { return this.metier.getNbCuves ();       }
	public int    getRelation(int c1, int c2) { return this.metier.getRelation(c1, c2); }
	public char   getIdCuve  (int indice)     { return this.metier.getIdCuve  (indice); }
	public double getContenu (int indice)     { return this.metier.getContenu (indice); }
	public int    getCapacite(int indice)     { return this.metier.getCapacite(indice); }
	public int    getPosX    (int indice)     { return this.metier.getPosX    (indice); }
	public int    getPosY    (int indice)     { return this.metier.getPosY    (indice); }
	public String getPosInfos(int indice)     { return this.metier.getPosInfos(indice); }

	// Modificateurs
	public void setPosition(int indice, int posX, int posY) 
	{
		this.metier.setPosition(indice, posX, posY);
	}

	public void setPositionInfos(int indice, String position) 
	{
		this.metier.setPositionInfos(indice, position);
	}

	public boolean setContenu(int indice, double quantite) 
	{
		return this.metier.setContenu(indice, quantite);
	}

	// Main permettant de lancer l'application
	public static void main(String[] args) { new Controleur(); }
}