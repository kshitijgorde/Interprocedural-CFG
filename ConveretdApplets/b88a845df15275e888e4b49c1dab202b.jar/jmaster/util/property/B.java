// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property;

import java.util.Properties;
import java.util.Enumeration;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.util.Iterator;
import java.io.IOException;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.io.File;
import java.util.MissingResourceException;
import java.net.URL;
import java.util.Hashtable;

public class B
{
    private static final String C = "@include";
    private static B A;
    protected Hashtable B;
    
    protected B() {
        this.B = new Hashtable();
    }
    
    public static synchronized B C() {
        if (B.A == null) {
            B.A = new B();
        }
        return B.A;
    }
    
    public URL F(final String s) throws MissingResourceException {
        return Thread.currentThread().getContextClassLoader().getResource(s);
    }
    
    public File A(final String s) throws MissingResourceException {
        File file = null;
        final URL f = this.F(s);
        if (f != null) {
            file = new File(f.getFile());
        }
        return file;
    }
    
    protected InputStream H(final String s) throws MissingResourceException {
        final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(s);
        if (resourceAsStream == null) {
            throw new MissingResourceException("cannot find file: filename=" + s, this.getClass().getName(), s);
        }
        return resourceAsStream;
    }
    
    protected Reader C(final String s) throws MissingResourceException, UnsupportedEncodingException {
        return new InputStreamReader(this.H(s), "utf-8");
    }
    
    public D G(final String s) throws MissingResourceException {
        Cloneable e;
        if ((e = this.E(s)) == null) {
            Reader c = null;
            try {
                c = this.C(s);
                final StringBuffer sb = new StringBuffer();
                int read;
                while ((read = c.read()) != -1) {
                    if (read <= 255) {
                        sb.append((char)read);
                    }
                    else {
                        final String hexString = Long.toHexString(read);
                        sb.append("\\u");
                        for (int i = hexString.length(); i < 4; ++i) {
                            sb.append('0');
                        }
                        sb.append(hexString);
                    }
                }
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sb.toString().getBytes("ISO-8859-1"));
                e = new D();
                ((Properties)e).load(byteArrayInputStream);
                Cloneable cloneable = null;
                for (final String s2 : ((Hashtable<String, ? extends V>)e).keySet()) {
                    if (s2.startsWith("@include")) {
                        if (cloneable == null) {
                            cloneable = new D();
                            ((Hashtable<String, V>)cloneable).putAll((Map<? extends String, ? extends V>)e);
                        }
                        ((Hashtable<String, ? extends V>)cloneable).putAll((Map<? extends String, ? extends V>)this.G(((Properties)e).getProperty(s2)));
                    }
                }
                if (cloneable != null) {
                    e = cloneable;
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
                throw new MissingResourceException("cannot read file: filename=" + s, this.getClass().getName(), s);
            }
            finally {
                try {
                    c.close();
                }
                catch (Exception ex2) {}
            }
            this.B.put(s, e);
        }
        return (D)e;
    }
    
    public D B(final Object o) throws MissingResourceException {
        final String name = o.getClass().getName();
        return this.G(name.substring(name.lastIndexOf(46) + 1) + ".properties");
    }
    
    public D A(final Class clazz) throws MissingResourceException {
        final String name = clazz.getName();
        return this.G(name.substring(name.lastIndexOf(46) + 1) + ".properties");
    }
    
    public Document B(final String s) throws MissingResourceException {
        final InputStream h = this.H(s);
        Document parse;
        try {
            parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(h);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new MissingResourceException("cannot read file, filename=" + s + ", error=" + ex, this.getClass().getName(), s);
        }
        return parse;
    }
    
    public Document A(final Object o) throws MissingResourceException {
        final String name = o.getClass().getName();
        return this.B(name.substring(name.lastIndexOf(46) + 1) + ".properties");
    }
    
    public D E(final String s) {
        return this.B.get(s);
    }
    
    public Enumeration A() {
        return this.B.keys();
    }
    
    public D D(final String s) {
        return this.B.remove(s);
    }
    
    public void B() {
        this.B.clear();
    }
    
    static {
        B.A = null;
    }
}
