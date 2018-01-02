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
import java.net.URL;

public class TabFile
{
    private URL url;
    
    public TabFile(final URL url, final String s) {
        try {
            this.url = new URL(url, s);
        }
        catch (MalformedURLException ex) {
            System.out.println(ex);
        }
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
                    ((Hashtable<String, String>)cloneable).put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
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
}
