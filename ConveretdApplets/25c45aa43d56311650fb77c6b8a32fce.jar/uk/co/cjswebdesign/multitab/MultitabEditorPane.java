// 
// Decompiled by Procyon v0.5.30
// 

package uk.co.cjswebdesign.multitab;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import java.io.IOException;
import javax.swing.text.html.HTMLEditorKit;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.JEditorPane;

class MultitabEditorPane extends JEditorPane
{
    private String html;
    private String title;
    private String description;
    
    public MultitabEditorPane(final String html) {
        super("text/html", html);
        this.html = html;
        this.title = this.parseTitle(html);
        this.parseDescription(html);
        this.setEditable(false);
        if (MultiTabJApplet.debug) {
            MultiTabJApplet.logger.info("Creating EditorPane");
        }
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    private String parseTitle(final String html) {
        if (MultiTabJApplet.debug) {
            MultiTabJApplet.logger.info("Parsing HTML for title");
        }
        final String startTag = "<title>";
        final String endTag = "</title>";
        String title = "";
        try {
            title = html.substring(html.indexOf(startTag) + startTag.length(), html.indexOf(endTag, html.indexOf(startTag)));
            title = title.replaceAll("\"", "\\\"");
            title = title.replaceAll("'", "\\'");
        }
        catch (StringIndexOutOfBoundsException siobe) {
            title = "";
        }
        return title;
    }
    
    private void parseDescription(final String html) {
        if (MultiTabJApplet.debug) {
            MultiTabJApplet.logger.info("Parsing HTML for description");
        }
        final String copy = html.toLowerCase();
        final StringReader r = new StringReader(copy);
        final HTMLEditorKit.Parser parser = new HTMLParse().getParser();
        final MultitabParserCallback callback = new MultitabParserCallback();
        try {
            parser.parse(r, callback, true);
        }
        catch (IOException iox) {
            if (MultiTabJApplet.debug) {
                MultiTabJApplet.logger.warning("Trouble parsing description " + iox.getLocalizedMessage());
            }
        }
    }
    
    class HTMLParse extends HTMLEditorKit
    {
        public Parser getParser() {
            return super.getParser();
        }
    }
    
    class MultitabParserCallback extends HTMLEditorKit.ParserCallback
    {
        public void handleComment(final char[] data, final int pos) {
        }
        
        public void handleEndTag(final HTML.Tag t, final int pos) {
        }
        
        public void handleError(final String errorMsg, final int pos) {
        }
        
        public void handleSimpleTag(final HTML.Tag t, final MutableAttributeSet a, final int pos) {
            if (t.equals(HTML.Tag.META) && a.isDefined(HTML.Attribute.NAME)) {
                final String name = (String)a.getAttribute(HTML.Attribute.NAME);
                if (name.equalsIgnoreCase("description") && a.isDefined(HTML.Attribute.CONTENT)) {
                    String content = null;
                    try {
                        content = (String)a.getAttribute(HTML.Attribute.CONTENT);
                        content = content.replaceAll("\"", "\\\"");
                        content = content.replaceAll("'", "\\'");
                    }
                    catch (ClassCastException x) {
                        if (MultiTabJApplet.debug) {
                            System.err.println(x.getMessage());
                        }
                    }
                    MultitabEditorPane.this.description = content;
                }
            }
        }
        
        public void handleStartTag(final HTML.Tag t, final MutableAttributeSet a, final int pos) {
        }
    }
}
