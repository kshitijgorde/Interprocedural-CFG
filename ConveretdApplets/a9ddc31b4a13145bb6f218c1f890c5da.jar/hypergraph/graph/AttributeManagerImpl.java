// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import java.util.HashMap;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.Graph;
import java.util.Map;
import hypergraph.graphApi.AttributeManager;

public class AttributeManagerImpl implements AttributeManager
{
    private Map attributesForName;
    private Graph graph;
    
    AttributeManagerImpl(final Graph graph) {
        this.attributesForName = null;
        this.setGraph(graph);
    }
    
    public Object getAttribute(final String s, final Element element) {
        if (this.attributesForName != null) {
            final Map<Object, Object> map = this.attributesForName.get(s);
            if (map != null) {
                final Object value = map.get(element);
                if (value != null) {
                    return value;
                }
                final Object value2 = map.get(element.getGroup());
                if (value2 != null) {
                    return value2;
                }
                final Object value3 = map.get(this.getGraph());
                if (value3 != null) {
                    return value3;
                }
            }
        }
        return null;
    }
    
    public void setAttribute(final String s, final Element element, final Object o) {
        if (this.attributesForName == null) {
            this.attributesForName = new HashMap();
        }
        Map<Element, Object> map = this.attributesForName.get(s);
        if (map == null) {
            map = new HashMap<Element, Object>();
            this.attributesForName.put(s, map);
        }
        map.put(element, o);
    }
    
    public String toString() {
        String string = "[ AttributeManager : \n";
        if (this.attributesForName != null) {
            string = string + "  Attribute names : " + this.attributesForName.keySet() + " ]\n";
        }
        return string + " ]\n";
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public void setGraph(final Graph graph) {
        this.graph = graph;
    }
}
