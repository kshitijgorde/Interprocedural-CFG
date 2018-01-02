// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.test;

import java.util.Random;
import com.island.clients.ds.bv.ReaderThread;
import java.net.URL;
import com.island.clients.ds.bv.ReadableStockRecord;
import com.island.clients.ds.bv.BookViewPane;

public class BookViewTest implements BookViewPane
{
    int readCount;
    int repeatCount;
    int maxRepeatCount;
    ReadableStockRecord lastRec;
    URL codebase;
    String symbol;
    String id;
    ReaderThread t;
    int maxRecords;
    static int nextId;
    static Object syn;
    static Random rand;
    
    static {
        BookViewTest.nextId = 0;
        BookViewTest.syn = new Object();
        BookViewTest.rand = new Random();
    }
    
    public BookViewTest(final URL codebase, final String symbol) {
        this.readCount = 0;
        this.repeatCount = 0;
        this.maxRepeatCount = 15;
        this.codebase = codebase;
        this.symbol = symbol;
        final int nextRand = Math.abs(BookViewTest.rand.nextInt());
        this.maxRecords = nextRand % 250 + 10;
        try {
            final URL url = new URL(codebase, "/SERVICE/SQUOTE?STOCK=" + symbol + "&AGGREGATE=Y" + " ");
            (this.t = new ReaderThread(url, this)).start();
            synchronized (BookViewTest.syn) {
                this.id = "TEST" + BookViewTest.nextId++;
            }
            // monitorexit(BookViewTest.syn)
            System.out.println(String.valueOf(this.id) + " started. Max Records: " + this.maxRecords);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void blankStockRecord(final String s) {
        System.err.println(String.valueOf(this.id) + ": blank message: " + s);
    }
    
    public void showMessage(final String s) {
    }
    
    public void showStockRecord(final ReadableStockRecord r) {
        ++this.readCount;
        if (this.isRepeatRecord(this.lastRec, r)) {
            ++this.repeatCount;
            if (this.repeatCount > this.maxRepeatCount) {
                System.err.println(String.valueOf(this.id) + ": " + this.symbol + " aggregate repeated " + this.maxRepeatCount + " times");
                this.repeatCount = 0;
                this.maxRepeatCount += 100;
                if (this.maxRepeatCount < 0) {
                    this.maxRepeatCount = 15;
                    this.repeatCount = 0;
                }
            }
        }
        else {
            if (this.readCount >= this.maxRecords) {
                System.err.println("killing " + this.id + ". Number of read records: " + this.readCount);
                this.t.kill();
                new BookViewTest(this.codebase, this.symbol);
            }
            this.maxRepeatCount = 15;
            this.repeatCount = 0;
        }
    }
    
    public boolean isRepeatRecord(final ReadableStockRecord r1, final ReadableStockRecord r2) {
        if (r1 == null) {
            return r2 == null;
        }
        return r2 != null && !r1.isHalted() && r1.buyCount == r2.buyCount && r1.sellCount == r2.sellCount && r1.totalOrderCount == r2.totalOrderCount && r1.volume == r2.volume && r1.lastTradePrice == r2.lastTradePrice && r1.lastTradeTime == r2.lastTradeTime;
    }
    
    public static void main(final String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Usage: BookViewTest codebase symbol threadCount");
            System.exit(1);
        }
        final URL codebase = new URL(args[0]);
        final String symbol = args[1];
        for (int threadCount = Integer.parseInt(args[2]), i = 0; i < threadCount; ++i) {
            new BookViewTest(codebase, symbol);
        }
        System.out.println("Threads created");
    }
}
