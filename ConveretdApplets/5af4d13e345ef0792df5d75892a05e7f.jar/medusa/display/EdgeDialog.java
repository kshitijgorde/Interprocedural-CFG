// 
// Decompiled by Procyon v0.5.30
// 

package medusa.display;

import javax.swing.JOptionPane;
import medusa.graph.Node;
import java.awt.event.ActionEvent;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import medusa.MedusaFrame;
import java.awt.Component;
import java.awt.Frame;
import medusa.graph.Edge;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;
import medusa.MedusaSettings;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class EdgeDialog extends JDialog implements ActionListener
{
    private static EdgeDialog edgeDialog;
    MedusaSettings ss;
    final Color stringColor;
    final int preferredHeight = 20;
    final int preferredWidth = 50;
    final Dimension textAreaSize;
    private JButton submitButton;
    private JButton cancelButton;
    private JLabel node1Label;
    private JTextArea node1Area;
    private JLabel node2Label;
    private JTextArea node2Area;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel sideLabel;
    private JPanel bottomPanel;
    private JLabel interactionLabel;
    private JComboBox interactionCB;
    private JLabel confidenceLabel;
    private JTextArea confidenceArea;
    private JLabel orientationLabel;
    private JTextArea orientationArea;
    private static Edge e;
    
    private EdgeDialog(final Frame frame, final Component component) {
        this(frame, component, new MedusaSettings());
    }
    
    private EdgeDialog(final Frame frame, final Component locationRelativeTo, final MedusaSettings ss) {
        super(frame, "", true);
        this.stringColor = MedusaFrame.STRINGCOLOR;
        this.textAreaSize = new Dimension(50, 20);
        this.submitButton = new JButton("Submit");
        this.cancelButton = new JButton("Cancel");
        this.node1Label = new JLabel("Node 1");
        this.node1Area = new JTextArea();
        this.node2Label = new JLabel("Node 2");
        this.node2Area = new JTextArea();
        this.mainPanel = new JPanel();
        this.titleLabel = new JLabel();
        this.sideLabel = new JLabel();
        this.bottomPanel = new JPanel();
        this.interactionLabel = new JLabel("Type");
        this.confidenceLabel = new JLabel("Conf");
        this.confidenceArea = new JTextArea("1.0");
        this.orientationLabel = new JLabel("Ori");
        this.orientationArea = new JTextArea("0.0");
        JDialog.setDefaultLookAndFeelDecorated(true);
        this.ss = ss;
        this.init();
        this.setLocationRelativeTo(locationRelativeTo);
    }
    
    private String[] getInteractions() {
        final int size = this.ss.getSize();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = this.ss.getName(new Integer(i + 1));
        }
        return array;
    }
    
    private void init() {
        this.interactionCB = new JComboBox((E[])this.getInteractions());
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        this.titleLabel.setBackground(this.stringColor);
        this.titleLabel.setHorizontalAlignment(0);
        this.titleLabel.setText("Edit edge");
        this.titleLabel.setOpaque(true);
        contentPane.add(this.titleLabel, "North");
        this.sideLabel.setBackground(this.stringColor);
        this.sideLabel.setIcon(new ImageIcon("logo_still_p.gif"));
        this.sideLabel.setVerticalAlignment(1);
        this.sideLabel.setOpaque(true);
        contentPane.add(this.sideLabel, "West");
        this.submitButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
        this.mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        this.node1Area.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.node2Area.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.confidenceArea.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.mainPanel.add(this.node1Label);
        this.mainPanel.add(this.node1Area);
        this.mainPanel.add(this.node2Label);
        this.mainPanel.add(this.node2Area);
        this.mainPanel.add(this.interactionLabel);
        this.mainPanel.add(this.interactionCB);
        this.mainPanel.add(this.confidenceLabel);
        this.mainPanel.add(this.confidenceArea);
        this.mainPanel.add(this.orientationLabel);
        this.mainPanel.add(this.orientationArea);
        this.mainPanel.add(this.submitButton);
        this.mainPanel.add(this.cancelButton);
        contentPane.add(this.mainPanel, "Center");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source.equals(this.submitButton)) {
            this.submitButtonActionPerformed();
        }
        if (source.equals(this.cancelButton)) {
            this.cancelButtonActionPerformed();
        }
    }
    
    private void setNodes(final String text, final String text2) {
        this.node1Area.setText(text);
        this.node2Area.setText(text2);
    }
    
    private void setText1(final String text) {
        this.node1Area.setText(text);
        this.node1Area.setEnabled(false);
    }
    
    private void setText2(final String text) {
        this.node2Area.setText(text);
        this.node2Area.setEnabled(false);
    }
    
    public static Edge showDialog(final Component component, final Component component2, final Node node, final Node node2, final MedusaSettings medusaSettings) {
        EdgeDialog.edgeDialog = new EdgeDialog(JOptionPane.getFrameForComponent(component), component2, medusaSettings);
        if (node.getLabel().compareTo("unnamed") != 0) {
            EdgeDialog.edgeDialog.setText1(node.getLabel());
        }
        if (node2.getLabel().compareTo("unnamed") != 0) {
            EdgeDialog.edgeDialog.setText2(node2.getLabel());
        }
        EdgeDialog.edgeDialog.pack();
        EdgeDialog.edgeDialog.setVisible(true);
        return EdgeDialog.e;
    }
    
    public static Edge showDialog(final Component component, final Component component2, final Node node, final Node node2) {
        return showDialog(component, component2, node, node2, new MedusaSettings());
    }
    
    public static String showDialog(final Component component, final Component component2) {
        (EdgeDialog.edgeDialog = new EdgeDialog(JOptionPane.getFrameForComponent(component), component2)).pack();
        EdgeDialog.edgeDialog.setVisible(true);
        return "ok";
    }
    
    private void cancelButtonActionPerformed() {
        EdgeDialog.e = null;
        this.setVisible(false);
    }
    
    private void submitButtonActionPerformed() {
        EdgeDialog.e = this.getEdge();
        this.setVisible(false);
    }
    
    private float getConf() {
        float float1;
        try {
            float1 = Float.parseFloat(this.confidenceArea.getText());
        }
        catch (NumberFormatException ex) {
            float1 = 1.0f;
        }
        return float1;
    }
    
    private double getOrientation() {
        double double1;
        try {
            double1 = Double.parseDouble(this.orientationArea.getText());
        }
        catch (NumberFormatException ex) {
            double1 = 0.0;
        }
        return double1;
    }
    
    private Edge getEdge() {
        return new Edge(this.node1Area.getText(), this.node2Area.getText(), this.getConf(), this.interactionCB.getSelectedIndex() + 1, this.getOrientation());
    }
    
    static {
        EdgeDialog.e = null;
    }
}
