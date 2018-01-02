// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import java.io.IOException;
import com.daysofwonder.util.t;
import java.io.DataOutput;
import java.io.DataInput;
import com.daysofwonder.req.p;
import com.daysofwonder.req.k;
import com.daysofwonder.a.n;

public class m implements n
{
    private String a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private int f;
    private boolean g;
    
    public m() {
    }
    
    public m(final String a, final int b, final int c, final boolean d, final boolean e, final int f, final boolean g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public int a(final k k) {
        int a = p.a(this.a);
        a += 4;
        a += 4;
        a += 3;
        a += 4;
        return a;
    }
    
    public void a(final DataInput dataInput) {
        this.a = dataInput.readUTF();
        this.b = dataInput.readInt();
        this.c = dataInput.readInt();
        this.d = dataInput.readBoolean();
        this.e = dataInput.readBoolean();
        this.f = dataInput.readInt();
        this.g = dataInput.readBoolean();
    }
    
    public void a(final DataOutput dataOutput) {
        try {
            dataOutput.writeUTF(this.a);
            dataOutput.writeInt(this.b);
            dataOutput.writeInt(this.c);
            dataOutput.writeBoolean(this.d);
            dataOutput.writeBoolean(this.e);
            dataOutput.writeInt(this.f);
            dataOutput.writeBoolean(this.g);
        }
        catch (IOException ex) {
            t.a(ex);
        }
    }
    
    public final String a() {
        return this.a;
    }
    
    public final int b() {
        return this.c;
    }
    
    public final boolean c() {
        return this.d;
    }
    
    public final boolean d() {
        return this.e;
    }
    
    public int e() {
        return this.f;
    }
}
