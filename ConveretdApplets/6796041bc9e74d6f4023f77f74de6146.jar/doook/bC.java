// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextArea;

public final class bC extends p
{
    private aS a;
    private aS b;
    private TextArea d;
    private Choice d;
    private u e;
    private aG w;
    private int ai;
    private Checkbox H;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.modifiers == 2 && event.key == 10) {
                    this.d.append("\n");
                    this.d.setCaretPosition(this.d.getText().length());
                    return true;
                }
                if (event.key == 10 || event.key == f.g) {
                    this.a.s();
                    return true;
                }
                if (event.key == 27) {
                    this.b.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final int selectedIndex = this.d.getSelectedIndex();
                    final cD cd = new cD(66307, 1);
                    cd.j = -1;
                    if (this.ai == 1) {
                        cd.a(0, 0, -999);
                        if (selectedIndex == 0) {
                            cd.o = -1;
                        }
                        else {
                            cd.o = ((cF)this.w.a(selectedIndex - 1)).h();
                        }
                    }
                    else {
                        cd.o = -1;
                        if (selectedIndex == 0) {
                            cd.a(0, 0, -1);
                        }
                        else {
                            cd.a(0, 0, ((cF)this.w.a(selectedIndex - 1)).h());
                        }
                    }
                    cd.a(0, 0, ao.e("Chat Broadcast"));
                    cd.a(0, 1, this.d.getText());
                    if (this.H.getState()) {
                        cd.a(0, 7, true);
                    }
                    this.e.o(cd);
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bC(final Frame frame, final u e, final int n, final int ai) {
        super(frame, ao.e("Chat Broadcast"), true);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.d = new TextArea("", 5, 35, 1);
        this.d = new Choice();
        this.setBackground(e.a.a);
        this.ai = ai;
        final cA ca = new cA();
        final cH ch = new cH();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(ao.e("Send to: "));
        final Label label2 = new Label(ao.e("Chat Broadcast"));
        this.e = e;
        if (ai == 1) {
            this.d.addItem(ao.e("ALL ROOMS"));
            this.w = (aG)e.d.clone();
        }
        else {
            this.d.addItem(ao.e("ALL SITES"));
            this.w = (aG)e.m.clone();
        }
        for (int i = 0; i < this.w.b(); ++i) {
            this.d.addItem(((cF)this.w.a(i)).f());
        }
        this.d.select(n);
        this.setResizable(true);
        this.a.a(ao.e("Send"));
        this.a.t();
        this.setLayout(gridBagLayout);
        ca.setLayout(gridBagLayout);
        ca.setBackground(e.a.g);
        ca.setForeground(e.a.f);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label2.setFont(bL.e);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        ca.add(label2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(ch, gridBagConstraints);
        ca.add(ch);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        label.setFont(bL.g);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ca.add(label);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        ca.add(this.d);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final aR ar = new aR(this.d);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        ca.add(ar);
        this.H = new Checkbox(ao.e("Send to super users only."));
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.H, gridBagConstraints);
        ca.add(this.H);
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(ao.e("Cancel"));
        this.b.t();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final aQ aq = new aQ(this.a);
        gridBagLayout.setConstraints(aq, gridBagConstraints);
        this.add(aq);
        this.pack();
        this.d.requestFocus();
    }
}
