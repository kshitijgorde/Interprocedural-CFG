// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.DTMAxisIterator;

public final class FilteredStepIterator extends StepIterator
{
    private Filter _filter;
    
    public FilteredStepIterator(final DTMAxisIterator source, final DTMAxisIterator iterator, final Filter filter) {
        super(source, iterator);
        this._filter = filter;
    }
    
    public int next() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            22
        //     3: aload_0         /* this */
        //     4: getfield        org/apache/xalan/xsltc/dom/FilteredStepIterator._filter:Lorg/apache/xalan/xsltc/dom/Filter;
        //     7: iload_1        
        //     8: invokeinterface org/apache/xalan/xsltc/dom/Filter.test:(I)Z
        //    13: ifeq            22
        //    16: aload_0         /* this */
        //    17: iload_1        
        //    18: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
        //    21: ireturn        
        //    22: aload_0         /* this */
        //    23: invokespecial   org/apache/xalan/xsltc/dom/StepIterator.next:()I
        //    26: dup            
        //    27: istore_1        /* node */
        //    28: iconst_m1      
        //    29: if_icmpne       3
        //    32: iload_1         /* node */
        //    33: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  -------------------------------------------------
        //  0      34      0     this  Lorg/apache/xalan/xsltc/dom/FilteredStepIterator;
        //  28     6       1     node  I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
