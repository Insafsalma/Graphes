package graphe;

import java.util.Iterator;

public class Graphe {

	protected int adj [][] = new int [Sommet.nombreSommets()+1][Sommet.nombreSommets()+1];
	protected int fs [] = new int[Sommet.nombreSommets()+Arete.nombreAretes()+1];
	protected int aps [] = new int [Sommet.nombreSommets() +1];
	protected Arete aretes[];
	
	private int[] prem =new int [Sommet.nombreSommets() +1];
	private int[] pilch=new int [Sommet.nombreSommets() +1];
	private int[] cfc=new int [Sommet.nombreSommets() +1];
	private int[] num=new int [Sommet.nombreSommets() +1];
	private int[] mu=new int [Sommet.nombreSommets() +1];
	private int[] tarj = new int [Sommet.nombreSommets() +1];
	private boolean[] entarj =new boolean [Sommet.nombreSommets() +1];
	private int p;
	private int[] prufer;
	private int[] rang;
	private int[] dist;
	private int fp [];
	private int app [];
	private int ddi[];
	
	
	public Graphe() {
		// TODO Auto-generated constructor stub
		
		//initialisation du Matrice d’adjacence
		for ( int i=1; i<= Sommet.nombreSommets(); i++) {
			for ( int j=1; j<= Sommet.nombreSommets(); j++) {
				adj[i][j]= 0;
			}
		}
		adj[0][0]= Sommet.nombreSommets();
		adj[0][1] = Arete.nombreAretes();
		//matriceAdj();
		//FsAps();
		Iterator iter = Arete.iterator();
               while (iter.hasNext()) {
        	Arete a = (Arete) iter.next();
        	for(int i=1;i<=adj[0][1];i++)
        	{
        		aretes[i]=a;
        	}
                }
	}
	
	public void matriceAdj() {
		
	}
	
	
	public void FsAps() {
		matriceAdj();	
		int nbsom= adj[0][0];
		int nbarcs = adj[0][1];
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
	public void calcul_ddi()
	{
		int n = aps[0];
		ddi= new int[n+1];
		ddi[0]=n;
		for(int i=1; i<=n; i++)
		{
			ddi[i]=0;
		}
		for(int i=1; i<= fs[0];i++)
		{
			if(fs[i]!=0)
			{
				ddi[fs[i]]++;
			}
		}
	}
	public void calcul_app()
	{
		int n = ddi[0];
		app= new int[n+1];
		app[0]=n;
		app[1]=1;
		for(int i=1; i<n; i++)
		{
			app[i+1] =app[i]+ddi[i]+1;
		}
	}
	public void calcul_fp()
	{
		int m= fs[0];
		fp= new int[m+1];
		int s=1;
		for (int k=1; k<=m; k++)
		{
			if(fs[k]==0)
			{
				s++;
			}else {
				fp[app[fs[k]]]=s;
				app[fs[k]]++;
			}
		}
		for(int i=m; i>1; i--)
		{
			fp[app[i]]=0;
			app[i]=app[i-1]+1;
		}
		fp[app[1]]=0;
		app[1]=1;
	}
	
		public void empiler(int s,  int[] pilch)
		{
			int x= pilch[0];
			pilch[0]=s;
			pilch[s]=x;
		}
		public int depiler(int[] pilch)
		{
			int x= pilch[0];
			pilch[0]=pilch[x];
			return x;
		}
		public void traverse(int s)
		{
			num[s]=p++;
			mu[s]= num[s];
			empiler(s,tarj);
			int t;
			for(int k= aps[s];(t=fs[k])!=0; k++)
			{
				if(num[t]==0)
				{
					traverse(t);
					if(mu[t]<mu[s])
					{
						mu[s]=mu[t];
					}
				}else {
					if((entarj[t] == false) && (num[t]<num[s]))
					{
						if(num[t]<mu[s])
						{
							mu[s]=num[s];
						}
					}
				}
			}
		}
		
		public void tarjan()
        {
            int x=0,ind,nbr=0;
            int n = aps[0];
            num[0]= Sommet.nombreSommets();
            mu[0]=Sommet.nombreSommets();
            cfc[0]= Sommet.nombreSommets();
            for(int i=1; i<=Sommet.nombreSommets();i++)
            {
                entarj[i]=false;
                num[i]=0;
            }
            for(int s=1; s<=n; s++)
            {
                if(num[s] == 0)
                {
                    traverse(s);
                }
                if(mu[s]==num[s])
                {
                    nbr++;
                    x= depiler(tarj);
                    prem[nbr]=x;
                    entarj[x]=false;
                    ind=x;
                 //   while(x!=s)
                   // {
                        pilch[ind]=x;
                        ind=x;
                        entarj[x]=false;
                        cfc[x]=nbr;
                  //  }
                }
                pilch[x]=0;
            }
        }

		public void prufer() 
		   {
            int m = adj[0][0];
            prufer = new int [m-1];
            prufer[0]=m-2;
            for(int i=1; i<=m; i++)
            {
                adj[i][0]=0;
                for(int j=1; j<=m; j++)
                {
                    if(adj[i][j] ==1)
                    {
                        adj[i][0]++;
                    }
                }
            }
             for(int k=1 ; k<=m-2 ; k++){
                    int i = 1 ;
                    for(i=1 ; i<=m ; i++){
                      if(adj[i][0] == 1){ 
                        adj[i][0] = 0;
                        break;
                      }
                    
                    for(int j=1 ; j<=m ; j++){
                      if(adj[i][j] == 1){
                    	  prufer[k]    = j;
                        adj[i][j] = 0; 
                        adj[j][i] = 0;
                        adj[j][0]--;
                        break;
                      }
                    }}
                  } 
        }
		
		public void ordonnancement(int[] fp, int[] app, int[]d, int[] lc, int[] fpc, int[] apps) {
			int n= app[0];
			int m=fp[0];
			apps = new int[n+1];
			apps[0]=n;
			lc = new int[n+1];
			fpc = new int[n+1];
			int kc;
			int t;
			fpc[1]=0;
			lc[1]=0;
			app[1]=1;
			kc=1;
			for(int s=2; s<=n; s++) {
				lc[s]=0;
				apps[s]=kc+1;
				for(int k=app[s];(t=fp[k])!=0;k++) {
					int longueur =lc[t]+d[t];
					if(longueur >= lc[s] ) {
						if(longueur> lc[s]) {
							kc =apps[s];
							fp[kc]=t;
						}
						else {
							k++;
							fpc[kc]=t;
						}
					}
				}
				kc++;
				fpc[kc]=0;
			}
			fpc[0]=kc;
		}
		
		public void rang() {
			int n = aps[0];
			int taillefs = fs[0];
			int s,k,h,t;
			rang = new int[n+1];
			int[] ddi= new int[n+1];
			int[] pilch= new int[n+1];
			int[] prem= new int[n+1];
		    for(int i=1; i <=n ; i++) ddi[i]=0;
		    for(int i=1; i <=taillefs ; i++) 
			{
				s=fs[i];
				if (s >0) ddi[s]++;
			}
			pilch[0]=0;
			for(s = 1; s <= n; s++)
			{
				rang[s] = -1; 
				if (ddi[s] == 0) empiler(s,pilch);
			}

			k=-1;
			s=pilch[0];
			prem[0] = s;
			while (pilch[0] > 0)
			{
				k++;
				pilch[0] = 0;
				while (s > 0)
				{
					rang[s] = k;
					h = aps[s]; t = fs[h];
					while (t > 0)
					{
						ddi[t]--;
						if (ddi[t] == 0) empiler(t,pilch);
						h++;
						t=fs[h];
					}
					s = pilch[s];
				}
				s = pilch[0];
				prem[k+1] = s;
			}
		}
		
		public void calcul_dist(int s) {
			int n = aps[0];
			dist = new int[n+1];
			dist[0]=n;
			for(int i=1; i<=Sommet.nombreSommets(); i++) {
				dist[i]=-1;}
				dist[s]=0;
				int d=0;
				int[] fa = new int[n];
				fa[0]=s;
				int t = -1, q = 0, p = 0 ;
			while (t<q) {
				d++;
				for(int i=t+1; i<q; i++) {
					int u = fa[i];
					int v;
					for( int k=aps[u]; (v=fs[k])!=0; k++) {
						if(dist[v]==-1) {
							dist[v]=d;
							fa[++p]=v;
						}
				}
			}
				t=q;
				q=p;
				
			}
			
		}
		
		public int dmin(int[] d, boolean[] InS) {
			int n = d[0];
			int min = Integer.MAX_VALUE;
			int j = -1;
			for(int i = 1; i<= n ; i++) {
				if(!InS[i]) {
					if(d[1]< min) {
						min = d[i];
						j = i;
					}
				}
			}
			return j;
		}
		
		public void dijkstra(int s, int[] fs, int[] aps, int[][] c, int[] d, int[] pred) {
			int n = aps[0];
			d = new int[n+1];
			d[0] = n;
			pred = new int[n+1];
			boolean[] InS = new boolean[n+1];
			for(int i=1; i<=n ; i++) {
				d[i] = c[s][i];
				InS[i] = false;
				if(d[i] != Integer.MAX_VALUE) {
					pred[i] = s;
				}else {
					pred[i] = -1;
				}
				InS[s] = true;
				int cpt = n-1;
				while(cpt>0) {
					int j = dmin(d, InS);
					if(j==-1) {return;}
					InS[j] = true;
					int k;
					for(int p = aps[j]; (k = fs[p]) != 0; p++) {
						if(!InS[k]) {
							int v;
							if(c[j][k] != Integer.MAX_VALUE) {
								v = d[j] + c[j][k];
								if(v < d[k]) {
									d[k]=v;
									pred[k] =j;
								}
							}
						}
					}
					cpt--;		
				}
			}
		}
		public void fusionner(int i, int j, int[] prem, int[] pilch, int[]cfc, int[] nbElem)
		{
			if(nbElem[i] < nbElem[j])
			{
				int aux=i;
				i=j;
				j=aux;
			}
			int s= prem[j];
			cfc[s]=i;
			while (pilch[s] != 0)
			{
				s = pilch[s];
				cfc[s] = i;
			}
			pilch[s] = prem[i];
			prem[i] = prem[j];
			nbElem[i] += nbElem[j];
		}
		
		public void trier()
		{
			int p;
			int s, t;
			int t1;
			for (int i = 0; i < adj[0][1] -1; i++)
			{
				for (int j = i + 1; j < adj[0][1]; j++)
				{
					s= aretes[j].getOrig().getEtiquette();
					t= aretes[i].getExtr().getEtiquette();
					t1= aretes[j].getExtr().getEtiquette();
					if ((aretes[j].getPoids() < aretes[i].getPoids()) || (aretes[j].getPoids() == aretes[i].getPoids() && s < t) || (aretes[j].getPoids() == aretes[i].getPoids() && t1< t))
					{
						p = aretes[j].getPoids();
						aretes[j].setPoids(aretes[i].getPoids());
						aretes[i].setPoids(p);
					}
				}
			}
		}
		
		public Graphe kruskal(int[] nbElem)
		{
			Graphe t= new Graphe();
			t.aretes=new Arete[adj[0][0]-1];
			int x;
			int y;
			int i=0,j=0;
			while (j < adj[0][0]-1)
			{
				Arete ar = aretes[i];
				int s= ar.getOrig().getEtiquette();
				int r= ar.getExtr().getEtiquette();
				x = cfc[s];
				y = cfc[r];
				if (x != y)
				{
					t.aretes[j++]=aretes[i];
					fusionner(x, y, prem, pilch, cfc, nbElem);
				}
				i++;
			}
			t.adj[0][0]=adj[0][0];
			t.adj[0][0]=adj[0][0]-1;
			return t;
		}
		public String prufertoString() {
			String s ="";
			for(int i=0; i<prufer.length;i++) {
				s += " | "+prufer[i];
			}
			s+=" | ";
			return s;
		}
		
		public String CFCtoString() {
			String s="";
			for(int i=0; i<cfc.length;i++) {
				s += " | "+cfc[i];
			}
			return s;
		}
		
		public String RangtoString() {
			String s="";
			for(int i=0; i<rang.length;i++) {
				s += " | "+rang[i];
			}
			return s;
		}
		
		public String DistancetoString() {
			String s="";
			for(int i=0; i<dist.length;i++) {
				s += " | "+dist[i];
			}
			return s;
		}
		
		
		
	}
