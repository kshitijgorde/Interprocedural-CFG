// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMException;
import java.lang.reflect.InvocationTargetException;
import org.w3c.dom.Element;
import java.util.Hashtable;
import org.apache.wml.WMLDocument;
import org.apache.xerces.dom.DocumentImpl;

public class WMLDocumentImpl extends DocumentImpl implements WMLDocument
{
    private static Hashtable _elementTypesWML;
    private static final Class[] _elemClassSigWML;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLDocumentImpl;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLBElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLNoopElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLAElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLSetvarElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLAccessElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLStrongElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLPostfieldElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLDoElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLWmlElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLTrElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLGoElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLBigElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLAnchorElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLTimerElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLSmallElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLOptgroupElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLHeadElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLTdElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLFieldsetElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLImgElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLRefreshElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLOneventElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLInputElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLPrevElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLTableElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLMetaElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLTemplateElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLBrElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLOptionElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLUElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLPElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLSelectElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLEmElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLIElementImpl;
    static /* synthetic */ Class class$org$apache$wml$dom$WMLCardElementImpl;
    
    public Element createElement(final String s) throws DOMException {
        final Class<Element> clazz = WMLDocumentImpl._elementTypesWML.get(s);
        if (clazz != null) {
            try {
                return clazz.getConstructor((Class<?>[])WMLDocumentImpl._elemClassSigWML).newInstance(this, s);
            }
            catch (Exception ex) {
                Throwable targetException;
                if (ex instanceof InvocationTargetException) {
                    targetException = ((InvocationTargetException)ex).getTargetException();
                }
                else {
                    targetException = ex;
                }
                System.out.println("Exception " + targetException.getClass().getName());
                System.out.println(targetException.getMessage());
                throw new IllegalStateException("Tag '" + s + "' associated with an Element class that failed to construct.");
            }
        }
        return new WMLElementImpl(this, s);
    }
    
    public WMLDocumentImpl(final DocumentType documentType) {
        super(documentType, false);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        _elemClassSigWML = new Class[] { (WMLDocumentImpl.class$org$apache$wml$dom$WMLDocumentImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLDocumentImpl = class$("org.apache.wml.dom.WMLDocumentImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLDocumentImpl, (WMLDocumentImpl.class$java$lang$String == null) ? (WMLDocumentImpl.class$java$lang$String = class$("java.lang.String")) : WMLDocumentImpl.class$java$lang$String };
        (WMLDocumentImpl._elementTypesWML = new Hashtable()).put("b", (WMLDocumentImpl.class$org$apache$wml$dom$WMLBElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLBElementImpl = class$("org.apache.wml.dom.WMLBElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLBElementImpl);
        WMLDocumentImpl._elementTypesWML.put("noop", (WMLDocumentImpl.class$org$apache$wml$dom$WMLNoopElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLNoopElementImpl = class$("org.apache.wml.dom.WMLNoopElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLNoopElementImpl);
        WMLDocumentImpl._elementTypesWML.put("a", (WMLDocumentImpl.class$org$apache$wml$dom$WMLAElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLAElementImpl = class$("org.apache.wml.dom.WMLAElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLAElementImpl);
        WMLDocumentImpl._elementTypesWML.put("setvar", (WMLDocumentImpl.class$org$apache$wml$dom$WMLSetvarElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLSetvarElementImpl = class$("org.apache.wml.dom.WMLSetvarElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLSetvarElementImpl);
        WMLDocumentImpl._elementTypesWML.put("access", (WMLDocumentImpl.class$org$apache$wml$dom$WMLAccessElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLAccessElementImpl = class$("org.apache.wml.dom.WMLAccessElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLAccessElementImpl);
        WMLDocumentImpl._elementTypesWML.put("strong", (WMLDocumentImpl.class$org$apache$wml$dom$WMLStrongElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLStrongElementImpl = class$("org.apache.wml.dom.WMLStrongElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLStrongElementImpl);
        WMLDocumentImpl._elementTypesWML.put("postfield", (WMLDocumentImpl.class$org$apache$wml$dom$WMLPostfieldElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLPostfieldElementImpl = class$("org.apache.wml.dom.WMLPostfieldElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLPostfieldElementImpl);
        WMLDocumentImpl._elementTypesWML.put("do", (WMLDocumentImpl.class$org$apache$wml$dom$WMLDoElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLDoElementImpl = class$("org.apache.wml.dom.WMLDoElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLDoElementImpl);
        WMLDocumentImpl._elementTypesWML.put("wml", (WMLDocumentImpl.class$org$apache$wml$dom$WMLWmlElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLWmlElementImpl = class$("org.apache.wml.dom.WMLWmlElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLWmlElementImpl);
        WMLDocumentImpl._elementTypesWML.put("tr", (WMLDocumentImpl.class$org$apache$wml$dom$WMLTrElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLTrElementImpl = class$("org.apache.wml.dom.WMLTrElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLTrElementImpl);
        WMLDocumentImpl._elementTypesWML.put("go", (WMLDocumentImpl.class$org$apache$wml$dom$WMLGoElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLGoElementImpl = class$("org.apache.wml.dom.WMLGoElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLGoElementImpl);
        WMLDocumentImpl._elementTypesWML.put("big", (WMLDocumentImpl.class$org$apache$wml$dom$WMLBigElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLBigElementImpl = class$("org.apache.wml.dom.WMLBigElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLBigElementImpl);
        WMLDocumentImpl._elementTypesWML.put("anchor", (WMLDocumentImpl.class$org$apache$wml$dom$WMLAnchorElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLAnchorElementImpl = class$("org.apache.wml.dom.WMLAnchorElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLAnchorElementImpl);
        WMLDocumentImpl._elementTypesWML.put("timer", (WMLDocumentImpl.class$org$apache$wml$dom$WMLTimerElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLTimerElementImpl = class$("org.apache.wml.dom.WMLTimerElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLTimerElementImpl);
        WMLDocumentImpl._elementTypesWML.put("small", (WMLDocumentImpl.class$org$apache$wml$dom$WMLSmallElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLSmallElementImpl = class$("org.apache.wml.dom.WMLSmallElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLSmallElementImpl);
        WMLDocumentImpl._elementTypesWML.put("optgroup", (WMLDocumentImpl.class$org$apache$wml$dom$WMLOptgroupElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLOptgroupElementImpl = class$("org.apache.wml.dom.WMLOptgroupElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLOptgroupElementImpl);
        WMLDocumentImpl._elementTypesWML.put("head", (WMLDocumentImpl.class$org$apache$wml$dom$WMLHeadElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLHeadElementImpl = class$("org.apache.wml.dom.WMLHeadElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLHeadElementImpl);
        WMLDocumentImpl._elementTypesWML.put("td", (WMLDocumentImpl.class$org$apache$wml$dom$WMLTdElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLTdElementImpl = class$("org.apache.wml.dom.WMLTdElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLTdElementImpl);
        WMLDocumentImpl._elementTypesWML.put("fieldset", (WMLDocumentImpl.class$org$apache$wml$dom$WMLFieldsetElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLFieldsetElementImpl = class$("org.apache.wml.dom.WMLFieldsetElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLFieldsetElementImpl);
        WMLDocumentImpl._elementTypesWML.put("img", (WMLDocumentImpl.class$org$apache$wml$dom$WMLImgElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLImgElementImpl = class$("org.apache.wml.dom.WMLImgElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLImgElementImpl);
        WMLDocumentImpl._elementTypesWML.put("refresh", (WMLDocumentImpl.class$org$apache$wml$dom$WMLRefreshElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLRefreshElementImpl = class$("org.apache.wml.dom.WMLRefreshElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLRefreshElementImpl);
        WMLDocumentImpl._elementTypesWML.put("onevent", (WMLDocumentImpl.class$org$apache$wml$dom$WMLOneventElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLOneventElementImpl = class$("org.apache.wml.dom.WMLOneventElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLOneventElementImpl);
        WMLDocumentImpl._elementTypesWML.put("input", (WMLDocumentImpl.class$org$apache$wml$dom$WMLInputElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLInputElementImpl = class$("org.apache.wml.dom.WMLInputElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLInputElementImpl);
        WMLDocumentImpl._elementTypesWML.put("prev", (WMLDocumentImpl.class$org$apache$wml$dom$WMLPrevElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLPrevElementImpl = class$("org.apache.wml.dom.WMLPrevElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLPrevElementImpl);
        WMLDocumentImpl._elementTypesWML.put("table", (WMLDocumentImpl.class$org$apache$wml$dom$WMLTableElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLTableElementImpl = class$("org.apache.wml.dom.WMLTableElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLTableElementImpl);
        WMLDocumentImpl._elementTypesWML.put("meta", (WMLDocumentImpl.class$org$apache$wml$dom$WMLMetaElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLMetaElementImpl = class$("org.apache.wml.dom.WMLMetaElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLMetaElementImpl);
        WMLDocumentImpl._elementTypesWML.put("template", (WMLDocumentImpl.class$org$apache$wml$dom$WMLTemplateElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLTemplateElementImpl = class$("org.apache.wml.dom.WMLTemplateElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLTemplateElementImpl);
        WMLDocumentImpl._elementTypesWML.put("br", (WMLDocumentImpl.class$org$apache$wml$dom$WMLBrElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLBrElementImpl = class$("org.apache.wml.dom.WMLBrElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLBrElementImpl);
        WMLDocumentImpl._elementTypesWML.put("option", (WMLDocumentImpl.class$org$apache$wml$dom$WMLOptionElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLOptionElementImpl = class$("org.apache.wml.dom.WMLOptionElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLOptionElementImpl);
        WMLDocumentImpl._elementTypesWML.put("u", (WMLDocumentImpl.class$org$apache$wml$dom$WMLUElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLUElementImpl = class$("org.apache.wml.dom.WMLUElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLUElementImpl);
        WMLDocumentImpl._elementTypesWML.put("p", (WMLDocumentImpl.class$org$apache$wml$dom$WMLPElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLPElementImpl = class$("org.apache.wml.dom.WMLPElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLPElementImpl);
        WMLDocumentImpl._elementTypesWML.put("select", (WMLDocumentImpl.class$org$apache$wml$dom$WMLSelectElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLSelectElementImpl = class$("org.apache.wml.dom.WMLSelectElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLSelectElementImpl);
        WMLDocumentImpl._elementTypesWML.put("em", (WMLDocumentImpl.class$org$apache$wml$dom$WMLEmElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLEmElementImpl = class$("org.apache.wml.dom.WMLEmElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLEmElementImpl);
        WMLDocumentImpl._elementTypesWML.put("i", (WMLDocumentImpl.class$org$apache$wml$dom$WMLIElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLIElementImpl = class$("org.apache.wml.dom.WMLIElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLIElementImpl);
        WMLDocumentImpl._elementTypesWML.put("card", (WMLDocumentImpl.class$org$apache$wml$dom$WMLCardElementImpl == null) ? (WMLDocumentImpl.class$org$apache$wml$dom$WMLCardElementImpl = class$("org.apache.wml.dom.WMLCardElementImpl")) : WMLDocumentImpl.class$org$apache$wml$dom$WMLCardElementImpl);
    }
}
