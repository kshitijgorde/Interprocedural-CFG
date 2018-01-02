// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public class MsgParser
{
    private static final String SEPARATORS = " ,.<>/?\"';:][}{=+-_()*&^%$#@!~`\t\\";
    private static final String IMG_TAG = "::";
    private Hashtable smileMapping;
    
    public MsgParser(final Hashtable smileMapping) {
        this.smileMapping = null;
        this.smileMapping = smileMapping;
    }
    
    public Vector parseMessage(final String s) {
        final Vector vector = new Vector();
        if (s == null) {
            return vector;
        }
        if (s.length() < 2) {
            this.addText(vector, s, 0, s.length());
            return vector;
        }
        final int length = s.length();
        int i = 0;
        int n = 0;
        while (i < length) {
            final String match = this.findMatch(i, s);
            if (match == null) {
                ++i;
            }
            else {
                this.addText(vector, s, n, i);
                if (match.equals("::")) {
                    final int separator = this.findSeparator(s, " ,.<>/?\"';:][}{=+-_()*&^%$#@!~`\t\\", "::".length() + i);
                    if (separator < 0) {
                        this.addImage(vector, s, i, length);
                        i = (n = length);
                    }
                    else {
                        this.addImage(vector, s, i, separator);
                        i = (n = separator);
                    }
                }
                else {
                    this.addSmiley(vector, match);
                    i = (n = i + match.length());
                }
            }
        }
        this.addText(vector, s, n, i);
        return vector;
    }
    
    private String findMatch(final int n, final String s) {
        if (s.startsWith("::", n)) {
            return "::";
        }
        return this.findSmileyMatch(n, s);
    }
    
    private String findSmileyMatch(final int n, final String s) {
        final Enumeration<String> keys = this.smileMapping.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s.startsWith(s2, n)) {
                return s2;
            }
        }
        return null;
    }
    
    private void addImage(final Vector vector, final String s, final int n, final int n2) {
        final MessageElement messageElement = new MessageElement();
        messageElement.text = s.substring(n, n2);
        messageElement.isImage = true;
        if (messageElement.text.length() == "::".length()) {
            messageElement.imageID = "";
        }
        else {
            messageElement.imageID = messageElement.text.substring("::".length());
        }
        vector.addElement(messageElement);
    }
    
    private void addText(final Vector vector, final String s, final int n, final int n2) {
        if (n == n2) {
            return;
        }
        final String substring = s.substring(n, n2);
        final MessageElement messageElement = new MessageElement();
        messageElement.text = substring;
        vector.addElement(messageElement);
    }
    
    private void addSmiley(final Vector vector, final String text) {
        final MessageElement messageElement = new MessageElement();
        messageElement.text = text;
        messageElement.isImage = true;
        messageElement.imageID = this.smileMapping.get(text);
        if (messageElement.imageID == null) {
            messageElement.imageID = "";
        }
        vector.addElement(messageElement);
    }
    
    private int findSeparator(final String s, final String s2, final int n) {
        if (n >= s.length()) {
            return -1;
        }
        for (int i = n; i < s.length(); ++i) {
            if (s2.indexOf(s.charAt(i)) >= 0) {
                return i;
            }
        }
        return -1;
    }
}
