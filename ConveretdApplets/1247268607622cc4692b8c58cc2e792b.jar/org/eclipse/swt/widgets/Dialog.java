// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

public abstract class Dialog
{
    int style;
    Shell parent;
    String title;
    
    public Dialog(final Shell shell) {
        this(shell, 32768);
    }
    
    public Dialog(final Shell parent, final int style) {
        this.checkParent(parent);
        this.parent = parent;
        this.style = style;
        this.title = "";
    }
    
    protected void checkSubclass() {
        if (!Display.isValidClass(this.getClass())) {
            this.error(43);
        }
    }
    
    void checkParent(final Shell shell) {
        if (shell == null) {
            this.error(4);
        }
        shell.checkWidget();
    }
    
    static int checkStyle(final Shell shell, int n) {
        final int n2 = 229376;
        if ((n & 0x10000000) != 0x0) {
            n &= 0xEFFFFFFF;
            if ((n & n2) == 0x0) {
                n |= ((shell == null) ? 65536 : 32768);
            }
        }
        if ((n & n2) == 0x0) {
            n |= 0x10000;
        }
        n &= 0xF7FFFFFF;
        if ((n & 0x6000000) == 0x0 && shell != null) {
            if ((shell.style & 0x2000000) != 0x0) {
                n |= 0x2000000;
            }
            if ((shell.style & 0x4000000) != 0x0) {
                n |= 0x4000000;
            }
        }
        return Widget.checkBits(n, 33554432, 67108864, 0, 0, 0, 0);
    }
    
    void error(final int n) {
        SWT.error(n);
    }
    
    public Shell getParent() {
        return this.parent;
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public String getText() {
        return this.title;
    }
    
    public void setText(final String title) {
        if (title == null) {
            this.error(4);
        }
        this.title = title;
    }
}
