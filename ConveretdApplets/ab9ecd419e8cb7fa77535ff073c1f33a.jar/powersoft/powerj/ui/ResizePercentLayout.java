// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.ui;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Insets;
import java.awt.Dimension;
import java.io.Serializable;
import java.awt.LayoutManager;

public class ResizePercentLayout implements LayoutManager, Serializable
{
    static final long serialVersionUID = 1293511557001699116L;
    protected Dimension _oldSize;
    protected Insets _oldInsets;
    protected Hashtable _compInfo;
    
    public ResizePercentLayout() {
        this._oldSize = null;
        this._oldInsets = null;
        this._compInfo = new Hashtable();
    }
    
    public Rectangle getResizePercent(final Component comp) {
        final RPLayoutCompInfo info = this._compInfo.get(comp);
        return (info != null) ? info.percent : null;
    }
    
    public void setResizePercent(final Component comp, final Rectangle percent) {
        if (percent == null) {
            this._compInfo.remove(comp);
        }
        else {
            RPLayoutCompInfo info = this._compInfo.get(comp);
            if (info == null) {
                info = new RPLayoutCompInfo(percent);
                this._compInfo.put(comp, info);
            }
            else {
                info.percent = percent;
            }
        }
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
    }
    
    public void removeLayoutComponent(final Component comp) {
        this._compInfo.remove(comp);
    }
    
    public Dimension preferredLayoutSize(final Container parent) {
        return parent.getSize();
    }
    
    public Dimension minimumLayoutSize(final Container parent) {
        return this.preferredLayoutSize(parent);
    }
    
    public void layoutContainer(final Container parent) {
        final Dimension newSize = parent.getSize();
        final Insets newInsets = parent.getInsets();
        if (newSize.width <= 0 || newSize.height <= 0) {
            return;
        }
        if (this._oldSize != null) {
            final int dWidth = newSize.width - this._oldSize.width;
            final int dHeight = newSize.height - this._oldSize.height;
            final int dLeft = newInsets.left - this._oldInsets.left;
            final int dTop = newInsets.top - this._oldInsets.top;
            if (dWidth != 0 || dHeight != 0 || dLeft != 0 || dTop != 0) {
                this.doLayout(parent, dWidth, dHeight, dLeft, dTop);
            }
        }
        this._oldSize = new Dimension(newSize);
        this._oldInsets = (Insets)newInsets.clone();
    }
    
    public void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.writeObject(this._oldSize);
        oos.writeObject(this._oldInsets);
        oos.writeObject(this._compInfo);
    }
    
    public void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this._oldSize = (Dimension)ois.readObject();
        this._oldInsets = (Insets)ois.readObject();
        this._compInfo = (Hashtable)ois.readObject();
    }
    
    private void doLayout(final Container parent, final int deltaWidth, final int deltaHeight, final int deltaLeft, final int deltaTop) {
        final Component[] components = parent.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component comp = components[i];
            final RPLayoutCompInfo info = this._compInfo.get(comp);
            final Rectangle rect = comp.getBounds();
            int dx = 0;
            int dy = 0;
            int dw = 0;
            int dh = 0;
            if (info != null && (deltaWidth != 0 || deltaHeight != 0)) {
                final Rectangle percent = info.percent;
                final Rectangle pending = info.pending;
                if (percent.x > 0) {
                    dx = deltaWidth * percent.x + pending.x;
                    pending.x = dx % 100;
                    dx /= 100;
                }
                else if (percent.x == -1 && this._oldSize.width != 0 && deltaWidth + this._oldSize.width != 0) {
                    dx = deltaWidth * rect.x + pending.x;
                    pending.x = dx % this._oldSize.width;
                    dx /= this._oldSize.width;
                }
                if (percent.y > 0) {
                    dy = deltaHeight * percent.y + pending.y;
                    pending.y = dy % 100;
                    dy /= 100;
                }
                else if (percent.y == -1 && this._oldSize.height != 0 && deltaHeight + this._oldSize.height != 0) {
                    dy = deltaHeight * rect.y + pending.y;
                    pending.y = dy % this._oldSize.height;
                    dy /= this._oldSize.height;
                }
                if (percent.width > 0) {
                    dw = deltaWidth * percent.width + pending.width;
                    pending.width = dw % 100;
                    dw /= 100;
                    if (dw + rect.width < 0) {
                        final Rectangle rectangle = pending;
                        rectangle.width += (rect.width + dw) * 100;
                        dw = -rect.width;
                    }
                }
                else if (percent.width == -1 && this._oldSize.width != 0 && deltaWidth + this._oldSize.width != 0) {
                    dw = deltaWidth * rect.width + pending.width;
                    pending.width = dw % this._oldSize.width;
                    dw /= this._oldSize.width;
                    if (dw + rect.width < 0) {
                        final Rectangle rectangle2 = pending;
                        rectangle2.width += (rect.width + dw) * this._oldSize.width;
                        dw = -rect.width;
                    }
                }
                if (percent.height > 0) {
                    dh = deltaHeight * percent.height + pending.height;
                    pending.height = dh % 100;
                    dh /= 100;
                    if (dh + rect.height < 0) {
                        final Rectangle rectangle3 = pending;
                        rectangle3.height += (rect.height + dh) * 100;
                        dh = -rect.height;
                    }
                }
                else if (percent.height == -1 && this._oldSize.height != 0 && deltaHeight + this._oldSize.height != 0) {
                    dh = deltaHeight * rect.height + pending.height;
                    pending.height = dh % this._oldSize.height;
                    dh /= this._oldSize.height;
                    if (dh + rect.height < 0) {
                        final Rectangle rectangle4 = pending;
                        rectangle4.height += (rect.height + dh) * this._oldSize.height;
                        dh = -rect.height;
                    }
                }
            }
            dx += deltaLeft;
            dy += deltaTop;
            if (dx != 0 || dy != 0 || dw != 0 || dh != 0) {
                comp.setBounds(rect.x + dx, rect.y + dy, rect.width + dw, rect.height + dh);
            }
        }
    }
}
