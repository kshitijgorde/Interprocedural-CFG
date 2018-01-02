import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

class CheckBoxPanel extends JPanel implements ActionListener
{
    JPanel checkboxPanel;
    JCheckBox[] checkboxes;
    
    public CheckBoxPanel(final String s) {
        this.setLayout(new BorderLayout());
        if (s != null) {
            this.add(new JLabel(s), "North");
        }
        (this.checkboxPanel = new JPanel()).setLayout(new BoxLayout(this.checkboxPanel, 1));
        this.add(new JScrollPane(this.checkboxPanel), "Center");
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        final JButton button = new JButton("Set All");
        button.setMnemonic(83);
        button.setToolTipText("Select all checkboxes");
        button.addActionListener(this);
        panel.add(button);
        final JButton button2 = new JButton("Clear All");
        button2.setMnemonic(76);
        button2.setToolTipText("Deselect all checkboxes");
        button2.addActionListener(this);
        panel.add(button2);
        this.add(panel, "South");
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    public void setCheckBoxes(final String[] array) {
        if (this.checkboxes != null) {
            this.checkboxPanel.removeAll();
            for (int i = 0; i < this.checkboxes.length; ++i) {
                this.checkboxes[i] = null;
            }
            this.checkboxes = null;
        }
        if (array.length > 0) {
            this.checkboxes = new JCheckBox[array.length];
            for (int j = 0; j < array.length; ++j) {
                this.checkboxes[j] = new JCheckBox(array[j], true);
                this.checkboxPanel.add(this.checkboxes[j]);
            }
        }
    }
    
    public void addActionListener(final ActionListener actionListener) {
        if (this.checkboxes != null) {
            for (int i = 0; i < this.checkboxes.length; ++i) {
                this.checkboxes[i].addActionListener(actionListener);
            }
        }
    }
    
    public boolean[] getSelectedArray() {
        boolean[] array = null;
        if (this.checkboxes != null) {
            array = new boolean[this.checkboxes.length];
            for (int i = 0; i < this.checkboxes.length; ++i) {
                array[i] = this.checkboxes[i].isSelected();
            }
        }
        return array;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.checkboxes != null) {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand.equals("Set All")) {
                for (int i = 1; i < this.checkboxes.length; ++i) {
                    this.checkboxes[i].setSelected(true);
                }
                this.checkboxes[0].setSelected(false);
                this.checkboxes[0].doClick();
            }
            else if (actionCommand.equals("Clear All")) {
                for (int j = 1; j < this.checkboxes.length; ++j) {
                    this.checkboxes[j].setSelected(false);
                }
                this.checkboxes[0].setSelected(true);
                this.checkboxes[0].doClick();
            }
        }
    }
}
