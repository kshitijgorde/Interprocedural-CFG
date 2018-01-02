// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.ItemSelectable;
import java.awt.MenuItem;

public class ai extends MenuItem implements ItemSelectable
{
    protected boolean a;
    protected ItemListener if;
    
    public ai(final String s) {
        super(s);
        this.a = false;
    }
    
    protected void if() {
        Font font = this.getFont();
        if (font == null) {
            final Exception ex = new Exception();
            font = new Font("Dialog", 0, 11);
        }
        this.setFont(new Font(font.getFamily(), (int)(this.a ? 1 : 0), font.getSize()));
    }
    
    public synchronized void a(final boolean a) {
        this.a = a;
        this.if();
    }
    
    public boolean a() {
        return this.a;
    }
    
    public synchronized void addItemListener(final ItemListener if1) {
        if (if1 == null) {
            return;
        }
        this.if = if1;
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        if (itemListener == null) {
            return;
        }
        this.if = null;
    }
    
    protected void a(final ItemEvent itemEvent) {
        this.if();
        if (this.if != null) {
            this.if.itemStateChanged(itemEvent);
        }
    }
    
    public boolean postEvent(final Event event) {
        if (event.target instanceof MenuItem) {
            this.a = !this.a;
            this.a(new ItemEvent(this, 1, this, this.a ? 1 : 2));
            return true;
        }
        return super.postEvent(event);
    }
    
    public synchronized Object[] getSelectedObjects() {
        if (this.a) {
            return new Object[] { this.getLabel() };
        }
        return null;
    }
}
