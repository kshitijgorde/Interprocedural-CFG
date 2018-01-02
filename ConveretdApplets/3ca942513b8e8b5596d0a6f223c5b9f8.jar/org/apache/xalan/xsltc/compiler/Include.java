// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Enumeration;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.apache.xml.utils.SystemIDResolver;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;

final class Include extends TopLevelElement
{
    private Stylesheet _included;
    
    Include() {
        this._included = null;
    }
    
    public Stylesheet getIncludedStylesheet() {
        return this._included;
    }
    
    public void parseContents(final Parser parser) {
        final XSLTC xsltc = parser.getXSLTC();
        final Stylesheet context = parser.getCurrentStylesheet();
        String docToLoad = this.getAttribute("href");
        try {
            if (context.checkForLoop(docToLoad)) {
                final ErrorMsg msg = new ErrorMsg("CIRCULAR_INCLUDE_ERR", docToLoad, this);
                parser.reportError(2, msg);
                return;
            }
            InputSource input = null;
            XMLReader reader = null;
            final String currLoadedDoc = context.getSystemId();
            final SourceLoader loader = context.getSourceLoader();
            if (loader != null) {
                input = loader.loadSource(docToLoad, currLoadedDoc, xsltc);
                if (input != null) {
                    docToLoad = input.getSystemId();
                    reader = xsltc.getXMLReader();
                }
            }
            if (input == null) {
                docToLoad = SystemIDResolver.getAbsoluteURI(docToLoad, currLoadedDoc);
                input = new InputSource(docToLoad);
            }
            if (input == null) {
                final ErrorMsg msg2 = new ErrorMsg("FILE_NOT_FOUND_ERR", docToLoad, this);
                parser.reportError(2, msg2);
                return;
            }
            SyntaxTreeNode root;
            if (reader != null) {
                root = parser.parse(reader, input);
            }
            else {
                root = parser.parse(input);
            }
            if (root == null) {
                return;
            }
            this._included = parser.makeStylesheet(root);
            if (this._included == null) {
                return;
            }
            this._included.setSourceLoader(loader);
            this._included.setSystemId(docToLoad);
            this._included.setParentStylesheet(context);
            this._included.setIncludingStylesheet(context);
            this._included.setTemplateInlining(context.getTemplateInlining());
            final int precedence = context.getImportPrecedence();
            this._included.setImportPrecedence(precedence);
            parser.setCurrentStylesheet(this._included);
            this._included.parseContents(parser);
            final Enumeration elements = this._included.elements();
            final Stylesheet topStylesheet = parser.getTopLevelStylesheet();
            while (elements.hasMoreElements()) {
                final Object element = elements.nextElement();
                if (element instanceof TopLevelElement) {
                    if (element instanceof Variable) {
                        topStylesheet.addVariable((Variable)element);
                    }
                    else if (element instanceof Param) {
                        topStylesheet.addParam((Param)element);
                    }
                    else {
                        topStylesheet.addElement((SyntaxTreeNode)element);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            parser.setCurrentStylesheet(context);
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
    }
}
