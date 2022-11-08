package gestiondecuves.metier;

public class Cuve 
{
	private static char idCuveAutoIncre = 'A';

	private char   idCuve;
	private int    capacite;
	private double contenu;
	private int[]  positionsXY;
	private String positionInfos;

	// Constructeur d'une Cuve
	private Cuve(int capacite) 
	{
		this.idCuve        = Cuve.idCuveAutoIncre++;
		this.capacite      = capacite;
		this.contenu       = 0.0;
		this.positionsXY   = new int[2]; // les valeurs du tableau sont automatiquement égal à 0
		this.positionInfos = "Haut";
	}

	// Fabrique d'une Cuve
	public static Cuve fabriquerCuve(int capacite) 
	{
		// On y vérifie la validité de la capacité renseignée et on vérifie si nous n'avons pas 
		// déjà 26 cuves
		if (capacite < 200 || capacite > 1000 || Cuve.idCuveAutoIncre > 'Z')
			return null;

		return new Cuve(capacite);
	}


	//     Accesseurs renvoyant

	// le caractère correspondant à la Cuve
	public char   getIdCuve  () { return this.idCuve;         }

	// la capacité maximale de la Cuve
	public int    getCapacite() { return this.capacite;       }

	// le nombre de liquide(en litres) contenu dans la Cuve
	public double getContenu () { return this.contenu;        }

	// la position sur l'axe des abscisses de la Cuve
	public int    getPosX    () { return this.positionsXY[0]; }

	// la position sur l'axe des ordonnées de la Cuve
	public int    getPosY    () { return this.positionsXY[1]; }

	// les informations de la position de la Cuve sous forme de String
	public String getPosInfos() { return this.positionInfos;  }


	//     Modificateurs

	// Permet de remplir la Cuve avec le nombre de litres de liquide renseigné 
	public boolean setContenu(double contenu)
	{
		// Vérification que le contenu ne dépasse pas la quantité maximale de la Cuve
		if (contenu > this.capacite) return false;

		// Vérification que le contenu renseigné n'est pas négatif
		if (contenu < 0)             return false;

		this.contenu = contenu;
		return true;
	}

	// Permet de situer la Cuve en X et Y
	public void setPosX(int posX) { this.positionsXY[0] = posX; }
	public void setPosY(int posY) { this.positionsXY[1] = posY; }

	// Permet de modifier la String contenant la position de la Cuve
	public boolean setPosInfos(String position) 
	{
		String[] tabPos = { "Haut", "Bas", "Gauche", "Droite" };

		for (String s : tabPos)
			if (position.equals(s)) 
			{
				this.positionInfos = position;
				return true;
			}

		return false;
	}


	// Methodes

	// Permet de d'ajouter ou enlever du contenu à la Cuve
	public void modifierContenu(double quantite)
	{
		// Si le contenu actuel additionné à la quantité de l'utlisateur dépasse la quantité
		// maximale de la Cuve, nous mettons le contenu à la capacité maximale de la Cuve
		if (this.contenu + quantite > this.capacite)
			this.contenu = (double) (this.capacite);

		// Vérification que le contenu de la Cuve ne peut être négatif
		else if (this.contenu + quantite < 0)
			this.contenu = 0.0;

		else
			this.contenu += quantite;		
	}

	// Permet de réinitialiser l'attribution des identifiants
	public static void reinitialiserId() 
	{
		Cuve.idCuveAutoIncre = 'A';
	}
}
