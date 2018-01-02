// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import org.apache.xpath.ExpressionNode;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import org.apache.xml.dtm.DTM;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xpath.Expression;

public class FunctionPattern extends StepPattern
{
    static final long serialVersionUID = -5426793413091209944L;
    Expression m_functionExpr;
    
    public FunctionPattern(final Expression expr, final int axis, final int predaxis) {
        super(0, null, null, axis, predaxis);
        this.m_functionExpr = expr;
    }
    
    public final void calcScore() {
        super.m_score = NodeTest.SCORE_OTHER;
        if (null == super.m_targetString) {
            this.calcTargetString();
        }
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.fixupVariables(vars, globalsSize);
        this.m_functionExpr.fixupVariables(vars, globalsSize);
    }
    
    public XObject execute(final XPathContext xctxt, final int context) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xpath/patterns/FunctionPattern.m_functionExpr:Lorg/apache/xpath/Expression;
        //     4: aload_1         /* xctxt */
        //     5: iload_2         /* context */
        //     6: invokevirtual   org/apache/xpath/Expression.asIterator:(Lorg/apache/xpath/XPathContext;I)Lorg/apache/xml/dtm/DTMIterator;
        //     9: astore_3        /* nl */
        //    10: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_NONE:Lorg/apache/xpath/objects/XNumber;
        //    13: astore          score
        //    15: aconst_null    
        //    16: aload_3         /* nl */
        //    17: if_acmpeq       67
        //    20: goto            54
        //    23: iload           5
        //    25: iload_2         /* context */
        //    26: if_icmpne       35
        //    29: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_OTHER:Lorg/apache/xpath/objects/XNumber;
        //    32: goto            38
        //    35: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_NONE:Lorg/apache/xpath/objects/XNumber;
        //    38: astore          score
        //    40: aload           score
        //    42: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_OTHER:Lorg/apache/xpath/objects/XNumber;
        //    45: if_acmpne       54
        //    48: iload           5
        //    50: istore_2        /* context */
        //    51: goto            67
        //    54: iconst_m1      
        //    55: aload_3         /* nl */
        //    56: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //    61: dup            
        //    62: istore          n
        //    64: if_icmpne       23
        //    67: aload_3         /* nl */
        //    68: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //    73: aload           score
        //    75: areturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  -------------------------------------------
        //  0      76      0     this     Lorg/apache/xpath/patterns/FunctionPattern;
        //  0      76      1     xctxt    Lorg/apache/xpath/XPathContext;
        //  0      76      2     context  I
        //  10     66      3     nl       Lorg/apache/xml/dtm/DTMIterator;
        //  15     61      4     score    Lorg/apache/xpath/objects/XNumber;
        //  64     3       5     n        I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public XObject execute(final XPathContext xctxt, final int context, final DTM dtm, final int expType) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xpath/patterns/FunctionPattern.m_functionExpr:Lorg/apache/xpath/Expression;
        //     4: aload_1         /* xctxt */
        //     5: iload_2         /* context */
        //     6: invokevirtual   org/apache/xpath/Expression.asIterator:(Lorg/apache/xpath/XPathContext;I)Lorg/apache/xml/dtm/DTMIterator;
        //     9: astore          nl
        //    11: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_NONE:Lorg/apache/xpath/objects/XNumber;
        //    14: astore          score
        //    16: aconst_null    
        //    17: aload           nl
        //    19: if_acmpeq       77
        //    22: goto            56
        //    25: iload           7
        //    27: iload_2         /* context */
        //    28: if_icmpne       37
        //    31: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_OTHER:Lorg/apache/xpath/objects/XNumber;
        //    34: goto            40
        //    37: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_NONE:Lorg/apache/xpath/objects/XNumber;
        //    40: astore          score
        //    42: aload           score
        //    44: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_OTHER:Lorg/apache/xpath/objects/XNumber;
        //    47: if_acmpne       56
        //    50: iload           7
        //    52: istore_2        /* context */
        //    53: goto            70
        //    56: iconst_m1      
        //    57: aload           nl
        //    59: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //    64: dup            
        //    65: istore          n
        //    67: if_icmpne       25
        //    70: aload           nl
        //    72: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //    77: aload           score
        //    79: areturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  -------------------------------------------
        //  0      80      0     this     Lorg/apache/xpath/patterns/FunctionPattern;
        //  0      80      1     xctxt    Lorg/apache/xpath/XPathContext;
        //  0      80      2     context  I
        //  0      80      3     dtm      Lorg/apache/xml/dtm/DTM;
        //  0      80      4     expType  I
        //  11     69      5     nl       Lorg/apache/xml/dtm/DTMIterator;
        //  16     64      6     score    Lorg/apache/xpath/objects/XNumber;
        //  67     10      7     n        I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* xctxt */
        //     1: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //     4: istore_2        /* context */
        //     5: aload_0         /* this */
        //     6: getfield        org/apache/xpath/patterns/FunctionPattern.m_functionExpr:Lorg/apache/xpath/Expression;
        //     9: aload_1         /* xctxt */
        //    10: iload_2         /* context */
        //    11: invokevirtual   org/apache/xpath/Expression.asIterator:(Lorg/apache/xpath/XPathContext;I)Lorg/apache/xml/dtm/DTMIterator;
        //    14: astore_3        /* nl */
        //    15: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_NONE:Lorg/apache/xpath/objects/XNumber;
        //    18: astore          score
        //    20: aconst_null    
        //    21: aload_3         /* nl */
        //    22: if_acmpeq       78
        //    25: goto            59
        //    28: iload           5
        //    30: iload_2         /* context */
        //    31: if_icmpne       40
        //    34: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_OTHER:Lorg/apache/xpath/objects/XNumber;
        //    37: goto            43
        //    40: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_NONE:Lorg/apache/xpath/objects/XNumber;
        //    43: astore          score
        //    45: aload           score
        //    47: getstatic       org/apache/xpath/patterns/NodeTest.SCORE_OTHER:Lorg/apache/xpath/objects/XNumber;
        //    50: if_acmpne       59
        //    53: iload           5
        //    55: istore_2        /* context */
        //    56: goto            72
        //    59: iconst_m1      
        //    60: aload_3         /* nl */
        //    61: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //    66: dup            
        //    67: istore          n
        //    69: if_icmpne       28
        //    72: aload_3         /* nl */
        //    73: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //    78: aload           score
        //    80: areturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  -------------------------------------------
        //  0      81      0     this     Lorg/apache/xpath/patterns/FunctionPattern;
        //  0      81      1     xctxt    Lorg/apache/xpath/XPathContext;
        //  5      76      2     context  I
        //  15     66      3     nl       Lorg/apache/xml/dtm/DTMIterator;
        //  20     61      4     score    Lorg/apache/xpath/objects/XNumber;
        //  69     9       5     n        I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void callSubtreeVisitors(final XPathVisitor visitor) {
        this.m_functionExpr.callVisitors(new FunctionOwner(), visitor);
        super.callSubtreeVisitors(visitor);
    }
    
    class FunctionOwner implements ExpressionOwner
    {
        public Expression getExpression() {
            return FunctionPattern.this.m_functionExpr;
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(FunctionPattern.this);
            FunctionPattern.this.m_functionExpr = exp;
        }
    }
}
