import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class g extends o implements Cloneable
{
    public byte[] a;
    public String b;
    public long c;
    public int d;
    public byte[] e;
    public boolean f;
    public int g;
    
    public g() {
        this.d();
    }
    
    public g(final String b, final int d) {
        this.d();
        this.b = b;
        this.d = d;
        this.f();
    }
    
    public final Object clone() {
        final g g = new g();
        this.f();
        g.a(this.a, this.a.length);
        return g;
    }
    
    private void d() {
        this.e = null;
        this.a = null;
        this.b = null;
        this.c = -1L;
        this.d = -1;
        this.f = false;
        this.g = 19;
    }
    
    public final void a(final byte[] array, final int n) {
        System.arraycopy(array, 0, this.a = new byte[n], 0, n);
        this.e();
    }
    
    public final void a(final byte[] a) {
        this.a = a;
        this.e();
    }
    
    private void e() {
        try {
            final DataInputStream dataInputStream;
            final int unsignedShort;
            if ((unsignedShort = (dataInputStream = new DataInputStream(new ByteArrayInputStream(this.a))).readUnsignedShort()) + 2 != this.a.length) {
                this.f = false;
                this.d = -1;
                this.d();
                return;
            }
            int n;
            for (n = 2; this.a[n] != 0; ++n) {}
            this.b = new String(this.a, 2, n - 2);
            dataInputStream.skipBytes(n - 1);
            ++n;
            this.d = dataInputStream.readUnsignedByte();
            ++n;
            if (o.a(this.d)) {
                n += 4;
                if (this.g < 19 && n >= unsignedShort) {
                    this.f = false;
                    this.d();
                    return;
                }
                this.c = dataInputStream.readUnsignedShort();
                this.c <<= 16;
                this.c += dataInputStream.readUnsignedShort();
            }
            dataInputStream.readFully(this.e = new byte[this.a.length - n]);
            this.f = true;
        }
        catch (Exception ex) {
            this.d = -1;
            this.d();
        }
    }
    
    private void f() {
        if (this.f) {
            return;
        }
        try {
            boolean b = false;
            int n = 2 + this.b.length();
            if (o.a(this.d)) {
                b = true;
                n += 4;
            }
            if (this.e != null) {
                n += this.e.length;
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(n + 2);
            final DataOutputStream dataOutputStream;
            (dataOutputStream = new DataOutputStream(byteArrayOutputStream)).writeShort(n);
            dataOutputStream.write(this.b.getBytes());
            dataOutputStream.writeByte(0);
            dataOutputStream.writeByte(this.d);
            if (b) {
                dataOutputStream.writeInt((int)this.c);
            }
            if (this.e != null) {
                dataOutputStream.write(this.e);
            }
            this.a = byteArrayOutputStream.toByteArray();
            this.f = true;
        }
        catch (Exception ex) {
            this.a = null;
        }
    }
    
    public final byte[] a() {
        this.f();
        return this.a;
    }
    
    public final int b() {
        if (this.e != null) {
            return this.e.length;
        }
        return 0;
    }
    
    public final void b(final byte[] e) {
        this.f = false;
        this.e = e;
    }
    
    public final String toString() {
        try {
            final StringBuffer sb;
            (sb = new StringBuffer(1024)).append(this.b);
            sb.append(" : typ ");
            sb.append(Integer.toString(this.d));
            sb.append(" : req ");
            sb.append(this.c);
            sb.append(" : ");
            if (this.e != null) {
                sb.append(this.e.length);
            }
            else {
                sb.append("size 0");
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public final boolean c() {
        switch (this.d) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 22:
            case 23:
            case 24:
            case 25:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
