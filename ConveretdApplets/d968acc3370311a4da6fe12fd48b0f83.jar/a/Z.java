// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Label;
import java.awt.Component;
import java.awt.TextComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public abstract class Z extends F
{
    GridBagConstraints q;
    GridBagLayout q;
    private g q;
    private g w;
    s q;
    TextComponent q;
    private aa q;
    
    public final void q(final String s, final Component component) {
        Component component2 = component;
        final Label label;
        (label = new Label(s)).setFont(m.t);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        this.q.add(label);
        this.q.gridwidth = 0;
        if (component2 instanceof TextComponent) {
            component2.setFont(m.r);
            if (this.q == null) {
                this.q = (TextComponent)component2;
            }
            component2 = new t(component2);
        }
        this.q.setConstraints(component2, this.q);
        this.q.add(component2);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
                    this.q.r();
                    return true;
                }
                if (event.key == 27 || (event.key == 46 && (event.modifiers & 0x4) != 0x0)) {
                    this.w.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    if (this.q()) {
                        this.q.q(this.q());
                        this.dispose();
                        return true;
                    }
                    return false;
                }
                else {
                    if (event.target == this.w) {
                        this.dispose();
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    protected abstract boolean q();
    
    protected abstract String q();
    
    protected Z(final Frame frame, final String s, final boolean b, final aa q) {
        super(frame, true);
        this.q = q;
        this.q = new GridBagConstraints();
        this.q = new GridBagLayout();
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.q = new s();
        this.q = null;
        this.setBackground(cf.w.q);
        this.setTitle(eb.q(s));
        this.setLocation(q.q());
        this.q.setBackground(cf.w.i);
        this.q.setForeground(cf.w.u);
        this.q.setLayout(this.q);
        this.setLayout(this.q);
        this.setResizable(false);
        this.q.insets = new Insets(4, 4, 4, 4);
        this.q.gridwidth = 0;
        this.q.fill = 1;
        this.q.setConstraints(this.q, this.q);
        this.add(this.q);
        this.q.gridheight = -1;
        this.q.weightx = 1.0;
        if (b) {
            final Component q2 = this.q(cf.w.e);
            this.q.setConstraints(q2, this.q);
            this.add(q2);
            this.q(cf.w.e());
            q2.setBackground(cf.w.r);
        }
        this.q.gridheight = 0;
        this.q.gridwidth = -1;
        this.q.anchor = 13;
        this.q.fill = 0;
        this.w.q(eb.q("Cancel"));
        this.w.t();
        this.q.setConstraints(this.w, this.q);
        this.add(this.w);
        this.q.gridwidth = 0;
        this.q.weightx = 0.0;
        this.q.q(eb.q("OK"));
        this.q.t();
        final f f = new f(this.q);
        this.q.setConstraints(f, this.q);
        this.add(f);
        this.q.gridheight = 1;
        this.q.anchor = 18;
    }
}
