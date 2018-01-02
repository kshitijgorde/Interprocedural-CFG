import javax.swing.JColorChooser;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

class ModifyLayerColorsDialog extends JDialog implements ActionListener
{
    private Color[] colors;
    private JButton[] colorButtons;
    private MapLayer[] layers;
    
    public ModifyLayerColorsDialog(final imgViewer imgViewer, final MapLayer[] layers) {
        super(new JFrame(), "Modify Map Layer Colors", false);
        this.layers = layers;
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JLabel("Modify the map layer colors"), "North");
        final JPanel panel = new JPanel();
        final JButton button = new JButton("Default");
        button.setMnemonic(68);
        button.setToolTipText("Reset to default colors");
        panel.add(button);
        final JButton button2 = new JButton("OK");
        button2.setMnemonic(79);
        button2.setToolTipText("Accept changes and close dialog");
        panel.add(button2);
        final JButton button3 = new JButton("Apply");
        button3.setMnemonic(65);
        button3.setToolTipText("Apply changes");
        panel.add(button3);
        final JButton button4 = new JButton("Cancel");
        button4.setMnemonic(67);
        button4.setToolTipText("Cancel unapplied changes and close dialog");
        panel.add(button4);
        this.getContentPane().add(panel, "South");
        button.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        final JPanel panel2 = new JPanel();
        this.colors = new Color[layers.length];
        this.colorButtons = new JButton[layers.length];
        panel2.setLayout(new GridLayout(layers.length, 2));
        for (int i = 0; i < layers.length; ++i) {
            this.colors[i] = layers[i].getColor();
            if (this.colors[i] != null) {
                final JLabel label = new JLabel(layers[i].getName());
                (this.colorButtons[i] = new JButton("Change")).setToolTipText("Change color for " + layers[i].getName() + " layer");
                this.colorButtons[i].setActionCommand("" + i);
                this.colorButtons[i].setBackground(this.colors[i]);
                this.colorButtons[i].addActionListener(this);
                panel2.add(label);
                panel2.add(this.colorButtons[i]);
            }
        }
        this.getContentPane().add(new JScrollPane(panel2), "Center");
        this.setSize(360, 460);
    }
    
    public void setColors() {
        for (int i = 0; i < this.layers.length; ++i) {
            this.colors[i] = this.layers[i].getColor();
            if (this.colors[i] != null) {
                this.colorButtons[i].setBackground(this.colors[i]);
            }
        }
    }
    
    public void updateMapLayers() {
        for (int i = 0; i < this.layers.length; ++i) {
            this.layers[i].setColor(this.colors[i]);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("OK")) {
            this.updateMapLayers();
            this.setVisible(false);
        }
        if (actionCommand.equals("Apply")) {
            this.updateMapLayers();
        }
        else if (actionCommand.equals("Cancel")) {
            this.setVisible(false);
        }
        else if (actionCommand.equals("Default")) {
            for (int i = 0; i < this.layers.length; ++i) {
                this.colors[i] = this.layers[i].getOriginalColor();
                if (this.colors[i] != null) {
                    this.colorButtons[i].setBackground(this.colors[i]);
                }
            }
        }
        else {
            try {
                final int int1 = Integer.parseInt(actionCommand);
                final Color showDialog = JColorChooser.showDialog(this, "Choose the color for the " + this.layers[int1].getName() + " map layer", this.colors[int1]);
                if (showDialog != null) {
                    this.colors[int1] = showDialog;
                    this.colorButtons[int1].setBackground(showDialog);
                }
            }
            catch (Exception ex) {}
        }
    }
}
