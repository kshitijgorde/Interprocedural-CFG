// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import java.util.HashMap;
import java.util.Map;
import com.stonewall.parser.Function;

class Sequence implements Function
{
    Map<String, Integer> sequences;
    
    Sequence() {
        this.sequences = new HashMap<String, Integer>();
    }
    
    @Override
    public String execute(final Context ctx, final String[] args) {
        final String name = (args.length > 1) ? args[1] : "__default__";
        Integer current = this.sequences.get(name);
        if (current == null) {
            current = 0;
            this.sequences.put(name, current);
        }
        else {
            this.sequences.put(name, ++current);
        }
        return current.toString();
    }
}
