// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.dw;
import java.awt.Label;
import java.awt.Insets;
import com.diginet.digichat.awt.bj;
import java.awt.GridLayout;
import com.esial.util.c;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Component;
import com.diginet.digichat.awt.r;
import java.awt.Checkbox;
import com.diginet.digichat.awt.ag;

public abstract class InsertBox extends ag
{
    private i iUser;
    private Checkbox chkClose;
    private r btnClose;
    private r btnInsert;
    private TextPanel txpPanel;
    private Component selector;
    
    public InsertBox(final i iUser, final TextPanel txpPanel) {
        super(iUser.y.b(), true);
        this.iUser = iUser;
        this.txpPanel = txpPanel;
    }
    
    protected abstract void setClose(final boolean p0);
    
    protected abstract boolean insert();
    
    public void dispose() {
        super.dispose();
        this.txpPanel.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.chkClose) {
                    this.setClose(this.chkClose.getState());
                    return true;
                }
                if (event.target == this.btnClose) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.btnInsert) {
                    if (this.insert()) {
                        this.dispose();
                    }
                    return true;
                }
                break;
            }
            case 701: {
                this.btnInsert.b();
                break;
            }
            case 702: {
                this.btnInsert.c();
                break;
            }
        }
        return (event.target == this.selector) ? this.selector.handleEvent(event) : super.handleEvent(event);
    }
    
    protected void addSelector(final String title, final Component selector, final boolean b) {
        this.setTitle(title);
        this.setBackground(this.iUser.cc.c);
        this.setForeground(this.iUser.cc.clrOutText);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        final boolean b2 = true;
        gridBagConstraints3.gridheight = (b2 ? 1 : 0);
        gridBagConstraints2.gridwidth = (b2 ? 1 : 0);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Panel panel = new Panel(gridBagLayout);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.chkClose = new Checkbox(c.a("Close after selection"), b), gridBagConstraints);
        panel.add(this.chkClose);
        final Panel panel2 = new Panel(new GridLayout(1, 0, 5, 0));
        panel2.add(this.btnClose = new r(c.a("Close")));
        panel2.add(this.btnInsert = new r(c.a("Insert")));
        this.btnInsert.c();
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(panel2, gridBagConstraints);
        panel.add(panel2);
        final bj bj;
        (bj = new bj()).setLayout(gridBagLayout);
        bj.setBackground(this.iUser.cc.d);
        bj.setForeground(this.iUser.cc.clrInnText);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final Label label = new Label(title, 0);
        label.setFont(dw.a);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        bj.add(label);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagLayout.setConstraints(this.selector = selector, gridBagConstraints);
        bj.add(selector);
        this.setLayout(gridBagLayout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(bj, gridBagConstraints);
        this.add(bj);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        this.pack();
        this.resize(350, 300);
        this.show();
    }
}
