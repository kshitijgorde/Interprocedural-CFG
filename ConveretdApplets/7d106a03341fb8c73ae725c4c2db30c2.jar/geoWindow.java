import java.awt.Event;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Panel;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.applet.Applet;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class geoWindow extends Dialog
{
    int H_SIZE;
    int V_SIZE;
    mapWindowMaker mwmWorld;
    mapWindowMaker mwmUSA;
    MapWindow windowWorld;
    MapWindow windowUSA;
    int width;
    int height;
    commonData data;
    imageLoader ilMaps;
    helpDialog help;
    fileLoader helpFile;
    String helpString;
    Applet app;
    ResourceCheck checkResources;
    TextField lat1;
    TextField long1;
    TextField lat2;
    TextField long2;
    TextField statusField;
    Scroller scroller;
    wgCheckbox nad27;
    wgCheckbox nad83;
    boolean coord27;
    boolean coord83;
    String homeLat27;
    String homeLong27;
    String homeLat83;
    String homeLong83;
    MessageBox notReadyMessage;
    Geoid clarke1866;
    Geoid grs80;
    
    void userInterface() {
        this.addNotify();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setFont(new Font("Helvetica", 1, 12));
        this.setLayout(layout);
        this.setBackground(Color.lightGray);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.setTitle("GEODETIC II - World Distance Calculator");
        gridBagConstraints.insets = new Insets(5, 5, 0, 0);
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = 13;
        final Label label = new Label("From               ");
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        gridBagConstraints.insets = new Insets(5, 0, 0, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        final Label label2 = new Label("To                   ");
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        final Label label3 = new Label("N. Lat:");
        layout.setConstraints(label3, gridBagConstraints);
        this.add(label3);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.lat1 = new TextField("", 15), gridBagConstraints);
        this.add(this.lat1);
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.lat2 = new TextField("", 15), gridBagConstraints);
        this.add(this.lat2);
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        final Label label4 = new Label("W. Long:");
        layout.setConstraints(label4, gridBagConstraints);
        this.add(label4);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.long1 = new TextField("", 15), gridBagConstraints);
        this.add(this.long1);
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.long2 = new TextField("", 15), gridBagConstraints);
        this.add(this.long2);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.nad27 = new wgCheckbox("NAD 27", checkboxGroup, false);
        this.nad83 = new wgCheckbox("NAD 83", checkboxGroup, false);
        final Panel panel = new Panel();
        panel.add(this.nad27);
        panel.add(this.nad83);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(this.scroller = new Scroller("", 5, 50), gridBagConstraints);
        this.scroller.setBackground(Color.white);
        this.add(this.scroller);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 1;
        final Button button = new Button("World>");
        layout.setConstraints(button, gridBagConstraints);
        this.add(button);
        gridBagConstraints.gridwidth = 2;
        final Button button2 = new Button("U.S.A.>");
        layout.setConstraints(button2, gridBagConstraints);
        this.add(button2);
        gridBagConstraints.gridwidth = 2;
        final Button button3 = new Button("Compute");
        layout.setConstraints(button3, gridBagConstraints);
        this.add(button3);
        gridBagConstraints.gridwidth = 2;
        final Button button4 = new Button("Reset");
        layout.setConstraints(button4, gridBagConstraints);
        this.add(button4);
        gridBagConstraints.gridwidth = 0;
        final Button button5 = new Button("Help");
        layout.setConstraints(button5, gridBagConstraints);
        this.add(button5);
        gridBagConstraints.insets = new Insets(3, 3, 10, 5);
        gridBagConstraints.gridwidth = 0;
        (this.statusField = new TextField("", 50)).setEditable(false);
        layout.setConstraints(this.statusField, gridBagConstraints);
        this.add(this.statusField);
        this.nad83.setState(true);
        if (this.coord83) {
            this.coord83 = this.setHomeFields(this.homeLat83, this.homeLong83);
        }
        else if (this.coord27) {
            this.coord27 = this.setHomeFields(this.homeLat27, this.homeLong27);
            if (this.coord27) {
                this.nad27.setState(true);
            }
        }
        this.scroller.setText("GEODETIC II v1.0 (C)1996 by W.Giel");
        this.resize(this.H_SIZE, this.V_SIZE);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.move((screenSize.width - this.H_SIZE) / 2, (screenSize.height - this.V_SIZE) / 2);
    }
    
    geoWindow(final Applet app) {
        super(new Frame(), true);
        this.H_SIZE = 400;
        this.V_SIZE = 300;
        this.coord27 = false;
        this.coord83 = false;
        this.clarke1866 = new Geoid(6378206.4, 294.978698);
        this.grs80 = new Geoid(6378137.0, 298.2572221008827);
        this.app = app;
        this.homeLat27 = this.app.getParameter("LAT27");
        this.homeLong27 = this.app.getParameter("LONG27");
        if (this.homeLat27 != null && this.homeLong27 != null) {
            this.coord27 = true;
        }
        this.homeLat83 = this.app.getParameter("LAT83");
        this.homeLong83 = this.app.getParameter("LONG83");
        if (this.homeLat83 != null && this.homeLong83 != null) {
            this.coord83 = true;
        }
        this.userInterface();
        (this.ilMaps = new imageLoader(this.app, new String[] { "redpin.gif", "mercator.gif", "us.gif" }, 3, null)).start();
        (this.mwmWorld = new mapWindowMaker(this.ilMaps, 1, this.lat1, this.long1, this.lat2, this.long2)).start();
        (this.mwmUSA = new mapWindowMaker(this.ilMaps, 2, this.lat1, this.long1, this.lat2, this.long2)).start();
        (this.helpFile = new fileLoader(this.app, "geodist.hlp", null)).start();
        (this.checkResources = new ResourceCheck(this.mwmWorld, this.mwmUSA, this.helpFile, this.statusField)).start();
        this.notReadyMessage = new MessageBox("Still loading resource...");
    }
    
    public void paint(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.lightGray);
        graphics.fill3DRect(0, 0, this.size().width, this.size().height, true);
        graphics.setColor(color);
    }
    
    double DecimalDegrees(final String s) {
        double dms2decdeg;
        try {
            dms2decdeg = dms.dms2decdeg(s);
        }
        catch (Invalid_Angular_Data invalid_Angular_Data) {
            this.scroller.appendText("\r\n" + invalid_Angular_Data);
            return 3.141592653589793;
        }
        if (dms2decdeg > 360.0 || dms2decdeg < -360.0) {
            return 3.141592653589793;
        }
        return dms2decdeg;
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("World>")) {
            if (this.windowUSA != null && this.windowUSA.isShowing()) {
                return true;
            }
            if (this.windowWorld == null) {
                if (this.checkResources.areReady()) {
                    this.windowWorld = this.mwmWorld.retrieveMapWindow();
                }
                else if (this.notReadyMessage != null) {
                    this.notReadyMessage.show();
                }
            }
            if (this.windowWorld != null && !this.windowWorld.isShowing()) {
                this.statusField.setText("Preparing world map dialog...");
                if ((this.coord83 && this.nad83.getState()) || (this.coord27 && this.nad27.getState())) {
                    this.windowWorld.show(new Mercator(this.nad83.getState() ? gwConst.MERC_83 : gwConst.MERC_27), commonData.latitude2, commonData.longitude2);
                }
                else {
                    this.windowWorld.show(new Mercator(this.nad83.getState() ? gwConst.MERC_83 : gwConst.MERC_27));
                }
                this.statusField.setText("Ready.");
            }
            return true;
        }
        else if (o.equals("U.S.A.>")) {
            if (this.windowWorld != null && this.windowWorld.isShowing()) {
                return true;
            }
            if (this.windowUSA == null) {
                if (this.checkResources.areReady()) {
                    this.windowUSA = this.mwmUSA.retrieveMapWindow();
                }
                else if (this.notReadyMessage != null) {
                    this.notReadyMessage.show();
                }
            }
            if (this.windowUSA != null && !this.windowUSA.isShowing()) {
                this.statusField.setText("Preparing U.S. Map dialog...");
                if ((this.coord83 && this.nad83.getState()) || (this.coord27 && this.nad27.getState())) {
                    this.windowUSA.show(new Sinusoidal(this.nad83.getState() ? gwConst.SINU_83 : gwConst.SINU_27), commonData.latitude2, commonData.longitude2);
                }
                else {
                    this.windowUSA.show(new Sinusoidal(this.nad83.getState() ? gwConst.SINU_83 : gwConst.SINU_27));
                }
                this.statusField.setText("Ready.");
            }
            return true;
        }
        else if (o.equals("Compute")) {
            this.statusField.setText("Computing...");
            final double decimalDegrees;
            final double decimalDegrees2;
            if ((decimalDegrees = this.DecimalDegrees(this.lat1.getText())) == 3.141592653589793 || (decimalDegrees2 = this.DecimalDegrees(this.long1.getText())) == 3.141592653589793) {
                this.statusField.setText("Ready.");
                return true;
            }
            this.scroller.appendText("\r\nFrom: " + ((decimalDegrees >= 0.0) ? "N " : "S ") + dms.decdeg2dms(Math.abs(decimalDegrees), 3) + ((decimalDegrees2 < 0.0) ? "    E " : "    W ") + dms.decdeg2dms(Math.abs(decimalDegrees2), 3));
            final double decimalDegrees3;
            final double decimalDegrees4;
            if ((decimalDegrees3 = this.DecimalDegrees(this.lat2.getText())) == 3.141592653589793 || (decimalDegrees4 = this.DecimalDegrees(this.long2.getText())) == 3.141592653589793) {
                this.statusField.setText("Ready.");
                return true;
            }
            final AzimuthDistance azimuthDistance = this.nad83.getState() ? this.grs80.RudoeInverse(decimalDegrees, -decimalDegrees2, decimalDegrees3, -decimalDegrees4) : this.clarke1866.RudoeInverse(decimalDegrees, -decimalDegrees2, decimalDegrees3, -decimalDegrees4);
            this.scroller.appendText("\r\nGeodetic Distance: " + Format.toString(azimuthDistance.distance / 1000.0, 3) + " km (" + Format.toString(azimuthDistance.distance / 0.3048 / 5280.0, 4) + " miles)");
            this.scroller.appendText("\r\nGeodetic Azimuth: " + dms.decdeg2dms(azimuthDistance.azimuth, 3) + " (reckoned from North.)");
            this.scroller.appendText("\r\nTo:   " + ((decimalDegrees3 >= 0.0) ? "N " : "S ") + dms.decdeg2dms(Math.abs(decimalDegrees3), 3) + ((decimalDegrees4 < 0.0) ? "    E " : "    W ") + dms.decdeg2dms(Math.abs(decimalDegrees4), 3));
            this.statusField.setText("Ready.");
            return true;
        }
        else {
            if (o.equals("Reset")) {
                this.lat1.setText("");
                this.long1.setText("");
                this.lat2.setText("");
                this.long2.setText("");
                if (this.nad83.getState() && this.coord83) {
                    this.setHomeFields(this.homeLat83, this.homeLong83);
                }
                else if (this.nad27.getState() && this.coord27) {
                    this.setHomeFields(this.homeLat27, this.homeLong27);
                }
                this.scroller.setText("GEODETIC II v1.0 (C)1996 by W.Giel");
                this.statusField.setText("Ready.");
                return true;
            }
            if (!o.equals("Help")) {
                return false;
            }
            if (this.help != null && this.help.isShowing()) {
                return true;
            }
            if (this.help == null) {
                if (this.checkResources.areReady()) {
                    this.helpString = this.helpFile.retrieveFileContent();
                    if (this.helpString != null) {
                        this.help = new helpDialog("Distance Calculator Help", this.helpString);
                    }
                }
                else if (this.notReadyMessage != null) {
                    this.notReadyMessage.show();
                }
            }
            if (this.help != null && !this.help.isShowing()) {
                this.help.show();
            }
            return true;
        }
    }
    
    boolean isUnchanged(final String s, final String s2) {
        double dms2decdeg;
        double dms2decdeg2;
        try {
            dms2decdeg = dms.dms2decdeg(s);
            dms2decdeg2 = dms.dms2decdeg(s2);
        }
        catch (Invalid_Angular_Data invalid_Angular_Data) {
            return false;
        }
        return commonData.longitude2 == dms2decdeg2 && commonData.latitude2 == dms2decdeg;
    }
    
    boolean setHomeFields(final String text, final String text2) {
        try {
            commonData.latitude2 = dms.dms2decdeg(text);
            commonData.longitude2 = dms.dms2decdeg(text2);
        }
        catch (Invalid_Angular_Data invalid_Angular_Data) {
            this.lat2.setText("");
            this.long2.setText("");
            return false;
        }
        this.lat2.setText(text);
        this.long2.setText(text2);
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || (event.id == 401 && event.key == 27)) {
            this.hide();
            return true;
        }
        if (event.id == 1) {
            if (this.nad83.getState()) {
                if (this.coord83 && this.isUnchanged(this.homeLat27, this.homeLong27)) {
                    this.setHomeFields(this.homeLat83, this.homeLong83);
                }
                else {
                    this.lat2.setText("");
                    this.long2.setText("");
                }
            }
            else if (this.coord27 && this.isUnchanged(this.homeLat83, this.homeLong83)) {
                this.setHomeFields(this.homeLat27, this.homeLong27);
            }
            else {
                this.lat2.setText("");
                this.long2.setText("");
            }
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.lat1.requestFocus();
    }
    
    public void finalize() {
        if (this.ilMaps != null && this.ilMaps.isAlive()) {
            this.ilMaps.stop();
        }
        if (this.helpFile != null && this.helpFile.isAlive()) {
            this.helpFile.stop();
        }
        if (this.mwmWorld != null && this.mwmWorld.isAlive()) {
            this.mwmWorld.stop();
        }
        if (this.mwmUSA != null && this.mwmUSA.isAlive()) {
            this.mwmUSA.stop();
        }
        if (this.checkResources != null && this.checkResources.isAlive()) {
            this.checkResources.stop();
        }
    }
}
