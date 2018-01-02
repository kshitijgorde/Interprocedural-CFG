// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.camel.component;

import java.io.File;
import org.apache.camel.Endpoint;
import java.util.Map;
import org.apache.camel.impl.DefaultComponent;

public class JournalComponent extends DefaultComponent
{
    protected Endpoint createEndpoint(final String uri, final String remaining, final Map parameters) throws Exception {
        final JournalEndpoint endpoint = new JournalEndpoint(uri, this, new File(remaining));
        this.setProperties((Object)endpoint, parameters);
        return (Endpoint)endpoint;
    }
}
