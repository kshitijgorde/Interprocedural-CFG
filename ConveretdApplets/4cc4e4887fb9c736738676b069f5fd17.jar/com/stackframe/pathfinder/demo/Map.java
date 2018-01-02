// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.io.Serializable;

public class Map implements Serializable
{
    private final Location[][] nodes;
    private static final Random rng;
    
    public Location getLocation(final int x, final int y) {
        return this.nodes[x][y];
    }
    
    public int getXSize() {
        return this.nodes.length;
    }
    
    public int getYSize() {
        return this.nodes[0].length;
    }
    
    public Collection<Location> getLocations() {
        final Collection<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < this.nodes.length; ++i) {
            for (int j = 0; j < this.nodes[0].length; ++j) {
                locations.add(this.nodes[i][j]);
            }
        }
        return locations;
    }
    
    private void attachNeighbors() {
        for (int i = 0; i < this.nodes.length; ++i) {
            for (int j = 0; j < this.nodes[0].length; ++j) {
                final Location t = this.nodes[i][j];
                if (i != 0) {
                    t.addNeighbor(this.nodes[i - 1][j]);
                    if (j != 0) {
                        t.addNeighbor(this.nodes[i - 1][j - 1]);
                    }
                    if (j != this.nodes[0].length - 1) {
                        t.addNeighbor(this.nodes[i - 1][j + 1]);
                    }
                }
                if (i != this.nodes.length - 1) {
                    t.addNeighbor(this.nodes[i + 1][j]);
                    if (j != 0) {
                        t.addNeighbor(this.nodes[i + 1][j - 1]);
                    }
                    if (j != this.nodes[0].length - 1) {
                        t.addNeighbor(this.nodes[i + 1][j + 1]);
                    }
                }
                if (j != 0) {
                    t.addNeighbor(this.nodes[i][j - 1]);
                }
                if (j != this.nodes[0].length - 1) {
                    t.addNeighbor(this.nodes[i][j + 1]);
                }
            }
        }
    }
    
    public void randomize() {
        for (int i = 0; i < this.nodes.length; ++i) {
            for (int j = 0; j < this.nodes[0].length; ++j) {
                if (Map.rng.nextInt(4) == 0) {
                    final Location t = this.nodes[i][j];
                    if (Map.rng.nextInt(2) == 0) {
                        t.setHeight(100);
                    }
                    else {
                        t.setBlocked(true);
                    }
                }
            }
        }
    }
    
    public Map(final int bounds) {
        this.nodes = new Location[bounds][bounds];
        for (int i = 0; i < bounds; ++i) {
            for (int j = 0; j < bounds; ++j) {
                this.nodes[i][j] = new Location(i, j);
            }
        }
        this.attachNeighbors();
    }
    
    private synchronized void readObject(final ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.attachNeighbors();
    }
    
    static {
        rng = new Random();
    }
}
