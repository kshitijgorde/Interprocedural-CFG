// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier;

import javax.swing.UIManager;
import java.awt.Dimension;
import com.ibm.xslt4j.bcel.generic.Type;
import javax.swing.ListModel;
import java.awt.Toolkit;

public class GraphicalVerifier
{
    boolean packFrame;
    
    public GraphicalVerifier() {
        this.packFrame = false;
        final VerifierAppFrame frame = new VerifierAppFrame();
        if (this.packFrame) {
            frame.pack();
        }
        else {
            frame.validate();
        }
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        frame.classNamesJList.setModel(new VerifierFactoryListModel());
        VerifierFactory.getVerifier(Type.OBJECT.getClassName());
        frame.classNamesJList.setSelectedIndex(0);
    }
    
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        new GraphicalVerifier();
    }
}
