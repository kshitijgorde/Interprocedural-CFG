// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.Component;
import java.awt.Frame;
import com.objectbox.runner.util.JBLogger;
import java.awt.Image;
import java.util.Hashtable;
import com.objectbox.gui.lwcomp.FlatButton;

public class JBProcessButton extends FlatButton
{
    private boolean visual;
    public static final int REMOTE_LOADING = 1;
    public static final int LOCAL_LOADING = 2;
    public static final int CLOSING = 3;
    public static final int ERRORED = 4;
    public static final int RUNNING = 5;
    public static final int ZOOMBIE = 6;
    public static final int DONE = 5;
    protected int state;
    protected Hashtable images;
    protected Hashtable keywords;
    protected boolean label_appended;
    protected String appendstring;
    protected String originallabel;
    
    public JBProcessButton() {
        this.visual = false;
        this.state = 2;
        this.images = new Hashtable();
        this.keywords = new Hashtable();
        this.label_appended = false;
        this.appendstring = "";
        this.originallabel = "";
        this.initialize();
        this.setLabel("Applet");
    }
    
    public JBProcessButton(final String originallabel) {
        super(originallabel);
        this.visual = false;
        this.state = 2;
        this.images = new Hashtable();
        this.keywords = new Hashtable();
        this.label_appended = false;
        this.appendstring = "";
        this.originallabel = "";
        this.originallabel = originallabel;
    }
    
    protected void checkstate() {
        if (this.visual) {
            final Image image = this.images.get(new Integer(this.state));
            if (image != null) {
                this.setImage(image, 3);
            }
        }
        else {
            this.setImage(null);
            this.appendstring = "(" + this.keywords.get(new Integer(this.state)) + ")";
            this.label_appended = true;
            this.setLabel(String.valueOf(this.originallabel) + this.appendstring);
        }
        this.repaint();
        this.validate();
    }
    
    public int getState() {
        return this.state;
    }
    
    public boolean getVisual() {
        return this.visual;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initialize() {
        this.setName("JBProcessButton");
        this.setSize(150, 150);
        this.keywords.put(new Integer(2), "Loading");
        this.keywords.put(new Integer(1), "Fetching");
        this.keywords.put(new Integer(5), "Running");
        this.keywords.put(new Integer(3), "Closing");
        this.keywords.put(new Integer(4), "Error");
        this.keywords.put(new Integer(6), "Not responding");
        try {
            this.images.put(new Integer(1), JBee.resources.getImageResource("/images/remote_state.gif"));
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
        }
        try {
            this.images.put(new Integer(2), JBee.resources.getImageResource("/images/local_state.gif"));
        }
        catch (Throwable t2) {
            JBLogger.log(t2.toString());
        }
        try {
            this.images.put(new Integer(5), JBee.resources.getImageResource("/images/running_state.gif"));
        }
        catch (Throwable t3) {
            JBLogger.log(t3.toString());
        }
        try {
            this.images.put(new Integer(3), JBee.resources.getImageResource("/images/closing_state.gif"));
        }
        catch (Throwable t4) {
            JBLogger.log(t4.toString());
        }
        try {
            this.images.put(new Integer(4), JBee.resources.getImageResource("/images/errored_state.gif"));
        }
        catch (Throwable t5) {
            JBLogger.log(t5.toString());
        }
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final JBProcessButton jbProcessButton = new JBProcessButton();
            frame.add("Center", jbProcessButton);
            frame.setSize(jbProcessButton.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of com.objectbox.runner.gui.JBProcessButton");
        }
    }
    
    public void setState(final int state) {
        this.state = state;
        this.checkstate();
    }
    
    public void setStateImage(final Image image, final int n) {
        this.images.put(new Integer(n), image);
    }
    
    public void setVisual(final boolean visual) {
        this.visual = visual;
        this.checkstate();
    }
}
