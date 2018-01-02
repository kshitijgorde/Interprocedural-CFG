// 
// Decompiled by Procyon v0.5.30
// 

package a;

import javax.accessibility.Accessible;
import java.awt.TextComponent;
import java.awt.Label;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Choice;
import java.awt.TextField;

public final class Y extends Z
{
    private TextField q;
    private TextField w;
    private Choice q;
    
    public Y(final Frame frame, String s, final boolean b, final aa aa) {
        super(frame, s, false, aa);
        this.q = new TextField(40);
        this.w = new TextField(5);
        (this.q = new Choice()).add(eb.q("minutes"));
        this.q.add(eb.q("hours"));
        this.q(eb.q("Link url:"), this.q);
        final String q = eb.q("Update every:");
        final TextField w = this.w;
        final Choice q2 = this.q;
        Accessible accessible = w;
        s = q;
        final Label label;
        (label = new Label(s)).setFont(m.t);
        super.q.gridwidth = 1;
        super.q.setConstraints(label, super.q);
        super.q.add(label);
        super.q.gridwidth = -1;
        if (accessible instanceof TextComponent) {
            ((Component)accessible).setFont(m.r);
            if (super.q == null) {
                super.q = (TextField)accessible;
            }
            accessible = new t((Component)accessible);
        }
        super.q.setConstraints((Component)accessible, super.q);
        super.q.add((Component)accessible);
        super.q.gridwidth = 0;
        super.q.add(q2, super.q);
        this.pack();
    }
    
    protected final String q() {
        final StringBuffer sb = new StringBuffer();
        final String text = this.q.getText();
        int int1 = Integer.parseInt(this.w.getText());
        if (this.q.getSelectedIndex() == 1) {
            int1 *= 60;
        }
        sb.append("rss:").append(text).append(":").append(int1);
        return sb.toString();
    }
    
    protected final boolean q() {
        try {
            this.w.getText();
        }
        catch (Exception ex) {
            new b((Frame)this.getParent(), eb.q("Note"), eb.q("You must enter number in this field."), null).setVisible(true);
            this.w.requestFocus();
            return false;
        }
        return true;
    }
}
