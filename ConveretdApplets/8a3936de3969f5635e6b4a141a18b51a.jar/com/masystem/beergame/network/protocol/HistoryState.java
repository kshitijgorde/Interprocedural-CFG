// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

import java.io.Serializable;

public class HistoryState implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int stock;
    private int incomingOrder;
    private int incomingStock;
    private int outgoingOrder;
    private int outgoingStock;
    private int result;
    
    public HistoryState() {
        this.stock = Integer.MIN_VALUE;
        this.incomingOrder = Integer.MIN_VALUE;
        this.incomingStock = Integer.MIN_VALUE;
        this.outgoingOrder = Integer.MIN_VALUE;
        this.outgoingStock = Integer.MIN_VALUE;
    }
    
    public final void setStock(final int stock) {
        this.stock = stock;
    }
    
    public final int getStock() {
        return this.stock;
    }
    
    public final void setIncomingOrder(final int incomingOrder) {
        this.incomingOrder = incomingOrder;
    }
    
    public final int getIncomingOrder() {
        return this.incomingOrder;
    }
    
    public final void setIncomingStock(final int incomingStock) {
        this.incomingStock = incomingStock;
    }
    
    public final int getIncomingStock() {
        return this.incomingStock;
    }
    
    public final void setOutgoingOrder(final int outgoingOrder) {
        this.outgoingOrder = outgoingOrder;
    }
    
    public final int getOutgoingOrder() {
        return this.outgoingOrder;
    }
    
    public final void setOutgoingStock(final int outgoingStock) {
        this.outgoingStock = outgoingStock;
    }
    
    public final int getOutgoingStock() {
        return this.outgoingStock;
    }
    
    public final void setResult(final int result) {
        this.result = result;
    }
    
    public final int getResult() {
        return this.result;
    }
}
