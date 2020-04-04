package graphe;

import java.util.*;

public class Arete {
    private Sommet orig, extr;
    private int poids;
    private static int poids_defaut=1;

    Arete(Sommet orig, Sommet extr) {
        this.orig = orig;
        this.extr = extr;
        this.poids= poids_defaut;
        toutesLesAretes.add(this);
    }

    public int getPoids() {
		return poids;
	}
    
    public String getPoidstoString() {
    	if (Step10.isOriente()&&Step10.isValue())
		return this.orig.getEtiquette()+">>"+poids+">>"+this.extr.getEtiquette();
    	else if (!Step10.isOriente()&& Step10.isValue()) return poids+"";
    	else if (!Step10.isOriente()&& !Step10.isValue())  return ">>";
    	else return"";
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public static int getPoids_defaut() {
		return poids_defaut;
	}

	public static void setPoids_defaut(int poids_defaut) {
		Arete.poids_defaut = poids_defaut;
	}

	public Sommet getOrig() {
        return orig;
    }

    public Sommet getExtr() {
        return extr;
    }

    public String toString() {
        return "\"" + orig.getEtiquette() + "\" \"" + extr.getEtiquette()
                + "\"";
    }
    
    /* Membres de classe */
    private static Collection<Arete> toutesLesAretes = new Vector<Arete>();

    public static int nombreAretes() {
        return toutesLesAretes.size();
    }

    public static Iterator iterator() {
        return toutesLesAretes.iterator();
    }
    
    public static void clearAretes() {
    	toutesLesAretes.clear();
    }
}