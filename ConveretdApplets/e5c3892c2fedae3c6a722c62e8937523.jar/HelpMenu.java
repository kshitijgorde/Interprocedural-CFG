import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class HelpMenu extends JMenu implements ActionListener
{
    private imgViewer applet;
    private JMenuItem dataAcqRequestItem;
    private String glovisTitle;
    private String glovisVersion;
    
    public HelpMenu(final imgViewer applet, final String s) {
        super(s);
        this.setMnemonic(72);
        this.applet = applet;
        this.glovisTitle = "USGS Global Visualization Viewer (GloVis)";
        this.glovisVersion = "Version: 8.3";
        final JMenuItem menuItem = new JMenuItem("Quick Start Guide", 81);
        menuItem.addActionListener(this);
        this.add(menuItem);
        final JMenuItem menuItem2 = new JMenuItem("User Guide", 85);
        menuItem2.addActionListener(this);
        this.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem("About Browse Images", 66);
        menuItem3.addActionListener(this);
        this.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("About GloVis", 71);
        menuItem4.addActionListener(this);
        this.add(menuItem4);
        this.addSeparator();
        final JMenuItem menuItem5 = new JMenuItem("Product Information", 80);
        menuItem5.addActionListener(this);
        this.add(menuItem5);
        final JMenuItem menuItem6 = new JMenuItem("Data Acquisition Schedule", 68);
        menuItem6.addActionListener(this);
        this.add(menuItem6);
        (this.dataAcqRequestItem = new JMenuItem("Data Acquisition Request", 82)).addActionListener(this);
        this.setSensor(applet.sensorMenu.getCurrentSensor());
    }
    
    public void setSensor(final Sensor sensor) {
        if (sensor.dataAcqRequestURL != null) {
            this.insert(this.dataAcqRequestItem, 5);
        }
        else {
            this.remove(this.dataAcqRequestItem);
        }
    }
    
    public void aboutGloVis() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(this.glovisTitle);
        list.add(this.glovisVersion);
        JOptionPane.showMessageDialog(this.applet.getDialogContainer(), list.toArray(), "About GloVis", 1);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        String s = null;
        String s2 = null;
        if (actionCommand.equals("Quick Start Guide")) {
            s = new String("../QuickStart.shtml");
            s2 = new String("glovishelp");
        }
        else if (actionCommand.equals("User Guide")) {
            s = new String("../ImgViewerHelp.shtml");
            s2 = new String("glovishelp");
        }
        else if (actionCommand.equals("About Browse Images")) {
            s = new String("../AboutBrowse.shtml");
            s2 = new String("glovishelp");
        }
        else if (actionCommand.equals("About GloVis")) {
            this.aboutGloVis();
        }
        else if (actionCommand.equals("Product Information")) {
            s = this.applet.sensorMenu.getCurrentSensor().productInfoURL;
            s2 = new String("_blank");
        }
        else if (actionCommand.equals("Data Acquisition Schedule")) {
            s = this.applet.sensorMenu.getCurrentSensor().acquisitionScheduleURL;
            s2 = new String("_blank");
        }
        else if (actionCommand.equals("Data Acquisition Request")) {
            s = this.applet.sensorMenu.getCurrentSensor().dataAcqRequestURL;
            s2 = new String("_blank");
        }
        else if (actionCommand.equals("Home")) {
            s = new String("../index.shtml");
            s2 = new String("_blank");
        }
        if (s != null) {
            try {
                this.applet.getAppletContext().showDocument(new URL(this.applet.getCodeBase(), s), s2);
            }
            catch (Exception ex) {}
        }
    }
}
