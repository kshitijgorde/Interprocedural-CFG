// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graphedit;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.LayoutManager;
import org.jdesktop.layout.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import medusa.graph.Edge;
import javax.swing.JDialog;

public class NewEdgeDialog extends JDialog
{
    Edge e;
    private JButton cancelButton;
    private JTextField confidenceField;
    private JLabel confidenceLabel;
    private JLabel explanationLabel;
    private JTextField fromField;
    private JLabel fromLabel;
    private JButton okButton;
    private JTextField orientationField;
    private JLabel orientationLabel;
    private JLabel titleLabel;
    private JTextField toField;
    private JLabel toLabel;
    private JTextField typeField;
    private JLabel typeLabel;
    
    public NewEdgeDialog(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.e = null;
        this.initComponents();
    }
    
    private void initComponents() {
        this.fromLabel = new JLabel();
        this.fromField = new JTextField();
        this.toLabel = new JLabel();
        this.toField = new JTextField();
        this.typeLabel = new JLabel();
        this.typeField = new JTextField();
        this.orientationLabel = new JLabel();
        this.orientationField = new JTextField();
        this.confidenceField = new JTextField();
        this.confidenceLabel = new JLabel();
        this.okButton = new JButton();
        this.cancelButton = new JButton();
        this.explanationLabel = new JLabel();
        this.titleLabel = new JLabel();
        this.setDefaultCloseOperation(2);
        this.fromLabel.setText("From");
        this.toLabel.setText("To");
        this.typeLabel.setText("Type");
        this.orientationLabel.setText("Orientation");
        this.orientationField.setText("0.0");
        this.confidenceField.setText("1.0");
        this.confidenceLabel.setText("Confidence");
        this.okButton.setText("OK");
        this.okButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                NewEdgeDialog.this.okButtonActionPerformed(evt);
            }
        });
        this.cancelButton.setText("Cancel");
        this.explanationLabel.setText("( 0.0 to 1.0 )");
        this.titleLabel.setText("<html>Add new <b>edge</b></html>");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.titleLabel).add((Component)this.fromLabel).add((Component)this.okButton).add((Component)this.orientationLabel).add((GroupLayout.Group)layout.createParallelGroup(2, false).add(1, (Component)this.fromField).add(1, (Component)this.orientationField, -1, 84, 32767))).add(32, 32, 32).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1, false).add((Component)this.toLabel).add((Component)this.confidenceLabel, -1, -1, 32767).add((Component)this.toField)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(2).add((Component)this.cancelButton).add(1, (GroupLayout.Group)layout.createSequentialGroup().add(12, 12, 12).add((GroupLayout.Group)layout.createParallelGroup(1).add(2, (Component)this.typeLabel, -1, 75, 32767).add((Component)this.typeField, -2, 38, -2))))).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.confidenceField, -2, 45, -2).addPreferredGap(0).add((Component)this.explanationLabel))).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.titleLabel).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.fromLabel).add((Component)this.toLabel).add((Component)this.typeLabel)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.toField, -2, -1, -2).add((Component)this.fromField, -2, -1, -2).add((Component)this.typeField, -2, -1, -2)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.orientationLabel).add((Component)this.confidenceLabel)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.confidenceField, -2, -1, -2).add((Component)this.explanationLabel).add((Component)this.orientationField, -2, -1, -2)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.okButton).add((Component)this.cancelButton)).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void okButtonActionPerformed(final ActionEvent evt) {
        try {
            this.e = this.readEdge();
            this.setVisible(false);
        }
        catch (NumberFormatException ne) {
            JOptionPane.showConfirmDialog(this, "One of the numbers you have entered is not parsable!", "Syntax error", 2);
        }
    }
    
    private Edge readEdge() throws NumberFormatException {
        final String from = this.fromField.getText();
        final String to = this.toField.getText();
        final int type = Integer.parseInt(this.typeField.getText());
        final double orientation = Double.parseDouble(this.orientationField.getText());
        final float confidence = Float.parseFloat(this.confidenceField.getText());
        final Edge e = new Edge(from, to, confidence, type, orientation);
        return e;
    }
    
    public Edge getEdge() {
        return this.e;
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewEdgeDialog(new JFrame(), true).setVisible(true);
            }
        });
    }
}
