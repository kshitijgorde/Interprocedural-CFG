// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jlgui.basicplayer;

import org.apache.commons.logging.LogFactory;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import org.apache.commons.logging.Log;

public class BasicPlayerApplet extends BasicPlayer
{
    private static Log log;
    
    protected void initAudioInputStream(final File file) throws UnsupportedAudioFileException, IOException {
        this.m_audioInputStream = AppletMpegSPIWorkaround.getAudioInputStream(file);
        this.m_audioFileFormat = AppletMpegSPIWorkaround.getAudioFileFormat(file);
    }
    
    protected void initAudioInputStream(final URL url) throws UnsupportedAudioFileException, IOException {
        this.m_audioInputStream = AppletMpegSPIWorkaround.getAudioInputStream(url);
        this.m_audioFileFormat = AppletMpegSPIWorkaround.getAudioFileFormat(url);
    }
    
    protected void initAudioInputStream(final InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        try {
            this.m_audioFileFormat = AppletMpegSPIWorkaround.getAudioFileFormat(inputStream);
            this.m_audioInputStream = AppletMpegSPIWorkaround.getAudioInputStream(inputStream);
        }
        catch (IOException ex) {
            throw ex;
        }
    }
    
    protected void createLine() throws LineUnavailableException {
        BasicPlayerApplet.log.info((Object)"Create Line");
        if (this.m_line == null) {
            final AudioFormat sourceFormat = this.m_audioInputStream.getFormat();
            BasicPlayerApplet.log.info((Object)("Create Line : Source format : " + sourceFormat.toString()));
            final AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16, sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false);
            BasicPlayerApplet.log.info((Object)("Create Line : Target format: " + targetFormat));
            this.m_encodedaudioInputStream = this.m_audioInputStream;
            try {
                this.encodedLength = this.m_encodedaudioInputStream.available();
            }
            catch (IOException e) {
                BasicPlayerApplet.log.error((Object)"Cannot get m_encodedaudioInputStream.available()", (Throwable)e);
            }
            this.m_audioInputStream = AppletMpegSPIWorkaround.getAudioInputStream(targetFormat, this.m_audioInputStream);
            final AudioFormat audioFormat = this.m_audioInputStream.getFormat();
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat, -1);
            this.m_line = (SourceDataLine)AudioSystem.getLine(info);
            BasicPlayerApplet.log.debug((Object)("Line AudioFormat: " + this.m_line.getFormat().toString()));
            final Control[] c = this.m_line.getControls();
            for (int p = 0; p < c.length; ++p) {
                BasicPlayerApplet.log.debug((Object)("Controls : " + c[p].toString()));
            }
            if (this.m_line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                this.m_gainControl = (FloatControl)this.m_line.getControl(FloatControl.Type.MASTER_GAIN);
                BasicPlayerApplet.log.info((Object)("Master Gain Control : [" + this.m_gainControl.getMinimum() + "," + this.m_gainControl.getMaximum() + "] " + this.m_gainControl.getPrecision()));
            }
            if (this.m_line.isControlSupported(FloatControl.Type.PAN)) {
                this.m_panControl = (FloatControl)this.m_line.getControl(FloatControl.Type.PAN);
                BasicPlayerApplet.log.info((Object)("Pan Control : [" + this.m_panControl.getMinimum() + "," + this.m_panControl.getMaximum() + "] " + this.m_panControl.getPrecision()));
            }
        }
    }
    
    static {
        BasicPlayerApplet.log = LogFactory.getLog((Class)BasicPlayerApplet.class);
    }
}
