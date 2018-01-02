// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.filter.msg;

import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.StyleConstants;
import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.Document;
import ji.document.ad;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;

public class nn extends HTMLEditorKit
{
    private static final String[] a;
    private ViewFactory b;
    
    public nn(final nd nd, final boolean b, final ad ad) {
        this.b = new zg(nd, b, ad);
    }
    
    public Document createDefaultDocument() {
        final StyleSheet styleSheet = this.getStyleSheet();
        final StyleSheet styleSheet2 = new StyleSheet();
        styleSheet2.addStyleSheet(styleSheet);
        styleSheet2.addRule(nn.a[0]);
        final no no = new no(styleSheet2);
        no.setParser(this.getParser());
        no.setAsynchronousLoadPriority(4);
        no.setTokenThreshold(100);
        return no;
    }
    
    public StyleSheet getStyleSheet() {
        final StyleSheet styleSheet = new StyleSheet();
        styleSheet.addRule("p { color: black; margin-top: 12px; }");
        styleSheet.addRule("a { color: blue; text-decoration: underline; }");
        styleSheet.addRule("h1 {font-size: 14; font-weight: bold; padding-top: 5px; padding-bottom: 5px;}");
        styleSheet.addRule(".messageHeader { width: 100%; border: none;}");
        styleSheet.addRule(".messageHeaderTitleF { font-family: Calibri,sans-serif; font-weight: bold; font-size: 13pt; text-align: left; padding: 10px 5px 2px 10px; background-color: #CCCCCC; }");
        styleSheet.addRule(".messageHeaderTitleL { font-family: Calibri,sans-serif; font-weight: bold; font-size: 13pt; text-align: left; padding: 2px 5px 10px 10px; background-color: #CCCCCC; }");
        styleSheet.addRule(".messageHeaderTitleE { font-family: Calibri,sans-serif; font-weight: bold; font-size: 13pt; text-align: left; padding: 2px 5px 2px 10px; background-color: #CCCCCC; }");
        styleSheet.addRule(".messageHeaderTitleO { font-family: Calibri,sans-serif; font-weight: bold; font-size: 13pt; text-align: left; padding: 2px 5px 2px 10px; background-color: #D2D2D2; }");
        styleSheet.addRule(".messageHeaderDataF {font-family: Calibri,sans-serif; font-size: 13pt; padding: 10px 10px 2px 2px; background-color: #CCCCCC; }");
        styleSheet.addRule(".messageHeaderDataL {font-family: Calibri,sans-serif; font-size: 13pt; padding: 2px 10px 10px 2px; background-color: #CCCCCC; }");
        styleSheet.addRule(".messageHeaderDataE {font-family: Calibri,sans-serif; font-size: 13pt; padding: 2px 10px 2px 2px; background-color: #CCCCCC; }");
        styleSheet.addRule(".messageHeaderDataO {font-family: Calibri,sans-serif; font-size: 13pt; padding: 2px 10px 2px 2px; background-color: #D2D2D2; }");
        styleSheet.addStyleSheet(super.getStyleSheet());
        return styleSheet;
    }
    
    public ViewFactory getViewFactory() {
        return this.b;
    }
    
    public void a() {
        ((zg)this.b).a();
    }
    
    static {
        a = new String[] { "body { padding: 60px; }", "body { 60px 60px 0px 60px; }", "body { 0px 60px 0px 60px; }", "body { 0px 60px 60px 60px; }" };
    }
    
    public class zg extends HTMLFactory
    {
        private nd a;
        private boolean b;
        private ad c;
        private View d;
        private int e;
        
        public zg(final nn nn, final nd a, final boolean b, final ad c) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = null;
            this.e = 0;
        }
        
        public View create(final Element element) {
            final AttributeSet attributes = element.getAttributes();
            final Object o = (attributes.getAttribute("$ename") != null) ? null : attributes.getAttribute(StyleConstants.NameAttribute);
            if (!(o instanceof HTML.Tag)) {
                return super.create(element);
            }
            if (o == HTML.Tag.IMG) {
                return new MSGImageView(element, this.a, this.b, this.c);
            }
            return super.create(element);
        }
        
        public void a() {
            this.c = null;
        }
    }
}
