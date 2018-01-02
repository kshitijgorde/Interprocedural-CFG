// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop.combo;

import com.island.servers.ds.chart.stockorder.OrderChartData;
import java.awt.FontMetrics;
import com.island.servers.ds.chart.share.TimeUtil;
import com.island.servers.ds.chart.share.FontUtil;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import com.island.clients.ds.aop.AverageOrderAppletData;
import java.awt.Image;
import com.island.servers.ds.chart.stockorder.averageorderchart.AverageOrderChartData;
import com.island.clients.ds.aop.AverageOrderChartImage;
import com.island.servers.ds.chart.stockorder.OrderChartProducer;

public abstract class AverageOrderComboChartImageProducer extends OrderChartProducer
{
    protected int chartWidth;
    protected int chartHeight;
    protected int priceChartHeight;
    protected int historyChartHeight;
    protected int msgHeight;
    protected AverageOrderChartImage orderChartProducer;
    protected AverageOrderHistoryChartImage historyChartProducer;
    protected AverageOrderChartData[] historyData;
    protected int size;
    protected int history;
    protected int milliSecondsInHistory;
    protected boolean islandChartMessageOn;
    protected String islandChartMessage;
    protected float bottomLabelHeightPercent;
    protected boolean historyChartOn;
    protected float rollFactor;
    
    public AverageOrderComboChartImageProducer(final String symbol) {
        super(symbol);
        this.chartWidth = 500;
        this.chartHeight = 660;
        this.priceChartHeight = 400;
        this.historyChartHeight = 100;
        this.msgHeight = 20;
        this.size = 100000;
        this.history = 5;
        this.milliSecondsInHistory = 10000;
        this.islandChartMessageOn = true;
        this.islandChartMessage = "http://data.island.com";
        this.bottomLabelHeightPercent = 0.1f;
        this.historyChartOn = true;
        this.rollFactor = 0.4f;
        (this.orderChartProducer = new AverageOrderChartImage(symbol)).setIslandChartMessageOn(false);
    }
    
    public Image createChart() {
        this.chartHeight = this.priceChartHeight + this.historyChartHeight + this.msgHeight;
        this.image = this.getImageTemplate(this.chartWidth, this.chartHeight);
        final Graphics g2D = this.image.getGraphics();
        g2D.setColor(this.backgroundColor);
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        if (this.orderChartData != null) {
            this.orderChartProducer.drawChart(g2D, 0);
            if (this.historyChartOn) {
                (this.historyChartProducer = new AverageOrderHistoryChartImage(this.symbol)).setChartWidth(this.chartWidth);
                this.historyChartProducer.setOrderChartData(this.historyData);
                this.historyChartProducer.setSize(this.size);
                this.historyChartProducer.setHistory(this.history);
                this.historyChartProducer.setChartHeight(this.historyChartHeight);
                this.historyChartProducer.setVolumeScaleInterval(this.orderChartProducer.getVolumeScaleInterval());
                this.historyChartProducer.setVolumeScaleMax(this.orderChartProducer.getVolumeScaleMax());
                this.historyChartProducer.setIslandChartMessageOn(false);
                this.historyChartProducer.setMilliSecondsInHistory(this.milliSecondsInHistory);
                this.historyChartProducer.setMaxPrice(this.orderChartProducer.getMaxPrice());
                this.historyChartProducer.setMinPrice(this.orderChartProducer.getMinPrice());
                this.historyChartProducer.drawChart(g2D, this.priceChartHeight);
            }
            this.drawBottomLabel(g2D, ((AverageOrderAppletData)this.orderChartData).getUpdateTime());
        }
        else {
            g2D.setColor(Color.black);
            g2D.setFont(new Font(this.fontName, this.fontStyle, 16));
            g2D.getFontMetrics();
        }
        return this.image;
    }
    
    protected void drawBottomLabel(final Graphics g2D, final long time) {
        if (this.islandChartMessageOn) {
            final String string1 = "As of : xx:xx:xx.xxx";
            final int cw = this.chartWidth / (string1.length() + this.islandChartMessage.length());
            final int ch = this.msgHeight;
            final int newFontSize = FontUtil.getFontSize(cw, ch, g2D, "s");
            final Font font = g2D.getFont();
            g2D.setFont(new Font(this.fontName, this.fontStyle, newFontSize));
            final FontMetrics fontMetrics = g2D.getFontMetrics();
            g2D.setColor(this.labelColor);
            final StringBuffer timeLabel = new StringBuffer().append("As of : ");
            if (time > 0L) {
                timeLabel.append(TimeUtil.getTimeString(time));
            }
            final int bottomLabelBase = this.chartHeight - fontMetrics.getDescent();
            g2D.drawString(timeLabel.toString(), 0, bottomLabelBase);
            g2D.drawString(this.islandChartMessage, this.chartWidth - fontMetrics.stringWidth(this.islandChartMessage), bottomLabelBase);
            g2D.setFont(font);
        }
    }
    
    public void setOrderDataHistory(final OrderChartData orderChartData) {
    }
    
    public void setOrderChartData(final OrderChartData orderChartData) {
        this.orderChartData = orderChartData;
        this.orderChartProducer.setOrderChartData(orderChartData, 0);
        this.orderChartProducer.setOrderDataHistory(orderChartData);
    }
    
    public void setChartWidth(final int chartWidth) {
        this.chartWidth = chartWidth;
        this.orderChartProducer.setChartWidth(chartWidth);
    }
    
    public void setPriceChartHeight(final int priceChartHeight) {
        this.priceChartHeight = priceChartHeight;
        this.orderChartProducer.setChartHeight(priceChartHeight);
    }
    
    public void setHistoryChartHeight(final int historyChartHeight) {
        this.historyChartHeight = historyChartHeight;
    }
    
    public void setMsgHeight(final int msgHeight) {
        this.msgHeight = msgHeight;
    }
    
    public void setSize(final int size) {
        this.size = size;
        this.orderChartProducer.setSize(size);
    }
    
    public void setHistory(final int history) {
        this.history = history;
    }
    
    public void setMilliSecondsInHistory(final int milliSecondsInHistory) {
        this.milliSecondsInHistory = milliSecondsInHistory;
    }
    
    public void setHistoryData(final AverageOrderChartData[] historyData) {
        this.historyData = historyData;
    }
    
    public void setHistoryChartOn(final boolean historyChartOn) {
        this.historyChartOn = historyChartOn;
    }
    
    public void setRollFactor(final float rollFactor) {
        this.rollFactor = rollFactor;
    }
}
