// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.List;
import org.w3c.dom.Element;
import com.itt.J2KViewer.util.SecurityUtil;
import org.w3c.dom.Document;
import java.awt.Rectangle;
import com.itt.J2KViewer.util.ImageUtils;
import java.beans.PropertyVetoException;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.itt.J2KViewer.controller.ViewCentral;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;

public class SelectChipAreaDialog extends JDialog implements PropertyChangeListener
{
    private ViewCentral viewCentral;
    private JOptionPane optionPane;
    private JFrame parentFrame;
    private boolean showWarning;
    
    public SelectChipAreaDialog(final JFrame parentFrame, final ViewCentral viewCentral) {
        super(parentFrame, false);
        this.showWarning = true;
        this.parentFrame = parentFrame;
        this.viewCentral = viewCentral;
        (this.optionPane = new JOptionPane("Select your chip area. Click OK when done.", -1, 2)).addPropertyChangeListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                SelectChipAreaDialog.this.optionPane.setValue(new Integer(-1));
            }
        });
        this.viewCentral.eventController().addPropertyChangeListener(this);
        this.getContentPane().add(this.optionPane);
        this.pack();
        this.showWarning = this.viewCentral.getPropertyManager().isShowChipWarning();
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (this.isVisible() && propertyChangeEvent.getSource() == this.optionPane && ("value".equals(propertyName) || "inputValue".equals(propertyName))) {
            final Object value = this.optionPane.getValue();
            if (value.equals(JOptionPane.UNINITIALIZED_VALUE)) {
                return;
            }
            if (Integer.parseInt(value.toString()) == 0) {
                final Point chipStart = this.viewCentral.getPropertyManager().getChipStart();
                final Point chipEnd = this.viewCentral.getPropertyManager().getChipEnd();
                if (chipStart != null || chipStart != chipEnd) {
                    if (this.checkChipSize() && this.showWarning) {
                        final LargeChipDialog largeChipDialog = new LargeChipDialog(this.parentFrame);
                        largeChipDialog.setLocation(this.parentFrame.getLocation().x + this.parentFrame.getSize().width / 2, this.parentFrame.getLocation().y + this.parentFrame.getSize().height / 4);
                        largeChipDialog.setVisible(true);
                        if (largeChipDialog.continueToChip()) {
                            this.continueChipping();
                        }
                        else {
                            this.closeDialog();
                        }
                    }
                    else {
                        this.continueChipping();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this.parentFrame, "To draw a bounding rectangle to specify your chip area, \n press the left mouse button and drag the mouse.");
                    this.optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
                }
            }
            else {
                this.closeDialog();
                this.viewCentral.getMainImagePanel().repaint();
            }
        }
        if (this.isVisible() && propertyName.equals("TranformationMode") && this.viewCentral.getPropertyManager().getTransformationMode() != 8) {
            this.viewCentral.getPropertyManager().setCropping(false);
            this.dispose();
        }
    }
    
    private void closeDialog() {
        try {
            this.viewCentral.getPropertyManager().setCropping(false);
            this.viewCentral.getPropertyManager().setTransformationMode(0);
        }
        catch (PropertyVetoException ex) {}
        this.dispose();
    }
    
    private void continueChipping() {
        try {
            this.viewCentral.getPropertyManager().setCropping(false);
            this.viewCentral.doShowChipperDialog();
            this.dispose();
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    private boolean checkChipSize() {
        final int numberOfComponents = this.viewCentral.getPropertyManager().getNumberOfComponents();
        final int n = this.viewCentral.getPropertyManager().getDataPrecision() / 8;
        final Rectangle rectangleFromTwoPoints = ImageUtils.getRectangleFromTwoPoints(this.viewCentral.getPropertyManager().getChipStart(), this.viewCentral.getPropertyManager().getChipEnd());
        final int blockSize = this.getBlockSize("H");
        final int blockSize2 = this.getBlockSize("V");
        final double n2 = rectangleFromTwoPoints.width / new Integer(blockSize);
        final double n3 = rectangleFromTwoPoints.height / new Integer(blockSize2);
        int width;
        if (n2 > 1.0) {
            int n4 = (int)Math.floor(n2);
            if (rectangleFromTwoPoints.width % blockSize > 0) {
                ++n4;
            }
            width = n4 * blockSize;
        }
        else {
            width = rectangleFromTwoPoints.width;
        }
        int height;
        if (n3 > 1.0) {
            int n5 = (int)Math.floor(n3);
            if (rectangleFromTwoPoints.width % blockSize2 > 0) {
                ++n5;
            }
            height = n5 * blockSize2;
        }
        else {
            height = rectangleFromTwoPoints.height;
        }
        return width * height * numberOfComponents * n / 1024 > 5000;
    }
    
    private int getBlockSize(final String s) {
        String simpleElementText = "";
        final List xmlBoxes = this.viewCentral.getXmlPropertiesParser().getXMLBoxes();
        boolean b = false;
        Node item = null;
        for (int i = 0; i < xmlBoxes.size(); ++i) {
            final Document document = xmlBoxes.get(i);
            document.getDocumentElement().getNodeName();
            if (SecurityUtil.checkXMLBox(document)) {
                final NodeList elementsByTagName = document.getElementsByTagName("NPPB" + s);
                if (elementsByTagName != null) {
                    item = elementsByTagName.item(0);
                    if (item != null) {
                        b = true;
                        break;
                    }
                }
            }
        }
        if (b) {
            simpleElementText = SecurityUtil.DOMUtil.getSimpleElementText((Element)item);
        }
        int int1;
        try {
            int1 = Integer.parseInt(simpleElementText);
        }
        catch (NumberFormatException ex) {
            int1 = 1024;
        }
        return int1;
    }
    
    class LargeChipDialog extends JDialog
    {
        private boolean continueChip;
        private JCheckBox noBox;
        
        private LargeChipDialog(final JFrame frame) {
            super(frame, true);
            this.continueChip = true;
            final JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, 3));
            panel.setBorder(new EmptyBorder(new Insets(10, 10, 5, 10)));
            final JLabel label = new JLabel("Your expected chip size exceeds 50M!");
            final JLabel label2 = new JLabel("Do you want to continue?");
            label.setAlignmentX(0.0f);
            label2.setAlignmentX(0.0f);
            label2.setBorder(new EmptyBorder(new Insets(3, 0, 3, 3)));
            (this.noBox = new JCheckBox("Don't show this dialog again")).setAlignmentX(0.0f);
            this.noBox.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
            final JButton button = new JButton("OK");
            final JButton button2 = new JButton("Cancel Chip");
            final JPanel panel2 = new JPanel();
            panel2.setAlignmentX(0.0f);
            panel2.setBorder(new EmptyBorder(new Insets(3, 3, 0, 3)));
            panel2.setLayout(new FlowLayout(2));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    LargeChipDialog.this.okBtnActionPerformed();
                }
            });
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    LargeChipDialog.this.cancelBtnActionPerformed();
                }
            });
            panel2.add(button);
            panel2.add(button2);
            panel.add(label);
            panel.add(label2);
            panel.add(this.noBox);
            panel.add(panel2);
            this.getContentPane().add(panel);
            this.setTitle("Large Chip!");
            this.setResizable(false);
            this.pack();
        }
        
        private void okBtnActionPerformed() {
            this.continueChip = true;
            SelectChipAreaDialog.this.viewCentral.getPropertyManager().setShowChipWarning(!this.noBox.isSelected());
            this.dispose();
        }
        
        private void cancelBtnActionPerformed() {
            this.continueChip = false;
            this.dispose();
        }
        
        public boolean continueToChip() {
            return this.continueChip;
        }
    }
}
