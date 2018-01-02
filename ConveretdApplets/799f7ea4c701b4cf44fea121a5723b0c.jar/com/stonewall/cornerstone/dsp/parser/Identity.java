// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.parser;

import com.stonewall.cornerstone.utility.IdentityFactory;
import com.stonewall.parser.Function;

class Identity implements Function
{
    private static final IdentityFactory factory;
    
    static {
        factory = new IdentityFactory();
    }
    
    @Override
    public String execute(final Context ctx, final String[] args) {
        return Identity.factory.next();
    }
}
