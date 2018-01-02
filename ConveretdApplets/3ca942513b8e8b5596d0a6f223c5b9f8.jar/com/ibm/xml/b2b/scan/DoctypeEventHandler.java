// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;

public interface DoctypeEventHandler
{
    void doctype(final QName p0, final XMLString p1, final XMLString p2, final boolean p3);
}
