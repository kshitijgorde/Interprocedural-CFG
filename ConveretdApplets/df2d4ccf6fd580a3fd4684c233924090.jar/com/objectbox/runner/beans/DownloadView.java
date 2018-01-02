// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.awt.Font;
import java.awt.Component;

public class DownloadView extends Component
{
    private boolean running;
    private Thread repaintthread;
    private Font font;
    private static long currload;
    private long bytes_pr_time;
    private long average_load;
    private int timespansec;
    private double maxload;
    private double mark;
    private int mark_delay;
    private long totalload;
    private long totaltime;
    private DecimalFormat format;
    public boolean testmode;
    
    public DownloadView() {
        this.running = true;
        this.repaintthread = null;
        this.font = new Font("SansSerif", 1, 10);
        this.bytes_pr_time = 0L;
        this.average_load = 0L;
        this.timespansec = 1;
        this.maxload = 0.0;
        this.mark = 0.0;
        this.mark_delay = 5;
        this.totalload = 0L;
        this.totaltime = 0L;
        this.format = new DecimalFormat();
        this.testmode = false;
        this.initialize();
    }
    
    public static final void addBytes(final long n) {
        DownloadView.currload += n;
    }
    
    public void finalize() {
        if (this.repaintthread != null) {
            this.repaintthread.stop();
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(100, 20);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(100, 20);
    }
    
    public boolean getRunning() {
        return this.running;
    }
    
    private void go() {
        (this.repaintthread = (Thread)new DownloadView$1.repainter(this)).start();
    }
    
    private void handleException(final Throwable t) {
        System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        t.printStackTrace(System.out);
    }
    
    private void initialize() {
        this.format.applyPattern("####.## 'kbs'");
        this.setName("DownloadView");
        this.setSize(188, 36);
        if (this.running) {
            this.go();
        }
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t2) {
                frame = new Frame();
            }
            final DownloadView downloadView = new DownloadView();
            downloadView.testmode = true;
            frame.add("Center", downloadView);
            frame.setSize(downloadView.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t) {
            System.err.println("Exception occurred in main() of com.objectbox.runner.beans.ResourceView");
            t.printStackTrace(System.out);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(this.font);
        final double maxload = DownloadView.currload / this.timespansec / 1024.0;
        this.totalload += DownloadView.currload;
        this.totaltime += this.timespansec;
        final double n = maxload / this.maxload;
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final double n2 = n * width;
        final String string = "Max." + this.format.format(this.maxload);
        graphics.clearRect(0, 0, width, height);
        graphics.setColor(Color.orange);
        graphics.fill3DRect(0, 0, (int)n2, height, true);
        graphics.setColor(Color.darkGray);
        graphics.drawString("" + this.format.format(maxload), 3, 10);
        graphics.drawString(string, width - graphics.getFontMetrics(this.font).stringWidth(string) - 2, height - 2);
        graphics.setColor(this.getBackground());
        graphics.draw3DRect(0, 0, width - 1, height - 1, true);
        if (this.totaltime > 60L) {
            final long n3 = 0L;
            this.totaltime = n3;
            this.totalload = n3;
            this.maxload = 0.0;
        }
        if (maxload > this.maxload) {
            this.maxload = maxload;
        }
        DownloadView.currload = 0L;
    }
    
    public void setRunning(final boolean running) {
        this.running = running;
        if (this.running && this.repaintthread != null) {
            this.go();
        }
        else if (this.repaintthread != null) {
            this.repaintthread.stop();
        }
    }
    
    static int access$timespansec(final DownloadView downloadView) {
        return downloadView.timespansec;
    }
}
