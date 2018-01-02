import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

class Index extends Hashtable
{
    Vector IndexItemList;
    String Name;
    
    public Index(final String s) {
        this.Name = "";
        this.IndexItemList = new Vector();
        this.Name = new String(s);
    }
    
    public void AddToIndex(final Record record, final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",;.:?()&#: ");
        while (stringTokenizer.hasMoreTokens()) {
            final String lowerCase = stringTokenizer.nextToken().toLowerCase();
            if (lowerCase.length() > 1) {
                if (this.containsKey(lowerCase)) {
                    this.IndexItemList = (Vector)this.get(lowerCase);
                }
                else {
                    this.put(lowerCase, this.IndexItemList = new Vector());
                }
                this.IndexItemList.addElement(record);
            }
        }
    }
}
