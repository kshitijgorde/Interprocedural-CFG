// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JDialog;

public class TestDialog extends JDialog
{
    TestDialog(final Frame frame) {
        super(frame, "JDialog", true);
        this.setDefaultCloseOperation(2);
        final JPanel panel = new JPanel(new FlowLayout(1, 0, 32));
        panel.add(new JLabel("<html><center>A <font color=\"#0000ff\">JDialog</font> for testing<br>dialog decoration."));
        this.getContentPane().add(panel, "Center");
        final JPanel panel2 = new JPanel(new FlowLayout(1, 0, 16));
        final JButton button = new JButton("Close");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                TestDialog.this.dispose();
            }
        });
        panel2.add(button);
        this.getContentPane().add(panel2, "South");
        this.pack();
        final int max = Math.max(320, this.getWidth() + 32);
        final int height = this.getHeight();
        final Point location = new Point(frame.getLocationOnScreen().x + (frame.getWidth() - max) / 2, frame.getLocationOnScreen().y + (frame.getHeight() - max) * 2 / 3);
        this.setSize(max, height);
        this.setLocation(location);
        this.setVisible(true);
    }
}
