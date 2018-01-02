// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.kb;
import java.net.URL;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.awt.Component;

public class pb implements ib, cb
{
    private static f a;
    private Component b;
    private db c;
    private int d;
    private int e;
    private static /* synthetic */ Class f;
    public static int g;
    private static String z;
    
    public boolean a() {
        return this.c.isActive();
    }
    
    public int b() {
        return this.d;
    }
    
    public int c() {
        return this.e;
    }
    
    public void a(final EventListener eventListener) {
        if (eventListener instanceof MouseListener) {
            this.b.addMouseListener((MouseListener)eventListener);
        }
        if (eventListener instanceof MouseMotionListener) {
            this.b.addMouseMotionListener((MouseMotionListener)eventListener);
        }
        if (eventListener instanceof KeyListener) {
            this.b.addKeyListener((KeyListener)eventListener);
        }
    }
    
    public void b(final EventListener eventListener) {
        if (eventListener instanceof MouseListener) {
            this.b.removeMouseListener((MouseListener)eventListener);
        }
        if (eventListener instanceof MouseMotionListener) {
            this.b.removeMouseMotionListener((MouseMotionListener)eventListener);
        }
        if (eventListener instanceof KeyListener) {
            this.b.removeKeyListener((KeyListener)eventListener);
        }
    }
    
    public Component a() {
        return this.b;
    }
    
    public db b() {
        return this.c;
    }
    
    public URL c() {
        return this.c.getCodeBase();
    }
    
    public kb d() {
        return null;
    }
    
    public boolean e() {
        return false;
    }
    
    public static pb a(final Component b, final db c, final int d, final int e) {
        final pb pb = (pb)neat.system.pb.a.a();
        pb.b = b;
        pb.c = c;
        pb.d = d;
        pb.e = e;
        return pb;
    }
    
    public void f() {
        pb.a.a(this);
    }
    
    public void g() {
    }
    
    public void h() {
        this.b = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public pb() {
        this.b = null;
        this.c = null;
    }
    
    static {
        final char[] charArray = "}\u0005@pr`\u0019Rp9~NQf".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0013';
                            break;
                        }
                        case 1: {
                            c2 = '`';
                            break;
                        }
                        case 2: {
                            c2 = '!';
                            break;
                        }
                        case 3: {
                            c2 = '\u0004';
                            break;
                        }
                        default: {
                            c2 = '\\';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                pb.z = new String(charArray).intern();
                pb.a = new f((pb.f != null) ? pb.f : (pb.f = a(pb.z)));
                return;
            }
            continue;
        }
    }
}
