// 
// Decompiled by Procyon v0.5.30
// 

package kiang.io;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.io.InputStream;

public abstract class LineParser
{
    public final void parse(final InputStream in) throws IOException {
        this.parse(in, Charset.forName("US-ASCII"));
    }
    
    public final void parse(final InputStream in, final Charset charset) throws IOException {
        final BufferedReader lineReader = new BufferedReader(new InputStreamReader(in, charset));
        int lineNum = 0;
        for (String line = lineReader.readLine(); line != null; line = lineReader.readLine()) {
            if (this.shouldParseLine(lineNum, line) && !this.parseLine(lineNum, line)) {
                this.lineError(lineNum, line);
            }
            ++lineNum;
        }
        in.close();
    }
    
    protected abstract boolean parseLine(final int p0, final String p1);
    
    protected void lineError(final int lineNum, final String line) {
        System.err.println("Error parsing line " + lineNum + ": " + line);
    }
    
    protected boolean shouldParseLine(final int lineNum, final String line) {
        return !this.isLineEmpty(line) && !this.isLineComment(line);
    }
    
    protected boolean isLineComment(final String line) {
        return line.matches("^\\s*//.*") || line.matches("^\\s*#.*");
    }
    
    protected boolean isLineEmpty(final String line) {
        return line.matches("^\\s*$");
    }
}
