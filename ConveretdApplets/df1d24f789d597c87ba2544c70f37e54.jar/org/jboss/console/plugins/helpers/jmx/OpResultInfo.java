// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.jmx;

public class OpResultInfo
{
    public String name;
    public String[] signature;
    public Object result;
    
    public OpResultInfo(final String name, final String[] signature, final Object result) {
        this.name = name;
        this.signature = signature;
        this.result = result;
    }
}
