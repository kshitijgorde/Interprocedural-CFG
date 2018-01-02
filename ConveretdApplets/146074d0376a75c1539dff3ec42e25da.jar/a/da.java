// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class da extends Panel
{
    private static Color q;
    private static Color w;
    protected g q;
    protected g w;
    protected w q;
    protected cM q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof db) {
                    if (((db)event.arg).w) {
                        this.q.e();
                    }
                    else {
                        this.q.q();
                    }
                }
                return true;
            }
            case 702: {
                this.q.e();
                return true;
            }
            case 1001: {
                if (event.target == this.q || event.target == this.q) {
                    this.q.q((db)this.q.q());
                    return true;
                }
                if (event.target == this.w) {
                    this.q.q();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final boolean q(final db db) {
        return this.q.q((aF)db);
    }
    
    public final void q(final db db) {
        synchronized (this.q) {
            final int q;
            if ((q = this.q.q((aF)db)) == -1) {
                this.q.e(db);
            }
            else {
                this.q.q(db, q);
            }
            if (this.q.w() == q) {
                this.q.q(q);
            }
            if (db.y() != 0) {
                this.q.q(db, new Color(db.y()), da.w);
            }
            else if (db.w) {
                this.q.q(db, da.q, da.w);
            }
            else {
                this.q.q(db, Color.black, Color.white);
            }
        }
    }
    
    public da() {
        this.q = new g(eb.q("Enter"));
        this.w = new g(eb.q("New"));
        this.q = new w();
    }
    
    static {
        da.q = new Color(153);
        da.w = new Color(10079487);
    }
}
