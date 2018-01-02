// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Vector;

public final class bl implements cw
{
    public r a;
    private boolean a;
    
    public bl(final byte b) {
        this();
    }
    
    private bl() {
        this.a = new r();
        this.a = false;
    }
    
    public bl(int i, final boolean b) {
        this.a = new r();
        int j;
        for (i = 2; i <= 14; ++i) {
            for (j = 0; j < 4; ++j) {
                this.a(new en(i, j));
            }
        }
    }
    
    public final void a(final en en) {
        this.a.addElement(en);
    }
    
    private int a(final en en, final boolean b) {
        for (int i = 0; i < this.a.size(); ++i) {
            if (en.a(en, (en)this.a.elementAt(i))) {
                if (b) {
                    this.a.removeElementAt(i);
                }
                return i;
            }
        }
        return -1;
    }
    
    public final boolean a(final en en) {
        return this.a(en, true) != -1;
    }
    
    public final boolean b(final en en) {
        return this.a(en, false) != -1;
    }
    
    public final String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.a.size(); ++i) {
            sb.append(((en)this.a.elementAt(i)).toString());
        }
        return sb.toString();
    }
    
    public final void a(final dp dp) {
        dp.a(this.a);
    }
    
    public final void a(final bl bl) {
        for (int i = 0; i < bl.a.size(); ++i) {
            this.a(bl.a(i));
        }
    }
    
    public final boolean a(final int n) {
        for (int i = 0; i < this.a.size(); ++i) {
            if (((en)this.a.elementAt(i)).b == n) {
                return true;
            }
        }
        return false;
    }
    
    public final en a(final int n) {
        return this.a.elementAt(n);
    }
    
    public final Object clone() {
        final bl bl = new bl((byte)0);
        for (int i = 0; i < this.a.size(); ++i) {
            bl.a.addElement(this.a.elementAt(i));
        }
        bl.a = this.a;
        return bl;
    }
    
    public final void a(final DataOutput dataOutput) {
        dataOutput.writeShort(this.a.size());
        for (int i = 0; i < this.a.size(); ++i) {
            this.a(i).a(dataOutput);
        }
    }
    
    public final void a(final DataInput dataInput) {
        this.a.removeAllElements();
        for (short short1 = dataInput.readShort(), n = 0; n < short1; ++n) {
            this.a(en.a(dataInput));
        }
    }
}
