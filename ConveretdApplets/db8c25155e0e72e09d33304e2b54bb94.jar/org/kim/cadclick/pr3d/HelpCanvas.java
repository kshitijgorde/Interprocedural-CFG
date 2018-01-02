// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Properties;
import c.a;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class HelpCanvas extends Panel implements ActionListener
{
    Font font;
    Font fontBold;
    Button btnClose;
    Hyperlink link;
    private static final int WIDTH = 280;
    private static final int HEIGHT = 275;
    
    public HelpCanvas(final Applet applet, final a a) {
        final Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResource("/conf/HelpCanvas.properties").openConnection().getInputStream());
        }
        catch (Exception ex) {
            System.out.println("Could not load 'HelpCanvas.properties'");
            ex.printStackTrace();
        }
        final Rectangle bounds = applet.getBounds();
        this.setLocation((bounds.width - 280) / 2, (bounds.height - 275) / 2);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        this.setLayout(layout);
        this.setBackground(Color.white);
        this.font = applet.getFont().deriveFont(11.0f).deriveFont(0);
        this.fontBold = applet.getFont().deriveFont(11.0f).deriveFont(1);
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_MouseControls"), this.fontBold, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        (this.btnClose = new Button(a.a("HelpCanvas_CloseButton"))).addActionListener(this);
        gridBagConstraints.anchor = 13;
        this.a(this.btnClose, layout, gridBagConstraints);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_MouseLeftLabel"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_MouseLeftFunction"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_MouseLeftCtrlLabel"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_MouseLeftCtrlFunction"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_MouseLeftShiftLabel"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_MouseLeftShiftFunction"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_KeyboardControls"), this.fontBold, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_KeyboardKey2"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_KeyboardKey7"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_KeyboardKey3"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_KeyboardKey8"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_KeyboardKey4"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_KeyboardKey5"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_KeyboardKey9"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = -1;
        this.a(a.a("HelpCanvas_KeyboardKey6"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(a.a("HelpCanvas_KeyboardKey0"), this.font, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.a(properties.getProperty("title"), this.fontBold, layout, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        this.a(this.link = new Hyperlink(properties.getProperty("link"), properties.getProperty("linkText"), this.fontBold, applet), layout, gridBagConstraints);
        this.setSize(280, 275);
    }
    
    private void a(final String s, final Font font, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints) {
        final Label label = new Label(s);
        label.setFont(font);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        this.add(label);
    }
    
    private void a(final Component component, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints) {
        gridBagLayout.setConstraints(component, gridBagConstraints);
        this.add(component);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, 279, 274);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.btnClose) {
            this.setVisible(false);
        }
    }
}
