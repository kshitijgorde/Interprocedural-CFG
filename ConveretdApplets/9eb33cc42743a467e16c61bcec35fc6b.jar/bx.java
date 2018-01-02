import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import java.io.OutputStream;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class bx extends s
{
    public b7 fq;
    public Hashtable k0;
    
    public bx(final b7 fq) {
        super(null, -2);
        this.fq = fq;
        this.k0 = new Hashtable();
    }
    
    public final synchronized void j1(final String s, final String s2, final int n) {
        final Vector<String> vector = new Vector<String>();
        vector.addElement(s2);
        vector.addElement((String)new Integer(n));
        vector.addElement((String)new Boolean(false));
        this.k0.put(s, vector);
    }
    
    public final synchronized void j0(final String s) {
        this.k0.remove(s);
    }
    
    public final synchronized Vector j_(final String s) {
        final Vector<Boolean> vector = this.k0.get(s);
        if (vector != null && !vector.elementAt(2)) {
            this.j0(s);
        }
        return vector;
    }
    
    public final int jz(final String s) {
        final int index;
        if (s == null || s.equals("") || (index = s.indexOf(58)) == -1) {
            return 0;
        }
        int n;
        if ((n = s.indexOf(46, index)) == -1) {
            n = s.length();
        }
        try {
            return Integer.parseInt(s.substring(index + 1, n));
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final String jy(final String s) {
        final int index;
        if (s == null || s.equals("") || s.charAt(0) == ':' || s.indexOf("unix:") == 0 || (index = s.indexOf(58)) == -1) {
            return "localhost";
        }
        return s.substring(0, index);
    }
    
    public final void d_() throws Exception {
        while (true) {
            final ax ax = (ax)super.e7.hm();
            final int int1 = ax.readInt();
            String s;
            int n;
            if (ax.h_ == 27) {
                if (!this.fq.lr().g1.f5()) {
                    break;
                }
                final String f8 = this.fq.lr().g1.f8();
                s = this.jy(f8);
                n = 6000 + this.jz(f8);
            }
            else {
                s = ax.jw();
                n = ax.readInt();
            }
            String jw;
            if (this.fq.lx()) {
                jw = ax.jw();
            }
            else {
                jw = "unknown (origin-option not used)";
            }
            final Vector j_ = this.j_(s);
            if (j_ != null) {
                s = j_.elementAt(0);
                n = (int)j_.elementAt(1);
            }
            try {
                final Socket socket = new Socket(s, n);
                final int l2 = this.fq.l2();
                final y y = new y(socket, l2, int1, this.fq);
                this.fq.lz(y);
                y.d6(jw);
                final av av = new av(21, this.fq.f7);
                av.writeInt(int1);
                av.writeInt(l2);
                ca.me("Port open (" + jw + ") : " + s + ": " + n + " (#" + int1 + ")" + " new: " + l2);
                this.fq.ee(av);
                y.ei();
            }
            catch (IOException ex) {
                final av av2 = new av(22, this.fq.f7);
                av2.writeInt(int1);
                this.fq.h9("Failed port open (" + jw + ") : " + s + ": " + n + " (#" + int1 + ")");
                this.fq.ee(av2);
            }
        }
        this.fq.h9("Something is fishy with the server, unsolicited X11 forward!");
        throw new Exception("Something is fishy with the server, unsolicited X11 forward!");
    }
}
