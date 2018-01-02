// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Window;
import java.awt.Dialog;
import java.applet.Applet;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class mb implements MouseListener, MouseMotionListener, Runnable
{
    private Container a;
    private a b;
    private Thread c;
    int d;
    int e;
    int f;
    int g;
    long h;
    int i;
    int j;
    eb k;
    Dimension l;
    bb m;
    int n;
    int o;
    boolean p;
    boolean q;
    boolean r;
    int s;
    int t;
    Component u;
    Container v;
    private static String z;
    
    public void a() {
        this.c.interrupt();
        this.c = null;
    }
    
    public mb() {
        this.d = 0;
        this.e = 0;
        this.f = 500;
        this.g = 4000;
        this.h = 0L;
        this.i = 0;
        this.j = 16;
        this.k = new eb();
        this.p = false;
        this.q = false;
        this.r = true;
        this.s = 0;
        this.t = 0;
        this.u = null;
        (this.c = new Thread(this)).setPriority(5);
        this.c.start();
    }
    
    synchronized void b() {
    }
    
    public void run() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        while (this.c != null) {
            final boolean interrupted = this.c.isInterrupted();
            Label_0312: {
                mb mb = null;
                Label_0037: {
                    if (!u) {
                        if (interrupted) {
                            break;
                        }
                        mb = this;
                        if (u) {
                            break Label_0037;
                        }
                        final boolean p = this.p;
                    }
                    if (!interrupted) {
                        break Label_0312;
                    }
                    mb = this;
                }
                final long n3;
                if (mb.m != null) {
                    final long n = lcmp(System.currentTimeMillis() - this.h, (long)this.f);
                    Label_0280: {
                        if (!u) {
                            if (n >= 0) {
                                final long n2 = lcmp(System.currentTimeMillis() - this.h, (long)(this.f + this.g));
                                if (u) {
                                    break Label_0280;
                                }
                                if (n2 <= 0) {
                                    final long q;
                                    final boolean b = (q = (this.q ? 1 : 0)) != 0L;
                                    if (u) {
                                        break Label_0280;
                                    }
                                    if (!b) {
                                        this.k.a(this.m);
                                        this.k.setBounds(this.d + this.i, this.e + this.j, this.l.width, this.l.height);
                                        System.out.println(com.easypano.tourweaver.b.mb.z + (this.d + this.i) + " " + this.i + " " + this.j + " " + this.e + " " + this.k.getBounds());
                                        this.k.repaint();
                                        this.k.setVisible(true);
                                        this.q = true;
                                    }
                                }
                            }
                            n3 = System.currentTimeMillis() - this.h - this.f;
                            if (u) {
                                break Label_0312;
                            }
                            long q = lcmp(n3, (long)this.g);
                        }
                    }
                    if (n >= 0) {
                        mb mb2 = this;
                        if (!u) {
                            if (!this.q) {
                                break Label_0312;
                            }
                            this.k.setVisible(false);
                            this.q = false;
                            mb2 = this;
                        }
                        mb2.p = false;
                    }
                }
                try {
                    Thread.sleep(n3);
                    continue;
                }
                catch (InterruptedException ex) {
                    if (!u) {
                        continue;
                    }
                }
            }
            break;
        }
    }
    
    public void a(final lb lb) {
        lb lb2 = lb;
        if (!com.easypano.tourweaver.b.f.u) {
            if (!(lb instanceof Component)) {
                return;
            }
            lb2 = lb;
        }
        final Component component = (Component)lb2;
        component.removeMouseListener(this);
        component.addMouseListener(this);
        component.removeMouseMotionListener(this);
        component.addMouseMotionListener(this);
    }
    
    private void a(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        if (!(mouseEvent.getSource() instanceof a)) {
            return;
        }
        this.n = 0;
        this.o = 0;
        this.b = (a)mouseEvent.getSource();
        this.m = this.b.a();
        mb mb = this;
        if (!u) {
            if (this.m == null) {
                return;
            }
            this.l = this.m.getPreferredSize();
            mb = this;
        }
        mb.a = this.b;
        while (true) {
            while (this.a != null) {
                Component component;
                final Container container = (Container)(component = this.a);
                if (!u && !u) {
                    if (container instanceof Frame) {
                        break;
                    }
                    final Container container2 = (Container)(component = this.a);
                    if (!u) {
                        if (container2 instanceof Applet) {
                            break;
                        }
                        boolean b2;
                        final boolean b = b2 = (this.a instanceof Dialog);
                        if (!u) {
                            if (b && !u) {
                                break;
                            }
                            this.n += this.a.getBounds().x;
                            this.o += this.a.getBounds().y;
                            final boolean b3;
                            b2 = (b3 = (this.a instanceof k));
                        }
                        mb mb2 = null;
                        Label_0215: {
                            if (!u) {
                                if (b) {
                                    this.p = false;
                                    if (!u) {
                                        break;
                                    }
                                }
                                mb2 = this;
                                if (u) {
                                    break Label_0215;
                                }
                                b2 = (this.a instanceof Window);
                            }
                            if (b2 && !u) {
                                break;
                            }
                            mb2 = this;
                        }
                        mb2.a = this.a.getParent();
                        if (u) {
                            break;
                        }
                        continue;
                    }
                }
                if (!u) {
                    if (container == null) {
                        return;
                    }
                    component = this.a.getComponent(0);
                }
                if (!u) {
                    if (component != this.k) {
                        this.a.add(this.k, 0);
                    }
                }
                return;
            }
            final Container a;
            Component component = a = this.a;
            continue;
        }
    }
    
    private k b(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Component component = (Component)mouseEvent.getSource();
        this.s = mouseEvent.getX();
        this.t = mouseEvent.getY();
        k k = null;
        Window window;
        for (Container parent = (Container)component; parent != null; parent = window.getParent()) {
            boolean b2;
            final boolean b = b2 = (parent instanceof ib);
            if (!u) {
                if (b) {
                    this.v = parent;
                    k = ((ib)parent).b();
                    break;
                }
                final boolean b3;
                b2 = (b3 = (parent instanceof jb));
            }
            if (!u) {
                if (b) {
                    this.v = parent;
                    k = ((jb)parent).a();
                    break;
                }
                final cb cb = (cb)(window = (Window)parent);
                if (u) {
                    continue;
                }
                b2 = (cb instanceof cb);
            }
            if (b2) {
                this.v = parent;
                k = ((cb)parent).a();
                break;
            }
            this.s += parent.getBounds().x;
            this.t += parent.getBounds().y;
            window = (Window)parent;
        }
        return k;
    }
    
    public synchronized void mouseEntered(final MouseEvent mouseEvent) {
        this.u = (Component)mouseEvent.getSource();
        this.h = System.currentTimeMillis();
        this.a(mouseEvent);
        this.d = this.n + mouseEvent.getX();
        this.e = this.o + mouseEvent.getY();
        this.p = true;
    }
    
    public synchronized void mouseExited(final MouseEvent mouseEvent) {
        this.k.setVisible(false);
        this.q = false;
        this.p = false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        this.k.setVisible(false);
        this.q = false;
        mb mb = this;
        if (!u) {
            this.p = false;
            if (mouseEvent.getSource() instanceof i) {
                return;
            }
            mb = this;
        }
        final k b = mb.b(mouseEvent);
        if (this.v != null) {
            final int modifiers = mouseEvent.getModifiers();
            Label_0159: {
                if (!u) {
                    if (modifiers != 26) {
                        final int n = modifiers;
                        final int n2 = 8;
                        if (u || n != n2) {
                            final int n3 = n & n2;
                            if (!u && n3 != 0) {
                                b.hide();
                                if (u) {
                                    goto Label_0129;
                                }
                                break Label_0159;
                            }
                            else {
                                if (n3 != 0) {
                                    b.hide();
                                    b.a(this.v, this.s, this.t);
                                    b.repaint();
                                }
                                break Label_0159;
                            }
                        }
                    }
                    b.hide();
                    b.a(this.v, this.s, this.t);
                    b.repaint();
                }
                if (u) {
                    goto Label_0108;
                }
            }
            this.v.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.k.setVisible(false);
        this.q = false;
        this.p = false;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.u) {
            this.d = this.n + mouseEvent.getX();
            this.e = this.o + mouseEvent.getY();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void c() {
        this.c.interrupt();
        this.c = null;
    }
    
    static {
        final char[] charArray = "T-ND)fbHO9W-".toCharArray();
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
                            c2 = '$';
                            break;
                        }
                        case 1: {
                            c2 = '\r';
                            break;
                        }
                        case 2: {
                            c2 = '=';
                            break;
                        }
                        case 3: {
                            c2 = '!';
                            break;
                        }
                        default: {
                            c2 = ']';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                mb.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
