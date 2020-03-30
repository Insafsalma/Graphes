package graphe;

import java.util.Iterator;

public class Graphe_non_oriente extends Graphe {

	public Graphe_non_oriente() {
		// TODO Auto-generated constructor stub
		super();
		matriceAdj();
		FsAps();
	}
	
	@Override
	public void matriceAdj(){
		Iterator iter = Arete.iterator();
		
        while (iter.hasNext()) {
        	Arete a = (Arete) iter.next();
        	super.adj[a.getOrig().getEtiquette()][a.getExtr().getEtiquette()] = a.getPoids();
        	super.adj[a.getExtr().getEtiquette()][a.getOrig().getEtiquette()] = a.getPoids();
        }
	}
	
	@Override
	public void FsAps() {
		
	}

}
