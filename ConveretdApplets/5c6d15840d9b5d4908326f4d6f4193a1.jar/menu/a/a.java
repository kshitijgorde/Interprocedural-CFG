// 
// Decompiled by Procyon v0.5.30
// 

package menu.a;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Canvas;

public abstract class a extends Canvas
{
    protected String try;
    protected boolean int;
    protected ActionListener a;
    protected String byte;
    protected Color new;
    protected Color if;
    protected boolean for;
    private int do;
    private int case;
    
    int a() {
        if (this.do == 0) {
            this.do = this.case;
        }
        this.do *= this.case;
        this.do += this.case % 5;
        this.do *= this.do % 7;
        this.do %= 90;
        if (++this.case > 18) {
            this.case = 1;
        }
        return this.do;
    }
    
    public a() {
        this.try = "BS Abstract Button";
        this.setCursor(new Cursor(12));
        this.enableEvents(16L);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.int = true;
                this.repaint();
                break;
            }
            case 502: {
                if (this.a != null && this.int) {
                    this.a.actionPerformed(new ActionEvent(this, 1001, this.try));
                }
                if (this.int) {
                    this.int = false;
                }
                this.repaint();
                break;
            }
            case 504: {
                if (this.for) {
                    if (this.new == null) {
                        this.setBackground(this.getBackground().brighter());
                    }
                    else {
                        this.setBackground(this.new);
                    }
                    if (this.if != null) {
                        this.setForeground(this.if);
                    }
                }
                this.repaint();
                break;
            }
            case 505: {
                if (this.for) {
                    this.setBackground(this.getParent().getBackground());
                    this.setForeground(this.getParent().getForeground());
                }
                if (this.int) {
                    this.int = false;
                }
                this.repaint();
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public synchronized void a(final ActionListener actionListener) {
        if (actionListener != null) {
            this.a = AWTEventMulticaster.add(this.a, actionListener);
        }
    }
    
    void a(final int do1) {
        this.do = do1;
        this.case = 10;
    }
}
