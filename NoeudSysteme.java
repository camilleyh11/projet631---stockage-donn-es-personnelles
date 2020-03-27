import java.util.ArrayList; //pour utiliser des listes


public class NoeudSysteme implements Noeud { // importation de l'interface "Noeud"
	
	
	private int idNoeudSysteme, capacite; //identifiant des noeuds contenant des valeurs + capacite max du noeud systeme
	private ArrayList<Integer> listeDonneesStockees = new ArrayList<Integer>(); 
	//liste contenant les id des donnees stockees dans un noeud systeme
	private ArrayList<NoeudSysteme> listeNoeudsAccessibles = new ArrayList<NoeudSysteme>(); 
	//liste contenant les noeuds auxquels les utilisateurs ont accès
	

	public NoeudSysteme(int idNoeudSyst, int capacite) { //initialisation des attributs de la classe "NoeudSysteme"
		this.idNoeudSysteme = idNoeudSyst;
		this.capacite = capacite;
	}
	
	
	//GETTERS
	public int getIdNoeud() { //methode pour obtenir l'identifiant d'un noeud
		return this.idNoeudSysteme;
	}
	
	
	public ArrayList<Integer> getListeDonneesStockees() { //methode pour obtenir la liste contenant les donnees du noeud systeme
		return listeDonneesStockees;
	}
	
	
	public int getCapacite() { //methode pour obtenir la capacite du noeud systeme
		return this.capacite;
	}
	
	
	public ArrayList<NoeudSysteme> getNoeudAccessibles() { //methode pour obtenir a partir de la liste contenant
		return listeNoeudsAccessibles; 						   //les noeuds systems le noeud system correspondant
	}
	
	
	//SETTERS
	public void setListeDonneesStockees(ArrayList<Integer> listeDonneesStockees) { //methode pour modifier la liste
		this.listeDonneesStockees = listeDonneesStockees; // contenant les donnees stockees dans un noeud systeme
	}

	
	//ADD	
	public void ajouterNoeudAccessible(NoeudSysteme noeud) { //methode pour ajouter un noeud systeme dans la liste
		this.listeNoeudsAccessibles.add(noeud); 					 //contenant les auxquels l'utilisateurs ont accès
	}
	
	
	public void ajouterDonnee(Donnees donnee) { //methode pour ajouter une donnee dans la liste contenant les donnees
		this.listeDonneesStockees.add(donnee.getIDdonnee()); //ajout de l'id de la donnee dans la liste contenant les id des donnees
		this.capacite = this.capacite - donnee.getTaille() ; //on enleve de la capacite totale la taille de la donnee
	}
	
	
	//TOSTRING
	public String toString() {
		return this.idNoeudSysteme+"";
	}
}