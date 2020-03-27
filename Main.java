import java.util.ArrayList;
import org.jgrapht.graph.*;


public class Main {
	
	
	public static void main(String[] args) {
		
		
//////////question 2		
		
		
		Graphe<NoeudSysteme, Edge> g = new Graphe <>(Edge.class); //creation du graphe
		
		
		//creation des donnees
		Donnees donnee1 = new Donnees(1,40);
		Donnees donnee2 = new Donnees(2,25);
		Donnees donnee3 = new Donnees(3,25);

		
		ArrayList<Donnees> listeDonnees = new ArrayList<Donnees>(); //liste contenant les donnees		
		//ajout des donnees dans la liste
		listeDonnees.add(donnee1);
		listeDonnees.add(donnee2);
		listeDonnees.add(donnee3);
		
		ArrayList<Integer> listeIDdesDonnees = new ArrayList<Integer>(); //liste contenants les idenfiants des donnes
		//ajout des identifiants des donnees dans la liste
		listeIDdesDonnees.add(donnee1.getIDdonnee());
		listeIDdesDonnees.add(donnee2.getIDdonnee());
		listeIDdesDonnees.add(donnee3.getIDdonnee());

		
		g.ajouterDonnees(listeDonnees); //on ajoute la liste dans le graphe
		
		
		//creation des noeuds systemes
		NoeudSysteme noeudSysteme1 = new NoeudSysteme(1,50);
		NoeudSysteme noeudSysteme2 = new NoeudSysteme(2,40);
		NoeudSysteme noeudSysteme3 = new NoeudSysteme(3,40);
		
		
		//creation d'un utilisateur
		Utilisateurs utilisateur1 = new Utilisateurs(1,listeIDdesDonnees,1);
		
		
		//ajout des noeuds systemes dans le graphe
		g.ajouterNoeudGraphe(noeudSysteme1);
		g.ajouterNoeudGraphe(noeudSysteme2);
		g.ajouterNoeudGraphe(noeudSysteme3);
		
		
		g.addVertex(noeudSysteme1);
		g.addVertex(noeudSysteme2);
		g.addVertex(noeudSysteme3);
		g.addVertex(utilisateur1);
		
		
		//on relie les noeuds systemes entre eux
		noeudSysteme1.ajouterNoeudAccessible(noeudSysteme2);
		noeudSysteme2.ajouterNoeudAccessible(noeudSysteme1);
		noeudSysteme2.ajouterNoeudAccessible(noeudSysteme3);
		noeudSysteme3.ajouterNoeudAccessible(noeudSysteme2);
		
		
		DefaultWeightedEdge liaisonDesNoeuds12 = (DefaultWeightedEdge)g.addEdge(noeudSysteme1, noeudSysteme2);
		DefaultWeightedEdge liaisonDesNoeuds23 = (DefaultWeightedEdge)g.addEdge(noeudSysteme2, noeudSysteme3);
		DefaultWeightedEdge liaisonUtilEtNoeud1 = (DefaultWeightedEdge)g.addEdge(utilisateur1, noeudSysteme1);
		
		
		//on donne un poids aux arcs du graphe
		g.setEdgeWeight(liaisonDesNoeuds12, 1);
		g.setEdgeWeight(liaisonDesNoeuds23, 1);
		g.setEdgeWeight(liaisonUtilEtNoeud1, 2);
		

		System.out.println("Question 2 : Voici les ID des donnees à placer dans le graphe : " + utilisateur1.getListeIdDonnees());
		
		
		//ajout de l'utilisateur dans le graphe
		g.mettreDonneeDansGraphe(utilisateur1);		
		g.affichageNoeudGraphe();		
////////g.placerDonneesDescendant(noeudSysteme1, donnee3);
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
//////////question 3	
	
		
		Graphe<NoeudSysteme, Edge> g2 = new Graphe <>(Edge.class); //creation du graphe
        
        
		//creation d'une donnee
		Donnees donnee01 = new Donnees(1,40);

		
		ArrayList<Donnees> listeDonneesQ3 = new ArrayList<Donnees>(); //liste contenant les donnees		
		//ajout des donnees dans la liste
		listeDonneesQ3.add(donnee01);
		
		
		ArrayList<Integer> listeIDdesDonneeQ3 = new ArrayList<Integer>(); //liste contenant l'idenfiant de la donne
		//ajout de l'identifiant de la donnee dans la liste
		listeIDdesDonneeQ3.add(donnee01.getIDdonnee());
		
		
        g2.ajouterDonnees(listeDonneesQ3); //on ajoute la liste dans le graphe
		
        
        //creation des noeuds systemes
        NoeudSysteme noeudSysteme01 = new NoeudSysteme(1,50);
        NoeudSysteme noeudSysteme02 = new NoeudSysteme(2,40);
        NoeudSysteme noeudSysteme03 = new NoeudSysteme(3,40);
        NoeudSysteme noeudSysteme04 = new NoeudSysteme(4,40);
        
        
        //creation des utilisateurs
        Utilisateurs utilisateur01 = new Utilisateurs(1,listeIDdesDonneeQ3,1);
        Utilisateurs utilisateur02 = new Utilisateurs(2,listeIDdesDonneeQ3,4);
        
        
        //ajout des noeuds systemes dans le graphe
        g2.ajouterNoeudGraphe(noeudSysteme01);
        g2.ajouterNoeudGraphe(noeudSysteme02);
        g2.ajouterNoeudGraphe(noeudSysteme03);
        g2.ajouterNoeudGraphe(noeudSysteme04);
        
        
        g2.addVertex(noeudSysteme01);
        g2.addVertex(noeudSysteme02);
        g2.addVertex(noeudSysteme03);
        g2.addVertex(noeudSysteme04);
        g2.addVertex(utilisateur01);
        g2.addVertex(utilisateur02);
        
        
        //on relie les noeuds systemes entre eux
        noeudSysteme01.ajouterNoeudAccessible(noeudSysteme02);
        noeudSysteme02.ajouterNoeudAccessible(noeudSysteme01);
        noeudSysteme02.ajouterNoeudAccessible(noeudSysteme03);
        noeudSysteme03.ajouterNoeudAccessible(noeudSysteme02);
        noeudSysteme03.ajouterNoeudAccessible(noeudSysteme04);
        noeudSysteme04.ajouterNoeudAccessible(noeudSysteme03);
        
        
        DefaultWeightedEdge liaisonDesNoeuds012 = (DefaultWeightedEdge)g2.addEdge(noeudSysteme01, noeudSysteme02);
        DefaultWeightedEdge liaisonDesNoeuds023 = (DefaultWeightedEdge)g2.addEdge(noeudSysteme02, noeudSysteme03);
        DefaultWeightedEdge liaisonDesNoeuds034 = (DefaultWeightedEdge)g2.addEdge(noeudSysteme03, noeudSysteme04);
        DefaultWeightedEdge liaisonUtil1EtNoeud1 = (DefaultWeightedEdge)g2.addEdge(utilisateur01, noeudSysteme01);
        DefaultWeightedEdge liaisonUtil2EtNoeud4 = (DefaultWeightedEdge)g2.addEdge(utilisateur02, noeudSysteme04);

        
        //on donne un poids aux arcs du graphe
        g2.setEdgeWeight(liaisonDesNoeuds012, 1);
        g2.setEdgeWeight(liaisonDesNoeuds023, 1);
        g2.setEdgeWeight(liaisonDesNoeuds034, 1);
        g2.setEdgeWeight(liaisonUtil1EtNoeud1, 2);
        g2.setEdgeWeight(liaisonUtil2EtNoeud4, 2);

        
        System.out.println("Question 3 : Voici l'ID de la donnee à placer dans le graphe : " + utilisateur01.getListeIdDonnees());
        
        
       //ajout des utilisateurs dans le graphe
        g2.placerDonneePour2Utilisateurs(utilisateur01, utilisateur02, donnee01);
        g2.affichageNoeudGraphe();


    }
}

