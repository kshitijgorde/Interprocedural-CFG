// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion;

import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.Vector;
import anon.shared.AbstractChannel;

public class MixminionPOPChannel extends AbstractChannel
{
    private int m_state;
    private Vector m_messages;
    private String[] m_deleted;
    
    public MixminionPOPChannel() {
        this.m_state = -1;
        this.m_messages = new Vector();
        this.m_deleted = null;
        this.m_state = 0;
        try {
            this.m_deleted = new String[this.m_messages.size()];
            for (int i = 0; i < this.m_messages.size(); ++i) {
                this.m_deleted[i] = (String)this.m_messages.elementAt(i);
            }
            this.toClient("+OK JAP POP3 server ready\r\n");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void close_impl() {
    }
    
    protected void toClient(final String s) throws IOException {
        this.recv(s.getBytes(), 0, s.length());
    }
    
    protected void send(final byte[] array, final int n) throws IOException {
        final String s = new String(array, 0, n);
        if (this.m_state == 0) {
            if (s.toUpperCase().startsWith("USER")) {
                this.m_state = 1;
                this.toClient("+OK\r\n");
            }
            else {
                if (!s.toUpperCase().startsWith("AUTH") && !s.toUpperCase().startsWith("CAPA")) {
                    throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
                }
                this.toClient("-ERR unrecognized\r\n");
            }
        }
        else if (this.m_state == 1) {
            if (!s.toUpperCase().startsWith("PASS")) {
                throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
            }
            this.m_state = 2;
            this.toClient("+OK\r\n");
        }
        else if (this.m_state == 2) {
            if (s.toUpperCase().startsWith("STAT")) {
                int n2 = 0;
                for (int i = 0; i < this.m_messages.size(); ++i) {
                    n2 += ((String)this.m_messages.elementAt(i)).getBytes().length;
                }
                this.toClient("+OK " + this.m_messages.size() + " " + n2 + "\r\n");
            }
            else if (s.toUpperCase().startsWith("LIST")) {
                this.m_state = 3;
                this.toClient("+OK " + this.m_messages.size() + " messages" + "\r\n");
                for (int j = 0; j < this.m_messages.size(); ++j) {
                    this.toClient(j + 1 + " " + ((String)this.m_messages.elementAt(j)).getBytes().length + "\r\n");
                }
                this.toClient(".\r\n");
            }
            else {
                if (!s.toUpperCase().startsWith("QUIT")) {
                    throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
                }
                this.toClient("+OK\r\n");
            }
        }
        else if (this.m_state == 3) {
            if (s.startsWith("UIDL") || s.startsWith("XTND")) {
                this.toClient("-ERR unrecognized\r\n");
            }
            else if (s.startsWith("TOP")) {
                final int int1 = Integer.parseInt(s.substring(4, 5));
                final int n3 = 1;
                System.out.println("id: " + int1 + " lines: " + n3);
                this.toClient("+OK " + ((String)this.m_messages.elementAt(int1 - 1)).getBytes().length + " octets\r\n");
                final LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(this.m_messages.elementAt(int1 - 1)));
                String s2 = lineNumberReader.readLine();
                for (int n4 = 0; n4 < 4 + n3 || s2 == null; s2 = lineNumberReader.readLine(), ++n4) {
                    this.toClient(s2 + "\r\n");
                }
                this.toClient(".\r\n");
            }
            else if (s.startsWith("RETR")) {
                final int n5 = 1;
                this.toClient("+OK " + ((String)this.m_messages.elementAt(n5 - 1)).getBytes().length + " octets\r\n");
                final LineNumberReader lineNumberReader2 = new LineNumberReader(new StringReader(this.m_messages.elementAt(n5 - 1)));
                for (String s3 = lineNumberReader2.readLine(); s3 != null; s3 = lineNumberReader2.readLine()) {
                    this.toClient(s3 + "\r\n");
                }
                this.toClient(".\r\n");
            }
            else if (s.startsWith("DELE")) {
                final int n6 = 1;
                this.m_deleted[n6 - 1] = null;
                this.toClient("+OK message " + n6 + " deleted\r\n");
            }
            else {
                if (!s.toUpperCase().startsWith("QUIT")) {
                    throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
                }
                this.m_messages = new Vector();
                for (int k = 0; k < this.m_deleted.length; ++k) {
                    if (this.m_deleted[k] != null) {
                        this.m_messages.addElement(this.m_deleted[k]);
                    }
                }
                if (this.m_messages.size() == 0) {
                    this.m_messages = null;
                }
                this.toClient("+OK\r\n");
            }
        }
    }
    
    public int getOutputBlockSize() {
        return 1000;
    }
}
