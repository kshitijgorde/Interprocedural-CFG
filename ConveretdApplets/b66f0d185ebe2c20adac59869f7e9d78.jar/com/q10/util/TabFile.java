// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

import java.util.Hashtable;
import java.net.URLConnection;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.net.MalformedURLException;
import java.util.Vector;
import java.net.URL;

public class TabFile
{
    private URL url;
    private Vector linhas;
    
    public TabFile(final URL url, final String s) {
        try {
            this.url = new URL(url, s);
        }
        catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        this.linhas = new Vector();
    }
    
    public Properties getTabFile() {
        Cloneable cloneable = null;
        try {
            final URLConnection openConnection = this.url.openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setAllowUserInteraction(false);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            cloneable = new Properties();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, "=");
                    final String nextToken = stringTokenizer.nextToken();
                    final String nextToken2 = stringTokenizer.nextToken();
                    ((Hashtable<String, String>)cloneable).put(nextToken, nextToken2);
                    this.linhas.addElement(new ChaveValor(nextToken, nextToken2));
                }
                catch (NoSuchElementException ex3) {}
            }
            bufferedReader.close();
        }
        catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        catch (IOException ex2) {
            System.out.println(ex2);
        }
        return (Properties)cloneable;
    }
    
    public Vector getLinhas() {
        return this.linhas;
    }
}
