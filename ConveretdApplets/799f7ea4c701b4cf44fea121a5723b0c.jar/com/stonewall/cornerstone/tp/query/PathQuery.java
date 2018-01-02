// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import java.util.Arrays;
import java.util.Iterator;
import com.stonewall.cornerstone.tp.graph.Endpoint;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import java.util.Set;
import com.stonewall.cornerstone.tp.graph.GraphObject;
import com.stonewall.cornerstone.tp.graph.Frame;
import java.util.List;
import com.stonewall.cornerstone.tp.graph.NetObject;
import com.stonewall.cornerstone.tp.graph.Packet;
import com.stonewall.cornerstone.tp.graph.Constraint;
import java.util.HashSet;
import java.util.Collection;
import com.stonewall.cornerstone.tp.graph.Graph;
import org.xmodel.log.Log;

public class PathQuery extends GraphQuery
{
    public static final Log log;
    
    static {
        log = Log.getLog(PathQuery.class);
    }
    
    public PathQuery() {
        this(Graph.instance());
    }
    
    public PathQuery(final Graph graph) {
        super(graph);
    }
    
    public Collection<Path> getPathsBetween(final String eA, final String eB) {
        PathQuery.log.debug(String.valueOf(eA) + "/" + eB);
        final Set<Path> result = new HashSet<Path>();
        final BasicWalker walker = this.getWalker();
        final Endpoint a = this.graph.getEndpoint(eA);
        final Endpoint b = this.graph.getEndpoint(eB);
        if (a == null || b == null) {
            return result;
        }
        walker.add(new EndpointConstraint(b));
        walker.setPacket(new Packet(a, b));
        walker.start(a);
        for (final List<Frame> frames : walker.getPaths()) {
            if (frames.size() < 2) {
                continue;
            }
            final Endpoint first = this.first(frames, GraphObject.T.Interface, Constraint.Direction.Outbound);
            final Endpoint last = this.last(frames, GraphObject.T.Interface, Constraint.Direction.Inbound);
            if (first == null || last == null) {
                continue;
            }
            result.add(new Path(first, last));
        }
        return result;
    }
    
    private Endpoint first(final List<Frame> frames, final GraphObject.T t, final Constraint.Direction d) {
        Endpoint result = null;
        for (final Frame f : frames) {
            final Endpoint e = f.endpoint();
            if (e.type(t) && f.direction() == d) {
                result = e;
                break;
            }
        }
        return result;
    }
    
    private Endpoint last(final List<Frame> frames, final GraphObject.T t, final Constraint.Direction d) {
        Endpoint result = null;
        for (final Frame f : frames) {
            final Endpoint e = f.endpoint();
            if (e.type(t) && f.direction() == d) {
                result = e;
            }
        }
        return result;
    }
    
    public class Path
    {
        private final Endpoint a;
        private final Endpoint b;
        
        Path(final Endpoint a, final Endpoint b) {
            final Endpoint[] sorted = this.sort(a, b);
            this.a = sorted[0];
            this.b = sorted[1];
        }
        
        public Endpoint getA() {
            return this.a;
        }
        
        public Endpoint getB() {
            return this.b;
        }
        
        @Override
        public String toString() {
            return "[" + this.a.getId() + " , " + this.b.getId() + "]";
        }
        
        @Override
        public boolean equals(final Object o) {
            final Path p = (Path)o;
            return this.a.getId().equals(p.a.getId()) && this.b.getId().equals(p.b.getId());
        }
        
        @Override
        public int hashCode() {
            return new String(String.valueOf(this.a.getId()) + this.b.getId()).hashCode();
        }
        
        private Endpoint[] sort(final Endpoint a, final Endpoint b) {
            final String[] ids = { a.getId(), b.getId() };
            Arrays.sort(ids);
            final Endpoint[] result = { (a.getId() == ids[0]) ? a : b, (a.getId() == ids[1]) ? a : b };
            return result;
        }
    }
}
