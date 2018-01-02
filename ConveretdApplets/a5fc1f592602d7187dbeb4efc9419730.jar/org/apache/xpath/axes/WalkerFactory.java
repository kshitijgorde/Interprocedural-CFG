// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.patterns.FunctionPattern;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.patterns.ContextMatchStepPattern;
import org.apache.xpath.Expression;
import org.apache.xpath.patterns.StepPattern;
import org.apache.xpath.res.XPATHMessages;
import org.apache.xpath.compiler.OpMap;
import org.apache.xml.dtm.DTMIterator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;

public class WalkerFactory
{
    static final boolean DEBUG_PATTERN_CREATION = false;
    static final boolean DEBUG_WALKER_CREATION = false;
    static final boolean DEBUG_ITERATOR_CREATION = false;
    public static final int BITS_COUNT = 255;
    public static final int BITS_RESERVED = 3840;
    public static final int BIT_PREDICATE = 4096;
    public static final int BIT_ANCESTOR = 8192;
    public static final int BIT_ANCESTOR_OR_SELF = 16384;
    public static final int BIT_ATTRIBUTE = 32768;
    public static final int BIT_CHILD = 65536;
    public static final int BIT_DESCENDANT = 131072;
    public static final int BIT_DESCENDANT_OR_SELF = 262144;
    public static final int BIT_FOLLOWING = 524288;
    public static final int BIT_FOLLOWING_SIBLING = 1048576;
    public static final int BIT_NAMESPACE = 2097152;
    public static final int BIT_PARENT = 4194304;
    public static final int BIT_PRECEDING = 8388608;
    public static final int BIT_PRECEDING_SIBLING = 16777216;
    public static final int BIT_SELF = 33554432;
    public static final int BIT_FILTER = 67108864;
    public static final int BIT_ROOT = 134217728;
    public static final int BITMASK_TRAVERSES_OUTSIDE_SUBTREE = 234381312;
    public static final int BIT_BACKWARDS_SELF = 268435456;
    public static final int BIT_ANY_DESCENDANT_FROM_ROOT = 536870912;
    public static final int BIT_NODETEST_ANY = 1073741824;
    public static final int BIT_MATCH_PATTERN = Integer.MIN_VALUE;
    
    static AxesWalker loadOneWalker(final WalkingIterator lpi, final Compiler compiler, final int stepOpCodePos) throws TransformerException {
        AxesWalker firstWalker = null;
        final int stepType = compiler.getOp(stepOpCodePos);
        if (stepType != -1) {
            firstWalker = createDefaultWalker(compiler, stepType, lpi, 0);
            firstWalker.init(compiler, stepOpCodePos, stepType);
        }
        return firstWalker;
    }
    
    static AxesWalker loadWalkers(final WalkingIterator lpi, final Compiler compiler, final int stepOpCodePos, final int stepIndex) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          firstWalker
        //     3: aconst_null    
        //     4: astore          prevWalker
        //     6: aload_1         /* compiler */
        //     7: iload_2         /* stepOpCodePos */
        //     8: iload_3         /* stepIndex */
        //     9: invokestatic    org/apache/xpath/axes/WalkerFactory.analyze:(Lorg/apache/xpath/compiler/Compiler;II)I
        //    12: istore          analysis
        //    14: goto            86
        //    17: aload_1         /* compiler */
        //    18: iload_2         /* stepOpCodePos */
        //    19: aload_0         /* lpi */
        //    20: iload           analysis
        //    22: invokestatic    org/apache/xpath/axes/WalkerFactory.createDefaultWalker:(Lorg/apache/xpath/compiler/Compiler;ILorg/apache/xpath/axes/WalkingIterator;I)Lorg/apache/xpath/axes/AxesWalker;
        //    25: astore          walker
        //    27: aload           walker
        //    29: aload_1         /* compiler */
        //    30: iload_2         /* stepOpCodePos */
        //    31: iload           4
        //    33: invokevirtual   org/apache/xpath/axes/AxesWalker.init:(Lorg/apache/xpath/compiler/Compiler;II)V
        //    36: aload           walker
        //    38: aload_0         /* lpi */
        //    39: invokevirtual   org/apache/xpath/Expression.exprSetParent:(Lorg/apache/xpath/ExpressionNode;)V
        //    42: aconst_null    
        //    43: aload           firstWalker
        //    45: if_acmpne       55
        //    48: aload           walker
        //    50: astore          firstWalker
        //    52: goto            69
        //    55: aload           prevWalker
        //    57: aload           walker
        //    59: invokevirtual   org/apache/xpath/axes/AxesWalker.setNextWalker:(Lorg/apache/xpath/axes/AxesWalker;)V
        //    62: aload           walker
        //    64: aload           prevWalker
        //    66: invokevirtual   org/apache/xpath/axes/AxesWalker.setPrevWalker:(Lorg/apache/xpath/axes/AxesWalker;)V
        //    69: aload           walker
        //    71: astore          prevWalker
        //    73: aload_1         /* compiler */
        //    74: iload_2         /* stepOpCodePos */
        //    75: invokevirtual   org/apache/xpath/compiler/OpMap.getNextStepPos:(I)I
        //    78: istore_2        /* stepOpCodePos */
        //    79: iload_2         /* stepOpCodePos */
        //    80: ifge            86
        //    83: goto            98
        //    86: iconst_m1      
        //    87: aload_1         /* compiler */
        //    88: iload_2         /* stepOpCodePos */
        //    89: invokevirtual   org/apache/xpath/compiler/OpMap.getOp:(I)I
        //    92: dup            
        //    93: istore          stepType
        //    95: if_icmpne       17
        //    98: aload           firstWalker
        //   100: areturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name           Signature
        //  -----  ------  ----  -------------  ---------------------------------------
        //  0      101     0     lpi            Lorg/apache/xpath/axes/WalkingIterator;
        //  0      101     1     compiler       Lorg/apache/xpath/compiler/Compiler;
        //  0      101     2     stepOpCodePos  I
        //  0      101     3     stepIndex      I
        //  95     6       4     stepType       I
        //  3      98      5     firstWalker    Lorg/apache/xpath/axes/AxesWalker;
        //  27     74      6     walker         Lorg/apache/xpath/axes/AxesWalker;
        //  6      95      7     prevWalker     Lorg/apache/xpath/axes/AxesWalker;
        //  14     87      8     analysis       I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean isSet(final int analysis, final int bits) {
        return 0x0 != (analysis & bits);
    }
    
    public static void diagnoseIterator(final String name, final int analysis, final Compiler compiler) {
        System.out.println(compiler.toString() + ", " + name + ", " + Integer.toBinaryString(analysis) + ", " + getAnalysisString(analysis));
    }
    
    public static DTMIterator newDTMIterator(final Compiler compiler, final int opPos, final boolean isTopLevel) throws TransformerException {
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        final int analysis = analyze(compiler, firstStepPos, 0);
        final boolean isOneStep = isOneStep(analysis);
        DTMIterator iter;
        if (isOneStep && walksSelfOnly(analysis) && isWild(analysis) && !hasPredicate(analysis)) {
            iter = new SelfIteratorNoPredicate(compiler, opPos, analysis);
        }
        else if (walksChildrenOnly(analysis) && isOneStep) {
            if (isWild(analysis) && !hasPredicate(analysis)) {
                iter = new ChildIterator(compiler, opPos, analysis);
            }
            else {
                iter = new ChildTestIterator(compiler, opPos, analysis);
            }
        }
        else if (isOneStep && walksAttributes(analysis)) {
            iter = new AttributeIterator(compiler, opPos, analysis);
        }
        else if (isOneStep && !walksFilteredList(analysis)) {
            if (!walksNamespaces(analysis) && (walksInDocOrder(analysis) || isSet(analysis, 4194304))) {
                iter = new OneStepIteratorForward(compiler, opPos, analysis);
            }
            else {
                iter = new OneStepIterator(compiler, opPos, analysis);
            }
        }
        else if (isOptimizableForDescendantIterator(compiler, firstStepPos, 0)) {
            iter = new DescendantIterator(compiler, opPos, analysis);
        }
        else if (isNaturalDocOrder(compiler, firstStepPos, 0, analysis)) {
            iter = new WalkingIterator(compiler, opPos, analysis, true);
        }
        else {
            iter = new WalkingIteratorSorted(compiler, opPos, analysis, true);
        }
        if (iter instanceof LocPathIterator) {
            ((LocPathIterator)iter).setIsTopLevel(isTopLevel);
        }
        return iter;
    }
    
    public static int getAxisFromStep(final Compiler compiler, final int stepOpCodePos) throws TransformerException {
        final int stepType = compiler.getOp(stepOpCodePos);
        switch (stepType) {
            case 43: {
                return 6;
            }
            case 44: {
                return 7;
            }
            case 46: {
                return 11;
            }
            case 47: {
                return 12;
            }
            case 45: {
                return 10;
            }
            case 49: {
                return 9;
            }
            case 37: {
                return 0;
            }
            case 38: {
                return 1;
            }
            case 39: {
                return 2;
            }
            case 50: {
                return 19;
            }
            case 40: {
                return 3;
            }
            case 42: {
                return 5;
            }
            case 41: {
                return 4;
            }
            case 48: {
                return 13;
            }
            case 22:
            case 23:
            case 24:
            case 25: {
                return 20;
            }
            default: {
                throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
            }
        }
    }
    
    public static int getAnalysisBitFromAxes(final int axis) {
        switch (axis) {
            case 0: {
                return 8192;
            }
            case 1: {
                return 16384;
            }
            case 2: {
                return 32768;
            }
            case 3: {
                return 65536;
            }
            case 4: {
                return 131072;
            }
            case 5: {
                return 262144;
            }
            case 6: {
                return 524288;
            }
            case 7: {
                return 1048576;
            }
            case 8:
            case 9: {
                return 2097152;
            }
            case 10: {
                return 4194304;
            }
            case 11: {
                return 8388608;
            }
            case 12: {
                return 16777216;
            }
            case 13: {
                return 33554432;
            }
            case 14: {
                return 262144;
            }
            case 16:
            case 17:
            case 18: {
                return 536870912;
            }
            case 19: {
                return 134217728;
            }
            case 20: {
                return 67108864;
            }
            default: {
                return 67108864;
            }
        }
    }
    
    static boolean functionProximateOrContainsProximate(final Compiler compiler, int opPos) {
        final int endFunc = opPos + compiler.getOp(opPos + 1) - 1;
        opPos = OpMap.getFirstChildPos(opPos);
        final int funcID = compiler.getOp(opPos);
        switch (funcID) {
            case 1:
            case 2: {
                return true;
            }
            default: {
                ++opPos;
                for (int i = 0, p = opPos; p < endFunc; p = compiler.getNextOpPos(p), ++i) {
                    final int innerExprOpPos = p + 2;
                    final int argOp = compiler.getOp(innerExprOpPos);
                    final boolean prox = isProximateInnerExpr(compiler, innerExprOpPos);
                    if (prox) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
    
    static boolean isProximateInnerExpr(final Compiler compiler, final int opPos) {
        final int op = compiler.getOp(opPos);
        final int innerExprOpPos = opPos + 2;
        switch (op) {
            case 26: {
                if (isProximateInnerExpr(compiler, innerExprOpPos)) {
                    return true;
                }
                break;
            }
            case 21:
            case 22:
            case 27:
            case 28: {
                break;
            }
            case 25: {
                final boolean isProx = functionProximateOrContainsProximate(compiler, opPos);
                if (isProx) {
                    return true;
                }
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9: {
                final int leftPos = OpMap.getFirstChildPos(op);
                final int rightPos = compiler.getNextOpPos(leftPos);
                boolean isProx = isProximateInnerExpr(compiler, leftPos);
                if (isProx) {
                    return true;
                }
                isProx = isProximateInnerExpr(compiler, rightPos);
                if (isProx) {
                    return true;
                }
                break;
            }
            default: {
                return true;
            }
        }
        return false;
    }
    
    public static boolean mightBeProximate(final Compiler compiler, final int opPos, final int stepType) throws TransformerException {
        final boolean mightBeProximate = false;
        switch (stepType) {
            case 22:
            case 23:
            case 24:
            case 25: {
                final int argLen = compiler.getArgLength(opPos);
                break;
            }
            default: {
                final int argLen = compiler.getArgLengthOfStep(opPos);
                break;
            }
        }
        int predPos = compiler.getFirstPredicateOpPos(opPos);
        int count = 0;
        while (29 == compiler.getOp(predPos)) {
            ++count;
            final int innerExprOpPos = predPos + 2;
            final int predOp = compiler.getOp(innerExprOpPos);
            switch (predOp) {
                case 22: {
                    return true;
                }
                case 28: {
                    break;
                }
                case 19:
                case 27: {
                    return true;
                }
                case 25: {
                    final boolean isProx = functionProximateOrContainsProximate(compiler, innerExprOpPos);
                    if (isProx) {
                        return true;
                    }
                    break;
                }
                case 5:
                case 6:
                case 7:
                case 8:
                case 9: {
                    final int leftPos = OpMap.getFirstChildPos(innerExprOpPos);
                    final int rightPos = compiler.getNextOpPos(leftPos);
                    boolean isProx = isProximateInnerExpr(compiler, leftPos);
                    if (isProx) {
                        return true;
                    }
                    isProx = isProximateInnerExpr(compiler, rightPos);
                    if (isProx) {
                        return true;
                    }
                    break;
                }
                default: {
                    return true;
                }
            }
            predPos = compiler.getNextOpPos(predPos);
        }
        return mightBeProximate;
    }
    
    private static boolean isOptimizableForDescendantIterator(final Compiler compiler, final int stepOpCodePos, final int stepIndex) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore          stepCount
        //     3: iconst_0       
        //     4: istore          foundDorDS
        //     6: iconst_0       
        //     7: istore          foundSelf
        //     9: iconst_0       
        //    10: istore          foundDS
        //    12: sipush          1033
        //    15: istore          nodeTestType
        //    17: goto            335
        //    20: iload           nodeTestType
        //    22: sipush          1033
        //    25: if_icmpeq       37
        //    28: iload           nodeTestType
        //    30: bipush          35
        //    32: if_icmpeq       37
        //    35: iconst_0       
        //    36: ireturn        
        //    37: iinc            stepCount, 1
        //    40: iload           stepCount
        //    42: iconst_3       
        //    43: if_icmple       48
        //    46: iconst_0       
        //    47: ireturn        
        //    48: aload_0         /* compiler */
        //    49: iload_1         /* stepOpCodePos */
        //    50: iload_3        
        //    51: invokestatic    org/apache/xpath/axes/WalkerFactory.mightBeProximate:(Lorg/apache/xpath/compiler/Compiler;II)Z
        //    54: istore          mightBeProximate
        //    56: iload           mightBeProximate
        //    58: ifeq            63
        //    61: iconst_0       
        //    62: ireturn        
        //    63: iload_3        
        //    64: tableswitch {
        //               44: 208
        //               45: 208
        //               46: 208
        //               47: 208
        //               48: 266
        //               49: 266
        //               50: 266
        //               51: 266
        //               52: 266
        //               53: 266
        //               54: 266
        //               55: 266
        //               56: 266
        //               57: 266
        //               58: 266
        //               59: 208
        //               60: 208
        //               61: 208
        //               62: 218
        //               63: 238
        //               64: 235
        //               65: 208
        //               66: 208
        //               67: 208
        //               68: 208
        //               69: 208
        //               70: 252
        //               71: 208
        //               72: 210
        //               73: 208
        //               74: 208
        //               75: 208
        //          default: 266
        //        }
        //   208: iconst_0       
        //   209: ireturn        
        //   210: iconst_1       
        //   211: iload           stepCount
        //   213: if_icmpeq       290
        //   216: iconst_0       
        //   217: ireturn        
        //   218: iload           foundDS
        //   220: ifne            290
        //   223: iload           foundDorDS
        //   225: ifeq            233
        //   228: iload           foundSelf
        //   230: ifne            290
        //   233: iconst_0       
        //   234: ireturn        
        //   235: iconst_1       
        //   236: istore          foundDS
        //   238: iconst_3       
        //   239: iload           stepCount
        //   241: if_icmpne       246
        //   244: iconst_0       
        //   245: ireturn        
        //   246: iconst_1       
        //   247: istore          foundDorDS
        //   249: goto            290
        //   252: iconst_1       
        //   253: iload           stepCount
        //   255: if_icmpeq       260
        //   258: iconst_0       
        //   259: ireturn        
        //   260: iconst_1       
        //   261: istore          foundSelf
        //   263: goto            290
        //   266: new             Ljava/lang/RuntimeException;
        //   269: dup            
        //   270: ldc             "ER_NULL_ERROR_HANDLER"
        //   272: iconst_1       
        //   273: anewarray       Ljava/lang/Object;
        //   276: dup            
        //   277: iconst_0       
        //   278: iload_3        
        //   279: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   282: aastore        
        //   283: invokestatic    org/apache/xpath/res/XPATHMessages.createXPATHMessage:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   286: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   289: athrow         
        //   290: aload_0         /* compiler */
        //   291: iload_1         /* stepOpCodePos */
        //   292: invokevirtual   org/apache/xpath/compiler/OpMap.getStepTestType:(I)I
        //   295: istore          nodeTestType
        //   297: aload_0         /* compiler */
        //   298: iload_1         /* stepOpCodePos */
        //   299: invokevirtual   org/apache/xpath/compiler/OpMap.getNextStepPos:(I)I
        //   302: istore          nextStepOpCodePos
        //   304: iload           nextStepOpCodePos
        //   306: ifge            312
        //   309: goto            346
        //   312: iconst_m1      
        //   313: aload_0         /* compiler */
        //   314: iload           nextStepOpCodePos
        //   316: invokevirtual   org/apache/xpath/compiler/OpMap.getOp:(I)I
        //   319: if_icmpeq       332
        //   322: aload_0         /* compiler */
        //   323: iload_1         /* stepOpCodePos */
        //   324: invokevirtual   org/apache/xpath/compiler/Compiler.countPredicates:(I)I
        //   327: ifle            332
        //   330: iconst_0       
        //   331: ireturn        
        //   332: iload           nextStepOpCodePos
        //   334: istore_1        /* stepOpCodePos */
        //   335: iconst_m1      
        //   336: aload_0         /* compiler */
        //   337: iload_1         /* stepOpCodePos */
        //   338: invokevirtual   org/apache/xpath/compiler/OpMap.getOp:(I)I
        //   341: dup            
        //   342: istore_3        /* stepType */
        //   343: if_icmpne       20
        //   346: iconst_1       
        //   347: ireturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name               Signature
        //  -----  ------  ----  -----------------  ------------------------------------
        //  0      348     0     compiler           Lorg/apache/xpath/compiler/Compiler;
        //  0      348     1     stepOpCodePos      I
        //  0      348     2     stepIndex          I
        //  343    5       3     stepType           I
        //  3      345     4     stepCount          I
        //  6      342     5     foundDorDS         Z
        //  9      339     6     foundSelf          Z
        //  12     336     7     foundDS            Z
        //  17     331     8     nodeTestType       I
        //  56     279     9     mightBeProximate   Z
        //  304    31      10    nextStepOpCodePos  I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static int analyze(final Compiler compiler, final int stepOpCodePos, final int stepIndex) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore          stepCount
        //     3: iconst_0       
        //     4: istore          analysisResult
        //     6: goto            435
        //     9: iinc            stepCount, 1
        //    12: aload_0         /* compiler */
        //    13: iload_1         /* stepOpCodePos */
        //    14: iload_3        
        //    15: invokestatic    org/apache/xpath/axes/WalkerFactory.analyzePredicate:(Lorg/apache/xpath/compiler/Compiler;II)Z
        //    18: istore          predAnalysis
        //    20: iload           predAnalysis
        //    22: ifeq            33
        //    25: iload           analysisResult
        //    27: sipush          4096
        //    30: ior            
        //    31: istore          analysisResult
        //    33: iload_3        
        //    34: tableswitch {
        //               44: 176
        //               45: 176
        //               46: 176
        //               47: 176
        //               48: 378
        //               49: 378
        //               50: 378
        //               51: 378
        //               52: 378
        //               53: 378
        //               54: 378
        //               55: 378
        //               56: 378
        //               57: 378
        //               58: 378
        //               59: 196
        //               60: 207
        //               61: 218
        //               62: 238
        //               63: 248
        //               64: 258
        //               65: 288
        //               66: 298
        //               67: 328
        //               68: 308
        //               69: 318
        //               70: 338
        //               71: 228
        //               72: 186
        //               73: 348
        //               74: 358
        //               75: 368
        //          default: 378
        //        }
        //   176: iload           analysisResult
        //   178: ldc             67108864
        //   180: ior            
        //   181: istore          analysisResult
        //   183: goto            402
        //   186: iload           analysisResult
        //   188: ldc             134217728
        //   190: ior            
        //   191: istore          analysisResult
        //   193: goto            402
        //   196: iload           analysisResult
        //   198: sipush          8192
        //   201: ior            
        //   202: istore          analysisResult
        //   204: goto            402
        //   207: iload           analysisResult
        //   209: sipush          16384
        //   212: ior            
        //   213: istore          analysisResult
        //   215: goto            402
        //   218: iload           analysisResult
        //   220: ldc             32768
        //   222: ior            
        //   223: istore          analysisResult
        //   225: goto            402
        //   228: iload           analysisResult
        //   230: ldc             2097152
        //   232: ior            
        //   233: istore          analysisResult
        //   235: goto            402
        //   238: iload           analysisResult
        //   240: ldc             65536
        //   242: ior            
        //   243: istore          analysisResult
        //   245: goto            402
        //   248: iload           analysisResult
        //   250: ldc             131072
        //   252: ior            
        //   253: istore          analysisResult
        //   255: goto            402
        //   258: iconst_2       
        //   259: iload           stepCount
        //   261: if_icmpne       278
        //   264: ldc             134217728
        //   266: iload           analysisResult
        //   268: if_icmpne       278
        //   271: iload           analysisResult
        //   273: ldc             536870912
        //   275: ior            
        //   276: istore          analysisResult
        //   278: iload           analysisResult
        //   280: ldc             262144
        //   282: ior            
        //   283: istore          analysisResult
        //   285: goto            402
        //   288: iload           analysisResult
        //   290: ldc             524288
        //   292: ior            
        //   293: istore          analysisResult
        //   295: goto            402
        //   298: iload           analysisResult
        //   300: ldc             1048576
        //   302: ior            
        //   303: istore          analysisResult
        //   305: goto            402
        //   308: iload           analysisResult
        //   310: ldc             8388608
        //   312: ior            
        //   313: istore          analysisResult
        //   315: goto            402
        //   318: iload           analysisResult
        //   320: ldc             16777216
        //   322: ior            
        //   323: istore          analysisResult
        //   325: goto            402
        //   328: iload           analysisResult
        //   330: ldc             4194304
        //   332: ior            
        //   333: istore          analysisResult
        //   335: goto            402
        //   338: iload           analysisResult
        //   340: ldc             33554432
        //   342: ior            
        //   343: istore          analysisResult
        //   345: goto            402
        //   348: iload           analysisResult
        //   350: ldc             -2147450880
        //   352: ior            
        //   353: istore          analysisResult
        //   355: goto            402
        //   358: iload           analysisResult
        //   360: ldc             -2147475456
        //   362: ior            
        //   363: istore          analysisResult
        //   365: goto            402
        //   368: iload           analysisResult
        //   370: ldc             -2143289344
        //   372: ior            
        //   373: istore          analysisResult
        //   375: goto            402
        //   378: new             Ljava/lang/RuntimeException;
        //   381: dup            
        //   382: ldc             "ER_NULL_ERROR_HANDLER"
        //   384: iconst_1       
        //   385: anewarray       Ljava/lang/Object;
        //   388: dup            
        //   389: iconst_0       
        //   390: iload_3        
        //   391: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   394: aastore        
        //   395: invokestatic    org/apache/xpath/res/XPATHMessages.createXPATHMessage:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   398: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   401: athrow         
        //   402: sipush          1033
        //   405: aload_0         /* compiler */
        //   406: iload_1         /* stepOpCodePos */
        //   407: iconst_3       
        //   408: iadd           
        //   409: invokevirtual   org/apache/xpath/compiler/OpMap.getOp:(I)I
        //   412: if_icmpne       422
        //   415: iload           analysisResult
        //   417: ldc             1073741824
        //   419: ior            
        //   420: istore          analysisResult
        //   422: aload_0         /* compiler */
        //   423: iload_1         /* stepOpCodePos */
        //   424: invokevirtual   org/apache/xpath/compiler/OpMap.getNextStepPos:(I)I
        //   427: istore_1        /* stepOpCodePos */
        //   428: iload_1         /* stepOpCodePos */
        //   429: ifge            435
        //   432: goto            446
        //   435: iconst_m1      
        //   436: aload_0         /* compiler */
        //   437: iload_1         /* stepOpCodePos */
        //   438: invokevirtual   org/apache/xpath/compiler/OpMap.getOp:(I)I
        //   441: dup            
        //   442: istore_3        /* stepType */
        //   443: if_icmpne       9
        //   446: iload           analysisResult
        //   448: iload           stepCount
        //   450: sipush          255
        //   453: iand           
        //   454: ior            
        //   455: istore          analysisResult
        //   457: iload           analysisResult
        //   459: ireturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name            Signature
        //  -----  ------  ----  --------------  ------------------------------------
        //  0      460     0     compiler        Lorg/apache/xpath/compiler/Compiler;
        //  0      460     1     stepOpCodePos   I
        //  0      460     2     stepIndex       I
        //  443    17      3     stepType        I
        //  3      457     4     stepCount       I
        //  6      454     5     analysisResult  I
        //  20     415     6     predAnalysis    Z
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean isDownwardAxisOfMany(final int axis) {
        return 5 == axis || 4 == axis || 6 == axis || 11 == axis;
    }
    
    static StepPattern loadSteps(final MatchPatternIterator mpi, final Compiler compiler, int stepOpCodePos, final int stepIndex) throws TransformerException {
        StepPattern step = null;
        StepPattern firstStep = null;
        StepPattern prevStep = null;
        final int analysis = analyze(compiler, stepOpCodePos, stepIndex);
        int stepType;
        while (-1 != (stepType = compiler.getOp(stepOpCodePos))) {
            step = createDefaultStepPattern(compiler, stepOpCodePos, mpi, analysis, firstStep, prevStep);
            if (null == firstStep) {
                firstStep = step;
            }
            else {
                step.setRelativePathPattern(prevStep);
            }
            prevStep = step;
            stepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
            if (stepOpCodePos < 0) {
                break;
            }
        }
        int axis = 13;
        final int paxis = 13;
        StepPattern tail = step;
        for (StepPattern pat = step; null != pat; pat = pat.getRelativePathPattern()) {
            final int nextAxis = pat.getAxis();
            pat.setAxis(axis);
            final int whatToShow = pat.getWhatToShow();
            if (whatToShow == 2 || whatToShow == 4096) {
                final int newAxis = (whatToShow == 2) ? 2 : 9;
                if (isDownwardAxisOfMany(axis)) {
                    final StepPattern attrPat = new StepPattern(whatToShow, pat.getNamespace(), pat.getLocalName(), newAxis, 0);
                    final XNumber score = pat.getStaticScore();
                    pat.setNamespace(null);
                    pat.setLocalName("*");
                    attrPat.setPredicates(pat.getPredicates());
                    pat.setPredicates(null);
                    pat.setWhatToShow(1);
                    final StepPattern rel = pat.getRelativePathPattern();
                    pat.setRelativePathPattern(attrPat);
                    attrPat.setRelativePathPattern(rel);
                    attrPat.setStaticScore(score);
                    if (11 == pat.getAxis()) {
                        pat.setAxis(15);
                    }
                    else if (4 == pat.getAxis()) {
                        pat.setAxis(5);
                    }
                    pat = attrPat;
                }
                else if (3 == pat.getAxis()) {
                    pat.setAxis(2);
                }
            }
            axis = nextAxis;
            tail = pat;
        }
        if (axis < 16) {
            final StepPattern selfPattern = new ContextMatchStepPattern(axis, paxis);
            final XNumber score2 = tail.getStaticScore();
            tail.setRelativePathPattern(selfPattern);
            tail.setStaticScore(score2);
            selfPattern.setStaticScore(score2);
        }
        return step;
    }
    
    private static StepPattern createDefaultStepPattern(final Compiler compiler, final int opPos, final MatchPatternIterator mpi, final int analysis, final StepPattern tail, final StepPattern head) throws TransformerException {
        final int stepType = compiler.getOp(opPos);
        boolean simpleInit = false;
        final int totalNumberWalkers = analysis & 0xFF;
        boolean prevIsOneStepDown = true;
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        int whatToShow = compiler.getWhatToShow(opPos);
        StepPattern ai = null;
        int axis = 0;
        int predicateAxis = 0;
        switch (stepType) {
            case 22:
            case 23:
            case 24:
            case 25: {
                prevIsOneStepDown = false;
                Expression expr = null;
                switch (stepType) {
                    case 22:
                    case 23:
                    case 24:
                    case 25: {
                        expr = compiler.compile(opPos);
                        break;
                    }
                    default: {
                        expr = compiler.compile(opPos + 2);
                        break;
                    }
                }
                axis = 20;
                predicateAxis = 20;
                ai = new FunctionPattern(expr, axis, predicateAxis);
                simpleInit = true;
                break;
            }
            case 50: {
                whatToShow = 1280;
                axis = 19;
                predicateAxis = 19;
                ai = new StepPattern(1280, axis, predicateAxis);
                break;
            }
            case 39: {
                whatToShow = 2;
                axis = 10;
                predicateAxis = 2;
                break;
            }
            case 49: {
                whatToShow = 4096;
                axis = 10;
                predicateAxis = 9;
                break;
            }
            case 37: {
                axis = 4;
                predicateAxis = 0;
                break;
            }
            case 40: {
                axis = 10;
                predicateAxis = 3;
                break;
            }
            case 38: {
                axis = 5;
                predicateAxis = 1;
                break;
            }
            case 48: {
                axis = 13;
                predicateAxis = 13;
                break;
            }
            case 45: {
                axis = 3;
                predicateAxis = 10;
                break;
            }
            case 47: {
                axis = 7;
                predicateAxis = 12;
                break;
            }
            case 46: {
                axis = 6;
                predicateAxis = 11;
                break;
            }
            case 44: {
                axis = 12;
                predicateAxis = 7;
                break;
            }
            case 43: {
                axis = 11;
                predicateAxis = 6;
                break;
            }
            case 42: {
                axis = 1;
                predicateAxis = 5;
                break;
            }
            case 41: {
                axis = 0;
                predicateAxis = 4;
                break;
            }
            default: {
                throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
            }
        }
        if (null == ai) {
            whatToShow = compiler.getWhatToShow(opPos);
            ai = new StepPattern(whatToShow, compiler.getStepNS(opPos), compiler.getStepLocalName(opPos), axis, predicateAxis);
        }
        final int argLen = compiler.getFirstPredicateOpPos(opPos);
        ai.setPredicates(compiler.getCompiledPredicates(argLen));
        return ai;
    }
    
    static boolean analyzePredicate(final Compiler compiler, final int opPos, final int stepType) throws TransformerException {
        switch (stepType) {
            case 22:
            case 23:
            case 24:
            case 25: {
                final int argLen = compiler.getArgLength(opPos);
                break;
            }
            default: {
                final int argLen = compiler.getArgLengthOfStep(opPos);
                break;
            }
        }
        final int pos = compiler.getFirstPredicateOpPos(opPos);
        final int nPredicates = compiler.countPredicates(pos);
        return nPredicates > 0;
    }
    
    private static AxesWalker createDefaultWalker(final Compiler compiler, final int opPos, final WalkingIterator lpi, final int analysis) {
        AxesWalker ai = null;
        final int stepType = compiler.getOp(opPos);
        boolean simpleInit = false;
        final int totalNumberWalkers = analysis & 0xFF;
        boolean prevIsOneStepDown = true;
        switch (stepType) {
            case 22:
            case 23:
            case 24:
            case 25: {
                prevIsOneStepDown = false;
                ai = new FilterExprWalker(lpi);
                simpleInit = true;
                break;
            }
            case 50: {
                ai = new AxesWalker(lpi, 19);
                break;
            }
            case 37: {
                prevIsOneStepDown = false;
                ai = new ReverseAxesWalker(lpi, 0);
                break;
            }
            case 38: {
                prevIsOneStepDown = false;
                ai = new ReverseAxesWalker(lpi, 1);
                break;
            }
            case 39: {
                ai = new AxesWalker(lpi, 2);
                break;
            }
            case 49: {
                ai = new AxesWalker(lpi, 9);
                break;
            }
            case 40: {
                ai = new AxesWalker(lpi, 3);
                break;
            }
            case 41: {
                prevIsOneStepDown = false;
                ai = new AxesWalker(lpi, 4);
                break;
            }
            case 42: {
                prevIsOneStepDown = false;
                ai = new AxesWalker(lpi, 5);
                break;
            }
            case 43: {
                prevIsOneStepDown = false;
                ai = new AxesWalker(lpi, 6);
                break;
            }
            case 44: {
                prevIsOneStepDown = false;
                ai = new AxesWalker(lpi, 7);
                break;
            }
            case 46: {
                prevIsOneStepDown = false;
                ai = new ReverseAxesWalker(lpi, 11);
                break;
            }
            case 47: {
                prevIsOneStepDown = false;
                ai = new ReverseAxesWalker(lpi, 12);
                break;
            }
            case 45: {
                prevIsOneStepDown = false;
                ai = new ReverseAxesWalker(lpi, 10);
                break;
            }
            case 48: {
                ai = new AxesWalker(lpi, 13);
                break;
            }
            default: {
                throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
            }
        }
        if (simpleInit) {
            ai.initNodeTest(-1);
        }
        else {
            final int whatToShow = compiler.getWhatToShow(opPos);
            if (0x0 == (whatToShow & 0x1043) || whatToShow == -1) {
                ai.initNodeTest(whatToShow);
            }
            else {
                ai.initNodeTest(whatToShow, compiler.getStepNS(opPos), compiler.getStepLocalName(opPos));
            }
        }
        return ai;
    }
    
    public static String getAnalysisString(final int analysis) {
        final StringBuffer buf = new StringBuffer();
        buf.append("count: " + getStepCount(analysis) + " ");
        if ((analysis & 0x40000000) != 0x0) {
            buf.append("NTANY|");
        }
        if ((analysis & 0x1000) != 0x0) {
            buf.append("PRED|");
        }
        if ((analysis & 0x2000) != 0x0) {
            buf.append("ANC|");
        }
        if ((analysis & 0x4000) != 0x0) {
            buf.append("ANCOS|");
        }
        if ((analysis & 0x8000) != 0x0) {
            buf.append("ATTR|");
        }
        if ((analysis & 0x10000) != 0x0) {
            buf.append("CH|");
        }
        if ((analysis & 0x20000) != 0x0) {
            buf.append("DESC|");
        }
        if ((analysis & 0x40000) != 0x0) {
            buf.append("DESCOS|");
        }
        if ((analysis & 0x80000) != 0x0) {
            buf.append("FOL|");
        }
        if ((analysis & 0x100000) != 0x0) {
            buf.append("FOLS|");
        }
        if ((analysis & 0x200000) != 0x0) {
            buf.append("NS|");
        }
        if ((analysis & 0x400000) != 0x0) {
            buf.append("P|");
        }
        if ((analysis & 0x800000) != 0x0) {
            buf.append("PREC|");
        }
        if ((analysis & 0x1000000) != 0x0) {
            buf.append("PRECS|");
        }
        if ((analysis & 0x2000000) != 0x0) {
            buf.append(".|");
        }
        if ((analysis & 0x4000000) != 0x0) {
            buf.append("FLT|");
        }
        if ((analysis & 0x8000000) != 0x0) {
            buf.append("R|");
        }
        return buf.toString();
    }
    
    public static boolean hasPredicate(final int analysis) {
        return 0x0 != (analysis & 0x1000);
    }
    
    public static boolean isWild(final int analysis) {
        return 0x0 != (analysis & 0x40000000);
    }
    
    public static boolean walksAncestors(final int analysis) {
        return isSet(analysis, 24576);
    }
    
    public static boolean walksAttributes(final int analysis) {
        return 0x0 != (analysis & 0x8000);
    }
    
    public static boolean walksNamespaces(final int analysis) {
        return 0x0 != (analysis & 0x200000);
    }
    
    public static boolean walksChildren(final int analysis) {
        return 0x0 != (analysis & 0x10000);
    }
    
    public static boolean walksDescendants(final int analysis) {
        return isSet(analysis, 393216);
    }
    
    public static boolean walksSubtree(final int analysis) {
        return isSet(analysis, 458752);
    }
    
    public static boolean walksSubtreeOnlyMaybeAbsolute(final int analysis) {
        return walksSubtree(analysis) && !walksExtraNodes(analysis) && !walksUp(analysis) && !walksSideways(analysis);
    }
    
    public static boolean walksSubtreeOnly(final int analysis) {
        return walksSubtreeOnlyMaybeAbsolute(analysis) && !isAbsolute(analysis);
    }
    
    public static boolean walksFilteredList(final int analysis) {
        return isSet(analysis, 67108864);
    }
    
    public static boolean walksSubtreeOnlyFromRootOrContext(final int analysis) {
        return walksSubtree(analysis) && !walksExtraNodes(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isSet(analysis, 67108864);
    }
    
    public static boolean walksInDocOrder(final int analysis) {
        return (walksSubtreeOnlyMaybeAbsolute(analysis) || walksExtraNodesOnly(analysis) || walksFollowingOnlyMaybeAbsolute(analysis)) && !isSet(analysis, 67108864);
    }
    
    public static boolean walksFollowingOnlyMaybeAbsolute(final int analysis) {
        return isSet(analysis, 35127296) && !walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis);
    }
    
    public static boolean walksUp(final int analysis) {
        return isSet(analysis, 4218880);
    }
    
    public static boolean walksSideways(final int analysis) {
        return isSet(analysis, 26738688);
    }
    
    public static boolean walksExtraNodes(final int analysis) {
        return isSet(analysis, 2129920);
    }
    
    public static boolean walksExtraNodesOnly(final int analysis) {
        return walksExtraNodes(analysis) && !isSet(analysis, 33554432) && !walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis);
    }
    
    public static boolean isAbsolute(final int analysis) {
        return isSet(analysis, 201326592);
    }
    
    public static boolean walksChildrenOnly(final int analysis) {
        return walksChildren(analysis) && !isSet(analysis, 33554432) && !walksExtraNodes(analysis) && !walksDescendants(analysis) && !walksUp(analysis) && !walksSideways(analysis) && (!isAbsolute(analysis) || isSet(analysis, 134217728));
    }
    
    public static boolean walksChildrenAndExtraAndSelfOnly(final int analysis) {
        return walksChildren(analysis) && !walksDescendants(analysis) && !walksUp(analysis) && !walksSideways(analysis) && (!isAbsolute(analysis) || isSet(analysis, 134217728));
    }
    
    public static boolean walksDescendantsAndExtraAndSelfOnly(final int analysis) {
        return !walksChildren(analysis) && walksDescendants(analysis) && !walksUp(analysis) && !walksSideways(analysis) && (!isAbsolute(analysis) || isSet(analysis, 134217728));
    }
    
    public static boolean walksSelfOnly(final int analysis) {
        return isSet(analysis, 33554432) && !walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis);
    }
    
    public static boolean walksUpOnly(final int analysis) {
        return !walksSubtree(analysis) && walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis);
    }
    
    public static boolean walksDownOnly(final int analysis) {
        return walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis);
    }
    
    public static boolean walksDownExtraOnly(final int analysis) {
        return walksSubtree(analysis) && walksExtraNodes(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis);
    }
    
    public static boolean canSkipSubtrees(final int analysis) {
        return isSet(analysis, 65536) | walksSideways(analysis);
    }
    
    public static boolean canCrissCross(final int analysis) {
        return !walksSelfOnly(analysis) && (!walksDownOnly(analysis) || canSkipSubtrees(analysis)) && !walksChildrenAndExtraAndSelfOnly(analysis) && !walksDescendantsAndExtraAndSelfOnly(analysis) && !walksUpOnly(analysis) && !walksExtraNodesOnly(analysis) && (walksSubtree(analysis) && (walksSideways(analysis) || walksUp(analysis) || canSkipSubtrees(analysis)));
    }
    
    public static boolean isNaturalDocOrder(final int analysis) {
        return !canCrissCross(analysis) && !isSet(analysis, 2097152) && !walksFilteredList(analysis) && walksInDocOrder(analysis);
    }
    
    private static boolean isNaturalDocOrder(final Compiler compiler, final int stepOpCodePos, final int stepIndex, final int analysis) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_3         /* analysis */
        //     1: invokestatic    org/apache/xpath/axes/WalkerFactory.canCrissCross:(I)Z
        //     4: ifeq            9
        //     7: iconst_0       
        //     8: ireturn        
        //     9: iload_3         /* analysis */
        //    10: ldc             2097152
        //    12: invokestatic    org/apache/xpath/axes/WalkerFactory.isSet:(II)Z
        //    15: ifeq            20
        //    18: iconst_0       
        //    19: ireturn        
        //    20: iload_3         /* analysis */
        //    21: ldc             1572864
        //    23: invokestatic    org/apache/xpath/axes/WalkerFactory.isSet:(II)Z
        //    26: ifeq            40
        //    29: iload_3         /* analysis */
        //    30: ldc             25165824
        //    32: invokestatic    org/apache/xpath/axes/WalkerFactory.isSet:(II)Z
        //    35: ifeq            40
        //    38: iconst_0       
        //    39: ireturn        
        //    40: iconst_0       
        //    41: istore          stepCount
        //    43: iconst_0       
        //    44: istore          foundWildAttribute
        //    46: iconst_0       
        //    47: istore          potentialDuplicateMakingStepCount
        //    49: goto            290
        //    52: iinc            stepCount, 1
        //    55: iload           4
        //    57: tableswitch {
        //               44: 230
        //               45: 230
        //               46: 230
        //               47: 230
        //               48: 247
        //               49: 247
        //               50: 247
        //               51: 247
        //               52: 247
        //               53: 247
        //               54: 247
        //               55: 247
        //               56: 247
        //               57: 247
        //               58: 247
        //               59: 230
        //               60: 230
        //               61: 200
        //               62: 240
        //               63: 230
        //               64: 230
        //               65: 230
        //               66: 230
        //               67: 230
        //               68: 230
        //               69: 230
        //               70: 240
        //               71: 230
        //               72: 240
        //               73: 200
        //               74: 230
        //               75: 230
        //          default: 247
        //        }
        //   200: iload           foundWildAttribute
        //   202: ifeq            207
        //   205: iconst_0       
        //   206: ireturn        
        //   207: aload_0         /* compiler */
        //   208: iload_1         /* stepOpCodePos */
        //   209: invokevirtual   org/apache/xpath/compiler/OpMap.getStepLocalName:(I)Ljava/lang/String;
        //   212: astore          localName
        //   214: aload           localName
        //   216: ldc             "*"
        //   218: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   221: ifeq            272
        //   224: iconst_1       
        //   225: istore          foundWildAttribute
        //   227: goto            272
        //   230: iload           potentialDuplicateMakingStepCount
        //   232: ifle            237
        //   235: iconst_0       
        //   236: ireturn        
        //   237: iinc            potentialDuplicateMakingStepCount, 1
        //   240: iload           foundWildAttribute
        //   242: ifeq            272
        //   245: iconst_0       
        //   246: ireturn        
        //   247: new             Ljava/lang/RuntimeException;
        //   250: dup            
        //   251: ldc             "ER_NULL_ERROR_HANDLER"
        //   253: iconst_1       
        //   254: anewarray       Ljava/lang/Object;
        //   257: dup            
        //   258: iconst_0       
        //   259: iload           4
        //   261: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   264: aastore        
        //   265: invokestatic    org/apache/xpath/res/XPATHMessages.createXPATHMessage:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   268: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   271: athrow         
        //   272: aload_0         /* compiler */
        //   273: iload_1         /* stepOpCodePos */
        //   274: invokevirtual   org/apache/xpath/compiler/OpMap.getNextStepPos:(I)I
        //   277: istore          nextStepOpCodePos
        //   279: iload           nextStepOpCodePos
        //   281: ifge            287
        //   284: goto            302
        //   287: iload           nextStepOpCodePos
        //   289: istore_1        /* stepOpCodePos */
        //   290: iconst_m1      
        //   291: aload_0         /* compiler */
        //   292: iload_1         /* stepOpCodePos */
        //   293: invokevirtual   org/apache/xpath/compiler/OpMap.getOp:(I)I
        //   296: dup            
        //   297: istore          stepType
        //   299: if_icmpne       52
        //   302: iconst_1       
        //   303: ireturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                               Signature
        //  -----  ------  ----  ---------------------------------  ------------------------------------
        //  0      304     0     compiler                           Lorg/apache/xpath/compiler/Compiler;
        //  0      304     1     stepOpCodePos                      I
        //  0      304     2     stepIndex                          I
        //  0      304     3     analysis                           I
        //  299    5       4     stepType                           I
        //  43     261     5     stepCount                          I
        //  46     258     6     foundWildAttribute                 Z
        //  49     255     7     potentialDuplicateMakingStepCount  I
        //  214    76      8     localName                          Ljava/lang/String;
        //  279    11      9     nextStepOpCodePos                  I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean isOneStep(final int analysis) {
        return (analysis & 0xFF) == 0x1;
    }
    
    public static int getStepCount(final int analysis) {
        return analysis & 0xFF;
    }
}
