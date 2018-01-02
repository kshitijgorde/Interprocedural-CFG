import java.awt.Point;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class LatLongEntry extends JPanel implements ActionListener, Observer
{
    private FocusTextField latitude;
    private FocusTextField longitude;
    private JButton goButton;
    private MosaicData md;
    private imgViewer applet;
    
    LatLongEntry(final imgViewer applet, final MosaicData md) {
        this.md = md;
        this.applet = applet;
        this.setLayout(new BoxLayout(this, 0));
        this.setFont(applet.normalFont);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        final JLabel label = new JLabel("Lat/");
        label.setFont(applet.boldFont);
        panel.add(label);
        final JLabel label2 = new JLabel("Long:");
        label2.setFont(applet.boldFont);
        panel.add(label2);
        this.add(panel);
        (this.latitude = new FocusTextField(5)).setToolTipText("Latitude entry");
        this.latitude.addActionListener(this);
        this.add(this.latitude);
        (this.longitude = new FocusTextField(6)).setToolTipText("Longitude entry");
        this.longitude.addActionListener(this);
        this.add(this.longitude);
        (this.goButton = new JButton("Go")).setToolTipText("Go to Lat/Long");
        this.goButton.addActionListener(this);
        this.add(this.goButton);
        final Dimension preferredSize = this.getPreferredSize();
        preferredSize.width = 100;
        this.setMinimumSize(preferredSize);
        preferredSize.width = 240;
        this.setMaximumSize(preferredSize);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.parseInput();
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        final TOC currentCell = this.md.getCurrentCell();
        LatLong latLong;
        if (currentSensor.displaySceneCenterLatLong && currentCell.valid) {
            latLong = this.md.getLatLong(currentCell.scenes[currentCell.currentDateIndex].centerXY);
        }
        else {
            final ProjectionTransformation projection = this.md.getProjection();
            final Point centerProjCoords = currentCell.getCenterProjCoords(projection);
            latLong = projection.projToLatLong(centerProjCoords.x, centerProjCoords.y);
            if (latLong == null) {
                this.latitude.setText("");
                this.longitude.setText("");
                return;
            }
        }
        final double n = latLong.latitude * 10.0;
        double n2;
        if (n > 0.0) {
            n2 = n + 0.5;
        }
        else {
            n2 = n - 0.5;
        }
        this.latitude.setText("" + (int)n2 / 10.0);
        final double n3 = latLong.longitude * 10.0;
        double n4;
        if (n3 > 0.0) {
            n4 = n3 + 0.5;
        }
        else {
            n4 = n3 - 0.5;
        }
        this.longitude.setText("" + (int)n4 / 10.0);
    }
    
    private void parseInput() {
        boolean b = false;
        try {
            final String text = this.latitude.getText();
            text.trim();
            if (text.equals("")) {
                this.applet.statusBar.showStatus("Latitude value invalid");
                return;
            }
            final double doubleValue = new Double(text);
            b = true;
            final String text2 = this.longitude.getText();
            text2.trim();
            if (text2.equals("")) {
                this.applet.statusBar.showStatus("Longitude value invalid");
                return;
            }
            final double doubleValue2 = new Double(text2);
            if (doubleValue < -90.0 || doubleValue > 90.0) {
                this.applet.statusBar.showStatus("Latitude " + doubleValue + " out of range!");
                this.latitude.setText("");
                return;
            }
            if (doubleValue2 < -180.0 || doubleValue2 > 180.0) {
                this.applet.statusBar.showStatus("Longitude " + doubleValue2 + " out of range!");
                this.longitude.setText("");
                return;
            }
            this.md.gotoLatLong(doubleValue, doubleValue2);
        }
        catch (NumberFormatException ex) {
            String s;
            if (b) {
                s = this.longitude.getText();
                this.longitude.setText("");
            }
            else {
                s = this.latitude.getText();
                this.latitude.setText("");
            }
            this.applet.statusBar.showStatus("Illegal lat/long value of \"" + s + "\"");
        }
    }
}
