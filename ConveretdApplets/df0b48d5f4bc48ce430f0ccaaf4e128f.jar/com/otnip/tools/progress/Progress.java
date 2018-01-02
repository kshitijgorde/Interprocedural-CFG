// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools.progress;

import java.util.Iterator;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import com.otnip.tools.IconFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JPanel;

public class Progress extends JPanel
{
    private JProgressBar progressBar;
    private JButton stopButton;
    private String taskName;
    private double valueForNextUpdate;
    private double startTime;
    private double maximumCounterValueInverse;
    private double currentCount;
    private ArrayList<StopListener> stopListeners;
    private JDialog dialog;
    
    public Progress(final String taskName, final boolean indeterminate) {
        this.taskName = "";
        this.valueForNextUpdate = 0.0;
        this.startTime = 0.0;
        this.maximumCounterValueInverse = 0.0;
        this.currentCount = 0.0;
        this.stopListeners = new ArrayList<StopListener>();
        this.taskName = taskName;
        this.initComponents();
        this.progressBar.setIndeterminate(indeterminate);
        this.progressBar.setStringPainted(!indeterminate);
        this.reset();
    }
    
    private void initComponents() {
        this.stopButton = new JButton();
        this.progressBar = new JProgressBar();
        this.setLayout(new BorderLayout());
        this.stopButton.setIcon(IconFactory.get("IconExperience/icons/48x48/plain/media_stop_red.png"));
        this.stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Progress.this.stopButtonActionPerformed(evt);
            }
        });
        this.add(this.stopButton, "West");
        this.progressBar.setStringPainted(true);
        this.add(this.progressBar, "Center");
    }
    
    private void stopButtonActionPerformed(final ActionEvent evt) {
        for (final StopListener stopListener : this.stopListeners) {
            stopListener.stop();
        }
    }
    
    public void setTaskName(final String taskName) {
        this.taskName = taskName;
        if (this.dialog != null) {
            this.dialog.setTitle(taskName);
        }
    }
    
    public String getTaskName() {
        return this.taskName;
    }
    
    public void setProgress(final double progressValue) {
        if (progressValue > this.valueForNextUpdate) {
            this.progressBar.setValue((int)(progressValue * 100.0));
            this.valueForNextUpdate += 0.009;
            final double elapsedTime = (System.currentTimeMillis() - this.startTime) * 0.001;
            final double timePerUnit = progressValue / elapsedTime;
            final double estRemainingTime = (1.0 - progressValue) * timePerUnit;
            final double estTotalTime = elapsedTime + estRemainingTime;
            final String toolTipText = "<html><b><i><p>Elapsed Time:  " + timeToString((int)elapsedTime) + "</p>" + "<p>Est. Remaining Time:  " + timeToString((int)estRemainingTime) + "</p>" + "<p>Est. Total Time:  " + timeToString((int)estTotalTime) + "</p>" + "</i></b></html>";
            this.progressBar.setToolTipText(toolTipText);
        }
    }
    
    public void setMaximumCounterValue(final double maximumCounterValue) {
        this.maximumCounterValueInverse = 1.0 / maximumCounterValue;
    }
    
    public void incrementCounter() {
        final double currentCount = this.currentCount + 1.0;
        this.currentCount = currentCount;
        this.setProgress(currentCount * this.maximumCounterValueInverse);
    }
    
    public void incrementCounter(final double N) {
        final double currentCount = this.currentCount + N;
        this.currentCount = currentCount;
        this.setProgress(currentCount * this.maximumCounterValueInverse);
    }
    
    public void reset() {
        this.progressBar.setValue(0);
        this.valueForNextUpdate = 0.0;
        this.startTime = System.currentTimeMillis();
        this.currentCount = 0.0;
    }
    
    public JDialog display(final Component c) {
        if (this.dialog == null) {
            (this.dialog = new JDialog()).setTitle(this.taskName);
            this.dialog.getContentPane().add(this);
            this.dialog.setLocationRelativeTo(c);
            this.dialog.setSize(250, 75);
            this.dialog.setResizable(false);
        }
        this.dialog.setVisible(true);
        return this.dialog;
    }
    
    public void removeStopButton() {
        this.remove(this.stopButton);
    }
    
    public void addStopListener(final StopListener stopListener) {
        this.stopListeners.add(stopListener);
    }
    
    public void removeStopListener(final StopListener stopListener) {
        this.stopListeners.remove(stopListener);
    }
    
    public StopListener[] getStopListeners() {
        return this.stopListeners.toArray(new StopListener[this.stopListeners.size()]);
    }
    
    private static final String timeToString(int time) {
        String result = "";
        if (time > 3600) {
            result = result + time / 3600 + " Hour(s), ";
            time %= 3600;
        }
        if (time > 60) {
            result = result + time / 60 + " Minute(s), ";
            time %= 60;
        }
        result = result + time + " Second(s)";
        return result;
    }
    
    public static void main(final String[] args) {
        final Progress p = new Progress("Hello", true);
        p.display(null);
        for (int N = 100, i = 0; i < N; ++i) {
            p.setProgress(i / N);
            try {
                Thread.sleep(100L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000L);
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        System.exit(0);
    }
}
