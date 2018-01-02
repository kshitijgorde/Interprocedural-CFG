// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.util.Enumeration;
import java.awt.Frame;
import java.awt.Cursor;
import java.awt.Container;
import netcharts.util.NFUtil;
import java.awt.Event;
import netcharts.graphics.NFRegion;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.CardLayout;
import java.util.Vector;
import java.awt.Panel;

public class NFMultiSash extends Panel implements NFGuiObserver
{
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private int g;
    private Vector h;
    private NFSashPanel i;
    private Panel j;
    private Panel k;
    private NFSashImage l;
    private CardLayout m;
    private int n;
    private Component o;
    private NFSashPanel p;
    private NFGuiObserver q;
    private boolean r;
    private Dimension s;
    private Color t;
    private NFSash u;
    private boolean v;
    private int w;
    private int x;
    private Image y;
    private Image z;
    private Graphics aa;
    private Graphics ab;
    private static boolean ac;
    private Dimension ad;
    private Point ae;
    
    public NFMultiSash() {
        this(2);
    }
    
    public NFMultiSash(final int sashLayout) {
        this.g = 2;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = 10;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = false;
        this.s = null;
        this.t = Color.blue;
        this.u = null;
        this.v = false;
        this.w = 0;
        this.x = 0;
        this.ad = null;
        this.ae = null;
        this.setLayout(null);
        this.h = new Vector();
        this.setSashLayout(sashLayout);
        (this.k = new Panel()).setLayout(this.m = new CardLayout());
        (this.i = new NFSashPanel(sashLayout)).addObserver(this);
        (this.j = new Panel()).setLayout(new BorderLayout());
        this.j.add("Center", this.i);
        this.l = new NFSashImage();
        this.k.add("mainPanel", this.j);
        this.k.add("sashImage", this.l);
        this.add(this.k);
    }
    
    public void setSashLayout(final int g) {
        switch (g) {
            case 1:
            case 2: {
                this.g = g;
                break;
            }
        }
    }
    
    public void setDragImageSize(final Dimension s) {
        if (s == null || s.width == 0 || s.height == 0) {
            this.s = null;
        }
        else {
            this.s = s;
        }
    }
    
    public void enableDrag(final boolean r) {
        this.r = r;
    }
    
    public Component addComponent(final Component component) {
        return this.add(component, 0);
    }
    
    public Component addComponent(final Component component, final int n) {
        NFSashPanel nfSashPanel;
        if (n >= this.h.size()) {
            nfSashPanel = new NFSashPanel((this.g == 1) ? 2 : 1);
            nfSashPanel.addObserver(this);
            nfSashPanel.setWidth(this.n);
            this.i.add(nfSashPanel);
            this.h.addElement(nfSashPanel);
        }
        else {
            nfSashPanel = this.h.elementAt(n);
        }
        return nfSashPanel.add(component);
    }
    
    public NFSashPanel addSashPanel(final NFSashPanel nfSashPanel) {
        this.h.addElement(nfSashPanel);
        nfSashPanel.addObserver(this);
        return (NFSashPanel)this.i.add(nfSashPanel);
    }
    
    public void destroy() {
        for (int i = 0; i < this.h.size(); ++i) {
            ((NFSashPanel)this.h.elementAt(i)).destroy();
        }
        this.h.removeAllElements();
        this.i.removeAll();
        this.i = null;
        this.j.removeAll();
        this.j = null;
        this.k.removeAll();
        this.k = null;
        if (this.l != null) {
            this.l.setImage(null);
        }
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.u = null;
        this.removeAll();
    }
    
    public void removeComponent(final Component component) {
        for (int size = this.h.size(), i = 0; i < size; ++i) {
            final NFSashPanel nfSashPanel = this.h.elementAt(i);
            if (nfSashPanel.getManagedComponents().indexOf(component) > 0) {
                nfSashPanel.remove(component);
                return;
            }
        }
    }
    
    public void setSashWidth(final int n) {
        this.n = n;
        this.i.setWidth(this.n);
        for (int size = this.h.size(), i = 0; i < size; ++i) {
            ((NFSashPanel)this.h.elementAt(i)).setWidth(this.n);
        }
    }
    
    public void addObserver(final NFGuiObserver q) {
        this.q = q;
    }
    
    public int countManagedComponents() {
        final int size = this.h.size();
        int n = 0;
        for (int i = 0; i < size; ++i) {
            n += ((NFSashPanel)this.h.elementAt(i)).countManagedComponents();
        }
        return n;
    }
    
    public Vector getManagedComponents() {
        return this.h;
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics);
        super.paint(graphics);
    }
    
    public void paintComponents(final Graphics graphics) {
        this.a(graphics);
        super.paint(graphics);
    }
    
    private void a(final Graphics graphics) {
        this.a(graphics, this.size());
    }
    
    private void a(final Graphics graphics, final Dimension dimension) {
        this.a(graphics, 5, this.getBackground(), dimension);
    }
    
    public void print(final Graphics graphics, final Dimension dimension) {
        this.a(graphics, dimension);
    }
    
    private void a(final Graphics graphics, final int n, final Color color) {
        this.a(graphics, n, color, this.size());
    }
    
    private void a(final Graphics graphics, final int n, final Color color, final Dimension dimension) {
        switch (n) {
            case 5: {
                NFRegion.draw(graphics, 0, 0, dimension.width, this.n, color, 3, 2, Color.black, null, 0);
                NFRegion.draw(graphics, 0, dimension.height - this.n, dimension.width, this.n, color, 3, 2, Color.black, null, 0);
                NFRegion.draw(graphics, 0, this.n, this.n, dimension.height - this.n * 2, color, 3, 2, Color.black, null, 0);
                NFRegion.draw(graphics, dimension.width - this.n, this.n, this.n, dimension.height - this.n * 2, color, 3, 2, Color.black, null, 0);
                break;
            }
            case 1: {
                NFRegion.draw(graphics, 0, 0, dimension.width, this.n, color, 3, 2, Color.black, null, 0);
                break;
            }
            case 3: {
                NFRegion.draw(graphics, 0, dimension.height - this.n, dimension.width, this.n, color, 3, 2, Color.black, null, 0);
                break;
            }
            case 4: {
                NFRegion.draw(graphics, 0, this.n, this.n, dimension.height - this.n * 2, color, 3, 2, Color.black, null, 0);
                break;
            }
            case 2: {
                NFRegion.draw(graphics, dimension.width - this.n, this.n, this.n, dimension.height - this.n * 2, color, 3, 2, Color.black, null, 0);
                break;
            }
        }
    }
    
    public void layout() {
        final Dimension size = this.size();
        this.k.reshape(this.n, this.n, size.width - this.n * 2, size.height - this.n * 2);
        super.layout();
    }
    
    public Dimension preferredSize() {
        final Dimension preferredSize;
        final Dimension dimension = preferredSize = this.i.preferredSize();
        preferredSize.width += this.n * 2;
        final Dimension dimension2 = dimension;
        dimension2.height += this.n * 2;
        return dimension;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.r) {
            return false;
        }
        this.o = this.c(n, n2);
        if (this.o == null) {
            return false;
        }
        this.p = this.findParent(this.o);
        final Point location = this.o.location();
        final Point location2 = this.o.getParent().location();
        if (this.s == null) {
            if (this.g == 1) {
                this.ad = new Dimension(n - location.x, n2 - location.y - location2.y);
            }
            else {
                this.ad = new Dimension(n - location.x - location2.x, n2 - location.y);
            }
        }
        else {
            this.ad = new Dimension(this.s.width / 2, this.s.height / 2);
        }
        final Dimension size = this.size();
        final Dimension size2 = this.o.size();
        if (this.s == null) {
            this.z = this.createImage(size2.width, size2.height);
        }
        else {
            this.z = this.createImage(this.s.width, this.s.height);
        }
        this.ab = this.z.getGraphics();
        this.y = this.createImage(size.width, size.height);
        (this.aa = this.y.getGraphics()).setColor(this.getBackground());
        this.aa.fillRect(0, 0, size.width, size.height);
        this.aa.setColor(Color.black);
        this.validate();
        NFUtil.compPaint(this.aa, this.i);
        if (this.s != null) {
            this.o.resize(this.s);
            this.o.validate();
            NFUtil.compPaint(this.ab, this.o);
            this.o.resize(size2);
            this.o.validate();
        }
        else {
            NFUtil.compPaint(this.ab, this.o);
        }
        this.l.setImage(this.y);
        this.m.show(this.k, "sashImage");
        this.l.setDragTarget(this.z);
        this.y.flush();
        this.z.flush();
        this.ab.dispose();
        this.aa.dispose();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (!this.r) {
            return false;
        }
        if (this.o == null) {
            return false;
        }
        this.l.drawDragImage(n - this.ad.width, n2 - this.ad.height);
        this.b(n, n2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.o == null) {
            return false;
        }
        this.a(n, n2);
        this.m.show(this.k, "mainPanel");
        this.l.flushDragTarget();
        if (this.v) {
            this.repaint();
        }
        this.u = null;
        this.ae = null;
        this.ad = null;
        this.o = null;
        this.p = null;
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.r) {
            return false;
        }
        if (!(this.c(n, n2) instanceof NFSash)) {
            try {
                this.x = this.getCursor().getType();
                this.setCursor(new Cursor(12));
                return true;
            }
            catch (Exception ex) {
                final Frame frame = NFUtil.getFrame(this);
                this.x = frame.getCursorType();
                frame.setCursor(12);
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.r) {
            return false;
        }
        try {
            if (this.getCursor().getType() == 3) {
                this.setCursor(new Cursor(this.x));
                return true;
            }
        }
        catch (Exception ex) {
            final Frame frame = NFUtil.getFrame(this);
            if (frame.getCursorType() == 3) {
                frame.setCursor(this.x);
                return true;
            }
        }
        return false;
    }
    
    private void a(int n, int n2) {
        final Dimension size = this.size();
        boolean b = false;
        NFSashPanel nfSashPanel = null;
        if (n2 <= this.n && this.g == 1) {
            nfSashPanel = new NFSashPanel(2);
            this.h.insertElementAt(nfSashPanel, 0);
            b = true;
        }
        if (n2 >= size.height - this.n && this.g == 1) {
            nfSashPanel = new NFSashPanel(2);
            this.h.addElement(nfSashPanel);
            b = true;
        }
        if (n <= this.n && this.g == 2) {
            nfSashPanel = new NFSashPanel(1);
            this.h.insertElementAt(nfSashPanel, 0);
            b = true;
        }
        if (n >= size.width - this.n && this.g == 2) {
            nfSashPanel = new NFSashPanel(1);
            this.h.addElement(nfSashPanel);
            b = true;
        }
        if (b) {
            this.p.remove(this.o);
            if (this.p.countManagedComponents() < 1) {
                this.h.removeElement(this.p);
            }
            nfSashPanel.add(this.o);
            this.i.removeAll();
            final Enumeration<Component> elements = (Enumeration<Component>)this.h.elements();
            while (elements.hasMoreElements()) {
                this.i.add(elements.nextElement());
            }
            if (this.q != null) {
                this.q.valueChanged(this);
            }
            return;
        }
        final int size2 = this.h.size();
        int i = 0;
        while (i < size2) {
            final NFSashPanel nfSashPanel2 = this.h.elementAt(i);
            final Point location;
            final Point point = location = nfSashPanel2.location();
            location.x += this.n;
            final Point point2 = point;
            point2.y += this.n;
            final Dimension size3 = nfSashPanel2.size();
            if ((n >= 0 && n <= this.n && point.y <= n2 && point.y + size3.height >= n2 && this.g == 1) || (n2 >= 0 && n2 <= this.n && point.x <= n && point.x + size3.width >= n && this.g == 2)) {
                if (this.p.countManagedComponents() <= 1 && nfSashPanel2 == this.p) {
                    return;
                }
                this.p.remove(this.o);
                if (this.p.countManagedComponents() < 1) {
                    this.h.removeElement(this.p);
                    this.i.remove(this.p);
                }
                final Vector<Component> vector = new Vector<Component>();
                vector.addElement(this.o);
                for (int countManagedComponents = nfSashPanel2.countManagedComponents(), j = 0; j < countManagedComponents; ++j) {
                    vector.addElement(nfSashPanel2.getManagedComponent(j));
                }
                nfSashPanel2.removeAll();
                final Enumeration<Component> elements2 = vector.elements();
                while (elements2.hasMoreElements()) {
                    nfSashPanel2.add(elements2.nextElement());
                }
                if (this.q != null) {
                    this.q.valueChanged(this);
                }
            }
            else if ((n >= size.width - this.n && n <= size.width && point.y <= n2 && point.y + size3.height >= n2 && this.g == 1) || (n2 >= size.height - this.n && n2 <= size.height && point.x <= n && point.x + size3.width >= n && this.g == 2)) {
                if (this.p.countManagedComponents() <= 1 && nfSashPanel2 == this.p) {
                    return;
                }
                this.p.remove(this.o);
                if (this.p.countManagedComponents() < 1) {
                    this.h.removeElement(this.p);
                    this.i.remove(this.p);
                }
                nfSashPanel2.add(this.o);
                if (this.q != null) {
                    this.q.valueChanged(this);
                }
            }
            else {
                if (point.x <= n && point.x + size3.width >= n && point.y <= n2 && point.y + size3.height >= n2) {
                    if (this.g == 1) {
                        n2 -= point.y;
                    }
                    else {
                        n -= point.x;
                    }
                    final int countComponents = nfSashPanel2.countComponents();
                    int k = 0;
                    while (k < countComponents) {
                        Component component = nfSashPanel2.getComponent(k);
                        final Point location2;
                        final Point point3 = location2 = component.location();
                        location2.x += this.n;
                        final Point point4 = point3;
                        point4.y += this.n;
                        final Dimension size4 = component.size();
                        if (point3.x <= n && point3.x + size4.width >= n && point3.y <= n2 && point3.y + size4.height >= n2) {
                            if (component instanceof NFSash) {
                                component = nfSashPanel2.getManagedComponent(k / 2);
                            }
                            if (component == this.o) {
                                return;
                            }
                            this.p.remove(this.o);
                            if (this.p.countManagedComponents() < 1) {
                                this.i.remove(this.p);
                                this.h.removeElement(this.p);
                            }
                            final Vector<Component> vector2 = new Vector<Component>();
                            for (int countManagedComponents2 = nfSashPanel2.countManagedComponents(), l = 0; l < countManagedComponents2; ++l) {
                                final Component managedComponent = nfSashPanel2.getManagedComponent(l);
                                vector2.addElement(managedComponent);
                                if (component == managedComponent) {
                                    vector2.addElement(this.o);
                                }
                            }
                            nfSashPanel2.removeAll();
                            final Enumeration<Component> elements3 = vector2.elements();
                            while (elements3.hasMoreElements()) {
                                nfSashPanel2.add(elements3.nextElement());
                            }
                            if (this.q != null) {
                                this.q.valueChanged(this);
                            }
                            return;
                        }
                        else {
                            ++k;
                        }
                    }
                }
                else if ((point.x <= n && point.x + size3.width + this.n >= n && this.g == 2) || (point.y <= n2 && point.y + size3.height + this.n >= n2 && this.g == 1)) {
                    this.p.remove(this.o);
                    if (this.p.countManagedComponents() < 1) {
                        this.i.remove(this.p);
                        this.h.removeElement(this.p);
                    }
                    int n3;
                    if (this.g == 1) {
                        n3 = 2;
                    }
                    else {
                        n3 = 1;
                    }
                    final int index = this.h.indexOf(nfSashPanel2);
                    final NFSashPanel nfSashPanel3 = new NFSashPanel(n3);
                    nfSashPanel3.add(this.o);
                    this.h.insertElementAt(nfSashPanel3, index + 1);
                    this.i.removeAll();
                    for (int size5 = this.h.size(), n4 = 0; n4 < size5; ++n4) {
                        this.i.add((Component)this.h.elementAt(n4));
                    }
                    if (this.q != null) {
                        this.q.valueChanged(this);
                    }
                    return;
                }
                ++i;
            }
        }
    }
    
    public NFSashPanel findParent(final Component component) {
        for (int size = this.h.size(), i = 0; i < size; ++i) {
            final NFSashPanel nfSashPanel = this.h.elementAt(i);
            if (nfSashPanel.getManagedComponents().indexOf(component) >= 0) {
                return nfSashPanel;
            }
        }
        return null;
    }
    
    private Point a(final Component component) {
        final Point location = component.location();
        final Container parent = component.getParent();
        if (parent == this.i) {
            return location;
        }
        for (int size = this.h.size(), i = 0; i < size; ++i) {
            final NFSashPanel nfSashPanel = this.h.elementAt(i);
            if (nfSashPanel == parent) {
                final Point location2 = nfSashPanel.location();
                final Point point = location;
                point.x += location2.x;
                final Point point2 = location;
                point2.y += location2.y;
                return location;
            }
        }
        return null;
    }
    
    private void b(final int n, final int n2) {
        final Dimension size = this.size();
        final Graphics graphics = this.getGraphics();
        if (n2 <= this.n && n2 >= 0) {
            if (this.u != null) {
                this.a(this.u);
                this.u = null;
            }
            else if (this.v && this.w != 1) {
                this.a(graphics, this.w, this.getBackground());
                this.w = 0;
            }
            if (this.w != 1) {
                this.a(graphics, 1, this.t);
                this.v = true;
                this.w = 1;
            }
            graphics.dispose();
            return;
        }
        if (n2 <= size.height && n2 >= size.height - this.n) {
            if (this.u != null) {
                this.a(this.u);
                this.u = null;
            }
            else if (this.v && this.w != 3) {
                this.a(graphics, this.w, this.getBackground());
                this.w = 0;
            }
            if (this.w != 3) {
                this.a(graphics, 3, this.t);
                this.v = true;
                this.w = 3;
            }
            graphics.dispose();
            return;
        }
        if (n >= 0 && n <= this.n && n2 > this.n && n2 < size.height - this.n) {
            if (this.u != null) {
                this.a(this.u);
                this.u = null;
            }
            else if (this.v && this.w != 4) {
                this.a(graphics, this.w, this.getBackground());
                this.w = 0;
            }
            if (this.w != 4) {
                this.a(graphics, 4, this.t);
                this.v = true;
                this.w = 4;
            }
            graphics.dispose();
            return;
        }
        if (n >= size.width - this.n && n <= size.width && n2 > this.n && n2 < size.height - this.n) {
            if (this.u != null) {
                this.a(this.u);
                this.u = null;
            }
            else if (this.v && this.w != 2) {
                this.a(graphics, this.w, this.getBackground());
                this.w = 0;
            }
            if (this.w != 2) {
                this.a(graphics, 2, this.t);
                this.v = true;
                this.w = 2;
            }
            graphics.dispose();
            return;
        }
        final NFSash a = this.a(this.i, n, n2);
        if (a != null) {
            final Point location = a.location();
            final Dimension size2 = a.size();
            if (a != this.u) {
                this.l.hiliteSash(location.x, location.y, size2.width, size2.height, this.t);
            }
            if (this.u != null && this.u != a) {
                this.a(this.u);
            }
            else if (this.v) {
                this.a(graphics);
                this.v = false;
                this.w = 0;
            }
            this.u = a;
            graphics.dispose();
            return;
        }
        for (int size3 = this.h.size(), i = 0; i < size3; ++i) {
            final NFSashPanel nfSashPanel = this.h.elementAt(i);
            final NFSash a2 = this.a(nfSashPanel, n, n2);
            if (a2 != null) {
                this.j.location();
                final Point location2 = nfSashPanel.location();
                final Point location3 = a2.location();
                final Dimension size4 = a2.size();
                final int n3 = location3.x + location2.x;
                final int n4 = location3.y + location2.y;
                if (a2 != this.u) {
                    this.l.hiliteSash(n3, n4, size4.width, size4.height, this.t);
                }
                if (this.u != null && this.u != a2) {
                    this.a(this.u);
                }
                else if (this.v) {
                    this.a(graphics);
                    this.v = false;
                    this.w = 0;
                }
                this.u = a2;
                graphics.dispose();
                return;
            }
        }
        if (this.u != null) {
            this.a(this.u);
        }
        else if (this.v) {
            this.a(graphics);
            this.v = false;
            this.w = 0;
        }
        this.u = null;
        graphics.dispose();
    }
    
    private void a(final NFSash nfSash) {
        final Point a = this.a((Component)this.u);
        final Dimension size = this.u.size();
        this.l.hiliteSash(a.x, a.y, size.width, size.height, this.t);
    }
    
    private NFSash a(final NFSashPanel nfSashPanel, int n, int n2) {
        final Point location = nfSashPanel.location();
        if (nfSashPanel != this.i) {
            final Point point = location;
            point.x += this.n;
            final Point point2 = location;
            point2.y += this.n;
        }
        final Dimension size = nfSashPanel.size();
        if (location.x <= n && location.x + size.width >= n && location.y <= n2 && location.y + size.height >= n2) {
            if (this.g == 1) {
                n2 -= location.y;
            }
            else {
                n -= location.x;
            }
            for (int countComponents = nfSashPanel.countComponents(), i = 0; i < countComponents; ++i) {
                final Component component = nfSashPanel.getComponent(i);
                if (component instanceof NFSash) {
                    final Point location2 = component.location();
                    final Dimension size2 = component.size();
                    final Point point3 = location2;
                    point3.x += this.n;
                    final Point point4 = location2;
                    point4.y += this.n;
                    if (location2.x <= n && location2.x + size2.width >= n && location2.y <= n2 && location2.y + size2.height >= n2) {
                        return (NFSash)component;
                    }
                }
            }
        }
        return null;
    }
    
    private Component c(int n, int n2) {
        for (int size = this.h.size(), i = 0; i < size; ++i) {
            final NFSashPanel nfSashPanel = this.h.elementAt(i);
            final Point location;
            final Point point = location = nfSashPanel.location();
            location.x += this.n;
            final Point point2 = point;
            point2.y += this.n;
            final Dimension size2 = nfSashPanel.size();
            if (point.x <= n && point.x + size2.width >= n && point.y <= n2 && point.y + size2.height >= n2) {
                if (this.g == 1) {
                    n2 -= point.y;
                }
                else {
                    n -= point.x;
                }
                for (int countManagedComponents = nfSashPanel.countManagedComponents(), j = 0; j < countManagedComponents; ++j) {
                    final Component managedComponent = nfSashPanel.getManagedComponent(j);
                    final Point location2 = managedComponent.location();
                    final Dimension size3 = managedComponent.size();
                    final Point point3 = location2;
                    point3.x += this.n;
                    final Point point4 = location2;
                    point4.y += this.n;
                    final int n3 = location2.x + size3.width;
                    if (location2.x <= n && location2.x + size3.width >= n && location2.y <= n2 && location2.y + size3.height >= n2) {
                        return managedComponent;
                    }
                }
            }
        }
        return null;
    }
    
    public void buttonPressed(final Object o, final String s) {
        if (o instanceof NFSashPanel && this.q != null) {
            this.q.buttonPressed(this, s);
        }
    }
    
    public void valueChanged(final Object o) {
    }
    
    static {
        NFMultiSash.ac = true;
    }
}
