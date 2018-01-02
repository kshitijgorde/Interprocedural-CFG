// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import org.apache.activemq.command.ActiveMQDestination;
import java.util.ArrayList;
import org.springframework.util.StringUtils;
import java.beans.PropertyEditorSupport;

public class ListEditor extends PropertyEditorSupport
{
    public static final String DEFAULT_SEPARATOR = ",";
    
    @Override
    public String getAsText() {
        return this.getValue().toString();
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.substring(1, text.length() - 1);
        final String[] array = StringUtils.delimitedListToStringArray(text, ",", (String)null);
        final ArrayList<ActiveMQDestination> list = new ArrayList<ActiveMQDestination>();
        for (final String item : array) {
            list.add(ActiveMQDestination.createDestination(item.trim(), (byte)1));
        }
        this.setValue(list);
    }
}
