// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.ui;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.Frame;

public class VerifyWindow extends Frame
{
    public static final String Ident = "$Id: VerifyWindow.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private GridBagLayout layout;
    private WrappedLabel label;
    private Button button;
    private Image bkgndImage;
    private boolean netscapeEsque;
    
    public VerifyWindow(final Image bkgndImage, final boolean netscapeEsque, final String s, final String s2) {
        super(s);
        this.layout = new GridBagLayout();
        this.label = null;
        this.button = new Button("Close");
        this.bkgndImage = null;
        this.netscapeEsque = netscapeEsque;
        this.label = new WrappedLabel(s2);
        this.setCursor(3);
        if (bkgndImage != null) {
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(bkgndImage, 0);
                mediaTracker.waitForAll();
                if (mediaTracker.isErrorAny()) {
                    throw new Exception("error");
                }
                this.bkgndImage = bkgndImage;
            }
            catch (Exception ex) {}
        }
        this.setLocation(200, 100);
        this.setResizable(false);
        this.setFont(new Font("Verdana", 1, 12));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(this.layout);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        this.layout.setConstraints(this.label, gridBagConstraints);
        this.add(this.label);
        this.button.resize(50, 15);
        this.button.disable();
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(2, 0, 2, 0);
        gridBagConstraints.weighty = 0.0;
        this.layout.setConstraints(this.button, gridBagConstraints);
        this.add(this.button);
        this.pack();
        this.show();
    }
    
    public void paint(final Graphics graphics) {
        if (this.bkgndImage != null) {
            final Insets insets = this.insets();
            graphics.drawImage(this.bkgndImage, insets.left, insets.top, this);
        }
        super.paint(graphics);
    }
    
    public void sigVerified(final int n, final String s) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final WrappedLabel[] array = new WrappedLabel[2];
        this.removeAll();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        this.label.setText("Verification Complete.");
        this.layout.setConstraints(this.label, gridBagConstraints);
        this.add(this.label);
        if (n == 0) {
            array[0] = new WrappedLabel("The document is intact and has not been tampered with.");
            array[1] = new WrappedLabel("The document was signed " + s);
        }
        else {
            (array[0] = new WrappedLabel("The document has been tampered with!")).setForeground(Color.red);
            array[1] = new WrappedLabel("Do not open this document!");
        }
        gridBagConstraints.weighty = 1.0;
        for (int i = 0; i < array.length; ++i) {
            this.layout.setConstraints(array[i], gridBagConstraints);
            this.add(array[i]);
        }
        this.button.enable();
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(2, 0, 2, 0);
        gridBagConstraints.weighty = 0.0;
        this.layout.setConstraints(this.button, gridBagConstraints);
        this.add(this.button);
        this.validate();
        this.button.requestFocus();
        this.setCursor(0);
        if (this.netscapeEsque) {
            this.repaint();
        }
    }
    
    public Dimension preferredSize() {
        final Insets insets = this.insets();
        final int n = insets.left + insets.right;
        final int n2 = insets.top + insets.bottom;
        if (this.bkgndImage == null) {
            return new Dimension(300 + n, 122 + n2);
        }
        return new Dimension(this.bkgndImage.getWidth(this) + n, this.bkgndImage.getHeight(this) + n2);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || (event.target == this.button && this.button.isEnabled() && (event.id == 1001 || event.id == 401))) {
            this.dispose();
        }
        return super.handleEvent(event);
    }
}
