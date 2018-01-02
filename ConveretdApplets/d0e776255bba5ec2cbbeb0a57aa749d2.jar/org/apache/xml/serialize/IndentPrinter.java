// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

class IndentPrinter extends Printer
{
    private StringBuffer _line;
    private StringBuffer _text;
    private int _spaces;
    private int _thisIndent;
    private int _nextIndent;
    
    IndentPrinter(final Writer writer, final OutputFormat outputFormat) {
        super(writer, outputFormat);
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
    
    public void printText(final String s) {
        this._text.append(s);
    }
    
    public void printText(final StringBuffer sb) {
        this._text.append((Object)sb);
    }
    
    public void printText(final char c) {
        this._text.append(c);
    }
    
    public void printText(final char[] array, final int n, final int n2) {
        this._text.append(array, n, n2);
    }
    
    public void printSpace() {
        if (this._text.length() > 0) {
            if (super._format.getLineWidth() > 0 && this._thisIndent + this._line.length() + this._spaces + this._text.length() > super._format.getLineWidth()) {
                this.flushLine(false);
                try {
                    super._writer.write(super._format.getLineSeparator());
                }
                catch (IOException exception) {
                    if (super._exception == null) {
                        super._exception = exception;
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
    
    public void breakLine(final boolean b) {
        if (this._text.length() > 0) {
            while (this._spaces > 0) {
                this._line.append(' ');
                --this._spaces;
            }
            this._line.append((Object)this._text);
            this._text = new StringBuffer(20);
        }
        this.flushLine(b);
        try {
            super._writer.write(super._format.getLineSeparator());
        }
        catch (IOException exception) {
            if (super._exception == null) {
                super._exception = exception;
            }
        }
    }
    
    public void flushLine(final boolean b) {
        if (this._line.length() > 0) {
            try {
                if (super._format.getIndenting() && !b) {
                    int i = this._thisIndent;
                    if (2 * i > super._format.getLineWidth() && super._format.getLineWidth() > 0) {
                        i = super._format.getLineWidth() / 2;
                    }
                    while (i > 0) {
                        super._writer.write(32);
                        --i;
                    }
                }
                this._thisIndent = this._nextIndent;
                this._spaces = 0;
                super._writer.write(this._line.toString());
                this._line = new StringBuffer(40);
            }
            catch (IOException exception) {
                if (super._exception == null) {
                    super._exception = exception;
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
        catch (IOException exception) {
            if (super._exception == null) {
                super._exception = exception;
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
    
    public void setNextIndent(final int nextIndent) {
        this._nextIndent = nextIndent;
    }
    
    public void setThisIndent(final int thisIndent) {
        this._thisIndent = thisIndent;
    }
}
