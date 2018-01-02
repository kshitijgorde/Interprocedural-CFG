import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class ResolutionMenu extends JMenu implements ActionListener
{
    private imgViewer applet;
    private MosaicData md;
    private JCheckBoxMenuItem[] cb;
    private int currentRes;
    
    public ResolutionMenu(final imgViewer applet, final MosaicData md) {
        super("Resolution");
        this.setMnemonic(82);
        this.applet = applet;
        this.md = md;
        final Sensor currentSensor = applet.sensorMenu.getCurrentSensor();
        this.setMenuItems(currentSensor);
        this.cb[0].setState(true);
        this.currentRes = currentSensor.resolutions[0];
    }
    
    private void setMenuItems(final Sensor sensor) {
        this.removeAll();
        final int length = sensor.resolutions.length;
        this.cb = new JCheckBoxMenuItem[length];
        for (int i = 0; i < length; ++i) {
            (this.cb[i] = new JCheckBoxMenuItem(sensor.getResolutionString(i), false)).addActionListener(this);
            this.add(this.cb[i]);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        int currentRes = this.currentRes;
        final String actionCommand = ((JCheckBoxMenuItem)actionEvent.getSource()).getActionCommand();
        for (int i = 0; i < this.cb.length; ++i) {
            this.cb[i].setState(false);
        }
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        int n = -1;
        for (int j = 0; j < currentSensor.resolutions.length; ++j) {
            if (actionCommand.equals(currentSensor.getResolutionString(j))) {
                this.cb[j].setState(true);
                n = j;
                currentRes = currentSensor.resolutions[j];
                break;
            }
        }
        if (currentRes != this.currentRes) {
            this.currentRes = currentRes;
            this.md.setResolution(currentRes);
            this.applet.statusBar.showResolution(currentSensor.getResolutionString(n));
        }
    }
    
    public int setSensor(final Sensor sensor, final Sensor menuItems) {
        int n = 0;
        if (sensor != null) {
            for (n = 0; n < sensor.resolutions.length - 1 && this.currentRes != sensor.resolutions[n]; ++n) {}
            if (sensor.resolutions.length != menuItems.resolutions.length && n != 0) {
                if (n == sensor.resolutions.length - 1) {
                    n = menuItems.resolutions.length - 1;
                }
                else {
                    n = 0;
                }
            }
        }
        this.setMenuItems(menuItems);
        this.currentRes = menuItems.resolutions[n];
        try {
            this.cb[n].setState(true);
        }
        catch (Exception ex) {}
        this.applet.statusBar.showResolution(menuItems.getResolutionString(n));
        return this.currentRes;
    }
}
