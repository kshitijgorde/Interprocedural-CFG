// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.esial.util.LanguageSupport;
import java.awt.Frame;
import java.awt.Event;
import com.diginet.digichat.awt.j;
import com.diginet.digichat.common.a6;
import com.diginet.digichat.common.bg;
import com.diginet.digichat.awt.bi;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

public class ct extends ShadedDialog
{
    private o a;
    protected bi b;
    protected cu c;
    protected br d;
    private h e;
    
    public final void a(final bg bg) {
        this.c.a(bg);
    }
    
    public final void a(final bf bf) {
        final bg bg = (bg)this.c.d.g();
        if (bg != null && bg.x() == bf.e) {
            this.d.b(bf);
        }
    }
    
    public final boolean b(final bf bf) {
        return this.d.a(bf);
    }
    
    public final void show() {
        final a6 a6 = (a6)this.e.al.d(this.e.b);
        this.c.d.b((j)this.e.ak.d(a6.e));
        this.d.e.b(a6);
        super.show();
        this.toFront();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.a) {
                    this.dispose();
                    return true;
                }
                return super.handleEvent(event);
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public ct(final h e, final Frame frame) {
        super(frame, LanguageSupport.translate("Categories and Rooms"), true);
        this.a = new o(LanguageSupport.translate("Close Window"));
        this.b = null;
        this.c = null;
        this.d = null;
        this.setBackground(e.df.tabsBackground);
        this.setForeground(e.df.tabsText);
        this.e = e;
        this.b = new bi(e.df.roundedCorners);
        this.d = new br(e, frame.getFont());
        this.d.e.resize(200, 200);
        (this.c = new cu(e, this.d)).a((bg)e.ak.d(1000));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.b.setLayout(gridBagLayout);
        this.setResizable(true);
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.b.add(this.c);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        this.b.add(this.d);
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 2;
        final Component a = this.b.a(e.df.roundedCorners);
        a.setBackground(e.df.helpBackground);
        a.setForeground(e.df.helpText);
        gridBagLayout.setConstraints(a, gridBagConstraints);
        this.add(a);
        this.b.a(e.df.helpLogo);
        this.d.b(this.d.d);
        this.d.b(this.d.c);
        this.pack();
    }
}
