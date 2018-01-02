// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Track;
import javax.sound.midi.Sequence;
import java.util.ArrayList;
import javax.sound.midi.MidiSystem;
import java.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileWriter;
import javax.sound.midi.Sequencer;

public class MIDITools
{
    private static MIDITools _instance;
    private boolean finished;
    private Sequencer sequencer;
    
    public static MIDITools instance() {
        if (MIDITools._instance == null) {
            (MIDITools._instance = new MIDITools()).setFinished(true);
        }
        return MIDITools._instance;
    }
    
    public String createMIDI(String head, String key, final String fileName, final String title, final int x, final String uniqueId) throws IOException, InterruptedException {
        head = head.trim() + "\r";
        key = key.trim();
        final String folder = System.getProperty("user.dir") + System.getProperty("file.separator") + MattProperties.getString("MIDIIndex");
        final String tempFile = folder + System.getProperty("file.separator") + "temp.abc";
        final FileWriter fw = new FileWriter(tempFile);
        fw.write(head);
        fw.write("Q:1/4 = 200\n");
        fw.write(key);
        fw.flush();
        fw.close();
        int variation = 0;
        boolean unique = false;
        String midiFileName;
        String fullName;
        do {
            midiFileName = uniqueId;
            if (variation > 0) {
                midiFileName = midiFileName + "-Variation " + variation;
            }
            midiFileName += ".mid";
            fullName = folder + System.getProperty("file.separator") + midiFileName;
            if (new File(fullName).exists()) {
                ++variation;
            }
            else {
                unique = true;
            }
        } while (!unique);
        final String cmd = MattProperties.getString("ABC2MIDI") + " \"" + tempFile + "\" -o " + "\"" + fullName + "\"";
        final Process abc2MIDI = Runtime.getRuntime().exec(cmd);
        final InputStream in = abc2MIDI.getInputStream();
        boolean finished = false;
        while (!finished) {
            try {
                while (in.available() > 0) {
                    final Character c = new Character((char)in.read());
                    System.out.print(c);
                }
                abc2MIDI.exitValue();
                finished = true;
            }
            catch (IllegalThreadStateException e) {
                Thread.currentThread();
                Thread.sleep(10L);
            }
        }
        abc2MIDI.waitFor();
        if (!new File(fullName).exists()) {
            Logger.log(fullName + " not created");
        }
        return midiFileName;
    }
    
    public int[] toMIDISequence(final TranscribedNote[] notes) {
        final Vector<Integer> v = new Vector<Integer>();
        for (int i = 0; i < notes.length; ++i) {
            v.add(notes[i].getMidiNote());
        }
        final int[] ret = new int[notes.length];
        for (int j = 0; j < notes.length; ++j) {
            ret[j] = v.get(j);
        }
        return ret;
    }
    
    public int[] toMIDISequence(final String file) throws InvalidMidiDataException, IOException {
        final String curDir = System.getProperty("user.dir");
        final String fileName = curDir + System.getProperty("file.separator") + MattProperties.getString("MIDIIndex") + System.getProperty("file.separator") + file;
        final Sequence sequence = MidiSystem.getSequence(new File(fileName));
        final Track[] tracks = sequence.getTracks();
        final ArrayList<Integer> midiSequence = new ArrayList<Integer>();
        for (int iTrack = 0; iTrack < tracks.length && midiSequence.size() == 0; ++iTrack) {
            for (int i = 0; i < tracks[iTrack].size(); ++i) {
                final MidiMessage mm = tracks[iTrack].get(i).getMessage();
                final int len = mm.getLength();
                final int status = mm.getStatus();
                if (status == 144) {
                    final byte[] b = mm.getMessage();
                    final int currentNote = b[1];
                    midiSequence.add(new Integer(currentNote));
                }
            }
        }
        final int[] ret = new int[midiSequence.size()];
        for (int j = 0; j < midiSequence.size(); ++j) {
            ret[j] = midiSequence.get(j);
        }
        return ret;
    }
    
    public String arrayToString(final int[] midiSequence) {
        final StringBuffer ret = new StringBuffer();
        for (int i = 0; i < midiSequence.length; ++i) {
            ret.append("" + midiSequence[i]);
            if (i < midiSequence.length - 1) {
                ret.append(",");
            }
        }
        return ret.toString();
    }
    
    public String toParsons(final int[] midiSequence) throws InvalidMidiDataException, IOException {
        int previousNote = -1;
        final StringBuffer parsons = new StringBuffer();
        for (int i = 0; i < midiSequence.length; ++i) {
            final int currentNote = midiSequence[i];
            if (previousNote != -1) {
                if (currentNote > previousNote) {
                    parsons.append("U");
                }
                else if (currentNote < previousNote) {
                    parsons.append("D");
                }
                else {
                    parsons.append("S");
                }
            }
            previousNote = currentNote;
        }
        return parsons.toString();
    }
    
    public void playMidiFile(final String file) {
        final String curDir = System.getProperty("user.dir");
        final String fileName = curDir + System.getProperty("file.separator") + MattProperties.getString("MIDIIndex") + System.getProperty("file.separator") + file;
        final File midiFile = new File(fileName);
        if (!midiFile.exists() || midiFile.isDirectory() || !midiFile.canRead()) {
            Logger.log("Could not play midi file: " + file);
            return;
        }
        try {
            (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(midiFile));
            this.sequencer.open();
            this.sequencer.start();
            this.finished = false;
            new Thread() {
                public void run() {
                    while (!MIDITools.this.isFinished() && MIDITools.this.sequencer.isRunning()) {
                        try {
                            Thread.sleep(1000L);
                            continue;
                        }
                        catch (InterruptedException ignore) {}
                        break;
                    }
                    MIDITools.this.sequencer.stop();
                    MIDITools.this.sequencer.close();
                    MIDITools.this.finished = true;
                }
            }.start();
        }
        catch (MidiUnavailableException mue) {
            System.out.println("Midi device unavailable!");
        }
        catch (InvalidMidiDataException imde) {
            System.out.println("Invalid Midi data!");
        }
        catch (IOException ioe) {
            System.out.println("I/O Error!");
        }
    }
    
    public boolean isFinished() {
        return this.finished;
    }
    
    public void setFinished(final boolean finished) {
        this.finished = finished;
    }
}
