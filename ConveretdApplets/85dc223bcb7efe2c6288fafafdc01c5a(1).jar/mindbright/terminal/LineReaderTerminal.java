// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;

public final class LineReaderTerminal implements TerminalListener
{
    TerminalWin terminal;
    StringBuffer readLineStr;
    boolean echoStar;
    boolean isReadingLine;
    ExternalMessageException extMsg;
    
    public LineReaderTerminal(final TerminalWin terminal) {
        (this.terminal = terminal).addTerminalListener(this);
    }
    
    public void print(final String str) {
        if (this.terminal != null) {
            this.terminal.write(str);
        }
        else {
            System.out.print(str);
        }
    }
    
    public void println(final String str) {
        if (this.terminal != null) {
            this.terminal.write(str + "\n\r");
        }
        else {
            System.out.println(str);
        }
    }
    
    public void breakPromptLine(final String msg) {
        if (this.isReadingLine) {
            synchronized (this) {
                this.extMsg = new ExternalMessageException(msg);
                this.notify();
            }
        }
    }
    
    public String readLine(final String defaultVal) {
        synchronized (this) {
            if (defaultVal != null) {
                this.readLineStr = new StringBuffer(defaultVal);
                this.terminal.write(defaultVal);
            }
            else {
                this.readLineStr = new StringBuffer();
            }
            this.isReadingLine = true;
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
            this.isReadingLine = false;
        }
        return this.readLineStr.toString();
    }
    
    public String promptLine(final String prompt, final String defaultVal, final boolean echoStar) throws ExternalMessageException {
        String line = null;
        if (this.terminal != null) {
            this.terminal.setAttribute(1, true);
            this.terminal.write(prompt);
            this.terminal.setAttribute(1, false);
            this.echoStar = echoStar;
            line = this.readLine(defaultVal);
            this.echoStar = false;
        }
        if (this.extMsg != null) {
            final ExternalMessageException msg = this.extMsg;
            this.extMsg = null;
            throw msg;
        }
        return line;
    }
    
    public synchronized void typedChar(final char c) {
        if (this.isReadingLine) {
            if (c == '\u007f' || c == '\b') {
                if (this.readLineStr.length() > 0) {
                    boolean ctrlChar = false;
                    if (this.readLineStr.charAt(this.readLineStr.length() - 1) < ' ') {
                        ctrlChar = true;
                    }
                    this.readLineStr.setLength(this.readLineStr.length() - 1);
                    this.terminal.write('\b');
                    if (ctrlChar) {
                        this.terminal.write('\b');
                    }
                    this.terminal.write(' ');
                    if (ctrlChar) {
                        this.terminal.write(' ');
                    }
                    this.terminal.write('\b');
                    if (ctrlChar) {
                        this.terminal.write('\b');
                    }
                }
                else {
                    this.terminal.doBell();
                }
            }
            else if (c == '\r') {
                this.notify();
                this.terminal.write("\n\r");
            }
            else {
                this.readLineStr.append(c);
                if (this.echoStar) {
                    this.terminal.write('*');
                }
                else {
                    this.terminal.write(c);
                }
            }
        }
    }
    
    public void sendBytes(final byte[] b) {
        for (int i = 0; i < b.length; ++i) {
            this.typedChar((char)b[i]);
        }
    }
    
    public void signalWindowChanged(final int rows, final int cols, final int vpixels, final int hpixels) {
    }
    
    public void setSelection(final String selection) {
    }
    
    public String getSelection() {
        return null;
    }
    
    public void selectionAvailable(final boolean val) {
    }
    
    public static void main(final String[] argv) {
        final Frame frame = new Frame();
        final TerminalWin terminal = new TerminalWin(frame, new TerminalXTerm());
        final LineReaderTerminal linereader = new LineReaderTerminal(terminal);
        frame.setLayout(new BorderLayout());
        frame.add(terminal.getPanelWithScrollbar(), "Center");
        frame.pack();
        frame.show();
        linereader.println("Now entering lines...");
        try {
            while (true) {
                final String line = linereader.promptLine("prompt> ", "", false);
                System.out.println("line: " + line);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static class ExternalMessageException extends Exception
    {
        public ExternalMessageException(final String msg) {
            super(msg);
        }
    }
}
