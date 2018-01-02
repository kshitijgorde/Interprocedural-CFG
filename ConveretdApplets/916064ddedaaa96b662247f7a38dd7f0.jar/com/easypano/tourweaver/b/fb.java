// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.util.Enumeration;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import com.easypano.tourweaver.d.e;
import java.awt.Panel;

class fb extends Panel implements e
{
    Image a;
    Graphics b;
    private static String z;
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        super.processKeyEvent(keyEvent);
        System.out.println(fb.z + keyEvent.getKeyChar());
    }
    
    public void paint(final Graphics graphics) {
        fb fb = this;
        if (!f.u) {
            if (this.a == null) {
                this.a = this.createImage(this.getBounds().width, this.getBounds().height);
                this.b = this.a.getGraphics();
            }
            this.b.setColor(this.getBackground());
            this.b.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
            fb = this;
        }
        fb.paint(this.b);
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public void addAttributes(final String s, final String s2) {
    }
    
    public void a(final Image image, final String s) {
        final boolean u = f.u;
        int i = 0;
        while (i < this.getComponentCount()) {
            final Component component = this.getComponent(i);
            if (!u) {
                if (component instanceof e) {
                    ((e)component).a(image, s);
                }
                ++i;
            }
            if (u) {
                break;
            }
        }
    }
    
    public void a(final e e) {
    }
    
    public e a(final String s) {
        return null;
    }
    
    public Enumeration d() {
        return null;
    }
    
    public void b(final e e) {
    }
    
    static {
        final char[] charArray = "\f0\u0014d4\u00110\u00030q\u000e;M\u0010\u0001\u0006;\b(q".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'g';
                            break;
                        }
                        case 1: {
                            c2 = 'U';
                            break;
                        }
                        case 2: {
                            c2 = 'm';
                            break;
                        }
                        case 3: {
                            c2 = 'D';
                            break;
                        }
                        default: {
                            c2 = 'Q';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                fb.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
