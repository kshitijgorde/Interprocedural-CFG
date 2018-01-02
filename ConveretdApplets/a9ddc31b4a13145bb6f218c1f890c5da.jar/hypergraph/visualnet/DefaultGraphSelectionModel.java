// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.util.Iterator;
import hypergraph.graphApi.Element;
import java.util.HashSet;
import javax.swing.event.EventListenerList;
import java.util.Set;
import hypergraph.graphApi.Graph;

public class DefaultGraphSelectionModel implements GraphSelectionModel
{
    Graph graph;
    Set selectedElements;
    EventListenerList listenerList;
    static /* synthetic */ Class class$hypergraph$visualnet$GraphSelectionListener;
    
    public DefaultGraphSelectionModel(final Graph graph) {
        this.setGraph(graph);
        this.selectedElements = new HashSet();
        this.listenerList = new EventListenerList();
    }
    
    void setGraph(final Graph graph) {
        this.graph = graph;
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public void addSelectionEventListener(final GraphSelectionListener graphSelectionListener) {
        this.listenerList.add((DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener == null) ? (DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener = class$("hypergraph.visualnet.GraphSelectionListener")) : DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener, graphSelectionListener);
    }
    
    public void removeSelectionEventListener(final GraphSelectionListener graphSelectionListener) {
        this.listenerList.remove((DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener == null) ? (DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener = class$("hypergraph.visualnet.GraphSelectionListener")) : DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener, graphSelectionListener);
    }
    
    void fireSelectionChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener == null) ? (DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener = class$("hypergraph.visualnet.GraphSelectionListener")) : DefaultGraphSelectionModel.class$hypergraph$visualnet$GraphSelectionListener)) {
                ((GraphSelectionListener)listenerList[i + 1]).valueChanged(new GraphSelectionEvent(this));
            }
        }
    }
    
    public void clearSelection() {
        this.selectedElements.clear();
        this.fireSelectionChanged();
    }
    
    public void addSelectionElement(final Element element) {
        this.selectedElements.add(element);
        this.fireSelectionChanged();
    }
    
    public void removeSelectionElement(final Element element) {
        this.selectedElements.remove(element);
        this.fireSelectionChanged();
    }
    
    public boolean isElementSelected(final Element element) {
        return this.selectedElements.contains(element);
    }
    
    public Iterator getSelectionElementIterator() {
        return this.selectedElements.iterator();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
