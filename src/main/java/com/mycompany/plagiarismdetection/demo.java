package com.mycompany.plagiarismdetection;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

class demo{

    public static void main( String a[] )
    {
        String source = readFileInList("079_03.c");

        System.out.println(source.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)",""));

    }


    public static String readFileInList(String fileName) {

        File file = new File(fileName);

        char[] buffer = null;

        try {
                BufferedReader bufferedReader = new BufferedReader( new FileReader(file));

                buffer = new char[(int)file.length()];

                int i = 0;
                int c = bufferedReader.read();

                while (c != -1) {
                    buffer[i++] = (char)c;
                    c = bufferedReader.read();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new String(buffer);
    }

}
