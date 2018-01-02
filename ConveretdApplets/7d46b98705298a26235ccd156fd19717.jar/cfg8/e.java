// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Panel;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Point;

final class e extends d
{
    private Point g;
    private Image h;
    private String i;
    private static String[] z;
    
    public e(final ItemApplet itemApplet) {
        super(itemApplet);
        this.g = null;
        this.h = null;
        this.i = "";
        this.setLayout(null);
        this.enableEvents(48L);
    }
    
    protected void a(final x x) {
        if (x.c(e.z[1])) {
            this.h = super.e.a(String.valueOf(super.a).concat(String.valueOf(x.d(e.z[1]))));
        }
        this.i = x.d(e.z[0]);
    }
    
    protected void a() {
        this.repaint(0L);
    }
    
    protected Font b(final String s) {
        String s2 = s;
        if (RotationImageFilter.a == 0) {
            if (s.length() < 1) {
                return null;
            }
            s2 = s;
        }
        return cfg8.g.a(s2, super.f.i());
    }
    
    protected void b(final Graphics graphics) {
        final int i = RotationImageFilter.a;
        final Font b = this.b(this.i);
        e e = this;
        Label_0039: {
            if (i == 0) {
                if (this.h == null) {
                    break Label_0039;
                }
                e = this;
            }
            e.b.addImage(this.h, 0);
        }
        int n = 0;
        do {
            final q a = super.f.a(n++);
            Label_0084: {
                if (a == null) {
                    if (i != 0) {
                        break Label_0084;
                    }
                    if (i == 0) {
                        break;
                    }
                }
                if (!a.m() && i == 0) {
                    continue;
                }
            }
            super.b.addImage(a.n(), n);
        } while (i == 0);
        try {
            super.b.waitForAll();
        }
        catch (InterruptedException ex) {}
        final Font font = graphics.getFont();
        if (b != null) {
            graphics.setFont(b);
        }
        final Rectangle bounds = this.getBounds();
        graphics.clearRect(0, 0, bounds.width, bounds.height);
        if (i == 0) {
            if (this.h != null) {
                final Rectangle b2 = super.f.b();
                final MediaTracker b3 = super.b;
                if (i == 0) {
                    if (!b3.isErrorID(0)) {
                        graphics.drawImage(this.h, b2.x, b2.y, b2.width, b2.height, this);
                    }
                    final MediaTracker b4 = super.b;
                }
                b3.removeImage(this.h, 0);
            }
            n = 0;
        }
        graphics.setColor(Color.black);
        do {
            final q a2 = super.f.a(n++);
            Label_0278: {
                if (a2 == null) {
                    if (i != 0) {
                        break Label_0278;
                    }
                    if (i == 0) {
                        break;
                    }
                }
                a2.a(graphics, this);
            }
            if (a2.m()) {
                super.b.removeImage(a2.n(), n);
            }
        } while (i == 0);
        graphics.setFont(font);
    }
    
    q a(final Point point) {
        final int a = RotationImageFilter.a;
        e e = this;
        if (a == 0) {
            if (super.f == null) {
                return null;
            }
            e = this;
        }
        final Rectangle bounds = e.getBounds();
        int i = super.f.d();
        while (i >= 0) {
            final q a2;
            final q q = a2 = super.f.a(i--);
            Label_0140: {
                final q q2;
                Label_0095: {
                    Label_0090: {
                        if (a != 0 || a2 != null) {
                            final boolean k = a2.k();
                            final boolean b = false;
                            if (a == 0) {
                                if (k == b) {
                                    break Label_0090;
                                }
                                q2 = q;
                                if (a != 0) {
                                    break Label_0095;
                                }
                                q2.F();
                            }
                            if (k != b) {
                                break Label_0095;
                            }
                        }
                    }
                    if (a == 0) {
                        break Label_0140;
                    }
                }
                final Rectangle j = q2.i();
                j.setLocation(bounds.x + j.x, bounds.y + j.y);
                if (j.contains(point)) {
                    return q;
                }
            }
            if (a != 0) {
                break;
            }
        }
        return null;
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        final int a = RotationImageFilter.a;
        super.processMouseEvent(mouseEvent);
        final int id = mouseEvent.getID();
        final int n = 502;
        if (a == 0) {
            if (id != n) {
                return;
            }
            mouseEvent.getClickCount();
        }
        if (id > n) {
            final q a2;
            final q q = a2 = this.a(mouseEvent.getPoint());
            Label_0112: {
                if (a != 0 || a2 != null) {
                    Label_0099: {
                        if (a2.c().length() < 1) {
                            super.f.a(q.b(), q.d());
                            if (a == 0) {
                                break Label_0099;
                            }
                        }
                        super.f.a(null, -1);
                        super.f.l(q.c());
                    }
                    if (a == 0) {
                        break Label_0112;
                    }
                }
                super.f.a(null, -1);
            }
            super.f.e();
            super.f.repaint(0L);
            final q q2 = q;
            if ((a != 0 || q2 != null) && q2.c().length() < 1) {
                this.repaint(0L);
            }
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 503) {
            this.setCursor(new Cursor((this.a(mouseEvent.getPoint()) != null) ? 12 : 0));
            if (RotationImageFilter.a == 0) {
                return;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "D\u0019\u000e]".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\"';
                            break;
                        }
                        case 1: {
                            c2 = 'v';
                            break;
                        }
                        case 2: {
                            c2 = '`';
                            break;
                        }
                        case 3: {
                            c2 = ')';
                            break;
                        }
                        default: {
                            c2 = 'F';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "K\u001b\u0007".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\"';
                            break;
                        }
                        case 1: {
                            c4 = 'v';
                            break;
                        }
                        case 2: {
                            c4 = '`';
                            break;
                        }
                        case 3: {
                            c4 = ')';
                            break;
                        }
                        default: {
                            c4 = 'F';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                e.z = z;
                return;
            }
            continue;
        }
    }
}
