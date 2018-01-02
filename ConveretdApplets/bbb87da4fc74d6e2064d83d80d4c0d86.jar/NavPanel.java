import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class NavPanel extends Panel implements ActionListener
{
    protected final int FWD = 1;
    protected final int BWD = 2;
    protected final int GET = 3;
    protected final int ADD = 4;
    private Button bhome;
    private Button bnext;
    private Button bprev;
    private Button bload;
    private password app;
    
    public NavPanel(final password app) {
        this.app = app;
        this.setLayout(new FlowLayout(1, 20, 10));
        (this.bhome = new Button("Home")).addActionListener(this);
        this.add(this.bhome);
        (this.bprev = new Button("Back")).addActionListener(this);
        this.bprev.setEnabled(false);
        this.add(this.bprev);
        (this.bnext = new Button("Next")).addActionListener(this);
        this.bnext.setEnabled(false);
        this.add(this.bnext);
        (this.bload = new Button("Reload")).addActionListener(this);
        this.bload.setEnabled(false);
        this.add(this.bload);
        this.setSize(300, 40);
    }
    
    public void setEnabled(final String s, final boolean b) {
        if (s.equals("Home")) {
            this.bprev.setEnabled(b);
            return;
        }
        if (s.equals("Back")) {
            this.bprev.setEnabled(b);
            return;
        }
        if (s.equals("Next")) {
            this.bnext.setEnabled(b);
            return;
        }
        if (s.equals("Reload")) {
            this.bload.setEnabled(b);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Button button = (Button)actionEvent.getSource();
        if (button.getLabel().equals("Home")) {
            this.app.home();
            return;
        }
        if (button.getLabel().equals("Back")) {
            this.app.setList(2, null);
            return;
        }
        if (button.getLabel().equals("Next")) {
            this.app.setList(1, null);
            return;
        }
        if (button.getLabel().equals("Reload")) {
            this.app.setList(3, null);
        }
    }
}
