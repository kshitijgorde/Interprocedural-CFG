import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class dd extends db implements ActionListener
{
    private List p;
    
    public dd(final y y, final int n, final int n2) {
        super(y, "List of players");
        this.setLayout(new BorderLayout());
        (this.p = new List()).addActionListener(this);
        this.p.setBackground(Color.white);
        this.add("Center", this.p);
        final Panel panel = new Panel();
        final dl dl;
        panel.add(dl = new dl("Chat"));
        final dl dl2;
        panel.add(dl2 = new dl("Follow"));
        final dl dl3;
        panel.add(dl3 = new dl("Invite"));
        final dl dl4;
        panel.add(dl4 = new dl("Update"));
        final dl dl5;
        panel.add(dl5 = new dl("Close"));
        this.add("South", panel);
        dl.p(this);
        dl2.p(this);
        dl3.p(this);
        dl4.p(this);
        dl5.p(this);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - n) / 2, (screenSize.height - n2) / 2, n, n2);
    }
    
    public final void d() {
        this.p.removeAll();
    }
    
    public final void p(final String s) {
        if (s.length() >= 2 && s.charAt(1) == '.') {
            p(this.p, "0" + s);
            return;
        }
        p(this.p, s);
    }
    
    private static final void p(final List list, final String s) {
        for (int i = 0; i < list.getItemCount(); ++i) {
            final int compareTo = list.getItem(i).toLowerCase().compareTo(s.toLowerCase());
            if (compareTo == 0) {
                return;
            }
            if (compareTo > 0) {
                list.add(s, i);
                return;
            }
        }
        list.add(s);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = super.p.p();
        final String actionCommand = actionEvent.getActionCommand();
        if (actionEvent.getSource() instanceof List) {
            this.setVisible(false);
            p.d("FOLLOW " + du.p(((List)actionEvent.getSource()).getSelectedItem(), 1, ' '));
            return;
        }
        if (actionCommand == "Close") {
            this.setVisible(false);
            return;
        }
        if (actionCommand == "Update") {
            this.setVisible(false);
            p.d("USERLIST");
            return;
        }
        if (actionCommand == "Follow") {
            this.setVisible(false);
            p.d("FOLLOW " + du.p(this.p.getSelectedItem(), 1, ' '));
            return;
        }
        if (actionCommand == "Chat") {
            this.setVisible(false);
            super.p.a(du.p(this.p.getSelectedItem(), 1, ' '));
            return;
        }
        if (actionCommand == "Invite") {
            this.setVisible(false);
            p.d("INVITE " + du.p(this.p.getSelectedItem(), 1, ' '));
            return;
        }
        System.out.println("Unhandle: " + actionEvent);
    }
}
