// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public final class ao extends Canvas
{
    private ac a;
    private Image a;
    private Image b;
    private Graphics a;
    private Graphics b;
    private int a;
    private int b;
    private boolean a;
    
    public ao() {
        this.a = false;
    }
    
    protected ao(final ac a) {
        this.a = false;
        this.a = a;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        synchronized (this.a.a.a) {
            Label_0652: {
                try {
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (this.a == null) {
                        if (this.a <= 0 || this.b <= 0) {
                            return;
                        }
                        this.a = this.createImage(this.a, this.b);
                        this.a = this.a.getGraphics();
                        this.b = this.createImage(this.a, this.b);
                        this.b = this.b.getGraphics();
                        this.a.j();
                    }
                    final ac a = this.a;
                    final Graphics a2 = this.a;
                    final ac ac = a;
                    a.a.a = a2;
                    ac.a.a = 0;
                    ac.a.b = 0;
                    ac.a(ac.a);
                    final Rectangle clipRect;
                    if ((clipRect = graphics.getClipRect()) == null) {
                        return;
                    }
                    final Graphics create;
                    (create = this.b.create(clipRect.x, clipRect.y, clipRect.width, clipRect.height)).drawImage(this.a, -clipRect.x, -clipRect.y, null);
                    this.a.a(create, clipRect.x, clipRect.y, clipRect.x + clipRect.width - 1, clipRect.y + clipRect.height - 1);
                    graphics.drawImage(this.b, 0, 0, null);
                    create.dispose();
                    Toolkit.getDefaultToolkit().sync();
                    if (this.a && (clipRect.width > 1 || clipRect.height > 1) && null != graphics) {
                        graphics.setColor(Color.blue);
                        final String string = Integer.toString(this.a.a.a());
                        graphics.fillRect(clipRect.x, clipRect.y, 36, 12);
                        graphics.setColor(Color.yellow);
                        graphics.drawString(string, clipRect.x, clipRect.y + 10);
                        final int n = (int)(System.currentTimeMillis() - currentTimeMillis);
                        final bb a3;
                        final bb bb = a3 = this.a.a;
                        bb.a -= a3.a[a3.b];
                        a3.a[a3.b] = n;
                        final bb bb2 = a3;
                        bb2.a += n;
                        a3.b = (a3.b + 1) % a3.a.length;
                        final bb bb3 = a3;
                        ++bb3.c;
                    }
                    if (this.a.a.a) {
                        return;
                    }
                    this.a.a.a = true;
                    final cq a4;
                    final cq cq = a4 = this.a.a.a;
                    synchronized (cq) {
                        if (a4.a) {
                            break Label_0652;
                        }
                        a4.a.removeAllElements();
                        a4.a.a(a4.a);
                        a4.a.removeAllElements();
                    }
                    for (int i = 0; i < a4.a.size(); ++i) {
                        final cx cx;
                        (cx = a4.a.elementAt(i)).a.a(cx.a, cx.a);
                        cx.a = null;
                        cx.a = null;
                        y.cx.a.a(cx);
                    }
                }
                catch (Throwable t) {
                    this.a.a.a(t);
                }
            }
            this.a.a.a = false;
        }
    }
    
    public final void reshape(final int n, final int n2, final int a, final int b) {
        synchronized (this.a.a.a) {
            try {
                this.a = null;
                super.reshape(n, n2, this.a = a, this.b = b);
                this.a.b(a, b);
            }
            catch (Throwable t) {
                this.a.a.a(t);
            }
        }
    }
    
    public final void addNotify() {
        synchronized (this.a.a.a) {
            try {
                super.addNotify();
                this.a.b();
            }
            catch (Throwable t) {
                this.a.a.a(t);
            }
        }
    }
    
    public final Dimension preferredSize() {
        Dimension a = null;
        synchronized (this.a.a.a) {
            try {
                a = this.a.a();
            }
            catch (Throwable a) {
                this.a.a.a((Throwable)a);
                a = new Dimension(1, 1);
            }
        }
        return a;
    }
    
    public final Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public final boolean handleEvent(final Event event) {
        synchronized (this.a.a.a) {
            try {
                if (event.id == 401 && event.key == 17 && (event.modifiers & 0x2) != 0x0) {
                    this.a = !this.a;
                }
                else {
                    final ac a = this.a;
                    Object target = null;
                    final u b = a.b;
                    Label_0783: {
                        switch (event.id) {
                            case 501: {
                                final ac ac = a;
                                final u a2 = a.a(event.x, event.y);
                                ac.a = a2;
                                target = a2;
                                if (a.b && b != null && b != target) {
                                    b.a(new Event(b, 1005, null));
                                    a.b = null;
                                }
                                a.b(event);
                                break;
                            }
                            case 503: {
                                target = a.a(event.x, event.y);
                                if (a.c != target) {
                                    if (a.c != null) {
                                        final Event event3;
                                        final Event event2 = event3 = new Event(a.c, event.when, 505, a.a, a.b, event.key, event.modifiers);
                                        event2.x -= a.c.a(a);
                                        final Event event4 = event3;
                                        event4.y -= a.c.b(a);
                                        a.c.a(event3);
                                        a.b(event3);
                                    }
                                    final Event event6;
                                    final Event event5 = event6 = new Event(target, event.when, 504, event.x, event.y, event.key, event.modifiers);
                                    event5.x -= ((u)target).a(a);
                                    final Event event7 = event6;
                                    event7.y -= ((u)target).b(a);
                                    ((u)target).a(event6);
                                    a.b(event6);
                                    a.c = (u)target;
                                    a.a = event.x;
                                    a.b = event.y;
                                    break;
                                }
                                break;
                            }
                            case 505: {
                                target = a.c;
                                a.c = null;
                                a.b(event);
                                break;
                            }
                            case 504: {
                                target = a.a(event.x, event.y);
                                a.c = (u)target;
                                a.a = event.x;
                                a.b = event.y;
                                a.b(event);
                                break;
                            }
                            case 506: {
                                target = a.a;
                                break;
                            }
                            case 502: {
                                target = a.a;
                                a.a = null;
                                a.b(event);
                                break;
                            }
                            case 1005: {
                                if (!a.a) {
                                    break;
                                }
                                a.a = false;
                                if (b != null) {
                                    target = (event.target = b);
                                    break;
                                }
                                break;
                            }
                            case 1004: {
                                if (!a.a) {
                                    a.a = true;
                                    if (a.b != null) {
                                        target = (event.target = a.b);
                                    }
                                }
                                a.a.b = a;
                                break;
                            }
                            case 401:
                            case 402:
                            case 403:
                            case 404: {
                                target = a.b;
                                final ac ac2 = a;
                                if (null == ac2.a) {
                                    break;
                                }
                                final int modifiers = event.modifiers;
                                final int key = event.key;
                                switch (event.id) {
                                    case 401:
                                    case 403: {
                                        ac2.a.keyPressed(new KeyEvent(ac2.a, 401, System.currentTimeMillis(), modifiers, key));
                                        break Label_0783;
                                    }
                                    case 402:
                                    case 404: {
                                        ac2.a.keyReleased(new KeyEvent(ac2.a, 402, System.currentTimeMillis(), modifiers, key));
                                        break Label_0783;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (target != null) {
                        event.x -= ((u)target).a(a);
                        event.y -= ((u)target).b(a);
                        ((u)(event.target = target)).a(event);
                    }
                    else {
                        a.a(event);
                    }
                }
            }
            catch (Throwable t) {
                this.a.a.a(t);
            }
        }
        return true;
    }
}
