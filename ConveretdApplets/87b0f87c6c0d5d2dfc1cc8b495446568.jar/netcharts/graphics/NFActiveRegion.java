// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Frame;
import java.awt.Dimension;
import netcharts.util.NFUtil;
import java.awt.Window;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Event;
import java.util.Date;
import netcharts.util.NFContext;
import java.applet.Applet;
import netcharts.util.NFDebug;
import java.applet.AppletContext;
import java.awt.Component;
import java.awt.Point;
import java.util.Vector;

public class NFActiveRegion implements Runnable
{
    public static final int FRONT = 0;
    public static final int BACK = -1;
    private final boolean a = false;
    private Vector b;
    private Thread c;
    private Point d;
    private long e;
    private boolean f;
    private boolean g;
    private long h;
    private Component i;
    private NFLabel j;
    private AppletContext k;
    private int l;
    private Vector m;
    private ActiveLabelWindow n;
    private boolean o;
    private boolean p;
    private boolean q;
    protected boolean antiAliasing;
    
    public NFActiveRegion(final long n, final NFDwellObserver nfDwellObserver) {
        this.b = null;
        this.d = new Point(-1, -1);
        this.f = false;
        this.g = false;
        this.j = null;
        this.l = 2;
        this.m = new Vector();
        this.n = null;
        this.o = false;
        this.p = false;
        this.q = false;
        this.antiAliasing = false;
        this.a("Created " + this);
        this.a(n, nfDwellObserver);
    }
    
    public Vector getActiveLabels() {
        return this.b;
    }
    
    public void setWindowMode(final boolean o) {
        this.o = o;
    }
    
    public void setToolBarMode(final boolean p) {
        if (p && this.o) {
            this.o = false;
        }
        this.p = p;
    }
    
    private void a(final long h, final NFDwellObserver nfDwellObserver) {
        this.h = h;
        this.addObserver(nfDwellObserver);
        this.b = new Vector();
    }
    
    private void a(final String s) {
        NFDebug.print(16L, "NFActiveRegion: " + s);
    }
    
    public boolean getSelectMode() {
        if (this.j == null) {
            return false;
        }
        final String label = this.j.getLabel();
        return label != null && label.equals("SELECT");
    }
    
    public void addObserver(final NFDwellObserver nfDwellObserver) {
        if (nfDwellObserver == null) {
            return;
        }
        if (this.m.contains(nfDwellObserver)) {
            return;
        }
        this.a("Add observer " + nfDwellObserver);
        this.m.addElement(nfDwellObserver);
    }
    
    public void removeObserver(final NFDwellObserver nfDwellObserver) {
        if (nfDwellObserver == null) {
            return;
        }
        if (!this.m.contains(nfDwellObserver)) {
            return;
        }
        this.a("Remove observer " + nfDwellObserver);
        this.m.removeElement(nfDwellObserver);
    }
    
    public synchronized void setLabel(final NFLabel j, final Component component) {
        this.i = component;
        this.j = j;
        if (this.j != null) {
            this.f = true;
            this.j.setComponent(component);
        }
        else {
            this.f = false;
        }
    }
    
    public NFLabel getLabel() {
        return this.j;
    }
    
    public void setDocument(final Applet applet, final int n) {
        try {
            this.setDocument(applet.getAppletContext(), n);
        }
        catch (Exception ex) {
            this.setDocument((AppletContext)null, n);
        }
    }
    
    public void setDocument(final AppletContext k, final int l) {
        if (k == null) {
            this.g = false;
        }
        else {
            this.g = true;
        }
        this.k = k;
        this.l = l;
    }
    
    public void setClickCount(final int l) {
        this.l = l;
    }
    
    public void start() {
        this.startDwell();
    }
    
    public synchronized void startDwell() {
        if (NFContext.getUserAgentType() == 3) {
            return;
        }
        this.stopDwell();
        this.c = new Thread(this);
        NFDebug.print(16400L, "NFActiveRegion: Thread started = " + this.c);
        this.c.start();
    }
    
    public void stop() {
        this.stopDwell();
    }
    
    public synchronized void stopDwell() {
        if (NFContext.getUserAgentType() == 3) {
            return;
        }
        if (this.c != null) {
            NFDebug.print(16400L, "NFActiveRegion: Thread stopped = " + this.c);
            synchronized (this.c) {
                this.q = true;
                this.c.notify();
            }
            try {
                this.c.join();
            }
            catch (Exception ex) {}
            this.q = false;
            this.c = null;
        }
    }
    
    public synchronized void mousePos(final int x, final int y) {
        if (this.c == null) {
            return;
        }
        this.d.x = x;
        this.d.y = y;
        this.e = new Date().getTime();
        if (x < 0 && y < 0 && (this.o || this.p) && this.n != null) {
            this.n.hide();
        }
        synchronized (this.c) {
            this.c.notify();
        }
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        return this.mouseDown(event, n, n2, true);
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2, final boolean b) {
        if (this.l > 0 && event.clickCount != this.l) {
            return false;
        }
        final NFActiveLabel match = this.findMatch(n, n2);
        if (match == null) {
            return false;
        }
        if (!b) {
            return true;
        }
        final Enumeration<NFDwellObserver> elements = this.m.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().dwellPress(event, n, n2, match)) {
                return true;
            }
        }
        if (!this.g || match.url == null) {
            return true;
        }
        match.activate(this.k);
        return true;
    }
    
    public NFActiveLabel addLabel(final NFActiveLabel nfActiveLabel) {
        return this.addLabel(nfActiveLabel, -1);
    }
    
    public NFActiveLabel addLabel(NFActiveLabel nfActiveLabel, final int n) {
        if (nfActiveLabel == null) {
            nfActiveLabel = new NFActiveLabel();
            nfActiveLabel.autoLabel = true;
        }
        synchronized (this.b) {
            if (n == -1 || n < 0 || n >= this.b.size()) {
                this.b.addElement(nfActiveLabel);
            }
            else {
                this.b.insertElementAt(nfActiveLabel, n);
            }
        }
        return nfActiveLabel;
    }
    
    public NFActiveLabel addLabel(final Vector vector, final int n) {
        NFActiveLabel nfActiveLabel;
        if (vector == null || n < 0 || n >= vector.size()) {
            nfActiveLabel = null;
        }
        else {
            nfActiveLabel = vector.elementAt(n);
        }
        return this.addLabel(nfActiveLabel);
    }
    
    public void addLabels(final Vector vector, final int n) {
        if (vector == null) {
            return;
        }
        for (int size = vector.size(), n2 = 0; n2 < n && n2 < size; ++n2) {
            this.addLabel(vector.elementAt(n2));
        }
    }
    
    public void resetLabels(final Vector vector) {
        if (vector == null) {
            return;
        }
        for (int size = vector.size(), i = 0; i < size; ++i) {
            vector.elementAt(i).reset();
        }
    }
    
    public Vector createSelectLabels(Vector vector, final int n) {
        if (vector == null) {
            vector = new Vector<NFActiveLabel>();
        }
        else {
            this.removeLabel(vector);
            vector.removeAllElements();
        }
        for (int i = 0; i < n; ++i) {
            final NFActiveLabel nfActiveLabel = new NFActiveLabel();
            nfActiveLabel.setLabel("OUTLINE");
            vector.addElement(nfActiveLabel);
            this.addLabel(nfActiveLabel);
        }
        return vector;
    }
    
    public void addPoint(final int n, final int n2, final int n3, final NFActiveLabel nfActiveLabel) {
        this.a("addPoint() has been obsoleted");
        nfActiveLabel.setBounds(n2 - n / 2, n3 - n / 2, n, n);
        this.addLabel(nfActiveLabel);
    }
    
    public void addPolygon(final int n, final int n2, final int n3, final int n4, final NFActiveLabel nfActiveLabel) {
        this.a("addPolygon() has been obsoleted");
        nfActiveLabel.setBounds(n, n2, n3, n4);
        this.addLabel(nfActiveLabel);
    }
    
    public void removeLabel(final NFActiveLabel nfActiveLabel) {
        synchronized (this.b) {
            nfActiveLabel.reset();
            this.b.removeElement(nfActiveLabel);
        }
    }
    
    public void removeLabel(final Vector vector) {
        if (vector == null) {
            return;
        }
        final Enumeration<NFActiveLabel> elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.removeLabel(elements.nextElement());
        }
    }
    
    public void removeAllLabels() {
        synchronized (this.b) {
            this.b.removeAllElements();
        }
    }
    
    public void deleteAllPoints() {
        this.a("deleteAllPoints() has been obsoleted");
        this.removeAllLabels();
    }
    
    public NFActiveLabel findMatch(final int n, final int n2) {
        synchronized (this.b) {
            for (int i = this.b.size() - 1; i >= 0; --i) {
                final NFActiveLabel nfActiveLabel = this.b.elementAt(i);
                if (nfActiveLabel.inside(n, n2)) {
                    return nfActiveLabel;
                }
            }
        }
        return null;
    }
    
    public void run() {
        NFActiveLabel nfActiveLabel;
        NFActiveLabel match = nfActiveLabel = null;
        long e = -1L;
        this.d.x = Integer.MIN_VALUE;
        this.d.y = Integer.MIN_VALUE;
        while (true) {
            try {
                Thread.yield();
            }
            catch (Exception ex) {
                return;
            }
            Label_0111: {
                if (this.j != null) {
                    if (this.d.x != Integer.MIN_VALUE) {
                        break Label_0111;
                    }
                }
                try {
                    synchronized (this.c) {
                        if (this.q) {
                            return;
                        }
                        try {
                            this.c.wait();
                        }
                        catch (Exception ex2) {}
                    }
                    continue;
                }
                catch (Exception ex3) {
                    return;
                }
            }
            if (match == null || this.e != e) {
                match = this.findMatch(this.d.x, this.d.y);
                if (match != null) {
                    e = this.e;
                }
            }
            if (match != null && nfActiveLabel == null) {
                if (new Date().getTime() - this.e < this.h) {
                    continue;
                }
                if (match.inside(this.d.x, this.d.y)) {
                    this.a(match);
                }
                nfActiveLabel = match;
                match = null;
                try {
                    synchronized (this.c) {
                        if (this.q) {
                            return;
                        }
                        try {
                            this.c.wait();
                        }
                        catch (Exception ex4) {}
                    }
                    continue;
                }
                catch (Exception ex5) {
                    return;
                }
            }
            if (nfActiveLabel != null && nfActiveLabel != match) {
                final Enumeration<NFDwellObserver> elements = (Enumeration<NFDwellObserver>)this.m.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().dwellDisplay(false, nfActiveLabel);
                }
                if ((this.o || this.p) && this.n != null) {
                    this.n.hide();
                }
                nfActiveLabel = null;
                try {
                    synchronized (this.c) {
                        if (this.q) {
                            return;
                        }
                        try {
                            this.c.wait();
                        }
                        catch (Exception ex6) {}
                    }
                    continue;
                }
                catch (Exception ex7) {
                    return;
                }
            }
            try {
                synchronized (this.c) {
                    if (this.q) {
                        return;
                    }
                    try {
                        this.c.wait();
                    }
                    catch (Exception ex8) {}
                }
            }
            catch (Exception ex9) {}
        }
    }
    
    private boolean a(final NFActiveLabel nfActiveLabel) {
        final Enumeration<NFDwellObserver> elements = (Enumeration<NFDwellObserver>)this.m.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().dwellDisplay(true, nfActiveLabel)) {
                return false;
            }
        }
        if (!this.f) {
            return false;
        }
        if (nfActiveLabel.label == null || nfActiveLabel.label.length() == 0 || this.j == null || this.j.getLabel() == null || this.j.getLabel().equals("OFF")) {
            return false;
        }
        if (this.o || this.p) {
            this.j.setLabel(nfActiveLabel.label);
            final Dimension bounds = this.j.getBounds(null);
            Component component = this.i;
            int x = this.d.x;
            int n = this.d.y + (this.o ? 2 : 18);
            while (component != null && !(component instanceof Window)) {
                final Point location = component.location();
                x += location.x;
                n += location.y;
                component = component.getParent();
            }
            final Point location2 = component.location();
            final int n2 = x + location2.x;
            final int n3 = n + location2.y;
            final Frame frame = NFUtil.getFrame(this.i);
            if (this.n == null) {
                this.n = new ActiveLabelWindow(frame);
            }
            this.n.reshape(n2, n3, bounds.width, bounds.height);
            this.n.show(nfActiveLabel, this.j, (Window)component);
            return true;
        }
        final Graphics graphics = this.i.getGraphics();
        if (graphics == null) {
            return false;
        }
        synchronized (graphics) {
            if (NFUtil.getJDKVersion() >= 1.2) {
                NFOffscreenImage.setAntiAliasing(graphics, this.antiAliasing);
            }
            if (nfActiveLabel.label.equals("OUTLINE")) {
                nfActiveLabel.drawOutline(graphics, this.j);
            }
            else {
                final String label = this.j.getLabel();
                if (label != null && label.equals("OUTLINE")) {
                    nfActiveLabel.drawOutline(graphics, this.j);
                }
                else {
                    nfActiveLabel.draw(graphics, this.d.x, this.d.y + 2, this.j);
                }
            }
        }
        graphics.dispose();
        return true;
    }
    
    public void setAntiAliasing(final boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }
}
