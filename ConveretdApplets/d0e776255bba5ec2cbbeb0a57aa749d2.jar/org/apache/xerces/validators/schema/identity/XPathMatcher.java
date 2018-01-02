// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema.identity;

import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.IntStack;

public class XPathMatcher
{
    protected static final boolean DEBUG_ALL = false;
    protected static final boolean DEBUG_METHODS = false;
    protected static final boolean DEBUG_METHODS2 = false;
    protected static final boolean DEBUG_METHODS3 = false;
    protected static final boolean DEBUG_MATCH = false;
    protected static final boolean DEBUG_STACK = false;
    protected static final boolean DEBUG_ANY = false;
    private XPath.LocationPath fLocationPath;
    private boolean fShouldBufferContent;
    private boolean fBufferContent;
    private StringBuffer fMatchedBuffer;
    private boolean fMatched;
    private String fMatchedString;
    private IntStack fStepIndexes;
    private int fCurrentStep;
    private int fNoMatchDepth;
    protected StringPool fStringPool;
    protected NamespacesScope fNamespacesScope;
    
    public XPathMatcher(final XPath xPath) {
        this(xPath, false);
    }
    
    public XPathMatcher(final XPath xPath, final boolean fShouldBufferContent) {
        this.fMatchedBuffer = new StringBuffer();
        this.fStepIndexes = new IntStack();
        this.fLocationPath = xPath.getLocationPath();
        this.fShouldBufferContent = fShouldBufferContent;
    }
    
    public boolean isMatched() {
        return this.fMatched;
    }
    
    public String getMatchedString() {
        return this.fMatchedString;
    }
    
    protected void matched(final String s) throws Exception {
    }
    
    public void startDocumentFragment(final StringPool fStringPool, final NamespacesScope fNamespacesScope) throws Exception {
        this.clear();
        this.fMatchedBuffer.setLength(0);
        this.fStepIndexes.clear();
        this.fCurrentStep = 0;
        this.fNoMatchDepth = 0;
        this.fStringPool = fStringPool;
        this.fNamespacesScope = fNamespacesScope;
        if (fNamespacesScope == null) {
            this.fNamespacesScope = new NamespacesScope();
        }
    }
    
    public void startElement(final QName qName, final XMLAttrList list, final int n) throws Exception {
        final int fCurrentStep = this.fCurrentStep;
        this.fStepIndexes.push(fCurrentStep);
        if (this.fMatched || this.fNoMatchDepth > 0) {
            ++this.fNoMatchDepth;
            return;
        }
        final XPath.Step[] steps = this.fLocationPath.steps;
        while (this.fCurrentStep < steps.length && steps[this.fCurrentStep].axis.type == 3) {
            ++this.fCurrentStep;
        }
        if (this.fCurrentStep == steps.length) {
            this.fMatched = true;
            this.fBufferContent = this.fShouldBufferContent;
        }
        if (this.fCurrentStep == fCurrentStep && steps[this.fCurrentStep].axis.type == 1) {
            final XPath.NodeTest nodeTest = steps[this.fCurrentStep].nodeTest;
            if (nodeTest.type == 1 && !nodeTest.name.equals(qName)) {
                ++this.fNoMatchDepth;
                return;
            }
            ++this.fCurrentStep;
        }
        if (this.fCurrentStep == steps.length) {
            this.fMatched = true;
            this.fBufferContent = this.fShouldBufferContent;
        }
        if (this.fCurrentStep < steps.length && steps[this.fCurrentStep].axis.type == 2) {
            int i = list.getFirstAttr(n);
            if (i != -1) {
                final XPath.NodeTest nodeTest2 = steps[this.fCurrentStep].nodeTest;
                final QName qName2 = new QName();
                while (i != -1) {
                    qName2.setValues(list.getAttrPrefix(i), list.getAttrLocalpart(i), list.getAttrName(i), list.getAttrURI(i));
                    if (nodeTest2.type != 1 || nodeTest2.name.equals(qName2)) {
                        ++this.fCurrentStep;
                        if (this.fCurrentStep == steps.length) {
                            this.fMatched = true;
                            this.matched(this.fMatchedString = this.fStringPool.toString(list.getAttValue(i)));
                            break;
                        }
                        break;
                    }
                    else {
                        i = list.getNextAttr(i);
                    }
                }
            }
            if (!this.fMatched) {
                ++this.fNoMatchDepth;
            }
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws Exception {
        if (this.fBufferContent && this.fNoMatchDepth == 0) {
            this.fMatchedBuffer.append(array, n, n2);
        }
    }
    
    public void endElement(final QName qName) throws Exception {
        if (this.fNoMatchDepth > 0) {
            --this.fNoMatchDepth;
        }
        else {
            if (this.fBufferContent) {
                this.fBufferContent = false;
                this.matched(this.fMatchedString = this.fMatchedBuffer.toString());
            }
            this.clear();
        }
        this.fCurrentStep = this.fStepIndexes.pop();
    }
    
    public void endDocumentFragment() throws Exception {
        this.clear();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        String s = super.toString();
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex != -1) {
            s = s.substring(lastIndex + 1);
        }
        sb.append(s);
        sb.append('[');
        final XPath.Step[] steps = this.fLocationPath.steps;
        for (int i = 0; i < steps.length; ++i) {
            if (i == this.fCurrentStep) {
                sb.append('^');
            }
            sb.append(steps[i].toString());
            if (i < steps.length - 1) {
                sb.append('/');
            }
        }
        if (this.fCurrentStep == steps.length) {
            sb.append('^');
        }
        sb.append(']');
        return sb.toString();
    }
    
    private void clear() {
        this.fBufferContent = false;
        this.fMatchedBuffer.setLength(0);
        this.fMatched = false;
        this.fMatchedString = null;
    }
    
    private String normalize(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 10: {
                    sb.append("\\n");
                    break;
                }
                default: {
                    sb.append(char1);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
