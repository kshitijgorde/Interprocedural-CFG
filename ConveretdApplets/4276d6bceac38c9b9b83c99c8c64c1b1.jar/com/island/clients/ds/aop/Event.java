// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

public class Event
{
    public static final int ASK = 1;
    public static final int BID = 2;
    public static final int EXECUTE = 4;
    public static final int REDUCE = 8;
    int timeStamp;
    int price;
    int shares;
    int type;
    Event next;
    
    public Event(final int timeStamp, final int price, final int shares, final int type) {
        this.timeStamp = timeStamp;
        this.price = price;
        this.shares = shares;
        this.type = type;
    }
    
    public int getTimeStamp() {
        return this.timeStamp;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public int getShares() {
        return this.shares;
    }
    
    public int getType() {
        return this.type;
    }
    
    public Event getNext() {
        return this.next;
    }
    
    public void setNext(final Event event) {
        this.next = event;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(this.timeStamp).append(",");
        buffer.append(this.price).append(",");
        buffer.append(this.shares).append(",");
        if (this.type == 1) {
            buffer.append("ask");
        }
        else if (this.type == 2) {
            buffer.append("bid");
        }
        else if (this.type == 5) {
            buffer.append("exec_ask");
        }
        else if (this.type == 6) {
            buffer.append("exec_bid");
        }
        else if (this.type == 9) {
            buffer.append("reduce_ask");
        }
        else if (this.type == 10) {
            buffer.append("reduce_bid");
        }
        else {
            buffer.append(this.type);
        }
        return buffer.toString();
    }
}
