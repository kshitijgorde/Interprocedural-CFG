import java.io.IOException;
import java.awt.IllegalComponentStateException;
import java.util.Date;
import java.awt.TextArea;
import java.io.BufferedReader;

// 
// Decompiled by Procyon v0.5.30
// 

class StreamListener extends Thread
{
    BufferedReader in;
    TextArea output;
    ChatClient frame;
    
    public StreamListener(final BufferedReader in, final TextArea output, final ChatClient frame) {
        this.in = in;
        this.output = output;
        this.frame = frame;
        this.start();
    }
    
    private void append_and_scroll(final String s) {
        if (this.frame.timestamp) {
            this.output.append(String.valueOf(String.valueOf(new Date())) + " " + s);
        }
        else {
            this.output.append(s);
        }
        try {
            this.output.setCaretPosition(this.output.getText().length());
        }
        catch (IllegalComponentStateException ex) {}
    }
    
    public void run() {
        try {
            while (true) {
                final String line = this.in.readLine();
                if (line == null) {
                    break;
                }
                if (line.endsWith("Clear")) {
                    this.frame.clientlist.clear();
                }
                else {
                    if (line.length() > 12 && line.substring(0, 12).equals("<ChatServer>")) {
                        final ChatClient frame = this.frame;
                        ++frame.cLines;
                        this.frame.clientlist.add(line.substring(15));
                    }
                    else {
                        this.append_and_scroll(String.valueOf(line) + "\n");
                    }
                    if (this.frame.sound == 2) {
                        if (this.frame.applet == null || this.frame.bell == null) {
                            continue;
                        }
                        try {
                            this.frame.bell.play();
                        }
                        catch (NegativeArraySizeException ex) {
                            this.append_and_scroll(String.valueOf(ex.toString()) + " (A bug in Java?)\n");
                        }
                    }
                    else {
                        if (this.frame.sound != 1) {
                            continue;
                        }
                        this.frame.getToolkit().beep();
                    }
                }
            }
        }
        catch (IOException ex2) {
            System.err.println(ex2.toString());
        }
        finally {
            this.frame.inputfield.setEditable(false);
            this.frame.closeChat(false);
        }
    }
}
