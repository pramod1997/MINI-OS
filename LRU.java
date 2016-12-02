package os;

public class LRU {
	int Output;
	public LRU(int fr,int[] in){
			int a[] = new int [fr];
			int c[] = new int [fr];
		//	int b[] = new int[n];
			for(int i=0;i<fr;i++)
			{
				a[i]=-1;
			}
			for(int i=0;i<fr;i++)
			{
				c[i]=0;
			}
			int fault=0;
			for(int i=0;i<in.length;i++)
			{
				int k=0;
				for(int j=0;j<fr;j++)
				{
					if(in[i]==a[j])
					{
						k=1;
						//y=j;
						break;
						//c[j]=0;
				//		System.out.println(k);
					}
			
				}
				for(int t=0;t<fr;t++)
				{
					if(a[t]!=-1)
						c[t]=c[t]+1;
				}
				
			//	for(int p=0;p<4;p++)
				//{
					//System.out.println(c[p]);
			    //}
				if(k!=1)
				{
				int index = 0;
				int g=0;
				int x=0;
				for(x=0;x<fr;x++)
				{
					if(a[x]==-1)
					{
						g=1;
						break;
					}   
				}
				if(g==1)
				{
				a[x]=in[i];
				fault=fault+1;
				//c[x]=c[x]+1;
				}
				if(g!=1)
				{
				for(int j=1;j<fr;j++)
				{
					int max = c[0];
					 index = 0;
					if(max<c[j])
					{
						//System.out.println(1);
						max=c[j];
						index = j;
					}
				}
				a[index]=in[i];
				c[index]=0;
				c[index]=c[index]+1;
				//for(int p=0;p<4;p++)
				//{
					//System.out.println(a[p]);
				//}
				fault=fault+1;
				}
				}
		
			}
			Output=fault;
	}
	public int method(){
		return Output;
	}
}
