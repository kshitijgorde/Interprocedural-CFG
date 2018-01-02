// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.function.custom.DelegateFunction;

public class NameStrFunction extends DelegateFunction
{
    public static final String name = "dsp:name-str";
    private static final String spec = "let $id   := $arg0/@id;let $name := string($id);if string-length($name) > 31   then substring($name, 0, 31)  else $name";
    
    public NameStrFunction() {
        super("dsp:name-str", "let $id   := $arg0/@id;let $name := string($id);if string-length($name) > 31   then substring($name, 0, 31)  else $name");
    }
}
