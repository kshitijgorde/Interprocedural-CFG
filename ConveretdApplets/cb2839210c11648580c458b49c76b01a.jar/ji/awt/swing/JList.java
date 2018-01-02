// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt.swing;

import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.ItemSelectable;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.EventListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ItemListener;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Dimension;
import java.awt.Container;
import ji.awt.ft;
import java.awt.event.KeyListener;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ListModel;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.DefaultListSelectionModel;
import javax.swing.DefaultListModel;
import ji.awt.fm;

public class JList extends javax.swing.JList implements fm
{
    DefaultListModel a;
    DefaultListSelectionModel b;
    yc c;
    Vector d;
    long e;
    private ya f;
    private boolean g;
    private boolean h;
    int i;
    static /* synthetic */ Class j;
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public void paintImmediately(final int n, final int n2, final int n3, final int n4) {
        super.paintImmediately(n, n2, n3, n4);
    }
    
    public void update(final Graphics graphics) {
        super.update(graphics);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
    }
    
    public void repaint() {
        boolean b = false;
        if (this.f != null) {
            synchronized (this.f) {
                if (this.f.a) {
                    b = true;
                }
            }
            // monitorexit(this.f)
        }
        if (b) {
            if (System.currentTimeMillis() - this.e > 300) {
                this.e = System.currentTimeMillis();
                super.repaint();
            }
        }
        else {
            super.repaint();
        }
    }
    
    public JList() {
        this.a = new DefaultListModel();
        this.b = new yb((aed)null);
        this.d = new Vector();
        this.e = System.currentTimeMillis();
        this.f = new ya((aed)null);
        this.g = false;
        this.h = true;
        this.i = 20;
        this.setModel(this.a);
        this.setFont(this.getFont().deriveFont(0));
        this.setSelectionBackground(SystemColor.textHighlight);
        this.setSelectionForeground(SystemColor.textHighlightText);
        this.setSelectionModel(this.b);
        this.setOpaque(true);
        this.setPrototypeCellValue("test");
        this.setMultipleMode(false);
        this.i = this.getFixedCellHeight();
        this.addKeyListener(this.c = new yc(this));
    }
    
    public void setMultipleMode(final boolean b) {
        if (b) {
            this.setSelectionMode(2);
        }
        else {
            this.setSelectionMode(0);
        }
    }
    
    public void setBounds(final int n, int n2, final int n3, int n4) {
        final Container parent = this.getParent();
        if (parent instanceof ft.wm) {
            final int c = ((ft.wm)parent).c();
            if (c > 0) {
                n2 = -c;
            }
            n4 = parent.getSize().height;
            final Dimension preferredSize = this.getPreferredSize();
            if (n4 < preferredSize.height) {
                n4 = preferredSize.height;
            }
        }
        super.setBounds(n, n2, n3, n4);
    }
    
    public int getRows() {
        synchronized (this.a) {
            // monitorexit(this.a)
            return this.a.getSize();
        }
    }
    
    public void addNotify() {
        super.addNotify();
        this.setBorder(new CompoundBorder(new MatteBorder(1, 1, 2, 1, this.getParent().getBackground()), new BevelBorder(1)));
    }
    
    public void add(final String s) {
        synchronized (this.a) {
            try {
                this.g = true;
                this.a.addElement(s);
                if (this.a.size() == 0) {
                    this.e = 0L;
                }
            }
            finally {
                this.g = false;
            }
        }
        // monitorexit(this.a)
    }
    
    public void addItemListener(final ItemListener itemListener) {
        final yd yd = new yd(itemListener);
        this.d.addElement(yd);
        this.getSelectionModel().addListSelectionListener(yd);
    }
    
    public ItemListener[] getItemListeners() {
        final ItemListener[] array = new ItemListener[this.d.size()];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = (ItemListener)this.d.elementAt(i);
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
        }
        return array;
    }
    
    public void clear() {
        synchronized (this.a) {
            try {
                this.g = true;
                this.a.removeAllElements();
            }
            finally {
                this.e = 0L;
                this.g = false;
            }
        }
        // monitorexit(this.a)
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.d.removeElement(itemListener);
        final EventListener[] listeners = super.getListeners((Class<EventListener>)((JList.j == null) ? (JList.j = class$("javax.swing.event.ListSelectionListener")) : JList.j));
        for (int i = 0; i < listeners.length; ++i) {
            if (listeners[i] instanceof yd) {
                final yd yd = (yd)listeners[i];
                if (yd != null && yd.equals(itemListener)) {
                    super.removeListSelectionListener((ListSelectionListener)listeners[i]);
                }
            }
        }
    }
    
    public void select(final int n) {
        this.select(n, true);
    }
    
    public void select(final int n, final boolean b) {
        if (n == -1) {
            ((yb)this.getSelectionModel()).a(b);
        }
        else {
            ((yb)this.getSelectionModel()).a(n, n, b);
        }
    }
    
    public void makeVisible(final int n) {
    }
    
    public Rectangle getItemBounds(final int n) {
        return this.getCellBounds(n, n);
    }
    
    public void releaseResources() {
        if (this.a != null) {
            this.a.removeAllElements();
        }
        if (this.d != null) {
            this.d.removeAllElements();
        }
        if (this.c != null) {
            this.removeKeyListener(this.c);
            this.c = null;
        }
        this.a = null;
        this.b = null;
    }
    
    public int getTotalRowHeight() {
        return this.getMaximumSize().height;
    }
    
    public void beginBulkAdd() {
        synchronized (this.f) {
            this.f.a = true;
        }
        // monitorexit(this.f)
    }
    
    public void finishBulkAdd() {
        synchronized (this.f) {
            this.f.a = false;
        }
        // monitorexit(this.f)
    }
    
    public Component getComponent() {
        return this;
    }
    
    public boolean isSwing() {
        return true;
    }
    
    public boolean isAdjustingValue() {
        return super.getValueIsAdjusting();
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private class yb extends DefaultListSelectionModel
    {
        private boolean a;
        
        private yb(final JList list) {
            this.a = false;
        }
        
        public void addSelectionInterval(final int n, final int n2) {
            super.addSelectionInterval(n, n2);
        }
        
        public void a(final boolean b) {
            synchronized (this) {
                try {
                    if (!b) {
                        this.a = true;
                    }
                    super.clearSelection();
                }
                finally {
                    this.a = false;
                }
            }
        }
        
        public void setSelectionInterval(final int n, final int n2) {
            synchronized (this) {
                super.setSelectionInterval(n, n2);
            }
        }
        
        public void a(final int n, final int n2, final boolean b) {
            synchronized (this) {
                try {
                    if (!b) {
                        this.a = true;
                    }
                    this.setSelectionInterval(n, n2);
                }
                finally {
                    this.a = false;
                }
            }
        }
        
        protected void fireValueChanged(final int n, final int n2, final boolean b) {
            boolean b2 = b;
            synchronized (this) {
                if (this.a) {
                    b2 = true;
                }
            }
            super.fireValueChanged(n, n2, b2);
        }
    }
    
    private class ab0 implements ItemSelectable
    {
        public void addItemListener(final ItemListener itemListener) {
        }
        
        public Object[] getSelectedObjects() {
            return new Object[] { JList.this.getModel().getElementAt(JList.this.getSelectionModel().getMinSelectionIndex()) };
        }
        
        public void removeItemListener(final ItemListener itemListener) {
        }
    }
    
    private class yd implements ListSelectionListener, ItemListener
    {
        ItemListener a;
        
        public yd(final ItemListener a) {
            this.a = a;
        }
        
        public void valueChanged(final ListSelectionEvent listSelectionEvent) {
            if (!listSelectionEvent.getValueIsAdjusting() && (!JList.this.g || (JList.this.g && !JList.this.h))) {
                boolean b = false;
                int n = listSelectionEvent.getFirstIndex();
                if (n < JList.this.getModel().getSize()) {
                    b = JList.this.getSelectionModel().isSelectedIndex(n);
                }
                if (!b) {
                    n = listSelectionEvent.getLastIndex();
                    if (n < JList.this.getModel().getSize()) {
                        b = JList.this.getSelectionModel().isSelectedIndex(n);
                    }
                }
                final ListModel<Object> model = JList.this.getModel();
                if (model != null) {
                    synchronized (model) {
                        if (n < model.getSize() && n >= 0) {
                            final Object element = model.getElementAt(n);
                            if (element != null) {
                                this.a.itemStateChanged(new ItemEvent(new ab0((aed)null), 701, element, b ? 1 : 2));
                            }
                        }
                    }
                    // monitorexit(model)
                }
            }
        }
        
        public void itemStateChanged(final ItemEvent itemEvent) {
        }
    }
    
    class yc implements KeyListener
    {
        JList a;
        
        yc(final JList a) {
            this.a = a;
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 40 || keyEvent.getKeyCode() == 38) {
                this.a.setValueIsAdjusting(true);
                if (this.a.getParent() != null && this.a.getParent().getParent() instanceof ft) {
                    int n;
                    if (keyEvent.getKeyCode() == 40) {
                        n = 1;
                    }
                    else {
                        n = -1;
                    }
                    ((ft)this.a.getParent().getParent()).makeVisible(JList.this.getSelectedIndex() + n);
                }
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 40 || keyEvent.getKeyCode() == 38) {
                this.a.setValueIsAdjusting(false);
            }
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
        }
    }
    
    private class ya
    {
        private boolean a;
        
        private ya(final JList list) {
            this.a = false;
        }
    }
    
    interface aed
    {
    }
}
