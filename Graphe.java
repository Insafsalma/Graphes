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
	public void adj2fsaps()
	{
		int nbsom= adj[0][0];
		int nbarcs=adj[0][1];
		fs= new int[nbsom+nbarcs+1];
		fs[0]=nbsom+nbarcs;
		aps=new int [nbsom+1];
		aps[0]=nbsom;
		int k=1;
		for(int i=1;i<=nbsom;i++)
		{
			aps[i]=k;
			for(int j=1; j<=nbsom; j++)
			{
				if(adj[i][j]==1) 
				{
					fs[k]=j;
					k++;
				}
			}
			fs[k]=0;
			k++;
		}
	}

}
