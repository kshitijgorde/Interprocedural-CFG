// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import com.google.common.collect.Ordering;
import java.util.Iterator;
import com.google.common.collect.Collections2;
import java.util.Set;
import java.util.Map;
import com.google.common.base.Predicate;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;

public class Dijkstra<T extends Node<T>> extends AbstractPathFinder<T>
{
    public List<T> findPath(final Collection<T> graph, final T start, final Collection<T> goals) {
        this.canceled = false;
        final Map<T, State> states = new HashMap<T, State>();
        final Set<T> Q = new HashSet<T>((Collection<? extends T>)graph);
        for (final T n : graph) {
            states.put(n, new State((Node)n, (State)null, Double.POSITIVE_INFINITY));
        }
        states.get(start).dist = 0.0;
        final Predicate<Map.Entry<T, State>> notVisited = (Predicate<Map.Entry<T, State>>)new Predicate<Map.Entry<T, State>>() {
            public boolean apply(final Map.Entry<T, State> t) {
                return Q.contains(t.getKey());
            }
        };
        final Ordering<Map.Entry<T, State>> orderByEntryValue = Utilities.orderByEntryValue();
        while (!Q.isEmpty() && !this.canceled) {
            final Collection<Map.Entry<T, State>> inQ = (Collection<Map.Entry<T, State>>)Collections2.filter((Collection)states.entrySet(), (Predicate)notVisited);
            final Map.Entry<T, State> uEntry = (Map.Entry<T, State>)orderByEntryValue.min((Iterable)inQ);
            if (uEntry.getValue().dist == Double.POSITIVE_INFINITY) {
                break;
            }
            final T u = uEntry.getKey();
            final State state = uEntry.getValue();
            this.fireConsidered(new PathEvent<T>(this) {
                public List<T> getPath() {
                    return state.makePath();
                }
            });
            Q.remove(u);
            if (goals.contains(u)) {
                return state.makePath();
            }
            for (final T v : u.neighbors()) {
                final double alt = state.dist + u.traverseCost(v);
                final State vState = states.get(v);
                if (alt < vState.dist) {
                    vState.dist = alt;
                    vState.previous = (NodeState<T>)state;
                }
            }
        }
        return null;
    }
    
    public String name() {
        return "Dijkstra";
    }
    
    private class State extends NodeState<T> implements Comparable<State>
    {
        private double dist;
        
        private State(final T node, final State parent, final double dist) {
            super(node, parent);
            this.dist = dist;
        }
        
        public int compareTo(final State other) {
            return (int)(this.dist - other.dist);
        }
    }
}
