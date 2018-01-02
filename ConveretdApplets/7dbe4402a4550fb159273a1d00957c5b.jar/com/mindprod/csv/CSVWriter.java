// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.csv;

import java.io.PrintWriter;
import java.text.DecimalFormat;

public final class CSVWriter
{
    private static final boolean DEBUGGING = false;
    private static final DecimalFormat df;
    private PrintWriter pw;
    private String lineSeparator;
    private boolean pendingLoneEmptyField;
    private final boolean trim;
    private boolean wasPreviousField;
    private final char commentChar;
    private final char quoteChar;
    private final char separatorChar;
    private int lineCount;
    private final int quoteLevel;
    
    public CSVWriter(final PrintWriter pw) {
        this(pw, 1, ',', '\"', '#', true);
    }
    
    public CSVWriter(final PrintWriter pw, final int quoteLevel, final char separatorChar, final char quoteChar, final char commentChar, final boolean trim) {
        this.lineSeparator = "\r\n";
        this.wasPreviousField = false;
        this.pw = pw;
        this.quoteLevel = quoteLevel;
        this.separatorChar = separatorChar;
        this.quoteChar = quoteChar;
        this.commentChar = commentChar;
        this.trim = trim;
    }
    
    public void close() {
        if (this.pw != null) {
            this.pw.close();
            this.pw = null;
        }
    }
    
    public int getLineCount() {
        return this.lineCount;
    }
    
    public void nl() {
        if (this.pw == null) {
            throw new IllegalArgumentException("attempt to write to a closed CSVWriter");
        }
        if (this.pendingLoneEmptyField) {
            this.pw.write(this.quoteChar);
            this.pw.write(this.quoteChar);
        }
        this.pw.write(this.lineSeparator);
        this.wasPreviousField = false;
        this.pendingLoneEmptyField = false;
        ++this.lineCount;
    }
    
    public void nl(final String comment) {
        if (this.pw == null) {
            throw new IllegalArgumentException("attempt to write to a closed CSVWriter");
        }
        if (this.wasPreviousField) {
            if (this.pendingLoneEmptyField) {
                this.pw.write(this.quoteChar);
                this.pw.write(this.quoteChar);
            }
            this.pw.write(32);
        }
        this.pw.write(this.commentChar);
        if (comment.length() <= 0 || comment.charAt(0) != this.commentChar) {
            this.pw.write(32);
        }
        this.pw.write(comment.trim());
        this.pw.write(this.lineSeparator);
        this.wasPreviousField = false;
        this.pendingLoneEmptyField = false;
        ++this.lineCount;
    }
    
    public void nl(final String[] fields, final boolean lastFieldWasComment) {
        if (lastFieldWasComment) {
            for (int i = 0; i < fields.length - 1; ++i) {
                this.put(fields[i]);
            }
            this.nl(fields[fields.length - 1]);
        }
        else {
            for (final String field : fields) {
                this.put(field);
            }
            this.nl();
        }
    }
    
    public void put(final String... fields) {
        for (final String field : fields) {
            this.put(field);
        }
    }
    
    public void put(final int i) {
        this.put(Integer.toString(i));
    }
    
    public void put(final char c) {
        this.put(String.valueOf(c));
    }
    
    public void put(final boolean b) {
        this.put(b ? "true" : "false");
    }
    
    public void put(final long l) {
        this.put(Long.toString(l));
    }
    
    public void put(final double d) {
        this.put(Double.toString(d));
    }
    
    public void put(final float f) {
        this.put(Float.toString(f));
    }
    
    public void put(String s) {
        if (this.pw == null) {
            throw new IllegalArgumentException("attempt to write to a closed CSVWriter");
        }
        if (s == null) {
            this.nl();
            return;
        }
        if (this.trim) {
            s = s.trim();
        }
        if (this.wasPreviousField) {
            this.pw.write(this.separatorChar);
            if (this.quoteLevel == -1) {
                this.pw.write(32);
            }
            this.pendingLoneEmptyField = false;
        }
        else {
            this.pendingLoneEmptyField = (s.trim().length() == 0);
        }
        if (s.indexOf(this.quoteChar) >= 0) {
            this.pw.write(this.quoteChar);
            for (int i = 0, n = s.length(); i < n; ++i) {
                final char c = s.charAt(i);
                if (c == this.quoteChar) {
                    this.pw.write(this.quoteChar);
                    this.pw.write(this.quoteChar);
                }
                else {
                    this.pw.write(c);
                }
            }
            this.pw.write(this.quoteChar);
        }
        else if (this.quoteLevel == 2 || (this.quoteLevel == 1 && s.indexOf(32) >= 0) || s.indexOf(this.separatorChar) >= 0 || s.indexOf(this.commentChar) >= 0) {
            this.pw.write(this.quoteChar);
            this.pw.write(s);
            this.pw.write(this.quoteChar);
            this.pendingLoneEmptyField = false;
        }
        else {
            this.pw.write(s);
        }
        this.wasPreviousField = true;
    }
    
    public void put(final double d, final int places) {
        CSVWriter.df.setMaximumFractionDigits(places);
        this.put(CSVWriter.df.format(d));
    }
    
    public void setLineSeparator(final String lineSeparator) {
        this.lineSeparator = ((lineSeparator == null) ? System.getProperty("line.separator") : lineSeparator);
    }
    
    static {
        df = new DecimalFormat("##0.000");
    }
}
