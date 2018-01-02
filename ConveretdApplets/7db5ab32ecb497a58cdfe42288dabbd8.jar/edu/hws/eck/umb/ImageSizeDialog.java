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
import java.awt.Dimension;
import javax.swing.JDialog;

public class ImageSizeDialog extends JDialog
{
    private Dimension valueInput;
    private boolean canceled;
    private JTextField widthInput;
    private JTextField heightInput;
    private JButton okButton;
    private JButton cancelButton;
    
    public static Dimension showDialog(Component parent, final Dimension dimension) {
        while (parent != null && !(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        final ImageSizeDialog imageSizeDialog = new ImageSizeDialog((Frame)parent, dimension);
        imageSizeDialog.setVisible(true);
        if (imageSizeDialog.canceled) {
            return null;
        }
        return imageSizeDialog.valueInput;
    }
    
    private ImageSizeDialog(final Frame frame, final Dimension dimension) {
        super(frame, I18n.tr("imageSizeDialog.title", new Object[0]), true);
        this.canceled = true;
        this.widthInput = new JTextField(5);
        this.heightInput = new JTextField(5);
        if (dimension != null) {
            this.widthInput.setText("" + dimension.width);
            this.heightInput.setText("" + dimension.height);
        }
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        this.setContentPane(contentPane);
        contentPane.add(new JLabel(I18n.tr("imageSizeDialog.instructions", new Object[0])), "North");
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel(I18n.tr("term.width", new Object[0]) + " = ", 4));
        panel.add(this.widthInput);
        panel.add(new JLabel(I18n.tr("term.height", new Object[0]) + " = ", 4));
        panel.add(this.heightInput);
        contentPane.add(panel, "Center");
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(1, 5, 5));
        this.okButton = new JButton(I18n.tr("buttonName.OK", new Object[0]));
        this.cancelButton = new JButton(I18n.tr("buttonName.Cancel", new Object[0]));
        final ButtonHandler buttonHandler = new ButtonHandler();
        this.okButton.addActionListener(buttonHandler);
        this.cancelButton.addActionListener(buttonHandler);
        panel2.add(this.cancelButton);
        panel2.add(this.okButton);
        contentPane.add(panel2, "South");
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
                ImageSizeDialog.this.cancelButton.doClick();
            }
        });
    }
    
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == ImageSizeDialog.this.cancelButton) {
                ImageSizeDialog.this.dispose();
            }
            else if (source == ImageSizeDialog.this.okButton) {
                int int1;
                try {
                    int1 = Integer.parseInt(ImageSizeDialog.this.widthInput.getText().trim());
                    if (int1 <= 0) {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ImageSizeDialog.this, I18n.tr("imageSizeDialog.error.WidthMustBePositive", new Object[0]));
                    ImageSizeDialog.this.widthInput.selectAll();
                    ImageSizeDialog.this.widthInput.requestFocus();
                    return;
                }
                int int2;
                try {
                    int2 = Integer.parseInt(ImageSizeDialog.this.heightInput.getText().trim());
                    if (int2 <= 0) {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException ex2) {
                    JOptionPane.showMessageDialog(ImageSizeDialog.this, I18n.tr("imageSizeDialog.error.HeightMustBePositive", new Object[0]));
                    ImageSizeDialog.this.heightInput.selectAll();
                    ImageSizeDialog.this.heightInput.requestFocus();
                    return;
                }
                ImageSizeDialog.this.valueInput = new Dimension(int1, int2);
                ImageSizeDialog.this.canceled = false;
                ImageSizeDialog.this.dispose();
            }
        }
    }
}
