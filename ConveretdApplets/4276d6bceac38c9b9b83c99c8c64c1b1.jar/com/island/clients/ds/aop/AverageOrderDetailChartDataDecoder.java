// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import com.island.servers.ds.chart.stockorder.SimpleOrder;
import java.io.IOException;
import com.island.servers.ds.chart.stockorder.OrderChartData;
import java.net.URL;
import java.io.InputStream;
import com.island.servers.ds.chart.stockorder.averageorderchart.AbstractAverageOrderChartDataController;

public class AverageOrderDetailChartDataDecoder extends AbstractAverageOrderChartDataController implements DataDecoder
{
    protected InputStream istream;
    protected URL streamer;
    protected Book book;
    protected EventList eventList;
    
    public AverageOrderDetailChartDataDecoder(final URL streamer) {
        this.eventList = new EventList();
        this.streamer = streamer;
    }
    
    public InputStream getIstream() {
        return this.istream;
    }
    
    public OrderChartData getOrderChartData(final String symbol, final int maxShares) {
        AverageOrderAppletData chartData = null;
        try {
            if (this.istream.available() < 1) {
                return this.handleNoPacket(symbol, maxShares);
            }
            final char type = (char)this.istream.read();
            if (type == 'N') {
                return this.handleNPacket(symbol, maxShares);
            }
            if (type == 'H') {
                return this.handleHPacket(symbol, maxShares);
            }
            if (type == 'S') {
                return this.handleSPacket(symbol, maxShares);
            }
            if (type == 'C') {
                return this.handleCPacket(symbol, maxShares);
            }
            chartData = new AverageOrderAppletData(symbol, maxShares);
            chartData.setDataType(3);
        }
        catch (Exception e) {
            try {
                this.istream = this.streamer.openStream();
                this.resetBook();
            }
            catch (Exception ex) {}
        }
        return (OrderChartData)chartData;
    }
    
    protected OrderChartData handleNoPacket(final String symbol, final int maxShares) {
        if (this.eventList.size > 0 && this.book != null) {
            this.updateBook();
            return (OrderChartData)this.getOrderData(symbol, maxShares);
        }
        return null;
    }
    
    protected OrderChartData handleNPacket(final String symbol, final int maxShares) {
        System.out.print("N");
        return (OrderChartData)this.getInvalidSymbolData(symbol, maxShares);
    }
    
    protected OrderChartData handleHPacket(final String symbol, final int maxShares) {
        System.out.print("H");
        return (OrderChartData)this.getHeartBeatData(symbol, maxShares);
    }
    
    protected OrderChartData handleSPacket(final String symbol, final int maxShares) throws IOException {
        System.out.print("S");
        if (this.book != null) {
            while (this.eventList.size > 0) {
                this.updateBook();
            }
            this.readEvents(this.istream);
            if (this.eventList.size > 0) {
                this.updateBook();
            }
            return (OrderChartData)this.getOrderData(symbol, maxShares);
        }
        this.skipEvents(this.istream);
        return null;
    }
    
    protected OrderChartData handleCPacket(final String symbol, final int maxShares) throws IOException {
        System.out.print("C");
        this.readBook(this.istream);
        return (OrderChartData)this.getOrderData(symbol, maxShares);
    }
    
    protected void skipEvents(final InputStream in) throws IOException {
        for (int count = read2ByteInt(in), i = 0; i < count; ++i) {
            this.readEvent(in);
        }
    }
    
    protected void readEvents(final InputStream in) throws IOException {
        for (int count = read2ByteInt(in), i = 0; i < count; ++i) {
            final Event event = this.readEvent(in);
            this.eventList.add(event);
        }
    }
    
    protected Event readEvent(final InputStream in) throws IOException {
        final int timeStamp = read4ByteInt(in);
        final int price = read3ByteInt(in);
        final int volume = read3ByteInt(in);
        final int type = in.read();
        return new Event(timeStamp, price, volume, type);
    }
    
    protected void readBook(final InputStream in) throws IOException {
        this.eventList.clear();
        this.book = new Book();
        final int lastPrice = read3ByteInt(in);
        this.book.lastPrice = lastPrice;
        final int bidSize = read3ByteInt(in);
        if (bidSize > 0) {
            Order current;
            final Order bids = current = new Order(0, 0);
            final int baseBid = read3ByteInt(in);
            final int bytesInDiff = this.istream.read();
            for (int i = 0; i < bidSize; ++i) {
                final int deltaBid = readPriceDiff(in, bytesInDiff);
                final int shares = read3ByteInt(in);
                current.next = new Order(baseBid - deltaBid, shares);
                current = current.next;
            }
            this.book.bids = bids.next;
            this.book.bidSize = bidSize;
        }
        final int askSize = read3ByteInt(in);
        if (askSize > 0) {
            Order current2;
            final Order asks = current2 = new Order(0, 0);
            final int baseAsk = read3ByteInt(in);
            final int bytesInDiff2 = this.istream.read();
            for (int j = 0; j < askSize; ++j) {
                final int deltaAsk = readPriceDiff(in, bytesInDiff2);
                final int shares2 = read3ByteInt(in);
                current2.next = new Order(baseAsk + deltaAsk, shares2);
                current2 = current2.next;
            }
            this.book.asks = asks.next;
            this.book.askSize = askSize;
        }
        final int matchedShares = read4ByteInt(in);
        this.book.matchedShares = matchedShares;
        final int timeStamp = read4ByteInt(in);
        this.book.timeStamp = timeStamp;
        final int netChange = read3ByteInt(in);
        this.book.netChange = netChange;
        this.book.previousClose = lastPrice - netChange;
    }
    
    protected void skipBook(final InputStream in) throws IOException {
        final int lastPrice = read3ByteInt(in);
        final int bidSize = read3ByteInt(in);
        if (bidSize > 0) {
            final int baseBid = read3ByteInt(in);
            for (int i = 0; i < bidSize; ++i) {
                final int deltaBid = read2ByteInt(in);
                read3ByteInt(in);
            }
        }
        final int askSize = read3ByteInt(in);
        if (askSize > 0) {
            final int baseAsk = read3ByteInt(in);
            for (int j = 0; j < askSize; ++j) {
                final int deltaAsk = read2ByteInt(in);
                read3ByteInt(in);
            }
            this.book.askSize = askSize;
        }
        final int matchedShares = read4ByteInt(in);
        final int timeStamp = read4ByteInt(in);
        final int netChange = read3ByteInt(in);
    }
    
    protected void updateBook() {
        if (this.book == null) {
            return;
        }
        final Event event = this.eventList.getEvent();
        if (event != null) {
            this.book.processEvent(event);
        }
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
        float low = -1.0f;
        float high = -1.0f;
        final float lastPrice = this.book.lastPrice / 1000.0f;
        data.setLastPrice(lastPrice);
        final int bidSize = this.book.bidSize;
        SimpleOrder.List bids = null;
        SimpleOrder.List asks = null;
        SimpleOrder[] bidArray = null;
        SimpleOrder[] askArray = null;
        if (bidSize > 0) {
            long sum = 0L;
            int needShares = maxShares;
            int totShares = 0;
            bids = new SimpleOrder.List();
            Order order = this.book.bids;
            for (int i = 0; i < bidSize; ++i) {
                final long price = order.price;
                final int shares = order.shares;
                if (needShares <= 0) {
                    break;
                }
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
                order = order.next;
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
        final int askSize = this.book.askSize;
        if (askSize > 0) {
            long sum2 = 0L;
            int needShares2 = maxShares;
            int totShares2 = 0;
            asks = new SimpleOrder.List();
            Order order2 = this.book.asks;
            for (int j = 0; j < askSize; ++j) {
                final long price2 = order2.price;
                final int shares2 = order2.shares;
                if (needShares2 <= 0) {
                    break;
                }
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
                order2 = order2.next;
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
        data.setMatchedShares((long)this.book.matchedShares);
        data.setNetChange(this.book.netChange / 1000.0f);
        data.setTimeStamp((long)this.book.timeStamp);
        data.setUpdateTime(data.getTimeStamp());
        data.setLow(low);
        data.setHigh(high);
        data.setDataType(0);
        chartData = data;
        return chartData;
    }
    
    public void resetBook() {
        this.book = null;
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
    
    class EventList
    {
        int size;
        Event first;
        Event last;
        
        public void clear() {
            final Event event = null;
            this.last = event;
            this.first = event;
            this.size = 0;
        }
        
        public void add(final Event event) {
            if (this.first == null) {
                this.last = event;
                this.first = event;
            }
            else {
                this.last.setNext(event);
                this.last = event;
            }
            ++this.size;
        }
        
        public Event getEvent() {
            final Event event = this.first;
            if (this.first == this.last) {
                final Event event2 = null;
                this.last = event2;
                this.first = event2;
            }
            else {
                this.first = this.first.getNext();
            }
            if (event != null) {
                --this.size;
            }
            return event;
        }
        
        public String toString() {
            final StringBuffer buffer = new StringBuffer();
            buffer.append(String.valueOf(this.size) + " events\n");
            for (Event e = this.first; e != null; e = e.getNext()) {
                buffer.append(e.getTimeStamp()).append(",").append(e.getPrice()).append(",").append(e.getShares());
                buffer.append(",").append(e.getType()).append("\n");
            }
            return buffer.toString();
        }
    }
    
    class Order
    {
        int price;
        int shares;
        Order pre;
        Order next;
        
        public Order(final int price, final int shares) {
            this.price = price;
            this.shares = shares;
        }
    }
    
    class Book
    {
        int previousClose;
        int timeStamp;
        int lastPrice;
        int netChange;
        int matchedShares;
        Order bids;
        Order asks;
        int bidSize;
        int askSize;
        
        protected void processEvent(final Event event) {
            this.timeStamp = event.getTimeStamp();
            final int type = event.getType();
            if ((type & 0x8) > 0) {
                this.reduceOrder(event);
            }
            else if ((type & 0x4) > 0) {
                this.lastPrice = event.getPrice();
                this.netChange = this.lastPrice - this.previousClose;
                this.matchedShares += event.getShares();
            }
            else {
                this.addOrder(event);
            }
        }
        
        protected void addOrder(final Event event) {
            if ((event.getType() & 0x1) > 0) {
                this.addAskOrder(event);
            }
            else {
                this.addBidOrder(event);
            }
        }
        
        protected void addAskOrder(final Event event) {
            if (this.asks == null) {
                this.asks = new Order(event.getPrice(), event.getShares());
                ++this.askSize;
                return;
            }
            final int price = event.getPrice();
            final int shares = event.getShares();
            Order pre = this.asks;
            if (price < pre.price) {
                final Order order = new Order(price, shares);
                order.next = pre;
                this.asks = order;
                ++this.askSize;
            }
            else {
                while (pre.next != null && price >= pre.next.price) {
                    pre = pre.next;
                }
                if (price == pre.price) {
                    final Order order2 = pre;
                    order2.shares += shares;
                }
                else {
                    final Order order = new Order(price, shares);
                    order.next = pre.next;
                    pre.next = order;
                    ++this.askSize;
                }
            }
        }
        
        protected void addBidOrder(final Event event) {
            if (this.bids == null) {
                this.bids = new Order(event.getPrice(), event.getShares());
                ++this.bidSize;
                return;
            }
            final int price = event.getPrice();
            final int shares = event.getShares();
            Order pre = this.bids;
            if (price > pre.price) {
                final Order order = new Order(price, shares);
                order.next = pre;
                this.bids = order;
                ++this.bidSize;
            }
            else {
                while (pre.next != null && price <= pre.next.price) {
                    pre = pre.next;
                }
                if (price == pre.price) {
                    final Order order2 = pre;
                    order2.shares += shares;
                }
                else {
                    final Order order = new Order(price, shares);
                    order.next = pre.next;
                    pre.next = order;
                    ++this.bidSize;
                }
            }
        }
        
        protected void reduceOrder(final Event event) {
            if ((event.getType() & 0x1) > 0) {
                this.reduceAskOrder(event);
            }
            else {
                this.reduceBidOrder(event);
            }
        }
        
        protected void reduceAskOrder(final Event event) {
            Order pre = this.asks;
            if (pre != null) {
                if (event.getPrice() == pre.price) {
                    final Order order = pre;
                    order.shares -= event.getShares();
                    if (pre.shares == 0) {
                        this.asks = pre.next;
                        --this.askSize;
                    }
                    return;
                }
                while (pre.next != null) {
                    if (event.getPrice() == pre.next.price) {
                        final Order next = pre.next;
                        next.shares -= event.getShares();
                        if (pre.next.shares == 0) {
                            pre.next = pre.next.next;
                            --this.askSize;
                        }
                        return;
                    }
                    pre = pre.next;
                }
            }
        }
        
        protected void reduceBidOrder(final Event event) {
            Order pre = this.bids;
            if (pre != null) {
                if (event.getPrice() == pre.price) {
                    final Order order = pre;
                    order.shares -= event.getShares();
                    if (pre.shares == 0) {
                        this.bids = pre.next;
                        --this.bidSize;
                    }
                    return;
                }
                while (pre.next != null) {
                    if (event.getPrice() == pre.next.price) {
                        final Order next = pre.next;
                        next.shares -= event.getShares();
                        if (pre.next.shares == 0) {
                            pre.next = pre.next.next;
                            --this.bidSize;
                        }
                        return;
                    }
                    pre = pre.next;
                }
            }
        }
        
        public String toString() {
            final StringBuffer buffer = new StringBuffer();
            buffer.append("Book:\n");
            buffer.append("timestamp=" + this.timeStamp + "\n");
            buffer.append("lastPrice=" + this.lastPrice + "\n");
            buffer.append("netChange=" + this.netChange + "\n");
            buffer.append(String.valueOf(this.bidSize) + " bids\n");
            if (this.bidSize > 0) {
                for (Order order = this.bids; order != null; order = order.next) {
                    buffer.append(String.valueOf(order.price) + "," + order.shares + "\n");
                }
            }
            buffer.append(String.valueOf(this.askSize) + " asks\n");
            if (this.askSize > 0) {
                for (Order order = this.asks; order != null; order = order.next) {
                    buffer.append(String.valueOf(order.price) + "," + order.shares + "\n");
                }
            }
            return buffer.toString();
        }
    }
}
