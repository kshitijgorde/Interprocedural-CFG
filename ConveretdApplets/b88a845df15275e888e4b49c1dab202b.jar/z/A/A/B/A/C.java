// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.B.A;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.Serializable;

public class C implements Serializable
{
    static final long B = 7110175216435025451L;
    private final HashMap A;
    
    public C() {
        this.A = new HashMap(10);
    }
    
    public void A(final byte b, final byte[] array) {
        this.E(b).add(array);
    }
    
    public byte[] A(final byte b) {
        return this.A(b, 0);
    }
    
    public byte[] A(final byte b, final int n) {
        final List b2 = this.B(b);
        if (b2 == null || b2.size() <= n) {
            return null;
        }
        return b2.get(n);
    }
    
    public int D(final byte b) {
        final List b2 = this.B(b);
        if (b2 == null) {
            return 0;
        }
        return b2.size();
    }
    
    public void B(final byte b, final int n) {
        this.A.get(new Byte(b)).remove(n);
    }
    
    public void C(final byte b) {
        this.A.remove(new Byte(b));
    }
    
    private List B(final byte b) {
        return this.A.get(new Byte(b));
    }
    
    private List E(final byte b) {
        final Byte b2 = new Byte(b);
        List list;
        if (this.A.containsKey(b2)) {
            list = this.A.get(b2);
        }
        else {
            list = new ArrayList();
            this.A.put(b2, list);
        }
        return list;
    }
    
    public boolean F(final byte b) {
        return this.A.containsKey(new Byte(b));
    }
    
    public static void A(final File file, final C c) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(c);
        }
        finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }
    
    public static C A(final File file) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            return (C)objectInputStream.readObject();
        }
        finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }
}
