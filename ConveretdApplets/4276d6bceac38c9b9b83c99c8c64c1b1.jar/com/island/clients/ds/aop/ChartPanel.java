// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import com.island.servers.ds.chart.stockorder.OrderChartData;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.awt.Panel;

class ChartPanel extends Panel implements Runnable
{
    public static int SHARES;
    protected String SOCKET_READER;
    protected String CHART_CREATER;
    protected Object socketLock;
    protected Object chartLock;
    protected Thread chartThread;
    protected Thread socketThread;
    protected URL codeBase;
    protected URL streamer;
    protected InputStream istream;
    protected Image offScreenImage;
    protected Graphics g;
    protected Applet applet;
    protected AverageOrderChartDataDecoder dataController;
    protected AverageOrderChartImage chartProducer;
    protected AverageOrderAppletData data;
    protected int width;
    protected int height;
    protected int stage;
    protected String oldSymbol;
    protected String symbol;
    protected int oldShares;
    protected int shares;
    protected int chartSleep;
    protected int socketSleep;
    protected float rollFactor;
    protected boolean isPaused;
    protected boolean started;
    protected boolean symbolChanged;
    protected boolean dataChanged;
    
    static {
        ChartPanel.SHARES = 100000;
    }
    
    public ChartPanel(final int width, final int height) {
        this.SOCKET_READER = "SOCKET_READER";
        this.CHART_CREATER = "CHART_CREATER";
        this.socketLock = new Object();
        this.chartLock = new Object();
        this.stage = 0;
        this.oldSymbol = "";
        this.symbol = "";
        this.chartSleep = 100;
        this.socketSleep = 1000;
        this.rollFactor = 0.4f;
        this.isPaused = true;
        this.started = false;
        this.symbolChanged = false;
        this.dataChanged = false;
        this.width = width;
        this.height = height;
        this.setVisible(true);
    }
    
    public void toLive() {
        this.chartThread = new Thread(this, this.CHART_CREATER);
        this.socketThread = new Thread(this, this.SOCKET_READER);
        this.chartThread.start();
        this.socketThread.start();
    }
    
    public void run() {
        while (true) {
            if (Thread.currentThread().getName().equals(this.CHART_CREATER)) {
                this.updateChart();
            }
            else {
                if (!Thread.currentThread().getName().equals(this.SOCKET_READER)) {
                    continue;
                }
                this.updateData();
            }
        }
    }
    
    protected void updateChart() {
        final long nextTime = System.currentTimeMillis() + this.chartSleep;
        try {
            if (this.data != null) {
                this.update();
            }
            final long delta = nextTime - System.currentTimeMillis();
            Thread.sleep((delta > 0L) ? delta : 0L);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void updateData() {
        final long nextTime = System.currentTimeMillis() + this.socketSleep;
        if (this.started) {
            AverageOrderAppletData d = null;
            synchronized (this.socketLock) {
                d = (AverageOrderAppletData)this.dataController.getOrderChartData(this.symbol, this.shares);
                this.dataChanged = true;
            }
            // monitorexit(this.socketLock)
            if (d != null) {
                synchronized (this.chartLock) {
                    if (d.getDataType() != 1) {
                        this.data = d;
                    }
                    else if (this.data != null) {
                        this.data.setTimeStamp(d.getTimeStamp());
                    }
                    this.stage = 0;
                }
                // monitorexit(this.chartLock)
            }
        }
        try {
            final long delta = nextTime - System.currentTimeMillis();
            Thread.sleep((delta > 0L) ? delta : 0L);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
        Graphics g = null;
        if ((g = this.getGraphics()) != null) {
            this.paint(g);
            g.dispose();
        }
    }
    
    public void paint(final Graphics g) {
        if (this.offScreenImage == null || this.offScreenImage.getHeight(null) != this.height || this.offScreenImage.getWidth(null) != this.width) {
            this.offScreenImage = this.createImage(this.width, this.height);
            final Graphics offg = this.offScreenImage.getGraphics();
            offg.setColor(Color.white);
            offg.fillRect(0, 0, this.width - 1, this.height - 1);
            offg.dispose();
        }
        Image image = null;
        if (this.started) {
            synchronized (this.chartLock) {
                if (!this.isPaused && this.data != null) {
                    if (this.data.getDataType() == 2) {
                        image = this.getInvalidSymbolImage();
                        this.showMessage("Did not find symbol");
                    }
                    else {
                        if (this.stage == 0) {
                            this.chartProducer.setOrderChartData((OrderChartData)this.data);
                            ++this.stage;
                        }
                        image = this.chartProducer.createChart();
                    }
                }
            }
            // monitorexit(this.chartLock)
            if (image != null) {
                g.drawImage(image, 0, 0, this);
                final Graphics offg2 = this.offScreenImage.getGraphics();
                offg2.drawImage(image, 0, 0, null);
                offg2.dispose();
            }
            else {
                g.drawImage(this.offScreenImage, 0, 0, this);
            }
            g.dispose();
        }
    }
    
    public void reset() {
        if (this.symbol.equals(this.oldSymbol)) {
            if (this.shares != this.oldShares) {
                this.chartProducer.setSize(this.shares);
            }
            this.update();
            return;
        }
        try {
            synchronized (this.chartLock) {
                this.isPaused = true;
                this.clearScreen();
            }
            // monitorexit(this.chartLock)
            synchronized (this.socketLock) {
                final CloseDecoderThread cdt = new CloseDecoderThread(this.dataController);
                cdt.start();
                this.showMessage("Requesting stream for " + this.symbol + " ... ");
                this.streamer = new URL(String.valueOf(this.codeBase.getProtocol()) + "://" + this.codeBase.getHost() + "/SERVICE/OCHART?STOCK=" + this.symbol);
                this.dataController = new AverageOrderChartDataDecoder(this.streamer);
                this.showMessage("Starting chart ...");
            }
            // monitorexit(this.socketLock)
            synchronized (this.chartLock) {
                (this.chartProducer = new AverageOrderChartImage(this.symbol)).setIslandChartMessage("http://www.island.com");
                this.chartProducer.setRollFactor(this.rollFactor);
                this.chartProducer.setChartWidth(this.width);
                this.chartProducer.setChartHeight(this.height);
                this.chartProducer.setIslandChartMessageOn(true);
                this.chartProducer.setRollFactor(this.rollFactor);
                final Image tempImage = this.getImageTemplate(this.width, this.height);
                this.chartProducer.setImage(tempImage);
                this.isPaused = false;
                this.data = null;
            }
            // monitorexit(this.chartLock)
            this.update();
        }
        catch (Exception e) {
            this.showMessage("Fail to obtain stream for " + this.symbol);
        }
    }
    
    protected Image getImageTemplate(final int width, final int height) {
        final Image image = this.createImage(width, height);
        final Graphics g = image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, width - 1, height - 1);
        g.dispose();
        return image;
    }
    
    protected void initOffScreenImage() {
        if (this.offScreenImage == null || this.offScreenImage.getHeight(null) != this.height || this.offScreenImage.getWidth(null) != this.width) {
            this.offScreenImage = this.createImage(this.width, this.height);
        }
        final Graphics offg = this.offScreenImage.getGraphics();
        offg.setColor(Color.white);
        offg.fillRect(0, 0, this.width - 1, this.height - 1);
        offg.dispose();
    }
    
    protected void clearScreen() {
        this.initOffScreenImage();
        this.update();
    }
    
    protected void showMessage(final String message) {
        if (this.applet != null) {
            this.applet.showStatus(message);
        }
        else {
            System.out.println(message);
        }
    }
    
    public void destroy() {
        if (this.istream != null) {
            try {
                this.istream.close();
            }
            catch (Exception ex) {}
        }
        if (this.streamer != null) {
            this.streamer = null;
        }
    }
    
    protected Image getInvalidSymbolImage() {
        final Image invalidSymbol = this.createImage(this.width, this.height);
        final Graphics g = invalidSymbol.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, this.width - 1, this.height - 1);
        g.dispose();
        return invalidSymbol;
    }
    
    public void setCodeBase(final URL codeBase) {
        this.codeBase = codeBase;
    }
    
    public void setSymbol(final String symbol) {
        this.oldSymbol = this.symbol;
        this.symbol = symbol.toUpperCase();
    }
    
    public void setShares(final int shares) {
        this.shares = shares;
    }
    
    public void setIsPaused(final boolean isPaused) {
        this.isPaused = isPaused;
        if (isPaused) {
            this.showMessage("Chart paused");
        }
        else {
            this.showMessage("Chart started");
        }
    }
    
    public void setStarted() {
        this.started = true;
    }
    
    public void setApplet(final Applet applet) {
        this.applet = applet;
    }
    
    public void setSocketSleep(final int socketSleep) {
        this.socketSleep = socketSleep;
    }
    
    public void setChartSleep(final int chartSleep) {
        this.chartSleep = chartSleep;
    }
}
