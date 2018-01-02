// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.hyperbolic.ModelPoint;
import hypergraph.graphApi.Node;
import java.util.HashMap;
import javax.swing.event.EventListenerList;
import java.util.Map;

public class DefaultGraphLayoutModel implements GraphLayoutModel
{
    Map nodePositions;
    EventListenerList listenerList;
    private boolean valid;
    static /* synthetic */ Class class$hypergraph$visualnet$GraphLayoutListener;
    
    public DefaultGraphLayoutModel() {
        this.clearNodePositions();
        this.listenerList = new EventListenerList();
    }
    
    public void setValid(final boolean valid) {
        if (valid != this.valid) {
            this.fireLayoutChanged();
        }
        this.valid = valid;
    }
    
    public boolean isValid() {
        return this.valid;
    }
    
    public void addLayoutEventListener(final GraphLayoutListener graphLayoutListener) {
        this.listenerList.add((DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener == null) ? (DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener = class$("hypergraph.visualnet.GraphLayoutListener")) : DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener, graphLayoutListener);
    }
    
    public void removeLayoutEventListener(final GraphLayoutListener graphLayoutListener) {
        this.listenerList.remove((DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener == null) ? (DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener = class$("hypergraph.visualnet.GraphLayoutListener")) : DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener, graphLayoutListener);
    }
    
    public void fireLayoutChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener == null) ? (DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener = class$("hypergraph.visualnet.GraphLayoutListener")) : DefaultGraphLayoutModel.class$hypergraph$visualnet$GraphLayoutListener)) {
                ((GraphLayoutListener)listenerList[i + 1]).valueChanged(new GraphLayoutEvent(this));
            }
        }
    }
    
    public void clearNodePositions() {
        this.nodePositions = new HashMap();
    }
    
    public void setNodePosition(final Node node, final ModelPoint modelPoint) {
        this.nodePositions.put(node, modelPoint);
        this.fireLayoutChanged();
    }
    
    public ModelPoint getNodePosition(final Node node) {
        return this.nodePositions.get(node);
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
