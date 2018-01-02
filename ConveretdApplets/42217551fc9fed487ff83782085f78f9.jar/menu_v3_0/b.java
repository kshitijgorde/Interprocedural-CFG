// 
// Decompiled by Procyon v0.5.30
// 

package menu_v3_0;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

public class b extends Canvas
{
    private Graphics new;
    private Image char;
    private Dimension a;
    private Image byte;
    private Image else;
    private int int;
    private int for;
    public int if;
    public int try;
    public Color goto;
    public Color case;
    private int do;
    
    public b(final Image byte1, final Color case1, final int int1, final int for1, final Image else1) {
        this.if = 1;
        this.try = 1;
        this.goto = Color.black;
        this.case = Color.white;
        this.do = -1;
        this.else = else1;
        if (case1 != null) {
            this.case = case1;
        }
        this.int = int1;
        this.for = for1;
        this.byte = byte1;
    }
    
    public synchronized void finalize() throws Throwable {
        synchronized (this.else) {
            if (this.else != null) {
                this.else.flush();
                this.else = null;
            }
        }
        // monitorexit(this.else)
        if (this.byte != null) {
            this.byte.flush();
            this.byte = null;
        }
        if (this.char != null) {
            this.char.flush();
            this.char = null;
        }
    }
    
    public synchronized Dimension getPreferredSize() {
        return new Dimension(this.if + this.int + this.try, this.getSize().height);
    }
    
    public synchronized Dimension getMinimumSize() {
        return new Dimension(0, this.getSize().height);
    }
    
    public synchronized void a(final int do1) {
        if (this.do != do1) {
            this.do = do1;
            this.a(this.getGraphics());
        }
    }
    
    public synchronized void a() {
        this.do = -1;
        this.a(this.getGraphics());
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.a(graphics);
    }
    
    public synchronized void a(final Graphics graphics) {
        this.if();
        if (this.char != null) {
            graphics.drawImage(this.char, 0, 0, this);
        }
    }
    
    private boolean a(final int[] array, final int[] array2, final int n, final int n2) {
        if (this.int > 0 && this.for > 0) {
            final int[] array3 = { 0, this.int - 1, 0 };
            final int[] array4 = { 0, (this.for - 1) / 2, this.for - 1 };
            for (int i = 0; i < 3; ++i) {
                array[i] = array3[i] + n;
                array2[i] = array4[i] + n2;
            }
            return true;
        }
        return false;
    }
    
    private void if() {
        final Dimension size = this.getSize();
        if (size.width > 0 && size.height > 0) {
            if (this.char == null || this.a.width != size.width || this.a.height != size.height) {
                if (this.char != null) {
                    this.char.flush();
                    this.char = null;
                }
                this.char = this.createImage(size.width, size.height);
                this.a = size;
                this.new = this.char.getGraphics();
            }
            this.new.setClip(0, 0, size.width, size.height);
            this.new.setColor(this.goto);
            this.new.fillRect(0, 0, size.width, size.height);
            if (this.else != null) {
                this.new.drawImage(this.else, 0, 0, this);
            }
            if (this.do >= 0 && this.do < size.height) {
                if (this.byte != null) {
                    this.new.drawImage(this.byte, this.if, this.do - this.for / 2, this.int, this.for, this);
                    return;
                }
                if (this.case != null) {
                    final int[] array = new int[3];
                    final int[] array2 = new int[3];
                    if (this.a(array, array2, this.if, this.do - this.for / 2)) {
                        this.new.setColor(this.case);
                        this.new.fillPolygon(array, array2, 3);
                        this.new.drawPolygon(array, array2, 3);
                    }
                }
            }
        }
    }
}
