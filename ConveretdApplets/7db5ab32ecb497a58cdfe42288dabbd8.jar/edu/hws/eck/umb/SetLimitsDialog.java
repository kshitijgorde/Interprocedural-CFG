// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import javax.swing.JOptionPane;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Container;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import edu.hws.eck.umb.util.I18n;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.math.BigDecimal;
import javax.swing.JDialog;

public class SetLimitsDialog extends JDialog
{
    private BigDecimal[] valueInput;
    private boolean canceled;
    private JTextField[] inputs;
    private JButton okButton;
    private JButton cancelButton;
    
    public static BigDecimal[] showDialog(Component parent, final String[] array) {
        while (parent != null && !(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        final SetLimitsDialog setLimitsDialog = new SetLimitsDialog((Frame)parent, array);
        setLimitsDialog.setVisible(true);
        if (setLimitsDialog.canceled) {
            return null;
        }
        return setLimitsDialog.valueInput;
    }
    
    private SetLimitsDialog(final Frame frame, final String[] array) {
        super(frame, I18n.tr("setLimitsDialog.title", new Object[0]), true);
        this.canceled = true;
        this.inputs = new JTextField[4];
        for (int i = 0; i < 4; ++i) {
            this.inputs[i] = new JTextField(20);
            if (array != null) {
                this.inputs[i].setText(array[i].toString());
            }
        }
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        this.setContentPane(contentPane);
        contentPane.add(new JLabel(I18n.tr("setLimitsDialog.instructions", new Object[0])), "North");
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 1, 3, 3));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(4, 1, 3, 3));
        panel.add(panel2, "West");
        panel.add(panel3, "Center");
        for (int j = 0; j < 4; ++j) {
            panel3.add(this.inputs[j]);
        }
        panel2.add(new JLabel(I18n.tr("term.MinimumX", new Object[0]) + " = "));
        panel2.add(new JLabel(I18n.tr("term.MaximumX", new Object[0]) + " = "));
        panel2.add(new JLabel(I18n.tr("term.MinimumY", new Object[0]) + " = "));
        panel2.add(new JLabel(I18n.tr("term.MaximumY", new Object[0]) + " = "));
        contentPane.add(panel, "Center");
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(1, 5, 5));
        this.okButton = new JButton(I18n.tr("buttonName.OK", new Object[0]));
        this.cancelButton = new JButton(I18n.tr("buttonName.Cancel", new Object[0]));
        final ButtonHandler buttonHandler = new ButtonHandler();
        this.okButton.addActionListener(buttonHandler);
        this.cancelButton.addActionListener(buttonHandler);
        panel4.add(this.cancelButton);
        panel4.add(this.okButton);
        contentPane.add(panel4, "South");
        this.getRootPane().setDefaultButton(this.okButton);
        this.setDefaultCloseOperation(2);
        this.pack();
        this.setResizable(false);
        if (frame != null) {
            this.setLocation(frame.getX() + 40, frame.getY() + 80);
        }
        contentPane.getInputMap(2).put(KeyStroke.getKeyStroke("ESCAPE"), "cancel");
        contentPane.getActionMap().put("cancel", new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SetLimitsDialog.this.cancelButton.doClick();
            }
        });
    }
    
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == SetLimitsDialog.this.cancelButton) {
                SetLimitsDialog.this.dispose();
            }
            else if (source == SetLimitsDialog.this.okButton) {
                final BigDecimal[] array = new BigDecimal[4];
                for (int i = 0; i < 4; ++i) {
                    try {
                        array[i] = new BigDecimal(SetLimitsDialog.this.inputs[i].getText().trim());
                    }
                    catch (NumberFormatException ex) {
                        String s;
                        if (i == 0) {
                            s = I18n.tr("term.MinimumX", new Object[0]);
                        }
                        else if (i == 1) {
                            s = I18n.tr("term.MaximumX", new Object[0]);
                        }
                        else if (i == 2) {
                            s = I18n.tr("term.MinimumY", new Object[0]);
                        }
                        else {
                            s = I18n.tr("term.MaximumY", new Object[0]);
                        }
                        JOptionPane.showMessageDialog(SetLimitsDialog.this, I18n.tr("setLimitsDialog.error.NotANumber", s));
                        SetLimitsDialog.this.inputs[i].selectAll();
                        SetLimitsDialog.this.inputs[i].requestFocus();
                        return;
                    }
                }
                if (array[0].compareTo(array[1]) >= 0) {
                    JOptionPane.showMessageDialog(SetLimitsDialog.this, I18n.tr("setLimitsDialog.error.MinMaxOutOfOrder", I18n.tr("term.MinimumX", new Object[0]), I18n.tr("term.MaximumX", new Object[0])));
                    SetLimitsDialog.this.inputs[1].selectAll();
                    SetLimitsDialog.this.inputs[1].requestFocus();
                    return;
                }
                if (array[2].compareTo(array[3]) >= 0) {
                    JOptionPane.showMessageDialog(SetLimitsDialog.this, I18n.tr("setLimitsDialog.error.MinMaxOutOfOrder", I18n.tr("term.MinimumY", new Object[0]), I18n.tr("term.MaximumY", new Object[0])));
                    SetLimitsDialog.this.inputs[3].selectAll();
                    SetLimitsDialog.this.inputs[3].requestFocus();
                    return;
                }
                SetLimitsDialog.this.valueInput = array;
                SetLimitsDialog.this.canceled = false;
                SetLimitsDialog.this.dispose();
            }
        }
    }
}
