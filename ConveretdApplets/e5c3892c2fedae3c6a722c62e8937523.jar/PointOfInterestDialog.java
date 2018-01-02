import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class PointOfInterestDialog extends JDialog implements ActionListener
{
    private JTextField latitude;
    private JTextField longitude;
    private imgViewer applet;
    private MosaicData md;
    private PointOfInterestMapLayer pointOfInterestMapLayer;
    
    public PointOfInterestDialog(final JFrame frame, final imgViewer applet, final MosaicData md, final PointOfInterestMapLayer pointOfInterestMapLayer) {
        super(frame, "Set Point of Interest", false);
        this.applet = applet;
        this.md = md;
        this.pointOfInterestMapLayer = pointOfInterestMapLayer;
        this.getContentPane().setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        panel.setLayout(layout);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 2));
        panel2.add(new JLabel("Enter a Latitude and Longitude"));
        panel.add(panel2, gridBagConstraints);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2, 2));
        panel3.add(new JLabel("Latitude:"));
        this.latitude = new JTextField("", 5);
        final Dimension preferredSize = this.latitude.getPreferredSize();
        this.latitude.setMinimumSize(preferredSize);
        this.latitude.setMaximumSize(preferredSize);
        this.latitude.setToolTipText("Latitude entry");
        this.latitude.addActionListener(this);
        panel3.add(this.latitude);
        panel3.add(new JLabel("Longitude:"));
        (this.longitude = new JTextField("", 5)).setMinimumSize(preferredSize);
        this.longitude.setMaximumSize(preferredSize);
        this.longitude.setToolTipText("Longitude entry");
        this.longitude.addActionListener(this);
        panel3.add(this.longitude);
        panel.add(panel3, gridBagConstraints);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1, 3));
        final JButton button = new JButton("Set");
        button.setMnemonic(83);
        button.setToolTipText("Set point of interest");
        button.addActionListener(this);
        final JButton button2 = new JButton("Clear");
        button2.setMnemonic(76);
        button2.setToolTipText("Clear point of interest");
        button2.addActionListener(this);
        final JButton button3 = new JButton("Close");
        button3.setMnemonic(67);
        button3.setToolTipText("Close point of interest");
        button3.addActionListener(this);
        panel4.add(button);
        panel4.add(button2);
        panel4.add(button3);
        this.getContentPane().add(panel, "North");
        this.getContentPane().add(panel4, "South");
        this.setSize(220, 140);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Clear")) {
            this.clearEntry();
            this.pointOfInterestMapLayer.clearPoint();
        }
        else if (actionCommand.equals("Close")) {
            this.setVisible(false);
        }
        else {
            final LatLong input = this.parseInput();
            if (input != null) {
                this.pointOfInterestMapLayer.setPoint(input);
            }
        }
    }
    
    private void clearEntry() {
        this.latitude.setText("");
        this.longitude.setText("");
    }
    
    public void setLatLong(final LatLong latLong) {
        final double n = latLong.latitude * 1000.0;
        double n2;
        if (n > 0.0) {
            n2 = n + 0.5;
        }
        else {
            n2 = n - 0.5;
        }
        this.latitude.setText("" + (int)n2 / 1000.0);
        final double n3 = latLong.longitude * 1000.0;
        double n4;
        if (n3 > 0.0) {
            n4 = n3 + 0.5;
        }
        else {
            n4 = n3 - 0.5;
        }
        this.longitude.setText("" + (int)n4 / 1000.0);
    }
    
    private LatLong parseInput() {
        boolean b = false;
        try {
            final String text = this.latitude.getText();
            text.trim();
            if (text.equals("")) {
                JOptionPane.showMessageDialog(this, "Latitude value invalid ", "Invalid Entry", 0);
                return null;
            }
            final double doubleValue = new Double(text);
            b = true;
            final String text2 = this.longitude.getText();
            text2.trim();
            if (text2.equals("")) {
                JOptionPane.showMessageDialog(this, "Longitude value invalid ", "Invalid Entry", 0);
                return null;
            }
            final double doubleValue2 = new Double(text2);
            if (doubleValue < -90.0 || doubleValue > 90.0) {
                JOptionPane.showMessageDialog(this, "Latitude " + doubleValue + " out of range!", "Invalid Entry", 0);
                this.latitude.setText("");
                return null;
            }
            if (doubleValue2 < -180.0 || doubleValue2 > 180.0) {
                JOptionPane.showMessageDialog(this, "Longitude " + doubleValue2 + " out of range!", "Invalid Entry", 0);
                this.longitude.setText("");
                return null;
            }
            this.md.gotoLatLong(doubleValue, doubleValue2);
            return new LatLong(doubleValue, doubleValue2);
        }
        catch (NumberFormatException ex) {
            String s;
            String s2;
            if (b) {
                s = this.longitude.getText();
                this.longitude.setText("");
                s2 = "Longitude";
            }
            else {
                s = this.latitude.getText();
                this.latitude.setText("");
                s2 = "Latitude";
            }
            JOptionPane.showMessageDialog(this, "Illegal " + s2 + " value of \"" + s + "\"", "Invalid Entry", 0);
            return null;
        }
    }
}
