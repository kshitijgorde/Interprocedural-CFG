// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop.combo;

import com.island.servers.ds.chart.stockorder.OrderChartData;
import com.island.servers.ds.chart.stockorder.SimpleOrder;
import com.island.servers.ds.chart.stockorder.averageorderchart.AverageOrderPrice;
import com.island.clients.ds.aop.AverageOrderAppletData;
import java.util.Vector;
import com.island.servers.ds.chart.share.NumberUtil;
import com.island.servers.ds.chart.share.TimeUtil;
import com.island.servers.ds.chart.share.FontUtil;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import com.island.servers.ds.chart.stockorder.averageorderchart.AverageOrderChartData;
import com.island.servers.ds.chart.stockorder.OrderChartProducer;

public abstract class AverageOrderHistoryChartImageProducer extends OrderChartProducer
{
    AverageOrderChartData[] orderHistory;
    protected float lastPriceLabelPercent;
    protected float graphHeightPercent;
    protected float bottomLabelPercent;
    protected int history;
    protected int milliSecondsInHistory;
    protected int size;
    protected int orderStripWidth;
    protected int DECIMAL;
    
    public AverageOrderHistoryChartImageProducer(final String symbol) {
        super(symbol);
        this.lastPriceLabelPercent = 0.1f;
        this.graphHeightPercent = 0.75f;
        this.bottomLabelPercent = 0.1f;
        this.history = 5;
        this.milliSecondsInHistory = 10000;
        this.size = 100000;
        this.DECIMAL = 1000;
    }
    
    public Image createChart() {
        this.image = this.getImageTemplate(this.chartWidth, this.chartHeight);
        if (this.orderChartData != null) {
            final AverageOrderChartData[] data = ((AverageOrderHistoryAppletData)this.orderChartData).getOrderHistory();
            if (data != null && data.length > 0) {
                this.setOrderDataHistory(this.orderChartData);
                return this.drawChart();
            }
        }
        final String message = "No order history.";
        final Graphics g2D = this.image.getGraphics();
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        g2D.setColor(Color.black);
        g2D.setFont(new Font(this.fontName, this.fontStyle, 16));
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        g2D.drawString(message, (this.chartWidth - fontMetrics.stringWidth(message)) / 2, this.chartHeight / 3);
        g2D.dispose();
        return this.image;
    }
    
    public Image drawChart() {
        this.setAverageOrderHistoryChartParam();
        final Graphics g2D = this.image.getGraphics();
        g2D.fillRect(0, 0, this.chartWidth, this.chartHeight);
        if (this.minPrice > 0.0f && this.maxPrice > 0.0f) {
            this.drawEdge(g2D);
            this.drawData(g2D);
        }
        g2D.dispose();
        return this.image;
    }
    
    public void drawChart(final Graphics g2D, final int top) {
        this.setAverageOrderHistoryChartParam(top);
        if (this.minPrice > 0.0f && this.maxPrice > 0.0f) {
            this.drawEdge(g2D);
            this.drawData(g2D);
        }
    }
    
    protected void setAverageOrderHistoryChartParam() {
        this.setOrderChartParam();
        final int volumeIntvalCount = this.volumeScaleMax / this.volumeScaleInterval;
        this.lastPriceLabel = (int)(this.chartHeight * this.lastPriceLabelPercent);
        this.graphHeight = (int)(this.chartHeight * this.graphHeightPercent);
        this.bottomLabel = (int)(this.chartHeight * this.bottomLabelPercent);
        if (!this.islandChartMessageOn) {
            this.graphHeight += this.bottomLabel;
        }
        this.orderStripWidth = this.graphHeight / this.history;
        this.graphHeight = this.orderStripWidth * this.history;
        this.graphLeft = this.yAxisLabel + this.xLabelSpace + this.yAxis + this.xLabelSpace;
        this.graphRight = this.graphLeft + this.graphWidth;
        this.graphTop = this.yLabelSpace + this.lastPriceLabel + this.yLabelSpace;
        this.graphBottom = this.graphTop + this.graphHeight;
    }
    
    protected void setAverageOrderHistoryChartParam(final int top) {
        this.setOrderChartParam();
        final int volumeIntvalCount = this.volumeScaleMax / this.volumeScaleInterval;
        this.lastPriceLabel = (int)(this.chartHeight * this.lastPriceLabelPercent);
        this.graphHeight = (int)(this.chartHeight * this.graphHeightPercent);
        this.bottomLabel = (int)(this.chartHeight * this.bottomLabelPercent);
        if (!this.islandChartMessageOn) {
            this.graphHeight += this.bottomLabel;
        }
        this.orderStripWidth = this.graphHeight / this.history;
        this.graphHeight = this.orderStripWidth * this.history;
        this.graphLeft = this.yAxisLabel + this.xLabelSpace + this.yAxis + this.xLabelSpace;
        this.graphRight = this.graphLeft + this.graphWidth;
        this.graphTop = top + this.yLabelSpace + this.lastPriceLabel + this.yLabelSpace;
        this.graphBottom = this.graphTop + this.graphHeight;
    }
    
    protected void drawEdge(final Graphics g2D) {
        g2D.setColor(this.gridColor);
        g2D.drawRect(this.graphLeft, this.graphTop, this.graphWidth, this.graphHeight);
        if (this.islandChartMessageOn) {
            final String string1 = "As of : xx:xx:xx.xxx";
            final int cw = this.chartWidth / (string1.length() + this.islandChartMessage.length());
            final int ch = this.chartHeight - this.graphTop - this.graphHeight;
            final int newFontSize = FontUtil.getFontSize(cw, ch, g2D, "s");
            final Font font = g2D.getFont();
            g2D.setFont(new Font(this.fontName, this.fontStyle, newFontSize));
            final FontMetrics fontMetrics = g2D.getFontMetrics();
            g2D.setColor(this.labelColor);
            this.time = this.orderChartData.getTimeStamp();
            final StringBuffer timeLabel = new StringBuffer().append("As of : ");
            if (this.time > 0L) {
                timeLabel.append(TimeUtil.getTimeString(this.time));
            }
            final int bottomLabelBase = this.chartHeight - fontMetrics.getDescent();
            g2D.drawString(timeLabel.toString(), this.xLabelSpace, bottomLabelBase);
            g2D.drawString(this.islandChartMessage, this.chartWidth - this.xLabelSpace - fontMetrics.stringWidth(this.islandChartMessage), bottomLabelBase);
            g2D.setFont(font);
        }
    }
    
    protected void drawData(final Graphics g2D) {
        if (this.orderHistory == null || this.orderHistory.length < 1) {
            return;
        }
        final int fontSize = FontUtil.getFontSizeByHeight(this.topLabel + this.yLabelSpace + this.lastPriceLabel, g2D);
        g2D.setColor(this.labelColor);
        g2D.setFont(new Font(this.fontName, this.fontStyle, fontSize));
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        final String minPriceLabel = NumberUtil.formatFraction(this.minPrice, this.maxFractionDigit);
        final String maxPriceLabel = NumberUtil.formatFraction(this.maxPrice, this.maxFractionDigit);
        g2D.drawString(minPriceLabel, this.graphLeft, this.graphTop - this.yLabelSpace);
        g2D.drawString(maxPriceLabel, this.graphRight - fontMetrics.stringWidth(maxPriceLabel), this.graphTop - this.yLabelSpace);
        final Vector list = new Vector();
        int frameCount = 0;
        if (this.orderHistory != null && this.orderHistory.length > 0) {
            final int orderHistorySize = this.orderHistory.length;
            final long historyEnd = ((AverageOrderAppletData)this.orderHistory[orderHistorySize - 1]).getUpdateTime();
            long lastStart = historyEnd - historyEnd % this.milliSecondsInHistory;
            final long frameEnd = ((AverageOrderAppletData)this.orderHistory[orderHistorySize - 1]).getTimeStamp();
            long frameStart = frameEnd - frameEnd % this.milliSecondsInHistory;
            final long historyStart = lastStart - (this.history - 1) * this.milliSecondsInHistory;
            OrderFrame o = null;
            for (int i = orderHistorySize - 1; i >= 0 && frameCount < this.history; --i) {
                final AverageOrderChartData chartData = this.orderHistory[i];
                final long timeStamp = chartData.getTimeStamp();
                if (timeStamp >= frameStart) {
                    if (o == null) {
                        o = this.getOrderFrame(chartData);
                    }
                    else {
                        o.add(this.getOrderFrame(chartData));
                    }
                }
                else {
                    if (o != null) {
                        while (lastStart >= frameStart && frameCount < this.history) {
                            list.addElement(o);
                            ++frameCount;
                            lastStart -= this.milliSecondsInHistory;
                        }
                    }
                    frameStart = chartData.getTimeStamp();
                    frameStart -= frameStart % this.milliSecondsInHistory;
                    o = this.getOrderFrame(chartData);
                }
            }
            if (o != null) {
                while (lastStart >= frameStart && frameCount < this.history) {
                    list.addElement(o);
                    ++frameCount;
                    lastStart -= this.milliSecondsInHistory;
                }
            }
        }
        int lineTop = this.graphTop;
        int stripCount = 0;
        int availHistory = list.size();
        int historyInChart = this.history;
        if (historyInChart > availHistory) {
            if (availHistory % 5 != 0) {
                availHistory += 5 - availHistory % 5;
            }
            historyInChart = this.history / (this.history / availHistory);
        }
        this.orderStripWidth = this.graphHeight / historyInChart;
        final int stripInPack = historyInChart / 5;
        long timeLine = ((AverageOrderAppletData)this.orderHistory[this.orderHistory.length - 1]).getUpdateTime();
        timeLine -= timeLine % this.milliSecondsInHistory;
        for (int j = 0, m = 0; j < list.size() && m < historyInChart; ++j, ++m) {
            final OrderFrame of = list.elementAt(j);
            final int lineBottom = lineTop + this.orderStripWidth - 1;
            if (of.getOffHigh()) {
                g2D.setColor(Color.green);
                g2D.fillPolygon(new int[] { this.graphRight, this.graphRight - this.orderStripWidth, this.graphRight - this.orderStripWidth }, new int[] { lineTop + this.orderStripWidth / 2, lineBottom, lineTop }, 3);
            }
            else if (of.getOffLow()) {
                g2D.setColor(Color.red);
                g2D.fillPolygon(new int[] { this.graphLeft, this.graphLeft + this.orderStripWidth, this.graphLeft + this.orderStripWidth }, new int[] { lineTop + this.orderStripWidth / 2, lineTop, lineBottom }, 3);
            }
            else {
                final OrderFrame o = of.getAverage();
                final int[] bids = o.bids;
                final int[] asks = o.asks;
                final int[] lastPrices = o.lastPrice;
                int k = 0;
                for (int l = this.graphLeft; l <= this.graphRight; ++l) {
                    if (lastPrices[k] != 0) {
                        g2D.setColor(this.lastPriceColor);
                        g2D.drawLine(l, lineTop, l, lineBottom);
                    }
                    else if (asks[k] != 0 || bids[k] != 0) {
                        final int ask = (asks[k] == 0) ? 0 : (255 - asks[k]);
                        final int bid = (bids[k] == 0) ? 0 : (255 - bids[k]);
                        try {
                            g2D.setColor(new Color(ask, bid, 0));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("asks[" + k + "]=" + asks[k] + ",bids[" + k + "]=" + bids[k]);
                        }
                        g2D.drawLine(l, lineTop, l, lineBottom);
                    }
                    ++k;
                }
            }
            g2D.setColor(this.gridColor);
            g2D.drawLine(this.graphLeft, lineBottom, this.graphLeft - 2, lineBottom);
            if (++stripCount % stripInPack == 0) {
                this.markTimeLine(g2D, timeLine, m + 1);
            }
            timeLine -= this.milliSecondsInHistory;
            lineTop += this.orderStripWidth;
        }
    }
    
    protected void markTimeLine(final Graphics g2D, final long timeLine, final int pos) {
        final String timeString = TimeUtil.getTimeString(timeLine);
        final Font oldFont = g2D.getFont();
        final Color oldColor = g2D.getColor();
        final int fontSize = FontUtil.getFontSizeByHeight(this.graphHeight / 5, g2D);
        final Font newFont = new Font(this.fontName, this.fontStyle, fontSize);
        g2D.setFont(newFont);
        final FontMetrics fontMetrics = g2D.getFontMetrics();
        final int y = this.graphTop + pos * this.orderStripWidth;
        final int ptrWidth = 4;
        g2D.setColor(this.gridColor);
        g2D.drawLine(this.graphLeft - ptrWidth, y - 1, this.graphLeft - 1, y - 1);
        final int stringLen = fontMetrics.stringWidth(timeString);
        final int stringBase = y - fontMetrics.getDescent();
        g2D.setColor(this.labelColor);
        g2D.drawString(timeString, this.graphLeft - 4 - this.xLabelSpace - stringLen, stringBase);
        g2D.setFont(oldFont);
        g2D.setColor(oldColor);
    }
    
    protected OrderFrame getOrderFrame(final AverageOrderChartData chartData) {
        final int[] bidsColor = new int[this.graphWidth + 1];
        final int[] asksColor = new int[this.graphWidth + 1];
        final int[] lastPriceColor = new int[this.graphWidth + 1];
        boolean offLow = false;
        boolean offHigh = false;
        final float priceDiff = this.maxPrice - this.minPrice;
        final int volumeSteps = this.volumeScaleMax / this.volumeScaleInterval;
        final int colorStep = this.colorSpace / (volumeSteps + 1);
        final float lastPrice = chartData.getLastPrice();
        final SimpleOrder[] bids = chartData.getBids();
        if (bids != null && bids.length > 0) {
            final AverageOrderPrice aop = new AverageOrderPrice(bids);
            AverageOrderPrice.Result result = aop.getFirstResult();
            float avePrice = result.getAvePrice() / this.DECIMAL;
            int currentVolume = result.getCurrentVolume();
            int currentX = this.graphLeft + (int)((avePrice - this.minPrice) / priceDiff * this.graphWidth);
            if (currentX > this.graphRight) {
                currentX = this.graphRight;
            }
            final int nextVolumeStep = (currentVolume / this.volumeScaleInterval + 1) * this.volumeScaleInterval - 1;
            int toNextVolumeStep = nextVolumeStep - currentVolume;
            while ((result = aop.getNextAveragePrice(toNextVolumeStep)) != null && nextVolumeStep <= this.volumeScaleMax) {
                avePrice = result.getAvePrice() / this.DECIMAL;
                currentVolume = result.getCurrentVolume();
                if (avePrice <= this.maxPrice && avePrice >= this.minPrice) {
                    final int x = this.graphLeft + (int)((avePrice - this.minPrice) * this.graphWidth / priceDiff);
                    int color = (currentVolume / this.volumeScaleInterval + 1) * colorStep;
                    if (currentVolume > this.volumeScaleMax) {
                        color = (this.volumeScaleMax / this.volumeScaleInterval + 1) * colorStep;
                    }
                    for (int i = x, j = x - this.graphLeft; i < currentX; ++i, ++j) {
                        bidsColor[j] = color;
                    }
                    currentX = x;
                    toNextVolumeStep = this.volumeScaleInterval;
                }
                else {
                    if (avePrice < this.minPrice) {
                        final int color2 = (currentVolume % this.volumeScaleMax / this.volumeScaleInterval + 1) * colorStep;
                        for (int k = this.graphLeft, l = 0; k < currentX; ++k, ++l) {
                            bidsColor[l] = color2;
                        }
                        break;
                    }
                    break;
                }
            }
            if (currentX >= this.graphRight) {
                offHigh = true;
            }
        }
        final SimpleOrder[] asks = chartData.getAsks();
        if (asks != null && asks.length > 0) {
            final AverageOrderPrice aop2 = new AverageOrderPrice(asks);
            AverageOrderPrice.Result result2 = aop2.getFirstResult();
            float avePrice2 = result2.getAvePrice() / this.DECIMAL;
            int currentVolume2 = result2.getCurrentVolume();
            int currentX2 = this.graphLeft + (int)((avePrice2 - this.minPrice) / priceDiff * this.graphWidth);
            if (currentX2 < this.graphLeft) {
                currentX2 = this.graphLeft;
            }
            final int nextVolumeStep2 = (currentVolume2 / this.volumeScaleInterval + 1) * this.volumeScaleInterval - 1;
            int toNextVolumeStep2 = nextVolumeStep2 - currentVolume2;
            while ((result2 = aop2.getNextAveragePrice(toNextVolumeStep2)) != null && nextVolumeStep2 <= this.volumeScaleMax) {
                avePrice2 = result2.getAvePrice() / this.DECIMAL;
                currentVolume2 = result2.getCurrentVolume();
                if (avePrice2 >= this.minPrice && avePrice2 <= this.maxPrice) {
                    final int x2 = this.graphLeft + (int)((avePrice2 - this.minPrice) * this.graphWidth / priceDiff);
                    int color3 = (currentVolume2 / this.volumeScaleInterval + 1) * colorStep;
                    if (currentVolume2 > this.volumeScaleMax) {
                        color3 = (this.volumeScaleMax / this.volumeScaleInterval + 1) * colorStep;
                    }
                    for (int m = currentX2, j2 = currentX2 - this.graphLeft; m < x2; ++m, ++j2) {
                        asksColor[j2] = color3;
                    }
                    currentX2 = x2;
                    toNextVolumeStep2 = this.volumeScaleInterval;
                }
                else {
                    if (avePrice2 > this.maxPrice) {
                        final int color = (currentVolume2 % this.volumeScaleMax / this.volumeScaleInterval + 1) * colorStep;
                        for (int i = currentX2, j = currentX2 - this.graphLeft; i < this.graphRight; ++i, ++j) {
                            asksColor[j] = color;
                        }
                        break;
                    }
                    break;
                }
            }
            if (currentX2 <= this.graphLeft) {
                offLow = true;
            }
        }
        if (lastPrice >= this.minPrice && lastPrice <= this.maxPrice) {
            final int lastPriceX = this.graphLeft + (int)((lastPrice - this.minPrice) / priceDiff * this.graphWidth);
            lastPriceColor[lastPriceX - this.graphLeft] = 255;
        }
        final OrderFrame of = new OrderFrame(chartData.getTimeStamp(), bidsColor, asksColor, lastPriceColor);
        of.setOffHigh(offHigh);
        of.setOffLow(offLow);
        return of;
    }
    
    protected void setOrderDataHistory(final OrderChartData chartData) {
        final AverageOrderChartData[] historyData = ((AverageOrderHistoryAppletData)chartData).getOrderHistory();
        final AverageOrderChartData averageOrderChartData = historyData[historyData.length - 1];
        final int volumeHigh = this.getVolumeHigh((OrderChartData)averageOrderChartData);
        this.volumeScaleInterval = this.getVolumeIntval(volumeHigh);
        this.volumeScaleMax = this.nextVolumeHigh(volumeHigh, this.volumeScaleInterval);
        this.minPrice = averageOrderChartData.getLowBound(this.size);
        this.maxPrice = averageOrderChartData.getHighBound(this.size);
    }
    
    protected int getVolumeHigh(final OrderChartData orderChartData) {
        final Object[] bids = orderChartData.getBids();
        final Object[] asks = orderChartData.getAsks();
        int maxBidVolume = 0;
        int maxAskVolume = 0;
        if (bids != null && bids.length > 0) {
            final int bidSize = bids.length;
            for (int i = bidSize - 1; i >= 0; --i) {
                maxBidVolume += ((SimpleOrder)bids[i]).getSize();
                if (maxBidVolume >= this.size) {
                    break;
                }
            }
        }
        if (asks != null && asks.length > 0) {
            for (int askSize = asks.length, i = 0; i < askSize; ++i) {
                maxAskVolume += ((SimpleOrder)asks[i]).getSize();
                if (maxAskVolume >= this.size) {
                    break;
                }
            }
        }
        int volumeHigh = (maxBidVolume > maxAskVolume) ? maxBidVolume : maxAskVolume;
        if (volumeHigh < 10) {
            volumeHigh = 10;
        }
        return volumeHigh;
    }
    
    public int getHistory() {
        return this.history;
    }
    
    public int getSecondsInHistory() {
        return this.milliSecondsInHistory;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setHistory(final int history) {
        if (history > 0) {
            this.history = history;
        }
    }
    
    public void setMilliSecondsInHistory(final int milliSecondsInHistory) {
        if (milliSecondsInHistory > 0) {
            this.milliSecondsInHistory = milliSecondsInHistory;
        }
    }
    
    public void setSize(final int size) {
        if (size > 0) {
            this.size = size;
        }
    }
    
    public void setOrderChartData(final AverageOrderChartData[] orderHistory) {
        this.orderHistory = orderHistory;
    }
    
    class OrderFrame
    {
        protected int[] bids;
        protected int[] asks;
        protected int[] lastPrice;
        protected long timeStamp;
        protected int size;
        protected boolean offLow;
        protected boolean offHigh;
        
        public OrderFrame(final long timeStamp, final int[] bids, final int[] asks, final int[] lastPrice) {
            this.offLow = true;
            this.offHigh = true;
            this.bids = bids;
            this.asks = asks;
            this.lastPrice = lastPrice;
            this.timeStamp = timeStamp;
            this.size = 1;
        }
        
        public void add(final OrderFrame orderFrame) {
            for (int bidSize = this.bids.length, i = 0; i < bidSize; ++i) {
                final int[] bids = this.bids;
                final int n = i;
                bids[n] += orderFrame.bids[i];
            }
            for (int askSize = this.asks.length, j = 0; j < askSize; ++j) {
                final int[] asks = this.asks;
                final int n2 = j;
                asks[n2] += orderFrame.asks[j];
            }
            for (int lastPriceSize = this.lastPrice.length, k = 0; k < lastPriceSize; ++k) {
                final int[] lastPrice = this.lastPrice;
                final int n3 = k;
                lastPrice[n3] += orderFrame.lastPrice[k];
            }
            this.size += orderFrame.size;
            this.offLow = (this.offLow && orderFrame.offLow);
            this.offHigh = (this.offHigh && orderFrame.offHigh);
        }
        
        public OrderFrame getAverage() {
            final int bidSize = this.bids.length;
            final int askSize = this.asks.length;
            final int lastPriceSize = this.lastPrice.length;
            final int[] avgBids = new int[bidSize];
            final int[] avgAsks = new int[askSize];
            final int[] avgLastPrice = new int[lastPriceSize];
            for (int i = 0; i < bidSize; ++i) {
                avgBids[i] = this.bids[i] / this.size;
            }
            for (int i = 0; i < askSize; ++i) {
                avgAsks[i] = this.asks[i] / this.size;
            }
            for (int i = 0; i < lastPriceSize; ++i) {
                avgLastPrice[i] = this.lastPrice[i] / this.size;
            }
            return new OrderFrame(this.timeStamp, avgBids, avgAsks, avgLastPrice);
        }
        
        public boolean getOffLow() {
            return this.offLow;
        }
        
        public boolean getOffHigh() {
            return this.offHigh;
        }
        
        public void setOffLow(final boolean offLow) {
            this.offLow = offLow;
        }
        
        public void setOffHigh(final boolean offHigh) {
            this.offHigh = offHigh;
        }
        
        public String toString() {
            final StringBuffer buffer = new StringBuffer();
            buffer.append("TimeStamp: " + this.timeStamp).append("\n");
            buffer.append("bids:").append("\n");
            for (int i = 0; i < this.bids.length; ++i) {
                buffer.append(String.valueOf(this.bids[i]) + " ");
            }
            buffer.append("\n");
            buffer.append("asks:").append("\n");
            for (int i = 0; i < this.asks.length; ++i) {
                buffer.append(String.valueOf(this.asks[i]) + " ");
            }
            buffer.append("\n");
            buffer.append("lastPrices:").append("\n");
            for (int i = 0; i < this.lastPrice.length; ++i) {
                buffer.append(String.valueOf(this.lastPrice[i]) + " ");
            }
            return buffer.toString();
        }
    }
}
