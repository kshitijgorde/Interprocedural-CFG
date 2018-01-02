// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Enumeration;
import java.util.Hashtable;

public class SimpleHistogram
{
    private float[] data;
    
    public float calculatePeek() {
        final Hashtable temp = new Hashtable();
        for (int i = 0; i < this.data.length; ++i) {
            final Integer key = new Integer(Math.round(this.data[i]));
            Integer count;
            if (temp.containsKey(key)) {
                count = temp.get(key);
            }
            else {
                count = new Integer(0);
            }
            count = new Integer(count + 1);
            temp.put(key, count);
        }
        float max = -1.0f;
        float freqInterval = -1.0f;
        final Enumeration en = temp.keys();
        while (en.hasMoreElements()) {
            final Integer key2 = en.nextElement();
            final Integer count2 = temp.get(key2);
            if (count2 > max) {
                max = count2;
                freqInterval = key2;
            }
        }
        return freqInterval;
    }
    
    public float[] getData() {
        return this.data;
    }
    
    public void setData(final float[] data) {
        this.data = data;
    }
}
