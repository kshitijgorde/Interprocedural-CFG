// 
// Decompiled by Procyon v0.5.30
// 

package speedometer.A;

import java.util.Enumeration;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.MissingResourceException;
import java.net.URL;
import java.util.Hashtable;

public class C
{
    private static C A;
    protected Hashtable B;
    
    static {
        C.A = null;
    }
    
    protected C() {
        this.B = new Hashtable();
    }
    
    public static synchronized C A() {
        if (C.A == null) {
            C.A = new C();
        }
        return C.A;
    }
    
    public URL E(final String s) throws MissingResourceException {
        return Thread.currentThread().getContextClassLoader().getResource(s);
    }
    
    public File A(final String s) throws MissingResourceException {
        File file = null;
        final URL e = this.E(s);
        if (e != null) {
            file = new File(e.getFile());
        }
        return file;
    }
    
    protected InputStream G(final String s) throws MissingResourceException {
        final InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(s);
        if (resourceAsStream == null) {
            throw new MissingResourceException("cannot find file: filename=" + s, this.getClass().getName(), s);
        }
        return resourceAsStream;
    }
    
    public F D(final String s) throws MissingResourceException {
        F c;
        if ((c = this.C(s)) == null) {
            final InputStream g = this.G(s);
            try {
                c = new F();
                c.load(g);
                this.B.put(s, c);
            }
            catch (IOException ex) {
                throw new MissingResourceException("cannot read file: filename=" + s, this.getClass().getName(), s);
            }
            finally {
                try {
                    g.close();
                }
                catch (Exception ex2) {}
            }
            try {
                g.close();
            }
            catch (Exception ex3) {}
        }
        return c;
    }
    
    public F B(final Object o) throws MissingResourceException {
        final String name = o.getClass().getName();
        return this.D(String.valueOf(name.substring(name.lastIndexOf(46) + 1)) + ".properties");
    }
    
    public Document B(final String s) throws MissingResourceException {
        final InputStream g = this.G(s);
        Document parse;
        try {
            parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(g);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new MissingResourceException("cannot read file, filename=" + s + ", error=" + ex, this.getClass().getName(), s);
        }
        return parse;
    }
    
    public Document A(final Object o) throws MissingResourceException {
        final String name = o.getClass().getName();
        return this.B(String.valueOf(name.substring(name.lastIndexOf(46) + 1)) + ".properties");
    }
    
    public F C(final String s) {
        return this.B.get(s);
    }
    
    public Enumeration B() {
        return this.B.keys();
    }
    
    public F F(final String s) {
        return this.B.remove(s);
    }
    
    public void C() {
        this.B.clear();
    }
}
