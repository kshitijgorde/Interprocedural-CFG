// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

import java.util.BitSet;

public class Trap
{
    public boolean isDecoded;
    public int trapId;
    public int version;
    public String sender;
    public String enterprise;
    public Variable[] vars;
    
    Trap(final TrapEvent trapEvent) {
        throw new Error("Unresolved compilation problems: \n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tTrapEvent cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tTrapPduv1 cannot be resolved to a type\n\tTrapPduv1 cannot be resolved to a type\n\tTrapPduv1 cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnObject cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n");
    }
    
    void setOid(final Variable variable, final varbind varbind) {
        throw new Error("Unresolved compilation problem: \n\tvarbind cannot be resolved to a type\n");
    }
    
    void setValue(final Variable variable, final varbind varbind) {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tAsnObject cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n");
    }
    
    public class Variable
    {
        public String oid;
        public String value;
        public byte[] octets;
        public BitSet bits;
        
        public Variable(final Trap trap) {
            throw new Error("Unresolved compilation problems: \n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tTrapEvent cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tTrapPduv1 cannot be resolved to a type\n\tTrapPduv1 cannot be resolved to a type\n\tTrapPduv1 cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnObject cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n");
        }
    }
}
