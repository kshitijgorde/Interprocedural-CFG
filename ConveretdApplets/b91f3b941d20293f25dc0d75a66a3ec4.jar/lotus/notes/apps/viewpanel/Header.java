// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import lotus.notes.components.StateChangeEvent;
import java.util.Enumeration;
import java.awt.Color;
import lotus.notes.components.huLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;
import java.awt.Component;
import lotus.notes.components.StateChangeListener;

public class Header implements StateChangeListener
{
    private Component component;
    private boolean resizable;
    private int width;
    private int ordinal;
    private int alignment;
    private int resort;
    int baseX;
    private Vector listeners;
    private boolean filler;
    private int sortState;
    private int sortImageIndex;
    private Image[] bulletImages;
    private static Font font;
    public static final int SORT_NONE = 0;
    public static final int SORT_ASCENDING = 1;
    public static final int SORT_DESCENDING = 2;
    
    public Header() {
        this.component = null;
        this.resizable = false;
        this.width = 0;
        this.ordinal = -1;
        this.alignment = 0;
        this.resort = 0;
        this.baseX = -1;
        this.listeners = new Vector();
        this.filler = false;
        this.sortState = 0;
        this.sortImageIndex = 0;
    }
    
    public Header(final int n) {
        this(" ", n, false, 0, 0, -1);
        this.filler = true;
    }
    
    public Header(final Component component, final int width, final boolean resizable, final int alignment, final int resort, final int ordinal) {
        this.component = null;
        this.resizable = false;
        this.width = 0;
        this.ordinal = -1;
        this.alignment = 0;
        this.resort = 0;
        this.baseX = -1;
        this.listeners = new Vector();
        this.filler = false;
        this.sortState = 0;
        this.sortImageIndex = 0;
        this.component = component;
        this.width = width;
        this.resizable = resizable;
        this.ordinal = ordinal;
        this.alignment = alignment;
        this.resort = resort;
    }
    
    public Header(final String s, final int n, final boolean b, final int halign, final int resortType, final int n2) {
        this(new huLabel(s), n, b, halign, resortType, n2);
        this.component.setForeground(Color.black);
        this.component.setBackground(Color.lightGray);
        this.component.setFont(Header.font);
        if (this.component instanceof huLabel) {
            this.setResortType(resortType);
            ((huLabel)this.component).setHalign(halign);
            ((huLabel)this.component).addStateChangeListener(this);
        }
    }
    
    public Header(final ViewColumnInfo viewColumnInfo, final int n, final int n2) {
        this(viewColumnInfo.title, n, viewColumnInfo.resizable, viewColumnInfo.headerAlignment, 0, n2);
        int resortType = 0;
        if (viewColumnInfo.resortAscending && viewColumnInfo.resortDescending) {
            resortType = 3;
        }
        else {
            if (viewColumnInfo.resortAscending) {
                resortType = 1;
            }
            if (viewColumnInfo.resortDescending) {
                resortType = 2;
            }
            if (viewColumnInfo.resortToView) {
                resortType = 4;
            }
        }
        this.setResortType(resortType);
        this.sortState = 0;
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public void setResizable(final boolean resizable) {
        this.resizable = resizable;
    }
    
    public boolean isResizable() {
        return this.resizable;
    }
    
    public void setOrdinal(final int ordinal) {
        this.ordinal = ordinal;
    }
    
    public int getOrdinal() {
        return this.ordinal;
    }
    
    public void setWidth(final int width) {
        if (width != this.width) {
            final int width2 = this.width;
            this.width = width;
            this.component.repaint();
            this.fireResizeEvent(width2);
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setFont(final Font font) {
        this.component.setFont(font);
    }
    
    public void setForeground(final Color color) {
        if (this.component instanceof huLabel) {
            ((huLabel)this.component).setFgColor(color);
        }
        else {
            this.component.setForeground(color);
        }
    }
    
    public void setResortType(final int resort) {
        this.resort = resort;
    }
    
    public void setResortImages(final Image[] bulletImages) {
        this.bulletImages = bulletImages;
        ((huLabel)this.component).setBullet(this.bulletImages[0]);
    }
    
    public boolean isFiller() {
        return this.filler;
    }
    
    public int getSortState() {
        return this.sortState;
    }
    
    public synchronized void addHeaderListener(final HeaderListener headerListener) {
        if (!this.listeners.contains(headerListener)) {
            this.listeners.addElement(headerListener);
        }
    }
    
    public synchronized void removeHeaderListener(final HeaderListener headerListener) {
        this.listeners.removeElement(headerListener);
    }
    
    private synchronized void fireResizeEvent(final int n) {
        final HeaderEvent headerEvent = new HeaderEvent(this, HeaderEvent.HEADER_RESIZED, n);
        final Enumeration<HeaderListener> elements = (Enumeration<HeaderListener>)this.listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().headerResized(headerEvent);
        }
    }
    
    private synchronized void fireStateChangedEvent() {
        final HeaderEvent headerEvent = new HeaderEvent(this, HeaderEvent.HEADER_STATECHANGED);
        final Enumeration<HeaderListener> elements = (Enumeration<HeaderListener>)this.listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().headerStateChanged(headerEvent);
        }
    }
    
    public void stateChanged(final StateChangeEvent stateChangeEvent) {
        if (this.resort != 0) {
            if (this.resort != 4) {
                this.bumpSortState();
                ((HeaderPanel)this.component.getParent()).resetSortState(this);
            }
            this.fireStateChangedEvent();
        }
    }
    
    void resetSortState() {
        if (this.resort != 0) {
            this.sortState = 0;
            this.sortImageIndex = 0;
            ((huLabel)this.component).setBullet(this.bulletImages[this.sortImageIndex]);
        }
    }
    
    private void bumpSortState() {
        if (this.resort == 0 || this.resort == 4) {
            return;
        }
        if (this.resort == 1) {
            if (this.sortState == 1) {
                this.sortState = 0;
            }
            else {
                this.sortState = 1;
            }
            if (this.sortImageIndex == 1) {
                this.sortImageIndex = -1;
            }
        }
        if (this.resort == 2) {
            if (this.sortState == 2) {
                this.sortState = 0;
            }
            else {
                this.sortState = 2;
            }
            if (this.sortImageIndex == 1) {
                this.sortImageIndex = -1;
            }
        }
        if (this.resort == 3) {
            if (this.sortState == 0) {
                this.sortState = 1;
            }
            else if (this.sortState == 1) {
                this.sortState = 2;
            }
            else if (this.sortState == 2) {
                this.sortState = 0;
            }
            if (this.sortImageIndex == 2) {
                this.sortImageIndex = -1;
            }
        }
        ((huLabel)this.component).setBullet(this.bulletImages[++this.sortImageIndex]);
        this.repaint();
    }
    
    public void setDirection(final int n) {
        ((huLabel)this.component).setDirection(n);
        ((huLabel)this.component).setReadingOrder(n);
        final huLabel huLabel = (huLabel)this.component;
        int bulletSide;
        if (n == 0) {
            final huLabel huLabel2 = (huLabel)this.component;
            bulletSide = 1;
        }
        else {
            final huLabel huLabel3 = (huLabel)this.component;
            bulletSide = 0;
        }
        huLabel.setBulletSide(bulletSide);
    }
    
    public void repaint() {
        this.component.repaint();
    }
    
    static {
        Header.font = new Font("Helvetica", 1, 14);
    }
}
