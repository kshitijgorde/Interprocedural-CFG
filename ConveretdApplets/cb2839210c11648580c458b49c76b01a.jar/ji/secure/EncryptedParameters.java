// 
// Decompiled by Procyon v0.5.30
// 

package ji.secure;

import java.util.Hashtable;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import ji.io.ac;
import ji.zip.a4;
import ji.util.cf;
import java.util.Properties;

public class EncryptedParameters
{
    private Properties a;
    
    public EncryptedParameters() {
        this.a = new Properties();
    }
    
    public EncryptedParameters(final String s) throws Exception {
        this();
        this.a(s);
    }
    
    private void a(final String s) throws Exception {
        this.a.load(new ByteArrayInputStream(a4.a(new cg().a(cf.b(s.getBytes(), 0, s.getBytes().length, 8), false), null)));
    }
    
    public String getParameter(final String s) {
        return this.a.getProperty(s.toLowerCase());
    }
    
    public void setParameter(final String s, final String s2) {
        ((Hashtable<String, String>)this.a).put(s.toLowerCase(), s2);
    }
    
    public String getParametersAsString() {
        try {
            return this.a();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private String a() throws Exception {
        final cg cg = new cg();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        this.a.save(gzipOutputStream, null);
        gzipOutputStream.close();
        return cf.a(cg.a(byteArrayOutputStream.toByteArray(), 0, false), 8);
    }
}
