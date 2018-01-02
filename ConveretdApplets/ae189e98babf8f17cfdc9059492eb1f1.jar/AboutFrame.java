import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import symantec.itools.multimedia.ImageViewer;
import java.awt.Label;
import java.awt.Panel;
import symantec.itools.awt.BorderPanel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AboutFrame extends Frame
{
    static String buildNum;
    boolean fComponentsAdjusted;
    BorderPanel borderPanel1;
    Panel panel2;
    Label label2;
    Label label3;
    Label label4;
    ImageViewer imageViewer1;
    Panel panel1;
    Button okBtn;
    
    public AboutFrame(final JMProps jmProps) {
        this.fComponentsAdjusted = false;
        this.setBackground(jmProps.background);
        this.setForeground(jmProps.foreground);
        this.setFont(jmProps.font);
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 430, this.insets().top + this.insets().bottom + 220);
        this.borderPanel1 = new BorderPanel();
        try {
            this.borderPanel1.setBevelStyle(0);
        }
        catch (PropertyVetoException ex) {}
        this.borderPanel1.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPanel1.setBounds(this.insets().left, this.insets().top, 430, 186);
        this.add("Center", (Component)this.borderPanel1);
        (this.panel2 = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.panel2.setBounds(0, 88, 409, 72);
        ((Container)this.borderPanel1).add("South", this.panel2);
        (this.label2 = new Label("MochaMail LITE 1.0.1 (" + AboutFrame.buildNum + ")", 1)).setBounds(0, 0, 409, 24);
        this.panel2.add(this.label2);
        (this.label3 = new Label("(c) Maczieg Software, 1998-99, all rights reserved", 1)).setBounds(0, 24, 409, 24);
        this.panel2.add(this.label3);
        (this.label4 = new Label("For more information: http://www.maczieg.com", 1)).setBounds(0, 48, 409, 24);
        this.panel2.add(this.label4);
        this.imageViewer1 = new ImageViewer();
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("images/logo8.gif");
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            this.imageViewer1.setImage(this.getToolkit().createImage(array));
        }
        catch (Exception ex2) {}
        ((Component)this.imageViewer1).setBounds(0, 0, 409, 75);
        ((Container)this.borderPanel1).add("North", (Component)this.imageViewer1);
        (this.panel1 = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.panel1.setBounds(this.insets().left, this.insets().top + 186, 430, 34);
        this.add("South", this.panel1);
        (this.okBtn = new Button()).setActionCommand("button");
        this.okBtn.setLabel(jmProps.language.getProperty("button.ok", "   OK   "));
        this.okBtn.setBounds(191, 5, 47, 24);
        this.okBtn.setBackground(new Color(12632256));
        this.panel1.add(this.okBtn);
        this.setTitle(jmProps.language.getProperty("title.about", "About MochaMail"));
        this.addWindowListener(new SymWindow());
        this.okBtn.addActionListener(new SymAction());
    }
    
    public synchronized void show() {
        this.move(100, 100);
        this.pack();
        super.show();
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        this.setSize(this.insets().left + this.insets().right + size.width, this.insets().top + this.insets().bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(this.insets().left, this.insets().top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void Frame1_WindowClosing(final WindowEvent windowEvent) {
        this.hide();
    }
    
    void okBtn_Action(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    static {
        AboutFrame.buildNum = "MML990217";
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == AboutFrame.this) {
                AboutFrame.this.Frame1_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == AboutFrame.this.okBtn) {
                AboutFrame.this.okBtn_Action(actionEvent);
            }
        }
    }
}
