// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Dimension;

public class IrisScrollablePanel extends IrisStyledPanel
{
    public boolean vScrollEnabled;
    public boolean hScrollEnabled;
    public int vScrollYoff;
    public int direction;
    public NScrollbar hScroll;
    public NScrollbar vScroll;
    public Dimension vScrollDimension;
    public Dimension hScrollDimension;
    public Dimension PanelDimension;
    
    public IrisScrollablePanel() {
        this.vScrollEnabled = false;
        this.hScrollEnabled = false;
        this.vScrollYoff = 0;
        this.direction = 0;
        this.vScrollDimension = new Dimension();
        this.hScrollDimension = new Dimension();
        this.PanelDimension = new Dimension();
        this.vScrollEnabled = false;
        this.hScrollEnabled = false;
        final NScrollbar nScrollbar = null;
        this.vScroll = nScrollbar;
        this.hScroll = nScrollbar;
    }
    
    public void InitScrollBars() {
        this.setLayout(null);
        this.add(this.vScroll = new NScrollbar(1));
        this.vScrollDimension = this.vScroll.preferredSize();
        this.vScroll.hide();
        this.add(this.hScroll = new NScrollbar(0));
        this.hScrollDimension = this.hScroll.preferredSize();
        this.hScroll.hide();
    }
    
    public void EnableHorzScrollBar(final boolean hScrollEnabled, final Component component) {
        final boolean hScrollEnabled2 = this.hScrollEnabled;
        final Insets insets = this.insets();
        this.hScrollEnabled = hScrollEnabled;
        final int n = this.vScrollEnabled ? this.vScrollDimension.width : 0;
        if (hScrollEnabled) {
            if (!hScrollEnabled2) {
                this.hScroll.show();
            }
            this.hScroll.reshape(insets.left, this.PanelDimension.height - this.hScrollDimension.height - insets.bottom, this.PanelDimension.width - insets.left - insets.right, this.hScrollDimension.height);
            if (this.vScroll.isShowing() && this.hScrollEnabled) {
                this.vScroll.drawCorner(true);
                this.hScroll.drawCorner(true);
            }
            else {
                this.vScroll.drawCorner(false);
                this.hScroll.drawCorner(false);
            }
        }
        else {
            this.hScroll.hide();
            this.vScroll.drawCorner(false);
            this.hScroll.drawCorner(false);
        }
        if (hScrollEnabled2 != this.hScrollEnabled) {
            final int n2 = this.hScrollEnabled ? this.hScrollDimension.height : 0;
            if (component != null) {
                if (this.direction == 1) {
                    component.reshape(this.vScrollEnabled ? (insets.left + n) : insets.left, insets.top, this.PanelDimension.width - n - (insets.left + insets.right), this.PanelDimension.height - n2 - (insets.top + insets.bottom));
                }
                else {
                    component.resize(this.PanelDimension.width - n - (insets.left + insets.right), this.PanelDimension.height - n2 - (insets.top + insets.bottom + this.vScrollYoff));
                }
            }
            if (this.vScrollEnabled && this.hScrollEnabled) {
                this.vScroll.drawCorner(true);
                this.hScroll.drawCorner(true);
            }
            else {
                this.vScroll.drawCorner(false);
                this.hScroll.drawCorner(false);
            }
        }
    }
    
    public void EnableVertScrollBar(final boolean b, final Component component) {
        final boolean vScrollEnabled = this.vScrollEnabled;
        final Insets insets = this.insets();
        this.vScrollEnabled = true;
        final int n = this.hScrollEnabled ? this.hScrollDimension.height : 0;
        if (b) {
            if (!vScrollEnabled) {
                this.vScroll.show();
            }
            this.vScroll.reshape((this.direction == 1) ? insets.left : (this.PanelDimension.width - this.vScrollDimension.width - insets.right), (this.vScrollYoff == 0) ? insets.top : this.vScrollYoff, this.vScrollDimension.width, this.PanelDimension.height - insets.bottom - this.vScrollYoff);
            if (this.hScrollEnabled && this.vScrollEnabled) {
                this.vScroll.drawCorner(true);
                this.hScroll.drawCorner(true);
                this.vScroll.repaint();
                this.hScroll.repaint();
            }
            else {
                this.vScroll.drawCorner(false);
                this.hScroll.drawCorner(false);
                this.vScroll.repaint();
                this.hScroll.repaint();
            }
        }
        else {
            if (this.vScrollEnabled && this.vScroll != null) {
                this.vScroll.hide();
            }
            this.vScrollEnabled = false;
            this.vScroll.drawCorner(false);
            this.hScroll.drawCorner(false);
            this.vScroll.repaint();
            this.hScroll.repaint();
        }
        if (vScrollEnabled != this.vScrollEnabled) {
            final int n2 = this.vScrollEnabled ? this.vScrollDimension.width : 0;
            final int n3 = this.hScrollEnabled ? this.hScrollDimension.height : 0;
            if (component != null) {
                if (this.direction == 1) {
                    component.reshape(this.vScrollEnabled ? (insets.left + n2) : insets.left, insets.top, this.PanelDimension.width - n2 - (insets.left + insets.right), this.PanelDimension.height - n3 - (insets.top + insets.bottom));
                }
                else {
                    component.resize(this.PanelDimension.width - n2 - (insets.left + insets.right), this.PanelDimension.height - n3 - (insets.top + insets.bottom + this.vScrollYoff));
                }
            }
            if (this.hScrollEnabled && this.vScrollEnabled) {
                this.vScroll.drawCorner(true);
                this.hScroll.drawCorner(true);
                this.vScroll.repaint();
                this.hScroll.repaint();
            }
            else {
                this.vScroll.drawCorner(false);
                this.hScroll.drawCorner(false);
                this.vScroll.repaint();
                this.hScroll.repaint();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        synchronized (this) {
            if (event.target == this.vScroll || event.target == this.hScroll) {
                switch (event.id) {
                    case 601:
                    case 602:
                    case 603:
                    case 604:
                    case 605: {
                        return this.ProcessScrollAbsolute((NScrollbar)event.target);
                    }
                }
            }
            return super.handleEvent(event);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1003: {
                return this.ProcessKeyPageDown();
            }
            case 1002: {
                return this.ProcessKeyPageUp();
            }
            case 1004: {
                return this.ProcessKeyUp();
            }
            case 1005: {
                return this.ProcessKeyDown();
            }
            case 1000: {
                return this.ProcessKeyHome();
            }
            case 1001: {
                return this.ProcessKeyEnd();
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean ProcessScrollAbsolute(final NScrollbar nScrollbar) {
        return false;
    }
    
    public boolean ProcessKeyPageDown() {
        this.vScroll.setValue(this.vScroll.getValue() + this.vScroll.getPageIncrement());
        return this.ProcessScrollAbsolute(this.vScroll);
    }
    
    public boolean ProcessKeyPageUp() {
        this.vScroll.setValue(this.vScroll.getValue() - this.vScroll.getPageIncrement());
        return this.ProcessScrollAbsolute(this.vScroll);
    }
    
    public boolean ProcessKeyDown() {
        this.vScroll.setValue(this.vScroll.getValue() + this.vScroll.getLineIncrement());
        return this.ProcessScrollAbsolute(this.vScroll);
    }
    
    public boolean ProcessKeyUp() {
        this.vScroll.setValue(this.vScroll.getValue() - this.vScroll.getLineIncrement());
        return this.ProcessScrollAbsolute(this.vScroll);
    }
    
    public boolean ProcessKeyHome() {
        this.vScroll.setValue(this.vScroll.getMinimum());
        return this.ProcessScrollAbsolute(this.vScroll);
    }
    
    public boolean ProcessKeyEnd() {
        this.vScroll.setValue(this.vScroll.getMaximum());
        return this.ProcessScrollAbsolute(this.vScroll);
    }
    
    public void setDirection(final int n) {
        this.direction = n;
        if (this.hScroll != null) {
            this.hScroll.setDirection(n);
        }
    }
}
