// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.Point;
import com.cc.gui.G;
import com.cc.B.E;
import java.awt.Rectangle;
import com.cc.B.F;
import java.util.Observable;
import com.cc.gui.H;
import com.cc.C.A;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import com.cc.gui.D;

public class C extends D implements MouseListener, KeyListener, Observer, ActionListener, ItemListener
{
    private int E;
    private final Grid_int D;
    private final B C;
    private final A F;
    private final com.cc.B.A B;
    
    public C(final Grid_int grid_int, final B b, final com.cc.B.A a, final A a2) {
        this(grid_int, b, a, a2, null);
    }
    
    public C(final Grid_int d, final B c, final com.cc.B.A b, final A f, final com.cc.gui.B[] array) {
        super(array);
        this.E = 0;
        this.D = d;
        this.C = c;
        this.F = f;
        (this.B = b).addObserver(this);
    }
    
    protected H A() {
        return this.C.C;
    }
    
    protected void B() {
        this.D.startTimer();
    }
    
    public void update(final Observable observable, final Object o) {
        final F f = (F)o;
        if (f.E == 5 && !this.B.d && this.D.Y != null) {
            final Rectangle rectangle = new Rectangle();
            final com.cc.C.B k = f.A.K();
            for (int i = 0; i < k.A(); ++i) {
                final G b = this.A().B((E)k.A(i));
                if (b != null) {
                    if (i == 0) {
                        rectangle.setBounds(b.B());
                    }
                    else {
                        rectangle.add(b.B());
                    }
                }
            }
            if (k.A() > 0) {
                final G b2 = this.A().B((E)k.A(0));
                if (b2 != null) {
                    final Point scrollPosition = this.D.Y.getScrollPosition();
                    final int n = this.D.Y.getWidth() - this.D.Y.getVScrollbarWidth();
                    final int n2 = this.D.Y.getHeight() - this.D.Y.getHScrollbarHeight();
                    int n3 = scrollPosition.x;
                    int n4 = scrollPosition.y;
                    if (b2.B().x < n3 || rectangle.x + rectangle.width > n3 + n) {
                        n3 = b2.B().x;
                    }
                    if (b2.B().y < n4 || rectangle.y + rectangle.height > n4 + n2) {
                        n4 = b2.B().y;
                    }
                    if (n3 != scrollPosition.x || n4 != scrollPosition.y) {
                        this.D.Y.setScrollPosition(n3, n4);
                    }
                }
            }
        }
        boolean b3 = f.E == 3;
        if (!this.F.b) {
            b3 |= (f.E == 2);
        }
        if (b3) {
            this.D.puzzleCompleted();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.C == null) {
            return;
        }
        final String actionCommand = actionEvent.getActionCommand();
        try {
            switch (Integer.decode(actionCommand)) {
                case 0: {
                    this.C(actionEvent.getSource());
                    break;
                }
                case 1: {
                    this.D.CheckClick();
                    break;
                }
                case 2: {
                    this.D.SubmitClick();
                    break;
                }
                case 3: {
                    this.D.SaveClick();
                    break;
                }
                case 4: {
                    this.D.RevertClick();
                    break;
                }
                case 5: {
                    this.D.SolutionClick();
                    break;
                }
                case 6: {
                    this.A(actionEvent.getSource());
                    break;
                }
                case 7: {
                    this.B(actionEvent.getSource());
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void B(final Object o) {
        final boolean d = ((com.cc.applet.A)o).D();
        ((com.cc.applet.A)o).A(!d);
        this.C.A(!d);
    }
    
    private void D(final Object o) {
        if (this.E >= this.F.\u00d2 && o != null) {
            if (o instanceof Button) {
                ((Button)o).setEnabled(false);
            }
            else {
                ((com.cc.applet.A)o).setEnabled(false);
            }
        }
        this.C.requestFocus();
    }
    
    public void C(final Object o) {
        ++this.E;
        if (this.E <= this.F.\u00d2) {
            this.C.A();
        }
        this.D(o);
    }
    
    public void A(final Object o) {
        ++this.E;
        if (this.E <= this.F.\u00d2) {
            this.C.B();
        }
        this.D(o);
    }
    
    public void A(final String s, final String s2, final boolean b, final boolean b2) {
        this.D.showProcessedURL(s, s2, b, b2);
    }
    
    public void A(final String s) {
        this.D.showStatus(s);
    }
}
