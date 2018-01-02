// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataOutput;
import java.io.EOFException;
import java.io.DataInput;

public final class en implements cw
{
    public int a;
    public int b;
    
    public en(final int a, final int b) {
        if (a < 1 || a > 15 || b < 0 || b >= 4 || (a == 15 && b >= 2)) {
            throw new IllegalArgumentException("rank = " + a + " suit = " + b);
        }
        this.a = a;
        this.b = b;
    }
    
    public en() {
    }
    
    public static en a(final DataInput dataInput) {
        final en en;
        (en = new en()).a(dataInput);
        return en;
    }
    
    public final void a(final DataInput dataInput) {
        final byte byte1;
        if ((byte1 = dataInput.readByte()) == -1) {
            throw new EOFException();
        }
        if (byte1 < 0 || byte1 >= 58) {
            throw new bk("Illegal card");
        }
        this.a = byte1 / 4 + 1;
        this.b = byte1 % 4;
    }
    
    public final void a(final DataOutput dataOutput) {
        dataOutput.write((this.a - 1 << 2) + this.b);
    }
    
    public static boolean a(final en en, final en en2) {
        return en.a == en2.a && en.b == en2.b;
    }
    
    public final boolean equals(final Object o) {
        return o instanceof en && ((en)o).a == this.a && ((en)o).b == this.b;
    }
    
    public final String toString() {
        return "suit: " + this.b + " number: " + this.a;
    }
    
    public final String a(final ak ak) {
        String s;
        if (this.a == 10) {
            s = ak.a(1716519018);
        }
        else if (this.a == 11) {
            s = ak.a(1716519022);
        }
        else if (this.a == 12) {
            s = ak.a(1716519017);
        }
        else if (this.a == 13) {
            s = ak.a(1716519023);
        }
        else if (this.a == 14) {
            s = ak.a(1716519020);
        }
        else if (this.a == 1) {
            s = ak.a(1716519026);
        }
        else if (this.a == 15) {
            s = ak.a(1716519025);
        }
        else {
            s = Integer.toString(this.a);
        }
        String s2;
        if (this.b == 0) {
            s2 = ak.a(1716519019);
        }
        else if (this.b == 1) {
            s2 = ak.a(1716519021);
        }
        else if (this.b == 3) {
            s2 = ak.a(1716519024);
        }
        else {
            s2 = ak.a(1716519016);
        }
        return s + s2;
    }
    
    public final int hashCode() {
        return this.b + (this.a - 2 << 2);
    }
}
