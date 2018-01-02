// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.converter;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.File;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.Obuffer;
import javazoom.jl.decoder.Bitstream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Decoder;

public class Converter
{
    public synchronized void convert(final String s, final String s2) throws JavaLayerException {
        this.convert(s, s2, null, null);
    }
    
    public synchronized void convert(final String s, final String s2, final ProgressListener progressListener) throws JavaLayerException {
        this.convert(s, s2, progressListener, null);
    }
    
    public void convert(final String s, String s2, final ProgressListener progressListener, final Decoder.Params params) throws JavaLayerException {
        if (s2.length() == 0) {
            s2 = null;
        }
        try {
            final InputStream openInput = this.openInput(s);
            this.convert(openInput, s2, progressListener, params);
            openInput.close();
        }
        catch (IOException ex) {
            throw new JavaLayerException(ex.getLocalizedMessage(), ex);
        }
    }
    
    public synchronized void convert(InputStream inputStream, final String s, ProgressListener stdOut, final Decoder.Params params) throws JavaLayerException {
        if (stdOut == null) {
            stdOut = PrintWriterProgressListener.newStdOut(0);
        }
        try {
            if (!(inputStream instanceof BufferedInputStream)) {
                inputStream = new BufferedInputStream(inputStream);
            }
            int countFrames = -1;
            if (inputStream.markSupported()) {
                inputStream.mark(-1);
                countFrames = this.countFrames(inputStream);
                inputStream.reset();
            }
            stdOut.converterUpdate(1, countFrames, 0);
            Obuffer outputBuffer = null;
            final Decoder decoder = new Decoder(params);
            final Bitstream bitstream = new Bitstream(inputStream);
            if (countFrames == -1) {
                countFrames = Integer.MAX_VALUE;
            }
            int i = 0;
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                while (i < countFrames) {
                    try {
                        final Header frame = bitstream.readFrame();
                        if (frame == null) {
                            break;
                        }
                        stdOut.readFrame(i, frame);
                        if (outputBuffer == null) {
                            outputBuffer = new WaveFileObuffer((frame.mode() == 3) ? 1 : 2, frame.frequency(), s);
                            decoder.setOutputBuffer(outputBuffer);
                        }
                        if (decoder.decodeFrame(frame, bitstream) != outputBuffer) {
                            throw new InternalError("Output buffers are different.");
                        }
                        stdOut.decodedFrame(i, frame, outputBuffer);
                        bitstream.closeFrame();
                    }
                    catch (Exception ex) {
                        if (!stdOut.converterException(ex)) {
                            throw new JavaLayerException(ex.getLocalizedMessage(), ex);
                        }
                    }
                    ++i;
                }
            }
            finally {
                if (outputBuffer != null) {
                    outputBuffer.close();
                }
            }
            stdOut.converterUpdate(2, (int)(System.currentTimeMillis() - currentTimeMillis), i);
        }
        catch (IOException ex2) {
            throw new JavaLayerException(ex2.getLocalizedMessage(), ex2);
        }
    }
    
    protected int countFrames(final InputStream inputStream) {
        return -1;
    }
    
    protected InputStream openInput(final String s) throws IOException {
        return new BufferedInputStream(new FileInputStream(new File(s)));
    }
    
    public static class PrintWriterProgressListener implements ProgressListener
    {
        public static final int NO_DETAIL = 0;
        public static final int EXPERT_DETAIL = 1;
        public static final int VERBOSE_DETAIL = 2;
        public static final int DEBUG_DETAIL = 7;
        public static final int MAX_DETAIL = 10;
        private PrintWriter pw;
        private int detailLevel;
        
        public static PrintWriterProgressListener newStdOut(final int n) {
            return new PrintWriterProgressListener(new PrintWriter(System.out, true), n);
        }
        
        public PrintWriterProgressListener(final PrintWriter pw, final int detailLevel) {
            this.pw = pw;
            this.detailLevel = detailLevel;
        }
        
        public boolean isDetail(final int n) {
            return this.detailLevel >= n;
        }
        
        public void converterUpdate(final int n, final int n2, int n3) {
            if (this.isDetail(2)) {
                switch (n) {
                    case 2: {
                        if (n3 == 0) {
                            n3 = 1;
                        }
                        this.pw.println();
                        this.pw.println("Converted " + n3 + " frames in " + n2 + " ms (" + n2 / n3 + " ms per frame.)");
                        break;
                    }
                }
            }
        }
        
        public void parsedFrame(final int n, final Header header) {
            if (n == 0 && this.isDetail(2)) {
                this.pw.println("File is a " + header.toString());
            }
            else if (this.isDetail(10)) {
                this.pw.println("Prased frame " + n + ": " + header.toString());
            }
        }
        
        public void readFrame(final int n, final Header header) {
            if (n == 0 && this.isDetail(2)) {
                this.pw.println("File is a " + header.toString());
            }
            else if (this.isDetail(10)) {
                this.pw.println("Read frame " + n + ": " + header.toString());
            }
        }
        
        public void decodedFrame(final int n, final Header header, final Obuffer obuffer) {
            if (this.isDetail(10)) {
                this.pw.println("Decoded frame " + n + ": " + header.toString());
                this.pw.println("Output: " + obuffer);
            }
            else if (this.isDetail(2)) {
                if (n == 0) {
                    this.pw.print("Converting.");
                    this.pw.flush();
                }
                if (n % 10 == 0) {
                    this.pw.print('.');
                    this.pw.flush();
                }
            }
        }
        
        public boolean converterException(final Throwable t) {
            if (this.detailLevel > 0) {
                t.printStackTrace(this.pw);
                this.pw.flush();
            }
            return false;
        }
    }
    
    public interface ProgressListener
    {
        public static final int UPDATE_FRAME_COUNT = 1;
        public static final int UPDATE_CONVERT_COMPLETE = 2;
        
        void converterUpdate(final int p0, final int p1, final int p2);
        
        void parsedFrame(final int p0, final Header p1);
        
        void readFrame(final int p0, final Header p1);
        
        void decodedFrame(final int p0, final Header p1, final Obuffer p2);
        
        boolean converterException(final Throwable p0);
    }
}
