// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Vector;
import java.io.IOException;
import java.net.URL;

public class FileLoader
{
    private URL ivBaseURL;
    private ProgressIndicator ivProgressIndicator;
    
    public FileLoader(final URL pBaseURL) {
        this(pBaseURL, null);
    }
    
    public FileLoader(final URL pBaseURL, final ProgressIndicator pProgressIndicator) {
        this.ivBaseURL = pBaseURL;
        this.ivProgressIndicator = pProgressIndicator;
    }
    
    public byte[] readFile(final String pFileName, final int pLength) throws IOException {
        if (pFileName.trim().length() == 0) {
            throw new IOException("Invalid filename");
        }
        if (this.ivProgressIndicator != null) {
            this.ivProgressIndicator.setProgressText(this.ivBaseURL + pFileName.substring(pFileName.indexOf(42) + 1));
            this.ivProgressIndicator.setProgress(0.0f);
            this.ivProgressIndicator.setVisible(true);
            ((ProgressIndicatorImpl)this.ivProgressIndicator).getParent().validate();
        }
        try {
            final URLConnection fileURLConnection = new URL(this.ivBaseURL, pFileName).openConnection();
            fileURLConnection.setUseCaches(true);
            final int fileLength = fileURLConnection.getContentLength();
            final InputStream is = fileURLConnection.getInputStream();
            final Vector data = new Vector();
            int total = 0;
            boolean eof = false;
            while (!eof) {
                int offset = 0;
                int remains = 2048;
                byte[] buffer = new byte[2048];
                while (!eof && remains > 0) {
                    final int read = is.read(buffer, offset, remains);
                    if (read >= 0) {
                        offset += read;
                        remains -= read;
                        total += read;
                    }
                    else {
                        eof = true;
                        buffer = Array.subArray(buffer, 0, offset);
                    }
                    if (this.ivProgressIndicator != null) {
                        this.ivProgressIndicator.setProgress(total, fileLength);
                    }
                }
                data.addElement(buffer);
            }
            is.close();
            if (fileLength > 0 && total != fileLength) {
                throw new IOException("Error reading file " + pFileName + ". File is " + fileLength + " bytes long, but " + total + " bytes were be read.");
            }
            byte[] result = new byte[total];
            int offset = 0;
            for (int i = 0; i < data.size(); ++i) {
                final byte[] buffer = data.elementAt(i);
                System.arraycopy(buffer, 0, result, offset, buffer.length);
                offset += buffer.length;
            }
            result = this.unzipIfZipped(result);
            if (pLength > 0 && result.length != pLength) {
                throw new IOException("Error reading file " + pFileName + ". " + pLength + " bytes were requested, but " + result.length + " bytes were read.");
            }
            return result;
        }
        finally {
            if (this.ivProgressIndicator != null) {
                this.ivProgressIndicator.setVisible(false);
                ((ProgressIndicatorImpl)this.ivProgressIndicator).getParent().validate();
            }
        }
    }
    
    private byte[] unzipIfZipped(final byte[] pBytes) throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(pBytes);
        final ZipInputStream zis = new ZipInputStream(bis);
        try {
            if (zis.getNextEntry() == null) {
                return pBytes;
            }
            final ByteArrayOutputStream bos = new ByteArrayOutputStream(pBytes.length);
            final byte[] buffer = new byte[2048];
            int length = 0;
            while (length >= 0) {
                length = zis.read(buffer);
                if (length > 0) {
                    bos.write(buffer, 0, length);
                }
            }
            bos.close();
            return bos.toByteArray();
        }
        catch (Exception e) {
            System.out.println("unzip error");
            return pBytes;
        }
    }
}
