// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

class Value implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final String var = args[1];
        final String def = (args.length > 2) ? args[2] : "";
        return ctx.references().get(var, def);
    }
}
