package com.mycompany.plagiarismdetection;

//package org.abcd.examples.CMaven;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;
import java.util.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.antlr.v4.runtime.tree.Trees;
//import org.antlr.v4.runtime.tree.gui.TreeViewer;

/**
 * Hello world!
 *
 */
public class MainClass {
    public static void main(String[] args) throws Exception 
    {
        // create a CharStream that reads from standard input
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the directory folder:-");
        String s1=sc.next();
        String s2=sc.next();
        FileReading f=new FileReading();
        f.compareTwoFiles(s1,s2);

    }

}
