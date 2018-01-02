// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.SystemColor;
import ji.util.e;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import ji.graphic.b3;
import ji.io.h;
import java.awt.event.KeyEvent;
import ji.v1event.oj;
import java.awt.event.ActionEvent;
import ji.v1event.aq;
import java.awt.event.FocusEvent;
import ji.util.d;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.util.Vector;
import ji.document.ad;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.Container;

public class dl extends Container implements FocusListener, ActionListener
{
    private TextField a;
    private uq b;
    private ur c;
    Panel d;
    Panel e;
    up f;
    private ad g;
    String h;
    Vector i;
    
    public dl(final String h, final ad g, final boolean b) {
        this.a = new TextField();
        this.d = new Panel();
        this.e = new Panel();
        this.i = new Vector();
        this.h = h;
        this.g = g;
        this.f = new up(this);
        this.f.a = this.h;
        (this.b = new uq(h, 1335, g)).addActionListener(this);
        this.a.addFocusListener(this);
        this.a.addKeyListener(this.f);
        (this.c = new ur(h, 1340, g)).addActionListener(this);
        this.e.setLayout(new BorderLayout());
        this.e.add(this.b, "Center");
        this.e.add(this.c, "East");
        this.setLayout(new BorderLayout());
        this.d.setLayout(new BorderLayout());
        this.d.add(this.a, "Center");
        this.d.add(this.e, "East");
        this.add(this.d, "Center");
    }
    
    public void setVisible(final boolean b) {
        super.setVisible(b);
        this.d.setVisible(b);
        if (b) {
            if (this.a != null) {
                this.a.repaint();
            }
            this.d.repaint();
        }
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = this.a.getPreferredSize();
        preferredSize.height = 23;
        return preferredSize;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.d.setBounds(0, 0, n3, n4);
        Rectangle bounds2;
        Rectangle bounds = bounds2 = new Rectangle(0, 0, 0, 0);
        if (this.b != null) {
            bounds = this.b.getBounds();
        }
        if (this.c != null) {
            bounds2 = this.c.getBounds();
        }
        if (this.a != null) {
            this.a.setBounds(0, 0, n3 - bounds.width - bounds2.width, n4);
        }
        final Rectangle bounds3 = this.e.getBounds();
        bounds3.width = bounds.width + bounds2.width;
        bounds3.height = bounds.height;
        bounds3.x = n3 - bounds3.width;
        this.e.setBounds(bounds3);
        if (this.b != null) {
            this.b.setBounds(bounds);
        }
        if (this.c != null) {
            bounds2.x = bounds.x + bounds.width;
            this.c.setBounds(bounds2);
        }
    }
    
    public void a(final String text) {
        if (this.a != null) {
            this.a.setText(text);
        }
    }
    
    public void a() {
        if (this.a != null) {
            this.a.setText(null);
        }
    }
    
    public void b() {
        if (this.a != null) {
            final bb bb = new bb(this.h, new wu(this.a));
            this.a.setCaretPosition(this.c().length());
            bb.start();
        }
    }
    
    public String c() {
        if (this.a != null) {
            return this.a.getText();
        }
        return null;
    }
    
    public void d() {
        this.g = null;
        if (this.d != null) {
            this.d.remove(this.a);
            this.remove(this.d);
            this.d = null;
        }
        if (this.a instanceof Component) {
            ji.util.d.a(this.f);
        }
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
        if (this.i != null) {
            this.i.removeAllElements();
            this.i = null;
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        ji.util.d.b7(false);
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        ji.util.d.b7(true);
    }
    
    public void a(final aq aq) {
        if (this.i != null && !this.i.contains(aq)) {
            this.i.addElement(aq);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        oj oj = null;
        if (actionEvent.getSource() == this.c) {
            oj = new oj(this, 4, this.c.getLocationOnScreen());
        }
        else if (actionEvent.getSource() == this.b) {
            oj = new oj(this, (this.a != null) ? this.a.getText() : null);
        }
        this.a(oj);
    }
    
    private void a(final oj oj) {
        if (oj != null && this.i != null) {
            for (int i = 0; i < this.i.size(); ++i) {
                ((aq)this.i.elementAt(i)).a(oj);
            }
        }
    }
    
    class up implements KeyListener
    {
        String a;
        dl b;
        
        public up(final dl b) {
            this.b = b;
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            if (dl.this.a != null) {
                try {
                    if (keyEvent.getKeyCode() == 10) {
                        if (dl.this.a != null) {
                            dl.this.a(new oj(this.b, dl.this.a.getText()));
                        }
                        keyEvent.consume();
                    }
                    else if (keyEvent.getKeyCode() == 27) {
                        if (dl.this.a != null) {
                            dl.this.a.setText(null);
                        }
                        keyEvent.consume();
                    }
                    else if (keyEvent.getKeyCode() == 40) {
                        dl.this.a(new oj(this.b, 2, true));
                        keyEvent.consume();
                    }
                    else if (keyEvent.getKeyCode() == 38) {
                        dl.this.a(new oj(this.b, 3, true));
                        keyEvent.consume();
                    }
                }
                catch (Exception ex) {}
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            try {
                if (keyEvent.getKeyCode() == 40) {
                    dl.this.a(new oj(this.b, 2, false));
                    keyEvent.consume();
                }
                else if (keyEvent.getKeyCode() == 38) {
                    dl.this.a(new oj(this.b, 3, false));
                    keyEvent.consume();
                }
            }
            catch (Exception ex) {
                ji.io.h.a(this.a, ex);
            }
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
        }
        
        public void a() {
        }
    }
    
    class uq extends dm
    {
        private String a;
        private b3 b;
        
        public uq(final dl dl, final String a, final int n, final ad ad) {
            super("searchbutton", a, n, ad);
            this.b = new b3();
            this.a = a;
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(22, 20);
        }
        
        public void paint(final Graphics graphics) {
            super.paint(graphics);
        }
        
        public void setBounds(final Rectangle bounds) {
            super.setBounds(bounds);
        }
        
        public void setBounds(final int n, final int n2, final int n3, final int n4) {
            super.setBounds(n, n2, n3, n4);
        }
        
        private Image a(final Color color, final Color color2, final boolean b) {
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            final Image a = this.a(width, height);
            final Graphics graphics = a.getGraphics();
            final int n = width;
            final int n2 = height;
            d.a(graphics);
            if (d.em()) {
                graphics.setColor(d.h);
                graphics.fillRect(0, 0, n, n2);
            }
            final int n3 = n / 4;
            final int n4 = n - n3 * 2;
            final int n5 = 0;
            final int n6 = height;
            final int n7 = n3 + 1;
            final int n8 = n5 + (n6 - n4) / 2 - 2;
            final int n9 = n7 + n4 / 2;
            final int n10 = n8 + n4 / 2;
            final int n11 = n9;
            final int n12 = n10;
            final int n13 = n3 - 1;
            final int n14 = n2 - 4;
            graphics.setColor(Color.black);
            graphics.drawLine(n11, n12, n13, n14);
            graphics.drawLine(n11, n12 - 1, n13, n14 - 1);
            graphics.setColor(Color.black);
            graphics.fillOval(n7, n8, n4, n4);
            graphics.setColor(Color.lightGray);
            graphics.fillOval(n7 + 2, n8 + 2, n4 - 4, n4 - 4);
            return this.a(a, d.h);
        }
        
        protected Image m() {
            return this.a(e.a0(), SystemColor.windowText, true);
        }
    }
    
    class ur extends dm
    {
        public ur(final dl dl, final String s, final int n, final ad ad) {
            super("searchmenubutton", s, n, ad);
        }
        
        public Dimension getPreferredSize() {
            final Dimension preferredSize = super.getPreferredSize();
            preferredSize.width = 15;
            return preferredSize;
        }
        
        protected Image m() {
            return this.a(e.a0(), SystemColor.windowText, 180, false, true);
        }
    }
    
    private class wu implements Runnable
    {
        private Component a;
        
        wu(final dl dl, final Component a) {
            this.a = a;
        }
        
        public void run() {
            ji.util.e.b(this.a);
        }
    }
}
