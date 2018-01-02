// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.function.custom.DelegateFunction;

public class ResolveSvcFunction extends DelegateFunction
{
    public static final String name = "dsp:resolve-svc";
    private static final String spec = "if ( name( $arg0) = 'en:ipServiceGroup')   then     for $id in $arg1/en:ipServiceGroups/en:ipServiceGroup[@id=$arg0/@id]/en:entries/en:ipService/@id      return $arg1/en:ipServices/en:ipService[@id=$id]  else    $arg1/en:ipServices/en:ipService[@id=$arg0/@id]";
    
    public ResolveSvcFunction() {
        super("dsp:resolve-svc", "if ( name( $arg0) = 'en:ipServiceGroup')   then     for $id in $arg1/en:ipServiceGroups/en:ipServiceGroup[@id=$arg0/@id]/en:entries/en:ipService/@id      return $arg1/en:ipServices/en:ipService[@id=$id]  else    $arg1/en:ipServices/en:ipService[@id=$arg0/@id]");
    }
}
