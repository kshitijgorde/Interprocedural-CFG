// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command.store.amq;

import org.apache.velocity.runtime.resource.Resource;
import java.io.ByteArrayInputStream;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.InputStream;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import java.util.HashMap;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

public class CustomResourceLoader extends ResourceLoader
{
    private static final ThreadLocal<HashMap<String, String>> resourcesTL;
    private final FileResourceLoader fileResourceLoader;
    
    public CustomResourceLoader() {
        this.fileResourceLoader = new FileResourceLoader();
    }
    
    public void commonInit(final RuntimeServices rs, final ExtendedProperties configuration) {
        super.commonInit(rs, configuration);
        this.fileResourceLoader.commonInit(rs, configuration);
    }
    
    public void init(final ExtendedProperties configuration) {
        this.fileResourceLoader.init(configuration);
    }
    
    public synchronized InputStream getResourceStream(final String name) throws ResourceNotFoundException {
        InputStream result = null;
        if (name == null || name.length() == 0) {
            throw new ResourceNotFoundException("No template name provided");
        }
        String value = null;
        final HashMap<String, String> resources = CustomResourceLoader.resourcesTL.get();
        if (resources != null) {
            value = resources.get(name);
        }
        if (value == null) {
            result = this.fileResourceLoader.getResourceStream(name);
        }
        else {
            try {
                result = new ByteArrayInputStream(value.getBytes());
            }
            catch (Exception e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        }
        return result;
    }
    
    public boolean isSourceModified(final Resource resource) {
        return false;
    }
    
    public long getLastModified(final Resource resource) {
        return 0L;
    }
    
    public static HashMap<String, String> getResources() {
        return CustomResourceLoader.resourcesTL.get();
    }
    
    public static void setResources(final HashMap<String, String> arg0) {
        CustomResourceLoader.resourcesTL.set(arg0);
    }
    
    static {
        resourcesTL = new ThreadLocal<HashMap<String, String>>();
    }
}
