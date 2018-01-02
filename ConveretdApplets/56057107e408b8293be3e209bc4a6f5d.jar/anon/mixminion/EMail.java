// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion;

import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.io.IOException;
import anon.mixminion.message.ReplyBlock;
import java.util.Vector;

public class EMail
{
    private String[] m_receiver;
    private String m_payload;
    private Vector m_replyblocks;
    private String m_type;
    private String m_multipartid;
    
    public EMail(final String[] receiver, String removeRepyBlocks) {
        this.m_receiver = new String[1];
        this.m_payload = null;
        this.m_replyblocks = new Vector();
        this.m_type = "";
        this.m_multipartid = "";
        if (this.testonEncrypted(removeRepyBlocks)) {
            this.m_type = "ENC";
            this.m_payload = removeRepyBlocks;
        }
        else {
            try {
                this.m_replyblocks = ReplyBlock.parseReplyBlocks(removeRepyBlocks, null);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            if (this.m_replyblocks.size() > 0) {
                this.m_type = "RPL";
                this.m_receiver[0] = "anonymous@fragmented.de";
                try {
                    removeRepyBlocks = ReplyBlock.removeRepyBlocks(removeRepyBlocks);
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
            else {
                this.m_type = "NOR";
                this.m_receiver = receiver;
            }
            try {
                this.m_payload = this.trimPayload(removeRepyBlocks);
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    private boolean testonEncrypted(final String s) {
        final LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(s));
        while (true) {
            String line;
            try {
                line = lineNumberReader.readLine();
            }
            catch (IOException ex) {
                break;
            }
            if (line == null) {
                break;
            }
            if (line.startsWith("Message-type: encrypted")) {
                return true;
            }
        }
        return false;
    }
    
    public String[] getReceiver() {
        return this.m_receiver;
    }
    
    public String getPayload() {
        return this.m_payload;
    }
    
    public void addRBtoPayload(final String s) {
        if (this.m_multipartid.equals("")) {
            this.m_payload += s;
        }
        else {
            this.m_payload = this.m_payload.substring(0, this.m_payload.indexOf("--" + this.m_multipartid + "--")) + "\n--" + this.m_multipartid + "\nContent-Type: text/plain; charset=ISO-8859-15\n" + "Content-Transfer-Encoding: 7bit\n" + s + "\n--" + this.m_multipartid + "--";
        }
    }
    
    public String getType() {
        return this.m_type;
    }
    
    public Vector getReplyBlocks() {
        return this.m_replyblocks;
    }
    
    public String toString() {
        String string = "";
        for (int i = 0; i < this.m_receiver.length; ++i) {
            string = string + "[" + this.m_receiver[i] + "]\n";
        }
        return string + this.m_payload;
    }
    
    private String trimPayload(final String s) throws IOException {
        final String s2 = "";
        String s3 = "";
        String s4 = "";
        if (this.m_type.equals("NOR")) {
            s3 += "\nMessage created with JAP/Mixminion Anonymous Mailing\n\n";
            s4 = "- ";
        }
        LineNumberReader lineNumberReader;
        String s5;
        for (lineNumberReader = new LineNumberReader(new StringReader(s)), s5 = lineNumberReader.readLine(); !s5.startsWith("Subject"); s5 = lineNumberReader.readLine()) {}
        String string;
        for (string = s2 + "Titel: " + s5.substring(9) + "\n"; s5.length() > 0; s5 = lineNumberReader.readLine(), s3 = s3 + "MIME-Version: 1.0\n" + string + "Content-Type: multipart/mixed;\n" + s5.substring(0, 11) + s4 + s5.substring(11) + "\n", this.m_multipartid = s5.substring(11, s5.length() - 1)) {
            s5 = lineNumberReader.readLine();
            if (s5.startsWith("Content-Type: multipart/mixed;")) {}
        }
        if (this.m_multipartid.equals("")) {
            s3 += string;
        }
        while (s5 != null) {
            s3 = s3 + s5 + "\n";
            s5 = lineNumberReader.readLine();
        }
        return s3;
    }
}
