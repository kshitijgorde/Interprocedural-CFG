// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

class Lower implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        return args[1].toLowerCase();
    }
}
