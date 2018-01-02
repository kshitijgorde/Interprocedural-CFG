import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.util.Vector;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class eb extends Panel
{
    private final esChat a;
    Vector b;
    TextArea c;
    public String d;
    a e;
    bb f;
    int g;
    boolean h;
    boolean i;
    boolean j;
    private static String z;
    
    eb(final esChat a, final String d) {
        this.a = a;
        this.b = new Vector();
        this.c = new TextArea("", 0, 0, 3);
        this.g = -1;
        this.h = true;
        this.i = false;
        this.j = false;
        this.setLayout(null);
        this.setBackground(a.h);
        this.setForeground(a.f);
        this.c.setBackground(a.h);
        this.c.setForeground(a.f);
        this.setSize(a.v.width, 20);
        a.getClass();
        this.e = new a(a, this.c);
        a.getClass();
        this.f = new bb(a, eb.z, "", this.c);
        this.add(this.c);
        this.add(this.e);
        this.add(this.f);
        this.c.setBounds(-2, -1, a.v.width + 2, this.getSize().height + 5);
        this.c.setColumns(0);
        this.c.setRows(0);
        this.e.setBounds(0, 50, a.v.width, 30);
        this.f.setBounds(0, 50, a.v.width, 30);
        this.d = d;
        this.c.addMouseListener(new j(this));
        this.c.addKeyListener(new h(this));
    }
    
    static esChat a(final eb eb) {
        return eb.a;
    }
    
    public void a() {
        this.c.setBounds(-2, -1, this.a.v.width + 5, this.getSize().height + 5);
    }
    
    public void requestFocus() {
        this.c.requestFocus();
    }
    
    static {
        final char[] charArray = ")ngOh".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '[';
                    break;
                }
                case 1: {
                    c2 = '\u000b';
                    break;
                }
                case 2: {
                    c2 = '\u0014';
                    break;
                }
                case 3: {
                    c2 = '&';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        eb.z = new String(charArray).intern();
    }
}
