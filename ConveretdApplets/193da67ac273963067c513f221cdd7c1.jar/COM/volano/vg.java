// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Event;
import java.util.Hashtable;
import java.awt.TextField;

public class vg extends TextField
{
    public int a;
    public long b;
    public String c;
    public String d;
    public String e;
    public String f;
    private vau g;
    private Hashtable h;
    
    public vg(final int n) {
        super(n);
        this.a = -1;
        this.b = 0L;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.h = new Hashtable();
    }
    
    public void a(final boolean b) {
        this.g = (b ? new vau(50) : null);
    }
    
    public void a(final int n, final String s) {
        this.h.put(new Integer(n), s);
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = false;
        if (this.a(event, n)) {
            b = true;
        }
        else {
            switch (n) {
                case 1004: {
                    if (this.g != null) {
                        this.setText(this.g.a());
                        b = true;
                        break;
                    }
                    break;
                }
                case 1005: {
                    if (this.g != null) {
                        this.setText(this.g.b());
                        b = true;
                        break;
                    }
                    break;
                }
                case 10:
                case 13: {
                    if (this.g != null) {
                        this.g.a(this.getText());
                        this.g.c();
                        break;
                    }
                    break;
                }
                default: {
                    if (this.a == -1 || this.getText().length() < this.a) {
                        this.a();
                        break;
                    }
                    b = true;
                    break;
                }
                case 8:
                case 9:
                case 127:
                case 1000:
                case 1001:
                case 1002:
                case 1003:
                case 1006:
                case 1007: {
                    break;
                }
                case 1008:
                case 1009:
                case 1010:
                case 1011:
                case 1012:
                case 1013:
                case 1014:
                case 1015:
                case 1016:
                case 1017:
                case 1018:
                case 1019: {
                    b = this.b(n, this.getText());
                    break;
                }
                case 27: {
                    this.setText("");
                    if (this.g != null) {
                        this.g.c();
                    }
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
    
    private boolean a(final Event event, final int n) {
        return ((event.modifiers & 0x8) != 0x0 && this.c.indexOf(n) != -1) || ((event.modifiers & 0x2) != 0x0 && this.d.indexOf(n) != -1) || ((event.modifiers & 0x4) != 0x0 && this.e.indexOf(n) != -1) || ((event.modifiers & 0x1) != 0x0 && this.f.indexOf(n) != -1);
    }
    
    private boolean b(final int n, final String s) {
        boolean b = false;
        final String s2 = this.h.get(new Integer(n));
        if (s2 != null && s2.length() > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final Vector<String> vector = new Vector<String>();
            while (stringTokenizer.hasMoreTokens()) {
                vector.addElement(stringTokenizer.nextToken());
            }
            this.setText(vaq.a(s2, vector));
            b = true;
        }
        return b;
    }
    
    private void a() {
        if (this.b > 0L) {
            try {
                Thread.sleep(this.b);
            }
            catch (InterruptedException ex) {}
        }
    }
}
