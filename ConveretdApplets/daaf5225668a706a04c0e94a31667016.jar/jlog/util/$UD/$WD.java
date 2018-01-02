// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$UD;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.applet.Applet;

public class $WD extends $XD
{
    private Applet applet;
    
    public boolean $ZD(final String s, final String s2) throws IOException {
        boolean b = false;
        if (s2 != null && s2.length() != 0) {
            try {
                final InputStream openStream = new URL(this.applet.getDocumentBase(), s2).openStream();
                try {
                    this.load(openStream);
                    b = true;
                }
                finally {
                    openStream.close();
                }
            }
            catch (IOException ex) {}
        }
        super.$DE = false;
        if (s != null && s.length() != 0) {
            final InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(s);
            try {
                this.$EE(systemResourceAsStream);
            }
            finally {
                systemResourceAsStream.close();
            }
        }
        return b;
    }
    
    public $WD(final Applet applet) {
        this.applet = null;
        if (applet == null) {
            throw new NullPointerException("applet");
        }
        this.applet = applet;
    }
    
    public $WD(final Applet applet, final Properties properties) {
        super(properties);
        this.applet = null;
        if (applet == null) {
            throw new NullPointerException("applet");
        }
        this.applet = applet;
    }
    
    public String getProperty(final String s, final String s2) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return super.getProperty(s, s2);
    }
}
