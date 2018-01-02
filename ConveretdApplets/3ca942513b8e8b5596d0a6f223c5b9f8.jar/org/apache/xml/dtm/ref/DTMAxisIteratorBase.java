// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.dtm.DTMAxisIterator;

public abstract class DTMAxisIteratorBase implements DTMAxisIterator
{
    protected int _last;
    protected int _position;
    protected int _markedNode;
    protected int _startNode;
    protected boolean _includeSelf;
    protected boolean _isRestartable;
    
    public DTMAxisIteratorBase() {
        this._last = -1;
        this._position = 0;
        this._startNode = -1;
        this._includeSelf = false;
        this._isRestartable = true;
    }
    
    public int getStartNode() {
        return this._startNode;
    }
    
    public DTMAxisIterator reset() {
        final boolean temp = this._isRestartable;
        this._isRestartable = true;
        this.setStartNode(this._startNode);
        this._isRestartable = temp;
        return this;
    }
    
    public DTMAxisIterator includeSelf() {
        this._includeSelf = true;
        return this;
    }
    
    public int getLast() {
        if (this._last == -1) {
            final int temp = this._position;
            this.setMark();
            this.reset();
            do {
                ++this._last;
            } while (this.next() != -1);
            this.gotoMark();
            this._position = temp;
        }
        return this._last;
    }
    
    public int getPosition() {
        return (this._position == 0) ? 1 : this._position;
    }
    
    public boolean isReverse() {
        return false;
    }
    
    public DTMAxisIterator cloneIterator() {
        try {
            final DTMAxisIteratorBase clone = (DTMAxisIteratorBase)super.clone();
            clone._isRestartable = false;
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new WrappedRuntimeException(e);
        }
    }
    
    protected final int returnNode(final int node) {
        ++this._position;
        return node;
    }
    
    protected final DTMAxisIterator resetPosition() {
        this._position = 0;
        return this;
    }
    
    public boolean isDocOrdered() {
        return true;
    }
    
    public int getAxis() {
        return -1;
    }
    
    public void setRestartable(final boolean isRestartable) {
        this._isRestartable = isRestartable;
    }
    
    public int getNodeByPosition(final int position) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1         /* position */
        //     1: ifle            47
        //     4: aload_0         /* this */
        //     5: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.isReverse:()Z
        //     8: ifeq            22
        //    11: aload_0         /* this */
        //    12: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.getLast:()I
        //    15: iload_1         /* position */
        //    16: isub           
        //    17: iconst_1       
        //    18: iadd           
        //    19: goto            23
        //    22: iload_1         /* position */
        //    23: istore_2        /* pos */
        //    24: goto            37
        //    27: iload_2         /* pos */
        //    28: aload_0         /* this */
        //    29: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.getPosition:()I
        //    32: if_icmpne       37
        //    35: iload_3        
        //    36: ireturn        
        //    37: aload_0         /* this */
        //    38: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.next:()I
        //    41: dup            
        //    42: istore_3        /* node */
        //    43: iconst_m1      
        //    44: if_icmpne       27
        //    47: iconst_m1      
        //    48: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  --------------------------------------------
        //  0      49      0     this      Lorg/apache/xml/dtm/ref/DTMAxisIteratorBase;
        //  0      49      1     position  I
        //  24     23      2     pos       I
        //  43     4       3     node      I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public abstract DTMAxisIterator setStartNode(final int p0);
    
    public abstract void gotoMark();
    
    public abstract void setMark();
    
    public abstract int next();
}
