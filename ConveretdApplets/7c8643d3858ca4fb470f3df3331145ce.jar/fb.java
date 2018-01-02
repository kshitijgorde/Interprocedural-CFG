import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class fb extends Panel
{
    private final esChat a;
    Vector b;
    d c;
    public String d;
    a e;
    cb f;
    int g;
    boolean h;
    boolean i;
    boolean j;
    private static String z;
    
    fb(final esChat a, final String d) {
        this.a = a;
        this.b = new Vector();
        this.c = new d(this.a, "", 230);
        this.g = -1;
        this.h = true;
        this.i = false;
        this.j = false;
        this.setLayout(null);
        this.setBackground(a.h);
        this.setForeground(a.f);
        this.c.setBackground(a.h);
        this.c.setForeground(a.f);
        this.setSize(a.w.width, 20);
        a.getClass();
        this.e = new a(a, this.c);
        a.getClass();
        this.f = new cb(a, fb.z, "", this.c);
        this.add(this.c);
        this.add(this.e);
        this.add(this.f);
        this.c.setBounds(0, 0, a.w.width + 2, this.getSize().height + 5);
        this.e.setBounds(0, 50, a.w.width, 30);
        this.f.setBounds(0, 50, a.w.width, 30);
        this.d = d;
        this.c.addMouseListener(new k(this));
        this.c.addKeyListener(new i(this));
    }
    
    static esChat a(final fb fb) {
        return fb.a;
    }
    
    public void a() {
        this.c.setBounds(0, 0, this.a.w.width + 5, this.getSize().height + 5);
    }
    
    public void requestFocus() {
        this.c.requestFocus();
    }
    
    static {
        final char[] charArray = "HL\u0014\u0015<".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = ':';
                    break;
                }
                case 1: {
                    c2 = ')';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = 'Q';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        fb.z = new String(charArray).intern();
    }
}
