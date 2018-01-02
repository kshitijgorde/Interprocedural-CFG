// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import zylom.ZylomDataGather;

public class mb extends kb implements Runnable, ab
{
    private static f f;
    public static final Object g;
    private transient ob h;
    private transient Object i;
    private transient boolean j;
    private transient neat.ob k;
    private static /* synthetic */ Class l;
    private static String[] z;
    
    public void a() throws rb {
        this.c();
    }
    
    private void c() {
        if (!this.h.b(this)) {
            this.d();
        }
    }
    
    public void n() {
        this.d();
    }
    
    public void o() {
        this.p();
        synchronized (this.i) {
            this.j = true;
            this.k.a(this);
        }
        // monitorexit(this.i)
    }
    
    public void p() {
        synchronized (this.i) {
            if (!this.j) {
                // monitorexit(this.i)
                return;
            }
            this.k.d();
            this.j = false;
        }
        // monitorexit(this.i)
        final ob h = this.h;
        this.b();
        h.b();
    }
    
    public void q() {
        synchronized (this.i) {
            if (this.j) {
                if (!this.k.c()) {
                    throw new RuntimeException(mb.z[0]);
                }
                throw new rb();
            }
            else {
            }
            // monitorexit(this.i)
        }
    }
    
    public lb r() {
        return (lb)this.b(lb.i);
    }
    
    public ob s() {
        return this.h;
    }
    
    protected void j() {
        nb.a(this);
        lb.a(this, this.h.a());
        this.h.a(this);
    }
    
    protected void k() {
        super.k();
    }
    
    public void run() {
        ZylomDataGather.bugfix = true;
        while (true) {
            synchronized (this.i) {
                if (!this.k.b()) {
                    // monitorexit(this.i)
                    break;
                }
            }
            // monitorexit(this.i)
            try {
                this.a();
            }
            catch (rb rb) {
                final ob h = this.h;
                this.p();
                h.c();
                break;
            }
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        synchronized (this.i) {
            objectOutputStream.writeBoolean(this.j);
        }
        // monitorexit(this.i)
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.i = new Object();
        this.j = objectInputStream.readBoolean();
        final neat.kb a = neat.kb.a(mb.z[1]);
        this.k = neat.ob.a(a);
        a.f();
    }
    
    public void validateObject() throws InvalidObjectException {
        if (this.j) {
            this.o();
        }
    }
    
    public static mb a(final ob h) {
        final mb mb = (mb)neat.system.mb.f.a();
        mb.h = h;
        mb.a(neat.system.mb.g);
        return mb;
    }
    
    public void f() {
        mb.f.a(this);
    }
    
    public void g() {
        super.g();
        this.j = false;
        final neat.kb a = neat.kb.a(mb.z[1]);
        this.k = neat.ob.a(a);
        a.f();
    }
    
    public void h() {
        this.k.f();
        this.k = null;
        this.h = null;
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public mb() {
        this.h = null;
        this.i = new Object();
        this.k = null;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "H{\"K~s`&Z,n{:W,hfvOz`|:OnmpvH~nxv]ura3C,u}$Kme5w".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0001';
                            break;
                        }
                        case 1: {
                            c2 = '\u0015';
                            break;
                        }
                        case 2: {
                            c2 = 'V';
                            break;
                        }
                        case 3: {
                            c2 = '.';
                            break;
                        }
                        default: {
                            c2 = '\f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "rl%Zil5$Acu".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0001';
                            break;
                        }
                        case 1: {
                            c4 = '\u0015';
                            break;
                        }
                        case 2: {
                            c4 = 'V';
                            break;
                        }
                        case 3: {
                            c4 = '.';
                            break;
                        }
                        default: {
                            c4 = '\f';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "op7Z\"rl%Zil;;L".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0001';
                            break;
                        }
                        case 1: {
                            c6 = '\u0015';
                            break;
                        }
                        case 2: {
                            c6 = 'V';
                            break;
                        }
                        case 3: {
                            c6 = '.';
                            break;
                        }
                        default: {
                            c6 = '\f';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                mb.z = z;
                mb.f = new f((mb.l != null) ? mb.l : (mb.l = a(mb.z[2])));
                g = ((mb.l != null) ? mb.l : (mb.l = a(mb.z[2])));
                return;
            }
            continue;
        }
    }
}
