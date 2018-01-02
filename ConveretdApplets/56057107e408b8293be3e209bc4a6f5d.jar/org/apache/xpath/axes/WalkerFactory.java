// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.compiler.OpMap;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;

public class WalkerFactory
{
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
    
    private static int analyze(final Compiler compiler, int stepOpCodePos, final int stepIndex) throws TransformerException {
        final int[] ops = compiler.getOpMap();
        int stepCount = 0;
        int analysisResult = 0;
        int stepType;
        while ((stepType = ops[stepOpCodePos]) != -1) {
            ++stepCount;
            final boolean predAnalysis = analyzePredicate(compiler, stepOpCodePos, stepType);
            if (predAnalysis) {
                analysisResult |= 0x1000;
            }
            switch (stepType) {
                case 22:
                case 23:
                case 24:
                case 25: {
                    analysisResult |= 0x4000000;
                    break;
                }
                case 50: {
                    analysisResult |= 0x8000000;
                    break;
                }
                case 37: {
                    analysisResult |= 0x2000;
                    break;
                }
                case 38: {
                    analysisResult |= 0x4000;
                    break;
                }
                case 39: {
                    analysisResult |= 0x8000;
                    break;
                }
                case 49: {
                    analysisResult |= 0x200000;
                    break;
                }
                case 40: {
                    analysisResult |= 0x10000;
                    break;
                }
                case 41: {
                    analysisResult |= 0x20000;
                    break;
                }
                case 42: {
                    if (stepCount == 2 && analysisResult == 134217728) {
                        analysisResult |= 0x20000000;
                    }
                    analysisResult |= 0x40000;
                    break;
                }
                case 43: {
                    analysisResult |= 0x40000;
                    break;
                }
                case 44: {
                    analysisResult |= 0x100000;
                    break;
                }
                case 46: {
                    analysisResult |= 0x800000;
                    break;
                }
                case 47: {
                    analysisResult |= 0x1000000;
                    break;
                }
                case 45: {
                    analysisResult |= 0x400000;
                    break;
                }
                case 48: {
                    analysisResult |= 0x2000000;
                    break;
                }
                case 51: {
                    analysisResult |= 0x80008000;
                    break;
                }
                case 52: {
                    analysisResult |= 0x80002000;
                    break;
                }
                case 53: {
                    analysisResult |= 0x80400000;
                    break;
                }
                default: {
                    throw new RuntimeException("Programmer's assertion: unknown opcode: " + stepType);
                }
            }
            if (ops[stepOpCodePos + 3] == 1033) {
                analysisResult |= 0x40000000;
            }
            stepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
            if (stepOpCodePos < 0) {
                break;
            }
        }
        analysisResult |= (stepCount & 0xFF);
        return analysisResult;
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
    
    private static AxesWalker createDefaultWalker(final Compiler compiler, final int opPos, final LocPathIterator lpi, final int analysis) {
        final int stepType = compiler.getOp(opPos);
        boolean simpleInit = false;
        final int totalNumberWalkers = analysis & 0xFF;
        boolean prevIsOneStepDown = true;
        AxesWalker ai = null;
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
                if ((analysis & 0xF7DE6F00) == 0x0) {
                    ai = new RootWalkerMultiStep(lpi);
                    break;
                }
                ai = new RootWalker(lpi);
                break;
            }
            case 37: {
                prevIsOneStepDown = false;
                ai = new AncestorWalker(lpi);
                break;
            }
            case 38: {
                prevIsOneStepDown = false;
                ai = new AncestorOrSelfWalker(lpi);
                break;
            }
            case 39: {
                if (totalNumberWalkers == 1) {
                    ai = new AttributeWalkerOneStep(lpi);
                    break;
                }
                ai = new AttributeWalker(lpi);
                break;
            }
            case 49: {
                ai = new NamespaceWalker(lpi);
                break;
            }
            case 40: {
                if (totalNumberWalkers == 1) {
                    ai = new ChildWalkerOneStep(lpi);
                    break;
                }
                if ((analysis & 0xF7DE6F00) == 0x0) {
                    ai = new ChildWalkerMultiStep(lpi);
                    break;
                }
                ai = new ChildWalker(lpi);
                break;
            }
            case 41: {
                prevIsOneStepDown = false;
                ai = new DescendantWalker(lpi);
                break;
            }
            case 42: {
                prevIsOneStepDown = false;
                ai = new DescendantOrSelfWalker(lpi);
                break;
            }
            case 43: {
                prevIsOneStepDown = false;
                ai = new FollowingWalker(lpi);
                break;
            }
            case 44: {
                prevIsOneStepDown = false;
                ai = new FollowingSiblingWalker(lpi);
                break;
            }
            case 46: {
                prevIsOneStepDown = false;
                ai = new PrecedingWalker(lpi);
                break;
            }
            case 47: {
                prevIsOneStepDown = false;
                ai = new PrecedingSiblingWalker(lpi);
                break;
            }
            case 45: {
                prevIsOneStepDown = false;
                ai = new ParentWalker(lpi);
                break;
            }
            case 48: {
                if (totalNumberWalkers == 1) {
                    ai = new SelfWalkerOneStep(lpi);
                    break;
                }
                ai = new SelfWalker(lpi);
                break;
            }
            case 51: {
                ai = new AttributeWalker(lpi);
                break;
            }
            case 52: {
                ai = new ChildWalker(lpi);
                break;
            }
            case 53: {
                ai = new ChildWalker(lpi);
                break;
            }
            default: {
                throw new RuntimeException("Programmer's assertion: unknown opcode: " + stepType);
            }
        }
        if (simpleInit) {
            ai.initNodeTest(-1);
        }
        else {
            final int whatToShow = compiler.getWhatToShow(opPos);
            if ((whatToShow & 0x43) == 0x0 || whatToShow == -1) {
                ai.initNodeTest(whatToShow);
            }
            else {
                ai.initNodeTest(whatToShow, compiler.getStepNS(opPos), compiler.getStepLocalName(opPos));
            }
        }
        return ai;
    }
    
    static AxesWalker loadOneWalker(final LocPathIterator lpi, final Compiler compiler, final int stepOpCodePos) throws TransformerException {
        AxesWalker firstWalker = null;
        final int stepType = compiler.getOpMap()[stepOpCodePos];
        if (stepType != -1) {
            firstWalker = createDefaultWalker(compiler, stepType, lpi, 0);
            firstWalker.init(compiler, stepOpCodePos, stepType);
        }
        return firstWalker;
    }
    
    static AxesWalker loadWalkers(final LocPathIterator lpi, final Compiler compiler, int stepOpCodePos, final int stepIndex) throws TransformerException {
        AxesWalker firstWalker = null;
        AxesWalker prevWalker = null;
        final int[] ops = compiler.getOpMap();
        final int analysis = analyze(compiler, stepOpCodePos, stepIndex);
        int stepType;
        while ((stepType = ops[stepOpCodePos]) != -1) {
            final AxesWalker walker = createDefaultWalker(compiler, stepOpCodePos, lpi, analysis);
            walker.init(compiler, stepOpCodePos, stepType);
            if (firstWalker == null) {
                firstWalker = walker;
            }
            else {
                prevWalker.setNextWalker(walker);
                walker.setPrevWalker(prevWalker);
            }
            prevWalker = walker;
            stepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
            if (stepOpCodePos < 0) {
                break;
            }
        }
        return firstWalker;
    }
    
    public static LocPathIterator newLocPathIterator(final Compiler compiler, final int opPos) throws TransformerException {
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        final int analysis = analyze(compiler, firstStepPos, 0);
        if ((analysis & 0x100FF) == 0x10001) {
            if ((analysis & 0x40000000) == 0x40000000 && (analysis & 0x1000) != 0x1000) {
                return new ChildIterator(compiler, opPos, analysis);
            }
            return new ChildTestIterator(compiler, opPos, analysis);
        }
        else {
            if ((analysis & 0x80FF) == 0x8001) {
                return new AttributeIterator(compiler, opPos, analysis);
            }
            if ((analysis & 0x600FF) == 0x60001 || (analysis & 0x20400FF) == 0x2040002 || (analysis & 0x420500FF) == 0x42050003 || (analysis & 0x680500FF) == 0x68050003) {
                return new DescendantIterator(compiler, opPos, analysis);
            }
            return new LocPathIterator(compiler, opPos, analysis, true);
        }
    }
}
