// 
// Decompiled by Procyon v0.5.30
// 

package ifs;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class IterationCountDialog extends JDialog implements ActionListener
{
    private IFSCanvas owner;
    private JTextField input1;
    private JTextField input2;
    
    public IterationCountDialog(final IFSCanvas owner) {
        super(getFrameParent(owner), "Set Iteration Counts", true);
        this.owner = owner;
        this.input1 = new JTextField(new StringBuilder().append(owner.getIterationCount()).toString(), 5);
        this.input2 = new JTextField(new StringBuilder().append(owner.getIterationsForPreview()).toString(), 5);
        final JButton okButton = new JButton(" OK ");
        final JButton cancelButton = new JButton("Cancel");
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        final JPanel content = new JPanel();
        this.setContentPane(content);
        content.setLayout(new BorderLayout(10, 10));
        final JLabel info = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Set the number of iterations that are applied<br>to a point to determine its location in the image.<br>Each iteration constists of applying a randomly selected<br>map to the point.  Increasing the interation count might<br>give a more accurate image, but it will take longer to<br>compute.  There are separate iteration counts for the actual<br>image and for the magenta-colored preview that is shown<br>when one of the maps is selected.  Minimum value is 10.");
        content.add(info, "North");
        final JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(0, 1, 5, 5));
        JPanel p = new JPanel();
        p.setAlignmentY(0.0f);
        p.add(new JLabel("Iteration count for the image  =  "));
        p.add(this.input1);
        bottom.add(p);
        p = new JPanel();
        p.setAlignmentY(0.0f);
        p.add(new JLabel("Iteration count for the preview = "));
        p.add(this.input2);
        bottom.add(p);
        p = new JPanel();
        p.setLayout(new FlowLayout(2));
        p.setAlignmentY(0.0f);
        p.add(cancelButton);
        p.add(okButton);
        bottom.add(p);
        content.add(bottom, "Center");
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.pack();
        final Frame f = getFrameParent(owner);
        if (f != null) {
            this.setLocation(f.getX() + 20, f.getY() + 50);
        }
    }
    
    private static Frame getFrameParent(final JComponent comp) {
        Container c;
        for (c = comp.getParent(); c != null && !(c instanceof Frame); c = c.getParent()) {}
        return (c == null) ? null : ((Frame)c);
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getActionCommand().equals("Cancel")) {
            this.dispose();
        }
        else if (evt.getActionCommand().trim().equals("OK")) {
            final String text1 = this.input1.getText();
            final String text2 = this.input2.getText();
            try {
                int v1;
                try {
                    v1 = Integer.parseInt(text1);
                }
                catch (NumberFormatException e2) {
                    throw new Exception("The value entered for the number of iterations\nin the image must be an integer.");
                }
                int v2;
                try {
                    v2 = Integer.parseInt(text2);
                }
                catch (NumberFormatException e2) {
                    throw new Exception("The value entered for the number of iterations\nin the preview must be an integer.");
                }
                if (v1 < 10) {
                    throw new Exception("The value entered for the number of iterations\nin the image must be 10 or greater.");
                }
                if (v2 < 10) {
                    throw new Exception("The value entered for the number of iterations\nin the preview must be 10 or greater.");
                }
                this.dispose();
                this.owner.setIterationCount(v1);
                this.owner.setIterationsForPreview(v2);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", 0);
            }
        }
    }
}
