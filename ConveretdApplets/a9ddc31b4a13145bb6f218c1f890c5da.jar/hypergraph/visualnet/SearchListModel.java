// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.util.TreeMap;
import java.util.SortedMap;
import javax.swing.AbstractListModel;

public class SearchListModel extends AbstractListModel
{
    SortedMap dataBase;
    
    public SearchListModel(SortedMap dataBase) {
        if (dataBase == null) {
            dataBase = new TreeMap();
        }
        this.dataBase = dataBase;
        this.fireIntervalAdded(this, 0, dataBase.size() - 1);
    }
    
    public void setDataBase(final SortedMap dataBase) {
        this.fireIntervalRemoved(this, 0, this.dataBase.size() - 1);
        this.dataBase = dataBase;
        this.fireIntervalAdded(this, 0, dataBase.size() - 1);
    }
    
    public Object getElementAt(final int n) {
        return this.dataBase.keySet().toArray()[n];
    }
    
    public int getSize() {
        return this.dataBase.size();
    }
}
