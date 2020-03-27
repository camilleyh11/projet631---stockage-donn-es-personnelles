public class Donnees { 
//Une donnée est représente par son identifiant "iDdonnee" et par sa taille "taille"
	
	
	private int IDdonnee, taille; //L'identifiant et la taille sont des entiers
	
	
	public Donnees(int idDonnee, int taille) { //Initialisation des attributs de la classe "Donnees"
		this.IDdonnee = idDonnee;
		this.taille = taille;
	}

	
	//GETTERS
	public int getIDdonnee() { //Methode pour obtenir l'identifiant de la donnee
		return this.IDdonnee;
	}

	
	public int getTaille() { //Methode pour obtenir la taille de la donnee
		return this.taille;
	}
	

	//SETTERS	
	public void setIDdonnee(int idDonnee) { //Methode pour modifier l'identifiant de la donnee
		this.IDdonnee = idDonnee;
	}


	public void setTaille(int taille) { //Methode pour modifier la taille d'une donnee
		this.taille = taille;
	}
	
	
}