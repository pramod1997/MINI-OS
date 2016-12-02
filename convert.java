package os;


public class convert{
	int n;
	int[] h;
public convert(String s){
	String[] ele = s.split("\\s+");
	n= ele.length;
	h=new int[n];
	// int i = Integer.parseInt(s.trim());
	 //System.out.println("int i = " + i);
	 for(int i=0;i<n;i++)
	 {
		 //System.out.println(ele[i]);
		// = Integer.parseInt(s.trim());
		 h[i]= Integer.parseInt(ele[i].trim());
	 }
	/* for(int i=0;i<n;i++)
	 {
		 System.out.println(h[i]);
	 }*/
	 
}
public int[] method1(){
	return h;
}
}