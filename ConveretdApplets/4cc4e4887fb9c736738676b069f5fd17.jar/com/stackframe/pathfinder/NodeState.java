// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.LinkedList;
import java.util.List;

class NodeState<T extends Node<T>>
{
    final T node;
    NodeState<T> previous;
    
    NodeState(final T node, final NodeState<T> previous) {
        this.node = node;
        this.previous = previous;
    }
    
    List<T> makePath() {
        final List<T> result = new LinkedList<T>();
        for (NodeState<T> s = this; s != null; s = s.previous) {
            result.add(0, s.node);
        }
        return result;
    }
}
