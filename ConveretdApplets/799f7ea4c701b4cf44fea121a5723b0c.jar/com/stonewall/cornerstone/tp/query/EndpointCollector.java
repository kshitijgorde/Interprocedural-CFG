// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.BasicWalker;
import java.util.ArrayList;
import com.stonewall.cornerstone.tp.graph.Frame;
import java.util.List;
import com.stonewall.cornerstone.tp.graph.GraphObject;
import com.stonewall.cornerstone.tp.graph.Constraint;

public class EndpointCollector implements Constraint
{
    private final int metric;
    private final GraphObject.T type;
    private final List<Frame> list;
    
    public EndpointCollector(final GraphObject.T type, final int metric) {
        this.list = new ArrayList<Frame>();
        this.type = type;
        this.metric = metric;
    }
    
    public EndpointCollector(final GraphObject.T type) {
        this(type, Integer.MAX_VALUE);
    }
    
    @Override
    public boolean match(final BasicWalker w, final Frame frame) {
        if (frame.inbound() && frame.endpoint().type(this.type)) {
            this.list.add(frame);
        }
        return frame.metric() > this.metric;
    }
    
    public List<Frame> list() {
        return this.list;
    }
}
