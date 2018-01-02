// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion;

import anon.mixminion.message.Message;
import java.io.IOException;
import java.util.Vector;
import anon.shared.AbstractChannel;

public class MixminionSMTPChannel extends AbstractChannel
{
    private int m_state;
    private Vector m_receiver;
    private String m_text;
    
    public MixminionSMTPChannel() {
        this.m_state = -1;
        this.m_receiver = new Vector();
        this.m_text = "";
        this.m_state = 0;
        try {
            this.toClient("220 127.0.0.1 SMTP JAP_MailServer\r\n");
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
            if (s.toUpperCase().startsWith("HELO")) {
                this.m_state = 2;
                this.toClient("250 OK\r\n");
            }
            else {
                if (this.m_state != 0 || !s.toUpperCase().startsWith("EHLO")) {
                    throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
                }
                this.m_state = 1;
                this.toClient("503\r\n");
            }
        }
        else if (this.m_state == 1) {
            if (!s.toUpperCase().startsWith("HELO")) {
                throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
            }
            this.m_state = 2;
            this.toClient("250 OK\r\n");
        }
        else if (this.m_state == 2) {
            if (!s.toUpperCase().startsWith("MAIL FROM")) {
                throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
            }
            this.m_receiver.removeAllElements();
            this.m_text = "";
            this.m_state = 3;
            this.toClient("250 OK\r\n");
        }
        else if (this.m_state == 3) {
            if (s.toUpperCase().startsWith("RCPT TO")) {
                this.m_receiver.addElement(s.substring(s.indexOf(60) + 1, s.indexOf(62)));
                this.toClient("250 OK\r\n");
            }
            else {
                if (!s.toUpperCase().startsWith("DATA")) {
                    throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
                }
                this.m_state = 4;
                this.toClient("354 Start mail input; end with <CRLF>.<CRLF>\r\n");
            }
        }
        else if (this.m_state == 4) {
            this.m_text += s;
            if (this.m_text.endsWith("\r\n.\r\n")) {
                this.m_text = this.m_text.substring(0, this.m_text.length() - 5);
                final String[] array2 = new String[this.m_receiver.size()];
                this.m_receiver.copyInto(array2);
                final EMail eMail = new EMail(array2, this.m_text);
                boolean b = true;
                final String myEMail = Mixminion.getMyEMail();
                if (myEMail == "") {
                    this.toClient("554 Keine Reply-E-Mail im JAP spezifiziert!\r\n");
                    b = false;
                }
                if (b) {
                    final Message message = new Message(eMail, Mixminion.getRouteLen(), myEMail, new PasswordManager().getPassword(), 3);
                    final boolean send = message.send();
                    this.m_state = 5;
                    if (send) {
                        this.toClient("250 OK\r\n");
                    }
                    else {
                        String s2;
                        if (message.getDecoded() != null) {
                            s2 = "250 OK\r\n";
                        }
                        else {
                            s2 = "554 Fehler beim Versenden der eMail zum MixMinionServer!\r\n";
                        }
                        this.toClient(s2);
                    }
                }
            }
        }
        else {
            if (this.m_state != 5) {
                throw new RuntimeException("(State=" + this.m_state + ") This State is not possible");
            }
            if (s.toUpperCase().startsWith("QUIT")) {
                this.m_receiver.addElement(s);
                this.toClient("221 Bye\r\n");
                this.m_state = 99;
            }
            else if (s.toUpperCase().startsWith("MAIL FROM")) {
                this.m_receiver.removeAllElements();
                this.m_text = "";
                this.m_state = 3;
                this.toClient("250 OK\r\n");
            }
            else {
                if (!s.toUpperCase().startsWith("RSET")) {
                    throw new RuntimeException("(State=" + this.m_state + ") Didn't understand this Command '" + s + "'");
                }
                this.m_state = 2;
                this.toClient("250 OK\r\n");
            }
        }
    }
    
    public int getOutputBlockSize() {
        return 1000;
    }
}
