// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.function.custom.DelegateFunction;

public class EntityDisplayFunction extends DelegateFunction
{
    public static final String name = "dsp:entity-display";
    private static final String spec = "if ( name( $arg0) = 'en:ipInterface') then   string($arg0/en:address)else if ( name( $arg0) = 'en:any') then  'Any'else if ( name( $arg0) = 'en:network' or name( $arg0) = 'en:host') then  (if boolean( $arg0/en:name)    then string( $arg0/en:name)    else string($arg0/en:address))else  string( $arg0/en:name)";
    
    public EntityDisplayFunction() {
        super("dsp:entity-display", "if ( name( $arg0) = 'en:ipInterface') then   string($arg0/en:address)else if ( name( $arg0) = 'en:any') then  'Any'else if ( name( $arg0) = 'en:network' or name( $arg0) = 'en:host') then  (if boolean( $arg0/en:name)    then string( $arg0/en:name)    else string($arg0/en:address))else  string( $arg0/en:name)");
    }
}
