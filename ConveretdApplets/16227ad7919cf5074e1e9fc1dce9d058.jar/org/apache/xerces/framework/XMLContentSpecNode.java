// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

public final class XMLContentSpecNode
{
    public static final int CONTENTSPECNODE_LEAF = 0;
    public static final int CONTENTSPECNODE_ZERO_OR_ONE = 1;
    public static final int CONTENTSPECNODE_ZERO_OR_MORE = 2;
    public static final int CONTENTSPECNODE_ONE_OR_MORE = 3;
    public static final int CONTENTSPECNODE_CHOICE = 4;
    public static final int CONTENTSPECNODE_SEQ = 5;
    public int type;
    public int value;
    public int otherValue;
}
