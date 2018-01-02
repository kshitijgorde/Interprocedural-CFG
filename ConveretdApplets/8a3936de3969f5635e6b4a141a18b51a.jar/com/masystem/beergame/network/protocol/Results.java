// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

import java.util.ArrayList;
import java.io.Serializable;

public class Results implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final ArrayList<HistoryState> retailer;
    private final ArrayList<HistoryState> wholesaler;
    private final ArrayList<HistoryState> distributor;
    private final ArrayList<HistoryState> producer;
    private int size;
    
    public Results(final ArrayList<HistoryState> retailer, final ArrayList<HistoryState> wholesaler, final ArrayList<HistoryState> distributor, final ArrayList<HistoryState> producer) {
        this.retailer = retailer;
        this.wholesaler = wholesaler;
        this.distributor = distributor;
        this.producer = producer;
        this.size = Integer.MAX_VALUE;
        Player.Type[] values;
        for (int length = (values = Player.Type.values()).length, i = 0; i < length; ++i) {
            final ArrayList<HistoryState> history;
            if ((history = this.getHistory(values[i])).size() < this.size) {
                this.size = history.size();
            }
        }
    }
    
    public final int getSize() {
        return this.size;
    }
    
    public final ArrayList<HistoryState> getHistory(final Player.Type type) {
        switch (type) {
            case RETAILER: {
                return this.retailer;
            }
            case WHOLESALER: {
                return this.wholesaler;
            }
            case DISTRIBUTOR: {
                return this.distributor;
            }
            default: {
                return this.producer;
            }
        }
    }
}
