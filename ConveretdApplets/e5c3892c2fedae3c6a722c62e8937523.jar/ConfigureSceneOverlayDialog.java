import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

class ConfigureSceneOverlayDialog extends JDialog implements ActionListener
{
    private SceneOverlayMapLayer mapLayer;
    private JCheckBox[] sensorCB;
    
    public ConfigureSceneOverlayDialog(final imgViewer imgViewer, final SceneOverlayMapLayer mapLayer, final boolean[] array) {
        super(new JFrame(), "Configure Scene List Overlay", false);
        this.mapLayer = mapLayer;
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JLabel("Select the scene lists to overlay"), "North");
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JButton button = new JButton("Set All");
        button.setMnemonic(83);
        button.setToolTipText("Set all checkboxes");
        panel2.add(button);
        final JButton button2 = new JButton("Clear All");
        button2.setMnemonic(76);
        button2.setToolTipText("Clear all checkboxes");
        panel2.add(button2);
        final JButton button3 = new JButton("OK");
        button3.setMnemonic(79);
        button3.setToolTipText("Accept changes and close dialog");
        panel3.add(button3);
        final JButton button4 = new JButton("Apply");
        button4.setMnemonic(65);
        button4.setToolTipText("Apply changes");
        panel3.add(button4);
        final JButton button5 = new JButton("Cancel");
        button5.setMnemonic(67);
        button5.setToolTipText("Cancel unapplied changes and close dialog");
        panel3.add(button5);
        panel.add(panel2);
        panel.add(panel3);
        this.getContentPane().add(panel, "South");
        button.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        final Sensor[] sensors = imgViewer.sensorMenu.getSensors();
        final int n = (sensors.length + 1) / 2;
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(n, 2));
        this.sensorCB = new JCheckBox[sensors.length];
        for (int i = 0; i < sensors.length; ++i) {
            this.sensorCB[i] = null;
            if (!sensors[i].hasMultipleDatasets) {
                (this.sensorCB[i] = new JCheckBox(sensors[i].sensorName)).setSelected(array[i]);
                this.sensorCB[i].setToolTipText("Show " + sensors[i].sensorName);
                panel4.add(this.sensorCB[i]);
            }
        }
        this.getContentPane().add(new JScrollPane(panel4), "Center");
        this.setSize(360, 460);
    }
    
    public void setEnabledSensors(final boolean[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this.sensorCB[i] != null) {
                this.sensorCB[i].setSelected(array[i]);
            }
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Set All")) {
            for (int i = 0; i < this.sensorCB.length; ++i) {
                if (this.sensorCB[i] != null) {
                    this.sensorCB[i].setSelected(true);
                }
            }
        }
        else if (actionCommand.equals("Clear All")) {
            for (int j = 0; j < this.sensorCB.length; ++j) {
                if (this.sensorCB[j] != null) {
                    this.sensorCB[j].setSelected(false);
                }
            }
        }
        else if (actionCommand.equals("OK") || actionCommand.equals("Apply")) {
            final boolean[] array = new boolean[this.sensorCB.length];
            for (int k = 0; k < array.length; ++k) {
                if (this.sensorCB[k] != null) {
                    array[k] = this.sensorCB[k].isSelected();
                }
            }
            this.mapLayer.updateShownSensors(array);
            if (actionCommand.equals("OK")) {
                this.setVisible(false);
            }
        }
        else if (actionCommand.equals("Cancel")) {
            this.setVisible(false);
        }
    }
}
