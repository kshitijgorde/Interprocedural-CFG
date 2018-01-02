// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.util.JBLogger;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.TextArea;

public class CustomTextArea extends TextArea
{
    public CustomTextArea() {
        super("", 0, 0, 3);
        this.initialize();
    }
    
    public CustomTextArea(final int n, final int n2) {
        super(n, n2);
    }
    
    public CustomTextArea(final String s) {
        super(s);
    }
    
    public CustomTextArea(final String s, final int n, final int n2) {
        super(s, n, n2);
    }
    
    public CustomTextArea(final String s, final int n, final int n2, final int n3) {
        super(s, n, n2, n3);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(250, 50);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(250, 50);
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initialize() {
        this.setName("CustomTextArea");
        this.setSize(232, 72);
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
            final CustomTextArea customTextArea = new CustomTextArea();
            frame.add("Center", customTextArea);
            frame.setSize(customTextArea.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of com.objectbox.vplan.gui.CustomTextArea");
        }
    }
}
