// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.JRadioButton;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.Canvas;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JLabel;
import com.itt.J2KViewer.util.Helper;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import java.awt.Image;
import java.util.Vector;
import com.itt.J2KViewer.controller.ViewCentral;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class ChooseNorthArrowDialog extends JDialog implements ActionListener, ComponentListener
{
    private static final String STATE_NORTH_ARROW_SELECTION = "NorthArrowSelection";
    private JPanel m_mainPanel;
    private JPanel m_buttonPanel;
    private JButton m_okButton;
    private JPanel m_centerPanel;
    private int m_selected_image_index;
    private ViewCentral viewCentral;
    private String[] imageNames;
    private Vector m_northArrowImageList;
    
    public Image getNorthArrowImage() {
        return this.m_northArrowImageList.get(this.m_selected_image_index);
    }
    
    public ChooseNorthArrowDialog(final JFrame locationRelativeTo, final boolean b, final ViewCentral viewCentral) {
        super(locationRelativeTo, b);
        this.m_mainPanel = null;
        this.m_buttonPanel = new JPanel();
        this.m_okButton = null;
        this.m_centerPanel = new JPanel();
        this.m_selected_image_index = 0;
        this.imageNames = new String[] { "NorthArrow.gif", "NorthArrowTrans.gif", "NorthArrowBorder.gif", "NorthArrowWhiteBack.gif", "NorthArrowBlackBack.gif", "NorthArrowSTTrans.gif", "NorthArrowSTShaded.gif" };
        this.m_northArrowImageList = this.buildImageList();
        this.viewCentral = viewCentral;
        this.buildCenterPanel();
        (this.m_mainPanel = new JPanel()).setLayout(new BorderLayout());
        this.getContentPane().add(this.m_mainPanel);
        this.m_mainPanel.add(this.m_centerPanel, "Center");
        (this.m_okButton = new JButton("OK")).addActionListener(this);
        this.m_buttonPanel.add(this.m_okButton);
        this.m_mainPanel.add(this.m_buttonPanel, "South");
        this.setTitle("North Arrow Symbol Selection");
        this.pack();
        this.setLocationRelativeTo(locationRelativeTo);
        this.addComponentListener(this);
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final Component component = componentEvent.getComponent();
        final Dimension size = component.getSize();
        final Dimension minimumSize = component.getMinimumSize();
        final Dimension size2 = new Dimension(size);
        boolean b = false;
        if (size.width < minimumSize.width) {
            b = true;
            size2.width = minimumSize.width;
        }
        if (size.height < minimumSize.height) {
            b = true;
            size2.height = minimumSize.height;
        }
        if (b) {
            component.setSize(size2);
        }
    }
    
    public Dimension getMinimumSize() {
        final Dimension preferredSize;
        final Dimension dimension = preferredSize = this.getContentPane().getPreferredSize();
        preferredSize.height += 40;
        return dimension;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.m_okButton == actionEvent.getSource()) {
            JOptionPane.showMessageDialog(this.m_mainPanel, "Your selection will be applied the \nnext time you open an image.");
            this.setVisible(false);
        }
    }
    
    protected Image loadImage(final String s) {
        return Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource(s));
    }
    
    protected Vector buildImageList() {
        final Vector<Image> vector = new Vector<Image>();
        for (int i = 0; i < this.imageNames.length; ++i) {
            final Image loadImage = this.loadImage(this.imageNames[i]);
            if (loadImage != null) {
                vector.add(loadImage);
            }
        }
        return vector;
    }
    
    protected NorthArrowRadioPanel createRadioButtonWithImage(final int n) {
        return new NorthArrowRadioPanel(this.m_northArrowImageList.get(n));
    }
    
    protected void buildCenterPanel() {
        final RadioListener radioListener = new RadioListener();
        this.m_centerPanel.setLayout(new RiverLayout());
        final JLabel label = new JLabel("Select A North Arrow Symbol");
        label.setFont(new Font("SansSerif", 1, 12));
        this.m_centerPanel.add("center", label);
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.loadStateLastSelectedNorthArrow();
        for (int i = 0; i < this.m_northArrowImageList.size(); ++i) {
            final NorthArrowRadioPanel radioButtonWithImage = this.createRadioButtonWithImage(i);
            if (this.imageNames[i].equals(this.viewCentral.getPropertyManager().getNorthArrowImage())) {
                radioButtonWithImage.radioBox.setSelected(true);
            }
            radioButtonWithImage.radioBox.setActionCommand(Integer.toString(i));
            radioButtonWithImage.radioBox.addActionListener(radioListener);
            buttonGroup.add(radioButtonWithImage.radioBox);
            if (i % 3 == 0) {
                this.m_centerPanel.add("br left", radioButtonWithImage);
            }
            else {
                this.m_centerPanel.add("tab", radioButtonWithImage);
            }
        }
    }
    
    protected void loadStateLastSelectedNorthArrow() {
    }
    
    protected void saveStateLastSelectedNorthArrow() {
        this.viewCentral.getPropertyManager().setNorthArrowImage(this.imageNames[this.m_selected_image_index]);
    }
    
    protected class ImagePanel extends Canvas implements MouseListener
    {
        ImageIcon m_image;
        NorthArrowRadioPanel m_narp;
        
        public ImagePanel(final Image image, final NorthArrowRadioPanel narp) {
            this.m_image = new ImageIcon(image);
            this.m_narp = narp;
            this.setSize(this.m_image.getIconWidth(), this.m_image.getIconHeight());
            this.addMouseListener(this);
        }
        
        public void paint(final Graphics graphics) {
            graphics.drawImage(this.m_image.getImage(), 0, 0, this);
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            this.m_narp.mouseClicked();
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
    }
    
    protected class NorthArrowRadioPanel extends JPanel
    {
        public JRadioButton radioBox;
        
        NorthArrowRadioPanel(final Image image) {
            this.radioBox = new JRadioButton();
            this.setLayout(new RiverLayout());
            this.init(image);
        }
        
        protected void init(final Image image) {
            final ImagePanel imagePanel = new ImagePanel(image, this);
            this.add("left", this.radioBox);
            this.add("tab", imagePanel);
        }
        
        public void mouseClicked() {
            this.radioBox.setSelected(true);
            final ActionListener[] actionListeners = this.radioBox.getActionListeners();
            for (int i = 0; i < actionListeners.length; ++i) {
                actionListeners[i].actionPerformed(new ActionEvent(this.radioBox, 0, this.radioBox.getActionCommand()));
            }
        }
    }
    
    protected class RadioListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ChooseNorthArrowDialog.this.m_selected_image_index = Integer.parseInt(actionEvent.getActionCommand());
            ChooseNorthArrowDialog.this.saveStateLastSelectedNorthArrow();
        }
    }
}
