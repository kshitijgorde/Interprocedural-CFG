// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class t implements bb
{
    public co q;
    public p q;
    
    public static String q(final String s, final String[] array) {
        try {
            final int length = array.length;
            int n = 0;
            int n2;
            if ((n2 = s.indexOf(37)) == -1) {
                return s;
            }
            String s2 = new String("");
            do {
                s2 = s2.concat(s.substring(n, n2));
                final char char1;
                switch (char1 = s.charAt(n2 + 1)) {
                    case '%': {
                        s2 = s2.concat("%");
                        ++n2;
                        break;
                    }
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        final char c;
                        if ((c = (char)(char1 - '1')) < length && array[c] != null) {
                            s2 = s2.concat(array[c]);
                        }
                        ++n2;
                        break;
                    }
                    default: {
                        System.out.println("I don't know what to do with " + s);
                        return null;
                    }
                }
                n = n2 + 1;
            } while ((n2 = s.indexOf(37, n)) != -1 && n2 < s.length());
            return s2.concat(s.substring(n));
        }
        catch (Exception ex) {
            System.err.println("format = " + s);
            return "Incorrect format";
        }
    }
    
    public static String q(final String s, final String s2) {
        return q(s, new String[] { s2 });
    }
    
    public t() {
    }
    
    public t(final co q) {
        this.q = q;
    }
    
    public void q(final cp cp) {
        if (cp.q() != 4202576) {
            return;
        }
        final String q = cp.q(0, 0);
        final int q2 = cp.q(0, 0);
        final int q3 = cp.q(0, 1);
        final int q4 = cp.q(0, 2);
        switch (q3) {
            case 1: {
                final p p;
                (p = new p(this.q.q, al.q("Info"), new String[] { al.q("Yes"), al.q("No") }, new String[] { al.q(q) }, null, this.q)).setVisible(true);
                this.q(q2, this.q.a, al.q("Yes").equals(p.q));
            }
            case 3: {
                if (this.q != null && this.q.isVisible()) {
                    this.q.dispose();
                }
                l l;
                if ((l = (l)this.q.e.w(q4)) == null) {
                    l = (l)this.q.r.w(q4);
                }
                bn.q("AV conference with " + l.o, q, h.q().getCodeBase() + "Resources/" + co.p + "/" + ((aw)this.q.w.w(l.e)).o);
                System.out.println("Open AV");
            }
            case 2: {
                if (this.q != null && this.q.isVisible()) {
                    this.q.q(q);
                    return;
                }
                (this.q = new p(this.q.q, al.q("Info"), new String[] { al.q("Close") }, new String[] { al.q(q) }, null, this.q)).setModal(false);
                this.q.setVisible(true);
                break;
            }
        }
    }
    
    public void q(final int n, final int n2, final boolean b) {
        final cp q;
        (q = q(n, 3, n2, "")).q(0, 0, b);
        this.q.r(q);
    }
    
    public static cp q(final int n, final int n2, final int n3, final String s) {
        final cp cp;
        (cp = new cp(4202576, 1)).q(0, 0, n);
        cp.q(0, 1, n2);
        cp.q(0, 2, n3);
        cp.q(0, 0, s);
        return cp;
    }
}
