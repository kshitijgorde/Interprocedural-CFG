// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Choice;
import java.awt.CardLayout;
import java.awt.TextField;

public class ci extends aO
{
    private static int g;
    private aS b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private cI c;
    private n c;
    private CardLayout b;
    private Choice h;
    
    public void dispose() {
        super.dispose();
        if (--ci.g > 0) {
            this.c.i();
        }
        else {
            this.c.j();
        }
    }
    
    public void a() {
        this.c.d();
    }
    
    public void a(final bU bu) {
        if (this.h.getSelectedIndex() != this.c.p.a((Object)bu)) {
            this.h.select(this.c.p.a((Object)bu));
        }
        this.c.a();
        switch (bu.h()) {
            case 0: {
                this.c.b.disable();
                this.c.e.disable();
                break;
            }
            case 1: {
                this.c.b.disable();
                this.c.e.enable();
                break;
            }
            default: {
                this.c.b.enable();
                this.c.e.enable();
                break;
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.h) {
                    this.c.a((bU)this.c.p.a(this.h.getSelectedIndex()));
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public ci(final cI c) {
        super(am.a(ao.e("%1 Login"), new String[] { z.G }));
        this.b = new aS(70, 20);
        this.c = new TextField(20);
        this.d = new TextField(20);
        this.e = new TextField(20);
        this.f = new TextField(10);
        this.b = new CardLayout(0, 0);
        this.h = new Choice();
        this.c = c;
        ++ci.g;
        c.p.a(false);
        try {
            for (int i = 0; i < c.p.b(); ++i) {
                this.h.addItem(((bU)c.p.a(i)).f());
            }
        }
        finally {
            c.p.a();
        }
        this.setResizable(false);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cA ca = new cA();
        ca.setLayout(layout);
        this.c = new n(null, c, true, true, true, true);
        final Label label = new Label(ao.e("Connect as "));
        label.setFont(bL.g);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 6);
        layout.setConstraints(label, gridBagConstraints);
        ca.add(label);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.h, gridBagConstraints);
        ca.add(this.h);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.c, gridBagConstraints);
        ca.add(this.c);
        this.setLayout(new BorderLayout());
        this.add("Center", ca);
        this.pack();
        this.c();
        final Rectangle bounds2;
        final Rectangle bounds = bounds2 = this.getBounds();
        bounds2.x += 20 * ci.g;
        final Rectangle rectangle = bounds;
        rectangle.y += 20 * ci.g;
        this.setBounds(bounds);
    }
    
    static {
        ci.g = 0;
    }
}
