// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class PopupList
{
    Shell shell;
    List list;
    int minimumWidth;
    
    public PopupList(final Shell shell) {
        this(shell, 0);
    }
    
    public PopupList(final Shell shell, final int n) {
        int n2 = 516;
        if ((n & 0x100) != 0x0) {
            n2 |= 0x100;
        }
        this.shell = new Shell(shell, checkStyle(n));
        this.list = new List(this.shell, n2);
        this.shell.addListener(27, new Listener() {
            public void handleEvent(final Event event) {
                PopupList.this.shell.setVisible(false);
            }
        });
        this.shell.addControlListener(new ControlListener() {
            public void controlMoved(final ControlEvent controlEvent) {
            }
            
            public void controlResized(final ControlEvent controlEvent) {
                final Rectangle clientArea = PopupList.this.shell.getClientArea();
                PopupList.this.list.setSize(clientArea.width, clientArea.height);
            }
        });
        this.list.addMouseListener(new MouseListener() {
            public void mouseDoubleClick(final MouseEvent mouseEvent) {
            }
            
            public void mouseDown(final MouseEvent mouseEvent) {
            }
            
            public void mouseUp(final MouseEvent mouseEvent) {
                PopupList.this.shell.setVisible(false);
            }
        });
        this.list.addKeyListener(new KeyListener() {
            public void keyReleased(final KeyEvent keyEvent) {
            }
            
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.character == '\r') {
                    PopupList.this.shell.setVisible(false);
                }
            }
        });
    }
    
    private static int checkStyle(final int n) {
        return n & 0x6000000;
    }
    
    public Font getFont() {
        return this.list.getFont();
    }
    
    public String[] getItems() {
        return this.list.getItems();
    }
    
    public int getMinimumWidth() {
        return this.minimumWidth;
    }
    
    public String open(final Rectangle rectangle) {
        final Point computeSize = this.list.computeSize(rectangle.width, -1, false);
        final int y = this.shell.getDisplay().getBounds().height - (rectangle.y + rectangle.height) - 30;
        final int y2 = rectangle.y - 30;
        int n;
        if (y2 > y && computeSize.y > y) {
            if (computeSize.y > y2) {
                computeSize.y = y2;
            }
            else {
                final Point point = computeSize;
                point.y += 2;
            }
            n = rectangle.y - computeSize.y;
        }
        else {
            if (computeSize.y > y) {
                computeSize.y = y;
            }
            else {
                final Point point2 = computeSize;
                point2.y += 2;
            }
            n = rectangle.y + rectangle.height;
        }
        computeSize.x = rectangle.width;
        if (computeSize.x < this.minimumWidth) {
            computeSize.x = this.minimumWidth;
        }
        this.shell.setBounds(rectangle.x + rectangle.width - computeSize.x, n, computeSize.x, computeSize.y);
        this.shell.open();
        this.list.setFocus();
        final Display display = this.shell.getDisplay();
        while (!this.shell.isDisposed() && this.shell.isVisible()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        String s = null;
        if (!this.shell.isDisposed()) {
            final String[] selection = this.list.getSelection();
            this.shell.dispose();
            if (selection.length != 0) {
                s = selection[0];
            }
        }
        return s;
    }
    
    public void select(final String s) {
        final String[] items = this.list.getItems();
        if (s != null) {
            for (int i = 0; i < items.length; ++i) {
                if (items[i].startsWith(s)) {
                    this.list.select(this.list.indexOf(items[i]));
                    break;
                }
            }
        }
    }
    
    public void setFont(final Font font) {
        this.list.setFont(font);
    }
    
    public void setItems(final String[] items) {
        this.list.setItems(items);
    }
    
    public void setMinimumWidth(final int minimumWidth) {
        if (minimumWidth < 0) {
            SWT.error(5);
        }
        this.minimumWidth = minimumWidth;
    }
}
