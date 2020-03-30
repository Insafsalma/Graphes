package graphe;

import java.util.*;

public class Sommet {
    private int x, y;
    private int etiquette;

    public Sommet(int etiquette, int x, int y) {
        this.etiquette = etiquette;
        setPosition(x, y);
        tousLesSommets.put(etiquette, this);
    }

    public Sommet(int x, int y) {
        this(tousLesSommets.size()+1, x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(int etiquette) {
        tousLesSommets.remove(this.etiquette);
        this.etiquette = etiquette;
        tousLesSommets.put(etiquette, this);
    }

    public String toString() {
        return "\"" + etiquette + "\" " + x + " " + y;
    }

    public boolean equals(Object o) {
        return o instanceof Sommet && 
                x == ((Sommet) o).x && y == ((Sommet) o).y;
    }

    /* Membres de classe */
    private static Map<Integer , Sommet> tousLesSommets = new HashMap<Integer, Sommet>();

    public static int nombreSommets() {
        return tousLesSommets.size();
    }
    
    public static Sommet trouverSommet(int etiquette) {
        return tousLesSommets.get(etiquette);
    }
    
    public static Iterator<Sommet> iterator() {
        return tousLesSommets.values().iterator();
    }
    
    public static void clearSommets() {
    	tousLesSommets.clear();
    }
}
