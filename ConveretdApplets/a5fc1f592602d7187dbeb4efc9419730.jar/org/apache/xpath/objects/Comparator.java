// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xml.utils.XMLString;

abstract class Comparator
{
    abstract boolean compareStrings(final XMLString p0, final XMLString p1);
    
    abstract boolean compareNumbers(final double p0, final double p1);
}
