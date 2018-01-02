// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.matrixExercise;

import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ApplicationMatrixExercise
{
    boolean packFrame;
    
    public ApplicationMatrixExercise() {
        this.packFrame = true;
        final MatrixExerciseFrame frame = new MatrixExerciseFrame();
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
    }
    
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        new ApplicationMatrixExercise();
    }
}
