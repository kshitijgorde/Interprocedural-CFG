// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class FrameCollection extends ArrayList<List<Frame>>
{
    static final long serialVersionUID = 0L;
    
    public List<Frame> flatten() {
        final List<Frame> result = new ArrayList<Frame>();
        for (final List<Frame> list : this) {
            result.addAll(list);
        }
        Collections.sort(result, new FrameSort());
        return result;
    }
    
    public PathCollection pathCollection() {
        final PathCollection result = new PathCollection();
        for (final List<Frame> p : this) {
            result.add(this.path(p));
        }
        return result;
    }
    
    public Collection<Link> tunnels() {
        final Set<Link> result = new HashSet<Link>();
        for (final List<Frame> path : this) {
            for (final Frame frame : path) {
                final Link link = frame.link();
                if (link != null && link.tunnel()) {
                    result.add(link);
                }
            }
        }
        return result;
    }
    
    public void sortPathsByMetric() {
        Collections.sort((List<Object>)this, (Comparator<? super Object>)new ShortestPath());
    }
    
    public Set<Endpoint> referencedEndpoints() {
        final Set<Endpoint> result = new HashSet<Endpoint>(this.size());
        for (final List<Frame> list : this) {
            for (final Frame f : list) {
                result.add(f.endpoint());
            }
        }
        return result;
    }
    
    public boolean notEmpty() {
        return !this.isEmpty();
    }
    
    private List<PathElement> path(final List<Frame> path) {
        final List<PathElement> result = new ArrayList<PathElement>();
        for (final Frame f : path) {
            switch (f.direction()) {
                default: {
                    continue;
                }
                case Inbound: {
                    final Link link = f.link();
                    if (link != null) {
                        result.add(new PathElement(f, true));
                    }
                    result.add(new PathElement(f));
                    continue;
                }
                case Outbound: {
                    result.add(new PathElement(f));
                    continue;
                }
            }
        }
        return result;
    }
    
    class FrameSort implements Comparator<Frame>
    {
        @Override
        public int compare(final Frame a, final Frame b) {
            if (b.depth() > a.depth()) {
                return -1;
            }
            if (b.depth() < a.depth()) {
                return 1;
            }
            if (b.sequence() > a.sequence()) {
                return -1;
            }
            if (b.sequence() < a.sequence()) {
                return 1;
            }
            return 0;
        }
    }
    
    class ShortestPath implements Comparator<List<Frame>>
    {
        @Override
        public int compare(final List<Frame> a, final List<Frame> b) {
            final Integer metricA = a.get(a.size() - 1).metric();
            final Integer metricB = b.get(b.size() - 1).metric();
            return metricA.compareTo(metricB);
        }
    }
}
