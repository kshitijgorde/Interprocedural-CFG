// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

import javax.swing.KeyStroke;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.awt.Insets;
import java.util.Vector;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;

public final class TransparencyDialog extends JDialog
{
    protected JPanel contentPane;
    protected JButton buttonOK;
    protected JButton buttonCancel;
    protected JPanel panel1;
    protected JPanel panel2;
    protected JPanel buttonPanel;
    private final JLabel[] layers;
    private final JSlider[] layerTransps;
    private final Capability parent;
    private final Vector oldTransparencies;
    
    public TransparencyDialog(final Vector layersNames, final Vector layersTransp, final Capability parent) {
        new TransparencyDialogSetup(this);
        this.parent = parent;
        this.oldTransparencies = (Vector)parent.getLayerTransparencies().clone();
        this.layers = new JLabel[layersNames.size()];
        this.layerTransps = new JSlider[layersTransp.size()];
        for (int i = 0; i < this.layers.length; ++i) {
            this.layers[i] = new JLabel();
            final JSlider tempSlider = new JSlider();
            tempSlider.setMinimum(0);
            tempSlider.setMaximum(100);
            tempSlider.setMajorTickSpacing(10);
            tempSlider.setMinorTickSpacing(5);
            tempSlider.setPaintTicks(true);
            tempSlider.setPaintTrack(true);
            tempSlider.setAutoscrolls(true);
            tempSlider.setName(Integer.toString(i));
            this.layerTransps[i] = tempSlider;
        }
        for (int i = 0; i < layersNames.size(); ++i) {
            final String layerName = layersNames.elementAt(i);
            float layerTransp = 0.0f;
            if (i == 0) {
                this.layerTransps[i].setValue(100);
                this.layerTransps[i].setEnabled(false);
            }
            else if (layersTransp.elementAt(i) != null) {
                layerTransp = layersTransp.elementAt(i);
            }
            else {
                this.layerTransps[i].setEnabled(false);
            }
            this.layers[i].setText(layerName);
            this.layerTransps[i].setValue((int)(layerTransp * 100.0f));
            this.panel1.add(this.layers[i], new MyGridBagConstraints(0, i, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
            this.panel1.add(this.layerTransps[i], new MyGridBagConstraints(1, i, 1, 1, 0.0, 0.0, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
            this.layerTransps[i].addChangeListener(new ChangeListener() {
                public void stateChanged(final ChangeEvent e) {
                    TransparencyDialog.this.onCtateChanged(e);
                }
            });
        }
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(this.buttonOK);
        this.buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransparencyDialog.this.onOK();
            }
        });
        this.buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransparencyDialog.this.onCancel();
            }
        });
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                TransparencyDialog.this.onCancel();
            }
        });
        this.contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransparencyDialog.this.onCancel();
            }
        }, KeyStroke.getKeyStroke(27, 0), 1);
    }
    
    private void onCtateChanged(final ChangeEvent e) {
        final JSlider slid = (JSlider)e.getSource();
        if (!slid.getValueIsAdjusting()) {
            this.parent.getLayerTransparencies().setElementAt(new Float(slid.getValue() / 100.0f), Integer.parseInt(slid.getName()));
            this.parent.getParent().map.setLayersVector(this.parent.wmsImages, this.parent.getLayerTransparencies());
            this.parent.getParent().map.combineMapImages();
        }
    }
    
    private void onOK() {
        for (int i = 0; i < this.layerTransps.length; ++i) {
            final JSlider layerTransp = this.layerTransps[i];
            if (layerTransp.isEnabled()) {
                this.parent.getLayerTransparencies().setElementAt(new Float(layerTransp.getValue() / 100.0f), i);
            }
        }
        this.parent.getParent().map.setLayersVector(this.parent.wmsImages, this.parent.getLayerTransparencies());
        this.parent.getParent().map.combineMapImages();
        this.dispose();
    }
    
    private void onCancel() {
        this.parent.setLayerTransparencies(this.oldTransparencies);
        this.parent.getParent().map.setLayersVector(this.parent.wmsImages, this.parent.getLayerTransparencies());
        this.parent.getParent().map.combineMapImages();
        this.dispose();
    }
    
    public static void main(final String[] args) {
        final Vector vec1 = new Vector();
        vec1.addElement("fgdfgf");
        vec1.addElement("fgfdgdgdfgf");
        vec1.addElement("fgdfgdfgdfgff");
        final Vector vec2 = new Vector();
        vec2.addElement(new Float(0.2));
        vec2.addElement(new Float(0.3));
        vec2.addElement(new Float(0.4));
        final TransparencyDialog dialog = new TransparencyDialog(vec1, vec2, null);
        dialog.pack();
        dialog.show();
        System.exit(0);
    }
}
