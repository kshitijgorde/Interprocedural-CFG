// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.util;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import com.guymcarthur.Debuggable;
import java.util.Vector;

public class WordList extends Vector implements Debuggable
{
    private boolean debug;
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public static void main(final String[] array) {
        if (array.length < 1) {
            System.out.println("Usage: WordList dictionary [words]");
            return;
        }
        try {
            final WordList list = new WordList(new BufferedReader(new FileReader(array[0])));
            if ("true".equals(System.getProperty("debug"))) {
                list.setDebug(true);
            }
            for (int i = 1; i < array.length; ++i) {
                System.out.println(array[i] + " => " + list.has(array[i]));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public int has(final String s) {
        boolean startsWith = false;
        int n = -1;
        int i = this.size();
        while (i > n + 1) {
            final int n2 = n + (i - n) / 2;
            if (this.debug) {
                System.out.println("" + n + " < " + n2 + " <  " + i);
            }
            final String s2 = this.elementAt(n2);
            final int compareTo = s.compareTo(s2);
            if (this.debug) {
                System.out.println("" + compareTo + " = Comparing " + s + " to " + s2);
            }
            if (compareTo == 0) {
                return 1;
            }
            if (compareTo > 0) {
                n = n2;
            }
            else {
                i = n2;
            }
            if (startsWith) {
                continue;
            }
            startsWith = s2.startsWith(s);
        }
        return startsWith ? -1 : 0;
    }
    
    public WordList(final BufferedReader bufferedReader) throws IOException {
        super(10000, 1000);
        this.debug = false;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.addElement(line.trim().toLowerCase());
        }
    }
}
