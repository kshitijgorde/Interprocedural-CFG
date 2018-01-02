// 
// Decompiled by Procyon v0.5.30
// 

package org.chaingang.common;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Iterator;
import java.util.Map;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

public class AppProperties
{
    private Hashtable hashProps;
    private Hashtable hashDesc;
    protected final String URL_DEF = "http://www.chaingang.org";
    
    public AppProperties() {
        this.hashProps = new Hashtable();
        this.hashDesc = new Hashtable();
    }
    
    public void add(final String s, final Object o) {
        this.hashProps.put(s, o);
    }
    
    public void add(final String s, final Object o, final String s2) {
        this.add(s, o);
        this.hashDesc.put(s, s2);
    }
    
    public URL getDefUrl() {
        URL url = null;
        try {
            url = new URL("http://www.chaingang.org");
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    public boolean isDefUrl(final URL url) {
        return this.getDefUrl().equals(url);
    }
    
    public Object get(final String s) {
        return this.hashProps.get(s);
    }
    
    public void loadParameters(final Applet applet) {
        final Iterator<Map.Entry<String, V>> iterator = this.hashProps.entrySet().iterator();
        for (int i = 0; i < this.hashProps.size(); ++i) {
            final String s = iterator.next().getKey();
            this.setProperty(s, applet.getParameter(s));
        }
    }
    
    public void loadProperties(final Properties properties) {
        final Iterator<Map.Entry<String, V>> iterator = this.hashProps.entrySet().iterator();
        for (int i = 0; i < this.hashProps.size(); ++i) {
            final String s = iterator.next().getKey();
            this.setProperty(s, properties.getProperty(s));
        }
    }
    
    public void loadProperties(final InputStream inputStream) throws IOException {
        final Properties properties = new Properties();
        properties.load(inputStream);
        this.loadProperties(properties);
    }
    
    public void setProperty(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        final Object value = this.hashProps.get(s);
        if (value == null) {
            return;
        }
        final String name = value.getClass().getName();
        if (name.equals("java.net.URL")) {
            try {
                this.hashProps.put(s, new URL(s2));
            }
            catch (MalformedURLException ex) {}
        }
        else if (name.equals("java.awt.Color")) {
            try {
                this.hashProps.put(s, Color.decode(s2));
            }
            catch (NumberFormatException ex2) {}
        }
        else if (name.equals("java.lang.Integer")) {
            try {
                this.hashProps.put(s, new Integer(Integer.parseInt(s2)));
            }
            catch (NumberFormatException ex3) {}
        }
        else {
            this.hashProps.put(s, s2);
        }
    }
    
    public String[][] getParameterInfo() {
        final String[][] array = new String[this.hashProps.size()][3];
        final Iterator<Map.Entry<String, V>> iterator = this.hashProps.entrySet().iterator();
        for (int i = 0; i < this.hashProps.size(); ++i) {
            final Map.Entry<String, V> entry = iterator.next();
            array[i][0] = entry.getKey();
            array[i][1] = entry.getValue().getClass().getName();
            array[i][2] = (String)this.hashDesc.get(array[i][0]);
        }
        return array;
    }
}
