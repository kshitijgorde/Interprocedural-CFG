// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import java.util.Hashtable;

class ElementState
{
    String rawName;
    String localName;
    String namespaceURI;
    boolean preserveSpace;
    boolean empty;
    boolean afterElement;
    boolean doCData;
    boolean unescaped;
    boolean inCData;
    Hashtable prefixes;
}
