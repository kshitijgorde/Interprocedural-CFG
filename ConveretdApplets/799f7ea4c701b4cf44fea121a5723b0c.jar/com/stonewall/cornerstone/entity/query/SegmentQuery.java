// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import com.stonewall.cornerstone.tp.graph.GraphObject;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.tp.graph.Endpoint;
import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.tp.graph.Link;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.Segment;
import java.util.List;

public class SegmentQuery
{
    public List<Segment> getConnected(final String ifaceId) {
        final List<Segment> segments = new ArrayList<Segment>();
        final GraphQuery query = new GraphQuery();
        final Interface iface = query.getInterface(ifaceId);
        final Collection<Link> links = iface.getLinks();
        for (final Link link : links) {
            if (!link.tunnel()) {
                segments.add(this.segment(link));
            }
        }
        return segments;
    }
    
    private Segment segment(final Link link) {
        final Segment segment = new Segment(link.getId());
        final Endpoint[] eps = link.endpoints();
        final EntityReference ep1 = this.reference(eps[0]);
        final EntityReference ep2 = this.reference(eps[1]);
        segment.setEndpoints(ep1, ep2);
        return segment;
    }
    
    private EntityReference reference(final Endpoint ep) {
        switch (ep.type()) {
            case Network: {
                return new EntityReference(EntityFactory.EntityType.network, ep.getId());
            }
            case Interface: {
                return new EntityReference(EntityFactory.EntityType.ipInterface, ep.getId());
            }
            default: {
                return null;
            }
        }
    }
}
