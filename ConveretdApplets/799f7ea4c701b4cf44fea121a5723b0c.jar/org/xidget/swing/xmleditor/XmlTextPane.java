// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyListener;
import javax.swing.text.EditorKit;
import javax.swing.JTextPane;

public class XmlTextPane extends JTextPane
{
    private int tabIndent;
    private String foundTag;
    
    public XmlTextPane() {
        this.setEditorKitForContentType("text/xml", new XmlEditorKit());
        this.setContentType("text/xml");
        this.tabIndent = 2;
        this.addKeyListener(new AutoKeyListener((AutoKeyListener)null));
    }
    
    private int determineIndent() {
        int i = this.getCaretPosition();
        while (i > 0) {
            --i;
            if (this.getLineAt(i).trim().length() > 0) {
                return this.getIndentAt(i);
            }
        }
        return 0;
    }
    
    private int getStartOfLine(final int n) {
        final String text = this.getText();
        for (int i = n - 1; i >= 0; --i) {
            if (text.charAt(i) == '\n') {
                return i + 1;
            }
        }
        return 0;
    }
    
    private int getEndOfLine(final int n) {
        final String text = this.getText();
        final int index = text.indexOf(10, n);
        if (index < 0) {
            return text.length();
        }
        return index;
    }
    
    private String getLineAt(final int n) {
        return this.getText().substring(this.getStartOfLine(n), this.getEndOfLine(n));
    }
    
    private int getIndentAt(final int n) {
        final String text = this.getText();
        int n2 = 0;
        for (int startOfLine = this.getStartOfLine(n); text.charAt(startOfLine) != '\n'; ++startOfLine) {
            if (text.charAt(startOfLine) == '\t') {
                n2 += this.tabIndent;
            }
            else {
                if (text.charAt(startOfLine) != ' ') {
                    break;
                }
                ++n2;
            }
        }
        return n2;
    }
    
    public void gotoStart() {
        this.setCaretPosition(0);
    }
    
    public void gotoEnd() {
        this.setCaretPosition(this.getDocument().getLength());
    }
    
    public void gotoLineStart(final boolean b) {
        final int caretPosition = this.getCaretPosition();
        if (caretPosition == 0) {
            return;
        }
        final String text = this.getText();
        int i = caretPosition - 1;
        while (i >= 0) {
            if (text.charAt(i) == '\n') {
                final int startOfLine = this.getStartOfLine(i + 1);
                final int indent = this.getIndentAt(i + 1);
                if (b && caretPosition != startOfLine + indent) {
                    this.setCaretPosition(i + 1 + indent);
                    break;
                }
                this.setCaretPosition(i + 1);
                break;
            }
            else {
                --i;
            }
        }
    }
    
    public void gotoLineEnd() {
        final int caretPosition = this.getCaretPosition();
        final String text = this.getText();
        int caretPosition2 = text.indexOf(10, caretPosition);
        if (caretPosition2 < 0) {
            caretPosition2 = text.length();
        }
        this.setCaretPosition(caretPosition2);
    }
    
    public void deleteLine() {
        final Document document = this.getDocument();
        final int caretPosition = this.getCaretPosition();
        int startOfLine = this.getStartOfLine(caretPosition);
        int n = this.getEndOfLine(caretPosition) + 1;
        try {
            if (n > document.getLength()) {
                --startOfLine;
                --n;
            }
            if (startOfLine >= 0) {
                document.remove(startOfLine, n - startOfLine);
            }
        }
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    
    private int findPreviousTag(final int n) {
        int n2 = -1;
        int n3 = -1;
        final String text = this.getText();
        for (int i = n; i >= 0; --i) {
            final char char1 = text.charAt(i);
            if (n2 >= 0) {
                if (char1 == '\n') {
                    n2 = -1;
                }
                else if (char1 == '<') {
                    n3 = i;
                    break;
                }
            }
            else if (char1 == '>') {
                n2 = i;
            }
        }
        if (n3 >= 0 && n2 >= 0) {
            this.foundTag = text.substring(n3, n2 + 1);
            return n3;
        }
        return -1;
    }
    
    private int findNextTag(final int n) {
        int n2 = -1;
        int n3 = -1;
        final String text = this.getText();
        for (int i = n; i < text.length(); ++i) {
            final char char1 = text.charAt(i);
            if (n3 >= 0) {
                if (char1 == '\n') {
                    n3 = -1;
                }
                else if (char1 == '>') {
                    n2 = i;
                    break;
                }
            }
            else if (char1 == '<') {
                n3 = i;
            }
        }
        if (n3 >= 0 && n2 >= 0) {
            this.foundTag = text.substring(n3, n2 + 1);
            return n3;
        }
        return -1;
    }
    
    private int findPreviousStartTag(final int n) {
        int n2;
        for (n2 = this.findPreviousTag(n); n2 > 0 && isEndTag(this.foundTag); n2 = this.findPreviousTag(n2 - 1)) {}
        return n2;
    }
    
    private int findNextEndTag(final int n) {
        throw new InternalError("Badly shrinked");
    }
    
    private int findEndTag(final int n, final String s) {
        int n2 = 0;
        for (int i = this.findNextTag(n + 2); i > 0; i = this.findNextTag(i + 1)) {
            if (getNameFromTag(this.foundTag).equals(s)) {
                if (isEndTag(this.foundTag)) {
                    --n2;
                }
                else {
                    ++n2;
                }
                if (n2 < 0) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private static String getNameFromTag(final String s) {
        final int n = (s.charAt(1) == '/') ? 2 : 1;
        final int index = s.indexOf(62, n);
        final int index2 = s.indexOf(32, n);
        if (index < 0) {
            return "";
        }
        String s2 = s.substring(n, (index2 >= 0 && index2 < index) ? index2 : index).trim();
        if (s2.endsWith("/")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        return s2;
    }
    
    private static boolean isEndTag(final String s) {
        for (int i = 1; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '/') {
                return true;
            }
            if (char1 != ' ') {
                return false;
            }
        }
        return false;
    }
    
    private static boolean isEmptyTag(final String s) {
        for (int i = s.length() - 2; i >= 0; --i) {
            if (s.charAt(i) == '/') {
                return true;
            }
            if (s.charAt(i) != ' ') {
                return false;
            }
        }
        return false;
    }
    
    private void autoCompleteTag() {
        int n2;
        final int n = n2 = this.getCaretPosition();
        while (true) {
            n2 = this.findPreviousStartTag(n2 - 1);
            if (n2 < 0) {
                return;
            }
            final String nameFromTag = getNameFromTag(this.foundTag);
            if (isEmptyTag(this.foundTag)) {
                continue;
            }
            final int endTag = this.findEndTag(n2, nameFromTag);
            if (endTag < 0) {
                try {
                    this.getDocument().insertString(n, String.valueOf(nameFromTag) + ">", null);
                    return;
                }
                catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
            if (endTag >= n) {
                return;
            }
        }
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
    }
    
    private class AutoKeyListener implements KeyListener
    {
        @Override
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 10 && keyEvent.getModifiers() == 0) {
                final int caretPosition = XmlTextPane.this.getCaretPosition();
                final int access$0 = XmlTextPane.this.determineIndent();
                try {
                    final char[] array = new char[access$0];
                    Arrays.fill(array, ' ');
                    final StringBuilder sb = new StringBuilder();
                    sb.append('\n');
                    sb.append(array);
                    XmlTextPane.this.getDocument().insertString(caretPosition, sb.toString(), null);
                }
                catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
                keyEvent.consume();
            }
            else if (keyEvent.getKeyCode() == 9) {
                final boolean b = keyEvent.getModifiers() == 0;
                final Document document = XmlTextPane.this.getDocument();
                int selectionStart = XmlTextPane.this.getSelectionStart();
                final int selectionEnd = XmlTextPane.this.getSelectionEnd();
                try {
                    String string = new String();
                    for (int i = 0; i < XmlTextPane.this.tabIndent; ++i) {
                        string = String.valueOf(string) + " ";
                    }
                    String text = null;
                    if (selectionStart > 0) {
                        --selectionStart;
                        for (text = document.getText(0, selectionStart + 1); text.charAt(selectionStart) != '\n' && selectionStart > 0; --selectionStart) {}
                    }
                    int n = selectionEnd;
                    if (selectionStart == 0) {
                        if (b) {
                            document.insertString(0, string, null);
                            n += XmlTextPane.this.tabIndent;
                        }
                        else {
                            if (text == null) {
                                return;
                            }
                            for (int j = 0; j < XmlTextPane.this.tabIndent; ++j) {
                                if (text.charAt(j) != ' ') {
                                    return;
                                }
                            }
                            document.remove(0, XmlTextPane.this.tabIndent);
                            n -= XmlTextPane.this.tabIndent;
                        }
                    }
                    final String text2 = document.getText(selectionStart, n - selectionStart - 1);
                    for (int n2 = selectionStart, k = 0; k < text2.length(); ++k, ++n2) {
                        if (text2.charAt(k) == '\n') {
                            if (b) {
                                document.insertString(n2 + 1, string, null);
                                n2 += XmlTextPane.this.tabIndent;
                            }
                            else if (k + 1 + XmlTextPane.this.tabIndent < text2.length()) {
                                for (int l = 1; l <= XmlTextPane.this.tabIndent; ++l) {
                                    if (text2.charAt(k + l) != ' ') {
                                        return;
                                    }
                                }
                                document.remove(n2 + 1, XmlTextPane.this.tabIndent);
                                n2 -= XmlTextPane.this.tabIndent;
                            }
                        }
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace(System.err);
                }
                keyEvent.consume();
            }
            else if (keyEvent.getKeyCode() == 36 && keyEvent.getModifiers() == 0) {
                XmlTextPane.this.gotoLineStart(true);
                keyEvent.consume();
            }
            else if (keyEvent.getKeyCode() == 35 && keyEvent.getModifiers() == 0) {
                XmlTextPane.this.gotoLineEnd();
                keyEvent.consume();
            }
            else if (keyEvent.getKeyCode() == 36 && keyEvent.getModifiers() == 4) {
                XmlTextPane.this.gotoStart();
                keyEvent.consume();
            }
            else if (keyEvent.getKeyCode() == 35 && keyEvent.getModifiers() == 4) {
                XmlTextPane.this.gotoEnd();
                keyEvent.consume();
            }
        }
        
        @Override
        public void keyReleased(final KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == '/' && keyEvent.getModifiers() == 0) {
                final int caretPosition = XmlTextPane.this.getCaretPosition();
                final String text = XmlTextPane.this.getText();
                if (caretPosition > 1 && text.charAt(caretPosition - 2) == '<') {
                    XmlTextPane.this.autoCompleteTag();
                }
            }
            else if (keyEvent.getKeyCode() == 8 && keyEvent.getModifiers() == 4) {
                XmlTextPane.this.deleteLine();
            }
        }
        
        @Override
        public void keyTyped(final KeyEvent keyEvent) {
        }
    }
}
