// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

class LessThanComparator extends Comparator
{
    boolean compareNumbers(final double n1, final double n2) {
        return n1 < n2;
    }
    
    boolean compareStrings(final String s1, final String s2) {
        return s1.compareTo(s2) < 0;
    }
}