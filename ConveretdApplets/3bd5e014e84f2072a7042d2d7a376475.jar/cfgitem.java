// 
// Decompiled by Procyon v0.5.30
// 

class cfgitem
{
    String name;
    String val;
    String opts;
    config cfg;
    
    cfgitem(final String name, final String val, final String opts, final config cfg) {
        this.name = name;
        this.val = val;
        this.opts = opts;
        this.cfg = cfg;
    }
}
