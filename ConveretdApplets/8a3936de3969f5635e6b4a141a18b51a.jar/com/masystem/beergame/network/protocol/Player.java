// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

import java.util.ArrayList;
import java.io.Serializable;

public class Player implements Serializable
{
    private static final long serialVersionUID = 1L;
    private ArrayList<HistoryState> historyStates;
    private HistoryState historyState;
    private String name;
    private String email;
    private Type type;
    private Game joinedGame;
    private boolean readyForNextTurn;
    
    public Player() {
        this.reset();
    }
    
    public Player(final String name, final String email) {
        this();
        this.name = name;
        this.email = email;
    }
    
    public final void reset() {
        this.historyStates = new ArrayList<HistoryState>();
        this.type = null;
        this.joinedGame = null;
        this.readyForNextTurn = false;
        this.newHistoryState();
    }
    
    public final ArrayList<HistoryState> getHistoryStates() {
        return this.historyStates;
    }
    
    public final void newHistoryState() {
        this.historyState = new HistoryState();
        this.historyStates.add(this.historyState);
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final void setName(final String name) {
        this.name = name;
    }
    
    public final String getEmail() {
        return this.email;
    }
    
    public final void setEmail(final String email) {
        this.email = email;
    }
    
    public final Type getType() {
        return this.type;
    }
    
    public final void setType(final Type type) {
        this.type = type;
    }
    
    public final void setType(final String s) {
        this.type = Type.fromString(s);
    }
    
    public final void setStock(final int stock, final int n) {
        this.historyStates.get(n - 1).setStock(stock);
    }
    
    public final int getStock(final int n) {
        if (n <= 0) {
            return 12;
        }
        return this.historyStates.get(n - 1).getStock();
    }
    
    public final void setIncomingOrder(final int incomingOrder, final int n) {
        this.historyStates.get(n - 1).setIncomingOrder(incomingOrder);
    }
    
    public final int getIncomingOrder(final int n) {
        if (n <= 0) {
            return 4;
        }
        return this.historyStates.get(n - 1).getIncomingOrder();
    }
    
    public final void setIncomingStock(final int incomingStock, final int n) {
        this.historyStates.get(n - 1).setIncomingStock(incomingStock);
    }
    
    public final int getIncomingStock(final int n) {
        if (n <= 0) {
            return 4;
        }
        return this.historyStates.get(n - 1).getIncomingStock();
    }
    
    public final void setOutgoingOrder(final int outgoingOrder, final int n) {
        this.historyStates.get(n - 1).setOutgoingOrder(outgoingOrder);
    }
    
    public final int getOutgoingOrder(final int n) {
        if (n <= 0) {
            return 4;
        }
        return this.historyStates.get(n - 1).getOutgoingOrder();
    }
    
    public final void setOutgoingStock(final int outgoingStock, final int n) {
        this.historyStates.get(n - 1).setOutgoingStock(outgoingStock);
    }
    
    public final int getOutgoingStock(final int n) {
        if (n <= 0) {
            return 4;
        }
        return this.historyStates.get(n - 1).getOutgoingStock();
    }
    
    public final void setResult(final int result, final int n) {
        this.historyStates.get(n - 1).setResult(result);
    }
    
    public final int getResult(final int n) {
        if (n <= 0) {
            return 0;
        }
        return this.historyStates.get(n - 1).getResult();
    }
    
    public final void setJoinedGame(final Game joinedGame) {
        this.joinedGame = joinedGame;
    }
    
    public final Game getJoinedGame() {
        return this.joinedGame;
    }
    
    public final void setReadyForNextTurn(final boolean readyForNextTurn) {
        this.readyForNextTurn = readyForNextTurn;
    }
    
    public final boolean isReadyForNextTurn() {
        return this.readyForNextTurn;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Player && this.name != null && this.name.equals(((Player)o).name);
    }
    
    @Override
    public int hashCode() {
        if (this.name != null) {
            return this.name.hashCode();
        }
        return 0;
    }
    
    public enum Type
    {
        RETAILER, 
        WHOLESALER, 
        DISTRIBUTOR, 
        PRODUCER;
        
        public static Type fromString(final String s) {
            try {
                return valueOf(s);
            }
            catch (Exception ex) {
                return null;
            }
        }
        
        public final int toInt() {
            return this.ordinal();
        }
        
        public static Type fromInt(final int n) {
            return values()[n];
        }
    }
}
