// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

public class NullDevice implements IfcDevice, Serializable
{
    private static final long serialVersionUID = 2224011782042263586L;
    
    public String name() {
        return "NULL";
    }
    
    public void reset() {
    }
    
    public void install(final JSSystem system) {
    }
    
    public int peek(final int address) {
        assert false;
        return 0;
    }
    
    public void poke(final int address, final int aByteValue) {
        assert false;
    }
    
    public boolean save(final DataOutputStream out) {
        return true;
    }
    
    public boolean load(final DataInputStream in) {
        return true;
    }
    
    public void systemCyclesReset() {
    }
}
