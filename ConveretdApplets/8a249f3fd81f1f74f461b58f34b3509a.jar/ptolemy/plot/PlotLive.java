// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Button;

public abstract class PlotLive extends Plot implements Runnable
{
    private Thread _plotLiveThread;
    private boolean _plotting;
    private boolean _paused;
    private Button _startButton;
    private Button _stopButton;
    
    public abstract void addPoints();
    
    public void makeButtons() {
        if (this._startButton == null) {
            (this._startButton = new Button("start")).addActionListener(new StartButtonListener());
            this.add(this._startButton);
        }
        this._startButton.setVisible(true);
        if (this._stopButton == null) {
            (this._stopButton = new Button("stop")).addActionListener(new StopButtonListener());
            this.add(this._stopButton);
        }
        this._stopButton.setVisible(true);
        this._stopButton.setEnabled(false);
        this._startButton.setEnabled(true);
    }
    
    public void pause() {
        this._paused = true;
        this._plotting = false;
        this._stopButton.setEnabled(false);
        this._startButton.setEnabled(true);
    }
    
    public void run() {
        while (this._plotting || this._paused) {
            if (this._plotting) {
                this.addPoints();
                Thread.yield();
            }
            else {
                if (!this._paused) {
                    continue;
                }
                synchronized (this) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
    }
    
    public void setButtons(final boolean visible) {
        super.setButtons(visible);
        if (this._startButton == null) {
            (this._startButton = new Button("start")).addActionListener(new StartButtonListener());
            this.add(this._startButton);
        }
        this._startButton.setVisible(visible);
        if (this._stopButton == null) {
            (this._stopButton = new Button("stop")).addActionListener(new StopButtonListener());
            this.add(this._stopButton);
        }
        this._stopButton.setVisible(visible);
        if (visible) {
            this._stopButton.setEnabled(false);
            this._startButton.setEnabled(true);
        }
    }
    
    public synchronized void start() {
        this._plotting = true;
        this._paused = false;
        this._stopButton.setEnabled(true);
        this._startButton.setEnabled(false);
        if (this._plotLiveThread == null) {
            (this._plotLiveThread = new Thread(this, "PlotLive Thread")).start();
        }
        else {
            synchronized (this) {
                this.notifyAll();
            }
        }
    }
    
    public void stop() {
        this._plotting = false;
        this._paused = false;
        this._plotLiveThread = null;
    }
    
    public PlotLive() {
        this._plotting = false;
        this._paused = false;
    }
    
    class StartButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            PlotLive.this.start();
        }
    }
    
    class StopButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            PlotLive.this.pause();
        }
    }
}
