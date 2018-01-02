// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.function.custom.DelegateFunction;

public class ServiceNameFunction extends DelegateFunction
{
    public static final String name = "dsp:service-name";
    private static final String spec = "let $pnum      := $arg0/en:protocol;let $src-start := $arg0/en:srcPort/en:start;let $src-end   := $arg0/en:srcPort/en:end;let $dst-start := $arg0/en:dstPort/en:start;let $dst-end   := $arg0/en:dstPort/en:end; let $icmpType  := $arg0/en:icmpType; let $icmpCode  := $arg0/en:icmpCode; if ($arg0[en:protocol = '0'])  then 'any'else  (if ($arg0/en:protocol)    then       printf('%s-%s-%s-%s-%s', $pnum, $src-start, $src-end, $dst-start, $dst-end)    else      printf('%s-%s', $icmpType, $icmpCode))";
    
    public ServiceNameFunction() {
        super("dsp:service-name", "let $pnum      := $arg0/en:protocol;let $src-start := $arg0/en:srcPort/en:start;let $src-end   := $arg0/en:srcPort/en:end;let $dst-start := $arg0/en:dstPort/en:start;let $dst-end   := $arg0/en:dstPort/en:end; let $icmpType  := $arg0/en:icmpType; let $icmpCode  := $arg0/en:icmpCode; if ($arg0[en:protocol = '0'])  then 'any'else  (if ($arg0/en:protocol)    then       printf('%s-%s-%s-%s-%s', $pnum, $src-start, $src-end, $dst-start, $dst-end)    else      printf('%s-%s', $icmpType, $icmpCode))");
    }
}
