// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.Panel;

public class b extends Panel
{
    private Vector et;
    private Vector ev;
    private int er;
    private int ex;
    private boolean ew;
    private c eu;
    private b es;
    private a7 eq;
    
    public b() {
        this.et = new Vector();
        this.ev = new Vector();
        this.er = 6;
        this.ex = 6;
        this.ew = false;
        this.eu = new c();
        this.es = new b();
        this.eq = new a7("SplitPaneLock");
        this.addMouseListener(this.eu);
        this.addMouseMotionListener(this.eu);
        this.addComponentListener(this.es);
    }
    
    public int G() {
        this.eq.do();
        try {
            return this.er;
        }
        finally {
            this.eq.a();
        }
    }
    
    public void m(final int er) {
        this.eq.do();
        try {
            final int er2 = this.er;
            this.er = er;
            if (this.er != er2) {
                this.do(true);
            }
        }
        finally {
            this.eq.a();
        }
    }
    
    protected void do(final boolean ew) {
        this.eq.do();
        try {
            this.ew = ew;
        }
        finally {
            this.eq.a();
        }
    }
    
    protected boolean H() {
        this.eq.do();
        try {
            return this.ew;
        }
        finally {
            this.eq.a();
        }
    }
    
    public av if(final av av) {
        this.eq.do();
        try {
            this.et.addElement(av);
            super.add(av);
            if (this.et.size() > 1) {
                final a a = new a();
                a.addMouseListener(this.eu);
                a.addMouseMotionListener(this.eu);
                this.ev.addElement(a);
                super.add(a);
            }
            this.K();
            return av;
        }
        finally {
            this.eq.a();
        }
    }
    
    public void a(final av av) {
        this.eq.do();
        try {
            if (this.et.indexOf(av) >= 0) {
                this.et.removeElement(av);
                super.remove(av);
                if (this.ev.size() > 0) {
                    final a a = this.ev.elementAt(this.ev.size() - 1);
                    a.removeMouseListener(this.eu);
                    a.removeMouseMotionListener(this.eu);
                    this.ev.removeElement(a);
                    super.remove(a);
                }
            }
            this.K();
        }
        finally {
            this.eq.a();
        }
    }
    
    public void J() {
        this.eq.do();
        try {
            super.removeAll();
            this.et.removeAllElements();
            this.ev.removeAllElements();
            this.do(true);
        }
        finally {
            this.eq.a();
        }
    }
    
    public int L() {
        this.eq.do();
        try {
            return this.et.size();
        }
        finally {
            this.eq.a();
        }
    }
    
    public av[] M() {
        this.eq.do();
        try {
            final av[] array = new av[this.et.size()];
            this.et.copyInto(array);
            return array;
        }
        finally {
            this.eq.a();
        }
    }
    
    public void doLayout() {
        this.do(true);
    }
    
    public void paint(final Graphics graphics) {
        if (this.H()) {
            this.I();
        }
        super.paint(graphics);
    }
    
    protected void a(final a a, final int n) {
        this.eq.do();
        try {
            if (Math.abs(n) < this.ex) {
                return;
            }
            final int index = this.ev.indexOf(a);
            if (index < 0) {
                return;
            }
            final Component component = this.et.elementAt(index);
            final Component component2 = this.et.elementAt(index + 1);
            final int n2 = component.getSize().height + n;
            final int n3 = component2.getSize().height - n;
            final int height = component.getMinimumSize().height;
            final int height2 = component2.getMinimumSize().height;
            final int n4 = Integer.MAX_VALUE;
            final int n5 = Integer.MAX_VALUE;
            if (n2 < height) {
                return;
            }
            if (n3 < height2) {
                return;
            }
            if (n2 > n4) {
                return;
            }
            if (n3 > n5) {
                return;
            }
            component.setBounds(component.getLocation().x, component.getLocation().y, component.getSize().width, n2);
            a.setLocation(a.getLocation().x, component.getLocation().y + n2);
            component2.setBounds(component2.getLocation().x, a.getLocation().y + a.getSize().height, component2.getSize().width, n3);
        }
        finally {
            this.eq.a();
        }
    }
    
    protected void I() {
        this.eq.do();
        try {
            this.es.do();
            this.do(false);
            if (this.et.size() == 0) {
                return;
            }
            final int size = this.et.size();
            final int[] array = new int[size];
            for (int i = 0; i < size; ++i) {
                array[i] = ((Component)this.et.elementAt(i)).getSize().height;
            }
            this.a(array);
        }
        finally {
            this.es.if();
            this.eq.a();
        }
    }
    
    protected void a(final int[] array) {
        this.eq.do();
        try {
            this.es.do();
            this.do(false);
            if (this.et.size() == 0) {
                return;
            }
            final Dimension size = this.getSize();
            final Insets insets = this.getInsets();
            final Dimension dimension = size;
            dimension.width -= insets.left + insets.right;
            final Dimension dimension2 = size;
            dimension2.height -= insets.top + insets.bottom;
            int n = 0;
            final int size2 = this.et.size();
            final int[] array2 = new int[size2];
            final int[] array3 = new int[size2];
            for (int i = 0; i < this.et.size(); ++i) {
                array2[i] = ((Component)this.et.elementAt(i)).getMinimumSize().height;
                array3[i] = Integer.MAX_VALUE;
                if (array[i] < array2[i]) {
                    array[i] = array2[i];
                }
                if (array[i] > array3[i]) {
                    array[i] = array3[i];
                }
                n += array[i];
                if (i < this.et.size() - 1) {
                    n += this.er;
                }
            }
            int j = size.height - n;
            if (j > 0) {
                while (j > 0) {
                    final int n2 = j;
                    for (int n3 = 0; n3 < this.et.size() && j > 0; ++n3) {
                        if (array3[n3] > array[n3]) {
                            final int n4 = n3;
                            ++array[n4];
                            --j;
                        }
                    }
                    if (n2 == j) {
                        break;
                    }
                }
            }
            else {
                while (j < 0) {
                    final int n5 = j;
                    for (int n6 = 0; n6 < this.et.size() && j < 0; ++n6) {
                        if (array2[n6] < array[n6]) {
                            final int n7 = n6;
                            --array[n7];
                            ++j;
                        }
                    }
                    if (n5 == j) {
                        break;
                    }
                }
            }
            int n8 = 0;
            for (int k = 0; k < this.et.size(); ++k) {
                ((Component)this.et.elementAt(k)).setBounds(insets.left, n8, size.width, array[k]);
                n8 += array[k];
                if (k < this.et.size() - 1) {
                    ((Component)this.ev.elementAt(k)).setBounds(insets.left, n8, size.width, this.er);
                    n8 += this.er;
                }
            }
        }
        finally {
            this.es.if();
            this.eq.a();
        }
    }
    
    protected void K() {
        this.eq.do();
        try {
            this.es.do();
            if (this.et.size() == 0) {
                return;
            }
            final Dimension size = this.getSize();
            final Insets insets = this.getInsets();
            final Dimension dimension = size;
            dimension.width -= insets.left + insets.right;
            final Dimension dimension2 = size;
            dimension2.height -= insets.top + insets.bottom;
            final int n = size.height - this.G() * this.ev.size();
            final int size2 = this.et.size();
            final int n2 = (int)Math.floor(n / size2);
            final int n3 = n - n2 * size2;
            final int[] array = new int[size2];
            for (int i = 0; i < size2; ++i) {
                array[i] = n2 + ((i == 0) ? n3 : false);
            }
            this.a(array);
        }
        finally {
            this.es.if();
            this.eq.a();
        }
    }
    
    public static class a extends Canvas
    {
        private boolean a;
        
        public a() {
            this.a = false;
            this.setBackground(new Color(192, 192, 192));
        }
        
        public boolean a() {
            return this.a;
        }
        
        public void a(final boolean a) {
            final boolean a2 = this.a;
            this.a = a;
            if (this.a != a2) {
                this.repaint();
            }
        }
        
        public void paint(final Graphics graphics) {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this.a(graphics);
        }
        
        private void a(final Graphics graphics) {
            final Color brighter = this.getBackground().brighter().brighter();
            final Color darker = this.getBackground().darker().darker();
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            graphics.setColor(this.a() ? darker : brighter);
            graphics.drawLine(0, 1, width - 3, 1);
            graphics.drawLine(1, 0, 1, height - 2);
            graphics.setColor(this.a() ? brighter : darker);
            graphics.drawLine(2, height - 2, width - 1, height - 2);
            graphics.drawLine(width - 2, 1, width - 2, height - 1);
        }
    }
    
    private class c implements MouseListener, MouseMotionListener
    {
        private a a;
        private av if;
        private av do;
        
        private c() {
            this.a = null;
            this.if = null;
            this.do = null;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() instanceof a) {
                b.this.setCursor(Cursor.getPredefinedCursor(8));
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() instanceof a && !this.if()) {
                b.this.setCursor(Cursor.getDefaultCursor());
            }
            if (mouseEvent.getSource() instanceof b) {
                b.this.setCursor(Cursor.getDefaultCursor());
                this.a(null);
            }
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (this.if()) {
                final a a = this.a();
                int y = 0;
                if (mouseEvent.getY() < 0) {
                    y = mouseEvent.getY();
                }
                else if (mouseEvent.getY() > a.getSize().height) {
                    y = mouseEvent.getY() - a.getSize().height;
                }
                this.a(null);
                this.if.int(-1);
                this.do.int(-1);
                b.this.a(a, y);
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() instanceof a) {
                if (!this.if()) {
                    this.a((a)mouseEvent.getSource());
                }
                if (this.if()) {
                    final int index = b.this.ev.indexOf(mouseEvent.getSource());
                    final int y = mouseEvent.getY();
                    if (index < 0) {
                        return;
                    }
                    this.if = (av)b.this.et.elementAt(index);
                    this.do = (av)b.this.et.elementAt(index + 1);
                    if (this.if.getSize().height + y < this.if.getMinimumSize().height || y > this.do.getSize().height - this.do.getMinimumSize().height) {
                        b.this.setCursor(new Cursor(0));
                        return;
                    }
                    b.this.setCursor(new Cursor(8));
                    if (y < 0) {
                        this.if.int(this.if.getSize().height + y);
                        this.do.int(-1);
                        this.if.repaint();
                        this.do.repaint();
                    }
                    else {
                        this.if.int(-1);
                        this.if.repaint();
                        this.do.int(y);
                        this.do.repaint();
                    }
                }
            }
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        protected synchronized boolean if() {
            return this.a != null;
        }
        
        protected synchronized a a() {
            return this.a;
        }
        
        protected synchronized void a(final a a) {
            if (this.a != null) {
                this.a.a(false);
            }
            this.a = a;
            if (this.a != null) {
                this.a.a(true);
            }
        }
    }
    
    private class b extends ComponentAdapter
    {
        private int a;
        
        private b() {
            this.a = 0;
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            dk.midas.web.chart.applet.b.this.eq.do();
            try {
                if (!this.a()) {
                    dk.midas.web.chart.applet.b.this.do(true);
                }
            }
            finally {
                dk.midas.web.chart.applet.b.this.eq.a();
            }
        }
        
        public synchronized void if() {
            if (!dk.midas.web.chart.applet.b.this.eq.if()) {
                System.out.println("Parent lock not locked in enableSetLayout()");
            }
            --this.a;
        }
        
        public synchronized void do() {
            if (!dk.midas.web.chart.applet.b.this.eq.if()) {
                System.out.println("Parent lock not locked in disableSetLayout()");
            }
            ++this.a;
        }
        
        public synchronized boolean a() {
            return this.a > 0;
        }
    }
}
