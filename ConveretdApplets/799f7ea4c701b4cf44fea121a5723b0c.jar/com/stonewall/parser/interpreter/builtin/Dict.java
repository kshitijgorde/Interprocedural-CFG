// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Dictionary;
import java.util.Collection;
import com.stonewall.parser.Tuple;
import com.stonewall.parser.interpreter.Event;
import com.stonewall.parser.Function;
import com.stonewall.parser.ClassFunction;

public class Dict extends ClassFunction
{
    public String keys(final Function.Context ctx, final String[] args) {
        final Dictionary<String> dictionary = ctx.dictionary(args[1]);
        if (dictionary == null) {
            final String msg = "postfix: keys() applied to non-dictionary: " + args[1];
            Dict.log.debug(msg);
            ctx.raiseEvent(Event.Severity.error, msg);
            return args[1];
        }
        return Tuple.join((Collection<String>)dictionary.keySet());
    }
    
    public String values(final Function.Context ctx, final String[] args) {
        final Dictionary<String> dictionary = ctx.dictionary(args[1]);
        if (dictionary == null) {
            final String msg = "postfix: keys() applied to non-dictionary: " + args[1];
            Dict.log.debug(msg);
            ctx.raiseEvent(Event.Severity.error, msg);
            return args[1];
        }
        return Tuple.join(dictionary.values());
    }
}
