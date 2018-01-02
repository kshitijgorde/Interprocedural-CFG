// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker;

import org.apache.activemq.util.IntrospectionSupport;
import java.util.Map;
import java.util.HashMap;
import org.apache.activemq.util.URISupport;
import java.net.URI;

public class DefaultBrokerFactory implements BrokerFactoryHandler
{
    @Override
    public BrokerService createBroker(final URI brokerURI) throws Exception {
        final URISupport.CompositeData compositeData = URISupport.parseComposite(brokerURI);
        final Map<String, String> params = new HashMap<String, String>(compositeData.getParameters());
        final BrokerService brokerService = new BrokerService();
        IntrospectionSupport.setProperties(brokerService, params);
        if (compositeData.getPath() != null) {
            brokerService.setBrokerName(compositeData.getPath());
        }
        final URI[] components = compositeData.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if ("network".equals(components[i].getScheme())) {
                brokerService.addNetworkConnector(components[i].getSchemeSpecificPart());
            }
            else if ("proxy".equals(components[i].getScheme())) {
                brokerService.addProxyConnector(components[i].getSchemeSpecificPart());
            }
            else {
                brokerService.addConnector(components[i]);
            }
        }
        return brokerService;
    }
}
