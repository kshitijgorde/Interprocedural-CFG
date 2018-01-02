// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.gui;

import ru.zhuk.graphics.b;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.Component;

public class g extends Component
{
    private String e;
    private int b;
    private Vector c;
    private int d;
    private boolean j;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean a;
    private boolean f;
    
    public g() {
        this.e = "";
        this.b = 0;
        this.d = 0;
        this.j = true;
        this.enableEvents(12L);
        this.setSize(1, 1);
        this.i = true;
    }
    
    public void a(final String s) {
        this.e = ((s != null) ? s : "");
        this.b = this.e.length();
        this.c = null;
        this.i = true;
        this.repaint();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.c = null;
        this.i = true;
        this.repaint();
    }
    
    public void a(final int d) {
        this.d = d;
        this.repaint();
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public void a(final boolean f) {
        this.f = f;
        this.repaint();
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        switch (focusEvent.getID()) {
            case 1004: {
                this.g = true;
                break;
            }
            case 1005: {
                this.g = false;
                break;
            }
        }
        this.repaint();
        super.processFocusEvent(focusEvent);
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        if (!keyEvent.isConsumed() && this.isEnabled() && this.c != null) {
            if (this.h) {
                this.a("");
                this.h = false;
            }
            String e = new String(this.e);
            Label_0715: {
                if (keyEvent.getID() == 400) {
                    char keyChar = keyEvent.getKeyChar();
                    switch (keyChar) {
                        case 8: {
                            if (e.length() > 0 && this.b > 0) {
                                e = String.valueOf(String.valueOf(e.substring(0, this.b - 1))).concat(String.valueOf(String.valueOf(e.substring(this.b, e.length()))));
                                --this.b;
                            }
                            break Label_0715;
                        }
                        case 65535: {
                            break Label_0715;
                        }
                        case 10:
                        case 13: {
                            keyChar = '\n';
                            break;
                        }
                    }
                    e = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(e.substring(0, this.b)))).append(keyChar).append(e.substring(this.b, e.length()))));
                    ++this.b;
                }
                else if (keyEvent.getID() == 401) {
                    switch (keyEvent.getKeyCode()) {
                        case 127: {
                            if (e.length() > 0 && this.b < e.length()) {
                                e = String.valueOf(String.valueOf(e.substring(0, this.b))).concat(String.valueOf(String.valueOf(e.substring(this.b + 1, e.length()))));
                                break;
                            }
                            break;
                        }
                        case 37: {
                            if (this.b > 0) {
                                --this.b;
                                break;
                            }
                            break;
                        }
                        case 39: {
                            if (this.b < this.e.length()) {
                                ++this.b;
                                break;
                            }
                            break;
                        }
                        case 33: {
                            this.b = 0;
                            break;
                        }
                        case 34: {
                            this.b = this.e.length();
                            break;
                        }
                        case 36: {
                            final Point a = a(this.c, this.b);
                            a.x = 0;
                            this.b = a(this.c, a);
                            break;
                        }
                        case 35: {
                            final Point a2 = a(this.c, this.b);
                            a2.x = ((String)this.c.elementAt(a2.y)).length();
                            this.b = a(this.c, a2);
                            break;
                        }
                        case 38: {
                            final Point a3 = a(this.c, this.b);
                            if (a3.y > 0) {
                                final Point point = a3;
                                --point.y;
                                a3.x = Math.min(Math.max(a3.x, 0), ((String)this.c.elementAt(a3.y)).length());
                                this.b = a(this.c, a3);
                                break;
                            }
                            break;
                        }
                        case 40: {
                            final Point a4 = a(this.c, this.b);
                            if (a4.y < this.c.size() - 1) {
                                final Point point2 = a4;
                                ++point2.y;
                                a4.x = Math.min(Math.max(a4.x, 0), ((String)this.c.elementAt(a4.y)).length());
                                this.b = a(this.c, a4);
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            if (!e.equals(this.e)) {
                this.e = e;
                this.c = null;
                this.i = true;
            }
            this.repaint();
        }
        super.processKeyEvent(keyEvent);
    }
    
    private static Vector a(final String s, final FontMetrics fontMetrics, final int n) {
        final Vector<String> vector = new Vector<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != '\n') {
                sb.append(char1);
                if (fontMetrics.stringWidth(sb.toString()) > n && n > 0) {
                    int n2;
                    for (n2 = sb.length() - 1; !Character.isWhitespace(sb.charAt(n2)) && n2 > 1; --n2) {}
                    final String substring = sb.toString().substring(0, n2);
                    final String substring2 = sb.toString().substring(n2 + 1, sb.length());
                    vector.addElement(substring);
                    sb = new StringBuffer(substring2);
                }
            }
            else {
                vector.addElement(sb.toString());
                sb = new StringBuffer();
            }
        }
        vector.addElement(sb.toString());
        return vector;
    }
    
    private static Point a(final Vector vector, final int n) {
        int n2 = 0;
        for (int i = 0; i < vector.size(); ++i) {
            if (n2 == n) {
                return new Point(0, i);
            }
            final String s = vector.elementAt(i);
            for (int j = 0; j < s.length(); ++j) {
                if (++n2 == n) {
                    return new Point(j + 1, i);
                }
            }
            ++n2;
        }
        return null;
    }
    
    private static int a(final Vector vector, final Point point) {
        int n = 0;
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            for (int j = 0; j <= s.length(); ++j) {
                if (point.y == i && point.x == j) {
                    return n;
                }
                ++n;
            }
        }
        return n;
    }
    
    private Dimension a(final Graphics graphics) {
        final int size = graphics.getFont().getSize();
        final Dimension size2 = this.getSize();
        size2.height = Math.max(size2.height, (this.c.size() + 1) * size);
        if (!this.a) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            for (int i = 0; i < this.c.size(); ++i) {
                size2.width = Math.max(size2.height, fontMetrics.stringWidth(String.valueOf(String.valueOf(this.c.elementAt(i))).concat("WWW WWW")));
            }
            this.a = true;
        }
        return size2;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setFont(this.getFont());
        this.c = a(this.e, graphics.getFontMetrics(), this.j ? (size.width - 2) : -1);
        if (this.i) {
            this.i = false;
            this.setBounds(new Rectangle(this.getLocation(), this.a(graphics)));
        }
        if (this.c != null) {
            final Point a = a(this.c, this.b);
            final int size2 = graphics.getFont().getSize();
            int n = 1;
            final int n2 = size2;
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            for (int i = 0; i < this.c.size(); ++i) {
                final String s = this.c.elementAt(i);
                switch (this.d) {
                    case 2: {
                        n = size.width - 2 - fontMetrics.stringWidth(s);
                        break;
                    }
                    case 1: {
                        n = (size.width - 2 - fontMetrics.stringWidth(s)) / 2;
                        break;
                    }
                }
                graphics.setPaintMode();
                graphics.setColor(this.getForeground());
                graphics.drawString(s, n, n2 + size2 * i);
                if (this.f && this.g && a != null && a.y == i) {
                    final int n3 = n + fontMetrics.stringWidth(s.substring(0, a.x));
                    final int n4 = n2 + size2 * a.y;
                    graphics.setColor(Color.white);
                    graphics.setXORMode(Color.black);
                    graphics.drawLine(n3, n4 - size2, n3, n4);
                }
            }
        }
        if (this.f) {
            graphics.setColor(Color.white);
            graphics.setXORMode(Color.black);
            ru.zhuk.graphics.b.a(graphics, 0, 0, size.width - 1, size.height - 1, 0);
        }
    }
}
