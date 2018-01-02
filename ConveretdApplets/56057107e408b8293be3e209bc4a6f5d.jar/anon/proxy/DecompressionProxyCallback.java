// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import logging.LogHolder;
import logging.LogType;
import java.util.zip.DataFormatException;
import java.util.Hashtable;

public class DecompressionProxyCallback implements ProxyCallback
{
    Hashtable decompressionKits;
    private static final int FHCRC = 2;
    private static final int FEXTRA = 4;
    private static final int FNAME = 8;
    private static final int FCOMMENT = 16;
    public static final int MAX_DECOMPRESSION_OUTPUT = 10000;
    
    public DecompressionProxyCallback() {
        this.decompressionKits = new Hashtable();
    }
    
    private int readGZIPHeader(final byte[] array, final int n, final int n2) throws DataFormatException, HeaderSplitException {
        if (n2 < 10) {
            throw new HeaderSplitException();
        }
        try {
            int n3 = n;
            if (this.toUnsignedShort(array[n3++], array[n3++]) != 35615) {
                throw new DataFormatException("Not in GZIP format");
            }
            if (this.toUnsignedByte(array[n3++]) != 8) {
                throw new DataFormatException("Unsupported compression method");
            }
            final int unsignedByte = this.toUnsignedByte(array[n3++]);
            n3 += 6;
            if ((unsignedByte & 0x4) == 0x4) {
                n3 += this.toUnsignedShort(array[n3++], array[n3++]);
            }
            if ((unsignedByte & 0x8) == 0x8) {
                while (this.toUnsignedShort(array[n3++], array[n3++]) != 0) {}
            }
            if ((unsignedByte & 0x10) == 0x10) {
                while (this.toUnsignedShort(array[n3++], array[n3++]) != 0) {}
            }
            if ((unsignedByte & 0x2) == 0x2) {
                n3 += 2;
            }
            if (n3 > n + n2) {
                throw new HeaderSplitException();
            }
            return n3 - n;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new HeaderSplitException();
        }
    }
    
    public int toUnsignedShort(final byte b, final byte b2) {
        return this.toUnsignedByte(b2) << 8 | b;
    }
    
    public int toUnsignedByte(final byte b) {
        return ((b < 0) ? 128 : 0) + (0x7F & b);
    }
    
    public void closeRequest(final AnonProxyRequest anonProxyRequest) {
        final DecompressionKit decompressionKit = this.decompressionKits.remove(anonProxyRequest);
        if (decompressionKit != null) {
            if (decompressionKit.getGzipInflater() != null) {
                decompressionKit.getGzipInflater().end();
            }
            if (decompressionKit.getZLibInflater() != null) {
                decompressionKit.getZLibInflater().end();
            }
        }
    }
    
    public int decompress(final ProxyCallbackBuffer proxyCallbackBuffer, final DecompressionKit decompressionKit, final boolean b, final boolean b2) throws DataFormatException, ArrayIndexOutOfBoundsException {
        boolean b3 = b2;
        int n = 0;
        final Inflater inflater = b ? decompressionKit.getGzipInflater() : decompressionKit.getZLibInflater();
        if (decompressionKit.headerBytesNotComplete()) {
            proxyCallbackBuffer.injectModificationData(decompressionKit.completeUnfinishedHeaderBytes());
            b3 = true;
        }
        if (b3) {
            try {
                n = (b ? this.readGZIPHeader(proxyCallbackBuffer.getChunk(), proxyCallbackBuffer.getModificationStartOffset(), proxyCallbackBuffer.getModificationDataLength()) : 0);
            }
            catch (HeaderSplitException ex) {
                decompressionKit.addHeaderBytes(proxyCallbackBuffer.extractModificationData());
                LogHolder.log(7, LogType.NET, "gzip splitted up between two chunks.");
                return 0;
            }
        }
        final Inflater inflater2 = b ? decompressionKit.getGzipInflater() : decompressionKit.getZLibInflater();
        if (inflater2.needsInput()) {
            final int leadingDataLength = proxyCallbackBuffer.getLeadingDataLength();
            final int trailingDataLength = proxyCallbackBuffer.getTrailingDataLength();
            int length = decompressionKit.getResult().length - trailingDataLength - leadingDataLength;
            inflater2.setInput(proxyCallbackBuffer.getChunk(), proxyCallbackBuffer.getModificationStartOffset() + n, proxyCallbackBuffer.getModificationDataLength() - n);
            ByteArrayOutputStream byteArrayOutputStream = null;
            int n2 = inflater2.inflate(decompressionKit.getResult(), leadingDataLength, length);
            while (n2 == length && !inflater2.needsInput()) {
                if (byteArrayOutputStream == null) {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    proxyCallbackBuffer.copyLeadingData(byteArrayOutputStream);
                    byteArrayOutputStream.write(decompressionKit.getResult(), leadingDataLength, length);
                }
                length = decompressionKit.getResult().length;
                n2 = inflater2.inflate(decompressionKit.getResult());
                byteArrayOutputStream.write(decompressionKit.getResult(), 0, n2);
            }
            if (byteArrayOutputStream == null) {
                proxyCallbackBuffer.copyLeadingData(decompressionKit.getResult());
                proxyCallbackBuffer.copyTrailingData(decompressionKit.getResult(), leadingDataLength + n2);
                proxyCallbackBuffer.setChunk(decompressionKit.getResult());
                proxyCallbackBuffer.setModificationStartOffset(leadingDataLength + n2);
                proxyCallbackBuffer.setModificationEndOffset(proxyCallbackBuffer.getModificationStartOffset());
                proxyCallbackBuffer.setPayloadLength(proxyCallbackBuffer.getModificationStartOffset() + trailingDataLength);
            }
            else {
                proxyCallbackBuffer.copyTrailingData(byteArrayOutputStream);
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                proxyCallbackBuffer.setChunk(byteArray);
                proxyCallbackBuffer.setModificationStartOffset(byteArray.length - trailingDataLength);
                proxyCallbackBuffer.setModificationEndOffset(proxyCallbackBuffer.getModificationStartOffset());
            }
            if (inflater2.finished()) {
                LogHolder.log(6, LogType.NET, "finish connection after decompressing.");
                inflater2.reset();
                return 2;
            }
        }
        return 2;
    }
    
    public synchronized int handleDownstreamChunk(final AnonProxyRequest anonProxyRequest, final ProxyCallbackBuffer proxyCallbackBuffer) throws ProxyCallbackNotProcessableException {
        if (proxyCallbackBuffer.getModificationStartOffset() < proxyCallbackBuffer.getPayloadLength()) {
            final String[] contentEncodings = anonProxyRequest.getContentEncodings();
            if (contentEncodings != null) {
                final Vector vector = new Vector<String>();
                for (int i = 0; i < contentEncodings.length; ++i) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(contentEncodings[i], "");
                    while (stringTokenizer.hasMoreTokens()) {
                        final String nextToken = stringTokenizer.nextToken();
                        if (nextToken.trim().equals("gzip") || nextToken.trim().equals("deflate")) {
                            vector.addElement(nextToken);
                        }
                        else {
                            LogHolder.log(4, LogType.NET, "The Content-Encoding " + nextToken + " is not supported.");
                        }
                    }
                }
                if (vector.size() > 0) {
                    DecompressionKit decompressionKit = null;
                    boolean b = false;
                    boolean equals = true;
                    try {
                        for (int j = 0; j < vector.size(); ++j) {
                            equals = vector.elementAt(j).equals("gzip");
                            decompressionKit = this.decompressionKits.get(anonProxyRequest);
                            if (decompressionKit == null) {
                                decompressionKit = new DecompressionKit();
                                decompressionKit.setNewInflater(equals);
                                decompressionKit.setResult(new byte[10000]);
                                this.decompressionKits.put(anonProxyRequest, decompressionKit);
                                b = true;
                            }
                            if (this.decompress(proxyCallbackBuffer, decompressionKit, equals, b) == 0) {
                                return 0;
                            }
                        }
                    }
                    catch (DataFormatException ex) {
                        if (decompressionKit != null) {
                            final Inflater inflater = decompressionKit.getInflater(equals);
                            if (inflater != null) {
                                inflater.reset();
                            }
                        }
                        LogHolder.log(4, LogType.NET, "compressed data has invalid format.", ex);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        if (decompressionKit != null) {
                            final Inflater inflater2 = decompressionKit.getInflater(equals);
                            if (inflater2 != null) {
                                inflater2.reset();
                            }
                        }
                        LogHolder.log(3, LogType.NET, "indexing error occured while decompressing data. Maybe the result buffer is too small?", ex2);
                    }
                }
            }
        }
        return 2;
    }
    
    public int handleUpstreamChunk(final AnonProxyRequest anonProxyRequest, final ProxyCallbackBuffer proxyCallbackBuffer) throws ProxyCallbackNotProcessableException {
        return 2;
    }
    
    public static class DecompressionKit
    {
        private Inflater gzipInflater;
        private Inflater zLibInflater;
        private ByteArrayOutputStream unfinishedGzipHeader;
        private byte[] result;
        
        public DecompressionKit() {
            this.gzipInflater = null;
            this.zLibInflater = null;
            this.result = null;
        }
        
        public byte[] getResult() {
            return this.result;
        }
        
        public void setResult(final byte[] result) {
            this.result = result;
        }
        
        public Inflater getGzipInflater() {
            return this.gzipInflater;
        }
        
        public void setGzipInflater(final Inflater gzipInflater) {
            this.gzipInflater = gzipInflater;
        }
        
        public Inflater getZLibInflater() {
            return this.zLibInflater;
        }
        
        public void setZLibInflater(final Inflater zLibInflater) {
            this.zLibInflater = zLibInflater;
        }
        
        public Inflater getInflater(final boolean b) {
            return b ? this.gzipInflater : this.zLibInflater;
        }
        
        public void setNewInflater(final boolean b) {
            if (b) {
                this.setGzipInflater(new Inflater(true));
            }
            else {
                this.setZLibInflater(new Inflater());
            }
        }
        
        private void addHeaderBytes(final byte[] array) {
            this.addHeaderBytes(array, 0, array.length);
        }
        
        private void addHeaderBytes(final byte[] array, final int n, final int n2) {
            if (this.unfinishedGzipHeader == null) {
                this.unfinishedGzipHeader = new ByteArrayOutputStream();
            }
            this.unfinishedGzipHeader.write(array, n, n2);
        }
        
        private byte[] completeUnfinishedHeaderBytes() {
            final byte[] byteArray = this.unfinishedGzipHeader.toByteArray();
            this.unfinishedGzipHeader = null;
            return byteArray;
        }
        
        public boolean headerBytesNotComplete() {
            return this.unfinishedGzipHeader != null;
        }
    }
    
    private class HeaderSplitException extends Exception
    {
    }
}
