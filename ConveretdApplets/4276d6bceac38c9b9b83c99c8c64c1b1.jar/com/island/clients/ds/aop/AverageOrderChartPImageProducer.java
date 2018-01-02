// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.FontMetrics;
import com.island.servers.ds.chart.share.NumberUtil;
import java.awt.Graphics;
import com.island.servers.ds.chart.stockorder.OrderChartData;
import com.island.servers.ds.chart.stockorder.averageorderchart.AverageOrderChartProducer;

public abstract class AverageOrderChartPImageProducer extends AverageOrderChartProducer
{
    protected OrderChartData oldOrderChartData;
    protected int[] currentAsksY;
    protected int[] currentBidsY;
    protected float deltaPrice;
    protected float priceBuffer;
    protected int maxStep;
    protected int step;
    protected int gray;
    
    public AverageOrderChartPImageProducer(final String symbol) {
        super(symbol);
        this.deltaPrice = 0.1f;
        this.priceBuffer = 0.05f;
        this.maxStep = 6;
        this.step = 1;
        this.gray = 100;
    }
    
    protected void drawTitle(final Graphics g2D) {
        if (this.orderChartData == null) {
            return;
        }
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        final String message = String.valueOf(this.symbol) + "  Shares Matched : " + NumberUtil.groupDigits(this.orderChartData.getMatchedShares());
        g2D.setColor(this.labelColor);
        g2D.drawString(message, (this.chartWidth - fontMetrics.stringWidth(message)) / 2, this.topLabel);
    }
    
    public void setOrderDataHistory(final OrderChartData chartData) {
        if (chartData == null) {
            return;
        }
        final AverageOrderAppletData orderChartData = (AverageOrderAppletData)chartData;
        float lastPrice = chartData.getLastPrice();
        if (lastPrice <= 0.0f) {
            if (chartData.getAsks() != null && chartData.getAsks().length > 0 && chartData.getBids() != null && chartData.getBids().length > 0) {
                lastPrice = (chartData.getAsks()[0].getPrice() + chartData.getBids()[0].getPrice()) / this.DECIMAL / 2.0f;
            }
            else if (chartData.getAsks() != null && chartData.getAsks().length > 0) {
                lastPrice = chartData.getAsks()[0].getPrice() / this.DECIMAL;
            }
            else if (chartData.getBids() != null && chartData.getBids().length > 0) {
                lastPrice = chartData.getBids()[0].getPrice() / this.DECIMAL;
            }
        }
        if (lastPrice >= 0.0f) {
            if (this.minPrice > lastPrice - this.deltaPrice || this.minPrice < 0.0f) {
                this.minPrice = lastPrice - this.deltaPrice - this.deltaPrice * 2.0f * this.priceBuffer;
                if (this.minPrice < 0.0f) {
                    this.minPrice = 0.0f;
                }
            }
            if (this.maxPrice < lastPrice + this.deltaPrice) {
                this.maxPrice = lastPrice + this.deltaPrice + this.deltaPrice * 2.0f * this.priceBuffer;
            }
        }
        int oldAskV = -1;
        int oldBidV = -1;
        int askV = orderChartData.getAskVolume(this.maxPrice);
        int bidV = orderChartData.getBidVolume(this.minPrice);
        if (this.oldOrderChartData != null) {
            oldAskV = ((AverageOrderAppletData)this.oldOrderChartData).getAskVolume(this.maxPrice);
            if (oldAskV > askV) {
                askV = oldAskV;
            }
            oldBidV = ((AverageOrderAppletData)this.oldOrderChartData).getBidVolume(this.minPrice);
            if (oldBidV > bidV) {
                bidV = oldBidV;
            }
        }
        final int volumeHigh = (askV > bidV) ? askV : bidV;
        if (volumeHigh >= this.volumeScaleMax || volumeHigh <= this.volumeScaleMax / 2) {
            this.volumeScaleInterval = this.getVolumeIntval(volumeHigh);
            this.volumeScaleMax = this.nextVolumeHigh(volumeHigh, this.volumeScaleInterval);
        }
    }
    
    public void setOrderChartData(final OrderChartData orderChartData) {
        synchronized (this) {
            this.step = 0;
            this.oldOrderChartData = this.orderChartData;
            this.orderChartData = orderChartData;
            if (orderChartData != null) {
                this.setOrderDataHistory(orderChartData);
                this.setAverageOrderChartParam();
                this.calculateData(orderChartData);
                this.distributeData(this.bidsY, this.asksY);
                this.currentBidsY = this.bidsY;
                this.currentAsksY = this.asksY;
                if (this.oldOrderChartData != null) {
                    this.calculateData(this.oldOrderChartData);
                    this.distributeData(this.bidsY, this.asksY);
                }
            }
        }
    }
    
    public void setOrderChartData(final OrderChartData orderChartData, final int top) {
        synchronized (this) {
            this.step = 0;
            this.oldOrderChartData = this.orderChartData;
            this.orderChartData = orderChartData;
            if (orderChartData != null) {
                this.setOrderDataHistory(orderChartData);
                this.setAverageOrderChartParam(top);
                this.calculateData(orderChartData);
                this.distributeData(this.bidsY, this.asksY);
                this.currentBidsY = this.bidsY;
                this.currentAsksY = this.asksY;
                if (this.oldOrderChartData != null) {
                    this.calculateData(this.oldOrderChartData);
                    this.distributeData(this.bidsY, this.asksY);
                }
            }
        }
    }
    
    public Image createChart() {
        this.image = this.getImageTemplate(this.chartWidth, this.chartHeight);
        if (this.orderChartData != null && (this.orderChartData.getAsks() != null || this.orderChartData.getBids() != null)) {
            return this.drawChart();
        }
        final String message = "No visible order in the book.";
        final Graphics g2D = this.image.getGraphics();
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        g2D.setColor(Color.black);
        g2D.setFont(new Font(this.fontName, this.fontStyle, 16));
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        g2D.drawString(message, (this.chartWidth - fontMetrics.stringWidth(message)) / 2, this.chartHeight / 3);
        g2D.dispose();
        return this.image;
    }
    
    protected void initGraphData() {
        this.bidsY = new int[this.graphWidth + 1];
        for (int i = 0; i <= this.graphWidth; ++i) {
            this.bidsY[i] = this.graphHeight + 1;
        }
        this.asksY = new int[this.graphWidth + 1];
        for (int i = 0; i <= this.graphWidth; ++i) {
            this.asksY[i] = this.graphHeight + 1;
        }
    }
    
    public Image drawChart() {
        final Graphics g2D = this.image.getGraphics();
        g2D.setColor(this.backgroundColor);
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        if (this.minPrice >= 0.0f && this.maxPrice >= 0.0f) {
            synchronized (this) {
                ++this.step;
            }
            this.drawGrid(g2D);
            if (this.oldOrderChartData != null && this.step <= this.maxStep) {
                this.drawDataAlpha(g2D);
            }
            else {
                this.drawData(g2D);
            }
            this.drawTicker(g2D);
        }
        g2D.dispose();
        return this.image;
    }
    
    public void drawChart(final Graphics g2D) {
        g2D.setColor(this.backgroundColor);
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        if (this.minPrice >= 0.0f && this.maxPrice >= 0.0f) {
            synchronized (this) {
                ++this.step;
            }
            this.drawGrid(g2D);
            if (this.oldOrderChartData != null && this.step <= this.maxStep) {
                this.drawDataAlpha(g2D);
            }
            else {
                this.drawData(g2D);
            }
            this.drawTicker(g2D);
        }
    }
    
    public void drawData(final Graphics g2D) {
        if (this.minPrice < 0.0f || this.maxPrice < 0.0f) {
            return;
        }
        final AverageOrderAppletData averageOrderChartData = (AverageOrderAppletData)this.orderChartData;
        final int volumeScaleIntvalCount = this.volumeScaleMax / this.volumeScaleInterval;
        final float priceDiff = this.maxPrice - this.minPrice;
        final int floorStep = this.volumeScaleInterval * this.graphHeight / this.volumeScaleMax;
        final int colorStep = this.colorSpace / (volumeScaleIntvalCount + 1);
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        final float lastPrice = averageOrderChartData.getLastPrice();
        if (lastPrice > 0.0f && lastPrice >= this.minPrice && lastPrice <= this.maxPrice) {
            g2D.setColor(new Color(0, 0, 255));
            final int lastPriceX = this.graphLeft + (int)((lastPrice - this.minPrice) / priceDiff * this.graphWidth);
            final String lastPriceLabel = "last price " + NumberUtil.formatFraction(lastPrice / 10.0f, this.maxFractionDigit);
            final int lastPriceLabelLen = fontMetrics.stringWidth(lastPriceLabel);
            if (this.graphRight - lastPriceX > lastPriceLabelLen / 2 && lastPriceX - this.graphLeft > lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, lastPriceX - lastPriceLabelLen / 2, this.graphTop - this.yLabelSpace);
            }
            else if (lastPriceX - this.graphLeft <= lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, this.graphLeft, this.graphTop - this.yLabelSpace);
            }
            else {
                g2D.drawString(lastPriceLabel, this.graphRight - lastPriceLabelLen, this.graphTop - this.yLabelSpace);
            }
            g2D.setColor(new Color(0, 0, 255));
            g2D.drawLine(lastPriceX, this.graphTop, lastPriceX, this.graphBottom - 1);
        }
        if (this.currentBidsY != null && this.currentBidsY.length > 0) {
            int x;
            for (x = this.graphWidth; x >= 0 && this.currentBidsY[x] < 0; --x) {}
            if (x >= 0) {
                int currentX = x;
                int lastY = this.graphHeight - this.currentBidsY[currentX];
                while (x >= 0) {
                    if (this.currentBidsY[x] <= this.graphHeight) {
                        final int color = (lastY / floorStep + 1) * colorStep;
                        g2D.setColor(new Color(0, 255 - color, 0));
                        g2D.fillRect(x + this.graphLeft, this.graphBottom - lastY, currentX - x, lastY);
                        lastY = this.graphHeight - this.currentBidsY[x];
                        currentX = x;
                    }
                    --x;
                }
                g2D.drawLine(currentX + this.graphLeft, this.graphBottom - lastY, currentX + this.graphLeft, this.graphBottom - 1);
            }
        }
        if (this.currentAsksY != null && this.currentAsksY.length > 0) {
            int x;
            for (x = 0; x <= this.graphWidth && this.currentAsksY[x] < 0; ++x) {}
            if (x <= this.graphWidth) {
                int currentX = x;
                int lastY = this.graphHeight - this.currentAsksY[currentX];
                while (x <= this.graphWidth) {
                    if (this.currentAsksY[x] <= this.graphHeight) {
                        final int color = (lastY / floorStep + 1) * colorStep;
                        g2D.setColor(new Color(255 - color, 0, 0));
                        g2D.fillRect(currentX + this.graphLeft, this.graphBottom - lastY, x - currentX, lastY);
                        lastY = this.graphHeight - this.currentAsksY[x];
                        currentX = x;
                    }
                    ++x;
                }
                g2D.drawLine(currentX + this.graphLeft, this.graphBottom - lastY, currentX + this.graphLeft, this.graphBottom - 1);
            }
        }
        this.labelAveragePrice(g2D, this.askPriceLabels, this.bidPriceLabels);
        this.markPrice(g2D, this.minPrice, this.maxPrice);
    }
    
    public void drawDataAlpha(final Graphics g2D) {
        if (this.minPrice < 0.0f || this.maxPrice < 0.0f) {
            return;
        }
        final int volumeScaleIntvalCount = this.volumeScaleMax / this.volumeScaleInterval;
        final float priceDiff = this.maxPrice - this.minPrice;
        final int floorStep = this.volumeScaleInterval * this.graphHeight / this.volumeScaleMax;
        final int colorStep = this.colorSpace / (volumeScaleIntvalCount + 1);
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        int alpha = 255;
        if (this.step <= this.maxStep) {
            alpha = this.step * (255 / this.maxStep);
        }
        for (int x = 0; x <= this.graphWidth; ++x) {
            if (this.currentBidsY[x] < this.graphHeight && this.bidsY[x] < this.graphHeight) {
                final int y = this.graphHeight - this.currentBidsY[x];
                final int oldY = this.graphHeight - this.bidsY[x];
                final Color color = new Color(0, 255 - (y / floorStep + 1) * colorStep, 0);
                final Color oldColor = new Color(0, 255 - (oldY / floorStep + 1) * colorStep, 0);
                if (y > oldY) {
                    g2D.setColor(this.getColor(color, this.backgroundColor, alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - oldY);
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else if (y == oldY) {
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else {
                    final Color c = this.getColor(oldColor, this.backgroundColor, 255 - alpha);
                    if (!c.equals(this.backgroundColor)) {
                        g2D.setColor(c);
                        g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - y);
                    }
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - 1);
                }
            }
            else if (this.currentBidsY[x] < this.graphHeight && this.asksY[x] < this.graphHeight) {
                final int y = this.graphHeight - this.currentBidsY[x];
                final int oldY = this.graphHeight - this.asksY[x];
                final Color color = new Color(0, 255 - (y / floorStep + 1) * colorStep, 0);
                final Color oldColor = new Color(255 - (oldY / floorStep + 1) * colorStep, 0, 0);
                if (y > oldY) {
                    g2D.setColor(this.getColor(color, this.backgroundColor, alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - oldY);
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else if (y == oldY) {
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else {
                    final Color c = this.getColor(oldColor, this.backgroundColor, 255 - alpha);
                    if (!c.equals(this.backgroundColor)) {
                        g2D.setColor(c);
                        g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - y);
                    }
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - 1);
                }
            }
            else if (this.currentBidsY[x] < this.graphHeight) {
                final int y = this.graphHeight - this.currentBidsY[x];
                final Color color2 = new Color(0, 255 - (y / floorStep + 1) * colorStep, 0);
                g2D.setColor(this.getColor(color2, this.backgroundColor, alpha));
                g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - 1);
            }
            else if (this.currentAsksY[x] < this.graphHeight && this.bidsY[x] < this.graphHeight) {
                final int y = this.graphHeight - this.currentAsksY[x];
                final int oldY = this.graphHeight - this.bidsY[x];
                final Color color = new Color(255 - (y / floorStep + 1) * colorStep, 0, 0);
                final Color oldColor = new Color(0, 255 - (oldY / floorStep + 1) * colorStep, 0);
                if (y > oldY) {
                    g2D.setColor(this.getColor(color, this.backgroundColor, alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - oldY);
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else if (y == oldY) {
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else {
                    final Color c = this.getColor(oldColor, this.backgroundColor, 255 - alpha);
                    if (!c.equals(this.backgroundColor)) {
                        g2D.setColor(c);
                        g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - y);
                    }
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - 1);
                }
            }
            else if (this.currentAsksY[x] < this.graphHeight && this.asksY[x] < this.graphHeight) {
                final int y = this.graphHeight - this.currentAsksY[x];
                final int oldY = this.graphHeight - this.asksY[x];
                final Color color = new Color(255 - (y / floorStep + 1) * colorStep, 0, 0);
                final Color oldColor = new Color(255 - (oldY / floorStep + 1) * colorStep, 0, 0);
                if (y > oldY) {
                    g2D.setColor(this.getColor(color, this.backgroundColor, alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - oldY);
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else if (y == oldY) {
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - 1);
                }
                else {
                    final Color c = this.getColor(oldColor, this.backgroundColor, 255 - alpha);
                    if (!c.equals(this.backgroundColor)) {
                        g2D.setColor(c);
                        g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY, this.graphLeft + x, this.graphBottom - y);
                    }
                    g2D.setColor(this.getColor(color, this.getColor(oldColor, this.backgroundColor, 255 - alpha), alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - 1);
                }
            }
            else if (this.currentAsksY[x] < this.graphHeight) {
                final int y = this.graphHeight - this.currentAsksY[x];
                final Color color2 = new Color(255 - (y / floorStep + 1) * colorStep, 0, 0);
                g2D.setColor(this.getColor(color2, this.backgroundColor, alpha));
                g2D.drawLine(this.graphLeft + x, this.graphBottom - y, this.graphLeft + x, this.graphBottom - 1);
            }
            else if (this.bidsY[x] < this.graphHeight) {
                final int oldY2 = this.graphHeight - this.bidsY[x];
                final Color oldColor2 = new Color(0, 255 - (oldY2 / floorStep + 1) * colorStep, 0);
                if (!oldColor2.equals(this.backgroundColor)) {
                    g2D.setColor(this.getColor(oldColor2, this.backgroundColor, 255 - alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY2, this.graphLeft + x, this.graphBottom - 1);
                }
            }
            else if (this.asksY[x] < this.graphHeight) {
                final int oldY2 = this.graphHeight - this.asksY[x];
                final Color oldColor2 = new Color(255 - (oldY2 / floorStep + 1) * colorStep, 0, 0);
                if (!oldColor2.equals(this.backgroundColor)) {
                    g2D.setColor(this.getColor(oldColor2, this.backgroundColor, 255 - alpha));
                    g2D.drawLine(this.graphLeft + x, this.graphBottom - oldY2, this.graphLeft + x, this.graphBottom - 1);
                }
            }
        }
        this.drawLastPrice(g2D, alpha);
        this.labelAveragePrice(g2D, this.askPriceLabels, this.bidPriceLabels);
        this.markPrice(g2D, this.minPrice, this.maxPrice);
    }
    
    protected void drawLastPrice(final Graphics g2D, final int alpha) {
        final AverageOrderAppletData averageOrderChartData = (AverageOrderAppletData)this.orderChartData;
        final AverageOrderAppletData averageOldOrderChartData = (AverageOrderAppletData)this.oldOrderChartData;
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        final float priceDiff = this.maxPrice - this.minPrice;
        final float lastPrice = averageOrderChartData.getLastPrice();
        final float oldLastPrice = averageOldOrderChartData.getLastPrice();
        if (lastPrice > 0.0f && lastPrice >= this.minPrice && lastPrice <= this.maxPrice) {
            final int lastPriceX = this.graphLeft + (int)((lastPrice - this.minPrice) / priceDiff * this.graphWidth);
            final String lastPriceLabel = "last price " + NumberUtil.formatFraction(lastPrice / 10.0f, this.maxFractionDigit);
            final int lastPriceLabelLen = fontMetrics.stringWidth(lastPriceLabel);
            g2D.setColor(this.lastPriceColor);
            if (this.graphRight - lastPriceX > lastPriceLabelLen / 2 && lastPriceX - this.graphLeft > lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, lastPriceX - lastPriceLabelLen / 2, this.graphTop - this.yLabelSpace);
            }
            else if (lastPriceX - this.graphLeft < lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, this.graphLeft, this.graphTop - this.yLabelSpace);
            }
            else {
                g2D.drawString(lastPriceLabel, this.graphRight - lastPriceLabelLen, this.graphTop - this.yLabelSpace);
            }
            if (lastPrice == oldLastPrice) {
                g2D.setColor(this.lastPriceColor);
                g2D.drawLine(lastPriceX, this.graphTop, lastPriceX, this.graphBottom - 1);
                return;
            }
            g2D.setColor(this.getColor(this.lastPriceColor, this.backgroundColor, alpha));
            g2D.drawLine(lastPriceX, this.graphTop, lastPriceX, this.graphBottom - 1);
        }
        if (lastPrice > 0.0f && lastPrice >= this.minPrice && lastPrice <= this.maxPrice) {
            final int lastPriceX = this.graphLeft + (int)((lastPrice - this.minPrice) / priceDiff * this.graphWidth);
            final String lastPriceLabel = "last price " + NumberUtil.formatFraction(lastPrice / 10.0f, this.maxFractionDigit);
            final int lastPriceLabelLen = fontMetrics.stringWidth(lastPriceLabel);
            g2D.setColor(this.lastPriceColor);
            if (this.graphRight - lastPriceX > lastPriceLabelLen / 2 && lastPriceX - this.graphLeft > lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, lastPriceX - lastPriceLabelLen / 2, this.graphTop - this.yLabelSpace);
            }
            else if (lastPriceX - this.graphLeft < lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, this.graphLeft, this.graphTop - this.yLabelSpace);
            }
            else {
                g2D.drawString(lastPriceLabel, this.graphRight - lastPriceLabelLen, this.graphTop - this.yLabelSpace);
            }
            g2D.setColor(this.getColor(this.lastPriceColor, this.backgroundColor, alpha));
            g2D.drawLine(lastPriceX, this.graphTop, lastPriceX, this.graphBottom - 1);
        }
    }
    
    protected void distributeData(final int[] bidsY, final int[] asksY) {
        if (bidsY != null && bidsY.length > 0) {
            int lastData = -1;
            for (int i = this.graphWidth; i >= 0; --i) {
                if (bidsY[i] < this.graphHeight) {
                    if (lastData > 0) {
                        for (int j = lastData - 1; j > i; --j) {
                            bidsY[j] = bidsY[lastData];
                        }
                    }
                    lastData = i;
                }
            }
        }
        if (asksY != null && asksY.length > 0) {
            int lastData = -1;
            for (int i = 0; i <= this.graphWidth; ++i) {
                if (asksY[i] < this.graphHeight) {
                    if (lastData > 0) {
                        for (int j = lastData + 1; j < i; ++j) {
                            asksY[j] = asksY[lastData];
                        }
                    }
                    lastData = i;
                }
            }
        }
    }
    
    protected Color getColor(final Color src, final Color dest, final int alpha) {
        final int r1 = src.getRed();
        final int g1 = src.getGreen();
        final int b1 = src.getBlue();
        final int r2 = dest.getRed();
        final int g2 = dest.getGreen();
        final int b2 = dest.getBlue();
        final int alphaD = 255 - alpha;
        final int r3 = r1 * alpha / 255 + r2 * alphaD / 255;
        final int g3 = g1 * alpha / 255 + g2 * alphaD / 255;
        final int b3 = b1 * alpha / 255 + b2 * alphaD / 255;
        return new Color(r3, g3, b3);
    }
    
    public void resetPriceRange() {
        final float n = -1.0f;
        this.maxPrice = n;
        this.minPrice = n;
        this.setOrderDataHistory(this.orderChartData);
    }
    
    public void setDeltaPrice(final float deltaPrice) {
        this.deltaPrice = deltaPrice;
    }
    
    public void setMaxPrice(final float maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    public void setMinPrice(final float minPrice) {
        this.minPrice = minPrice;
    }
    
    public void setMaxStep(final int maxStep) {
        this.maxStep = maxStep;
    }
    
    public synchronized void resetStep() {
        this.step = 0;
    }
    
    public synchronized int getStep() {
        return this.step++;
    }
}
