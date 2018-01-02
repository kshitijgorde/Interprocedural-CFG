// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.d;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class a extends b
{
    private String hd;
    private Properties he;
    private Properties hc;
    
    public a() {
        this("./chart1.props");
    }
    
    public a(final String hd) {
        this.hd = hd;
        this.he = new Properties();
        this.hc = new Properties();
        try {
            final FileInputStream fileInputStream = new FileInputStream(hd);
            this.he.load(fileInputStream);
            fileInputStream.close();
            this.aw();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isApplication() {
        return true;
    }
    
    public String if(final String s, final String s2) {
        return this.a(s, s2);
    }
    
    public int a(final String s, final int n) {
        return this.if(s, n);
    }
    
    public String getParameter(final String s) {
        if (this.hc.containsKey(s)) {
            return this.hc.getProperty(s);
        }
        return this.he.getProperty(s);
    }
    
    public void setPersistentString(final String s) {
        ((Hashtable<String, String>)this.he).put("persist", s);
        this.aw();
        final File file = new File(this.hd);
        if (file.exists() && !file.canWrite()) {
            System.out.println("Chart persistence is not avaliable: file [" + this.hd + "] cannot be rewritten");
            return;
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.he.store(fileOutputStream, "Chart properties");
            fileOutputStream.close();
            System.out.println("Chart properties have been successfully saved");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void aw() {
        this.hc.clear();
        final String a = this.a();
        if (a == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(a, "|");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(",");
            ((Hashtable<String, String>)this.hc).put(nextToken.substring(0, index), nextToken.substring(index + 1));
        }
    }
}
