// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.function.custom.DelegateFunction;

public class ResolveIPsFunction extends DelegateFunction
{
    public static final String name = "dsp:resolve-ips";
    private static final String spec = "if ( name( $arg0/*) = 'en:addressGroup')   then for $id in $arg0/en:addressGroup/*/@id return          $arg1/*/*[ @id = $id]/en:address  else     for $id in $arg0/*/@id return       $arg1/*/*[ @id = $id]/en:address";
    
    public ResolveIPsFunction() {
        super("dsp:resolve-ips", "if ( name( $arg0/*) = 'en:addressGroup')   then for $id in $arg0/en:addressGroup/*/@id return          $arg1/*/*[ @id = $id]/en:address  else     for $id in $arg0/*/@id return       $arg1/*/*[ @id = $id]/en:address");
    }
}
