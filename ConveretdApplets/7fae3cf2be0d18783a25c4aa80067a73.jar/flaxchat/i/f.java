// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import flaxchat.a.h;
import java.awt.Color;
import java.awt.event.MouseEvent;
import flaxchat.d.b;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class f extends e implements MouseListener, MouseMotionListener
{
    private static String z;
    
    public f(final String s, final String s2, final Image image) {
        super(s, s2, image);
        this.setFont(flaxchat.d.b.d(f.z));
        this.a();
        this.addMouseMotionListener(this);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        super.g = true;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        super.g = true;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        super.g = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        super.g = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.getForeground() == Color.red) {
            this.setForeground(Color.black);
        }
        if (!flaxchat.a.h.b(mouseEvent)) {
            return;
        }
        super.h = !super.h;
        this.b();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void a(final boolean h) {
        super.h = h;
        this.repaint();
    }
    
    public boolean a() {
        return super.h;
    }
    
    public boolean g() {
        return true;
    }
    
    public void b(final boolean g) {
        super.g = g;
        this.repaint();
    }
    
    static {
        f.z = z(z("cteCScaho`x{s"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '&';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0017';
                    break;
                }
                case 1: {
                    c2 = '\u0015';
                    break;
                }
                case 2: {
                    c2 = '\u0007';
                    break;
                }
                case 3: {
                    c2 = '\u0001';
                    break;
                }
                default: {
                    c2 = '&';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
