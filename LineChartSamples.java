/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;


import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;;

public class LineChartSamples extends Application {
	public void start(Stage primaryStage) throws Exception{
		init(primaryStage);
	}

	public void init(Stage primaryStage) {
		int n,avwt=0,avtat=0,i,j;
		 System.out.println("Enter total number of processes(maximum 20):");
		 Scanner sc=new Scanner(System.in);
		 int balla =sc.nextInt();
		 int vinni=balla;
		 int bt[] = new int[10];
		 int wt[] = new int[10];
		 int tat[] = new int[10];
		 int fc[] = new int[10];
		 System.out.println("\nEnter Process Burst Time\n");
		 for(i=0;i<balla;i++)
		   {
		        System.out.println("P[%d]:"+i+1);
		        bt[i]=sc.nextInt();
		        fc[i]=bt[i];
		   }
		  wt[0]=0;   
		  for(i=1;i<balla;i++)
		   {
		       wt[i]=0;
		       for(j=0;j<i;j++)
		           wt[i]+=bt[j];
		   }
		  for(i=0;i<balla;i++)
		    {
		        tat[i]=bt[i]+wt[i];
		        avwt+=wt[i];
		        avtat+=tat[i];
		    }
		 
		    avwt/=i;
		    avtat/=i;
		    System.out.println(avwt+"\n");
		    System.out.println(avtat);
		    
		    int total=0,pos,temp;
		    int p[]=new int[10];
		    float avg_wt,avg_tat;
		    for(i=0;i<balla;i++)
		    {
		        p[i]=i+1;        
		    }
		    for(i=0;i<balla;i++)
		    {
		        pos=i;
		        for(j=i+1;j<balla;j++)
		        {
		            if(bt[j]<bt[pos])
		                pos=j;
		        }
		 
		        temp=bt[i];
		        bt[i]=bt[pos];
		        bt[pos]=temp;
		 
		        temp=p[i];
		        p[i]=p[pos];
		        p[pos]=temp;
		    }
		    wt[0]=0;            
		    for(i=1;i<balla;i++)
		    {
		        wt[i]=0;
		        for(j=0;j<i;j++)
		            wt[i]+=bt[j];
		 
		        total+=wt[i];
		    }
		    avg_wt=(float)total/balla;      
		    total=0;		 
		    for(i=0;i<balla;i++)
		    {
		        tat[i]=bt[i]+wt[i];     
		        total+=tat[i];
		    }		 
		    avg_tat=(float)total/balla;     
		    System.out.println(avg_wt+"\n");
		    System.out.println(avg_tat);		
		    
		    int a[]=new int[balla];
		    int x[] = new int[balla];
		    int smallest,count=0,time;
		    double avg=0,tt=0,end;
		    for(i=0;i<balla;i++)
		        a[i]=i; 
		    for(i=0;i<balla;i++)
		        x[i]=bt[i];
		    bt[9]=9999;		     
		    for(time=0;count!=balla;time++)
		    {
		      smallest=9;
		     for(i=0;i<balla;i++)
		     {
		      if(a[i]<=time && bt[i]<bt[smallest] && bt[i]>0 )
		      smallest=i;
		     }
		     bt[smallest]--;
		     if(bt[smallest]==0)
		     {
		      count++;
		      end=time+1;
		      avg=avg+end-a[smallest]-x[smallest];
		      tt= tt+end-a[smallest];
		     }
		    }
		    double WT=avg/balla;
		    System.out.println(avg/balla);
		    System.out.println("Average Turnaround time"+tt/balla);
		    
		    int remain,flag=0,time_quantum; 
		    int wait_time=0,turnaround_time=0;
		    int at[] = new int[10];
		    int rt[] = new int[10]; 
		    remain=balla; 
		    for(count=0;count<vinni;count++) 
		    {  
		      at[count]=count;  
		      rt[count]=fc[count]; 
		    } 
		    System.out.println("Enter Time Quantum:\t"); 
		    time_quantum=sc.nextInt();  
		    for(time=0,count=0;remain!=0;) 
		    { 
		      if(rt[count]<=time_quantum && rt[count]>0) 
		      { 
		        time+=rt[count]; 
		        rt[count]=0; 
		        flag=1; 
		      } 
		      else if(rt[count]>0) 
		      { 
		        rt[count]-=time_quantum; 
		        time+=time_quantum; 
		      } 
		      if(rt[count]==0 && flag==1) 
		      { 
		        remain--; 
		        wait_time+=time-at[count]-fc[count]; 
		        turnaround_time+=time-at[count]; 
		        flag=0; 
		      } 
		      if(count==vinni-1) 
		        count=0; 
		      else if(at[count+1]<=time) 
		        count++; 
		      else 
		        count=0; 
		    }
		    double WT1=wait_time*1.0/vinni;
		    System.out.printf("\nAverage Waiting Time= %f\n",wait_time*1.0/vinni); 
		    System.out.printf("Avg Turnaround Time = %f",turnaround_time*1.0/vinni); 


		HBox root = new HBox();
		Scene scene = new Scene(root , 450,330);
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("ALGORITHM");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Average Waiting Time");
		LineChart<String ,Number> lineChart= new LineChart<String ,Number>(xAxis,yAxis);
		lineChart.setTitle("Graph On Schedulin Algorithms");
		XYChart.Series<String,Number> data= new XYChart.Series<String,Number>();
		data.getData().add(new XYChart.Data<String,Number>("FCFS",avg_wt));
		data.getData().add(new XYChart.Data<String,Number>("SJF",avwt));
		data.getData().add(new XYChart.Data<String,Number>("SRTF",WT));
		data.getData().add(new XYChart.Data<String,Number>("RR",WT1));
		lineChart.getData().add(data);
		root.getChildren().add(lineChart);
		primaryStage.setTitle("Hi!This is vinnu");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
		

	
		public static void main(String args[]){
			
			launch(args);
			
	}
	


}