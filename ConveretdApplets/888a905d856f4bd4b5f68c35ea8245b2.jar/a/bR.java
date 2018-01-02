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

public final class bR extends cD
{
    private TextField q;
    private TextField w;
    private Choice q;
    
    public bR(final Frame frame, String s, final boolean b, final dP dp) {
        super(frame, s, false, dp);
        this.q = new TextField(40);
        this.w = new TextField(5);
        (this.q = new Choice()).add(be.w("minutes"));
        this.q.add(be.w("hours"));
        this.q(be.w("Link url:"), this.q);
        final String w = be.w("Update every:");
        final TextField w2 = this.w;
        final Choice q = this.q;
        Accessible accessible = w2;
        s = w;
        final Label label;
        (label = new Label(s)).setFont(cb.t);
        super.q.gridwidth = 1;
        super.q.setConstraints(label, super.q);
        super.q.add(label);
        super.q.gridwidth = -1;
        if (accessible instanceof TextComponent) {
            ((Component)accessible).setFont(cb.r);
            if (super.q == null) {
                super.q = (TextField)accessible;
            }
            accessible = new bZ((Component)accessible);
        }
        super.q.setConstraints((Component)accessible, super.q);
        super.q.add((Component)accessible);
        super.q.gridwidth = 0;
        super.q.add(q, super.q);
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
            new dd((Frame)this.getParent(), be.w("Note"), be.w("You must enter number in this field."), null).setVisible(true);
            this.w.requestFocus();
            return false;
        }
        return true;
    }
}
