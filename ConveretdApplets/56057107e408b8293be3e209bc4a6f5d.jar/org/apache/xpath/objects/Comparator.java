// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

abstract class Comparator
{
    abstract boolean compareNumbers(final double p0, final double p1);
    
    abstract boolean compareStrings(final String p0, final String p1);
}
