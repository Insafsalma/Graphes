package graphe;

public class Algorithme {
int[] prem ;
	int[] pilch;
	int[] cfc;
	int[] num;
	int[] mu;
	int[] tarj;
	boolean[] entarj;
	int p;
	int[] aps;
	int[] fs;
	public void empiler(int s, int[] pilch)
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
				while(x!=s)
				{
					pilch[ind]=x;
					ind=x;
					entarj[x]=false;
					cfc[x]=nbr;
				}
			}
			pilch[x]=0;
		}
	}

	public void prufer(int[][]A, int[]t) 
	{
		int m = A[0][0];
		t = new int [m-1];
		for(int i=1; i<=m; i++)
		{
			A[i][0]=0;
			for(int j=1; j<=m; j++)
			{
				if(A[i][j] ==1)
				{
					A[i][0]++;
				}
			}
		}
		t[01]=m-2;
		for(int k=1; k<=m-2; k++)
		{
			for(int i=1; i<=m;i++)
			{
				if(A[i][0] ==1)
				{
					A[i][0]=0;
					break;
				}
				for( int j=1; j<=m; j++)
				{
					if(A[i][j] ==1)
					{
						t[k]=j;
					}
					A[i][j]=0;
					A[j][i]=0;
					A[j][0]--;
				}
				break;
			}
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
	public void rang(int[] rang, int[] fs, int[] aps) {
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
	public void calcul_dist(int s, int[] fs, int[] aps, int[] dist) {
		int n = aps[0];
		dist = new int[n+1];
		dist[0]=n;
		for(int i=1; i<=11; i++) {
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
	
}
