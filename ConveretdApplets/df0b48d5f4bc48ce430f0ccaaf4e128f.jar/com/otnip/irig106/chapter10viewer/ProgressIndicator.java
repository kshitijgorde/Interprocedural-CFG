// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10viewer;

import java.util.Enumeration;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.JPanel;

public class ProgressIndicator extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JProgressBar progressBar;
    String task;
    double interval;
    double min;
    double max;
    private boolean keepRunning;
    private double totalCountInv;
    private double currentCount;
    private JDialog dialog;
    private static ImageIcon[] animationImages;
    private static ImageIcon icon;
    private double nextUpdate;
    private double startTime;
    
    public ProgressIndicator(final String task, final boolean showCancelButton, final boolean animate) {
        this.task = null;
        this.interval = 1.0;
        this.min = 0.0;
        this.max = 1.0;
        this.keepRunning = true;
        this.nextUpdate = 0.0;
        this.startTime = System.currentTimeMillis();
        this.initComponents();
        this.task = task;
    }
    
    private void initComponents() {
        this.progressBar = new JProgressBar();
        this.setLayout(new GridBagLayout());
        this.progressBar.setToolTipText("What do you want to know");
        this.progressBar.setStringPainted(true);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.add(this.progressBar, gridBagConstraints);
    }
    
    public void setTask(final String task) {
        this.task = task;
        if (this.dialog != null) {
            this.dialog.setTitle(task);
        }
    }
    
    public String getTask() {
        return this.task;
    }
    
    public void setProgressBounds(final double min, final double max) {
        this.interval = max - min;
        this.min = min;
        this.max = max;
    }
    
    private static final String timeToText(int time) {
        String text = "";
        if (time > 3600) {
            text = text + time / 3600 + " Hours ";
            time %= 3600;
        }
        if (time > 60) {
            text = text + time / 60 + " Minutes ";
            time %= 60;
        }
        text = text + time + " Seconds";
        return text;
    }
    
    public void setProgress(final double value) {
        if (value > this.nextUpdate) {
            final double elapsedTime = System.currentTimeMillis() - this.startTime;
            final double timePerProgressUnit = elapsedTime / value;
            final double progressRemaining = 1.0 - value;
            final double timeRemaining = timePerProgressUnit * progressRemaining;
            final String text = "<html><b><p>Est. Time Remaining:  " + timeToText((int)(timeRemaining / 1000.0)) + "</p>" + "<p>Est. Total Time:  " + timeToText((int)((elapsedTime + timeRemaining) / 1000.0)) + "</p></b></html>";
            this.progressBar.setToolTipText(text);
            this.progressBar.setValue((int)((this.min + value * this.interval) * 100.0));
            this.nextUpdate += 0.009;
        }
    }
    
    public void removeProgressBar() {
        this.remove(this.progressBar);
        this.validate();
        this.repaint();
    }
    
    public void display(final Component component) {
        int iconSize = 64;
        if (ProgressIndicator.animationImages != null) {
            iconSize = ProgressIndicator.animationImages[0].getIconHeight();
        }
        (this.dialog = new JDialog()).setTitle(this.task);
        this.dialog.setModal(false);
        final JDialog dialog = this.dialog;
        final JDialog dialog2 = this.dialog;
        dialog.setDefaultCloseOperation(0);
        this.dialog.setResizable(false);
        this.dialog.add(this);
        this.dialog.setSize(236 + iconSize, iconSize + 11);
        this.dialog.setLocationRelativeTo(component);
        this.dialog.setAlwaysOnTop(true);
        this.dialog.setVisible(true);
    }
    
    public void dispose() {
        if (this.dialog != null) {
            this.dialog.dispose();
        }
    }
    
    public boolean keepRunning() {
        return this.keepRunning;
    }
    
    public void increment() {
        final double currentCount = this.currentCount + 1.0;
        this.currentCount = currentCount;
        this.setProgress(currentCount * this.totalCountInv);
    }
    
    public void setTotalCount(final double totalCount) {
        this.totalCountInv = 1.0 / totalCount;
    }
    
    public void reset() {
        this.startTime = System.currentTimeMillis();
        this.nextUpdate = 0.0;
        this.currentCount = 0.0;
        this.progressBar.setValue(0);
    }
    
    public static void setDialogIcon(final ImageIcon icon) {
        ProgressIndicator.icon = icon;
    }
    
    public static void setAnimationImages(final ImageIcon[] animationImages) {
        ProgressIndicator.animationImages = animationImages;
    }
    
    public static void setAnimationImages(final ZipFile zipFile) throws Exception {
        final Enumeration entries = zipFile.entries();
        ProgressIndicator.animationImages = new ImageIcon[zipFile.size()];
        while (entries.hasMoreElements()) {
            final ZipEntry zipEntry = entries.nextElement();
            final StringTokenizer str = new StringTokenizer(zipEntry.getName(), ".");
            final ImageIcon icon = new ImageIcon(ImageIO.read(zipFile.getInputStream(zipEntry)));
            final int iconIndex = Integer.parseInt(str.nextToken());
            ProgressIndicator.animationImages[iconIndex] = icon;
        }
    }
}
