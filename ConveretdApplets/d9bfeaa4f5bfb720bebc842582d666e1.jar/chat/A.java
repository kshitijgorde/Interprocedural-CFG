// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Image;
import java.awt.Component;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Panel;

public abstract class A extends B implements av
{
    private i a;
    private i b;
    public Panel a;
    public boolean a;
    private Vector a;
    private Frame a;
    private bl a;
    
    public final void a(final D d) {
        d.setBackground(this.a.getBackground());
        if (this.a) {
            ((aR)this.a).a(d.getName(), d, null, null);
        }
        else {
            this.a.add("Center", d);
        }
        this.a.addElement(d);
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.a.size(); ++i) {
                ((D)this.a.elementAt(i)).b();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(Event event2, final Object o) {
        if (event.target == this.a) {
            D d;
            for (event2 = (Event)0; event2 < this.a.size(); ++event2) {
                d = this.a.elementAt((int)event2);
                try {
                    d.a();
                }
                catch (T t) {
                    if (this.a) {
                        ((aR)this.a).a((int)event2);
                    }
                    new ad(this.a, ak.a(4), new String[] { ak.a(2) }, new String[] { t.getMessage() }, null, this.a).setVisible(true);
                    return true;
                }
            }
            try {
                this.dispose();
            }
            catch (StackOverflowError stackOverflowError) {}
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
            new ad(this.a, "Warning", new String[] { ak.a(2), ak.a(3) }, new String[] { ak.a(462) }, this, this.a).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final void a(final Object o, final Object o2) {
        if ("OK".equals(o2)) {
            this.dispose();
        }
    }
    
    private A(final Frame frame, final String s, final Image image, final bl bl) {
        this(frame, s, 1, image, true, bl);
    }
    
    public A(final Frame frame, final String s, final bl bl) {
        this(frame, s, bl.a.a, bl);
    }
    
    private A(final Frame frame, final String s, final int n, final Image image, final boolean b, final bl a) {
        super(frame, s);
        this.a = new i(80, 20);
        this.b = new i(80, 20);
        this.a = true;
        this.a = new Vector();
        this.a = a;
        this.a = true;
        this.a = (Frame)this.getParent();
        this.setBackground(this.a.a.a);
        this.a = new aR(this.a);
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
        final int c = this.a.a.c;
        if (super.a == null) {
            super.a = new aM(5, 7, 5, 7, c);
            super.a = new t();
            ((Container)(super.a = new l())).setLayout(new BorderLayout(10, 3));
            super.a.add("West", super.a);
            super.a.add("Center", super.a);
            super.a.setFont(ay.c);
            super.a.a = false;
            final FontMetrics fontMetrics = this.getFontMetrics(ay.c);
            super.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final aM a2;
        (a2 = super.a).setBackground(this.a.a.d);
        a2.setForeground(this.a.a.c);
        layout.setConstraints(a2, gridBagConstraints);
        this.add(a2);
        super.a.b(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(ak.a(3));
        this.b.d();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(ak.a(459));
        this.a.d();
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.a(ak.a(460), "");
        this.b.a(ak.a(461), "");
    }
}
