// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.protobuf;

public interface PBMessage<Bean, Buffer extends MessageBuffer>
{
    Bean copy();
    
    boolean frozen();
    
    Buffer freeze();
}
