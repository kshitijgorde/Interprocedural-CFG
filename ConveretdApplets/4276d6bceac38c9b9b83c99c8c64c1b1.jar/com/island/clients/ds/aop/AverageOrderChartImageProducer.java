// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import com.island.servers.ds.chart.share.FontUtil;
import java.awt.Color;
import java.awt.Image;
import com.island.servers.ds.chart.stockorder.OrderChartData;
import java.awt.FontMetrics;
import java.awt.Font;
import com.island.servers.ds.chart.share.NumberUtil;
import java.awt.Graphics;
import com.island.servers.ds.chart.stockorder.averageorderchart.AverageOrderChartProducer;

public abstract class AverageOrderChartImageProducer extends AverageOrderChartProducer
{
    protected int[] currentAsksY;
    protected int[] currentBidsY;
    protected float lastMinPrice;
    protected float lastMaxPrice;
    protected float currentMinPrice;
    protected float currentMaxPrice;
    protected float rollFactor;
    
    public AverageOrderChartImageProducer(final String symbol) {
        super(symbol);
        this.lastMinPrice = -1.0f;
        this.lastMaxPrice = -1.0f;
        this.currentMinPrice = -1.0f;
        this.currentMaxPrice = -1.0f;
        this.rollFactor = 0.4f;
    }
    
    protected void drawTitle(final Graphics g2D) {
        if (this.orderChartData == null) {
            return;
        }
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        final String message = String.valueOf(this.symbol) + "  Shares Matched : " + NumberUtil.groupDigits(this.orderChartData.getMatchedShares());
        g2D.setColor(this.labelColor);
        g2D.setFont(new Font("Default", 0, 10));
        g2D.drawString(message, (this.chartWidth - fontMetrics.stringWidth(message)) / 2, this.topLabel);
    }
    
    public void setOrderChartData(final OrderChartData orderChartData) {
        this.orderChartData = orderChartData;
        if (orderChartData != null) {
            this.setOrderDataHistory(orderChartData);
            this.setAverageOrderChartParam();
            this.calculateData(orderChartData);
            if (this.currentMaxPrice < 0.0f || this.currentMinPrice < 0.0f) {
                this.currentMaxPrice = this.maxPrice;
                this.currentMinPrice = this.minPrice;
            }
        }
    }
    
    public void setOrderChartData(final OrderChartData orderChartData, final int top) {
        this.orderChartData = orderChartData;
        if (orderChartData != null) {
            this.setOrderDataHistory(orderChartData);
            this.setAverageOrderChartParam(top);
            this.calculateData(orderChartData);
            if (this.currentMaxPrice < 0.0f || this.currentMinPrice < 0.0f) {
                this.currentMaxPrice = this.maxPrice;
                this.currentMinPrice = this.minPrice;
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
        g2D.setFont(new Font(this.fontName, this.fontStyle, 100));
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        g2D.drawString(message, (this.chartWidth - fontMetrics.stringWidth(message)) / 2, this.chartHeight / 3);
        g2D.dispose();
        return this.image;
    }
    
    public Image createChart(final Graphics g2D, final int top) {
        if (this.orderChartData != null) {
            return this.drawChart();
        }
        final String message = "No order in the book.";
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        g2D.setColor(Color.black);
        g2D.setFont(new Font(this.fontName, this.fontStyle, 100));
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        g2D.drawString(message, (this.chartWidth - fontMetrics.stringWidth(message)) / 2, this.chartHeight / 3);
        g2D.dispose();
        return this.image;
    }
    
    public Image drawChart() {
        final Graphics g2D = this.image.getGraphics();
        g2D.setColor(this.backgroundColor);
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        if (this.minPrice > 0.0f && this.maxPrice > 0.0f) {
            this.drawGrid(g2D);
            this.setIntermediateChartData();
            this.drawData(g2D);
            this.drawTicker(g2D);
        }
        g2D.dispose();
        return this.image;
    }
    
    public void drawChart(final Graphics g2D, final int top) {
        g2D.setColor(this.backgroundColor);
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        if (this.minPrice > 0.0f && this.maxPrice > 0.0f) {
            this.drawGrid(g2D);
            this.setIntermediateChartData();
            this.drawData(g2D);
            this.drawTicker(g2D);
        }
    }
    
    protected void setIntermediateChartData() {
        final int error = (int)(1.0f / this.rollFactor) + 1;
        if (this.currentBidsY == null) {
            this.currentBidsY = this.bidsY;
        }
        else {
            final int[] tempBidsY = new int[this.graphWidth + 1];
            for (int i = 0; i <= this.graphWidth; ++i) {
                tempBidsY[i] = -1;
            }
            int x = this.graphWidth;
            int currentX = this.graphWidth;
            int tempX = this.graphWidth;
            while (true) {
                if (currentX > 0) {
                    if (this.currentBidsY[currentX] < 0 || this.graphHeight - this.currentBidsY[currentX] < 0) {
                        --currentX;
                        continue;
                    }
                }
                while (x > 0 && this.bidsY[x] < 0) {
                    --x;
                }
                if (Math.abs(x - currentX) < error) {
                    tempX = x;
                }
                else {
                    tempX = (int)((x - currentX) * this.rollFactor) + currentX;
                }
                if (this.graphHeight - this.currentBidsY[currentX] == this.graphHeight - this.bidsY[x]) {
                    tempBidsY[tempX] = this.bidsY[x];
                    --currentX;
                    if (x > 0) {
                        --x;
                    }
                }
                else if (this.graphHeight - this.currentBidsY[currentX] > this.graphHeight - this.bidsY[x]) {
                    tempBidsY[tempX] = this.bidsY[x];
                    if (x > 0) {
                        --x;
                    }
                    else {
                        --currentX;
                    }
                }
                else {
                    tempBidsY[tempX] = this.currentBidsY[currentX];
                    --currentX;
                }
                if (currentX < 0) {
                    break;
                }
            }
            this.currentBidsY = tempBidsY;
        }
        if (this.currentAsksY == null) {
            this.currentAsksY = this.asksY;
        }
        else {
            final int[] tempAsksY = new int[this.graphWidth + 1];
            for (int i = 0; i <= this.graphWidth; ++i) {
                tempAsksY[i] = -1;
            }
            int x = 0;
            int currentX = 0;
            int tempX = 0;
            while (true) {
                if (currentX < this.graphWidth) {
                    if (this.currentAsksY[currentX] < 0 || this.graphHeight - this.currentAsksY[currentX] <= 0) {
                        ++currentX;
                        continue;
                    }
                }
                while (x < this.graphWidth && this.asksY[x] < 0) {
                    ++x;
                }
                if (Math.abs(x - currentX) < error) {
                    tempX = x;
                }
                else {
                    tempX = (int)((x - currentX) * this.rollFactor) + currentX;
                }
                if (this.graphHeight - this.currentAsksY[currentX] == this.graphHeight - this.asksY[x]) {
                    tempAsksY[tempX] = this.asksY[x];
                    ++currentX;
                    if (x < this.graphWidth) {
                        ++x;
                    }
                }
                else if (this.graphHeight - this.currentAsksY[currentX] > this.graphHeight - this.asksY[x]) {
                    tempAsksY[tempX] = this.asksY[x];
                    if (x < this.graphWidth) {
                        ++x;
                    }
                    else {
                        ++currentX;
                    }
                }
                else {
                    tempAsksY[tempX] = this.currentAsksY[currentX];
                    ++currentX;
                }
                if (currentX > this.graphWidth) {
                    break;
                }
            }
            this.currentAsksY = tempAsksY;
        }
        this.currentMaxPrice += (this.maxPrice - this.currentMaxPrice) * this.rollFactor;
        this.currentMinPrice += (this.minPrice - this.currentMinPrice) * this.rollFactor;
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
            g2D.setColor(this.lastPriceColor);
            final int lastPriceX = this.graphLeft + (int)((lastPrice - this.minPrice) / priceDiff * this.graphWidth);
            final String lastPriceLabel = "last price " + NumberUtil.formatFraction(lastPrice / 10.0f, this.maxFractionDigit);
            final int lastPriceLabelLen = fontMetrics.stringWidth(lastPriceLabel);
            g2D.drawLine(lastPriceX, this.graphTop, lastPriceX, this.graphBottom);
            if (this.graphRight - lastPriceX > lastPriceLabelLen / 2 && lastPriceX - this.graphLeft > lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, lastPriceX - lastPriceLabelLen / 2, this.graphTop - this.yLabelSpace);
            }
            else if (lastPriceX - this.graphLeft <= lastPriceLabelLen / 2) {
                g2D.drawString(lastPriceLabel, this.graphLeft, this.graphTop - this.yLabelSpace);
            }
            else {
                g2D.drawString(lastPriceLabel, this.graphRight - lastPriceLabelLen, this.graphTop - this.yLabelSpace);
            }
        }
        if (this.currentBidsY != null && this.currentBidsY.length > 0) {
            int x;
            for (x = this.graphWidth; this.currentBidsY[x] < 0 && x >= 0; --x) {}
            int currentX = x;
            int lastY = this.graphHeight - this.currentBidsY[currentX];
            while (x >= 0) {
                if (this.currentBidsY[x] >= 0) {
                    final int color = (lastY / floorStep + 1) * colorStep;
                    g2D.setColor(new Color(0, 255 - color, 0));
                    g2D.fillRect(x + this.graphLeft, this.graphBottom - lastY, currentX - x, lastY);
                    lastY = this.graphHeight - this.currentBidsY[x];
                    currentX = x;
                }
                --x;
            }
            if (currentX > 0) {
                g2D.fillRect(this.graphLeft, this.graphBottom - lastY, currentX, lastY);
            }
            else {
                g2D.drawLine(this.graphLeft, this.graphBottom - lastY, this.graphLeft, this.graphBottom - 1);
            }
        }
        if (this.currentAsksY != null && this.currentAsksY.length > 0) {
            int x;
            for (x = 0; this.currentAsksY[x] < 0 && x <= this.graphWidth; ++x) {}
            int currentX = x;
            int lastY = this.graphHeight - this.currentAsksY[currentX];
            while (x <= this.graphWidth) {
                if (this.currentAsksY[x] >= 0) {
                    final int color = (lastY / floorStep + 1) * colorStep;
                    g2D.setColor(new Color(255 - color, 0, 0));
                    g2D.fillRect(currentX + this.graphLeft, this.graphBottom - lastY, x - currentX, lastY);
                    lastY = this.graphHeight - this.currentAsksY[x];
                    currentX = x;
                }
                ++x;
            }
            if (currentX < this.graphWidth + 1) {
                g2D.fillRect(currentX + this.graphLeft, this.graphBottom - lastY, this.graphWidth + 1 - currentX, lastY);
            }
            else {
                g2D.drawLine(currentX + this.graphLeft, this.graphBottom - lastY, currentX + this.graphLeft, this.graphBottom - 1);
            }
        }
        this.labelAveragePrice(g2D, this.askPriceLabels, this.bidPriceLabels);
        this.markPrice(g2D, this.currentMinPrice, this.currentMaxPrice);
    }
    
    protected void markPrice(final Graphics g2D) {
        if (this.lastMaxPrice == -1.0f || this.lastMinPrice == -1.0f) {
            this.lastMaxPrice = this.maxPrice;
            this.lastMinPrice = this.minPrice;
        }
        if (Math.abs(this.maxPrice - this.lastMaxPrice) < 0.01f && Math.abs(this.minPrice - this.lastMinPrice) < 0.01f) {
            this.lastMaxPrice = this.maxPrice;
            this.lastMinPrice = this.minPrice;
        }
        g2D.setColor(this.backgroundColor);
        g2D.fillRect(0, this.graphBottom + 1, this.chartWidth, this.xAxis);
        final float priceDiff = this.lastMaxPrice - this.lastMinPrice;
        final float majorTicker = this.getMajorTicker(this.lastMinPrice, this.lastMaxPrice);
        final float minorTicker = majorTicker / 5.0f;
        float majorPrice;
        final float nextMajorPrice = majorPrice = this.getNextMajorTicker(this.lastMinPrice, majorTicker);
        float minorPrice = majorPrice + minorTicker;
        final int fontSize = FontUtil.getFontSize((int)(majorTicker / priceDiff * this.graphWidth / 7.0f), this.xAxis, g2D, "0");
        g2D.setFont(new Font(this.fontName, this.fontStyle, fontSize));
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        do {
            final int majorTickPos = this.graphLeft + (int)((majorPrice - this.lastMinPrice) / priceDiff * this.graphWidth);
            g2D.setColor(this.gridColor);
            g2D.drawLine(majorTickPos, this.graphBottom, majorTickPos, this.graphBottom + this.majorPriceTickerLength);
            minorPrice = majorPrice + minorTicker;
            for (int i = 1; i < 5 && minorPrice <= this.lastMaxPrice; minorPrice += minorTicker, ++i) {
                final int minorTickPos = this.graphLeft + (int)((minorPrice - this.lastMinPrice) / priceDiff * this.graphWidth);
                g2D.drawLine(minorTickPos, this.graphBottom, minorTickPos, this.graphBottom + this.minorPriceTickerLength);
            }
            g2D.setColor(this.labelColor);
            final String priceLabel = NumberUtil.formatFraction(majorPrice, this.maxFractionDigit);
            final int labelLen = fontMetrics.stringWidth(priceLabel);
            g2D.drawString(priceLabel, majorTickPos - labelLen / 2, this.graphBottom + this.majorPriceTickerLength + fontMetrics.getHeight());
            majorPrice += majorTicker;
        } while (majorPrice <= this.lastMaxPrice);
        minorPrice = nextMajorPrice - minorTicker;
        for (int j = 1; j < 5 && minorPrice >= this.lastMinPrice; minorPrice -= minorTicker, ++j) {
            final int minorTickPos2 = this.graphLeft + (int)((minorPrice - this.lastMinPrice) / priceDiff * this.graphWidth);
            g2D.drawLine(minorTickPos2, this.graphBottom, minorTickPos2, this.graphBottom + this.minorPriceTickerLength);
        }
        g2D.setColor(this.gridColor);
        minorPrice = nextMajorPrice - minorTicker;
        for (int j = 1; j < 5 && minorPrice >= this.lastMinPrice; minorPrice -= minorTicker, ++j) {
            final int minorTickPos2 = this.graphLeft + (int)((minorPrice - this.lastMinPrice) / priceDiff * this.graphWidth);
            g2D.drawLine(minorTickPos2, this.graphBottom, minorTickPos2, this.graphBottom + this.minorPriceTickerLength);
        }
        this.lastMinPrice += (this.minPrice - this.lastMinPrice) * this.rollFactor;
        this.lastMaxPrice += (this.maxPrice - this.lastMaxPrice) * this.rollFactor;
    }
    
    public Image adjustPriceAxis() {
        if (((AverageOrderAppletData)this.orderChartData).getDataType() == 0 && this.image != null && this.minPrice > 0.0f && this.maxPrice > 0.0f) {
            this.markPrice(this.image.getGraphics());
        }
        return this.image;
    }
    
    public void adjustPriceAxis(final Graphics g) {
        if (((AverageOrderAppletData)this.orderChartData).getDataType() == 0 && g != null && this.minPrice > 0.0f && this.maxPrice > 0.0f) {
            this.markPrice(g);
        }
    }
    
    public Image adjustChart() {
        if (((AverageOrderAppletData)this.orderChartData).getDataType() == 0 && this.image != null && this.minPrice > 0.0f && this.maxPrice > 0.0f) {
            this.markPrice(this.image.getGraphics());
        }
        return this.image;
    }
    
    public void setRollFactor(final float rollFactor) {
        this.rollFactor = rollFactor;
    }
}
