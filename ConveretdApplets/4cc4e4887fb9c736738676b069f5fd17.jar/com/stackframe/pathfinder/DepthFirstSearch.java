// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;

public class DepthFirstSearch<T extends Node<T>> extends AbstractPathFinder<T>
{
    public List<T> findPath(final Collection<T> graph, final T start, final Collection<T> goals) {
        this.canceled = false;
        return this.search(new NodeState<T>(start, null), new HashSet<T>(), goals);
    }
    
    private List<T> search(final NodeState<T> state, final Set<T> visited, final Collection<T> goals) {
        if (this.canceled) {
            return null;
        }
        visited.add(state.node);
        this.fireConsidered(new PathEvent<T>(this) {
            public List<T> getPath() {
                return state.makePath();
            }
        });
        if (goals.contains(state.node)) {
            return state.makePath();
        }
        for (final T neighbor : state.node.neighbors()) {
            if (!visited.contains(neighbor)) {
                final List<T> path = this.search(new NodeState<T>(neighbor, state), visited, goals);
                if (path != null) {
                    return path;
                }
                continue;
            }
        }
        return null;
    }
    
    public String name() {
        return "Depth First";
    }
}
