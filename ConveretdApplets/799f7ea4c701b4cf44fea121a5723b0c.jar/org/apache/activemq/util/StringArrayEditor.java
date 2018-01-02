// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.beans.PropertyEditorSupport;

public class StringArrayEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(final String text) {
        if (text == null || text.length() == 0) {
            this.setValue(null);
        }
        else {
            final StringTokenizer stok = new StringTokenizer(text, ",");
            final List<String> list = new ArrayList<String>();
            while (stok.hasMoreTokens()) {
                list.add(stok.nextToken());
            }
            final Object array = list.toArray(new String[list.size()]);
            this.setValue(array);
        }
    }
    
    @Override
    public String getAsText() {
        final Object[] objects = (Object[])this.getValue();
        if (objects == null || objects.length == 0) {
            return null;
        }
        final StringBuffer result = new StringBuffer(String.valueOf(objects[0]));
        for (int i = 1; i < objects.length; ++i) {
            result.append(",").append(objects[i]);
        }
        return result.toString();
    }
}
