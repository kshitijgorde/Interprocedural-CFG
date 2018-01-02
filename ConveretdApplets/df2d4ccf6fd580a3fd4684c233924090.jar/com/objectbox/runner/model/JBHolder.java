// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.util.Hashtable;
import com.objectbox.runner.gui.BeanRunner;
import java.io.Serializable;

public abstract class JBHolder implements JBObjectWithProperties, Serializable
{
    protected transient BeanRunner runner;
    protected JBProperties props;
    static final long serialVersionUID = -123456789L;
    
    public JBHolder() {
        this.runner = null;
        this.props = null;
    }
    
    public void cleanup() {
        this.runner = null;
        this.props.cleanup();
        this.props = null;
    }
    
    public BeanRunner getBeanRunner() {
        return (this.runner == null) ? new BeanRunner() : this.runner;
    }
    
    public String getName() {
        final String s = ((Hashtable<K, String>)this.props.getProps()).get("code");
        return (s == null) ? "Unknown" : s;
    }
    
    public JBProperties getProperties() {
        return this.props;
    }
    
    public abstract BeanRunner run(final ThreadGroup p0);
    
    public void setBeanRunner(final BeanRunner runner) {
        this.runner = runner;
    }
    
    public void setProperties(final JBProperties props) {
        this.props = props;
    }
}
