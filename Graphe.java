import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.alg.*;
import java.util.ArrayList;
import java.util.List;


public class Graphe<Noeud,Edge> extends SimpleWeightedGraph {
	
	
	private ArrayList<Donnees> listeDonneesAPlacer = new ArrayList<Donnees>(); //liste contenant les id des donnees a placer
	private ArrayList<NoeudSysteme> listeNoeud = new ArrayList<NoeudSysteme>(); //liste contenant les noeuds systemes

	
	public Graphe(Class arg0) {
		super(arg0);
	}
	
	
	public List cheminLePlusCourt(NoeudSysteme n1,NoeudSysteme n2) {
	//methode qui retourne la liste des noeuds qui sont liés avec le chemin le plus court possible
		DijkstraShortestPath<NoeudSysteme,Edge> dij = new DijkstraShortestPath<NoeudSysteme,Edge> (this, n1, n2);		
		//System.out.println(dij.findPathBetween(this, n1, n2)); affiche les id des noeuds liés
		return dij.findPathBetween(this, n1, n2); //retourne les id des noeuds liés
	}
	
	
	public void ajouterDonnees(ArrayList<Donnees> don) { //methode pour ajouter des donnees
		for(int i = 0;i<don.size();i++) { //parcourt la les donnees de la liste entre en parametre
			this.listeDonneesAPlacer.add(don.get(i)); //ajoute chaque donnee dans la liste contenant les donnees
		}
	}
	
	
	public void ajouterNoeudGraphe(NoeudSysteme noeud) { //methode qui permet d'ajouter des noeuds de type
		this.listeNoeud.add(noeud); 					 //noeud system a la liste "listeNoeud"
	}
	
	
	public NoeudSysteme getNoeudById(int idNoeud) { //methode qui retourne l'identifiant du noeud (sans l'adresse)
		for(int i = 0;i<this.listeNoeud.size();i++) { //parcourt la liste contenant les noeuds systemes
			if (this.listeNoeud.get(i).getIdNoeud() == idNoeud){ 
			//si un des id d'un noeud de la liste correspond à l'id du noeud considere en parametre
				return this.listeNoeud.get(i); //on retourne l'identifiant de ce noeud
			}
		}
		System.out.println("Le noeud n'est pas dans le graphe"); //si le noeud considere n'est pas dans la liste
		return null; //on retourne rien
	}
	
	
	public ArrayList<Donnees> getListeSauvegarde(ArrayList<Donnees> listeDeDonnees){ //permet de retourner une
		ArrayList<Donnees> listeSauvegarde = new ArrayList<Donnees>(); 				//"copie" d'une liste passee
		for(Donnees donnee : listeDeDonnees) { 										//en parametre
			listeSauvegarde.add(donnee);
		}
		return listeSauvegarde;
	}
	
	
	public void mettreDonneeDansGraphe(Utilisateurs utilisateur) {
		ArrayList<Donnees> listeDesDonnees = new ArrayList<Donnees>(); //liste des donnees a placer dans le graphe
		ArrayList<Donnees> listeSauvegarde = new ArrayList<Donnees>(); //liste "sauvegarde"
		listeDesDonnees = this.listeDonneesAPlacer;
		listeSauvegarde = this.getListeSauvegarde(listeDesDonnees); //on cree une copie de la liste contenant les noeuds			
		NoeudSysteme noeudAccessUtil = this.getNoeudById(utilisateur.getNoeudAccessible()); //recup l'id du noeud auquel l'utilisateur est lie
		for (int i = 0;i<listeDesDonnees.size();i++) { //parcourt des donnees a placer dans le graphe
			if (listeDesDonnees.get(i).getTaille() <= noeudAccessUtil.getCapacite()) {
			//si la taille de la donnee est <= a la taille du noeud					 
				noeudAccessUtil.ajouterDonnee(listeDesDonnees.get(i)); //ajout de l'id la donnee dans ce noeud			
				listeSauvegarde.remove(listeDesDonnees.get(i)); //retire l'id de la donnee de la liste
			}else { //sinon
				ArrayList<NoeudSysteme> listeNoeudsAccessibles = noeudAccessUtil.getNoeudAccessibles();
				//liste contenant les noeuds accessible depuis le noeud courant
				double minPoidsArc = 10; //on fixe une taille pour le poids d'un arc
				NoeudSysteme NoeudSystSuivant = null; //initialisation d'un noeud systeme
				for(NoeudSysteme n : listeNoeudsAccessibles) { //parcourt des noeuds pour trouver 
				//le chemin le plus court entre le noeud courant et les autres noeuds
					if (this.getEdgeWeight(this.getEdge(noeudAccessUtil,n))< minPoidsArc & n.getCapacite()>= listeDesDonnees.get(i).getTaille()) {
					//si le poids de l'arc entre le noeud courant et un noeud de la liste est < minPoidsArc
					//et si la capacite du noeud de la liste est suffisante
						minPoidsArc = this.getEdgeWeight(this.getEdge(noeudAccessUtil,n)); //minPoidsArc prend la valeur de cet arc
						NoeudSystSuivant = n; //NoeudSystSuivant prend la valeur du noeud de la liste
					}
				}
				if(NoeudSystSuivant != null) { //si le noeud systeme n'est pas nul
					NoeudSystSuivant.ajouterDonnee(listeDesDonnees.get(i)); //ajout de la donnee dans NoeudSystSuivant
					listeSauvegarde.remove(listeDesDonnees.get(i));	//on enleve la donnee de la liste de sauvegarde
				}else { //dans notre exemple pas utilise car noeud accessible par un seul noeud a chaque fois
				//permet dans le cas ou une donnee est trop grande et qu'on arrive "en fin de chemin" de pouvoir
				//remonter pour parcourir les autres neouds et placer la donnee
					this.placerDonneesDansNoeudSuivant(noeudAccessUtil, listeDesDonnees.get(i));
					listeSauvegarde.remove(listeDesDonnees.get(i));					
				
				}
			}
	}if(listeSauvegarde.size() > 0 ) { //si il reste un element dans listeSauvegarde
		System.out.println("Il n'y a actuellement pas assez de place pour placer ces donnes : " + listeSauvegarde);
	}
}
	
//
	public ArrayList<NoeudSysteme> getNoeudAccessibleDuNoeudSuivant(NoeudSysteme n) {
	//methode pour obtenir les noeuds accessibles des noeuds accessibles
		ArrayList<NoeudSysteme> listeNoeuds = new ArrayList<NoeudSysteme>(); //liste contenant des noeuds systemes
		for (NoeudSysteme noeud1 : n.getNoeudAccessibles()) { //parcours les noeuds accessibles
			NoeudSysteme noeud2 = noeud1;
			for(NoeudSysteme noeud3 : noeud2.getNoeudAccessibles()) { 
			//parcourt les noeuds accessibles des noeuds accessibles
				listeNoeuds.add(noeud3);
			}
		}
		return listeNoeuds;
	}
	
	
	public void placerDonneesDansNoeudSuivant(NoeudSysteme n,Donnees don) {
	//methode pour placer la donnee dans le noeud suivant
		ArrayList<NoeudSysteme> listeNoeudsAcess = this.getNoeudAccessibleDuNoeudSuivant(n); 
		//liste contenant le noeud accessbile du noeud accessible
		for(NoeudSysteme noeud : listeNoeudsAcess) { //parcourt de la liste "listeNoeudsAcess"
			if(noeud.getCapacite() >= don.getTaille()) { //si la capacité du noeud est suffisante 
				noeud.ajouterDonnee(don); //on ajoute la donnee dans ce noeud
			}
		}
	}
	
	
	public void placerDonneePour2Utilisateurs(Utilisateurs util1,Utilisateurs util2,Donnees donnee) {
	//Recherche du chemin le plus court entre les deux noeuds accessibles des utilisateurs
		ArrayList<Edge> listeID2Util = (ArrayList) this.cheminLePlusCourt(this.getNoeudById(util1.getNoeudAccessible()),this.getNoeudById(util2.getNoeudAccessible()));
		//ajout dans la liste des ID des noeuds accessibles par les 2 utilisateurs
			int position = listeID2Util.size()/2; //division entiere = 1, on prend le 1er element de la liste
			//si il y a un nombre pair d'arc entre les noeuds, recuperation du noeud au milieu
			String noeud = listeID2Util.get(position).toString().charAt(1)+"";
			//on prend le 1er ID
			int idNoeud = Integer.parseInt(noeud);
			if(this.getNoeudById(idNoeud).getCapacite() >= donnee.getTaille()) {
				this.getNoeudById(idNoeud).ajouterDonnee(donnee);
			}else {
				noeud = listeID2Util.get(position).toString().charAt(5)+"";
				//on prend le 2e ID
				idNoeud = Integer.parseInt(noeud);
				if(this.getNoeudById(idNoeud).getCapacite() >= donnee.getTaille()) {
					this.getNoeudById(idNoeud).ajouterDonnee(donnee);
				}
			}
	}
	
	
	public void affichageNoeudGraphe() {
		for(NoeudSysteme n : this.listeNoeud) {
			System.out.println("Le noeud " + n.getIdNoeud() + " a une capacite de : "+n.getCapacite() + ". Les donnees placees à l'interieur sont : "+n.getListeDonneesStockees());
		}
	}
	

}
