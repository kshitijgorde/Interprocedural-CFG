// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import com.daysofwonder.a.n;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;

public class E extends k
{
    private byte[] l;
    private static final Boolean m;
    private static final Boolean n;
    private int o;
    private boolean p;
    private Vector q;
    private Vector r;
    
    private static Boolean a(final boolean b) {
        return b ? E.m : E.n;
    }
    
    private static boolean b(final Boolean b) {
        return b == E.m;
    }
    
    public E(final int n) {
        super(12);
        this.k(n);
    }
    
    public E() {
        this(32768);
    }
    
    public E(final G g) {
        this.g = 12;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.o);
        this.writeBoolean(this.p);
        if (this.l != null) {
            this.write(this.l);
        }
    }
    
    public void b() {
        super.b();
        this.o = this.readInt();
        this.p = this.readBoolean();
        final DataInputStream dataInputStream = new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(this.v())));
        final int int1 = dataInputStream.readInt();
        this.q = new Vector();
        for (int i = 0; i < int1; ++i) {
            final int int2 = dataInputStream.readInt();
            final String utf = dataInputStream.readUTF();
            final boolean boolean1 = dataInputStream.readBoolean();
            final int int3 = dataInputStream.readInt();
            final boolean boolean2 = dataInputStream.readBoolean();
            final boolean boolean3 = dataInputStream.readBoolean();
            final boolean boolean4 = dataInputStream.readBoolean();
            final boolean boolean5 = dataInputStream.readBoolean();
            final int int4 = dataInputStream.readInt();
            final Vector vector = new Vector<String>(int4);
            final Vector vector2 = new Vector<Integer>(int4);
            final Vector vector3 = new Vector<Integer>(int4);
            final Vector vector4 = new Vector<Integer>(int4);
            final Vector vector5 = new Vector<Boolean>(int4);
            final Vector vector6 = new Vector<Integer>(int4);
            if (int4 > 0) {
                for (int j = 0; j < int4; ++j) {
                    vector.addElement(dataInputStream.readUTF());
                    vector2.addElement(dataInputStream.readInt());
                    vector3.addElement(dataInputStream.readInt());
                    vector4.addElement(dataInputStream.readInt());
                    vector5.addElement(a(dataInputStream.readBoolean()));
                    vector6.addElement(dataInputStream.readInt());
                }
            }
            final int int5 = dataInputStream.readInt();
            final int int6 = dataInputStream.readInt();
            n c = null;
            if (dataInputStream.readBoolean()) {
                c = com.daysofwonder.a.k.d().c();
                c.a(dataInputStream);
            }
            this.q.addElement(new b(int2, utf, boolean1, vector, vector3, vector4, vector5, vector6, vector2, int3, boolean2, boolean3, boolean4, boolean5, int5, int6, c, null));
            vector.removeAllElements();
            vector2.removeAllElements();
            vector3.removeAllElements();
            vector4.removeAllElements();
            vector5.removeAllElements();
            vector6.removeAllElements();
        }
        final int int7 = dataInputStream.readInt();
        this.r = new Vector();
        for (int k = 0; k < int7; ++k) {
            this.r.addElement(new l(dataInputStream.readUTF(), dataInputStream.readInt(), dataInputStream.readBoolean(), dataInputStream.readInt(), null));
        }
    }
    
    public Vector d() {
        return this.q;
    }
    
    public Vector e() {
        return this.r;
    }
    
    public int f() {
        return this.o;
    }
    
    public boolean g() {
        return this.p;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        ++i;
        i += 4;
        return i + ((this.l != null) ? this.l.length : 0);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[tclist=");
        super.a(sb);
        if (this.p) {
            sb.append(",Open]");
        }
        else {
            sb.append(",Closed]");
        }
        return sb;
    }
    
    static {
        m = new Boolean(true);
        n = new Boolean(false);
    }
}
