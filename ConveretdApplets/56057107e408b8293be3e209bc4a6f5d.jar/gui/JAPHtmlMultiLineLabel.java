// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.util.StringTokenizer;
import java.awt.Container;
import javax.swing.text.Element;
import javax.swing.text.PlainView;
import java.awt.Component;
import gui.dialog.JAPDialog;
import logging.LogType;
import javax.swing.text.StyledDocument;
import java.io.Writer;
import javax.swing.text.html.MinimalHTMLWriter;
import java.io.StringWriter;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.View;
import java.awt.Font;
import javax.swing.JLabel;

public class JAPHtmlMultiLineLabel extends JLabel
{
    public static final int FONT_STYLE_PLAIN = 0;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_BOLD = 1;
    public static final String TAG_BREAK = "<br>";
    public static final String TAG_A_OPEN = "<a href=\"\">";
    public static final String TAG_A_CLOSE = "</a>";
    public static final int UNLIMITED_LABEL_HEIGHT = 5000;
    private static final String TAG_HTML_OPEN = "<html>";
    private static final String TAG_HTML_CLOSE = "</html>";
    private static final String TAG_BODY_OPEN = "<body>";
    private static final String TAG_BODY_CLOSE = "</body>";
    private static final String TAG_HEAD_OPEN = "<head>";
    private static final String TAG_HEAD_CLOSE = "</head>";
    private static final String CLIENT_PROPERTY_HTML = "html";
    private static final String CURRENT_JAVA_VENDOR;
    private static final String CURRENT_JAVA_VERSION;
    private static final boolean HTML_COMPATIBILITY_MODE;
    private boolean m_bInitialised;
    private String m_rawText;
    
    public JAPHtmlMultiLineLabel(final String rawText, final Font font, final int n) {
        super("", n);
        this.m_bInitialised = false;
        this.m_rawText = rawText;
        this.setFont(font);
    }
    
    public JAPHtmlMultiLineLabel(final String s, final Font font) {
        this(s, font, 2);
    }
    
    public JAPHtmlMultiLineLabel(final String s, final int n) {
        this(s, (Font)null, n);
    }
    
    public JAPHtmlMultiLineLabel(final int n) {
        this(null, (Font)null, n);
    }
    
    public JAPHtmlMultiLineLabel(final String s) {
        this(s, (Font)null, 2);
    }
    
    public JAPHtmlMultiLineLabel() {
        this("", (Font)null, 2);
    }
    
    public void setText(final String rawText) {
        if (!this.m_bInitialised) {
            this.m_bInitialised = true;
            super.setText(formatTextAsHTML(rawText, this.getFont()));
        }
        else {
            this.m_rawText = rawText;
            this.setFont(this.getFont());
        }
    }
    
    public int getHTMLDocumentLength() {
        return ((View)this.getClientProperty("html")).getDocument().getLength();
    }
    
    public String getHTMLDocumentText() {
        final HTMLDocument htmlDocument = (HTMLDocument)((View)this.getClientProperty("html")).getDocument();
        String s;
        try {
            s = htmlDocument.getText(0, htmlDocument.getLength());
            if (s.charAt(s.length() - 1) == '\n') {
                s = s.substring(0, s.length() - 1);
            }
            if (s.charAt(0) == '\n') {
                s = s.substring(1, s.length());
            }
        }
        catch (BadLocationException ex) {
            s = null;
        }
        return s;
    }
    
    public void cutHTMLDocument(final int n) {
        final StringWriter stringWriter = new StringWriter();
        final HTMLDocument htmlDocument = (HTMLDocument)((View)this.getClientProperty("html")).getDocument();
        if (n > htmlDocument.getLength()) {
            return;
        }
        if (n <= 0) {
            this.setText("");
            return;
        }
        try {
            htmlDocument.remove(n, htmlDocument.getLength() - n);
            new MinimalHTMLWriter(stringWriter, htmlDocument).write();
            this.setText(stringWriter.toString());
        }
        catch (Exception ex) {
            JAPDialog.showErrorDialog(this, LogType.GUI, ex);
        }
    }
    
    public void setPreferredWidth(final int n) {
        final View parent = (View)this.getClientProperty("html");
        final float preferredSpan = parent.getPreferredSpan(0);
        final float preferredSpan2 = parent.getPreferredSpan(1);
        try {
            parent.setSize(n, 5000.0f);
        }
        catch (NullPointerException ex) {
            parent.getView(0).setParent(new PlainView(parent.getElement()) {
                public Container getContainer() {
                    return null;
                }
            });
            parent.setSize(preferredSpan, preferredSpan2);
            parent.setSize(n, 5000.0f);
            parent.getView(0).setParent(parent);
        }
        this.invalidate();
    }
    
    public void setFontStyle(final int n) {
        this.setFont(new Font(this.getFont().getName(), n, this.getFont().getSize()));
    }
    
    public void setFont(Font font) {
        if (font == null) {
            font = new JLabel().getFont();
        }
        if (JAPHtmlMultiLineLabel.HTML_COMPATIBILITY_MODE && font.isBold() && font.getSize() >= 16 && font.getSize() <= 18) {
            final Font font2 = new Font(font.getName(), 0, font.getSize());
            super.setFont(font2);
            super.setText(formatTextAsHTML(this.m_rawText, font2));
        }
        else {
            super.setFont(font);
            super.setText(formatTextAsHTML(this.m_rawText, font));
        }
    }
    
    public static String formatTextAsHTML(final String s, Font font) {
        if (s == null) {
            return s;
        }
        if (s.trim().length() == 0) {
            return "";
        }
        if (font == null) {
            font = new JLabel().getFont();
        }
        final int size = font.getSize();
        String s2;
        if (size < 13) {
            s2 = "-1";
        }
        else if (size < 16) {
            s2 = "+0";
        }
        else if (size < 19) {
            s2 = "+1";
        }
        else if (size < 26) {
            s2 = "+2";
        }
        else {
            s2 = "+3";
        }
        String s3 = "<html>" + "<body>".substring(0, "<body>".length() - 1) + (JAPHtmlMultiLineLabel.HTML_COMPATIBILITY_MODE ? ("><font size=" + s2 + ">") : (" style=\"font-size:" + size + "pt;" + "font-family:" + font.getFamily() + "\">"));
        String string = "</body></html>";
        if (font.isBold()) {
            s3 += "<b>";
            string = "</b>" + string;
        }
        return s3 + removeHTMLHEADAndBODYTags(s) + string;
    }
    
    public static String removeTagsAndNewLines(final String s) {
        if (s == null) {
            return null;
        }
        String s2 = s;
        while (true) {
            int index = s2.indexOf("<");
            int index2 = s2.indexOf(">");
            if (index < 0 && index2 < 0) {
                break;
            }
            if (index2 >= 0 && (index < 0 || index2 < index)) {
                index = index2;
            }
            else if (index2 < 0) {
                index2 = index;
            }
            if (++index2 >= s2.length()) {
                s2 = s2.substring(0, index);
            }
            else {
                s2 = s2.substring(0, index) + s2.substring(index2, s2.length());
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, "\t\n\r\f");
        String string = "";
        while (stringTokenizer.hasMoreTokens()) {
            string += stringTokenizer.nextToken();
        }
        return string.trim();
    }
    
    public static String removeHTMLHEADAndBODYTags(String s) {
        if (s == null || s.trim().length() == 0) {
            return s;
        }
        final String lowerCase = s.toLowerCase();
        final int index = lowerCase.indexOf("<head>".substring(0, "<head>".length() - 1));
        final int index2 = lowerCase.indexOf("</head>");
        if (index >= 0 || index2 >= 0) {
            if (index < 0 || index2 < 0 || index >= index2) {
                return "";
            }
            final int n = index2 + "</head>".length();
            if (index == 0) {
                s = s.substring(n, s.length() - n);
            }
            else if (n == s.length()) {
                s = s.substring(0, index);
            }
            else {
                s = s.substring(0, index) + s.substring(n, s.length());
            }
        }
        return removeTAG(removeTAG(s, "<html>", "</html>"), "<body>", "</body>");
    }
    
    private static String removeTAG(String s, final String s2, final String s3) {
        if (s == null || (s = s.trim()).length() == 0) {
            return s;
        }
        final String substring = s2.substring(0, s2.length() - 1);
        final String lowerCase = s.toLowerCase();
        int n = 0;
        int length = s.length();
        if (lowerCase.startsWith(substring)) {
            n = lowerCase.indexOf(">") + 1;
        }
        if (lowerCase.endsWith(s3)) {
            length -= s3.length();
        }
        if (n > 0 || length < s.length()) {
            if (n < 0 || length < 0 || n >= length) {
                s = "";
            }
            else {
                s = s.substring(n, length).trim();
            }
        }
        return s;
    }
    
    static {
        CURRENT_JAVA_VENDOR = System.getProperty("java.vendor");
        CURRENT_JAVA_VERSION = System.getProperty("java.version");
        final String property = System.getProperty("java.version");
        if (property != null && property.compareTo("1.3") < 0) {
            HTML_COMPATIBILITY_MODE = true;
        }
        else {
            HTML_COMPATIBILITY_MODE = false;
        }
    }
}
