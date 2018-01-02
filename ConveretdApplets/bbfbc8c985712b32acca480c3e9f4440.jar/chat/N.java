// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Event;
import java.awt.Component;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Panel;

public abstract class N extends P implements bc
{
    private cr a;
    private cr b;
    public Panel a;
    public boolean a;
    public Vector a;
    private Frame a;
    private cx a;
    
    public final void b() {
        if (this.a) {
            ((bM)this.a).b(2);
        }
    }
    
    public final void a(final S s) {
        s.a = this.a;
        s.setBackground(this.a.getBackground());
        if (this.a) {
            ((bM)this.a).a(s.getName(), s);
        }
        else {
            this.a.add("Center", s);
        }
        this.a.addElement(s);
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.a.size(); ++i) {
                ((S)this.a.elementAt(i)).a();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(Event event2, final Object o) {
        if (event.target == this.a) {
            S s;
            for (event2 = (Event)0; event2 < this.a.size(); ++event2) {
                s = this.a.elementAt((int)event2);
                try {
                    s.b();
                }
                catch (ar ar) {
                    if (this.a) {
                        ((bM)this.a).a((int)event2);
                    }
                    new bD(this.a, aS.a(4), new String[] { aS.a(2) }, new String[] { ar.getMessage() }, null, this.a).setVisible(true);
                    return true;
                }
            }
            try {
                this.dispose();
            }
            catch (StackOverflowError stackOverflowError) {}
            this.a();
        }
        else {
            if (event.target != this.b) {
                return super.action(event, o);
            }
            this.dispose();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            new bD(this.a, "Warning", new String[] { aS.a(2), aS.a(3) }, new String[] { aS.a(462) }, this, this.a).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final S a() {
        for (int i = 0; i < this.a.size(); ++i) {
            final S s;
            if ((s = this.a.elementAt(i)) instanceof cj) {
                return s;
            }
        }
        return null;
    }
    
    public final void a(final Object o, final Object o2) {
        if (aS.a(2).equals(o2)) {
            this.dispose();
        }
    }
    
    public abstract void a();
    
    private N(final Frame frame, final String s, final Image image, final cx cx) {
        this(frame, s, 1, image, cx);
    }
    
    public N(final Frame frame, final String s, final cx cx) {
        this(frame, s, cx.a.a, cx);
    }
    
    private N(final Frame frame, final String s, final int n, final Image image, final cx a) {
        super(frame, s);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = true;
        this.a = new Vector();
        this.a = a;
        this.a = true;
        this.a = (Frame)this.getParent();
        this.setBackground(this.a.a.a);
        this.a = new bM(this.a);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Component a2;
        (a2 = this.a(this.a.a.c)).setBackground(this.a.a.d);
        a2.setForeground(this.a.a.c);
        layout.setConstraints(a2, gridBagConstraints);
        this.add(a2);
        this.a(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(aS.a(3));
        this.b.d();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(aS.a(459));
        this.a.d();
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.a(aS.a(460), "");
        this.b.a(aS.a(461), "");
    }
}
