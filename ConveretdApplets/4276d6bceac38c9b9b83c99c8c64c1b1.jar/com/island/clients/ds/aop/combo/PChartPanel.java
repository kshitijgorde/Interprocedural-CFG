// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop.combo;

import com.island.clients.ds.aop.DataDecoder;
import com.island.clients.ds.aop.CloseDecoderThread;
import com.island.servers.ds.chart.stockorder.OrderChartData;
import java.awt.Color;
import java.awt.image.ImageObserver;
import com.island.clients.ds.aop.AverageOrderAppletData;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.awt.Panel;

class PChartPanel extends Panel implements Runnable
{
    public static final float DELTA_PRICE = 0.1f;
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
    protected AverageOrderComboChartDataDecoder dataController;
    protected AverageOrderComboChartPImage chartProducer;
    protected AverageOrderAppletData data;
    protected DataList dataList;
    protected int width;
    protected int height;
    protected int priceHeight;
    protected int historyHeight;
    protected int msgHeight;
    protected int stage;
    protected String oldSymbol;
    protected String symbol;
    protected float oldDeltaPrice;
    protected float deltaPrice;
    protected int chartSleep;
    protected int socketSleep;
    protected float rollFactor;
    protected int maxSteps;
    protected boolean isPaused;
    protected boolean started;
    protected boolean symbolChanged;
    protected boolean dataChanged;
    protected int[] secondsInHistory;
    protected int currentZoom;
    protected int milliSecondsInHistory;
    protected int history;
    protected int timeLimit;
    
    static {
        PChartPanel.SHARES = 10000000;
    }
    
    public PChartPanel(final int width, final int priceHeight, final int historyHeight, final int msgHeight) {
        this.SOCKET_READER = "SOCKET_READER";
        this.CHART_CREATER = "CHART_CREATER";
        this.socketLock = new Object();
        this.chartLock = new Object();
        this.stage = 0;
        this.oldSymbol = "";
        this.symbol = "";
        this.chartSleep = 100;
        this.socketSleep = 500;
        this.rollFactor = 0.3f;
        this.maxSteps = 5;
        this.isPaused = true;
        this.started = false;
        this.symbolChanged = false;
        this.dataChanged = false;
        this.secondsInHistory = new int[] { 1, 5, 10, 15, 30, 60, 300, 600, 900, 1800, 3600 };
        this.currentZoom = 2;
        this.milliSecondsInHistory = this.secondsInHistory[this.currentZoom] * 1000;
        this.history = 30;
        this.timeLimit = 600000;
        this.width = width;
        this.priceHeight = priceHeight;
        this.historyHeight = historyHeight;
        this.msgHeight = msgHeight;
        this.height = priceHeight + historyHeight + msgHeight;
        this.dataList = new DataList(this.timeLimit);
        this.setVisible(true);
    }
    
    public void toLive() {
        this.chartThread = new Thread(this, this.CHART_CREATER);
        this.socketThread = new Thread(this, this.SOCKET_READER);
        this.chartThread.start();
        this.socketThread.start();
        this.reset();
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
            DataList list = null;
            AverageOrderAppletData d = null;
            synchronized (this.socketLock) {
                list = this.dataController.getOrderChartData(this.symbol, PChartPanel.SHARES);
                if (list != null && list.size > 0) {
                    d = (AverageOrderAppletData)list.getLast().data;
                    this.updateDataList(list);
                    list = null;
                }
            }
            // monitorexit(this.socketLock)
            synchronized (this.chartLock) {
                if (d != null) {
                    this.dataChanged = true;
                    final int dataType = d.getDataType();
                    if (dataType == 0 || dataType == 2) {
                        this.data = d;
                    }
                    else if (dataType == 1) {
                        this.data.setUpdateTime(d.getTimeStamp());
                    }
                    else {
                        this.dataChanged = false;
                    }
                    this.stage = 0;
                }
                else {
                    this.dataChanged = false;
                }
            }
            // monitorexit(this.chartLock)
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
    
    protected void updateDataList(final DataList list) {
        if (list == null) {
            return;
        }
        DataList.Data data = null;
        while ((data = list.removeFirst()) != null) {
            if (((AverageOrderAppletData)data.data).getDataType() == 0) {
                this.dataList.add(data);
            }
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
            if (!this.isPaused) {
                synchronized (this.chartLock) {
                    if (this.data != null) {
                        if (this.data.getDataType() == 2) {
                            image = this.getInvalidSymbolImage();
                            this.showMessage("Did not find symbol");
                            this.dataChanged = false;
                            this.data = null;
                        }
                        else if (this.stage == 0) {
                            this.chartProducer.setOrderChartData((OrderChartData)this.data);
                            this.chartProducer.setHistoryData(this.dataList.toArray());
                            ++this.stage;
                        }
                    }
                    if (this.dataChanged) {
                        image = this.chartProducer.createChart();
                    }
                }
                // monitorexit(this.chartLock)
            }
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
            if (this.deltaPrice != this.oldDeltaPrice) {
                this.chartProducer.setDeltaPrice(this.deltaPrice);
            }
            this.isPaused = false;
            this.update();
            return;
        }
        try {
            synchronized (this.chartLock) {
                this.isPaused = true;
                this.dataChanged = false;
                this.clearScreen();
            }
            // monitorexit(this.chartLock)
            synchronized (this.socketLock) {
                final CloseDecoderThread cdt = new CloseDecoderThread(this.dataController);
                cdt.start();
                this.showMessage("Requesting stream for " + this.symbol + " ... ");
                this.streamer = new URL(String.valueOf(this.codeBase.getProtocol()) + "://" + this.codeBase.getHost() + "/SERVICE/EVENT?STOCK=" + this.symbol);
                this.dataController = new AverageOrderComboChartDataDecoder(this.streamer, PChartPanel.SHARES);
                this.showMessage("Starting chart ...");
            }
            // monitorexit(this.socketLock)
            synchronized (this.chartLock) {
                (this.chartProducer = new AverageOrderComboChartPImage(this.symbol)).setIslandChartMessage("http://www.island.com");
                this.chartProducer.setRollFactor(this.rollFactor);
                this.chartProducer.setChartWidth(this.width);
                this.chartProducer.setPriceChartHeight(this.priceHeight);
                this.chartProducer.setHistoryChartHeight(this.historyHeight);
                this.chartProducer.setMsgHeight(this.msgHeight);
                this.chartProducer.setIslandChartMessageOn(true);
                this.chartProducer.setHistory(this.history);
                this.chartProducer.setMilliSecondsInHistory(this.milliSecondsInHistory);
                final Image tempImage = this.createImage(this.width, this.height);
                this.chartProducer.setImage(tempImage);
                this.data = null;
                this.dataList.clear();
                this.isPaused = false;
            }
            // monitorexit(this.chartLock)
            this.update();
        }
        catch (Exception e) {
            this.showMessage("Fail to obtain stream for " + this.symbol);
        }
    }
    
    public boolean zoomIn() {
        if (this.currentZoom > 0) {
            --this.currentZoom;
            this.milliSecondsInHistory = this.secondsInHistory[this.currentZoom] * 1000;
            if (this.chartProducer != null) {
                this.chartProducer.setMilliSecondsInHistory(this.milliSecondsInHistory);
            }
            this.update();
            if (this.currentZoom == 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean zoomOut() {
        if (this.currentZoom < this.secondsInHistory.length - 1) {
            ++this.currentZoom;
            this.milliSecondsInHistory = this.secondsInHistory[this.currentZoom] * 1000;
            if (this.chartProducer != null) {
                this.chartProducer.setMilliSecondsInHistory(this.milliSecondsInHistory);
            }
            this.update();
            if (this.currentZoom == this.secondsInHistory.length - 1) {
                return false;
            }
        }
        return true;
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
    
    public int getCurrentTimeUnit() {
        return this.secondsInHistory[this.currentZoom];
    }
    
    public void setCodeBase(final URL codeBase) {
        this.codeBase = codeBase;
    }
    
    public void setSymbol(final String symbol) {
        this.oldSymbol = this.symbol;
        this.symbol = symbol.toUpperCase();
    }
    
    public void setDeltaPrice(final float deltaPrice) {
        this.oldDeltaPrice = this.deltaPrice;
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
    
    public void setTimeLimit(final int timeLimit) {
        if (timeLimit > 0) {
            this.timeLimit = timeLimit * 1000;
            this.dataList.setTimeLimit(timeLimit * 1000);
        }
    }
}
