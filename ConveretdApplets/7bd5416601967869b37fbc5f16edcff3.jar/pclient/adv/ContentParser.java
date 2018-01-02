// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.util.Vector;
import java.util.Enumeration;
import com.pchat.sc.WindowUtil;
import java.util.ArrayList;
import com.pchat.sc.StringUtil;
import java.util.Hashtable;

public class ContentParser
{
    private static final String SEPS = " ,.<>/?\"';:][}{=+()*&^%$#@!~`\t\\";
    private static final String IMG_TAG = "::";
    private Hashtable smileMapping;
    
    public ContentParser(final Hashtable smileMapping) {
        this.smileMapping = null;
        this.smileMapping = smileMapping;
    }
    
    public ContentElement[] parse(final String s) {
        if (StringUtil.isEmpty(s)) {
            return new ContentElement[0];
        }
        final ArrayList<ContentElement> list = new ArrayList<ContentElement>(64);
        this.parse(s, list);
        final ArrayList<ContentElement> list2 = new ArrayList<ContentElement>(64);
        for (int i = 0; i < list.size(); ++i) {
            final ContentElement contentElement = list.get(i);
            if (contentElement.type == 4) {
                list2.add(contentElement);
            }
            else {
                this.parseLinks(contentElement.text, list2);
            }
        }
        return list2.toArray(new ContentElement[0]);
    }
    
    private void parseLinks(final String s, final ArrayList list) {
        final String[] splitBySpaceAndNewLine = this.splitBySpaceAndNewLine(s);
        StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < splitBySpaceAndNewLine.length; ++i) {
            final String s2 = splitBySpaceAndNewLine[i];
            if (WindowUtil.isLink(s2)) {
                this.addString(list, sb.toString());
                sb = new StringBuffer();
                this.fixLink(list, s2, sb);
            }
            else {
                sb.append(s2);
            }
        }
        this.addString(list, sb.toString());
    }
    
    private void fixLink(final ArrayList list, final String s, final StringBuffer sb) {
        final int index = s.indexOf(" ");
        if (index <= 0) {
            this.addLink(list, s);
            return;
        }
        String s2 = s.substring(0, index);
        final String substring = s.substring(index);
        if (s2.endsWith(".") || s2.endsWith(",") || s2.endsWith("\"") || s2.endsWith("'") || s2.endsWith("?") || s2.endsWith("!") || s2.endsWith("|") || s2.endsWith(";") || s2.endsWith("}") || s2.endsWith("]") || s2.endsWith(">") || s2.endsWith(")")) {
            sb.append(s2.charAt(s2.length() - 1));
            s2 = s2.substring(0, s2.length() - 1);
        }
        sb.append(substring);
        this.addLink(list, s2);
    }
    
    private void addString(final ArrayList list, final String text) {
        if (StringUtil.isEmpty(text)) {
            return;
        }
        final ContentElement contentElement = new ContentElement();
        contentElement.type = 2;
        contentElement.text = text;
        list.add(contentElement);
    }
    
    private void addLink(final ArrayList list, final String text) {
        if (StringUtil.isEmpty(text)) {
            return;
        }
        final ContentElement contentElement = new ContentElement();
        contentElement.type = 6;
        contentElement.text = text;
        contentElement.link = text.trim();
        list.add(contentElement);
    }
    
    public void parse(final String s, final ArrayList list) {
        if (s == null) {
            return;
        }
        if (s.length() < 2) {
            this.addText(list, s, 0, s.length());
            return;
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
                this.addText(list, s, n, i);
                if (match.equals("::")) {
                    final int separator = this.findSeparator(s, " ,.<>/?\"';:][}{=+()*&^%$#@!~`\t\\", "::".length() + i);
                    if (separator < 0) {
                        this.addImage(list, s, i, length);
                        i = (n = length);
                    }
                    else {
                        this.addImage(list, s, i, separator);
                        i = (n = separator);
                    }
                }
                else {
                    this.addSmiley(list, match);
                    i = (n = i + match.length());
                }
            }
        }
        this.addText(list, s, n, i);
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
    
    private void addImage(final ArrayList list, final String s, final int n, final int n2) {
        final ContentElement contentElement = new ContentElement();
        contentElement.text = s.substring(n, n2);
        contentElement.type = 4;
        if (contentElement.text.length() == "::".length()) {
            contentElement.imageID = "";
        }
        else {
            contentElement.imageID = contentElement.text.substring("::".length());
        }
        list.add(contentElement);
    }
    
    private void addText(final ArrayList list, final String s, final int n, final int n2) {
        if (n == n2) {
            return;
        }
        final String substring = s.substring(n, n2);
        final ContentElement contentElement = new ContentElement();
        contentElement.text = substring;
        list.add(contentElement);
    }
    
    private void addSmiley(final ArrayList list, final String text) {
        final ContentElement contentElement = new ContentElement();
        contentElement.text = text;
        contentElement.type = 4;
        contentElement.imageID = this.smileMapping.get(text);
        if (contentElement.imageID == null) {
            contentElement.imageID = "";
        }
        list.add(contentElement);
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
    
    private String[] splitBySpaceAndNewLine(final String s) {
        final Vector vector = new Vector<String>(64);
        int n = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '\n') {
                if (n < i) {
                    vector.addElement(s.substring(n, i));
                }
                vector.addElement("\n");
                n = i + 1;
            }
            else if (s.charAt(i) == ' ') {
                while (i < s.length() && s.charAt(i) == ' ') {
                    ++i;
                }
                vector.addElement(s.substring(n, i));
                n = i;
            }
            if (++i == s.length()) {
                vector.addElement(s.substring(n, i));
                break;
            }
        }
        final int size = vector.size();
        final String[] array = new String[size];
        for (int j = 0; j < size; ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
}
