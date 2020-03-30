package graphe;

import java.util.Iterator;

public abstract class Graphe {

	protected int adj [][] = new int [50][50];
	protected int fs [] = new int[50];
	protected int aps [] = new int [50];
	
	
	public Graphe() {
		// TODO Auto-generated constructor stub
		
		//initialisation du Matrice d’adjacence
		for ( int i=1; i<= Sommet.nombreSommets(); i++) {
			for ( int j=1; j<= Sommet.nombreSommets(); j++) {
				adj[i][j]= 0;
			}
		}
		//matriceAdj();
		//FsAps();
	}
	
	public void matriceAdj(){
		        
	}
	
	public void FsAps() {
		
	}
	

}
