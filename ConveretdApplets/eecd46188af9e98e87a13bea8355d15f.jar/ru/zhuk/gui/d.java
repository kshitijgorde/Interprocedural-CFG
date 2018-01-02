// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class d extends MouseAdapter implements MouseMotionListener
{
    private Component c;
    private Point a;
    private int e;
    public static int d;
    private Dimension b;
    private e f;
    
    public d(final Component c) {
        this.e = -1;
        (this.c = c).addMouseListener(this);
        c.addMouseMotionListener(this);
    }
    
    private int a(final Point point) {
        final int x = point.x;
        final int y = point.y;
        final Dimension size = this.c.getSize();
        final int width = size.width;
        final int height = size.height;
        if (x >= 0 && x < ru.zhuk.gui.d.d && y >= 0 && y < ru.zhuk.gui.d.d) {
            return 0;
        }
        if (x >= width - ru.zhuk.gui.d.d && x < width && y >= 0 && y < ru.zhuk.gui.d.d) {
            return 2;
        }
        if (x >= width - ru.zhuk.gui.d.d && x < width && y >= height - ru.zhuk.gui.d.d && y < height) {
            return 4;
        }
        if (x >= 0 && x < ru.zhuk.gui.d.d && y >= height - ru.zhuk.gui.d.d && y < height) {
            return 6;
        }
        if (x >= ru.zhuk.gui.d.d && x < width - ru.zhuk.gui.d.d && y >= 0 && y < ru.zhuk.gui.d.d) {
            return 1;
        }
        if (x >= width - ru.zhuk.gui.d.d && x < width && y >= ru.zhuk.gui.d.d && y < height - ru.zhuk.gui.d.d) {
            return 3;
        }
        if (x >= ru.zhuk.gui.d.d && x < width - ru.zhuk.gui.d.d && y >= height - ru.zhuk.gui.d.d && y < height) {
            return 5;
        }
        if (x >= 0 && x < ru.zhuk.gui.d.d && y >= ru.zhuk.gui.d.d && y < height - ru.zhuk.gui.d.d) {
            return 7;
        }
        return -1;
    }
    
    private void a(final int n) {
        final int[] array = { 6, 8, 7, 11, 5, 9, 4, 10 };
        final Cursor cursor = (n >= 0 && n < array.length) ? Cursor.getPredefinedCursor(array[n]) : ((this.a != null) ? Cursor.getPredefinedCursor(1) : Cursor.getPredefinedCursor(12));
        if (cursor != this.c.getCursor()) {
            this.c.setCursor(cursor);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a(this.a(mouseEvent.getPoint()));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a = mouseEvent.getPoint();
        this.a(this.e = this.a(this.a));
        this.c.requestFocus();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.a == null) {
            return;
        }
        this.a(this.e);
        final int n = mouseEvent.getX() - this.a.x;
        final int n2 = mouseEvent.getY() - this.a.y;
        final Rectangle bounds = this.c.getBounds();
        final Rectangle a = a(bounds, n, n2, this.e);
        if (this.b != null) {
            this.c.getParent();
            this.a(a, this.e);
        }
        if (!a.equals(bounds)) {
            this.c.setBounds(a);
            if (this.e >= 2 && this.e <= 6) {
                int n3 = a.width - bounds.width;
                int n4 = a.height - bounds.height;
                if (this.e == 2) {
                    n4 = 0;
                }
                else if (this.e == 6) {
                    n3 = 0;
                }
                this.a.translate(n3, n4);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a = null;
        this.a(this.a(mouseEvent.getPoint()));
    }
    
    private static Rectangle a(final Rectangle rectangle, final int n, final int n2, final int n3) {
        int x = rectangle.x;
        int y = rectangle.y;
        int n4 = x + rectangle.width - 1;
        int n5 = y + rectangle.height - 1;
        switch (n3) {
            case 0: {
                x += n;
                y += n2;
                break;
            }
            case 2: {
                n4 += n;
                y += n2;
                break;
            }
            case 4: {
                n4 += n;
                n5 += n2;
                break;
            }
            case 6: {
                x += n;
                n5 += n2;
                break;
            }
            case 1: {
                y += n2;
                break;
            }
            case 3: {
                n4 += n;
                break;
            }
            case 5: {
                n5 += n2;
                break;
            }
            case 7: {
                x += n;
                break;
            }
            default: {
                x += n;
                n4 += n;
                y += n2;
                n5 += n2;
                break;
            }
        }
        return new Rectangle(Math.min(x, n4), Math.min(y, n5), Math.abs(n4 - x) + 1, Math.abs(n5 - y) + 1);
    }
    
    public void a(final Dimension b) {
        this.b = b;
        if (b != null) {
            final e e = new e(this.b.width, this.b.height);
            this.f = e.a(1 / e.b());
        }
    }
    
    public void a(final Rectangle rectangle, final int n) {
        int x = rectangle.x;
        int y = rectangle.y;
        int n2 = x + rectangle.width - 1;
        int n3 = y + rectangle.height - 1;
        if (this.b != null && b(n)) {
            final e e = new e(rectangle.width, rectangle.height);
            final e a = this.f.a(e.b()).a(e.a(-1.0));
            final int n4 = (int)Math.round(a.c());
            final int n5 = (int)Math.round(a.a());
            switch (n) {
                case 0:
                case 7: {
                    x -= n4;
                    y -= n5;
                    break;
                }
                case 1:
                case 2: {
                    n2 += n4;
                    y -= n5;
                    break;
                }
                case 3:
                case 4: {
                    n2 += n4;
                    n3 += n5;
                    break;
                }
                case 5:
                case 6: {
                    x -= n4;
                    n3 += n5;
                    break;
                }
            }
        }
        rectangle.setBounds(Math.min(x, n2), Math.min(y, n3), Math.abs(n2 - x) + 1, Math.abs(n3 - y) + 1);
    }
    
    public static boolean b(final int n) {
        return (n & 0x1) == 0x0;
    }
    
    static {
        ru.zhuk.gui.d.d = 3;
    }
}
