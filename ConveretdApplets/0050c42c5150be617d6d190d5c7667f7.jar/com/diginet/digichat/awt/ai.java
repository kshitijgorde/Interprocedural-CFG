// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.Event;
import com.diginet.digichat.exceptions.c0;
import com.diginet.digichat.client.h;
import com.esial.util.LanguageSupport;
import java.awt.Component;
import com.diginet.digichat.client.g;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Panel;
import java.awt.event.MouseListener;
import com.diginet.digichat.util.aj;

public abstract class ai extends ak implements aj, MouseListener
{
    private o a;
    private o b;
    private Panel c;
    private boolean d;
    private Vector e;
    private Frame f;
    private g g;
    
    public final void a(final int n) {
        if (this.d) {
            ((bj)this.c).a(n);
        }
    }
    
    public final void a(final al al) {
        al.e = this.f;
        al.setBackground(this.c.getBackground());
        if (this.d) {
            ((bj)this.c).a(al.getName(), al);
        }
        else {
            this.c.add("Center", al);
        }
        this.e.addElement(al);
    }
    
    private final void c() {
        for (int i = 0; i < this.e.size(); ++i) {
            final al al = this.e.elementAt(i);
            try {
                al.a();
            }
            catch (c0 c0) {
                if (this.d) {
                    ((bj)this.c).a(i);
                }
                new am(this.f, LanguageSupport.translate("Error"), new String[] { LanguageSupport.translate("OK") }, new String[] { c0.getMessage() }, null, this.g).setVisible(true);
                return;
            }
        }
        try {
            this.dispose();
        }
        catch (StackOverflowError stackOverflowError) {}
        this.b();
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.e.size(); ++i) {
                ((al)this.e.elementAt(i)).b();
            }
        }
        super.setVisible(visible);
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.c();
        }
        else {
            if (event.target != this.b) {
                return super.action(event, o);
            }
            this.dispose();
        }
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            new am(this.f, "Warning", new String[] { LanguageSupport.translate("OK"), LanguageSupport.translate("Cancel") }, new String[] { LanguageSupport.translate("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.g).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final void a(final Object o, final Object o2) {
        if ("OK".equals(o2)) {
            this.dispose();
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() == this.a) {
            this.c();
        }
        else if (mouseEvent.getComponent() == this.b) {
            this.dispose();
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() == this.c) {
            this.c.mouseDown(new Event(mouseEvent.getSource(), mouseEvent.getID(), null), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public abstract void b();
    
    public ai(final Frame frame, final String s, final int n, final Image image, final g g) {
        this(frame, s, n, image, true, g);
    }
    
    public ai(final Frame frame, final String s, final g g) {
        this(frame, s, 1, g.df.helpLogo, g);
    }
    
    public ai(final Frame frame, final String s, final int n, final Image image, final boolean d, final g g) {
        super(frame, s, false);
        this.a = new o(80, 20);
        this.b = new o(80, 20);
        this.d = true;
        this.e = new Vector();
        this.g = g;
        this.d = d;
        this.f = (Frame)this.getParent();
        this.setBackground(this.g.df.outerBackground);
        if (d) {
            this.c = new bj(2, 10, 2, 10, n, this.g);
        }
        else {
            (this.c = new Panel()).setLayout(new BorderLayout());
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        this.c.addMouseListener(this);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Component b = this.b(this.g.df.roundedCorners);
        b.setBackground(this.g.df.helpBackground);
        b.setForeground(this.g.df.helpText);
        layout.setConstraints(b, gridBagConstraints);
        this.add(b);
        this.a(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(LanguageSupport.translate("Cancel"));
        this.b.f();
        this.b.addMouseListener(this);
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(LanguageSupport.translate("Save"));
        this.a.f();
        this.a.addMouseListener(this);
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.a(LanguageSupport.translate("Click here to save changes to your preferences and close this window."), "");
        this.b.a(LanguageSupport.translate("Click here to discard changes to your preferences and close this window."), "");
    }
}
