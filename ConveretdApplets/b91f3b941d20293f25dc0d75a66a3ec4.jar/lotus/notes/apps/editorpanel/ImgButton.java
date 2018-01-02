// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Container;
import java.awt.Point;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Image;
import java.awt.Canvas;

public class ImgButton extends Canvas
{
    public static final boolean DEBUG = false;
    public static final int FILTER_NONE = 0;
    public static final int FILTER_MAX = 100;
    public static final int FILTER_MIN = -100;
    protected Image buttonBg;
    protected Image mouseOver;
    protected Image selectedButtonBg;
    protected Image armedButtonBg;
    protected boolean showSelected;
    protected boolean isSelected;
    protected boolean isArmed;
    protected int selectedFilter;
    protected boolean isOver;
    protected Vector ttText;
    
    public ImgButton(final Image buttonBg, final Image mouseOver) {
        this.mouseOver = null;
        this.selectedButtonBg = null;
        this.armedButtonBg = null;
        this.showSelected = true;
        this.isSelected = false;
        this.isArmed = false;
        this.selectedFilter = -20;
        this.isOver = false;
        this.ttText = null;
        this.buttonBg = buttonBg;
        this.mouseOver = mouseOver;
        Image buttonBg2 = mouseOver;
        if (buttonBg2 == null) {
            buttonBg2 = this.buttonBg;
        }
        if (buttonBg2 != null) {
            this.resize(buttonBg2.getWidth(this), buttonBg2.getHeight(this));
        }
        this.armedButtonBg = this.filterImage(this.buttonBg, -10);
    }
    
    public void setToolTipText(final String s) {
        (this.ttText = new Vector()).addElement(s);
    }
    
    public String getToolTipText() {
        String s = null;
        if (this.ttText != null) {
            s = this.ttText.elementAt(0);
        }
        return s;
    }
    
    public void setToolTipText(final String[] array) {
        this.ttText = new Vector();
        for (int i = 0; i < array.length; ++i) {
            this.ttText.addElement(array[i]);
        }
    }
    
    public String getToolTipText(final int n) {
        String s = null;
        if (this.ttText != null && n < this.ttText.size()) {
            s = this.ttText.elementAt(n);
        }
        return s;
    }
    
    public boolean getSelected() {
        return this.isSelected;
    }
    
    public void setSelected(final boolean isSelected) {
        if (this.isSelected != isSelected) {
            this.isSelected = isSelected;
            if (this.showSelected) {
                if (this.isSelected && this.showSelected && this.selectedButtonBg == null) {
                    this.selectedButtonBg = this.filterImage(this.buttonBg, this.selectedFilter);
                }
                this.repaint();
            }
        }
    }
    
    public void showSelected(final boolean showSelected) {
        this.showSelected = showSelected;
    }
    
    public void setSelectedFilter(final int selectedFilter) {
        this.selectedFilter = selectedFilter;
        if (this.showSelected) {
            this.selectedButtonBg = this.filterImage(this.buttonBg, this.selectedFilter);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        super.mouseEnter(event, n, n2);
        this.showToolTip(0, this.isOver = true);
        this.repaint();
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        super.mouseExit(event, n, n2);
        this.isOver = false;
        this.showToolTip(0, this.isArmed = false);
        if (this.mouseOver != null) {
            this.repaint();
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        super.mouseUp(event, n, n2);
        this.isSelected = !this.isSelected;
        this.isArmed = false;
        this.getParent().postEvent(new Event(this, 1001, null));
        this.repaint();
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        super.mouseDown(event, n, n2);
        this.isArmed = true;
        this.showToolTip(0, false);
        this.repaint();
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.isOver && this.mouseOver != null) {
            graphics.drawImage(this.mouseOver, 0, 0, this);
        }
        Image image;
        if (this.isSelected && this.showSelected) {
            if (this.selectedButtonBg == null) {
                this.selectedButtonBg = this.filterImage(this.buttonBg, this.selectedFilter);
            }
            image = this.selectedButtonBg;
        }
        else if (this.isArmed) {
            image = this.armedButtonBg;
        }
        else {
            image = this.buttonBg;
        }
        if (image != null) {
            graphics.drawImage(image, 0, 0, this);
        }
    }
    
    public Image filterImage(final Image image, final int n) {
        if (image == null) {
            return null;
        }
        final int min = Math.min(Math.max(-100, n), 100);
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        if (min != 0) {
            int n2 = 0;
            if (min > 0) {
                n2 = 255;
            }
            for (int i = 0; i < array.length; ++i) {
                final int n3 = array[i];
                final int n4 = (n3 & 0xFF0000) >> 16;
                final int n5 = (n3 & 0xFF00) >> 8;
                final int n6 = n3 & 0xFF;
                array[i] = (n3 & 0xFF000000) + (n4 + Math.abs(n2 - n4) * min / 100 << 16) + (n5 + Math.abs(n2 - n5) * min / 100 << 8) + (n6 + Math.abs(n2 - n6) * min / 100);
            }
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    protected void showToolTip(final int n, final boolean b) {
        final ToolTip tip;
        if ((tip = this.getTip()) != null) {
            if (this.ttText != null && b && n < this.ttText.size()) {
                tip.setText(this.ttText.elementAt(n));
                final Point calcTipLocation = this.calcTipLocation();
                tip.showTip(this, calcTipLocation.x + this.size().width, calcTipLocation.y);
            }
            else {
                tip.hideTip(this);
            }
        }
    }
    
    private ToolTip getTip() {
        ToolTip toolTip = null;
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof ToolHost); container = container.getParent()) {}
        if (container != null && container instanceof ToolHost) {
            toolTip = ((ToolHost)container).getToolTip();
        }
        return toolTip;
    }
    
    private Point calcTipLocation() {
        final Point location;
        final Point point = location = this.location();
        location.y += this.size().height;
        if (this.getTip() != null) {
            for (Container container = this.getParent(); container != null && !(container instanceof ToolHost); container = container.getParent()) {
                final Point location2 = container.location();
                final Point point2 = point;
                point2.x += location2.x;
                final Point point3 = point;
                point3.y += location2.y;
            }
        }
        return point;
    }
}
