// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Packet;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.tp.graph.Endpoint;
import com.stonewall.cornerstone.tp.graph.GraphObject;
import com.stonewall.cornerstone.tp.graph.Frame;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import com.stonewall.cornerstone.tp.graph.Constraint;

public class AddressConstraint implements Constraint
{
    @Override
    public boolean match(final BasicWalker w, final Frame frame) {
        boolean result = false;
        final Endpoint endpoint = frame.endpoint();
        if (this.privatePacket(frame) && frame.inbound() && endpoint.type(GraphObject.T.Network)) {
            final IpAddr a = endpoint.getAddress();
            result = (a != null && a.isPublic());
        }
        return result;
    }
    
    boolean privatePacket(final Frame frame) {
        boolean result = false;
        final Packet packet = frame.getPacket();
        if (packet != null) {
            result = (packet.dst != null && packet.dst.getAddress().isPrivate());
        }
        return result;
    }
}
