// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;
import org.xmodel.log.Log;

class Post extends Context
{
    static Log log;
    
    static {
        Post.log = Log.getLog(Post.class);
    }
    
    Post(final Parser parser, final Element root) throws Exception {
        super(parser, root);
    }
    
    @Override
    public String id() {
        return super.id();
    }
    
    void apply() {
        this.applyActions();
    }
}
