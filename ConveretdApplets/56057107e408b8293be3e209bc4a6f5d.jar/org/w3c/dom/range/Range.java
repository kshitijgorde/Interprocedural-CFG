// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom.range;

import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentFragment;

public interface Range
{
    public static final short START_TO_START = 0;
    public static final short START_TO_END = 1;
    public static final short END_TO_END = 2;
    public static final short END_TO_START = 3;
    
    DocumentFragment cloneContents() throws DOMException;
    
    Range cloneRange() throws DOMException;
    
    void collapse(final boolean p0) throws DOMException;
    
    short compareBoundaryPoints(final short p0, final Range p1) throws DOMException;
    
    void deleteContents() throws DOMException;
    
    void detach() throws DOMException;
    
    DocumentFragment extractContents() throws DOMException;
    
    boolean getCollapsed() throws DOMException;
    
    Node getCommonAncestorContainer() throws DOMException;
    
    Node getEndContainer() throws DOMException;
    
    int getEndOffset() throws DOMException;
    
    Node getStartContainer() throws DOMException;
    
    int getStartOffset() throws DOMException;
    
    void insertNode(final Node p0) throws DOMException, RangeException;
    
    void selectNode(final Node p0) throws RangeException, DOMException;
    
    void selectNodeContents(final Node p0) throws RangeException, DOMException;
    
    void setEnd(final Node p0, final int p1) throws RangeException, DOMException;
    
    void setEndAfter(final Node p0) throws RangeException, DOMException;
    
    void setEndBefore(final Node p0) throws RangeException, DOMException;
    
    void setStart(final Node p0, final int p1) throws RangeException, DOMException;
    
    void setStartAfter(final Node p0) throws RangeException, DOMException;
    
    void setStartBefore(final Node p0) throws RangeException, DOMException;
    
    void surroundContents(final Node p0) throws DOMException, RangeException;
    
    String toString() throws DOMException;
}
