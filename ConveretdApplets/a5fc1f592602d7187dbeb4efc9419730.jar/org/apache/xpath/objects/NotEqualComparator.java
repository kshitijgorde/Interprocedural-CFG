// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xml.utils.XMLString;

class NotEqualComparator extends Comparator
{
    boolean compareStrings(final XMLString s1, final XMLString s2) {
        return !s1.equals(s2);
    }
    
    boolean compareNumbers(final double n1, final double n2) {
        return n1 != n2;
    }
}
