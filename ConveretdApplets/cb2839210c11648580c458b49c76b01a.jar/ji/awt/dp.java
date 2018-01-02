// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Image;
import ji.v1event.fz;
import java.awt.event.ActionEvent;
import ji.v1event.cx;
import java.awt.Component;
import ji.document.ad;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.Container;

public class dp extends Container implements ActionListener
{
    private static final int[] a;
    private ut[] b;
    private Vector c;
    
    public dp(final String s, final ad ad) {
        this.b = new ut[4];
        this.c = new Vector();
        for (int i = 0; i < 4; ++i) {
            (this.b[i] = new ut(s, dp.a[i], ad, i)).setVisible(false);
            this.add(this.b[i]);
            this.b[i].addActionListener(this);
        }
    }
    
    public void a() {
        if (this.b != null) {
            for (int i = 0; i < this.b.length; ++i) {
                if (this.b[i] != null) {
                    this.b[i].releaseResources();
                    this.b[i] = null;
                }
            }
            this.b = null;
        }
        if (this.c != null) {
            this.c.removeAllElements();
            this.c = null;
        }
    }
    
    public void a(final cx cx) {
        if (this.c == null) {
            this.c = new Vector();
        }
        if (!this.c.contains(cx)) {
            this.c.addElement(cx);
        }
    }
    
    public void b(final cx cx) {
        if (this.c != null && this.c.contains(cx)) {
            this.c.removeElement(cx);
        }
    }
    
    public int getHeight() {
        if (this.b != null && this.b.length > 0 && this.b[0] != null) {
            return this.b[0].getHeight() + 2;
        }
        return 20;
    }
    
    public int getWidth() {
        int n = 0;
        if (this.b != null) {
            for (int i = 0; i < this.b.length; ++i) {
                if (this.b[i] != null) {
                    n += this.b[i].getWidth();
                }
            }
        }
        return n;
    }
    
    public void setBounds(int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        if (this.b != null) {
            for (int i = 0; i < this.b.length; ++i) {
                if (this.b[i] != null) {
                    this.b[i].setBounds(n, 0, this.b[i].getWidth(), n4);
                    n += this.b[i].getWidth();
                }
            }
        }
    }
    
    public void setVisible(final boolean b) {
        if (this.isVisible() != b) {
            for (int i = 0; i < this.b.length; ++i) {
                this.b[i].setVisible(b);
            }
            super.setVisible(b);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source instanceof ut) {
            fz fz = null;
            switch (((ut)source).n()) {
                case 0: {
                    fz = new fz(this, 0);
                    break;
                }
                case 1: {
                    fz = new fz(this, 1);
                    break;
                }
                case 2: {
                    fz = new fz(this, 2);
                    break;
                }
                case 3: {
                    fz = new fz(this, 3);
                    break;
                }
            }
            if (fz != null) {
                for (int i = 0; i < this.c.size(); ++i) {
                    ((cx)this.c.elementAt(i)).a(fz);
                }
            }
        }
    }
    
    static {
        a = new int[] { 1336, 1337, 1338, 1339 };
    }
    
    private class ut extends dm
    {
        private int a;
        
        public ut(final dp dp, final String s, final int n, final ad ad, final int a) {
            super("navigation", s, n, ad);
            this.a = a;
        }
        
        public int n() {
            return this.a;
        }
        
        public void releaseResources() {
            super.releaseResources();
        }
        
        public int getHeight() {
            return 18;
        }
        
        public int getWidth() {
            return 18;
        }
        
        private Image o() {
            return this.a(this.getBackground(), SystemColor.windowText, 270, true, false);
        }
        
        private Image p() {
            return this.a(this.getBackground(), SystemColor.windowText, 270, false, false);
        }
        
        private Image q() {
            return this.a(this.getBackground(), SystemColor.windowText, 90, false, false);
        }
        
        private Image r() {
            return this.a(this.getBackground(), SystemColor.windowText, 90, true, false);
        }
        
        protected Image m() {
            switch (this.a) {
                case 0: {
                    return this.o();
                }
                case 1: {
                    return this.p();
                }
                case 2: {
                    return this.q();
                }
                case 3: {
                    return this.r();
                }
                default: {
                    return null;
                }
            }
        }
    }
}
