// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.server;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.TreeMap;
import com.masystem.beergame.network.protocol.Game;
import java.util.Map;

public class GameList
{
    private final Map<String, Game> games;
    
    public GameList() {
        this.games = new TreeMap<String, Game>();
    }
    
    public final Game createGame(final String s) {
        final Game game = new Game(s);
        this.games.put(s.toLowerCase(), game);
        return game;
    }
    
    public final SortedSet<String> getGameList() {
        final TreeSet<String> set = new TreeSet<String>();
        final Iterator<Game> iterator = this.games.values().iterator();
        while (iterator.hasNext()) {
            set.add(iterator.next().getName());
        }
        return set;
    }
    
    public final Game getGame(final String s) {
        return this.games.get(s.toLowerCase());
    }
    
    public final void destroyGame(final String s) {
        this.games.remove(s.toLowerCase());
    }
    
    public final boolean containsGame(final String s) {
        return this.getGame(s) != null;
    }
}
