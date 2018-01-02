// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.stackframe.pathfinder.Node;

public class Location implements Node<Location>, Serializable
{
    private final int x;
    private final int y;
    private int height;
    private boolean blocked;
    private transient List<Location> neighbors;
    
    public Location(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.neighbors = new ArrayList<Location>();
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setBlocked(final boolean blocked) {
        this.blocked = blocked;
    }
    
    public boolean getBlocked() {
        return this.blocked;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public double getDistance(final Location dest) {
        final double a = dest.x - this.x;
        final double b = dest.y - this.y;
        return Math.sqrt(a * a + b * b);
    }
    
    public double pathCostEstimate(final Location goal) {
        return this.getDistance(goal) * 0.99;
    }
    
    public double traverseCost(final Location target) {
        final double distance = this.getDistance(target);
        final double diff = target.getHeight() - this.getHeight();
        return Math.abs(diff) + distance;
    }
    
    public Iterable<Location> neighbors() {
        final List<Location> realNeighbors = new ArrayList<Location>();
        if (!this.blocked) {
            for (final Location loc : this.neighbors) {
                if (!loc.blocked) {
                    realNeighbors.add(loc);
                }
            }
        }
        return realNeighbors;
    }
    
    public void addNeighbor(final Location l) {
        this.neighbors.add(l);
    }
    
    public String toString() {
        return "{x=" + this.x + ",y=" + this.y + "}";
    }
    
    private synchronized void readObject(final ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.neighbors = new ArrayList<Location>();
    }
}
