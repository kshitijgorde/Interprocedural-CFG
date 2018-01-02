// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jcodings.Encoding;
import java.util.List;
import java.util.Map;
import org.jruby.ast.NodeType;
import org.jruby.parser.StaticScope;
import org.jruby.util.ByteList;
import java.math.BigInteger;
import org.jruby.lexer.yacc.ISourcePosition;

public interface BodyCompiler
{
    void endBody();
    
    void consumeCurrentValue();
    
    void duplicateCurrentValue();
    
    void aprintln();
    
    void swapValues();
    
    void reverseValues(final int p0);
    
    void lineNumber(final ISourcePosition p0);
    
    VariableCompiler getVariableCompiler();
    
    InvocationCompiler getInvocationCompiler();
    
    void retrieveSelf();
    
    void retrieveSelfClass();
    
    void retrieveClassVariable(final String p0);
    
    void assignClassVariable(final String p0);
    
    void assignClassVariable(final String p0, final CompilerCallback p1);
    
    void declareClassVariable(final String p0);
    
    void declareClassVariable(final String p0, final CompilerCallback p1);
    
    void createNewFixnum(final long p0);
    
    void createNewFloat(final double p0);
    
    void createNewBignum(final BigInteger p0);
    
    void createNewString(final ByteList p0, final int p1);
    
    void createNewString(final ArrayCallback p0, final int p1);
    
    void createNewSymbol(final ArrayCallback p0, final int p1);
    
    void createNewSymbol(final String p0);
    
    void createObjectArray(final Object[] p0, final ArrayCallback p1);
    
    void createObjectArray(final int p0);
    
    void createNewArray(final boolean p0);
    
    void createNewArray(final Object[] p0, final ArrayCallback p1, final boolean p2);
    
    void createNewLiteralArray(final Object[] p0, final ArrayCallback p1, final boolean p2);
    
    void createEmptyArray();
    
    void createEmptyHash();
    
    void createNewHash(final Object p0, final ArrayCallback p1, final int p2);
    
    void createNewLiteralHash(final Object p0, final ArrayCallback p1, final int p2);
    
    void createNewHash19(final Object p0, final ArrayCallback p1, final int p2);
    
    void createNewRange(final CompilerCallback p0, final boolean p1);
    
    void createNewLambda(final CompilerCallback p0);
    
    void performBooleanBranch(final BranchCallback p0, final BranchCallback p1);
    
    void performLogicalAnd(final BranchCallback p0);
    
    void performLogicalOr(final BranchCallback p0);
    
    void performBooleanLoopSafe(final BranchCallback p0, final BranchCallback p1, final boolean p2);
    
    void performBooleanLoop(final BranchCallback p0, final BranchCallback p1, final boolean p2);
    
    void performBooleanLoopLight(final BranchCallback p0, final BranchCallback p1, final boolean p2);
    
    void performReturn();
    
    void createNewClosure(final String p0, final int p1, final StaticScope p2, final int p3, final CompilerCallback p4, final CompilerCallback p5, final boolean p6, final NodeType p7, final ASTInspector p8);
    
    void createNewClosure19(final String p0, final int p1, final StaticScope p2, final int p3, final CompilerCallback p4, final CompilerCallback p5, final boolean p6, final NodeType p7, final String p8, final ASTInspector p9);
    
    void createNewForLoop(final int p0, final CompilerCallback p1, final CompilerCallback p2, final boolean p3, final NodeType p4, final ASTInspector p5);
    
    void defineNewMethod(final String p0, final int p1, final StaticScope p2, final CompilerCallback p3, final CompilerCallback p4, final CompilerCallback p5, final ASTInspector p6, final boolean p7, final String p8, final int p9, final String p10);
    
    void defineAlias(final CompilerCallback p0);
    
    void assignConstantInCurrent(final String p0);
    
    void assignConstantInModule(final String p0);
    
    void assignConstantInObject(final String p0);
    
    void retrieveConstant(final String p0);
    
    void retrieveConstantFromModule(final String p0);
    
    void retrieveConstantFromObject(final String p0);
    
    void loadFalse();
    
    void loadTrue();
    
    void loadNil();
    
    void loadNull();
    
    void loadObject();
    
    void retrieveInstanceVariable(final String p0);
    
    void assignInstanceVariable(final String p0);
    
    void assignInstanceVariable(final String p0, final CompilerCallback p1);
    
    void assignGlobalVariable(final String p0);
    
    void assignGlobalVariable(final String p0, final CompilerCallback p1);
    
    void retrieveGlobalVariable(final String p0);
    
    void negateCurrentValue();
    
    void splatCurrentValue(final String p0);
    
    void singlifySplattedValue();
    
    void singlifySplattedValue19();
    
    void forEachInValueArray(final int p0, final int p1, final Object p2, final ArrayCallback p3, final CompilerCallback p4);
    
    void forEachInValueArray(final int p0, final int p1, final Object p2, final int p3, final Object p4, final ArrayCallback p5, final CompilerCallback p6);
    
    void ensureRubyArray();
    
    void ensureMultipleAssignableRubyArray(final boolean p0);
    
    void issueBreakEvent(final CompilerCallback p0);
    
    void issueNextEvent(final CompilerCallback p0);
    
    void issueRedoEvent();
    
    void issueRetryEvent();
    
    void asString();
    
    void nthRef(final int p0);
    
    void match();
    
    void match2(final CompilerCallback p0);
    
    void match2Capture(final CompilerCallback p0, final int[] p1);
    
    void match3();
    
    void createNewRegexp(final ByteList p0, final int p1);
    
    void createNewRegexp(final CompilerCallback p0, final int p1);
    
    void pollThreadEvents();
    
    void literal(final String p0);
    
    void backref();
    
    void backrefMethod(final String p0);
    
    void nullToNil();
    
    void protect(final BranchCallback p0, final BranchCallback p1, final Class p2);
    
    void rescue(final BranchCallback p0, final Class p1, final BranchCallback p2, final Class p3);
    
    void performRescue(final BranchCallback p0, final BranchCallback p1, final BranchCallback p2, final boolean p3);
    
    void performRescueLight(final BranchCallback p0, final BranchCallback p1, final BranchCallback p2, final boolean p3);
    
    void performEnsure(final BranchCallback p0, final BranchCallback p1);
    
    void inDefined();
    
    void outDefined();
    
    void stringOrNil();
    
    void pushNull();
    
    void pushString(final String p0);
    
    void pushByteList(final ByteList p0);
    
    void isMethodBound(final String p0, final BranchCallback p1, final BranchCallback p2);
    
    void hasBlock(final BranchCallback p0, final BranchCallback p1);
    
    void isGlobalDefined(final String p0, final BranchCallback p1, final BranchCallback p2);
    
    void isConstantDefined(final String p0, final BranchCallback p1, final BranchCallback p2);
    
    void isInstanceVariableDefined(final String p0, final BranchCallback p1, final BranchCallback p2);
    
    void isClassVarDefined(final String p0, final BranchCallback p1, final BranchCallback p2);
    
    Object getNewEnding();
    
    void ifNull(final Object p0);
    
    void isNil(final BranchCallback p0, final BranchCallback p1);
    
    void isNull(final BranchCallback p0, final BranchCallback p1);
    
    void ifNotNull(final Object p0);
    
    void setEnding(final Object p0);
    
    void go(final Object p0);
    
    void isConstantBranch(final BranchCallback p0, final String p1);
    
    void metaclass();
    
    void getVisibilityFor(final String p0);
    
    void isPrivate(final Object p0, final int p1);
    
    void isNotProtected(final Object p0, final int p1);
    
    void selfIsKindOf(final Object p0);
    
    void loadCurrentModule();
    
    void notIsModuleAndClassVarDefined(final String p0, final Object p1);
    
    void loadSelf();
    
    void ifSingleton(final Object p0);
    
    void getInstanceVariable(final String p0);
    
    void getFrameName();
    
    void getFrameKlazz();
    
    void superClass();
    
    void attached();
    
    void ifNotSuperMethodBound(final Object p0);
    
    void isInstanceOf(final Class p0, final BranchCallback p1, final BranchCallback p2);
    
    void isCaptured(final int p0, final BranchCallback p1, final BranchCallback p2);
    
    void concatArrays();
    
    void appendToArray();
    
    void convertToJavaArray();
    
    void aryToAry();
    
    void toJavaString();
    
    void aliasGlobal(final String p0, final String p1);
    
    void undefMethod(final CompilerCallback p0);
    
    void defineClass(final String p0, final StaticScope p1, final CompilerCallback p2, final CompilerCallback p3, final CompilerCallback p4, final CompilerCallback p5, final ASTInspector p6);
    
    void defineModule(final String p0, final StaticScope p1, final CompilerCallback p2, final CompilerCallback p3, final ASTInspector p4);
    
    void unwrapPassedBlock();
    
    void performBackref(final char p0);
    
    void callZSuper(final CompilerCallback p0);
    
    void appendToObjectArray();
    
    void checkIsExceptionHandled(final ArgumentsCallback p0);
    
    void rethrowException();
    
    void loadClass(final String p0);
    
    void loadStandardError();
    
    void unwrapRaiseException();
    
    void loadException();
    
    void setFilePosition(final ISourcePosition p0);
    
    void setLinePosition(final ISourcePosition p0);
    
    void checkWhenWithSplat();
    
    void createNewEndBlock(final CompilerCallback p0);
    
    void runBeginBlock(final StaticScope p0, final CompilerCallback p1);
    
    void rethrowIfSystemExit();
    
    BodyCompiler chainToMethod(final String p0);
    
    BodyCompiler outline(final String p0);
    
    void wrapJavaException();
    
    void literalSwitch(final int[] p0, final Object[] p1, final ArrayCallback p2, final CompilerCallback p3);
    
    void typeCheckBranch(final Class p0, final BranchCallback p1, final BranchCallback p2);
    
    void loadFilename();
    
    void storeExceptionInErrorInfo();
    
    void clearErrorInfo();
    
    void compileSequencedConditional(final CompilerCallback p0, final FastSwitchType p1, final Map<CompilerCallback, int[]> p2, final List<ArgumentsCallback> p3, final List<CompilerCallback> p4, final CompilerCallback p5);
    
    void raiseTypeError(final String p0);
    
    void traceLine();
    
    void traceClass();
    
    void traceEnd();
    
    String getNativeMethodName();
    
    void preMultiAssign(final int p0, final boolean p1);
    
    boolean isSimpleRoot();
    
    void argsPush();
    
    void argsCat();
    
    void loadEncoding(final Encoding p0);
    
    void definedCall(final String p0);
    
    void definedNot();
}
