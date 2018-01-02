// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import java.awt.GridBagConstraints;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;

public class ColorJDialog extends JDialog
{
    private String label;
    private static ColorJDialog cjd;
    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;
    private JLabel color1Label;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private JRadioButton jRadioButton5;
    private JRadioButton jRadioButton6;
    private int color1;
    private int color2;
    
    public ColorJDialog(final Frame frame, final boolean b) {
        super(frame, b);
        this.color1 = 0;
        this.color2 = 0;
        this.initComponents();
    }
    
    public ColorJDialog(final Frame frame, final Component locationRelativeTo, final String label) {
        super(frame, "Color", true);
        this.color1 = 0;
        this.color2 = 0;
        this.label = label;
        JDialog.setDefaultLookAndFeelDecorated(true);
        this.initComponents();
        this.setLocationRelativeTo(locationRelativeTo);
    }
    
    public static int[] showDialog(final Component component, final Component component2, final String s) {
        (ColorJDialog.cjd = new ColorJDialog(JOptionPane.getFrameForComponent(component), component2, s)).setVisible(true);
        return new int[] { ColorJDialog.cjd.getColor1(), ColorJDialog.cjd.getColor2() };
    }
    
    private void initComponents() {
        this.buttonGroup1 = new ButtonGroup();
        this.buttonGroup2 = new ButtonGroup();
        this.jPanel1 = new JPanel();
        this.color1Label = new JLabel();
        this.jRadioButton1 = new JRadioButton();
        this.jRadioButton2 = new JRadioButton();
        this.jRadioButton3 = new JRadioButton();
        this.jLabel2 = new JLabel();
        this.jRadioButton4 = new JRadioButton();
        this.jRadioButton5 = new JRadioButton();
        this.jRadioButton6 = new JRadioButton();
        this.jLabel3 = new JLabel();
        this.jLabel1 = new JLabel();
        this.jPanel2 = new JPanel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        final Dimension dimension = new Dimension(40, 20);
        this.setDefaultCloseOperation(2);
        this.jPanel1.setLayout(new GridBagLayout());
        this.jPanel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        this.color1Label.setText("Channel 1");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.ipady = 10;
        this.jPanel1.add(this.color1Label, gridBagConstraints);
        this.jRadioButton1.setBackground(new Color(255, 0, 0));
        this.buttonGroup1.add(this.jRadioButton1);
        this.jRadioButton1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ColorJDialog.this.button1ActionPerformed(actionEvent);
            }
        });
        this.jRadioButton1.setPreferredSize(dimension);
        this.jRadioButton1.setText("R");
        this.jRadioButton1.setSelected(true);
        this.jRadioButton1.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.jRadioButton1.setBorderPainted(true);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        this.jPanel1.add(this.jRadioButton1, gridBagConstraints2);
        this.jRadioButton2.setBackground(new Color(0, 255, 0));
        this.buttonGroup1.add(this.jRadioButton2);
        this.jRadioButton2.setPreferredSize(dimension);
        this.jRadioButton2.setText("G");
        this.jRadioButton2.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.jRadioButton2.setBorderPainted(true);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        this.jRadioButton2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ColorJDialog.this.button2ActionPerformed(actionEvent);
            }
        });
        this.jPanel1.add(this.jRadioButton2, gridBagConstraints3);
        this.jRadioButton3.setBackground(new Color(0, 0, 255));
        this.buttonGroup1.add(this.jRadioButton3);
        this.jRadioButton3.setPreferredSize(dimension);
        this.jRadioButton3.setText("B");
        this.jRadioButton3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ColorJDialog.this.button3ActionPerformed(actionEvent);
            }
        });
        this.jRadioButton3.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.jRadioButton3.setBorderPainted(true);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 3;
        this.jPanel1.add(this.jRadioButton3, gridBagConstraints4);
        this.jLabel2.setText(this.label);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = 2;
        gridBagConstraints5.ipadx = 10;
        this.jPanel1.add(this.jLabel2, gridBagConstraints5);
        this.jRadioButton4.setBackground(new Color(255, 0, 0));
        this.buttonGroup2.add(this.jRadioButton4);
        this.jRadioButton4.setPreferredSize(dimension);
        this.jRadioButton4.setText("R");
        this.jRadioButton4.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.jRadioButton4.setBorderPainted(true);
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 2;
        gridBagConstraints6.gridy = 1;
        this.jPanel1.add(this.jRadioButton4, gridBagConstraints6);
        this.jRadioButton5.setBackground(new Color(0, 255, 0));
        this.buttonGroup2.add(this.jRadioButton5);
        this.jRadioButton5.setPreferredSize(dimension);
        this.jRadioButton5.setText("G");
        this.jRadioButton5.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.jRadioButton5.setBorderPainted(true);
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 2;
        gridBagConstraints7.gridy = 2;
        this.jPanel1.add(this.jRadioButton5, gridBagConstraints7);
        this.jRadioButton6.setBackground(new Color(0, 0, 255));
        this.buttonGroup2.add(this.jRadioButton6);
        this.jRadioButton6.setPreferredSize(dimension);
        this.jRadioButton6.setText("B");
        this.jRadioButton6.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.jRadioButton6.setBorderPainted(true);
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 2;
        gridBagConstraints8.gridy = 3;
        this.jPanel1.add(this.jRadioButton6, gridBagConstraints8);
        this.jLabel3.setText("Channel 2");
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.gridx = 2;
        gridBagConstraints9.gridy = 0;
        gridBagConstraints9.ipady = 10;
        this.jPanel1.add(this.jLabel3, gridBagConstraints9);
        this.getContentPane().add(this.jPanel1, "Center");
        this.jLabel1.setText("Select channels to switch");
        this.getContentPane().add(this.jLabel1, "North");
        this.jButton1.setText("OK");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ColorJDialog.this.okActionPerformed(actionEvent);
            }
        });
        this.jPanel2.add(this.jButton1);
        this.jButton2.setText("Cancel");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ColorJDialog.this.cancelActionPerformed(actionEvent);
            }
        });
        this.jPanel2.add(this.jButton2);
        this.getContentPane().add(this.jPanel2, "South");
        this.pack();
    }
    
    public static void main(final String[] array) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorJDialog(new JFrame(), true).setVisible(true);
            }
        });
    }
    
    public final int getColor2() {
        return this.color2;
    }
    
    public final void setColor2(final int color2) {
        this.color2 = color2;
    }
    
    public final int getColor1() {
        return this.color1;
    }
    
    public final void setColor1(final int color1) {
        this.color1 = color1;
    }
    
    private void button1ActionPerformed(final ActionEvent actionEvent) {
        this.setColor1(0);
    }
    
    private void button2ActionPerformed(final ActionEvent actionEvent) {
        this.setColor1(1);
    }
    
    private void button3ActionPerformed(final ActionEvent actionEvent) {
        this.setColor1(2);
    }
    
    private void button4ActionPerformed(final ActionEvent actionEvent) {
        this.setColor2(0);
    }
    
    private void button5ActionPerformed(final ActionEvent actionEvent) {
        this.setColor2(1);
    }
    
    private void button6ActionPerformed(final ActionEvent actionEvent) {
        this.setColor2(2);
    }
    
    private void okActionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    private void cancelActionPerformed(final ActionEvent actionEvent) {
        this.setColor1(1);
        this.setColor2(1);
        this.setVisible(false);
    }
}
