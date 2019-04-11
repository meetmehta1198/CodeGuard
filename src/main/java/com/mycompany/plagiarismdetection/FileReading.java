package com.mycompany.plagiarismdetection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author meetmehta
 */
 class LongestCommonSubsequence 
{ 
  
  /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
  int lcs( List<String> X, List<String> Y, int m, int n ) 
  { 
    int L[][] = new int[m+1][n+1]; 
		for(int i=0;i<=m;i++)
			L[i][0]=0;
		for(int i=0;i<=n;i++)
			L[0][i]=0;
    /* Following steps build L[m+1][n+1] in bottom up fashion. Note 
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
    /*for (int i=0; i<=m; i++) 
    { 
      for (int j=0; j<=n; j++) 
      { 
        if (i == 0 || j == 0) 
            L[i][j] = 0; 
        else if (X[i-1] == Y[j-1]) 
            L[i][j] = L[i-1][j-1] + 1; 
        else
            L[i][j] = max(L[i-1][j], L[i][j-1]); 
      } 
    } */
	int i=1,j=1;
	for(String t1 : X)
	{
	//	System.out.println("JAAAAAAAAy " + t1);
		j=1;
		for(String t2 : Y)
		{
		//	System.out.println("MEEEEEEEEEEEEEET " + t2);
			if(t1.equals(t2))
				L[i][j] = L[i-1][j-1] + 1;
			else
				L[i][j] = max(L[i-1][j], L[i][j-1]);
			
			j++;	
		}
		i++;
	}
	
  return L[m][n]; 
  } 
  
  /* Utility function to get max of 2 integers */
  int max(int a, int b) 
  { 
    return (a > b)? a : b; 
  } 
}

public class FileReading {

    /**
     * @param args the command line arguments
     */
    public static double compareFiles( String i1,String i2) throws IOException
    {
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        /*for (String line : i1) {
            baos1.write(line.getBytes());
        }
        for (String line : i2) {
            baos2.write(line.getBytes());
        }*/
        baos1.write(i1.getBytes());
        baos2.write(i2.getBytes());
        byte[] bytes1 = baos1.toByteArray();
        byte[] bytes2 = baos2.toByteArray();
        InputStream in1 = new ByteArrayInputStream(bytes1);
        InputStream in2 = new ByteArrayInputStream(bytes2);
        ANTLRInputStream input1 = new ANTLRInputStream(in1);
        ANTLRInputStream input2 = new ANTLRInputStream(in2);
        // create a lexer that feeds off of input CharStream
        CLexer lexer1 = new CLexer(input1);
        CLexer lexer2 = new CLexer(input2);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens1 = new CommonTokenStream(lexer1);
        CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
        // create a parser that feeds off the tokens buffer
        CParser parser1 = new CParser(tokens1);
        CParser parser2 = new CParser(tokens2);
        //int count = 0;
        ParseTree tree1 = parser1.compilationUnit();
        ParseTree tree2 = parser2.compilationUnit();
        String s1=tree1.toStringTree(parser1);
        String s2=tree2.toStringTree(parser2);
        List<String> l1=new ArrayList<String>();
        List<String> l2=new ArrayList<String>();
        String[] temp1=s1.split("\\s+");
        String[] temp2=s2.split("\\s+");
         for(String s3: temp1)
         {
             l1.add(s3);
         }
        for(String s3: temp2)
        {
            l2.add(s3);
        }
        int a=l1.size();
        int b=l2.size();
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        int ans = lcs.lcs( l1, l2, a, b );
        double percent= ((double) (2*ans)/(a+b))*100;
        return percent;
        
    }
    public static double checkF(List<String> temp,String fname) throws IOException 
    {
        demo d=new demo();
        String s2=d.readFileInList(fname);
         s2=s2.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
         String s1=" ";
         for(String t : temp)
         {
             s1=s1.concat(t);
         }
          s1=s1.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
        double percent=compareFiles(s1,s2);
        return percent;
    }
     private static boolean isErrorProne(String name) {
        return !name.matches("/\\*(.|[\\r\\n])*?\\*/");
}       
    public static double compareTwoFiles(String fname1, String fname2) throws IOException
    {
        demo d=new demo();
         
        String i1 = d.readFileInList(fname1);
        i1=i1.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
        i1=i1.replaceAll("#include<.*","");
        //i1=i1.replaceAll("#include<stdlib.h>","");
        /*Iterator<String> itr = i1.iterator();
            while (itr.hasNext())
                System.out.println(itr.next());*/
        String i2 = d.readFileInList(fname2);
        i2=i2.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
        i2=i2.replaceAll("#include<.*","");
        //i2=i2.replaceAll("#include<stdlib.h>","");
        double percent=compareFiles(i1,i2);
         System.out.println(percent);
         
         return percent;
    }
    
    /*public static JTable compareDirectory(String fname) throws IOException
    {
        demo d=new demo();
        File folder=new File(fname);
        File[] listOffiles = folder.listFiles();
       // System.out.println(fname);
      // DefaultTableModel model = new DefaultTableModel();
      //  model.addColumn("InputFile");
       // model.addColumn("CompareFile");
       // model.addColumn("Match");
                javax.swing.JTable table = new javax.swing.JTable();
       //DefaultTableModel model = (DefaultTableModel) table.getModel();
       int i=0;
        for( File file : listOffiles)
        {
            if(file.isFile())
            {
              //  System.out.println(file.getName());
                List<String> i1 = d.readFileInList(fname+'/'+file.getName());
                for( File f1 : listOffiles)
                {
                    if(!(f1.getName().equals(file.getName())))
                    {
                        List<String> i2=d.readFileInList(fname+'/'+f1.getName());
                        double percent=compareFiles(i1,i2);
                         //model.addRow(new Object[]{file.getName(),f1.getName(),percent});
                         table.setValueAt(file.getName(),i,0);
                         table.setValueAt(f1.getName(),i,1);
                         table.setValueAt(percent,i,2);
                      //  System.out.println("EEEEE##########"+percent);
                        i++;
                    }
                }
            }
            
        }
     
        return table;
    }*/
  /*  public static void main(String[] args) throws IOException 
    {
        Scanner sc=new Scanner(System.in);
        //String s=sc.next();
        //compareDirectory(s);
    }
    */
}
