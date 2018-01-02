// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Image;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Canvas;

public final class az extends Canvas implements Runnable
{
    private boolean d;
    private Graphics a;
    public boolean a;
    private boolean e;
    public Font a;
    public Color a;
    public Color b;
    public int a;
    private int d;
    private int e;
    private int f;
    private Vector a;
    private Image a;
    public Thread a;
    public int b;
    public String a;
    public int c;
    public boolean b;
    public boolean c;
    private bh a;
    
    public final void a(final int n, final int n2) {
        final Dimension size;
        if (((size = this.size()).height != n2 || size.width != n) && n > 0 && n2 > 0) {
            this.resize(n, n2);
            az az = null;
            for (Component parent = this; parent != null && !(az instanceof Window); az = (az)parent, parent = ((az)parent).getParent()) {
                ((az)parent).invalidate();
            }
            az.validate();
        }
    }
    
    public final boolean mouseUp(final Event event, final int n, int i) {
        ba ba;
        for (i = 0; i < this.a.size(); ++i) {
            if ((ba = this.a.elementAt(i)).a(this.f, this.f + this.a) && ba.a != null && ba.a - this.f < n && ba.a + ba.b - this.f > n && n > 0) {
                this.a.a(ba.a, "_blank");
                while (ba.e > 0) {
                    if (n >= ba.a[2 * this.e] && n <= ba.a[2 * this.e + 1]) {
                        break;
                    }
                    ++this.e;
                }
                break;
            }
        }
        return true;
    }
    
    public final boolean mouseMove(final Event event, int i, final int n) {
        if (this.a) {
            this.d = true;
            final az az = this;
            final int n2 = i;
            this = az;
            i = 0;
            while (i < this.a.size()) {
                final ba ba;
                if ((ba = this.a.elementAt(i)).a(this.f, this.f + this.a) && ba.e > 0 && ba.a - this.f < n2 && ba.a + ba.b - this.f > n2 && n2 > 0) {
                    i = 0;
                    final FontMetrics fontMetrics = this.getFontMetrics((ba.a() != null) ? ba.a() : this.a);
                    final int n3 = n2 - (ba.a - this.f);
                    final String a = ba.a;
                    int n4 = 0;
                    int j;
                    int n5 = (j = a.length()) / 2;
                    if (n3 > fontMetrics.stringWidth(a.substring(0, j))) {
                        break;
                    }
                    while (j > n4 + 1) {
                        if (fontMetrics.stringWidth(a.substring(0, n5)) < n3) {
                            n4 = n5;
                        }
                        else {
                            j = n5;
                        }
                        n5 = (n4 + j) / 2;
                    }
                    final int n6;
                    if ((n6 = n5) != -1) {
                        for (int k = 0; k < ba.e; ++k) {
                            if (n6 >= ba.a[k << 1] && n6 <= ba.a[(k << 1) + 1]) {
                                i = 1;
                                break;
                            }
                        }
                        try {
                            if (i != 0 && !this.e) {
                                this.e = true;
                                this.setCursor(Cursor.getPredefinedCursor(12));
                            }
                            else if (i == 0) {
                                this.e = false;
                                this.setCursor(Cursor.getPredefinedCursor(0));
                            }
                        }
                        catch (Throwable t) {}
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.d = false;
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        return true;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.a != null) {
            if (this.size().height != this.d || this.size().width != this.a) {
                this.d = this.size().height;
                final int width = this.size().width;
                this.a = width;
                this.e = width;
                this.c = true;
            }
            else {
                final FontMetrics fontMetrics = this.a.getFontMetrics();
                final Dimension size = this.size();
                this.a.setColor(this.b);
                this.a.fillRect(0, 0, this.a, this.d);
                this.a.setColor(this.a);
                this.a.setFont(this.a);
                for (int i = 0; i < this.a.size(); ++i) {
                    final ba ba;
                    if ((ba = this.a.elementAt(i)).a(this.f, this.f + this.a)) {
                        int n = ba.a - this.f;
                        final Font a;
                        if ((a = ba.a()) == null) {
                            this.a.setFont(this.a);
                        }
                        else {
                            this.a.setFont(a);
                        }
                        final int n2 = ba.d + (size.height - ba.c) / 2;
                        final ba ba2;
                        if ((ba2 = ba).a != null && ba2.a.b != null) {
                            this.a.setColor(ba.a.b);
                            this.a.fillRect(n, 1, ba.b, ba.c);
                        }
                        this.a.setColor(ba.a() ? ba.a.a : this.a);
                        int n3 = 0;
                        final int length = ba.a.length();
                        int j = 0;
                        while (j < ba.e) {
                            int n4 = ba.a[j << 1];
                            int n5;
                            if ((n5 = ba.a[(j << 1) + 1]) > length) {
                                n5 = length;
                            }
                            else {
                                ++j;
                            }
                            if (n4 < n3) {
                                n4 = n3;
                            }
                            if (n3 >= n5 || length <= n4) {
                                break;
                            }
                            if (n4 != n3) {
                                while (n3 < length && ba.a.charAt(n3) == ' ') {
                                    ++n3;
                                }
                                String s;
                                try {
                                    s = ba.a.substring(n3, n4);
                                }
                                catch (StringIndexOutOfBoundsException ex) {
                                    s = ba.a.substring(n3, n3);
                                }
                                this.a.drawString(s, n, n2);
                                n += fontMetrics.stringWidth(s);
                            }
                            this.a.setColor(Color.blue);
                            final String substring = ba.a.substring(n4, n5);
                            final int stringWidth = fontMetrics.stringWidth(substring);
                            this.a.drawLine(n, n2 + 1, n + stringWidth, n2 + 1);
                            this.a.setColor(ba.a() ? ba.a.a : this.a);
                            this.a.drawString(substring, n + 1, n2);
                            this.a.drawString(substring, n - 1, n2);
                            this.a.setColor(Color.blue);
                            this.a.drawString(substring, n, n2);
                            n += stringWidth;
                            n3 = n5;
                        }
                        final String substring2;
                        if (n3 != length && (substring2 = ba.a.substring(n3, length)) != null) {
                            this.a.drawString(substring2, n, n2);
                        }
                    }
                }
            }
        }
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public final void a() {
        try {
            if (this.a != null) {
                Label_0059: {
                    if (this.a.isAlive()) {
                        this.a.interrupt();
                        synchronized (this.a) {
                            try {
                                this.a.wait();
                            }
                            catch (InterruptedException ex) {}
                            break Label_0059;
                        }
                    }
                    this.a.stop();
                }
                final az az;
                az.a = null;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final void run() {
        this.a = new Font(this.a, 0, this.b);
        this.setBackground(this.b);
        while (true) {
            if (this.c) {
                this.setBackground(this.b);
                this.a.removeAllElements();
                try {
                    for (int i = 0; i < this.a.s.a(); ++i) {
                        final aG ag;
                        if ((ag = (aG)this.a.s.a(i)) != null && !ag.a(8) && ag.a(3)) {
                            this.a(ag, ag.c);
                            this.a(null, "       ");
                        }
                    }
                    if (this.a.size() == 0) {
                        this.setVisible(false);
                        this.a(false);
                        this.a();
                        return;
                    }
                }
                finally {
                    if (this.a.size() == 0) {
                        this.setVisible(false);
                        this.a(false);
                        this.a();
                        return;
                    }
                }
                this.a(this.a, this.d);
                if (this.a != null) {
                    this.a.dispose();
                    this.a = null;
                }
                if (this.a != null) {
                    try {
                        this.a.flush();
                    }
                    catch (SecurityException ex2) {}
                }
                this.a = this.createImage(this.a, this.d);
                this.a = this.a.getGraphics();
                this.c = false;
            }
            if (this.b) {
                if (!this.d) {
                    ++this.f;
                }
                if (this.f >= this.e) {
                    this.f = 0;
                }
            }
            else {
                if (!this.d) {
                    --this.f;
                }
                if (this.f <= 0) {
                    this.f = this.e;
                }
            }
            this.repaint();
            if (this.c > 0) {
                try {
                    Thread.sleep(this.c);
                }
                catch (InterruptedException ex) {
                    System.out.println("Error in synchronized wait(): " + ex);
                }
            }
        }
    }
    
    private void a(final boolean b) {
        if (this.getParent().isShowing()) {
            this.getParent().hide();
        }
        az az = null;
        for (Component parent = this; parent != null && !(az instanceof Window); az = (az)parent, parent = ((az)parent).getParent()) {
            ((az)parent).invalidate();
        }
        az.validate();
    }
    
    private void a(final aG ag, final String s) {
        if (s != null) {
            final ba ba;
            FontMetrics fontMetrics;
            if ((ba = new ba(this, ag, s, this.e)).a() != null) {
                ba.b = (fontMetrics = this.getFontMetrics(ba.a())).stringWidth(ba.a);
            }
            else {
                fontMetrics = this.getFontMetrics(this.a);
            }
            if (this.d < fontMetrics.getHeight()) {
                this.d = fontMetrics.getHeight();
                this.a(this.size().width, this.d);
            }
            ba.c = fontMetrics.getHeight();
            ba.d = fontMetrics.getAscent();
            this.a.addElement(ba);
            this.e += ba.b;
        }
    }
    
    public final void resize(final int a, final int d) {
        super.resize(a, d);
        this.a = a;
        this.d = d;
    }
    
    public az(final bl a) {
        this.a = a;
        this.b = 12;
        this.a = "";
        this.c = 20;
        this.b = false;
        this.c = true;
        this.a = new Thread(this);
        this.a = null;
        this.a = new Vector();
        this.f = 0;
        this.b = Color.black;
        this.a = Color.white;
        this.a = false;
        this.d = false;
        this.e = false;
    }
}
