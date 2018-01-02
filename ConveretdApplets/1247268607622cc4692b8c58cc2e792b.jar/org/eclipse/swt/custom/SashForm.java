// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Composite;

public class SashForm extends Composite
{
    public int SASH_WIDTH;
    int sashStyle;
    Sash[] sashes;
    Color background;
    Color foreground;
    Control[] controls;
    Control maxControl;
    Listener sashListener;
    static final int DRAG_MINIMUM = 20;
    
    public SashForm(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.SASH_WIDTH = 3;
        this.sashes = new Sash[0];
        this.background = null;
        this.foreground = null;
        this.controls = new Control[0];
        this.maxControl = null;
        super.setLayout(new SashFormLayout());
        this.sashStyle = (((n & 0x200) != 0x0) ? 256 : 512);
        if ((n & 0x800) != 0x0) {
            this.sashStyle |= 0x800;
        }
        if ((n & 0x10000) != 0x0) {
            this.sashStyle |= 0x10000;
        }
        this.sashListener = new Listener() {
            public void handleEvent(final Event event) {
                SashForm.this.onDragSash(event);
            }
        };
    }
    
    static int checkStyle(final int n) {
        return n & 0x6000800;
    }
    
    Sash createSash() {
        final Sash sash = new Sash(this, this.sashStyle);
        sash.setBackground(this.background);
        sash.setForeground(this.foreground);
        sash.setToolTipText(this.getToolTipText());
        sash.addListener(13, this.sashListener);
        return sash;
    }
    
    public int getOrientation() {
        return ((this.sashStyle & 0x200) != 0x0) ? 256 : 512;
    }
    
    public int getSashWidth() {
        this.checkWidget();
        return this.SASH_WIDTH;
    }
    
    public int getStyle() {
        int n = super.getStyle() | ((this.getOrientation() == 512) ? 512 : 256);
        if ((this.sashStyle & 0x10000) != 0x0) {
            n |= 0x10000;
        }
        return n;
    }
    
    public Control getMaximizedControl() {
        return this.maxControl;
    }
    
    public int[] getWeights() {
        this.checkWidget();
        final Control[] controls = this.getControls(false);
        final int[] array = new int[controls.length];
        for (int i = 0; i < controls.length; ++i) {
            final Object layoutData = controls[i].getLayoutData();
            if (layoutData != null && layoutData instanceof SashFormData) {
                array[i] = (int)(((SashFormData)layoutData).weight * 1000L >> 16);
            }
            else {
                array[i] = 200;
            }
        }
        return array;
    }
    
    Control[] getControls(final boolean b) {
        final Control[] children = this.getChildren();
        Control[] array = new Control[0];
        for (int i = 0; i < children.length; ++i) {
            if (!(children[i] instanceof Sash)) {
                if (!b || children[i].getVisible()) {
                    final Control[] array2 = new Control[array.length + 1];
                    System.arraycopy(array, 0, array2, 0, array.length);
                    array2[array.length] = children[i];
                    array = array2;
                }
            }
        }
        return array;
    }
    
    void onDragSash(final Event event) {
        final Sash sash = (Sash)event.widget;
        int n = -1;
        for (int i = 0; i < this.sashes.length; ++i) {
            if (this.sashes[i] == sash) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        final Control control = this.controls[n];
        final Control control2 = this.controls[n + 1];
        final Rectangle bounds = control.getBounds();
        final Rectangle bounds2 = control2.getBounds();
        final Rectangle bounds3 = sash.getBounds();
        final Rectangle clientArea = this.getClientArea();
        boolean b;
        if (this.getOrientation() == 256) {
            b = (bounds.width < 20 || bounds2.width < 20);
            final int n2 = bounds2.x + bounds2.width - bounds.x;
            final int n3 = event.x - bounds3.x;
            final Rectangle rectangle = bounds;
            rectangle.width += n3;
            final Rectangle rectangle2 = bounds2;
            rectangle2.x += n3;
            final Rectangle rectangle3 = bounds2;
            rectangle3.width -= n3;
            if (bounds.width < 20) {
                bounds.width = 20;
                bounds2.x = bounds.x + bounds.width + bounds3.width;
                bounds2.width = n2 - bounds2.x;
                event.x = bounds.x + bounds.width;
                event.doit = false;
            }
            if (bounds2.width < 20) {
                bounds.width = n2 - 20 - bounds3.width;
                bounds2.x = bounds.x + bounds.width + bounds3.width;
                bounds2.width = 20;
                event.x = bounds.x + bounds.width;
                event.doit = false;
            }
            Object layoutData = control.getLayoutData();
            if (layoutData == null || !(layoutData instanceof SashFormData)) {
                layoutData = new SashFormData();
                control.setLayoutData(layoutData);
            }
            Object layoutData2 = control2.getLayoutData();
            if (layoutData2 == null || !(layoutData2 instanceof SashFormData)) {
                layoutData2 = new SashFormData();
                control2.setLayoutData(layoutData2);
            }
            ((SashFormData)layoutData).weight = ((bounds.width << 16) + clientArea.width - 1L) / clientArea.width;
            ((SashFormData)layoutData2).weight = ((bounds2.width << 16) + clientArea.width - 1L) / clientArea.width;
        }
        else {
            b = (bounds.height < 20 || bounds2.height < 20);
            final int n4 = bounds2.y + bounds2.height - bounds.y;
            final int n5 = event.y - bounds3.y;
            final Rectangle rectangle4 = bounds;
            rectangle4.height += n5;
            final Rectangle rectangle5 = bounds2;
            rectangle5.y += n5;
            final Rectangle rectangle6 = bounds2;
            rectangle6.height -= n5;
            if (bounds.height < 20) {
                bounds.height = 20;
                bounds2.y = bounds.y + bounds.height + bounds3.height;
                bounds2.height = n4 - bounds2.y;
                event.y = bounds.y + bounds.height;
                event.doit = false;
            }
            if (bounds2.height < 20) {
                bounds.height = n4 - 20 - bounds3.height;
                bounds2.y = bounds.y + bounds.height + bounds3.height;
                bounds2.height = 20;
                event.y = bounds.y + bounds.height;
                event.doit = false;
            }
            Object layoutData3 = control.getLayoutData();
            if (layoutData3 == null || !(layoutData3 instanceof SashFormData)) {
                layoutData3 = new SashFormData();
                control.setLayoutData(layoutData3);
            }
            Object layoutData4 = control2.getLayoutData();
            if (layoutData4 == null || !(layoutData4 instanceof SashFormData)) {
                layoutData4 = new SashFormData();
                control2.setLayoutData(layoutData4);
            }
            ((SashFormData)layoutData3).weight = ((bounds.height << 16) + clientArea.height - 1L) / clientArea.height;
            ((SashFormData)layoutData4).weight = ((bounds2.height << 16) + clientArea.height - 1L) / clientArea.height;
        }
        if (b || (event.doit && event.detail != 1)) {
            control.setBounds(bounds);
            sash.setBounds(event.x, event.y, event.width, event.height);
            control2.setBounds(bounds2);
        }
    }
    
    public void setOrientation(final int n) {
        this.checkWidget();
        if (this.getOrientation() == n) {
            return;
        }
        if (n != 256 && n != 512) {
            SWT.error(5);
        }
        this.sashStyle &= 0xFFFFFCFF;
        this.sashStyle |= ((n == 512) ? 256 : 512);
        for (int i = 0; i < this.sashes.length; ++i) {
            this.sashes[i].dispose();
            this.sashes[i] = this.createSash();
        }
        this.layout(false);
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.background = color;
        for (int i = 0; i < this.sashes.length; ++i) {
            this.sashes[i].setBackground(this.background);
        }
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.foreground = color;
        for (int i = 0; i < this.sashes.length; ++i) {
            this.sashes[i].setForeground(this.foreground);
        }
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setMaximizedControl(final Control maxControl) {
        this.checkWidget();
        if (maxControl == null) {
            if (this.maxControl != null) {
                this.maxControl = null;
                this.layout(false);
                for (int i = 0; i < this.sashes.length; ++i) {
                    this.sashes[i].setVisible(true);
                }
            }
            return;
        }
        for (int j = 0; j < this.sashes.length; ++j) {
            this.sashes[j].setVisible(false);
        }
        this.maxControl = maxControl;
        this.layout(false);
    }
    
    public void setSashWidth(final int sash_WIDTH) {
        this.checkWidget();
        if (this.SASH_WIDTH == sash_WIDTH) {
            return;
        }
        this.SASH_WIDTH = sash_WIDTH;
        this.layout(false);
    }
    
    public void setToolTipText(final String s) {
        super.setToolTipText(s);
        for (int i = 0; i < this.sashes.length; ++i) {
            this.sashes[i].setToolTipText(s);
        }
    }
    
    public void setWeights(final int[] array) {
        this.checkWidget();
        final Control[] controls = this.getControls(false);
        if (array == null || array.length != controls.length) {
            SWT.error(5);
        }
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < 0) {
                SWT.error(5);
            }
            n += array[i];
        }
        if (n == 0) {
            SWT.error(5);
        }
        for (int j = 0; j < controls.length; ++j) {
            Object layoutData = controls[j].getLayoutData();
            if (layoutData == null || !(layoutData instanceof SashFormData)) {
                layoutData = new SashFormData();
                controls[j].setLayoutData(layoutData);
            }
            ((SashFormData)layoutData).weight = ((array[j] << 16) + n - 1L) / n;
        }
        this.layout(false);
    }
}
