// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.algorithms;

import hypergraph.graphApi.Element;
import hypergraph.graphApi.Graph;
import javax.swing.event.EventListenerList;

public abstract class GraphWalker
{
    private EventListenerList listenerList;
    private Graph graph;
    static /* synthetic */ Class class$hypergraph$graphApi$algorithms$GraphWalkerListener;
    
    public GraphWalker() {
        this.listenerList = new EventListenerList();
    }
    
    public void setGraph(final Graph graph) {
        this.graph = graph;
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public void addListener(final GraphWalkerListener graphWalkerListener) {
        this.listenerList.add((GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener == null) ? (GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener = class$("hypergraph.graphApi.algorithms.GraphWalkerListener")) : GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener, graphWalkerListener);
    }
    
    public void removeListener(final GraphWalkerListener graphWalkerListener) {
        this.listenerList.remove((GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener == null) ? (GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener = class$("hypergraph.graphApi.algorithms.GraphWalkerListener")) : GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener, graphWalkerListener);
    }
    
    public void visitElement(final Element element) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener == null) ? (GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener = class$("hypergraph.graphApi.algorithms.GraphWalkerListener")) : GraphWalker.class$hypergraph$graphApi$algorithms$GraphWalkerListener)) {
                ((GraphWalkerListener)listenerList[i + 1]).visitElement(element);
            }
        }
    }
    
    public abstract void walk();
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
