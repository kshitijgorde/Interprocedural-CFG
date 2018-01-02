// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

import com.masystem.beergame.Config;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Game implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final Map<String, Player> playerMap;
    private final String name;
    private int week;
    
    public Game(final String name) {
        this.name = name;
        this.playerMap = new HashMap<String, Player>();
        this.week = 0;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final synchronized void addPlayer(final Player player) {
        this.playerMap.put(player.getName(), player);
    }
    
    public final synchronized Player removePlayer(final Player player) {
        return this.playerMap.remove(player.getName());
    }
    
    public final synchronized Player getPlayer(final String s) {
        return this.playerMap.get(s);
    }
    
    public final synchronized Player getPlayer(final Player.Type type) {
        final Iterator<Player> iterator = this.getPlayerList().iterator();
        while (iterator.hasNext()) {
            final Player player;
            if ((player = iterator.next()).getType() == type) {
                return player;
            }
        }
        return null;
    }
    
    public final synchronized boolean isReadyForNextTurn() {
        final Iterator<Player> iterator = this.getPlayerList().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isReadyForNextTurn()) {
                return false;
            }
        }
        return true;
    }
    
    public final synchronized int getNbrPlayers() {
        return this.playerMap.size();
    }
    
    public final boolean isReadyForStart() {
        return this.getNbrPlayers() == 4;
    }
    
    public final int getWeek() {
        return this.week;
    }
    
    public final void setWeek(final int week) {
        this.week = week;
    }
    
    public final Collection<Player> getPlayerList() {
        return this.playerMap.values();
    }
    
    public final Collection<String> getPlayerNameList() {
        return this.playerMap.keySet();
    }
    
    public final int getStock(final Player player, final int n) {
        return player.getStock(n - 1) + this.getIncomingStock(player, n) - this.getIncomingOrder(player, n);
    }
    
    public final int getResult(final Player player, final int n) {
        final int stockCost = Costs.getStockCost(this.getStock(player, n));
        if (Config.RESULT_TYPE == Config.ResultType.TRANSACTIONS_AND_STOCK) {
            return player.getResult(n - 1) + Costs.getSellRevenue(this.getOutgoingStock(player, n)) - Costs.getPurchaseCost(this.getIncomingStock(player, n)) - stockCost;
        }
        return player.getResult(n - 1) - stockCost;
    }
    
    public final int getOutgoingStock(final Player player, final int n) {
        final int stock;
        final boolean b;
        return Math.min(((-(b = (((stock < 0) ? stock : false) != 0))) ? 1 : 0) + this.getIncomingOrder(player, n), (((stock = player.getStock(n - 1)) > 0) ? stock : false) + this.getIncomingStock(player, n));
    }
    
    public final int getIncomingStock(Player player, final int n) {
        switch (player.getType()) {
            case RETAILER: {
                player = this.getPlayer(Player.Type.WHOLESALER);
                break;
            }
            case WHOLESALER: {
                player = this.getPlayer(Player.Type.DISTRIBUTOR);
                break;
            }
            case DISTRIBUTOR: {
                player = this.getPlayer(Player.Type.PRODUCER);
                break;
            }
            default: {
                return player.getOutgoingOrder(n - 3);
            }
        }
        return this.getOutgoingStock(player, n - 2);
    }
    
    public final int getIncomingOrder(Player player, final int n) {
        switch (player.getType()) {
            case WHOLESALER: {
                player = this.getPlayer(Player.Type.RETAILER);
                break;
            }
            case DISTRIBUTOR: {
                player = this.getPlayer(Player.Type.WHOLESALER);
                break;
            }
            case PRODUCER: {
                player = this.getPlayer(Player.Type.DISTRIBUTOR);
                break;
            }
            default: {
                if (n <= 4) {
                    return 4;
                }
                return 8;
            }
        }
        return player.getOutgoingOrder(n - 2);
    }
}
