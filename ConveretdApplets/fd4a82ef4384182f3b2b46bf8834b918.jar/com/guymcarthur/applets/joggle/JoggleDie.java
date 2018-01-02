// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets.joggle;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.BufferedReader;

public class JoggleDie implements Cloneable
{
    private String[] faces;
    private int face;
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public String getValue() {
        return this.faces[this.face];
    }
    
    public String roll() {
        final String[] faces = this.faces;
        final int face = (int)(Math.random() * this.faces.length);
        this.face = face;
        return faces[face];
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.faces[0]);
        for (int i = 1; i < this.faces.length; ++i) {
            sb.append(", ").append(this.faces[i]);
        }
        return sb.toString();
    }
    
    public static final JoggleDie[] loadDice(final BufferedReader bufferedReader) throws IOException {
        final Vector vector = new Vector<JoggleDie>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(line.trim(), ",");
            final String[] array = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = stringTokenizer.nextToken().trim().toLowerCase();
                if (array[i].equals("q")) {
                    array[i] = "qu";
                }
            }
            try {
                vector.addElement(new JoggleDie(array));
            }
            catch (Throwable t) {
                t.printStackTrace(System.err);
            }
        }
        final JoggleDie[] array2 = new JoggleDie[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array2[j] = vector.elementAt(j);
        }
        return array2;
    }
    
    public JoggleDie(final String[] faces) throws Exception {
        this.face = 0;
        if (faces.length < 6) {
            throw new Exception("The die must have at least six faces.");
        }
        this.faces = faces;
    }
}
