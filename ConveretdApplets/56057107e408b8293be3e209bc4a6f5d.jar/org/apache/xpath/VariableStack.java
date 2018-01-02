// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;
import org.apache.xml.utils.IntStack;
import java.util.Stack;

public class VariableStack extends Stack
{
    private IntStack m_elemFramePos;
    private static final Stack m_emptyStackFrame;
    private int m_globalStackFrameIndex;
    private int m_searchStart;
    
    static {
        m_emptyStackFrame = new Stack();
    }
    
    public VariableStack() {
        this.m_elemFramePos = new IntStack();
        this.m_globalStackFrameIndex = -1;
        this.m_searchStart = -1;
        this.pushContextMarker();
    }
    
    private Stack allocateCurrentFrame() {
        final int stackFrameIndex = (this.m_searchStart == -1) ? (this.size() - 1) : this.m_searchStart;
        final Stack newFrame = new Stack();
        this.setElementAt(newFrame, stackFrameIndex);
        return newFrame;
    }
    
    public int getContextPos() {
        return this.size() - 1;
    }
    
    private Stack getCurrentFrame() {
        final int stackFrameIndex = (this.m_searchStart == -1) ? (this.size() - 1) : this.m_searchStart;
        return this.elementAt(stackFrameIndex);
    }
    
    public Arg getParamArg(final QName qname) throws TransformerException {
        final XObject val = null;
        final Stack frame = this.getCurrentFrame();
        for (int i = frame.size() - 1; i >= 0; --i) {
            final Arg arg = (Arg)frame.elementAt(i);
            if (arg.getQName().equals(qname) && arg.isParamVar()) {
                return arg;
            }
        }
        return null;
    }
    
    public int getSearchStart() {
        return this.m_searchStart;
    }
    
    public int getSearchStartOrTop() {
        return (this.m_searchStart == -1) ? (this.size() - 1) : this.m_searchStart;
    }
    
    public XObject getVariable(final XPathContext xctxt, final QName name) throws TransformerException {
        final Stack frame = this.getCurrentFrame();
        final Stack gframe = this.elementAt(0);
        if (frame != gframe) {
            for (int i = frame.size() - 1; i >= 0; --i) {
                final Arg arg = (Arg)frame.elementAt(i);
                if (arg.getQName().equals(name) && !arg.isParamVar()) {
                    XObject val = arg.getVal();
                    if (val.getType() == 600) {
                        val = val.execute(xctxt);
                        arg.setVal(val);
                    }
                    return val;
                }
            }
        }
        for (int i = gframe.size() - 1; i >= 0; --i) {
            final Arg arg = (Arg)gframe.elementAt(i);
            if (arg.getQName().equals(name)) {
                XObject val = arg.getVal();
                if (val.getType() == 600) {
                    val = val.execute(xctxt);
                    arg.setVal(val);
                }
                return val;
            }
        }
        return null;
    }
    
    public void markGlobalStackFrame() {
        this.m_globalStackFrameIndex = this.size();
    }
    
    public void popContextPosition() {
    }
    
    public void popCurrentContext() {
        this.pop();
    }
    
    public void popElemFrame() {
        final Stack frame = this.getCurrentFrame();
        final int newSize = this.m_elemFramePos.pop();
        frame.setSize(newSize);
    }
    
    public void pushContextMarker() {
        this.push(VariableStack.m_emptyStackFrame);
    }
    
    public void pushContextPosition(final int pos) {
    }
    
    public void pushElemFrame() {
        final Stack frame = this.getCurrentFrame();
        this.m_elemFramePos.push(frame.size());
    }
    
    public void pushOrReplaceVariable(final QName qname, final XObject xval) {
        Stack frame = this.getCurrentFrame();
        if (frame == VariableStack.m_emptyStackFrame) {
            frame = this.allocateCurrentFrame();
        }
        for (int i = frame.size() - 1; i >= 0; --i) {
            final Arg arg = (Arg)frame.elementAt(i);
            if (arg.getQName().equals(qname) && arg.isParamVar()) {
                frame.setElementAt(new Arg(qname, xval), i);
                return;
            }
        }
        frame.push(new Arg(qname, xval, false));
    }
    
    public void pushVariable(final QName qname, final XObject val) {
        Stack frame = this.getCurrentFrame();
        if (frame == VariableStack.m_emptyStackFrame) {
            frame = this.allocateCurrentFrame();
        }
        frame.push(new Arg(qname, val, false));
    }
    
    public void pushVariableArg(final Arg arg) {
        Stack frame = this.getCurrentFrame();
        if (frame == VariableStack.m_emptyStackFrame) {
            frame = this.allocateCurrentFrame();
        }
        frame.push(arg);
    }
    
    public void remarkParams() {
        final Stack frame = this.getCurrentFrame();
        for (int i = frame.size() - 1; i >= 0; --i) {
            final Arg arg = (Arg)frame.elementAt(i);
            arg.setIsParamVar(true);
        }
    }
    
    public void setSearchStart(final int startPos) {
        this.m_searchStart = startPos;
    }
    
    public boolean variableIsDeclared(final QName qname) throws TransformerException {
        final Stack frame = this.getCurrentFrame();
        for (int i = frame.size() - 1; i >= 0; --i) {
            final Object obj = frame.elementAt(i);
            if (((Arg)obj).getQName().equals(qname)) {
                return true;
            }
        }
        final Stack gframe = this.elementAt(0);
        if (gframe == frame) {
            return false;
        }
        for (int j = gframe.size() - 1; j >= 0; --j) {
            final Object obj2 = gframe.elementAt(j);
            if (((Arg)obj2).getQName().equals(qname)) {
                return true;
            }
        }
        return false;
    }
}
