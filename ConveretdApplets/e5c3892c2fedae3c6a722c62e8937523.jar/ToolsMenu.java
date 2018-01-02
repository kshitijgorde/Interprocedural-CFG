import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.event.MenuEvent;
import javax.swing.ToolTipManager;
import javax.swing.KeyStroke;
import java.io.BufferedInputStream;
import java.net.URL;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.MenuListener;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class ToolsMenu extends JMenu implements ActionListener, MenuListener
{
    private imgViewer applet;
    private JCheckBoxMenuItem defaultToDateEnabled;
    private JCheckBoxMenuItem swathModeEnabled;
    private JCheckBoxMenuItem toolTipsEnabled;
    private JMenuItem userDefinedAreaDialog;
    private JMenuItem ndviGraphDialog;
    private boolean ndviDataPresent;
    
    public ToolsMenu(final imgViewer applet) {
        super("Tools");
        this.setMnemonic(84);
        this.applet = applet;
        (this.defaultToDateEnabled = new JCheckBoxMenuItem("Default To Selected Date", false)).setMnemonic(68);
        this.defaultToDateEnabled.addActionListener(this);
        this.add(this.defaultToDateEnabled);
        final JMenuItem menuItem = new JMenuItem("Hide Scene...", 72);
        menuItem.addActionListener(this);
        this.add(menuItem);
        this.ndviDataPresent = false;
        try {
            new BufferedInputStream(new URL(applet.getCodeBase(), "NDVI_EXISTS.TXT").openStream()).close();
            this.ndviDataPresent = true;
        }
        catch (Exception ex) {}
        if (this.ndviDataPresent) {
            (this.ndviGraphDialog = new JMenuItem("NDVI Graph...", 78)).addActionListener(this);
            this.add(this.ndviGraphDialog);
        }
        final JMenuItem menuItem2 = new JMenuItem("Print...", 80);
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(80, 2));
        menuItem2.addActionListener(this);
        this.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem("Refresh", 82);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(82, 2));
        menuItem3.addActionListener(this);
        this.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("Scene List...", 76);
        menuItem4.addActionListener(this);
        this.add(menuItem4);
        final JMenuItem menuItem5 = new JMenuItem("Search For Scene...", 70);
        menuItem5.addActionListener(this);
        this.add(menuItem5);
        final JMenuItem menuItem6 = new JMenuItem("Search Limits...", 83);
        menuItem6.addActionListener(this);
        this.add(menuItem6);
        (this.swathModeEnabled = new JCheckBoxMenuItem("Swath Mode", false)).setMnemonic(77);
        this.swathModeEnabled.addActionListener(this);
        this.add(this.swathModeEnabled);
        (this.toolTipsEnabled = new JCheckBoxMenuItem("Tool Tips", false)).setMnemonic(84);
        this.toolTipsEnabled.setState(ToolTipManager.sharedInstance().isEnabled());
        this.toolTipsEnabled.addActionListener(this);
        this.add(this.toolTipsEnabled);
        (this.userDefinedAreaDialog = new JMenuItem("User Defined Area...", 85)).addActionListener(this);
        this.add(this.userDefinedAreaDialog);
        this.addMenuListener(this);
        this.setSensor(applet.sensorMenu.getCurrentSensor());
    }
    
    public void setSensor(final Sensor sensor) {
        this.defaultToDateEnabled.setVisible(sensor.hasAcqDate);
        this.swathModeEnabled.setVisible(sensor.hasSwathMode);
        if (this.ndviDataPresent) {
            if (sensor.hasNdviLineGraph) {
                this.ndviGraphDialog.setVisible(true);
            }
            else {
                this.ndviGraphDialog.setVisible(false);
            }
        }
        this.userDefinedAreaDialog.setVisible(sensor.hasUserDefinedArea);
        if (!sensor.hasUserDefinedArea && this.applet.userDefinedAreaDialog != null && this.applet.userDefinedAreaDialog.isVisible()) {
            this.applet.userDefinedAreaDialog.setVisible(false);
        }
    }
    
    public boolean isSwathModeEnabled() {
        return this.swathModeEnabled.getState();
    }
    
    public boolean isDefaultToDateEnabled() {
        return this.defaultToDateEnabled.getState();
    }
    
    @Override
    public void menuSelected(final MenuEvent menuEvent) {
        this.toolTipsEnabled.setState(ToolTipManager.sharedInstance().isEnabled());
    }
    
    @Override
    public void menuDeselected(final MenuEvent menuEvent) {
    }
    
    @Override
    public void menuCanceled(final MenuEvent menuEvent) {
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s == null) {
            final JMenuItem menuItem = (JMenuItem)actionEvent.getSource();
            if (menuItem == null) {
                return;
            }
            s = menuItem.getActionCommand();
        }
        final Point dialogLoc;
        final Point point = dialogLoc = this.applet.getDialogLoc();
        dialogLoc.y += 30;
        if (s.equals("Default To Selected Date")) {
            if (this.isDefaultToDateEnabled()) {
                this.applet.md.updateDisplay();
            }
            else {
                this.applet.md.resetTargetDate();
            }
        }
        else if (s.equals("Search For Scene...")) {
            this.applet.searchForSceneDialog.setLocation(point);
            this.applet.searchForSceneDialog.setVisible(true);
        }
        else if (s.equals("Search Limits...")) {
            this.applet.searchLimitDialog.setLocation(point);
            this.applet.searchLimitDialog.setVisible(true);
        }
        else if (s.equals("Scene List...")) {
            this.applet.sceneListDialog.setLocation(point);
            this.applet.sceneListDialog.setVisible(true);
        }
        else if (s.equals("Hide Scene...")) {
            this.applet.hideSceneDialog.setLocation(point);
            this.applet.hideSceneDialog.setVisible(true);
        }
        else if (s.equals("NDVI Graph...")) {
            this.applet.ndviGraphDialog.setLocation(point);
            this.applet.ndviGraphDialog.setVisible(true);
        }
        else if (s.equals("Print...")) {
            this.applet.print();
        }
        else if (s.equals("Refresh")) {
            this.applet.md.refreshDisplay();
        }
        else if (s.equals("User Defined Area...")) {
            this.applet.userDefinedAreaDialog.setLocation(point);
            this.applet.userDefinedAreaDialog.setVisible(true);
        }
        else if (s.equals("Tool Tips")) {
            ToolTipManager.sharedInstance().setEnabled(this.toolTipsEnabled.getState());
        }
        else if (s.equals("Swath Mode")) {
            if (this.isSwathModeEnabled()) {
                this.applet.md.updateDisplay();
            }
            else {
                this.applet.imgArea.repaint();
            }
        }
    }
}
