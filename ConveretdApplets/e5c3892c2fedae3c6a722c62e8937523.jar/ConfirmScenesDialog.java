import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ConfirmScenesDialog extends JDialog implements WindowListener, ActionListener
{
    private Metadata[] scenes;
    private boolean cancelled;
    private JCheckBox[] checkBoxes;
    private int goodSceneCount;
    private JButton orderButton;
    
    public ConfirmScenesDialog(final Metadata[] scenes, final int goodSceneCount) {
        super(new JFrame(), "Include Low Quality Scenes?", true);
        this.scenes = scenes;
        this.goodSceneCount = goodSceneCount;
        this.cancelled = false;
        this.getContentPane().setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = 17;
        int n = 0;
        final String[] array = { "The following scenes have excessive cloud cover or poor quality.  Do you really", "want to include them?", " ", "Select the scenes you want to include and press the Continue button to proceed ", "with the selected scenes plus any scenes from your original list that are not", "listed below.  Unselected scenes will remain in the scene list.  Pressing the", "Cancel button will cancel all scenes and leave all scenes in the scene list.", " " };
        for (int i = 0; i < array.length; ++i) {
            final JLabel label = new JLabel(array[i]);
            gridBagConstraints.gridy = n;
            panel.add(label, gridBagConstraints);
            ++n;
        }
        final boolean hasCloudCover = this.scenes[0].getSensor().hasCloudCover;
        this.checkBoxes = new JCheckBox[this.scenes.length];
        for (int j = 0; j < this.scenes.length; ++j) {
            final Metadata metadata = this.scenes[j];
            String s = "Scene ID: " + metadata.entityID;
            if (hasCloudCover) {
                s = s + ", " + metadata.cloudCover + "% Cloud Cover";
            }
            if (metadata.quality != null) {
                for (int length = metadata.quality.length, k = 0; k < length; ++k) {
                    if (length > 1) {
                        s = s + ", Quality " + (k + 1) + ": " + metadata.quality[k];
                    }
                    else {
                        s = s + ", Quality: " + metadata.quality[k];
                    }
                }
            }
            (this.checkBoxes[j] = new JCheckBox(s)).setToolTipText("Confirm " + metadata.entityID);
            gridBagConstraints.gridy = n;
            panel.add(this.checkBoxes[j], gridBagConstraints);
            ++n;
            if (goodSceneCount == 0) {
                this.checkBoxes[j].addActionListener(this);
            }
        }
        final JScrollPane scrollPane = new JScrollPane(panel);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        (this.orderButton = new JButton("Continue")).setMnemonic(79);
        this.orderButton.setToolTipText("Continue with selected scenes");
        this.orderButton.addActionListener(this);
        if (goodSceneCount == 0) {
            this.orderButton.setEnabled(false);
        }
        final JButton button = new JButton("Cancel");
        button.setMnemonic(67);
        button.setToolTipText("Cancel all scenes");
        button.addActionListener(this);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.weightx = 20.0;
        gridBagConstraints2.fill = 1;
        panel2.add(this.orderButton, gridBagConstraints2);
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridwidth = 0;
        panel2.add(button, gridBagConstraints2);
        this.getContentPane().add(scrollPane, "Center");
        this.getContentPane().add(panel2, "South");
        this.setSize(520, 350);
        this.addWindowListener(this);
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.cancelled = true;
        this.setVisible(false);
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public boolean wasCancelled() {
        return this.cancelled;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Continue")) {
            for (int i = 0; i < this.scenes.length; ++i) {
                if (this.checkBoxes[i].isSelected()) {
                    this.scenes[i].visible = true;
                }
            }
            this.setVisible(false);
        }
        else if (actionCommand.equals("Cancel")) {
            this.cancelled = true;
            this.setVisible(false);
        }
        else if (this.goodSceneCount == 0) {
            boolean enabled = false;
            for (int j = 0; j < this.scenes.length; ++j) {
                if (this.checkBoxes[j].isSelected()) {
                    enabled = true;
                    break;
                }
            }
            this.orderButton.setEnabled(enabled);
        }
    }
}
