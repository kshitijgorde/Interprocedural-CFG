// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.matrixExercise.applet;

import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.UIManager;
import javax.swing.JButton;
import org.namekata.matrixExercise.MatrixExerciseFrame;
import javax.swing.JApplet;

public class AppletmatrixExercise extends JApplet
{
    private MatrixExerciseFrame matrixFrame1;
    private JButton jButtonStart;
    
    public AppletmatrixExercise() {
        this.jButtonStart = null;
    }
    
    @Override
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
    }
    
    @Override
    public void start() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
        if (this.jButtonStart == null) {
            (this.jButtonStart = new JButton()).setText(ResourceBundle.getBundle("org.namekata.matrixExercise.applet/AppletMatrixExercise").getString("ExecuteApplet"));
            this.jButtonStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    AppletmatrixExercise.this.jButtonStart_actionPerformed(e);
                }
            });
            this.setContentPane(this.jButtonStart);
        }
    }
    
    @Override
    public void stop() {
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public String getAppletInfo() {
        return "Applet for MatrixExercise";
    }
    
    private void finished() {
        this.jButtonStart.setEnabled(true);
    }
    
    void jButtonStart_actionPerformed(final ActionEvent e) {
        this.jButtonStart.setEnabled(false);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        (this.matrixFrame1 = new MatrixExerciseFrame(false)).pack();
        this.matrixFrame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                AppletmatrixExercise.this.finished();
            }
        });
        this.matrixFrame1.setLocation((d.width - this.matrixFrame1.getSize().width) / 2, (d.height - this.matrixFrame1.getSize().height) / 2);
        this.matrixFrame1.setVisible(true);
    }
}
