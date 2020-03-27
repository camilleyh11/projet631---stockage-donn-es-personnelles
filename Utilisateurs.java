import java.util.ArrayList; //pour utiliser des listes


public class Utilisateurs {
	
	
	private int idUtilisateur; //identifiant de l'utilisateur
	private ArrayList<Integer> listeIdDonnees = new ArrayList<Integer>(); //liste contenant les id des donnees
	private int noeudAccessible; //l'unique noeud par lequel l'utilisateur est relié à l'aide d'un arc
	
	
	public Utilisateurs(int idUtil, ArrayList<Integer> listeIdDonnees, int noeudAccessible) {
	//initialisation des attributs de la classe "Utilisateurs"
		this.idUtilisateur = idUtil;
		this.listeIdDonnees = listeIdDonnees;
		this.noeudAccessible = noeudAccessible;
	}

	
	//GETTERS
	public int getidUtilisateur() { //methode pour obtenir l'identifiant de l'utilisateur
		return idUtilisateur;
	}


	public ArrayList<Integer> getListeIdDonnees() { //methode pour obtenir la liste des identifants des donnees
		return listeIdDonnees;
	}
	
	
	public int getNoeudAccessible() { //methode pour obtenir le noeud accessible par l'utilisateur
		return noeudAccessible;
	}

	
	//SETTERS
	public void setListeIdDonnees(ArrayList<Integer> listeIDdonnees) { //methode pour modifier la liste des id données
		this.listeIdDonnees = listeIDdonnees;
	}


	public void setNoeudAccessible(int noeudAccess) { //methode pour modifier le noeud accessible par l'utilisateur
		this.noeudAccessible = noeudAccess;
	}
}