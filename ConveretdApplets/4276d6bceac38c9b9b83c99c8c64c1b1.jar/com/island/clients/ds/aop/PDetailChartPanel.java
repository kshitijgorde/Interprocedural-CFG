// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import java.awt.Color;
import java.awt.image.ImageObserver;
import com.island.servers.ds.chart.stockorder.OrderChartData;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.awt.Panel;

class PDetailChartPanel extends Panel implements Runnable
{
    public static final float DELTA_PRICE = 0.1f;
    public static final int MAX_SHARES = 10000000;
    protected String SOCKET_READER;
    protected String CHART_CREATER;
    protected Object socketLock;
    protected Object chartLock;
    protected Object dataLock;
    protected Thread chartThread;
    protected Thread socketThread;
    protected URL codeBase;
    protected URL streamer;
    protected InputStream istream;
    protected Image offScreenImage;
    protected Graphics g;
    protected Applet applet;
    protected AverageOrderDetailChartDataDecoder dataController;
    protected AverageOrderChartPImage chartProducer;
    protected AverageOrderAppletData data;
    protected AverageOrderAppletData preData;
    protected int width;
    protected int height;
    protected int stage;
    protected String oldSymbol;
    protected String symbol;
    protected float oldDeltaPrice;
    protected float deltaPrice;
    protected int chartSleep;
    protected int socketSleep;
    protected int maxSteps;
    protected boolean isPaused;
    protected boolean started;
    protected boolean symbolChanged;
    protected boolean dataChanged;
    
    public PDetailChartPanel(final int width, final int height) {
        this.SOCKET_READER = "SOCKET_READER";
        this.CHART_CREATER = "CHART_CREATER";
        this.socketLock = new Object();
        this.chartLock = new Object();
        this.dataLock = new Object();
        this.stage = 0;
        this.oldSymbol = "";
        this.symbol = "";
        this.chartSleep = 50;
        this.socketSleep = 100;
        this.maxSteps = 5;
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
            this.update();
            synchronized (this.dataLock) {
                this.dataLock.notifyAll();
            }
            // monitorexit(this.dataLock)
            final long delta = nextTime - System.currentTimeMillis();
            this.pause((delta > 0L) ? delta : 0L);
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
                if (this.dataController != null) {
                    d = (AverageOrderAppletData)this.dataController.getOrderChartData(this.symbol, 10000000);
                }
            }
            // monitorexit(this.socketLock)
            if (d != null) {
                synchronized (this.chartLock) {
                    if (d.getDataType() != 1) {
                        this.dataChanged = true;
                        this.data = d;
                    }
                    else if (this.preData != null) {
                        this.preData.setTimeStamp(d.getTimeStamp());
                        this.data = this.preData;
                    }
                    this.stage = 0;
                }
                // monitorexit(this.chartLock)
            }
        }
        try {
            synchronized (this.dataLock) {
                final long delta = nextTime - System.currentTimeMillis();
                this.pause((delta > 0L) ? delta : 0L);
                if (delta > 0L) {
                    this.dataLock.wait(delta);
                }
            }
            // monitorexit(this.dataLock)
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
        this.initOffScreenImage();
        Image image = null;
        if (this.started) {
            synchronized (this.chartLock) {
                if (!this.isPaused) {
                    if (this.data != null) {
                        if (this.data.getDataType() == 2) {
                            image = this.getInvalidSymbolImage();
                            this.showMessage("Did not find symbol");
                        }
                        else if (this.stage == 0) {
                            this.chartProducer.setOrderChartData((OrderChartData)this.data);
                            ++this.stage;
                        }
                        this.preData = this.data;
                        this.data = null;
                    }
                    if (this.dataChanged) {
                        image = this.chartProducer.createChart();
                    }
                }
                if (image != null) {
                    g.drawImage(image, 0, 0, this);
                    final Graphics offg = this.offScreenImage.getGraphics();
                    offg.drawImage(image, 0, 0, null);
                    offg.dispose();
                }
                else {
                    g.drawImage(this.offScreenImage, 0, 0, this);
                }
            }
            // monitorexit(this.chartLock)
            g.dispose();
        }
    }
    
    public void reset() {
        if (this.symbol.equals(this.oldSymbol)) {
            if (this.deltaPrice != this.oldDeltaPrice) {
                this.chartProducer.setDeltaPrice(this.deltaPrice);
                synchronized (this.chartLock) {
                    this.chartProducer.resetPriceRange();
                }
                // monitorexit(this.chartLock)
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
                this.dataChanged = false;
                final AverageOrderAppletData averageOrderAppletData = null;
                this.data = averageOrderAppletData;
                this.preData = averageOrderAppletData;
                final CloseDecoderThread cdt = new CloseDecoderThread(this.dataController);
                cdt.start();
                this.showMessage("Requesting stream for " + this.symbol + " ... ");
                this.streamer = new URL(String.valueOf(this.codeBase.getProtocol()) + "://" + this.codeBase.getHost() + "/SERVICE/EVENT?STOCK=" + this.symbol);
                this.dataController = null;
                this.dataController = new AverageOrderDetailChartDataDecoder(this.streamer);
                this.showMessage("Starting chart ...");
            }
            // monitorexit(this.socketLock)
            synchronized (this.chartLock) {
                (this.chartProducer = new AverageOrderChartPImage(this.symbol)).setIslandChartMessage("http://www.island.com");
                this.chartProducer.setDeltaPrice(this.deltaPrice);
                this.chartProducer.setChartWidth(this.width);
                this.chartProducer.setChartHeight(this.height);
                this.chartProducer.setIslandChartMessageOn(true);
                this.chartProducer.setMaxStep(this.maxSteps);
                final Image tempImage = this.getImageTemplate(this.width, this.height);
                this.chartProducer.setImage(tempImage);
                this.initOffScreenImage();
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
    
    protected void pause(final long mSec) {
        try {
            Thread.sleep(mSec);
        }
        catch (InterruptedException ex) {}
    }
    
    public void setCodeBase(final URL codeBase) {
        this.codeBase = codeBase;
    }
    
    public void setSymbol(final String symbol) {
        this.oldSymbol = this.symbol;
        this.symbol = symbol.toUpperCase();
    }
    
    public void setDeltaPrice(final float deltaPrice) {
        this.deltaPrice = deltaPrice;
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
    
    public static class EventList
    {
        int current;
        int size;
        float expansion;
        Event[] events;
        
        public EventList() {
            this.size = 16;
            this.expansion = 0.5f;
            this.events = new Event[this.size];
            this.current = 0;
        }
        
        public EventList(final int size, final float expansion) {
            this.size = 16;
            this.expansion = 0.5f;
            this.size = size;
            this.expansion = expansion;
            this.events = new Event[size];
            this.current = 0;
        }
        
        public synchronized void addEvent(final Event event) {
            if (this.current >= this.size) {
                final int newSize = this.size + (int)(this.size * this.expansion);
                final Event[] newEvents = new Event[newSize];
                System.arraycopy(this.events, 0, newEvents, 0, this.size);
                this.size = newSize;
                this.events = newEvents;
            }
            this.events[this.current++] = event;
        }
        
        public synchronized Event[] getEvents() {
            final Event[] e = new Event[this.current];
            System.arraycopy(this.events, 0, e, 0, this.current);
            return e;
        }
        
        public synchronized int size() {
            return this.current;
        }
        
        public synchronized void clear() {
            this.current = 0;
        }
    }
}
