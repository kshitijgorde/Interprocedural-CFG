import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ProgressIndicator extends JPanel implements ActionListener
{
    private imgViewer applet;
    private Timer updateTimer;
    private JProgressBar progressBar;
    private boolean isActive;
    private WorkMonitor[] workers;
    
    ProgressIndicator(final imgViewer applet) {
        this.applet = applet;
        this.setLayout(new BoxLayout(this, 0));
        this.progressBar = new JProgressBar();
        final Dimension maximumSize = new Dimension(50, 18);
        this.progressBar.setPreferredSize(maximumSize);
        this.progressBar.setMinimumSize(maximumSize);
        this.progressBar.setMaximumSize(maximumSize);
        this.progressBar.setFont(applet.normalFont);
        this.progressBar.setForeground(Color.BLUE);
        this.add(this.progressBar);
        maximumSize.width = 130;
        this.setPreferredSize(maximumSize);
        this.setMaximumSize(maximumSize);
        this.isActive = false;
        this.updateTimer = new Timer(200, this);
    }
    
    public void addWorker(final WorkMonitor workMonitor) {
        int n = 1;
        if (this.workers != null) {
            n += this.workers.length;
        }
        final WorkMonitor[] workers = new WorkMonitor[n];
        for (int i = 0; i < n - 1; ++i) {
            workers[i] = this.workers[i];
        }
        workers[n - 1] = workMonitor;
        this.workers = workers;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!this.isActive) {
            this.applet.statusBar.progress.setVisible(false);
            this.updateTimer.stop();
            return;
        }
        WorkMonitor workMonitor = null;
        for (int i = 0; i < this.workers.length; ++i) {
            if (this.workers[i].isWorking()) {
                workMonitor = this.workers[i];
                break;
            }
        }
        if (workMonitor != null) {
            this.progressBar.setString(workMonitor.getWorkLabel());
            this.progressBar.setStringPainted(true);
            this.progressBar.setMaximum(workMonitor.getTotalWork());
            this.progressBar.setValue(workMonitor.getWorkComplete());
            if (this.isActive) {
                this.applet.statusBar.progress.setVisible(true);
            }
        }
    }
    
    public void setBusy() {
        this.isActive = true;
        this.updateTimer.start();
    }
    
    public void clearBusy() {
        this.isActive = false;
    }
}
