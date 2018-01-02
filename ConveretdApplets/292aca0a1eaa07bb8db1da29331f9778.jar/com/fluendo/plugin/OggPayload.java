// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import java.util.Vector;
import com.jcraft.jogg.Packet;

interface OggPayload
{
    boolean isType(final Packet p0);
    
    int takeHeader(final Packet p0);
    
    boolean isHeader(final Packet p0);
    
    boolean isKeyFrame(final Packet p0);
    
    long getFirstTs(final Vector p0);
    
    long granuleToTime(final long p0);
    
    String getMime();
}
