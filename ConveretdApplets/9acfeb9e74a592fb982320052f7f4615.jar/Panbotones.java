import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Panbotones extends Panel implements ActionListener
{
    Difus padre;
    int id;
    Button pause;
    Button conti;
    Button start;
    Button info;
    static final String[] sb;
    
    public Panbotones(final Difus padre, final Color background) {
        this.padre = padre;
        this.start = new Button(Panbotones.sb[0]);
        this.pause = new Button(Panbotones.sb[1]);
        this.conti = new Button(Panbotones.sb[2]);
        this.info = new Button(Panbotones.sb[3]);
        this.setLayout(new GridLayout(1, 4, 8, 4));
        this.setBackground(background);
        this.start.addActionListener(this);
        this.pause.addActionListener(this);
        this.conti.addActionListener(this);
        this.info.addActionListener(this);
        this.add(this.start);
        this.add(this.pause);
        this.add(this.conti);
        this.add(this.info);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals(Panbotones.sb[0])) {
            this.padre.start();
            return;
        }
        if (actionCommand.equals(Panbotones.sb[1])) {
            this.padre.stop();
            return;
        }
        if (actionCommand.equals(Panbotones.sb[2])) {
            this.padre.continuar();
            return;
        }
        if (actionCommand.equals(Panbotones.sb[3])) {
            this.padre.finfo.show();
            this.info.setLabel(Panbotones.sb[4]);
            return;
        }
        if (actionCommand.equals(Panbotones.sb[4])) {
            this.padre.finfo.setVisible(false);
            this.info.setLabel(Panbotones.sb[3]);
        }
    }
    
    static {
        sb = new String[] { "Start", "Pause", "Continue", "Show Info", "Hide Info" };
    }
}
