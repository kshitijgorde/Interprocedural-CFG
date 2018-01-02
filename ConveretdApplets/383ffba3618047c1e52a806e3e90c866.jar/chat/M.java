// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Enumeration;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class M extends C
{
    private be a;
    private W a;
    private i a;
    private i b;
    private static Checkbox a;
    private TextArea a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.c();
                    return true;
                }
                break;
            }
            case 201: {
                this.a.c();
                return true;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    try {
                        this.a.append(" " + this.a.a().c);
                        if (M.a.getState()) {
                            this.dispose();
                        }
                    }
                    catch (Exception ex) {}
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public M(final Frame frame, final bh bh, final TextArea a) {
        super(frame, ak.a(531), true);
        super.a = new i(80, 20);
        this.a = new i(80, 20);
        this.b = new i(80, 20);
        if (M.a == null) {
            (M.a = new Checkbox(ak.a(529))).setForeground(bh.a.g);
            M.a.setState(true);
        }
        this.setBackground(bh.a.a);
        final aM am = new aM();
        final bb bb = new bb();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final aw aw = new aw(ak.a(531), (byte)0);
        this.a = a;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        am.setLayout(gridBagLayout);
        am.setBackground(bh.a.h);
        am.setForeground(bh.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        aw.setFont(ay.a);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        am.add(aw);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(bb, gridBagConstraints);
        am.add(bb);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.a = new W(1000);
        (this.a = new be(bh)).setSize(350, 200);
        this.a.a.a();
        this.b();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        am.add(this.a);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(am, gridBagConstraints);
        this.add(am);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(M.a, gridBagConstraints);
        this.add(M.a);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.a.a(ak.a(95));
        this.a.d();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.b.a(ak.a(530));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.pack();
    }
    
    private void b() {
        this.a.a();
        final Enumeration a = K.a();
        while (a.hasMoreElements()) {
            this.a.a(a.nextElement());
        }
        for (int i = 0; i < this.a.a(); ++i) {
            this.a.a.a((R)this.a.a(i));
        }
    }
}
