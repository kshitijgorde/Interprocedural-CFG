// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.graphApi.Element;
import java.util.Iterator;

public interface GraphSelectionModel
{
    void addSelectionEventListener(final GraphSelectionListener p0);
    
    void removeSelectionEventListener(final GraphSelectionListener p0);
    
    Iterator getSelectionElementIterator();
    
    boolean isElementSelected(final Element p0);
    
    void addSelectionElement(final Element p0);
    
    void removeSelectionElement(final Element p0);
    
    void clearSelection();
}
