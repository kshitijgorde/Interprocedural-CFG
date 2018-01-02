// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.interpreter.Event;
import com.stonewall.parser.Function;

class Log implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append('\"');
            sb.append(args[i]);
            sb.append("\",");
        }
        ctx.raiseEvent(Event.Severity.info, sb.toString());
        return "";
    }
}
