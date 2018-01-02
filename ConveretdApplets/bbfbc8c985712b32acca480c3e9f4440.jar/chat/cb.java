// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

public final class cb extends x
{
    private int a;
    private boolean a;
    private boolean b;
    private Image a;
    private Graphics a;
    private int b;
    private int c;
    private ca a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503: {
                if (this.a) {
                    boolean b = false;
                    final aU a;
                    if ((a = this.a.a(event)) != null && a.d != -1) {
                        for (int i = 0; i < a.c; ++i) {
                            if (a.d >= a.b[i * 3] && a.d <= a.b[i * 3 + 1]) {
                                b = true;
                                break;
                            }
                        }
                    }
                    try {
                        if (b && !this.b) {
                            this.b = true;
                            this.setCursor(Cursor.getPredefinedCursor(12));
                        }
                        else if (!b) {
                            this.b = false;
                            this.setCursor(Cursor.getPredefinedCursor(0));
                        }
                    }
                    catch (Throwable t) {
                        this.a = false;
                    }
                    break;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.a = event.y;
                final aU a2;
                if ((a2 = this.a.a(event)) != null && a2.d != -1) {
                    for (int j = 0; j < a2.c; ++j) {
                        if (a2.d >= a2.b[j * 3] && a2.d <= a2.b[j * 3 + 1]) {
                            String s;
                            final String lowerCase = (s = a2.a.substring(a2.b[j * 3], a2.b[j * 3 + 1])).toLowerCase();
                            final int index = s.indexOf(64);
                            final int index2 = s.indexOf("://");
                            if (index > 0 && index2 == -1 && lowerCase.indexOf("mailto:") == -1) {
                                s = "mailto:" + s;
                            }
                            try {
                                this.postEvent(new Event(this.a, event.when, 1001, event.x, event.y, event.key, event.modifiers, new URL(s)));
                            }
                            catch (Exception ex) {}
                            break;
                        }
                    }
                }
                this.a.a = a2;
                return true;
            }
            case 502: {
                final aU a3;
                if ((a3 = this.a.a(event)) != null && a3 == this.a.a) {
                    this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, a3));
                    this.a.a = null;
                }
                return true;
            }
            case 506: {
                ((cn)(this.a.a = null)).setValue(this.a.a - (event.y - this.a));
                final int n;
                if ((n = this.a.a - this.a.a.getValue()) != 0) {
                    this.a.b(n);
                }
                this.a = event.y;
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x40) != 0x0) {
            return false;
        }
        if ((n & 0x20) != 0x0) {
            this.doLayout();
            this.repaint(6, 0, 24, this.size().height);
            return false;
        }
        return true;
    }
    
    public final synchronized void invalidate() {
        super.invalidate();
        this.a.a = false;
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.a.a) {
            this.a.b();
            this.a.a = true;
        }
        this.a(graphics, 0, this.size().height);
    }
    
    public final void a(final Graphics graphics, int n, final int n2) {
        if (this.a == null || this.size().height != this.b || this.size().width != this.c) {
            this.b = this.size().height;
            this.c = this.size().width;
            if (this.a != null) {
                try {
                    this.a.flush();
                }
                catch (SecurityException ex) {}
            }
            this.a = this.createImage(this.c, this.b);
            this.a = this.a.getGraphics();
        }
        if (this.a.b) {
            if (this.a.a == null) {
                this.a.a = this.a.a.a.b();
            }
            if (this.a.a != null) {
                final Dimension size = this.size();
                final Insets insets = this.a.insets();
                final int n3 = size.width - insets.right - insets.left + 30;
                n = size.height - insets.bottom - insets.top + 30;
                int width = this.a.a.getWidth(null);
                int height = this.a.a.getHeight(null);
                if (this.a.a.a.c()) {
                    height = height * n3 / width;
                    width = n3;
                }
                int i;
                for (i = this.a.b; i >= height; i -= height) {}
                this.a.clearRect(0, -i, width, height - i);
                this.a.drawImage(this.a.a, 0, -i, width, height, null);
                this.a.drawImage(this.a.a, 0, height - i, width, height, null);
                for (int j = 0; j <= n3 / width; ++j) {
                    for (int k = 0; k <= n / height; ++k) {
                        if (j + k > 0) {
                            this.a.copyArea(0, 0, width, height, j * width, k * height);
                        }
                    }
                }
            }
        }
        n = -this.a.a;
        for (int l = 0; l < this.a.c; ++l) {
            final aU a = this.a.a(l);
            if (n + a.a >= 0) {
                this.a.a(a, n, this.a);
            }
            if ((n += a.a) >= n2) {
                break;
            }
        }
        graphics.drawImage(this.a, 0, 0, null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void a() {
        if (this.a != null) {
            try {
                this.a.flush();
            }
            catch (SecurityException ex) {}
        }
        this.a = null;
        this.repaint();
    }
    
    cb(final ca a) {
        this.a = a;
        this.a = 0;
        this.a = true;
        this.b = false;
        this.a = null;
        this.a = null;
        this.b = 0;
        this.c = 0;
    }
}
