// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.command;

public interface DataStructure
{
    byte getDataStructureType();
    
    boolean isMarshallAware();
}
