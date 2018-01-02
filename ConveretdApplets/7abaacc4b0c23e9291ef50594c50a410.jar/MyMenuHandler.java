import java.awt.event.ItemEvent;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class MyMenuHandler implements ActionListener, ItemListener
{
    Geo geo;
    
    public MyMenuHandler(final Geo geo) {
        this.geo = geo;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Quit...")) {
            this.geo.setVisible(false);
            System.exit(0);
            return;
        }
        if (actionCommand.equals("Reset...")) {
            this.geo.b1 = 0;
            this.geo.arraylength = 0;
            this.geo.b[0] = new Connector(0, 0, this.geo.empty, this.geo.empty);
            this.geo.a[0] = new Line(0, 0, 0, 0);
            this.geo.repaint();
            return;
        }
        if (actionCommand.equals("About...")) {
            new AboutDialog(this.geo, "About").setVisible(true);
            return;
        }
        if (actionCommand.equals("Controls...")) {
            new ControlDialog(this.geo, "Controls").setVisible(true);
        }
        if (actionCommand.equals("Version Info")) {
            new VersionDialog(this.geo, "Version Info").setVisible(true);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
    }
}
