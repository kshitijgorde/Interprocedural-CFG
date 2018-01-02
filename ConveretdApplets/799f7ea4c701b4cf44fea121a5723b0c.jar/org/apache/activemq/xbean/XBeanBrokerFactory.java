// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.xbean;

import java.beans.PropertyEditorManager;
import org.apache.xbean.spring.context.impl.URIEditor;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.apache.xbean.spring.context.ResourceXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.ResourceUtils;
import org.springframework.core.io.FileSystemResource;
import java.io.File;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.BeansException;
import java.util.Map;
import org.apache.activemq.util.IntrospectionSupport;
import org.apache.activemq.util.URISupport;
import org.apache.activemq.broker.BrokerService;
import java.net.URI;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerFactoryHandler;

public class XBeanBrokerFactory implements BrokerFactoryHandler
{
    private static final transient Logger LOG;
    private boolean validate;
    
    public XBeanBrokerFactory() {
        this.validate = true;
    }
    
    public boolean isValidate() {
        return this.validate;
    }
    
    public void setValidate(final boolean validate) {
        this.validate = validate;
    }
    
    @Override
    public BrokerService createBroker(final URI config) throws Exception {
        String uri = config.getSchemeSpecificPart();
        if (uri.lastIndexOf(63) != -1) {
            IntrospectionSupport.setProperties(this, URISupport.parseQuery(uri));
            uri = uri.substring(0, uri.lastIndexOf(63));
        }
        final ApplicationContext context = this.createApplicationContext(uri);
        BrokerService broker = null;
        try {
            broker = (BrokerService)context.getBean("broker");
        }
        catch (BeansException ex) {}
        if (broker == null) {
            final String[] names = context.getBeanNamesForType((Class)BrokerService.class);
            for (int i = 0; i < names.length; ++i) {
                final String name = names[i];
                broker = (BrokerService)context.getBean(name);
                if (broker != null) {
                    break;
                }
            }
        }
        if (broker == null) {
            throw new IllegalArgumentException("The configuration has no BrokerService instance for resource: " + config);
        }
        if (broker instanceof ApplicationContextAware) {
            ((ApplicationContextAware)broker).setApplicationContext(context);
        }
        return broker;
    }
    
    protected ApplicationContext createApplicationContext(final String uri) throws MalformedURLException {
        XBeanBrokerFactory.LOG.debug("Now attempting to figure out the type of resource: " + uri);
        final File file = new File(uri);
        Resource resource;
        if (file.exists()) {
            resource = (Resource)new FileSystemResource(uri);
        }
        else if (ResourceUtils.isUrl(uri)) {
            resource = (Resource)new UrlResource(uri);
        }
        else {
            resource = (Resource)new ClassPathResource(uri);
        }
        return (ApplicationContext)new ResourceXmlApplicationContext(resource) {
            protected void initBeanDefinitionReader(final XmlBeanDefinitionReader reader) {
                reader.setValidating(XBeanBrokerFactory.this.isValidate());
            }
        };
    }
    
    static {
        LOG = LoggerFactory.getLogger(XBeanBrokerFactory.class);
        PropertyEditorManager.registerEditor(URI.class, URIEditor.class);
    }
}
