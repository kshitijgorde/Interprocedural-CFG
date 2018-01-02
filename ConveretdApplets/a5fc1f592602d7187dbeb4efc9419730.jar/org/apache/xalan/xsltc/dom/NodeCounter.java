// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import java.util.Vector;
import org.apache.xalan.xsltc.Translet;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xalan.xsltc.DOM;

public abstract class NodeCounter implements Axis
{
    public static final int END = -1;
    protected int _node;
    protected int _nodeType;
    protected int _value;
    public final DOM _document;
    public final DTMAxisIterator _iterator;
    public final Translet _translet;
    protected String _format;
    protected String _lang;
    protected String _letterValue;
    protected String _groupSep;
    protected int _groupSize;
    private boolean separFirst;
    private boolean separLast;
    private Vector separToks;
    private Vector formatToks;
    private int nSepars;
    private int nFormats;
    private static String[] Thousands;
    private static String[] Hundreds;
    private static String[] Tens;
    private static String[] Ones;
    
    protected NodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
        this._node = -1;
        this._nodeType = -1;
        this._value = Integer.MIN_VALUE;
        this.separFirst = true;
        this.separLast = false;
        this.separToks = null;
        this.formatToks = null;
        this.nSepars = 0;
        this.nFormats = 0;
        this._translet = translet;
        this._document = document;
        this._iterator = iterator;
    }
    
    public abstract NodeCounter setStartNode(final int p0);
    
    public NodeCounter setValue(final int value) {
        this._value = value;
        return this;
    }
    
    protected void setFormatting(final String format, final String lang, final String letterValue, final String groupSep, final String groupSize) {
        this._lang = lang;
        this._format = format;
        this._groupSep = groupSep;
        this._letterValue = letterValue;
        try {
            this._groupSize = Integer.parseInt(groupSize);
        }
        catch (NumberFormatException e) {
            this._groupSize = 0;
        }
        final int length = this._format.length();
        boolean isFirst = true;
        this.separFirst = true;
        this.separLast = false;
        this.separToks = new Vector();
        this.formatToks = new Vector();
        int j = 0;
        int i = 0;
        while (i < length) {
            char c = this._format.charAt(i);
            j = i;
            while (Character.isLetterOrDigit(c) && ++i != length) {
                c = this._format.charAt(i);
            }
            if (i > j) {
                if (isFirst) {
                    this.separToks.addElement(".");
                    final boolean separFirst = false;
                    this.separFirst = separFirst;
                    isFirst = separFirst;
                }
                this.formatToks.addElement(this._format.substring(j, i));
            }
            if (i == length) {
                break;
            }
            c = this._format.charAt(i);
            j = i;
            while (!Character.isLetterOrDigit(c) && ++i != length) {
                c = this._format.charAt(i);
                isFirst = false;
            }
            if (i <= j) {
                continue;
            }
            this.separToks.addElement(this._format.substring(j, i));
        }
        this.nSepars = this.separToks.size();
        this.nFormats = this.formatToks.size();
        if (this.nSepars > this.nFormats) {
            this.separLast = true;
        }
        if (this.separFirst) {
            --this.nSepars;
        }
        if (this.separLast) {
            --this.nSepars;
        }
        if (this.nSepars == 0) {
            this.separToks.insertElementAt(".", 1);
            ++this.nSepars;
        }
        if (this.separFirst) {
            ++this.nSepars;
        }
    }
    
    public NodeCounter setDefaultFormatting() {
        this.setFormatting("1", "en", "alphabetic", null, null);
        return this;
    }
    
    public abstract String getCounter();
    
    public String getCounter(final String format, final String lang, final String letterValue, final String groupSep, final String groupSize) {
        this.setFormatting(format, lang, letterValue, groupSep, groupSize);
        return this.getCounter();
    }
    
    public boolean matchesCount(final int node) {
        return this._nodeType == this._document.getExpandedTypeID(node);
    }
    
    public boolean matchesFrom(final int node) {
        return false;
    }
    
    protected String formatNumbers(final int value) {
        return this.formatNumbers(new int[] { value });
    }
    
    protected String formatNumbers(final int[] values) {
        final int nValues = values.length;
        final int length = this._format.length();
        boolean isEmpty = true;
        for (int i = 0; i < nValues; ++i) {
            if (values[i] != Integer.MIN_VALUE) {
                isEmpty = false;
            }
        }
        if (isEmpty) {
            return "";
        }
        boolean isFirst = true;
        int t = 0;
        int n = 0;
        int s = 1;
        final StringBuffer buffer = new StringBuffer();
        if (this.separFirst) {
            buffer.append(this.separToks.elementAt(0));
        }
        while (n < nValues) {
            final int value = values[n];
            if (value != Integer.MIN_VALUE) {
                if (!isFirst) {
                    buffer.append(this.separToks.elementAt(s++));
                }
                this.formatValue(value, this.formatToks.elementAt(t++), buffer);
                if (t == this.nFormats) {
                    --t;
                }
                if (s >= this.nSepars) {
                    --s;
                }
                isFirst = false;
            }
            ++n;
        }
        if (this.separLast) {
            buffer.append(this.separToks.lastElement());
        }
        return buffer.toString();
    }
    
    private void formatValue(final int value, final String format, final StringBuffer buffer) {
        final char c = format.charAt(0);
        if (Character.isDigit(c)) {
            final char zero = (char)(c - Character.getNumericValue(c));
            StringBuffer temp = buffer;
            if (this._groupSize > 0) {
                temp = new StringBuffer();
            }
            String s = "";
            for (int n = value; n > 0; n /= 10) {
                s = (char)(zero + n % 10) + s;
            }
            for (int i = 0; i < format.length() - s.length(); ++i) {
                temp.append(zero);
            }
            temp.append(s);
            if (this._groupSize > 0) {
                for (int j = 0; j < temp.length(); ++j) {
                    if (j != 0 && (temp.length() - j) % this._groupSize == 0) {
                        buffer.append(this._groupSep);
                    }
                    buffer.append(temp.charAt(j));
                }
            }
        }
        else if (c == 'i' && !this._letterValue.equals("alphabetic")) {
            buffer.append(this.romanValue(value));
        }
        else if (c == 'I' && !this._letterValue.equals("alphabetic")) {
            buffer.append(this.romanValue(value).toUpperCase());
        }
        else {
            final int min = c;
            int max;
            if ((max = c) >= 945 && c <= '\u03c9') {
                max = 969;
            }
            else {
                while (Character.isLetterOrDigit((char)(max + 1))) {
                    ++max;
                }
            }
            buffer.append(this.alphaValue(value, min, max));
        }
    }
    
    private String alphaValue(final int value, final int min, final int max) {
        if (value <= 0) {
            return "" + value;
        }
        final int range = max - min + 1;
        final char last = (char)((value - 1) % range + min);
        if (value > range) {
            return this.alphaValue((value - 1) / range, min, max) + last;
        }
        return "" + last;
    }
    
    private String romanValue(final int n) {
        if (n <= 0 || n > 4000) {
            return "" + n;
        }
        return NodeCounter.Thousands[n / 1000] + NodeCounter.Hundreds[n / 100 % 10] + NodeCounter.Tens[n / 10 % 10] + NodeCounter.Ones[n % 10];
    }
    
    static {
        NodeCounter.Thousands = new String[] { "", "m", "mm", "mmm" };
        NodeCounter.Hundreds = new String[] { "", "c", "cc", "ccc", "cd", "d", "dc", "dcc", "dccc", "cm" };
        NodeCounter.Tens = new String[] { "", "x", "xx", "xxx", "xl", "l", "lx", "lxx", "lxxx", "xc" };
        NodeCounter.Ones = new String[] { "", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix" };
    }
}
