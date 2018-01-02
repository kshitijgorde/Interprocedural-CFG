// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Panel;
import java.awt.event.AdjustmentEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.Component;
import java.awt.LayoutManager;
import ji.util.d;
import ji.io.h;
import ji.util.e;
import ji.util.i;
import ji.graphic.by;
import java.awt.Rectangle;
import java.awt.event.AdjustmentListener;
import java.awt.Container;

public class ft extends Container implements fm, AdjustmentListener
{
    private fm a;
    private String b;
    private Rectangle c;
    wm d;
    by e;
    private boolean f;
    private boolean g;
    
    public boolean isSwing() {
        return this.f;
    }
    
    public boolean isAdjustingValue() {
        return this.a.isAdjustingValue();
    }
    
    public ft(final String b, final boolean b2) {
        this.c = new Rectangle();
        this.d = new wm();
        this.e = null;
        this.f = false;
        this.g = false;
        this.b = b;
        if (b2 && !i.c(280) && ji.util.e.av()) {
            try {
                final Class<?> forName = Class.forName("ji.awt.swing.JList");
                if (forName != null) {
                    this.a = (fm)forName.newInstance();
                    if (i.c(252)) {
                        h.d(b, "List is a Swing JList");
                    }
                    this.f = true;
                }
            }
            catch (ClassNotFoundException ex) {
                if (i.c(252)) {
                    h.d(b, "Swing JList could not be created.");
                }
                ji.util.d.a(ex);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        if (this.a == null) {
            this.a = new fu(b);
            if (i.c(252)) {
                h.d(b, "List is an AWT JList");
            }
            this.f = false;
        }
        this.setLayout(null);
        this.d.setLayout(null);
        this.d.add((Component)this.a);
        if (this.f) {
            (this.e = new by(1, 1, b)).addAdjustmentListener(this);
            this.b(false);
            this.e.setSize(18, 0);
            this.d.add(this.e);
            this.e.setUnitIncrement(40);
            this.e.setBlockIncrement(40);
        }
        this.add(this.d);
        this.c.setBounds(this.getBounds());
    }
    
    public void releaseResources() {
        if (this.e != null) {
            this.e.removeAdjustmentListener(this);
            this.e.releaseResources();
            this.d.remove(this.e);
        }
        if (this.a != null) {
            final ItemListener[] itemListeners = this.a.getItemListeners();
            for (int i = 0; i < itemListeners.length; ++i) {
                this.a.removeItemListener(itemListeners[i]);
            }
            this.a.releaseResources();
            this.d.remove((Component)this.a);
            this.a = null;
        }
        if (this.d != null) {
            this.d.b();
            this.remove(this.d);
        }
    }
    
    public void validate() {
        super.validate();
    }
    
    public void setVisible(final boolean b) {
        super.setVisible(b);
        this.d.setVisible(b);
        if (b) {
            this.a.repaint();
            this.d.repaint();
        }
    }
    
    private void a(final boolean b) {
        if (this.d != null) {
            this.d.a();
        }
    }
    
    public int getRows() {
        return this.a.getRows();
    }
    
    public void add(final String s) {
        this.a.add(s);
        if (!this.g) {
            this.a(true);
        }
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.a.addItemListener(itemListener);
    }
    
    public void clear() {
        this.a.clear();
        if (this.e != null) {
            this.e.setValue(0);
            this.a(true);
        }
        this.b();
    }
    
    public int getSelectedIndex() {
        return this.a.getSelectedIndex();
    }
    
    public void makeVisible(final int n) {
        if (n < 0 || n >= this.getRows()) {
            return;
        }
        try {
            if (this.f) {
                final Rectangle itemBounds = this.a.getItemBounds(n);
                final Rectangle bounds = this.a.getBounds();
                final Rectangle bounds2 = this.d.getBounds();
                final int n2 = bounds2.height - bounds2.y;
                final int n3 = itemBounds.y + bounds.y + itemBounds.height;
                final int n4 = itemBounds.y + bounds.y;
                if (n3 > n2) {
                    this.e.setValue(this.e.getValue() + (n3 - n2));
                }
                else if (n4 < bounds2.y) {
                    this.e.setValue(this.e.getValue() - (bounds2.y - n4));
                }
            }
            else {
                this.a.makeVisible(n);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.a.removeItemListener(itemListener);
    }
    
    public int getTotalRowHeight() {
        return this.a.getTotalRowHeight();
    }
    
    public void select(final int n) {
        synchronized (this.a) {
            if (this.a.getSelectedIndex() != n) {
                this.a.select(n);
            }
        }
        // monitorexit(this.a)
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        boolean b = true;
        if (this.c != null) {
            synchronized (this.c) {
                final Rectangle bounds = new Rectangle(n, n2, n3, n4);
                if (!this.c.equals(bounds)) {
                    this.c.setBounds(bounds);
                }
                else {
                    b = false;
                }
            }
            // monitorexit(this.c)
        }
        if (b) {
            super.setBounds(n, n2, n3, n4);
            this.a(true);
            ji.util.e.a(this.d, new Rectangle(n, n2, n3, n4));
        }
    }
    
    private void b(final boolean visible) {
        if (this.e != null) {
            this.e.setVisible(visible);
        }
    }
    
    private boolean a() {
        return this.e != null && this.e.isVisible();
    }
    
    public void doLayout() {
        super.doLayout();
    }
    
    public void repaint() {
        super.repaint();
    }
    
    public void setSize(final Dimension size) {
        super.setSize(size);
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void b() {
        if (this.a != null) {
            final Rectangle bounds = this.a.getBounds();
            this.a.setBounds(bounds.x, 0, bounds.width, bounds.height);
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource() == this.e) {
            if (adjustmentEvent.getValue() == this.e.j()) {
                this.repaint();
            }
            else {
                final Rectangle bounds = this.a.getBounds();
                this.a.setBounds(bounds.x, -this.e.getValue(), bounds.width, bounds.height);
            }
        }
    }
    
    public void beginBulkAdd() {
        if (this.a != null) {
            this.a.beginBulkAdd();
        }
        synchronized (this) {
            this.g = true;
        }
    }
    
    public void finishBulkAdd() {
        synchronized (this) {
            this.g = false;
            this.d.invalidate();
        }
        if (this.a != null) {
            this.a.finishBulkAdd();
            if (!this.a()) {
                this.a.repaint();
            }
        }
        this.a(true);
    }
    
    public Component getComponent() {
        return this;
    }
    
    public Rectangle getItemBounds(final int n) {
        if (this.f) {
            return this.a.getItemBounds(n);
        }
        return null;
    }
    
    public ItemListener[] getItemListeners() {
        return this.a.getItemListeners();
    }
    
    public void select(final int n, final boolean b) {
        synchronized (this.a) {
            if (this.a.getSelectedIndex() != n) {
                this.a.select(n, b);
            }
        }
        // monitorexit(this.a)
    }
    
    public class wm extends Panel
    {
        public void setBounds(final int n, final int n2, final int n3, final int n4) {
            super.setBounds(n, n2, n3, n4);
            int n5 = n3;
            if (ft.this.a()) {
                n5 -= ft.this.e.getWidth();
                ft.this.e.getBounds();
                ft.this.e.setBounds(n3 - ft.this.e.getWidth(), n2, ft.this.e.getWidth(), n4);
            }
            ft.this.a.setBounds(n, n2, n5, n4);
        }
        
        public void a() {
            if (ft.this.e == null) {
                return;
            }
            final Rectangle rectangle = new Rectangle();
            final Dimension dimension = new Dimension();
            if (this.getParent() != null) {
                rectangle.setBounds(this.getParent().getBounds());
                dimension.setSize(this.getParent().getSize());
            }
            final int totalRowHeight = ft.this.a.getTotalRowHeight();
            final int height = dimension.height;
            final int height2 = ft.this.a.getPreferredSize().height;
            if (totalRowHeight > height && height > 0) {
                if (!ft.this.a()) {
                    ft.this.b(true);
                    this.setBounds(rectangle.getBounds());
                }
            }
            else if (ft.this.a()) {
                ft.this.b(false);
                this.setBounds(rectangle.getBounds());
            }
            if (ft.this.a()) {
                final int n = totalRowHeight - height;
                final int value = ft.this.e.getValue();
                if (totalRowHeight > 0 && n != 0) {
                    final int n2 = (int)(totalRowHeight * (100.0 / totalRowHeight * height / 100.0));
                    final int blockIncrement = height;
                    ft.this.e.a(value, blockIncrement, 0, n);
                    ft.this.e.setBlockIncrement(blockIncrement);
                }
            }
        }
        
        public void paintComponents(final Graphics graphics) {
            super.paintComponents(graphics);
        }
        
        public void b() {
        }
        
        public void paint(final Graphics graphics) {
            super.paint(graphics);
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void repaint() {
            super.repaint();
        }
        
        public void validate() {
            super.validate();
        }
        
        public int c() {
            if (ft.this.e != null && ft.this.a()) {
                return ft.this.e.getValue();
            }
            return -1;
        }
    }
}
