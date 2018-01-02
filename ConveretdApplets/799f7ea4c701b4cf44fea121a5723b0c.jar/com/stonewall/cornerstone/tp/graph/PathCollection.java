// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class PathCollection extends ArrayList<List<PathElement>>
{
    static final long serialVersionUID = 0L;
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("path-collection:\n");
        for (final List<PathElement> path : this) {
            sb.append("\tpath:\n");
            for (final PathElement e : path) {
                sb.append("\t\t");
                sb.append(e);
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
