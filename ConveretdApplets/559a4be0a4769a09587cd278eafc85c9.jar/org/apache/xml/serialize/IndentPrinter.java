// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class IndentPrinter extends Printer
{
    private StringBuffer _line;
    private StringBuffer _text;
    private int _spaces;
    private int _thisIndent;
    private int _nextIndent;
    
    public IndentPrinter(final Writer writer, final OutputFormat format) {
        super(writer, format);
        this._line = new StringBuffer(80);
        this._text = new StringBuffer(20);
        this._spaces = 0;
        final boolean b = false;
        this._nextIndent = (b ? 1 : 0);
        this._thisIndent = (b ? 1 : 0);
    }
    
    public void enterDTD() {
        if (super._dtdWriter == null) {
            this._line.append((Object)this._text);
            this._text = new StringBuffer(20);
            this.flushLine(false);
            super._dtdWriter = new StringWriter();
            super._docWriter = super._writer;
            super._writer = super._dtdWriter;
        }
    }
    
    public String leaveDTD() {
        if (super._writer == super._dtdWriter) {
            this._line.append((Object)this._text);
            this._text = new StringBuffer(20);
            this.flushLine(false);
            super._writer = super._docWriter;
            return super._dtdWriter.toString();
        }
        return null;
    }
    
    public void printText(final String text) {
        this._text.append(text);
    }
    
    public void printText(final StringBuffer text) {
        this._text.append((Object)text);
    }
    
    public void printText(final char ch) {
        this._text.append(ch);
    }
    
    public void printText(final char[] chars, final int start, final int length) {
        this._text.append(chars, start, length);
    }
    
    public void printSpace() {
        if (this._text.length() > 0) {
            if (super._format.getLineWidth() > 0 && this._thisIndent + this._line.length() + this._spaces + this._text.length() > super._format.getLineWidth()) {
                this.flushLine(false);
                try {
                    super._writer.write(super._format.getLineSeparator());
                }
                catch (IOException except) {
                    if (super._exception == null) {
                        super._exception = except;
                    }
                }
            }
            while (this._spaces > 0) {
                this._line.append(' ');
                --this._spaces;
            }
            this._line.append((Object)this._text);
            this._text = new StringBuffer(20);
        }
        ++this._spaces;
    }
    
    public void breakLine() {
        this.breakLine(false);
    }
    
    public void breakLine(final boolean preserveSpace) {
        if (this._text.length() > 0) {
            while (this._spaces > 0) {
                this._line.append(' ');
                --this._spaces;
            }
            this._line.append((Object)this._text);
            this._text = new StringBuffer(20);
        }
        this.flushLine(preserveSpace);
        try {
            super._writer.write(super._format.getLineSeparator());
        }
        catch (IOException except) {
            if (super._exception == null) {
                super._exception = except;
            }
        }
    }
    
    public void flushLine(final boolean preserveSpace) {
        if (this._line.length() > 0) {
            try {
                if (super._format.getIndenting() && !preserveSpace) {
                    int indent = this._thisIndent;
                    if (2 * indent > super._format.getLineWidth() && super._format.getLineWidth() > 0) {
                        indent = super._format.getLineWidth() / 2;
                    }
                    while (indent > 0) {
                        super._writer.write(32);
                        --indent;
                    }
                }
                this._thisIndent = this._nextIndent;
                this._spaces = 0;
                super._writer.write(this._line.toString());
                this._line = new StringBuffer(40);
            }
            catch (IOException except) {
                if (super._exception == null) {
                    super._exception = except;
                }
            }
        }
    }
    
    public void flush() {
        if (this._line.length() > 0 || this._text.length() > 0) {
            this.breakLine();
        }
        try {
            super._writer.flush();
        }
        catch (IOException except) {
            if (super._exception == null) {
                super._exception = except;
            }
        }
    }
    
    public void indent() {
        this._nextIndent += super._format.getIndent();
    }
    
    public void unindent() {
        this._nextIndent -= super._format.getIndent();
        if (this._nextIndent < 0) {
            this._nextIndent = 0;
        }
        if (this._line.length() + this._spaces + this._text.length() == 0) {
            this._thisIndent = this._nextIndent;
        }
    }
    
    public int getNextIndent() {
        return this._nextIndent;
    }
    
    public void setNextIndent(final int indent) {
        this._nextIndent = indent;
    }
    
    public void setThisIndent(final int indent) {
        this._thisIndent = indent;
    }
}
