import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.sound.midi.MidiSystem;
import java.awt.Color;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.Scrollbar;
import java.awt.Choice;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.Instrument;
import javax.sound.midi.Synthesizer;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MidiGuitar extends Panel implements ControlContext, Runnable
{
    Synthesizer synthesizer;
    Instrument[] instruments;
    MidiDevice.Info[] midi_device_info;
    MidiChannel[] midichannels;
    private int channel;
    Thread midiThread;
    MidiGuitar midi_guitar;
    ImageCanvas imgCanvas;
    GuitarCodex guitCodex;
    int[] nMidiNotes;
    int nOctave;
    private Panel midiConsolePanel;
    private Choice comboInstr;
    private Scrollbar sliderArpeggio;
    private Checkbox cbxSoundString;
    private Checkbox cbxLoopChord;
    private Checkbox cbxSoundPressedBtn;
    private Checkbox cbxUpAndDown;
    private Label arpeggioLabel;
    private int nInstrumentNo;
    private int nArpegVal;
    private int nVeloVal;
    private int nChordDuration;
    private long lArpeggioTime;
    private double dArpegValue;
    private boolean bRunLoop;
    static boolean bStandAlone;
    private boolean bSoundbank;
    private boolean bSoundcard;
    private Color bgColor;
    private final String[] strInstruments;
    
    public MidiGuitar(final GuitarCodex guitCodex, final Color bgColor) {
        this.channel = 0;
        this.midiThread = null;
        this.nOctave = 0;
        this.nVeloVal = 110;
        this.nChordDuration = 1000;
        this.bSoundcard = true;
        this.strInstruments = new String[] { "000 - Grand Piano", "001 - Bright Piano", "002 - Electric Piano", "003 - Honky Tonk Piano", "004 - Rhodes Piano", "005 - Chorus Piano", "006 - Harpsichord", "007 - Clavichord", "008 - Celesta", "009 - Glockenspiel", "010 - Music Box", "011 - Vibraphone", "012 - Marimba", "013 - Xylophone", "014 - Tubular Bells", "015 - Dulcimer", "016 - Hammond Organ", "017 - Percussive Organ", "018 - Rock Organ", "019 - Church Organ", "020 - Reed Organ", "021 - Accordion", "022 - Harmonica", "023 - Tango Accordion", "024 - Nylon Guitar", "025 - Steel Guitar", "026 - Jazz Guitar", "027 - Electric Guitar", "028 - Muted Electric Guitar", "029 - Overdriven Guitar", "030 - Distortion Guitar", "031 - Guitar Harmonics", "032 - Accoustic Bass", "033 - Finger Bass", "034 - Picked Bass", "035 - Fretless Bass", "036 - Slap Bass 1", "037 - Slap Bass 2", "038 - Synth Bass 1", "039 - Synth Bass 2", "040 - Violin", "041 - Viola", "042 - Cello", "043 - Contrabass", "044 - Tremelo Strings", "045 - Pizzicato Strings", "046 - Harp", "047 - Timpani", "048 - Strings 1", "049 - Strings 2", "050 - Synthstrings 1", "051 - Synthstrings 2", "052 - Choir Aahs", "053 - Voice Oohs", "054 - Synth Voice", "055 - Orchestra Hit", "056 - Trumpet", "057 - Trombone", "058 - Tuba", "059 - Muted Trumpet", "060 - French Horn", "061 - Brass Section", "062 - Synthbrass 1", "063 - Synthbrass 2", "064 - Soprano Sax", "065 - Alto Sax", "066 - Tenor Sax", "067 - Baritone Sax", "068 - Oboe", "069 - English Horn", "070 - Bassoon", "071 - Clarinet", "072 - Piccolo", "073 - Flute", "074 - Recorder", "075 - Pan Flute", "076 - Bottle Blow", "077 - Shakuhachi", "078 - Whistle", "079 - Ocarina", "080 - Square", "081 - Sawtooth", "082 - Calliope", "083 - Chiff", "084 - Charang", "085 - Air Chorus", "086 - Fifths", "087 - Bass Lead", "088 - New Age", "089 - Warm", "090 - Polysynth", "091 - Ghost", "092 - Bowed", "093 - Metallic", "094 - Halo", "095 - Sweep", "096 - Rain", "097 - Soundtrack", "098 - Crystal", "099 - Atmosphere", "100 - Brightness", "101 - Goblins", "102 - Echoes", "103 - Scifi", "104 - Sitar", "105 - Banjo", "106 - Shamisen", "107 - Koto", "108 - Kalimba", "109 - Bag Pipe", "110 - Fiddle", "111 - Shanai", "112 - Tinkle Bell", "113 - Agogo", "114 - Steel Drums", "115 - Woodblock", "116 - Taiko Drum", "117 - Melodic Tom", "118 - Synth Drum", "119 - Reverse Cymbal", "120 - Guitar Fret", "121 - Breath Noise", "122 - Seashore", "123 - Bird Tweet", "124 - Telephone", "125 - Helicopter", "126 - Applause", "127 - Gunshot" };
        this.guitCodex = guitCodex;
        this.bgColor = bgColor;
        this.imgCanvas = guitCodex.getImageCanvas();
        (this.midi_guitar = this).setMidiConsole();
        this.open();
    }
    
    public void open() {
        this.nInstrumentNo = 25;
        try {
            if (this.synthesizer == null && (this.synthesizer = MidiSystem.getSynthesizer()) == null) {
                return;
            }
            this.synthesizer.open();
            this.midichannels = this.synthesizer.getChannels();
        }
        catch (Exception ex) {
            this.bSoundcard = false;
            return;
        }
        if (this.synthesizer.getDefaultSoundbank() != null) {
            this.instruments = this.synthesizer.getDefaultSoundbank().getInstruments();
            this.synthesizer.loadInstrument(this.instruments[this.nInstrumentNo]);
            this.bSoundbank = true;
            if (this.synthesizer.getDefaultSoundbank().getInstruments().length > 128) {
                for (int i = 0; i < 128; ++i) {
                    this.comboInstr.addItem(i + 1 + " " + this.instruments[i].getName().trim());
                }
            }
        }
        else {
            for (int j = 0; j < this.strInstruments.length; ++j) {
                this.comboInstr.addItem(this.strInstruments[j]);
            }
            this.bSoundbank = false;
        }
        this.comboInstr.select(this.nInstrumentNo);
        this.midichannels[this.channel].programChange(this.nInstrumentNo);
    }
    
    public void setMidiConsole() {
        (this.midiConsolePanel = new Panel()).setLayout(null);
        this.midiConsolePanel.setSize(520, 75);
        this.midiConsolePanel.setBackground(this.bgColor);
        final Panel panel = new Panel(new GridLayout(2, 0));
        final Panel panel2 = new Panel(new GridLayout(2, 2));
        final Panel panel3 = new Panel(new GridLayout(0, 2));
        (this.comboInstr = new Choice()).setBounds(5, 5, 140, 20);
        this.comboInstr.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MidiGuitar.this.changeInstrument(MidiGuitar.this.comboInstr.getSelectedIndex());
            }
        });
        this.midiConsolePanel.add(this.comboInstr);
        final Button button = new Button("Play");
        button.setBounds(5, 30, 65, 20);
        this.midiConsolePanel.add(button);
        button.addMouseListener(new MouseListener() {
            public void mousePressed(final MouseEvent mouseEvent) {
                if (MidiGuitar.this.midiThread != null) {
                    MidiGuitar.this.stop();
                }
                (MidiGuitar.this.midiThread = new Thread(MidiGuitar.this.midi_guitar)).start();
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (MidiGuitar.this.cbxSoundPressedBtn.getState()) {
                    MidiGuitar.this.stop();
                }
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
            }
        });
        final Button button2 = new Button("Stop");
        button2.setBounds(80, 30, 65, 20);
        this.midiConsolePanel.add(button2);
        button2.addMouseListener(new MouseListener() {
            public void mousePressed(final MouseEvent mouseEvent) {
                MidiGuitar.this.stop();
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
            }
        });
        panel.add(panel3);
        (this.cbxSoundString = new Checkbox("Sound strings if fingered", false)).setBounds(170, 5, 160, 20);
        this.midiConsolePanel.add(this.cbxSoundString);
        (this.cbxLoopChord = new Checkbox("Loop chord", false)).setBounds(350, 5, 140, 20);
        this.midiConsolePanel.add(this.cbxLoopChord);
        (this.cbxUpAndDown = new Checkbox("Roll chord up & down", true)).setBounds(170, 30, 160, 20);
        this.midiConsolePanel.add(this.cbxUpAndDown);
        (this.cbxSoundPressedBtn = new Checkbox("While Play is pressed", false)).setBounds(350, 30, 140, 20);
        this.midiConsolePanel.add(this.cbxSoundPressedBtn);
        (this.arpeggioLabel = new Label("Arpeggio = 0 ms")).setBounds(10, 55, 150, 20);
        this.midiConsolePanel.add(this.arpeggioLabel);
        final Scrollbar scrollbar = new Scrollbar(0, 0, 1, 0, 310);
        scrollbar.setBounds(170, 57, 330, 15);
        this.nArpegVal = scrollbar.getValue();
        scrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                MidiGuitar.this.dArpegValue = scrollbar.getValue();
                MidiGuitar.this.lArpeggioTime = (long)MidiGuitar.this.dArpegValue * 10L;
                if (MidiGuitar.this.lArpeggioTime < 1000L) {
                    MidiGuitar.this.arpeggioLabel.setText("Arpeggio = " + MidiGuitar.this.lArpeggioTime + " ms");
                }
                else {
                    MidiGuitar.this.arpeggioLabel.setText("Arpeggio = " + MidiGuitar.this.lArpeggioTime / 1000.0 + " sec");
                }
            }
        });
        this.midiConsolePanel.add(scrollbar);
    }
    
    public Panel getMidiConsole() {
        return this.midiConsolePanel;
    }
    
    public Checkbox getSoundStrCbx() {
        return this.cbxSoundString;
    }
    
    public void playSingleNote(final int n) {
        if (n != -1) {
            if (this.midichannels[this.channel] != null) {
                this.midichannels[this.channel].noteOn(n + this.nOctave, this.nVeloVal);
            }
            try {
                Thread.sleep(this.nChordDuration);
            }
            catch (InterruptedException ex) {}
            if (this.midichannels[this.channel] != null) {
                this.midichannels[this.channel].noteOff(n + this.nOctave);
            }
        }
    }
    
    public void run() {
        if (!this.bSoundcard) {
            return;
        }
        this.nMidiNotes = this.imgCanvas.getMidiChordValues();
        if (this.nMidiNotes != null) {
            boolean b = false;
            for (int i = 0; i < this.nMidiNotes.length; ++i) {
                if (this.nMidiNotes[i] != -1) {
                    b = true;
                }
            }
            if (!b) {
                this.stop();
                final AlertFrame alertFrame = new AlertFrame("Please select a chord or set a note.");
                return;
            }
            try {
                if (this.cbxLoopChord.getState()) {
                    this.bRunLoop = true;
                    if (this.dArpegValue > 0.0) {
                        while (this.bRunLoop) {
                            for (int j = 0; j < this.nMidiNotes.length; ++j) {
                                if (this.nMidiNotes[j] != -1) {
                                    this.midichannels[this.channel].noteOn(this.nMidiNotes[j] + this.nOctave, this.nVeloVal);
                                    Thread.sleep(this.lArpeggioTime);
                                    this.midichannels[this.channel].noteOff(this.nMidiNotes[j] + this.nOctave);
                                }
                            }
                            if (this.cbxUpAndDown.getState()) {
                                for (int k = this.nMidiNotes.length - 2; k > 0; --k) {
                                    if (this.nMidiNotes[k] != -1) {
                                        this.midichannels[this.channel].noteOn(this.nMidiNotes[k] + this.nOctave, this.nVeloVal);
                                        Thread.sleep(this.lArpeggioTime);
                                        this.midichannels[this.channel].noteOff(this.nMidiNotes[k] + this.nOctave);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        final AlertFrame alertFrame2 = new AlertFrame("To loop a chord set the arpeggio value greater 0.");
                    }
                }
                else {
                    if (this.dArpegValue > 0.0) {
                        for (int l = 0; l < this.nMidiNotes.length; ++l) {
                            if (this.nMidiNotes[l] != -1) {
                                this.midichannels[this.channel].noteOn(this.nMidiNotes[l] + this.nOctave, this.nVeloVal);
                                Thread.sleep(this.lArpeggioTime);
                                this.midichannels[this.channel].noteOff(this.nMidiNotes[l] + this.nOctave);
                            }
                        }
                    }
                    else {
                        for (int n = 0; n < this.nMidiNotes.length; ++n) {
                            if (this.nMidiNotes[n] != -1) {
                                this.midichannels[this.channel].noteOn(this.nMidiNotes[n] + this.nOctave, this.nVeloVal);
                            }
                        }
                        if (!this.cbxSoundPressedBtn.getState()) {
                            Thread.sleep(this.nChordDuration);
                            for (int n2 = 0; n2 < this.nMidiNotes.length; ++n2) {
                                if (this.nMidiNotes[n2] != -1) {
                                    this.midichannels[this.channel].noteOff(this.nMidiNotes[n2] + this.nOctave);
                                }
                            }
                        }
                    }
                    if (this.cbxUpAndDown.getState() && this.dArpegValue > 0.0) {
                        for (int n3 = this.nMidiNotes.length - 2; n3 >= 0; --n3) {
                            if (this.nMidiNotes[n3] != -1) {
                                this.midichannels[this.channel].noteOn(this.nMidiNotes[n3] + this.nOctave, this.nVeloVal);
                                Thread.sleep(this.lArpeggioTime);
                                this.midichannels[this.channel].noteOff(this.nMidiNotes[n3] + this.nOctave);
                            }
                        }
                    }
                }
            }
            catch (InterruptedException ex) {}
        }
        else {
            final AlertFrame alertFrame3 = new AlertFrame("Please select a chord or set a note.");
        }
    }
    
    public void stop() {
        if (this.nMidiNotes != null) {
            for (int i = 0; i < this.nMidiNotes.length; ++i) {
                if (this.nMidiNotes[i] != -1) {
                    this.midichannels[this.channel].noteOff(this.nMidiNotes[i] + this.nOctave);
                }
            }
        }
        this.bRunLoop = false;
        if (this.midiThread.isAlive()) {
            this.midiThread.interrupt();
        }
    }
    
    public void changeInstrument(final int n) {
        if (this.instruments != null) {
            this.synthesizer.loadInstrument(this.instruments[n]);
        }
        if (this.midichannels[this.channel] != null) {
            this.midichannels[this.channel].programChange(n);
        }
    }
    
    public void close() {
        if (this.synthesizer != null) {
            this.synthesizer.close();
        }
        this.synthesizer = null;
        this.instruments = null;
        this.midichannels = null;
    }
    
    static {
        MidiGuitar.bStandAlone = false;
    }
}
