// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.awt.event.MouseEvent;
import JAVACharter.Charter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class f implements MouseListener, MouseMotionListener
{
    public boolean try;
    boolean if;
    private e byte;
    private int for;
    public e[] case;
    public g a;
    private int do;
    private Charter new;
    private boolean int;
    
    public f(final Charter new1) {
        this.try = true;
        this.if = true;
        this.for = 0;
        this.do = -1;
        this.int = true;
        this.new = new1;
    }
    
    public void if(final boolean int1) {
        this.int = int1;
    }
    
    public void a(final boolean b) {
        if (b) {
            this.try = true;
        }
        else {
            this.try = false;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.int) {
            if (this.try) {
                final int if1 = this.a.if(mouseEvent.getX());
                final int a = this.a.a(if1);
                for (int i = 0; i < this.case.length; ++i) {
                    this.case[i].a(a, mouseEvent.getY(), if1);
                    this.for = this.a.if(mouseEvent.getX());
                }
            }
            else {
                (this.byte = (e)mouseEvent.getComponent()).do(this.a.if(mouseEvent.getX()), mouseEvent.getY());
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.int) {
            if (this.try) {
                final int if1 = this.a.if(mouseEvent.getX());
                this.case[0].if(this.a.a(if1), mouseEvent.getY(), if1);
                for (int i = 0; i < this.case.length; ++i) {
                    this.case[i].new();
                }
            }
            else {
                (this.byte = (e)mouseEvent.getComponent()).if(this.a.if(mouseEvent.getX()), mouseEvent.getY());
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.int) {
            if (this.try) {
                final int a = this.a.a(this.a.if(mouseEvent.getX()));
                for (int i = 0; i < this.case.length; ++i) {
                    this.case[i].a(a, mouseEvent.getY());
                }
            }
            else {
                (this.byte = (e)mouseEvent.getComponent()).int(this.a.if(mouseEvent.getX()), mouseEvent.getY());
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.int) {
            final int a = this.a.a(this.a.if(mouseEvent.getX()));
            if (a != this.do) {
                for (int i = 0; i < this.case.length; ++i) {
                    this.case[i].for(a, mouseEvent.getY());
                    this.do = a;
                }
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
}
