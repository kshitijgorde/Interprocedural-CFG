// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.xmodel.xml.XmlException;
import java.io.InputStream;
import java.io.FileInputStream;
import org.xmodel.ModelAlgorithms;
import java.util.Arrays;
import org.xmodel.xpath.XPath;
import java.io.IOException;
import org.xmodel.xpath.expression.IContext;
import java.util.Iterator;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.xmodel.xml.IXmlIO;
import org.xmodel.ModelObjectFactory;
import org.xmodel.log.Log;
import java.util.Stack;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import java.io.BufferedReader;
import org.xmodel.xml.XmlIO;
import org.xmodel.IModelObjectFactory;

public class BreakAction extends GuardedAction
{
    private IModelObjectFactory \u010c;
    private static String \u010e;
    private static int \u0113;
    private XmlIO \u0115;
    private BufferedReader \u010f;
    private IExpression \u0109;
    private ScriptAction \u0114;
    private List<IExpression> \u0117;
    private List<String> \u0111;
    private IModelObject \u0116;
    private _A \u0108;
    private IXAction \u0110;
    private int \u010d;
    private static ThreadLocal<Stack<BreakAction>> \u010a;
    private static Log \u010b;
    private static /* synthetic */ int[] \u0112;
    
    static {
        BreakAction.\u010e = "";
        BreakAction.\u0113 = 1000;
        BreakAction.\u010a = new ThreadLocal<Stack<BreakAction>>();
        BreakAction.\u010b = Log.getLog("org.xmodel.xaction");
    }
    
    public BreakAction() {
        this.\u010c = new ModelObjectFactory() {
            @Override
            public IModelObject createClone(final IModelObject modelObject) {
                final IModelObject clone = super.createClone(modelObject);
                clone.removeAttribute("xm:compiled");
                return clone;
            }
        };
        this.\u0108 = _A.B;
        (this.\u0115 = new XmlIO()).skipOutputPrefix("break");
        this.\u0115.setOutputStyle(IXmlIO.Style.printable);
    }
    
    public static BreakAction getThreadBreakAction() {
        if (BreakAction.\u010a == null) {
            BreakAction.\u010a = new ThreadLocal<Stack<BreakAction>>();
        }
        final Stack<BreakAction> stack = BreakAction.\u010a.get();
        if (stack != null && !stack.empty()) {
            return stack.peek();
        }
        return null;
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u0111 = new ArrayList<String>(1);
        this.\u010f = new BufferedReader(new InputStreamReader(System.in));
        this.\u0114 = xActionDocument.createScript("skip", "lines", "watch");
        this.\u0117 = new ArrayList<IExpression>();
        final Iterator<IModelObject> iterator = xActionDocument.getRoot().getChildren("watch").iterator();
        while (iterator.hasNext()) {
            this.\u0117.add(xActionDocument.getExpression(iterator.next()));
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        try {
            this.F();
            try {
                while (System.in.available() != 0) {
                    System.in.read();
                }
            }
            catch (Exception ex) {}
            if (this.\u0114.getActions().size() > 0) {
                this.A(BreakAction.\u010e, this.getDocument().getRoot(), BreakAction.\u0113);
                final Object[] run = this.\u0114.run(context);
                if (run != null) {
                    return run;
                }
                this.prompt(context, null);
            }
            else {
                this.prompt(context, this.document.getRoot());
            }
        }
        finally {
            this.G();
        }
        this.G();
        return null;
    }
    
    protected boolean startAction(final IXAction ixAction) {
        switch (C()[this.\u0108.ordinal()]) {
            case 1: {
                this.\u0110 = ixAction;
                return true;
            }
            case 2: {
                if (this.\u0110 == null) {
                    this.\u0110 = ixAction;
                    return true;
                }
                return false;
            }
            case 3: {
                ++this.\u010d;
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    protected void endAction(final IXAction ixAction) {
        switch (C()[this.\u0108.ordinal()]) {
            case 1: {
                this.\u0110 = null;
                break;
            }
            case 2: {
                if (ixAction == this.\u0110) {
                    this.\u0110 = null;
                    break;
                }
                break;
            }
            case 3: {
                if (this.\u010d-- < 0) {
                    this.\u0108 = _A.B;
                }
                if (ixAction == this.\u0110) {
                    this.\u0110 = null;
                    break;
                }
                break;
            }
        }
    }
    
    protected void prompt(final IContext context, final IModelObject \u0117) {
        if (this.\u0109 == null || this.\u0109.evaluateBoolean(context)) {
            if ((this.\u0116 = \u0117) != null) {
                this.A(BreakAction.\u010e, \u0117, BreakAction.\u0113);
            }
            while (true) {
                if (this.\u0117.size() <= 0) {
                    break Label_0128;
                }
                for (final IExpression expression : this.\u0117) {
                    this.A(String.valueOf(BreakAction.\u010e) + expression.toString() + "=", expression.evaluateString(context), 1);
                }
                try {
                    String string;
                    do {
                        System.err.flush();
                        System.out.printf("-> ", new Object[0]);
                        final StringBuilder sb = new StringBuilder();
                        String trim;
                        while (true) {
                            trim = this.D().trim();
                            if (trim.length() <= 1 || trim.charAt(trim.length() - 1) != '+') {
                                break;
                            }
                            sb.append(trim);
                            sb.deleteCharAt(sb.length() - 1);
                            System.out.print(String.valueOf(BreakAction.\u010e) + "+> ");
                        }
                        sb.append(trim);
                        string = sb.toString();
                        if (string.length() > 0) {
                            this.\u0111.add(string);
                        }
                    } while (!this.B(context, string));
                }
                catch (IOException ex) {
                    BreakAction.\u010b.exception(ex);
                    continue;
                }
                break;
            }
        }
    }
    
    private String D() throws IOException {
        while (!this.\u010f.ready()) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        return this.\u010f.readLine();
    }
    
    private boolean B(final IContext context, final String s) {
        if (s.equals("?")) {
            this.E();
            return false;
        }
        if (s.length() == 0) {
            this.\u0108 = _A.B;
            return true;
        }
        if (s.equals(">")) {
            this.\u0108 = _A.D;
            return true;
        }
        if (s.equals("<")) {
            this.\u0108 = _A.C;
            this.\u010d = 0;
            return true;
        }
        if (s.equals("$")) {
            this.B(context);
            return false;
        }
        if (s.charAt(0) == '|') {
            this.A(context, s.substring(1).trim());
            return false;
        }
        if (s.charAt(0) == '~') {
            this.B(BreakAction.\u010e);
            return false;
        }
        if (s.equals("@")) {
            this.A(BreakAction.\u010e, this.\u0116, BreakAction.\u0113);
            return false;
        }
        if (s.equals("#")) {
            this.A(context);
            return false;
        }
        if (s.charAt(0) == '!') {
            this.\u0111.remove(this.\u0111.size() - 1);
            final String substring = s.substring(1);
            if (substring.length() == 0) {
                for (int i = 0; i < this.\u0111.size(); ++i) {
                    System.out.printf("%s[%d] %s\n", BreakAction.\u010e, i + 1, this.\u0111.get(i));
                }
                System.out.println(BreakAction.\u010e);
                return false;
            }
            final int n = this.A(substring) - 1;
            if (n != Integer.MAX_VALUE) {
                if (n >= 0 && n < this.\u0111.size()) {
                    final String s2 = this.\u0111.get(n);
                    System.out.printf("%s!%s\n", BreakAction.\u010e, s2);
                    return this.B(context, s2);
                }
                return false;
            }
            else {
                for (final String s3 : this.\u0111) {
                    if (s3.startsWith(substring)) {
                        System.out.printf("%s!%s\n", BreakAction.\u010e, s3);
                        return this.B(context, s3);
                    }
                }
            }
        }
        try {
            final IExpression compileExpression = XPath.compileExpression(s);
            final IExpression.ResultType type = compileExpression.getType(context);
            if (compileExpression != null) {
                if (type == IExpression.ResultType.BOOLEAN) {
                    this.\u0109 = compileExpression;
                    this.A(BreakAction.\u010e, "Breakpoint condition: " + this.\u0109, BreakAction.\u0113);
                    return true;
                }
                this.A(compileExpression, context);
            }
            return false;
        }
        catch (Exception ex) {
            System.out.printf("%sSyntax error: %s\n\n", BreakAction.\u010e, ex.getMessage());
            return false;
        }
        catch (Error error) {
            System.out.printf("%sSyntax error: %s\n\n", BreakAction.\u010e, error.getMessage());
            return false;
        }
    }
    
    private int A(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return Integer.MAX_VALUE;
        }
    }
    
    private void E() {
        System.out.println(BreakAction.\u010e);
        System.out.printf("%sEnter one of the following:\n", BreakAction.\u010e);
        System.out.printf("%s  ? to repeat this message.\n", BreakAction.\u010e);
        System.out.printf("%s  [Return] to step over the next action.\n", BreakAction.\u010e);
        System.out.printf("%s  < to step out of the current action.\n", BreakAction.\u010e);
        System.out.printf("%s  > to step into the next action.\n", BreakAction.\u010e);
        System.out.printf("%s  An expression to be evaluated in the current context.\n", BreakAction.\u010e);
        System.out.printf("%s  $ to print a summary of all context defined variables.\n", BreakAction.\u010e);
        System.out.printf("%s  @ to reprint the current breakpoint location.\n", BreakAction.\u010e);
        System.out.printf("%s  # to dump the context stack.\n", BreakAction.\u010e);
        System.out.printf("%s  ~ will dump a stack trace to the console.\n", BreakAction.\u010e);
        System.out.printf("%s  | followed by a file path to execute an XAction from a file.\n", BreakAction.\u010e);
        System.out.printf("%s  !, by itself, to show history.\n", BreakAction.\u010e);
        System.out.printf("%s  ! followed by the index of the command to execute.\n", BreakAction.\u010e);
        System.out.printf("%s  ! followed by the first few letters of the command to execute.\n", BreakAction.\u010e);
        System.out.println(BreakAction.\u010e);
    }
    
    private void B(final IContext context) {
        System.out.println(BreakAction.\u010e);
        final String[] array = context.getScope().getAll().toArray(new String[0]);
        Arrays.sort(array);
        String[] array2;
        for (int length = (array2 = array).length, i = 0; i < length; ++i) {
            final String s = array2[i];
            System.out.print(BreakAction.\u010e);
            System.out.println(s);
        }
        System.out.println(BreakAction.\u010e);
    }
    
    private void A(final IExpression expression, final IContext context) {
        System.out.println(BreakAction.\u010e);
        if (expression.getType(context) == IExpression.ResultType.NODES) {
            final List<IModelObject> evaluateNodes = expression.evaluateNodes(context);
            for (int i = 0; i < evaluateNodes.size(); ++i) {
                this.A(String.valueOf(BreakAction.\u010e) + "[" + i + "] ", evaluateNodes.get(i), BreakAction.\u0113);
                System.out.println(BreakAction.\u010e);
            }
        }
        else {
            this.A(BreakAction.\u010e, expression.evaluateString(context), BreakAction.\u0113);
            System.out.println(BreakAction.\u010e);
        }
    }
    
    private void A(final String s, final IModelObject modelObject, final int n) {
        try {
            this.A(s, this.\u0115.write(this.A(modelObject)), n);
        }
        catch (Exception ex) {}
    }
    
    private IModelObject A(final IModelObject modelObject) {
        return ModelAlgorithms.cloneTree(modelObject, this.\u010c);
    }
    
    private void A(final String s, final String s2, int length) {
        try {
            final String[] split = s2.split("\n");
            if (length <= 0) {
                length = split.length;
            }
            for (int i = 0; i < length; ++i) {
                if (i >= split.length) {
                    break;
                }
                System.out.printf("%s%s\n", s, split[i]);
            }
        }
        catch (Exception ex) {}
    }
    
    private void B(final String s) {
        final StringBuilder sb = new StringBuilder();
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length; ++i) {
            sb.append(i);
            sb.append(". ");
            sb.append(stackTrace[i].toString());
            sb.append('\n');
        }
        this.A(s, sb.toString(), 64);
    }
    
    private void A(IContext parent) {
        final ArrayList<IContext> list = new ArrayList<IContext>();
        while (parent != null) {
            list.add(0, parent);
            parent = parent.getParent();
        }
        final StringBuilder sb = new StringBuilder();
        for (final IContext context : list) {
            sb.append(String.format("%x", System.identityHashCode(context)));
            sb.append(": ");
            sb.append(ModelAlgorithms.createIdentityExpression(context.getObject()));
            sb.append("\n");
        }
        this.A(BreakAction.\u010e, sb.toString(), 64);
    }
    
    private void A(final IContext context, final String s) {
        try {
            final IModelObject read = this.\u0115.read(new FileInputStream(s));
            if (read != null) {
                this.A(context, read);
            }
        }
        catch (IOException ex) {
            System.out.printf("%sFile not found.", BreakAction.\u010e);
        }
        catch (XmlException ex2) {
            System.out.printf("%sInvalid xml document.", BreakAction.\u010e);
        }
    }
    
    private void A(final IContext context, final IModelObject modelObject) {
        final IXAction action = this.getDocument().getDocument(modelObject).getAction(modelObject);
        if (action != null) {
            action.run(context);
        }
    }
    
    private void F() {
        Stack<BreakAction> stack = BreakAction.\u010a.get();
        if (stack == null) {
            stack = new Stack<BreakAction>();
            BreakAction.\u010a.set(stack);
        }
        stack.push(this);
    }
    
    private void G() {
        BreakAction.\u010a.get().pop();
    }
    
    static /* synthetic */ int[] C() {
        final int[] \u0113 = BreakAction.\u0112;
        if (\u0113 != null) {
            return \u0113;
        }
        final int[] \u01132 = new int[_A.values().length];
        try {
            \u01132[_A.D.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u01132[_A.C.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u01132[_A.B.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        return BreakAction.\u0112 = \u01132;
    }
    
    private enum _A
    {
        D("stepIn", 0), 
        B("stepOver", 1), 
        C("stepOut", 2);
        
        static {
            A = new _A[] { _A.D, _A.B, _A.C };
        }
        
        private _A(final String s, final int n) {
        }
    }
}
