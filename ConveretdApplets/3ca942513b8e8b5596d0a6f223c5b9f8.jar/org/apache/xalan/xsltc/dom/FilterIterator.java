// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xml.dtm.DTMFilter;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;

public final class FilterIterator extends DTMAxisIteratorBase
{
    private DTMAxisIterator _source;
    private final DTMFilter _filter;
    private final boolean _isReverse;
    
    public FilterIterator(final DTMAxisIterator source, final DTMFilter filter) {
        this._source = source;
        this._filter = filter;
        this._isReverse = source.isReverse();
    }
    
    public boolean isReverse() {
        return this._isReverse;
    }
    
    public void setRestartable(final boolean isRestartable) {
        super._isRestartable = isRestartable;
        this._source.setRestartable(isRestartable);
    }
    
    public DTMAxisIterator cloneIterator() {
        try {
            final FilterIterator clone = (FilterIterator)super.clone();
            clone._source = this._source.cloneIterator();
            clone._isRestartable = false;
            return clone.reset();
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
            return null;
        }
    }
    
    public DTMAxisIterator reset() {
        this._source.reset();
        return this.resetPosition();
    }
    
    public int next() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            24
        //     3: aload_0         /* this */
        //     4: getfield        org/apache/xalan/xsltc/dom/FilterIterator._filter:Lorg/apache/xml/dtm/DTMFilter;
        //     7: iload_1        
        //     8: iconst_m1      
        //     9: invokeinterface org/apache/xml/dtm/DTMFilter.acceptNode:(II)S
        //    14: iconst_1       
        //    15: if_icmpne       24
        //    18: aload_0         /* this */
        //    19: iload_1        
        //    20: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
        //    23: ireturn        
        //    24: aload_0         /* this */
        //    25: getfield        org/apache/xalan/xsltc/dom/FilterIterator._source:Lorg/apache/xml/dtm/DTMAxisIterator;
        //    28: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    33: dup            
        //    34: istore_1        /* node */
        //    35: iconst_m1      
        //    36: if_icmpne       3
        //    39: iconst_m1      
        //    40: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  -------------------------------------------
        //  0      41      0     this  Lorg/apache/xalan/xsltc/dom/FilterIterator;
        //  35     6       1     node  I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public DTMAxisIterator setStartNode(final int node) {
        if (super._isRestartable) {
            this._source.setStartNode(super._startNode = node);
            return this.resetPosition();
        }
        return this;
    }
    
    public void setMark() {
        this._source.setMark();
    }
    
    public void gotoMark() {
        this._source.gotoMark();
    }
}
