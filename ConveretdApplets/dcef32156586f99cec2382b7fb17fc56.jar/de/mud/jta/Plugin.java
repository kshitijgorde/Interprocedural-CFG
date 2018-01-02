// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

public class Plugin
{
    protected PluginBus bus;
    protected String id;
    
    public Plugin(final PluginBus bus, final String id) {
        this.bus = bus;
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void error(final String msg) {
        String name = this.getClass().toString();
        name = name.substring(name.lastIndexOf(46) + 1);
        System.err.println(name + ((this.id != null) ? ("(" + this.id + ")") : "") + ": " + msg);
    }
}
