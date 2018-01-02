// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual;

import prefuse.Visualization;
import prefuse.data.tuple.TupleSet;

public interface VisualTupleSet extends TupleSet
{
    Visualization getVisualization();
    
    String getGroup();
}
