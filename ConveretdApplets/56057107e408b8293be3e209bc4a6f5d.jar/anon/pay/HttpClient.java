// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import anon.pay.xml.XMLDescription;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import anon.util.XMLParseException;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;

public final class HttpClient
{
    private BufferedReader m_reader;
    private BufferedOutputStream m_OS;
    private Socket m_socket;
    
    public HttpClient(final Socket socket) throws IOException {
        this.m_socket = socket;
        this.m_reader = new BufferedReader(new InputStreamReader(this.m_socket.getInputStream()));
        this.m_OS = new BufferedOutputStream(this.m_socket.getOutputStream(), 4096);
    }
    
    public void close() throws IOException, XMLParseException {
        this.writeRequest("GET", "close", null);
        this.readAnswer();
        if (this.m_socket != null) {
            this.m_socket.close();
        }
    }
    
    public void writeRequest(final String s, final String s2, final String s3) throws IOException {
        this.m_OS.write((s + " /" + s2 + " HTTP/1.1\r\n").getBytes());
        if (s.equals("POST")) {
            this.m_OS.write(("Content-Length: " + s3.length() + "\r\n").getBytes());
            this.m_OS.write("\r\n".getBytes());
            this.m_OS.write(s3.getBytes());
        }
        else {
            this.m_OS.write("\r\n".getBytes());
        }
        this.m_OS.flush();
    }
    
    public Document readAnswer() throws IOException, XMLParseException {
        int int1 = -1;
        char[] array = null;
        final String line = this.m_reader.readLine();
        if (line == null) {
            throw new IOException("No answer received");
        }
        final int index;
        if ((index = line.indexOf(" ")) == -1) {
            throw new IOException("Wrong Header");
        }
        final String substring = line.substring(index + 1);
        final int index2;
        if ((index2 = substring.indexOf(" ")) == -1) {
            throw new IOException("Wrong Header");
        }
        final String substring2 = substring.substring(0, index2);
        final String substring3 = substring.substring(index2 + 1);
        for (String s = this.m_reader.readLine(); s != null && !s.equals(""); s = this.m_reader.readLine()) {
            final int index3;
            if ((index3 = s.indexOf(" ")) == -1) {
                throw new IOException("Wrong Header: " + s);
            }
            final String substring4 = s.substring(0, index3);
            final String trim = s.substring(index3 + 1).trim();
            if (substring4.equalsIgnoreCase("Content-length:")) {
                try {
                    int1 = Integer.parseInt(trim);
                }
                catch (NumberFormatException ex) {
                    throw new IOException("Error: received invalid value for header Content-length: " + trim);
                }
            }
        }
        if (int1 > 0) {
            array = new char[int1];
            int i = 0;
            do {
                final int read = this.m_reader.read(array, i, int1 - i);
                if (read == -1) {
                    break;
                }
                i += read;
            } while (i < int1);
        }
        if (substring2.equals("200")) {
            return XMLUtil.toXMLDocument(array);
        }
        if (substring2.equals("409")) {
            String description;
            try {
                description = new XMLDescription(array).getDescription();
            }
            catch (Exception ex2) {
                description = "Unkown Error";
            }
            throw new IOException(description);
        }
        throw new IOException(substring3);
    }
}
