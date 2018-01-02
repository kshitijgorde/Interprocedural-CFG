// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import flaxchat.d.d;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Window;

public class f extends Window implements MouseListener, Runnable
{
    private int a;
    private int b;
    private String c;
    private r d;
    private boolean e;
    private static String z;
    
    public f() {
        super(flaxchat.a.c.b());
        this.a = 100;
        this.b = 100;
        this.addMouseListener(this);
        this.d = new r(this, 3000);
    }
    
    public void paint(final Graphics graphics) {
        final Image f = flaxchat.d.d.f(flaxchat.a.f.z);
        if (f != null) {
            final Dimension size = this.getSize();
            graphics.drawImage(f, 0, 0, size.width, size.height, this);
        }
        flaxchat.a.c.a(graphics, this.c, 2, 0, this.getSize().height);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void a() {
        this.setVisible(this.e = false);
    }
    
    public void run() {
        this.a();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    static {
        f.z = z(z("9$/U:\"'=@'"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'U';
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
                    c2 = 'M';
                    break;
                }
                case 1: {
                    c2 = 'K';
                    break;
                }
                case 2: {
                    c2 = '_';
                    break;
                }
                case 3: {
                    c2 = '!';
                    break;
                }
                default: {
                    c2 = 'U';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
