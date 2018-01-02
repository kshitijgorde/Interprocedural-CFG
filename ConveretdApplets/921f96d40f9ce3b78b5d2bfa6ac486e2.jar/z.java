import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class z extends db implements ActionListener
{
    private do p;
    private String d;
    
    public z(final y p) {
        super(p, "Table");
        super.p = p;
        this.p = p.p();
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(2, 1));
        final dl dl;
        this.add(dl = new dl("Query"));
        final dl dl2;
        this.add(dl2 = new dl("Join"));
        dl.p(this);
        dl2.p(this);
        this.pack();
    }
    
    public final void setName(String substring) {
        if (substring == null) {
            return;
        }
        this.setTitle(substring);
        if (substring.startsWith("Table #")) {
            substring = substring.substring(7);
        }
        this.d = substring;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = super.p.p();
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand == "Query") {
            this.setVisible(false);
            p.d("QUERYTABLE " + this.d);
            return;
        }
        if (actionCommand == "Join") {
            if (!super.p.d()) {
                this.p.l("<4>*** Please unsit first before going to a different table");
            }
            else {
                this.p.l("<12>*** Joining table " + this.d + "...");
                p.d("JOIN " + this.d);
            }
            this.setVisible(false);
        }
    }
}
