// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.GA;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class Launcher extends Applet implements ActionListener
{
    private Button launchButton;
    private boolean frameIsOpen;
    private GAFrame frame;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.add(this.launchButton = new Button("Start the World"), "Center");
        this.launchButton.addActionListener(this);
    }
    
    synchronized void frameHasOpened() {
        this.frameIsOpen = true;
        this.launchButton.setEnabled(true);
        this.launchButton.setLabel("End the World");
    }
    
    synchronized void frameHasClosed() {
        this.frameIsOpen = false;
        this.launchButton.setLabel("Start the World");
        this.frame = null;
    }
    
    public synchronized void destroy() {
        if (this.frame != null) {
            this.frame.close();
            this.frame = null;
        }
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        if (this.frame == null) {
            this.launchButton.setEnabled(false);
            try {
                this.frame = new GAFrame(this);
                return;
            }
            catch (Exception ex) {
                this.launchButton.setEnabled(true);
                this.launchButton.setLabel("Error! Try Again?");
                return;
            }
        }
        this.frame.close();
    }
}
