// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.write;

import java.util.Iterator;
import java.util.Map;
import org.apache.mina.util.MapBackedSet;
import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.io.IOException;

public class WriteException extends IOException
{
    private static final long serialVersionUID = -4174407422754524197L;
    
    public WriteException(WriteRequest writeRequest) {
        if ((writeRequest = writeRequest) == null) {
            throw new IllegalArgumentException("request");
        }
        final ArrayList<Object> list;
        (list = new ArrayList<Object>(1)).add(writeRequest.getOriginalRequest());
        Collections.unmodifiableList((List<?>)list);
    }
    
    public WriteException(final Collection<WriteRequest> collection) {
        asRequestList(collection);
    }
    
    private static List<WriteRequest> asRequestList(final Collection<WriteRequest> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("requests");
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("requests is empty.");
        }
        final MapBackedSet<WriteRequest> set = new MapBackedSet<WriteRequest>(new LinkedHashMap<WriteRequest, Boolean>());
        final Iterator<WriteRequest> iterator = collection.iterator();
        while (iterator.hasNext()) {
            set.add(iterator.next().getOriginalRequest());
        }
        return (List<WriteRequest>)Collections.unmodifiableList((List<?>)new ArrayList<Object>(set));
    }
}
