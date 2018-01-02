// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import com.hw.client.util.a;
import java.io.File;
import java.net.URL;

public final class aZ
{
    private URL a;
    private File b;
    private do c;
    
    public aZ(final URL a, final File b, final do c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final boolean a() {
        if (!this.b.getParentFile().canWrite()) {
            com.hw.client.util.a.d("unable to write to archive directory");
            return false;
        }
        try {
            final HttpURLConnection httpURLConnection;
            (httpURLConnection = (HttpURLConnection)this.a.openConnection()).setRequestMethod("GET");
            httpURLConnection.connect();
            final int responseCode;
            if ((responseCode = httpURLConnection.getResponseCode()) < 200 || responseCode >= 300) {
                com.hw.client.util.a.d("unable to make http connection, code => " + responseCode + ", m_url => " + this.a);
                return false;
            }
            final int contentLength;
            if ((contentLength = httpURLConnection.getContentLength()) <= 0) {
                com.hw.client.util.a.d("invalid content-length, content_length => " + contentLength + ", m_url => " + this.a);
                return false;
            }
            com.hw.client.util.a.b("attempting to save url to file, content_length => " + contentLength + ", m_url => " + this.a + ", m_file => " + this.b);
            final byte[] array = new byte[2048];
            int n = 0;
            final InputStream inputStream = httpURLConnection.getInputStream();
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.b));
            int read;
            while (n < contentLength && (read = inputStream.read(array, 0, 2048)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
                if (this.c != null) {
                    this.c.a(n * 100 / contentLength);
                }
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            com.hw.client.util.a.b("finished saving url to file, total_bytes_read => " + n + ", content_length => " + contentLength + ", m_url => " + this.a + ", m_file => " + this.b);
        }
        catch (Exception ex) {
            com.hw.client.util.a.a("unable to download archive", ex);
            this.b.delete();
            return false;
        }
        return true;
    }
}
