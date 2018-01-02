// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.Iterator;
import com.google.common.collect.Ordering;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;

public class AStar<T extends Node<T>> extends AbstractPathFinder<T>
{
    public List<T> findPath(final Collection<T> graph, final T start, final Collection<T> goals) {
        this.canceled = false;
        final Map<T, State> open = new HashMap<T, State>();
        final Map<T, State> closed = new HashMap<T, State>();
        final State startState = new State((Node)start, 0.0, (State)null, (Collection)goals);
        open.put(start, startState);
        final Ordering<Map.Entry<T, State>> orderByEntryValue = Utilities.orderByEntryValue();
        while (!open.isEmpty() && !this.canceled) {
            final State state = open.remove(((Map.Entry)orderByEntryValue.min((Iterable)open.entrySet())).getKey());
            this.fireConsidered(new PathEvent<T>(this) {
                public List<T> getPath() {
                    return state.makePath();
                }
            });
            if (goals.contains(state.node)) {
                return state.makePath();
            }
            for (final T newNode : state.node.neighbors()) {
                final double newCost = state.costFromStart + state.node.traverseCost((T)newNode);
                final State openNode = open.get(newNode);
                if (openNode != null && openNode.costFromStart <= newCost) {
                    continue;
                }
                final State closedNode = closed.get(newNode);
                if (closedNode != null && closedNode.costFromStart <= newCost) {
                    continue;
                }
                if (closedNode != null) {
                    closed.remove(newNode);
                }
                if (openNode != null) {
                    open.remove(newNode);
                }
                final State newState = new State((Node)newNode, newCost, state, (Collection)goals);
                open.put(newNode, newState);
            }
            closed.put((T)state.node, state);
        }
        return null;
    }
    
    public String name() {
        return "A*";
    }
    
    private class State extends NodeState<T> implements Comparable<State>
    {
        private final double costFromStart;
        private final double costToGoal;
        
        private State(final T node, final double costFromStart, final State parent, final Collection<T> goals) {
            super(node, parent);
            this.costFromStart = costFromStart;
            this.costToGoal = this.minimumPathCostEstimate(node, goals);
        }
        
        private double minimumPathCostEstimate(final T node, final Collection<T> goals) {
            double min = Double.MAX_VALUE;
            for (final T goal : goals) {
                final double cost = node.pathCostEstimate(goal);
                if (cost < min) {
                    min = cost;
                }
            }
            return min;
        }
        
        private double totalCost() {
            return this.costFromStart + this.costToGoal;
        }
        
        public int compareTo(final State other) {
            return (int)(this.totalCost() - other.totalCost());
        }
    }
}
