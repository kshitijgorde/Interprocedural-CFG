// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.utils.QName;

public class InsertableElementsInfo
{
    public boolean canHoldPCData;
    public int childCount;
    public QName[] curChildren;
    public boolean isValidEOC;
    public int insertAt;
    public QName[] possibleChildren;
    public int resultsCount;
    public boolean[] results;
}
