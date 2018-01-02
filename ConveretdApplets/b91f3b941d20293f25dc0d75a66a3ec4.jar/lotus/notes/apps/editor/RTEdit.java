// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Graphics;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Scrollbar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class RTEdit extends Panel
{
    public static final boolean DEBUG = false;
    public static final char BULLET_NONE = '\0';
    public static final char BULLET_NUMERIC = '1';
    public static final char BULLET_ALPHAUPPER = 'A';
    public static final char BULLET_ALPHALOWER = 'a';
    public static final char BULLET_ROMANUPPER = 'I';
    public static final char BULLET_ROMANLOWER = 'i';
    public static final char BULLET_DISC = '\u25cf';
    public static final char BULLET_CIRCLE = '\u25cb';
    public static final char BULLET_SQUARE = '\u25a0';
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 3;
    public static final int ALIGN_MIDDLE = 4;
    public static final int ALIGN_BOTTOM = 5;
    public static final int ALIGN_NONE = -1;
    public static final String MIMETYPE_PLAIN = "text/plain";
    public static final String MIMETYPE_HTML = "text/html";
    public static final String MIMETYPE_MULTI = "multi/mixed";
    public static final String MIMEMULTI_BOUNDARY = "boundary=";
    public static final String MIMETYPE_SPELL = "text/spell";
    public static final String HAIKU_HEADLINE = "Limerick";
    public static final int STYLE_DEFAULT = 0;
    public static final int STYLE_HEADING1 = 1;
    public static final int STYLE_HEADING2 = 2;
    public static final int STYLE_HEADING3 = 3;
    public static final int STYLE_HEADING4 = 4;
    public static final int STYLE_HEADING5 = 5;
    public static final int STYLE_HEADING6 = 6;
    public static final int STYLE_PREFORMATTED = 7;
    public static final int STYLE_ADDRESS = 8;
    public static final int STYLE_BLOCKQUOTE = 9;
    public static final int STYLE_DEF_TERM = 10;
    public static final int STYLE_DEF_DESC = 11;
    public static final int STYLE_UNORDERED_LIST = 12;
    public static final int STYLE_ORDERED_LIST = 13;
    public static final int STYLE_DIVISION = 14;
    public static final int STYLE_COUNT = 15;
    public static final int BEHAVE_V5 = 0;
    public static final int BEHAVE_HAIKU = 1;
    public static final int BROWSER_UNKNOWN = 0;
    public static final int BROWSER_IE3 = 1;
    public static final int BROWSER_NS3 = 2;
    public static final int BROWSER_IE4 = 3;
    public static final int BROWSER_NS4 = 4;
    private CRTEdit cEdit;
    private CRTEditor cEditor;
    private ActionListener cListener;
    private Applet cApplet;
    private CHtmlCommon cHtmlConverter;
    public static final char CHAR_LINEBREAK = '\u0003';
    
    public RTEdit(final Applet cApplet, final boolean b, final int behavior, final int browser) {
        this.cHtmlConverter = null;
        this.setLayout(new BorderLayout());
        this.add("Center", this.cEdit = new CRTEdit());
        (this.cEditor = this.cEdit.getEditor()).setBehavior(behavior);
        this.cEditor.setBrowser(browser);
        if (b) {
            this.addScrollbar();
        }
        this.cApplet = cApplet;
    }
    
    public RTEdit(final Applet applet) {
        this(applet, false, 0, 0);
    }
    
    public RTEdit() {
        this(null, false, 0, 0);
    }
    
    private void addScrollbar() {
        final Scrollbar scrollbar = new Scrollbar(1);
        this.add("East", scrollbar);
        this.cEditor.setScrollbar(scrollbar);
    }
    
    public void destroy() {
        this.cEdit.destroy();
    }
    
    public Dimension getMinimumSize() {
        return this.cEditor.minimumSize();
    }
    
    public Applet getApplet() {
        return this.cApplet;
    }
    
    public boolean isEmpty() {
        return this.cEditor.isEmpty();
    }
    
    public String getStyle() {
        return this.cEditor.getStyle();
    }
    
    public boolean setStyle(final String style) {
        this.cEditor.beginChange();
        final boolean setStyle = this.cEditor.setStyle(style);
        this.cEditor.endChange(true);
        return setStyle;
    }
    
    public boolean setStylePreserve(final String stylePreserve) {
        this.cEditor.beginChange();
        final boolean setStylePreserve = this.cEditor.setStylePreserve(stylePreserve);
        this.cEditor.endChange(true);
        return setStylePreserve;
    }
    
    public String[] getStyles() {
        return this.cEditor.getStyles();
    }
    
    public void setEditable(final boolean editable) {
        this.cEditor.setEditable(editable);
    }
    
    public boolean isEditable() {
        return this.cEditor.isEditable();
    }
    
    public void setBold(final boolean bold) {
        this.cEditor.beginChange();
        this.cEditor.setBold(bold);
        this.cEditor.endChange(true);
    }
    
    public boolean isBold() {
        return this.cEditor.isBold();
    }
    
    public void setItalic(final boolean italic) {
        this.cEditor.beginChange();
        this.cEditor.setItalic(italic);
        this.cEditor.endChange(true);
    }
    
    public boolean isItalic() {
        return this.cEditor.isItalic();
    }
    
    public void setUnderline(final boolean underline) {
        this.cEditor.beginChange();
        this.cEditor.setUnderline(underline);
        this.cEditor.endChange(true);
    }
    
    public boolean isUnderline() {
        return this.cEditor.isUnderline();
    }
    
    public void setStrikeThrough(final boolean strikeThrough) {
        this.cEditor.beginChange();
        this.cEditor.setStrikeThrough(strikeThrough);
        this.cEditor.endChange(true);
    }
    
    public boolean isStrikeThrough() {
        return this.cEditor.isStrikeThrough();
    }
    
    public void setPointSize(final int pointSize) {
        this.cEditor.beginChange();
        this.cEditor.setPointSize(pointSize);
        this.cEditor.endChange(true);
    }
    
    public int getPointSize() {
        return this.cEditor.getPointSize();
    }
    
    public int getHTMLSize() {
        if (this.cHtmlConverter == null) {
            this.cHtmlConverter = new CHtmlCommon(this.cEditor);
        }
        return this.cHtmlConverter.getClosestSize(this.cEditor.getPointSize());
    }
    
    public void setFaceName(final String faceName) {
        this.cEditor.beginChange();
        this.cEditor.setFaceName(faceName);
        this.cEditor.endChange(true);
    }
    
    public String getFaceName() {
        return this.cEditor.getFaceName();
    }
    
    public void setFontColor(final Color fontColor) {
        this.cEditor.beginChange();
        this.cEditor.setFontColor(fontColor);
        this.cEditor.endChange(true);
    }
    
    public Color getFontColor() {
        return this.cEditor.getFontColor();
    }
    
    public void setURL(String url) {
        if ("".equals(url)) {
            url = null;
        }
        this.cEditor.beginChange();
        this.cEditor.setURL(url);
        this.cEditor.endChange(true);
    }
    
    public String getURL() {
        return this.cEditor.getURL();
    }
    
    public void selectURL() {
        this.cEditor.selectURL();
    }
    
    public void deselect() {
        this.cEditor.deselect();
    }
    
    public void selectAll() {
        this.cEditor.selectAll();
    }
    
    public void setNormal() {
        this.cEditor.beginChange();
        this.cEditor.setNormal();
        this.cEditor.endChange(true);
    }
    
    public void setAlignment(final int alignment) {
        this.cEditor.beginChange();
        this.cEditor.setAlignment(alignment);
        this.cEditor.endChange(true);
    }
    
    public int getAlignment() {
        return this.cEditor.getAlignment();
    }
    
    public void setFirstIndent(final int firstIndent) {
        this.cEditor.beginChange();
        this.cEditor.setFirstIndent(firstIndent);
        this.cEditor.endChange(true);
    }
    
    public int getFirstIndent() {
        return this.cEditor.getFirstIndent();
    }
    
    public void setRestIndent(final int restIndent) {
        this.cEditor.beginChange();
        this.cEditor.setRestIndent(restIndent);
        this.cEditor.endChange(true);
    }
    
    public int getRestIndent() {
        return this.cEditor.getRestIndent();
    }
    
    public void setRightIndent(final int rightIndent) {
        this.cEditor.beginChange();
        this.cEditor.setRightIndent(rightIndent);
        this.cEditor.endChange(true);
    }
    
    public int getRightIndent() {
        return this.cEditor.getRightIndent();
    }
    
    public char getBullet() {
        return this.cEditor.getBullet();
    }
    
    public void setSpaceAbove(final int spaceAbove) {
        this.cEditor.beginChange();
        this.cEditor.setSpaceAbove(spaceAbove);
        this.cEditor.endChange(true);
    }
    
    public int getSpaceAbove() {
        return this.cEditor.getSpaceAbove();
    }
    
    public void setPointSize(final String s, final int n) {
        this.cEditor.beginChange();
        this.cEditor.setPointSize(s, n);
        this.cEditor.endChange(true);
    }
    
    public int getPointSize(final String s) {
        return this.cEditor.getPointSize(s);
    }
    
    public void setFaceName(final String s, final String s2) {
        this.cEditor.beginChange();
        this.cEditor.setFaceName(s, s2);
        this.cEditor.endChange(true);
    }
    
    public String getFaceName(final String s) {
        return this.cEditor.getFaceName(s);
    }
    
    public void setFontColor(final String s, final Color color) {
        this.cEditor.beginChange();
        this.cEditor.setFontColor(s, color);
        this.cEditor.endChange(true);
    }
    
    public Color getFontColor(final String s) {
        return this.cEditor.getFontColor(s);
    }
    
    public String getDataFromURL(final String s) {
        return this.cEditor.getDataFromURL(s);
    }
    
    public void readText(final String s) {
        this.cEditor.readText(s);
    }
    
    public void setText(final String s, final String s2) {
        this.cEditor.beginChange();
        this.cEditor.setText(s, s2, false);
        this.cEditor.endChange(false);
        this.cEdit.repaint();
    }
    
    public void insertText(final String s) {
        this.cEditor.beginChange();
        this.cEditor.insertText(s);
        this.cEditor.endChange(false);
        this.cEdit.repaint();
    }
    
    public void appendText(final String s, final String s2) {
        this.cEditor.beginChange();
        this.cEditor.setText(s, s2, true);
        this.cEditor.endChange(true);
    }
    
    public String getText(final String s) {
        return this.cEditor.getText(s);
    }
    
    public boolean insertImage(final Applet applet, final String s, final String s2, final String s3) {
        return this.cEditor.insertImage(s, s2, s3, 2);
    }
    
    public String getHeadline() {
        return this.cEditor.getHeadline();
    }
    
    public String setHeadline(final String s, final String s2, final String s3) {
        return this.cEditor.setHeadline(s, s2, s3);
    }
    
    public int countEscObjects(final int n) {
        return this.cEditor.cEscMgr.countEscObjects(n);
    }
    
    public void createHTMLStyles() {
        new CHtmlCommon(this.cEditor).createHtmlStyles();
    }
    
    public String[] getHTMLStyleNames() {
        return CHtmlCommon.getStyles();
    }
    
    public String getDefaultStyleName() {
        return this.getHTMLStyleNames()[0];
    }
    
    public int getHTMLBaseIndent() {
        return CHtmlCommon.getBaseIndent();
    }
    
    public void showCursor() {
        this.cEditor.showCursor();
    }
    
    public String getCursorPos() {
        final int[] firstLineOffsets = this.cEditor.getContainer().getFirstLineOffsets();
        final String string = String.valueOf(firstLineOffsets[0]) + ',' + String.valueOf(firstLineOffsets[1]) + ',';
        final int[] insertionPointOffsets = this.cEditor.getInsertionPointOffsets();
        return string + String.valueOf(insertionPointOffsets[0]) + ',' + String.valueOf(insertionPointOffsets[1]);
    }
    
    public boolean setCursorPos(final String s) {
        final int[] cursorPos = new int[4];
        int index = s.indexOf(44, 0);
        cursorPos[0] = Integer.parseInt(s.substring(0, index++));
        cursorPos[1] = Integer.parseInt(s.substring(index, s.indexOf(44, index)));
        int index2 = s.indexOf(44, index);
        cursorPos[2] = Integer.parseInt(s.substring(++index2, s.lastIndexOf(44)));
        cursorPos[3] = Integer.parseInt(s.substring(s.lastIndexOf(44) + 1));
        this.cEditor.flow(this.cEdit.getGraphics());
        this.cEditor.setCursorPos(cursorPos);
        return true;
    }
    
    public void setLinkColor(final Color linkColor) {
        this.cEditor.setLinkColor(linkColor);
    }
    
    public Color getLinkColor() {
        return this.cEditor.getLinkColor();
    }
    
    public void moveToStart() {
        this.cEditor.moveBOS(2);
    }
    
    public void moveToEnd() {
        this.cEditor.moveEOS(2);
    }
    
    public boolean select(final int n, final int n2) {
        return this.cEditor.select(n, n2);
    }
    
    public boolean replace(final int n, final int n2, final String s) {
        this.cEditor.beginChange();
        final boolean replace = this.cEditor.replace(n, n2, s);
        this.cEditor.endChange(true);
        return replace;
    }
    
    public boolean insertLinkedText(final String s, final String s2) {
        this.cEditor.beginChange();
        final boolean insertLinkedText = this.cEditor.insertLinkedText(s, s2);
        this.cEditor.endChange(true);
        return insertLinkedText;
    }
    
    public synchronized void addActionListener(final ActionListener cListener) {
        this.cListener = cListener;
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.cListener = null;
    }
    
    void processActionEvent(final boolean b) {
        if (this.cListener != null && !b) {
            this.cListener.actionPerformed(null);
        }
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.cEdit.repaint();
    }
    
    public void requestFocus() {
        this.cEdit.requestFocus();
    }
    
    public void setUILocale(final Locale uiLocale) {
        this.cEdit.setUILocale(uiLocale);
    }
    
    public void setActiveLocale(final Locale activeLocale) {
        this.cEdit.setActiveLocale(activeLocale);
    }
    
    public void render(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Dimension size = this.cEdit.getSize();
        this.cEditor.setSize(n3, n4);
        graphics.translate(n, n2);
        this.cEditor.paint(graphics, graphics);
        graphics.translate(-n, -n2);
        this.cEditor.setSize(size.width, size.height);
    }
}
