// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import javax.xml.transform.TransformerException;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XRTreeFrag;
import org.apache.xpath.objects.XObject;

public class MethodResolver
{
    public static final int STATIC_ONLY = 1;
    public static final int INSTANCE_ONLY = 2;
    public static final int STATIC_AND_INSTANCE = 3;
    public static final int DYNAMIC = 4;
    private static final int SCOREBASE = 1;
    static ConversionInfo[] m_javaObjConversions;
    static ConversionInfo[] m_booleanConversions;
    static ConversionInfo[] m_numberConversions;
    static ConversionInfo[] m_stringConversions;
    static ConversionInfo[] m_rtfConversions;
    static ConversionInfo[] m_nodesetConversions;
    static ConversionInfo[][] m_conversions;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$org$w3c$dom$traversal$NodeIterator;
    static /* synthetic */ Class class$org$w3c$dom$DocumentFragment;
    static /* synthetic */ Class class$org$w3c$dom$Node;
    static /* synthetic */ Class class$org$w3c$dom$NodeList;
    static /* synthetic */ Class class$org$apache$xalan$extensions$ExpressionContext;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExtensionCall;
    static /* synthetic */ Class class$org$apache$xalan$extensions$XSLProcessorContext;
    static /* synthetic */ Class class$java$lang$Class;
    
    static {
        MethodResolver.m_javaObjConversions = new ConversionInfo[] { new ConversionInfo(Double.TYPE, 11), new ConversionInfo(Float.TYPE, 12), new ConversionInfo(Long.TYPE, 13), new ConversionInfo(Integer.TYPE, 14), new ConversionInfo(Short.TYPE, 15), new ConversionInfo(Character.TYPE, 16), new ConversionInfo(Byte.TYPE, 17), new ConversionInfo((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")), 18) };
        MethodResolver.m_booleanConversions = new ConversionInfo[] { new ConversionInfo(Boolean.TYPE, 0), new ConversionInfo((MethodResolver.class$java$lang$Boolean != null) ? MethodResolver.class$java$lang$Boolean : (MethodResolver.class$java$lang$Boolean = class$("java.lang.Boolean")), 1), new ConversionInfo((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")), 1), new ConversionInfo((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")), 2) };
        MethodResolver.m_numberConversions = new ConversionInfo[] { new ConversionInfo(Double.TYPE, 0), new ConversionInfo((MethodResolver.class$java$lang$Double != null) ? MethodResolver.class$java$lang$Double : (MethodResolver.class$java$lang$Double = class$("java.lang.Double")), 1), new ConversionInfo(Float.TYPE, 3), new ConversionInfo(Long.TYPE, 4), new ConversionInfo(Integer.TYPE, 5), new ConversionInfo(Short.TYPE, 6), new ConversionInfo(Character.TYPE, 7), new ConversionInfo(Byte.TYPE, 8), new ConversionInfo(Boolean.TYPE, 9), new ConversionInfo((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")), 10), new ConversionInfo((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")), 11) };
        MethodResolver.m_stringConversions = new ConversionInfo[] { new ConversionInfo((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")), 0), new ConversionInfo((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")), 1), new ConversionInfo(Character.TYPE, 2), new ConversionInfo(Double.TYPE, 3), new ConversionInfo(Float.TYPE, 3), new ConversionInfo(Long.TYPE, 3), new ConversionInfo(Integer.TYPE, 3), new ConversionInfo(Short.TYPE, 3), new ConversionInfo(Byte.TYPE, 3), new ConversionInfo(Boolean.TYPE, 4) };
        MethodResolver.m_rtfConversions = new ConversionInfo[] { new ConversionInfo((MethodResolver.class$org$w3c$dom$traversal$NodeIterator != null) ? MethodResolver.class$org$w3c$dom$traversal$NodeIterator : (MethodResolver.class$org$w3c$dom$traversal$NodeIterator = class$("org.w3c.dom.traversal.NodeIterator")), 0), new ConversionInfo((MethodResolver.class$org$w3c$dom$DocumentFragment != null) ? MethodResolver.class$org$w3c$dom$DocumentFragment : (MethodResolver.class$org$w3c$dom$DocumentFragment = class$("org.w3c.dom.DocumentFragment")), 1), new ConversionInfo((MethodResolver.class$org$w3c$dom$Node != null) ? MethodResolver.class$org$w3c$dom$Node : (MethodResolver.class$org$w3c$dom$Node = class$("org.w3c.dom.Node")), 2), new ConversionInfo((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")), 3), new ConversionInfo(Boolean.TYPE, 4), new ConversionInfo((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")), 5), new ConversionInfo(Character.TYPE, 6), new ConversionInfo(Double.TYPE, 7), new ConversionInfo(Float.TYPE, 7), new ConversionInfo(Long.TYPE, 7), new ConversionInfo(Integer.TYPE, 7), new ConversionInfo(Short.TYPE, 7), new ConversionInfo(Byte.TYPE, 7), new ConversionInfo(Boolean.TYPE, 8) };
        MethodResolver.m_nodesetConversions = new ConversionInfo[] { new ConversionInfo((MethodResolver.class$org$w3c$dom$traversal$NodeIterator != null) ? MethodResolver.class$org$w3c$dom$traversal$NodeIterator : (MethodResolver.class$org$w3c$dom$traversal$NodeIterator = class$("org.w3c.dom.traversal.NodeIterator")), 0), new ConversionInfo((MethodResolver.class$org$w3c$dom$NodeList != null) ? MethodResolver.class$org$w3c$dom$NodeList : (MethodResolver.class$org$w3c$dom$NodeList = class$("org.w3c.dom.NodeList")), 1), new ConversionInfo((MethodResolver.class$org$w3c$dom$Node != null) ? MethodResolver.class$org$w3c$dom$Node : (MethodResolver.class$org$w3c$dom$Node = class$("org.w3c.dom.Node")), 2), new ConversionInfo((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")), 3), new ConversionInfo(Boolean.TYPE, 4), new ConversionInfo((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")), 5), new ConversionInfo(Character.TYPE, 6), new ConversionInfo(Double.TYPE, 7), new ConversionInfo(Float.TYPE, 7), new ConversionInfo(Long.TYPE, 7), new ConversionInfo(Integer.TYPE, 7), new ConversionInfo(Short.TYPE, 7), new ConversionInfo(Byte.TYPE, 7), new ConversionInfo(Boolean.TYPE, 8) };
        MethodResolver.m_conversions = new ConversionInfo[][] { MethodResolver.m_javaObjConversions, MethodResolver.m_booleanConversions, MethodResolver.m_numberConversions, MethodResolver.m_stringConversions, MethodResolver.m_nodesetConversions, MethodResolver.m_rtfConversions };
    }
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    static Object convert(Object xsltObj, final Class javaClass) throws TransformerException {
        if (xsltObj instanceof XObject) {
            final XObject xobj = (XObject)xsltObj;
            final int xsltClassType = xobj.getType();
            switch (xsltClassType) {
                case -1: {
                    return null;
                }
                case 1: {
                    if (javaClass == ((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")))) {
                        return xobj.str();
                    }
                    return new Boolean(xobj.bool());
                }
                case 2: {
                    if (javaClass == ((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")))) {
                        return xobj.str();
                    }
                    if (javaClass == Boolean.TYPE) {
                        return new Boolean(xobj.bool());
                    }
                    return convertDoubleToNumber(xobj.num(), javaClass);
                }
                case 3: {
                    if (javaClass == ((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String"))) || javaClass == ((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")))) {
                        return xobj.str();
                    }
                    if (javaClass == Character.TYPE) {
                        final String str = xobj.str();
                        if (str.length() > 0) {
                            return new Character(str.charAt(0));
                        }
                        return null;
                    }
                    else {
                        if (javaClass == Boolean.TYPE) {
                            return new Boolean(xobj.bool());
                        }
                        return convertDoubleToNumber(xobj.num(), javaClass);
                    }
                    break;
                }
                case 5: {
                    if (javaClass == ((MethodResolver.class$org$w3c$dom$traversal$NodeIterator != null) ? MethodResolver.class$org$w3c$dom$traversal$NodeIterator : (MethodResolver.class$org$w3c$dom$traversal$NodeIterator = class$("org.w3c.dom.traversal.NodeIterator"))) || javaClass == ((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")))) {
                        return ((XRTreeFrag)xobj).asNodeIterator();
                    }
                    if (javaClass == ((MethodResolver.class$org$w3c$dom$NodeList != null) ? MethodResolver.class$org$w3c$dom$NodeList : (MethodResolver.class$org$w3c$dom$NodeList = class$("org.w3c.dom.NodeList")))) {
                        return ((XRTreeFrag)xobj).convertToNodeset();
                    }
                    if (javaClass == ((MethodResolver.class$org$w3c$dom$Node != null) ? MethodResolver.class$org$w3c$dom$Node : (MethodResolver.class$org$w3c$dom$Node = class$("org.w3c.dom.Node")))) {
                        return xobj.rtree().getFirstChild();
                    }
                    if (javaClass == ((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")))) {
                        return xobj.str();
                    }
                    if (javaClass == Boolean.TYPE) {
                        return new Boolean(xobj.bool());
                    }
                    if (javaClass.isPrimitive()) {
                        return convertDoubleToNumber(xobj.num(), javaClass);
                    }
                    final Node child = xobj.rtree().getFirstChild();
                    if (javaClass.isAssignableFrom(child.getClass())) {
                        return child;
                    }
                    return null;
                }
                case 4: {
                    if (javaClass == ((MethodResolver.class$org$w3c$dom$traversal$NodeIterator != null) ? MethodResolver.class$org$w3c$dom$traversal$NodeIterator : (MethodResolver.class$org$w3c$dom$traversal$NodeIterator = class$("org.w3c.dom.traversal.NodeIterator"))) || javaClass == ((MethodResolver.class$java$lang$Object != null) ? MethodResolver.class$java$lang$Object : (MethodResolver.class$java$lang$Object = class$("java.lang.Object")))) {
                        return xobj.nodeset();
                    }
                    if (javaClass == ((MethodResolver.class$org$w3c$dom$NodeList != null) ? MethodResolver.class$org$w3c$dom$NodeList : (MethodResolver.class$org$w3c$dom$NodeList = class$("org.w3c.dom.NodeList")))) {
                        return xobj.mutableNodeset();
                    }
                    if (javaClass == ((MethodResolver.class$org$w3c$dom$Node != null) ? MethodResolver.class$org$w3c$dom$Node : (MethodResolver.class$org$w3c$dom$Node = class$("org.w3c.dom.Node")))) {
                        final NodeIterator ni = xobj.nodeset();
                        return ni.nextNode();
                    }
                    if (javaClass == ((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")))) {
                        return xobj.str();
                    }
                    if (javaClass == Boolean.TYPE) {
                        return new Boolean(xobj.bool());
                    }
                    if (javaClass.isPrimitive()) {
                        return convertDoubleToNumber(xobj.num(), javaClass);
                    }
                    final Node child = xobj.nodeset().nextNode();
                    if (javaClass.isAssignableFrom(child.getClass())) {
                        return child;
                    }
                    return null;
                }
                default: {
                    xsltObj = xobj.object();
                    break;
                }
            }
        }
        if (javaClass == ((MethodResolver.class$java$lang$String != null) ? MethodResolver.class$java$lang$String : (MethodResolver.class$java$lang$String = class$("java.lang.String")))) {
            return xsltObj.toString();
        }
        if (javaClass.isPrimitive()) {
            final XString xstr = new XString(xsltObj.toString());
            final double num = xstr.num();
            return convertDoubleToNumber(num, javaClass);
        }
        if (javaClass == ((MethodResolver.class$java$lang$Class != null) ? MethodResolver.class$java$lang$Class : (MethodResolver.class$java$lang$Class = class$("java.lang.Class")))) {
            return xsltObj.getClass();
        }
        return xsltObj;
    }
    
    static Object convertDoubleToNumber(final double num, final Class javaClass) {
        if (javaClass == Double.TYPE || javaClass == ((MethodResolver.class$java$lang$Double != null) ? MethodResolver.class$java$lang$Double : (MethodResolver.class$java$lang$Double = class$("java.lang.Double")))) {
            return new Double(num);
        }
        if (javaClass == Float.TYPE) {
            return new Float(num);
        }
        if (javaClass == Long.TYPE) {
            return new Long((long)num);
        }
        if (javaClass == Integer.TYPE) {
            return new Integer((int)num);
        }
        if (javaClass == Short.TYPE) {
            return new Short((short)num);
        }
        if (javaClass == Character.TYPE) {
            return new Character((char)num);
        }
        if (javaClass == Byte.TYPE) {
            return new Byte((byte)num);
        }
        return new Double(num);
    }
    
    public static void convertParams(final Object[] argsIn, final Object[][] argsOut, final Class[] paramTypes, final ExpressionContext exprContext) throws TransformerException {
        if (paramTypes == null) {
            argsOut[0] = null;
        }
        else {
            final int nParams = paramTypes.length;
            argsOut[0] = new Object[nParams];
            int paramIndex = 0;
            if (nParams > 0 && ((MethodResolver.class$org$apache$xalan$extensions$ExpressionContext != null) ? MethodResolver.class$org$apache$xalan$extensions$ExpressionContext : (MethodResolver.class$org$apache$xalan$extensions$ExpressionContext = class$("org.apache.xalan.extensions.ExpressionContext"))).isAssignableFrom(paramTypes[0])) {
                argsOut[0][0] = exprContext;
                ++paramIndex;
            }
            if (argsIn != null) {
                int i = argsIn.length - nParams + paramIndex;
                while (paramIndex < nParams) {
                    argsOut[0][paramIndex] = convert(argsIn[i], paramTypes[paramIndex]);
                    ++i;
                    ++paramIndex;
                }
            }
        }
    }
    
    private static String errArgs(final Object[] xsltArgs, final int startingArg) {
        final StringBuffer returnArgs = new StringBuffer();
        for (int i = startingArg; i < xsltArgs.length; ++i) {
            if (i != startingArg) {
                returnArgs.append(", ");
            }
            if (xsltArgs[i] instanceof XObject) {
                returnArgs.append(((XObject)xsltArgs[i]).getTypeString());
            }
            else {
                returnArgs.append(xsltArgs[i].getClass().getName());
            }
        }
        return returnArgs.toString();
    }
    
    private static String errString(final String callType, final String searchType, final Class classObj, final String funcName, final int searchMethod, final Object[] xsltArgs) {
        final String resultString = "For extension " + callType + ", could not find " + searchType + " ";
        switch (searchMethod) {
            case 1: {
                return String.valueOf(resultString) + "static " + classObj.getName() + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").";
            }
            case 2: {
                return String.valueOf(resultString) + classObj.getName() + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").";
            }
            case 3: {
                return String.valueOf(resultString) + classObj.getName() + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").\n" + "Checked both static and instance methods.";
            }
            case 4: {
                return String.valueOf(resultString) + "static " + classObj.getName() + "." + funcName + "([ExpressionContext, ]" + errArgs(xsltArgs, 0) + ") nor\n" + classObj + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 1) + ").";
            }
            default: {
                if (callType.equals("function")) {
                    return String.valueOf(resultString) + classObj.getName() + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").";
                }
                return String.valueOf(resultString) + classObj.getName() + "." + funcName + "(org.apache.xalan.extensions.XSLProcessorContext, " + "org.apache.xalan.templates.ElemExtensionCall).";
            }
        }
    }
    
    public static Constructor getConstructor(final Class classObj, final Object[] argsIn, final Object[][] argsOut, final ExpressionContext exprContext) throws NoSuchMethodException, SecurityException, TransformerException {
        Constructor bestConstructor = null;
        Class[] bestParamTypes = null;
        final Constructor[] constructors = classObj.getConstructors();
        final int nMethods = constructors.length;
        int bestScore = Integer.MAX_VALUE;
        int bestScoreCount = 0;
        for (final Constructor ctor : constructors) {
            final Class[] paramTypes = ctor.getParameterTypes();
            final int numberMethodParams = paramTypes.length;
            int paramStart = 0;
            boolean isFirstExpressionContext = false;
            Label_0179: {
                int scoreStart;
                if (numberMethodParams == argsIn.length + 1) {
                    final Class javaClass = paramTypes[0];
                    if (!((MethodResolver.class$org$apache$xalan$extensions$ExpressionContext != null) ? MethodResolver.class$org$apache$xalan$extensions$ExpressionContext : (MethodResolver.class$org$apache$xalan$extensions$ExpressionContext = class$("org.apache.xalan.extensions.ExpressionContext"))).isAssignableFrom(javaClass)) {
                        break Label_0179;
                    }
                    isFirstExpressionContext = true;
                    scoreStart = 0;
                    ++paramStart;
                }
                else {
                    scoreStart = 1000;
                }
                if (argsIn.length == numberMethodParams - paramStart) {
                    final int score = scoreMatch(paramTypes, paramStart, argsIn, scoreStart);
                    if (score != -1) {
                        if (score < bestScore) {
                            bestConstructor = ctor;
                            bestParamTypes = paramTypes;
                            bestScore = score;
                            bestScoreCount = 1;
                        }
                        else if (score == bestScore) {
                            ++bestScoreCount;
                        }
                    }
                }
            }
        }
        if (bestConstructor == null) {
            throw new NoSuchMethodException(errString("function", "constructor", classObj, "", 0, argsIn));
        }
        convertParams(argsIn, argsOut, bestParamTypes, exprContext);
        return bestConstructor;
    }
    
    public static Method getElementMethod(final Class classObj, final String name) throws NoSuchMethodException, SecurityException, TransformerException {
        Method bestMethod = null;
        final Method[] methods = classObj.getMethods();
        final int nMethods = methods.length;
        int bestScore = Integer.MAX_VALUE;
        int bestScoreCount = 0;
        for (final Method method : methods) {
            if (method.getName().equals(name)) {
                final Class[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == 2 && paramTypes[1].isAssignableFrom((MethodResolver.class$org$apache$xalan$templates$ElemExtensionCall != null) ? MethodResolver.class$org$apache$xalan$templates$ElemExtensionCall : (MethodResolver.class$org$apache$xalan$templates$ElemExtensionCall = class$("org.apache.xalan.templates.ElemExtensionCall")))) {
                    int score = -1;
                    if (paramTypes[0].isAssignableFrom((MethodResolver.class$org$apache$xalan$extensions$XSLProcessorContext != null) ? MethodResolver.class$org$apache$xalan$extensions$XSLProcessorContext : (MethodResolver.class$org$apache$xalan$extensions$XSLProcessorContext = class$("org.apache.xalan.extensions.XSLProcessorContext")))) {
                        score = 10;
                        if (score < bestScore) {
                            bestMethod = method;
                            bestScore = score;
                            bestScoreCount = 1;
                        }
                        else if (score == bestScore) {
                            ++bestScoreCount;
                        }
                    }
                }
            }
        }
        if (bestMethod == null) {
            throw new NoSuchMethodException(errString("element", "method", classObj, name, 0, null));
        }
        if (bestScoreCount > 1) {
            throw new TransformerException("More than one best match for element method " + name);
        }
        return bestMethod;
    }
    
    public static Method getMethod(final Class classObj, final String name, final Object[] argsIn, final Object[][] argsOut, final ExpressionContext exprContext, final int searchMethod) throws NoSuchMethodException, SecurityException, TransformerException {
        Method bestMethod = null;
        Class[] bestParamTypes = null;
        final Method[] methods = classObj.getMethods();
        final int nMethods = methods.length;
        int bestScore = Integer.MAX_VALUE;
        int bestScoreCount = 0;
        for (final Method method : methods) {
            int xsltParamStart = 0;
            Label_0280: {
                if (method.getName().equals(name)) {
                    final boolean isStatic = Modifier.isStatic(method.getModifiers());
                    switch (searchMethod) {
                        case 1: {
                            if (!isStatic) {
                                break Label_0280;
                            }
                            break;
                        }
                        case 2: {
                            if (isStatic) {
                                break Label_0280;
                            }
                            break;
                        }
                        case 4: {
                            if (!isStatic) {
                                xsltParamStart = 1;
                                break;
                            }
                            break;
                        }
                    }
                    int javaParamStart = 0;
                    final Class[] paramTypes = method.getParameterTypes();
                    final int numberMethodParams = paramTypes.length;
                    boolean isFirstExpressionContext = false;
                    final int argsLen = (argsIn != null) ? argsIn.length : 0;
                    int scoreStart;
                    if (numberMethodParams == argsLen - xsltParamStart + 1) {
                        final Class javaClass = paramTypes[0];
                        if (!((MethodResolver.class$org$apache$xalan$extensions$ExpressionContext != null) ? MethodResolver.class$org$apache$xalan$extensions$ExpressionContext : (MethodResolver.class$org$apache$xalan$extensions$ExpressionContext = class$("org.apache.xalan.extensions.ExpressionContext"))).isAssignableFrom(javaClass)) {
                            break Label_0280;
                        }
                        isFirstExpressionContext = true;
                        scoreStart = 0;
                        ++javaParamStart;
                    }
                    else {
                        scoreStart = 1000;
                    }
                    if (argsLen - xsltParamStart == numberMethodParams - javaParamStart) {
                        final int score = scoreMatch(paramTypes, javaParamStart, argsIn, scoreStart);
                        if (score != -1) {
                            if (score < bestScore) {
                                bestMethod = method;
                                bestParamTypes = paramTypes;
                                bestScore = score;
                                bestScoreCount = 1;
                            }
                            else if (score == bestScore) {
                                ++bestScoreCount;
                            }
                        }
                    }
                }
            }
        }
        if (bestMethod == null) {
            throw new NoSuchMethodException(errString("function", "method", classObj, name, searchMethod, argsIn));
        }
        convertParams(argsIn, argsOut, bestParamTypes, exprContext);
        return bestMethod;
    }
    
    public static int scoreMatch(final Class[] javaParamTypes, final int javaParamsStart, final Object[] xsltArgs, int score) {
        if (xsltArgs == null || javaParamTypes == null) {
            return score;
        }
        for (int nParams = xsltArgs.length, i = nParams - javaParamTypes.length + javaParamsStart, javaParamTypesIndex = javaParamsStart; i < nParams; ++i, ++javaParamTypesIndex) {
            final Object xsltObj = xsltArgs[i];
            final int xsltClassType = (xsltObj instanceof XObject) ? ((XObject)xsltObj).getType() : 0;
            final Class javaClass = javaParamTypes[javaParamTypesIndex];
            if (xsltClassType == -1) {
                if (javaClass.isPrimitive()) {
                    return -1;
                }
                score += 10;
            }
            else {
                for (final ConversionInfo cinfo : MethodResolver.m_conversions[xsltClassType]) {
                    if (javaClass.isAssignableFrom(cinfo.m_class)) {
                        score += cinfo.m_score;
                        break;
                    }
                }
                final int nConversions;
                final int k;
                if (k == nConversions) {
                    if (xsltClassType != 0) {
                        return -1;
                    }
                    if (!javaClass.isAssignableFrom((xsltObj instanceof XObject) ? ((XObject)xsltObj).object().getClass() : xsltObj.getClass())) {
                        return -1;
                    }
                    score += 0;
                }
            }
        }
        return score;
    }
    
    static class ConversionInfo
    {
        Class m_class;
        int m_score;
        
        ConversionInfo(final Class cl, final int score) {
            this.m_class = cl;
            this.m_score = score;
        }
    }
}
