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

final class Import extends TopLevelElement
{
    private Stylesheet _imported;
    
    Import() {
        this._imported = null;
    }
    
    public Stylesheet getImportedStylesheet() {
        return this._imported;
    }
    
    public void parseContents(final Parser parser) {
        final XSLTC xsltc = parser.getXSLTC();
        final Stylesheet context = parser.getCurrentStylesheet();
        try {
            String docToLoad = this.getAttribute("href");
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
            this._imported = parser.makeStylesheet(root);
            if (this._imported == null) {
                return;
            }
            this._imported.setSourceLoader(loader);
            this._imported.setSystemId(docToLoad);
            this._imported.setParentStylesheet(context);
            this._imported.setImportingStylesheet(context);
            this._imported.setTemplateInlining(context.getTemplateInlining());
            final int currPrecedence = parser.getCurrentImportPrecedence();
            final int nextPrecedence = parser.getNextImportPrecedence();
            this._imported.setImportPrecedence(currPrecedence);
            context.setImportPrecedence(nextPrecedence);
            parser.setCurrentStylesheet(this._imported);
            this._imported.parseContents(parser);
            final Enumeration elements = this._imported.elements();
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
