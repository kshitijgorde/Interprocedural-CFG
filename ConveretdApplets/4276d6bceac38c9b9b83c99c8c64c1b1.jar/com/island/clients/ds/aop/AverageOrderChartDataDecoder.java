// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import com.island.servers.ds.chart.stockorder.SimpleOrder;
import java.io.IOException;
import com.island.servers.ds.chart.stockorder.OrderChartData;
import java.io.InputStream;
import java.net.URL;
import com.island.servers.ds.chart.stockorder.averageorderchart.AbstractAverageOrderChartDataController;

public class AverageOrderChartDataDecoder extends AbstractAverageOrderChartDataController implements DataDecoder
{
    protected URL streamer;
    protected InputStream istream;
    
    public AverageOrderChartDataDecoder(final URL streamer) {
        this.streamer = streamer;
    }
    
    public InputStream getIstream() {
        return this.istream;
    }
    
    public OrderChartData getOrderChartData(final String symbol, final int maxShares) {
        AverageOrderAppletData chartData = null;
        try {
            if (this.istream.available() < 1) {
                return null;
            }
            final char type = (char)this.istream.read();
            if (type == 'N') {
                chartData = this.getInvalidSymbolData(symbol, maxShares);
            }
            else if (type == 'H') {
                chartData = this.getHeartBeatData(symbol, maxShares);
            }
            else if (type == 'S') {
                chartData = this.getOrderData(symbol, maxShares);
            }
            else {
                chartData = new AverageOrderAppletData(symbol, maxShares);
                chartData.setDataType(3);
            }
        }
        catch (Exception e) {
            try {
                this.istream = this.streamer.openStream();
            }
            catch (Exception ex) {
                e.printStackTrace();
            }
        }
        return (OrderChartData)chartData;
    }
    
    protected AverageOrderAppletData getInvalidSymbolData(final String symbol, final int maxShares) {
        final AverageOrderAppletData data = new AverageOrderAppletData(symbol, maxShares);
        data.setDataType(2);
        return data;
    }
    
    protected AverageOrderAppletData getHeartBeatData(final String symbol, final int maxShares) {
        AverageOrderAppletData chartData = null;
        final AverageOrderAppletData data = new AverageOrderAppletData(symbol, maxShares);
        try {
            data.setTimeStamp(read3ByteInt(this.istream) * 1000L);
            data.setDataType(1);
            chartData = data;
            data.setUpdateTime(data.getTimeStamp());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    protected AverageOrderAppletData getOrderData(final String symbol, final int maxShares) {
        AverageOrderAppletData chartData = null;
        final AverageOrderAppletData data = new AverageOrderAppletData(symbol, maxShares);
        try {
            float low = -1.0f;
            float high = -1.0f;
            final float lastPrice = read3ByteInt(this.istream) / 1000.0f;
            data.setLastPrice(lastPrice);
            final int bidSize = read3ByteInt(this.istream);
            SimpleOrder.List bids = null;
            SimpleOrder.List asks = null;
            SimpleOrder[] bidArray = null;
            SimpleOrder[] askArray = null;
            if (bidSize > 0) {
                long sum = 0L;
                int needShares = maxShares;
                int totShares = 0;
                bids = new SimpleOrder.List();
                final int priceBase = read3ByteInt(this.istream);
                final int bytesInDiff = this.istream.read();
                for (int i = 0; i < bidSize; ++i) {
                    final long price = readPriceDiff(this.istream, bytesInDiff) + priceBase;
                    final int shares = read3ByteInt(this.istream);
                    if (needShares > 0) {
                        if (shares <= needShares) {
                            totShares += shares;
                            sum += price * shares;
                            needShares -= shares;
                            bids.add(new SimpleOrder(price, shares));
                        }
                        else {
                            totShares += needShares;
                            sum += price * needShares;
                            bids.add(new SimpleOrder(price, needShares));
                            needShares = 0;
                        }
                    }
                }
                bidArray = bids.toArray();
                data.setBids(bidArray);
                low = sum / totShares / 1000.0f;
                final float bidHigh = bidArray[0].getPrice() / SimpleOrder.DECIMAL;
                if (bidHigh == low) {
                    high = low + 0.05f;
                }
                else {
                    high = bidHigh;
                }
                if (lastPrice > 0.0f) {
                    high = ((bidHigh > lastPrice) ? bidHigh : lastPrice);
                }
            }
            final int askSize = read3ByteInt(this.istream);
            if (askSize > 0) {
                long sum2 = 0L;
                int needShares2 = maxShares;
                int totShares2 = 0;
                asks = new SimpleOrder.List();
                final int priceBase2 = read3ByteInt(this.istream);
                final int byteInDiff = this.istream.read();
                for (int j = 0; j < askSize; ++j) {
                    final long price2 = readPriceDiff(this.istream, byteInDiff) + priceBase2;
                    final int shares2 = read3ByteInt(this.istream);
                    if (needShares2 > 0) {
                        if (shares2 <= needShares2) {
                            totShares2 += shares2;
                            sum2 += price2 * shares2;
                            needShares2 -= shares2;
                            asks.add(new SimpleOrder(price2, shares2));
                        }
                        else {
                            totShares2 += needShares2;
                            sum2 += price2 * needShares2;
                            asks.add(new SimpleOrder(price2, needShares2));
                            needShares2 = 0;
                        }
                    }
                }
                askArray = asks.toArray();
                data.setAsks(askArray);
                high = sum2 / totShares2 / 1000.0f;
                if (low < 0.0f) {
                    low = askArray[0].getPrice() / SimpleOrder.DECIMAL;
                    if (low > lastPrice && lastPrice > 0.0f) {
                        low = lastPrice;
                    }
                    if (low == high) {
                        low = high - 0.05f;
                    }
                }
            }
            if (lastPrice > 0.0f && high < 0.0f && low < 0.0f) {
                low = lastPrice - 0.05f;
                high = lastPrice + 0.05f;
            }
            data.setMatchedShares((long)read4ByteInt(this.istream));
            data.setNetChange(read3ByteInt(this.istream) / 1000.0f);
            data.setTimeStamp((long)read4ByteInt(this.istream));
            data.setUpdateTime(data.getTimeStamp());
            data.setLow(low);
            data.setHigh(high);
            data.setDataType(0);
            chartData = data;
        }
        catch (IOException e) {
            System.out.println("Caught error.");
            e.printStackTrace();
        }
        return chartData;
    }
    
    protected static int read2ByteInt(final InputStream istream) throws IOException {
        final int i = istream.read();
        final int j = istream.read();
        return i + (j << 8);
    }
    
    protected static int read3ByteInt(final InputStream istream) throws IOException {
        final int i = istream.read();
        final int j = istream.read();
        final int k = istream.read();
        return i + (j << 8) + (k << 16);
    }
    
    protected static int read4ByteInt(final InputStream istream) throws IOException {
        final int i = istream.read();
        final int j = istream.read();
        final int k = istream.read();
        final int l = istream.read();
        return i + (j << 8) + (k << 16) + (l << 24);
    }
    
    protected static int readPriceDiff(final InputStream istream, final int c) throws IOException {
        if (c == 1) {
            return istream.read();
        }
        if (c == 2) {
            return read2ByteInt(istream);
        }
        return read3ByteInt(istream);
    }
}
