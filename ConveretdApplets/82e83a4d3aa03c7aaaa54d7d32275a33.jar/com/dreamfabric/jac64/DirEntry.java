// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class DirEntry
{
    public String name;
    public int trk;
    public int sec;
    public int size;
    public int type;
    
    public DirEntry(final String name, final int trk, final int sec, final int size, final int type) {
        this.name = name;
        this.trk = trk;
        this.sec = sec;
        this.size = size;
        this.type = type;
    }
    
    public String getTypeString() {
        switch (this.type) {
            case 128: {
                return " DEL ";
            }
            case 129: {
                return " SEQ ";
            }
            case 130: {
                return " PRG ";
            }
            case 131: {
                return " USR ";
            }
            case 132: {
                return " REL ";
            }
            default: {
                return "---";
            }
        }
    }
    
    public String toString() {
        return this.name + " (" + this.getTypeString() + ") " + this.size;
    }
}
