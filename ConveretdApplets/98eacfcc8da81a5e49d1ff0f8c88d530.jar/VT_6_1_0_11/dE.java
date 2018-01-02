// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import java.awt.Container;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.hw.client.util.a;
import java.awt.event.ActionListener;

public final class dE extends bD implements ActionListener
{
    private au a;
    
    public dE() {
        super("");
        this.setClosable(false);
    }
    
    public final void setVisible(final boolean b) {
        this.a(b, false);
    }
    
    public final void a(final au au, final String s, final String s2) {
        this.a(au, s, s2, null, null, null, null, null, null);
    }
    
    public final void a(final au au, final String s, final String s2, final String s3, final String s4) {
        this.a(au, s, s2, s3, s4, null, null, null, null);
    }
    
    public final void a(final au au, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        this.a(au, s, s2, s3, s4, s5, s6, null, null);
    }
    
    private void a(final au a, final String title, final String s, final String s2, final String actionCommand, final String s3, final String actionCommand2, final String s4, final String s5) {
        this.a = a;
        if (a.a()) {
            a.b("DialogFrame.open: title(" + title + ") message(" + s + ")");
        }
        final JPanel contentPane;
        (contentPane = new JPanel()).setOpaque(true);
        contentPane.setBackground(h.b());
        contentPane.setLayout(new BorderLayout());
        final aT at;
        (at = new aT("", 0)).setFont(new Font(h.a(), 0, 12));
        at.setBorder(new EmptyBorder(20, 20, 20, 20));
        at.setText("<html><div>" + s + "</div></html>");
        final JPanel panel;
        (panel = new JPanel(new GridBagLayout())).setOpaque(false);
        AbstractButton abstractButton = null;
        if (s2 != null) {
            (abstractButton = new cP(s2)).setOpaque(false);
            abstractButton.setActionCommand(actionCommand);
            abstractButton.addActionListener(this);
            abstractButton.setNextFocusableComponent(abstractButton);
            ca.a(panel, abstractButton, 0, 0, 1, 1, 0.0, 0.0, 10, 10, 10, 10);
        }
        if (s3 != null) {
            final cP nextFocusableComponent;
            (nextFocusableComponent = new cP(s3)).setOpaque(false);
            nextFocusableComponent.setActionCommand(actionCommand2);
            nextFocusableComponent.addActionListener(this);
            if (abstractButton != null) {
                abstractButton.setNextFocusableComponent(nextFocusableComponent);
            }
            nextFocusableComponent.setNextFocusableComponent(abstractButton);
            ca.a(panel, nextFocusableComponent, 1, 0, 1, 1, 0.0, 0.0, 10, 10, 10, 10);
        }
        contentPane.add(at, "Center");
        contentPane.add(panel, "South");
        this.setTitle(title);
        this.setContentPane(contentPane);
        this.revalidate();
        this.setVisible(true);
        this.repaint();
        if (abstractButton != null) {
            if (bj.e()) {
                abstractButton.requestFocus();
            }
        }
        else {
            this.setNextFocusableComponent(this);
            if (bj.e()) {
                this.requestFocus();
            }
        }
    }
    
    public final void a() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("DialogFrame.close");
        }
        this.a(false, false);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.a == null) {
            return;
        }
        this.a.a(this, actionEvent.getActionCommand());
    }
}
