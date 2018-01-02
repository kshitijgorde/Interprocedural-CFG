// 
// Decompiled by Procyon v0.5.30
// 

package abc.xml;

import java.io.OutputStream;
import java.util.Vector;
import java.util.Arrays;
import abc.notation.BarLine;
import abc.notation.MultiNote;
import abc.notation.Note;
import abc.notation.MusicElement;
import abc.notation.KeySignature;
import abc.notation.TimeSignature;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import abc.notation.Tune;

public class Abc2xml
{
    protected static final String SCORE_PARTWISE_TAG = "score-partwise";
    protected static final String PART_LIST_TAG = "part-list";
    protected static final String SCORE_PART_TAG = "score-part";
    protected static final String PART_NAME_TAG = "part-name";
    protected static final String PART_TAG = "part";
    protected static final String MEASURE_TAG = "measure";
    protected static final String ATTRIBUTES_TAG = "attributes";
    protected static final String DIVISIONS_TAG = "divisions";
    protected static final String CLEF_TAG = "clef";
    protected static final String SIGN_TAG = "sign";
    protected static final String LINE_TAG = "line";
    protected static final String NOTE_TAG = "note";
    protected static final String CHORD_TAG = "chord";
    protected static final String PITCH_TAG = "pitch";
    protected static final String REST_TAG = "rest";
    protected static final String STEP_TAG = "step";
    protected static final String OCTAVE_TAG = "octave";
    protected static final String DURATION_TAG = "duration";
    protected static final String DOTS_TAG = "dots";
    protected static final String TYPE_TAG = "type";
    protected static final String KEY_TAG = "key";
    protected static final String FIFTHS_TAG = "fifths";
    protected static final String TIME_TAG = "time";
    protected static final String BEATS_TAG = "beats";
    protected static final String BEAT_TYPE_TAG = "beat-type";
    protected static final String BAR_LINE_TAG = "barline";
    protected static final String REPEAT_TAG = "repeat";
    protected static final String ACCIDENTAL_TAG = "accidental";
    protected static final String BEAM_TAG = "beam";
    protected static final String ID_ATTRIBUTE = "id";
    protected static final String NUMBER_ATTRIBUTE = "number";
    protected static final String LOCATION_ATTRIBUTE = "location";
    protected static final String DIRECTION_ATTRIBUTE = "direction";
    protected static final int DIVISIONS_PER_QUARTER_NOTE = 32;
    protected static final byte[] KEY_NO_ACCIDENTAL;
    protected static final byte[] KEY_SHARP_1ST;
    protected static final byte[] KEY_SHARP_2ND;
    protected static final byte[] KEY_SHARP_3RD;
    protected static final byte[] KEY_SHARP_4TH;
    protected static final byte[] KEY_SHARP_5TH;
    protected static final byte[] KEY_SHARP_6TH;
    protected static final byte[] KEY_SHARP_7TH;
    protected static final byte[] KEY_FLAT_1ST;
    protected static final byte[] KEY_FLAT_2ND;
    protected static final byte[] KEY_FLAT_3RD;
    protected static final byte[] KEY_FLAT_4TH;
    protected static final byte[] KEY_FLAT_5TH;
    protected static final byte[] KEY_FLAT_6TH;
    protected static final byte[] KEY_FLAT_7TH;
    
    public static void writeAsMusicXML(final Tune tune, final File file) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        final Document doc = createMusicXmlDOM(tune);
        writeAsMusicXML(doc, writer);
        writer.flush();
        writer.close();
    }
    
    public static void writeAsMusicXML(final Node node, final BufferedWriter writer) throws IOException {
        try {
            final TransformerFactory transfac = TransformerFactory.newInstance();
            final Transformer trans = transfac.newTransformer();
            trans.setOutputProperty("indent", "yes");
            trans.setOutputProperty("doctype-public", "test 2 doctype");
            final StreamResult result = new StreamResult(writer);
            final DOMSource source = new DOMSource(node);
            trans.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Document createMusicXmlDOM(final Tune tune) {
        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            final Element root = doc.createElement("score-partwise");
            doc.appendChild(root);
            doc.setXmlVersion("1.0");
            final Element partListEl = doc.createElement("part-list");
            final Element scorePartEl = doc.createElement("score-part");
            scorePartEl.setAttribute("id", "1");
            final Element partNameEl = doc.createElement("part-name");
            final Element partEl = doc.createElement("part");
            partEl.setAttribute("id", "1");
            partNameEl.appendChild(doc.createTextNode(tune.getTitles()[0]));
            scorePartEl.appendChild(partNameEl);
            partListEl.appendChild(scorePartEl);
            root.appendChild(partListEl);
            root.appendChild(partEl);
            convert(doc, tune.getMusic(), partEl);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    
    protected static void appendTo(final Element measureElement, final TimeSignature time, final Document context) {
        Element attributeEl = (Element)measureElement.getElementsByTagName("attributes").item(0);
        if (attributeEl == null) {
            attributeEl = createMeasureGeneralAttributes(context);
            measureElement.insertBefore(attributeEl, measureElement.getFirstChild());
        }
        attributeEl.appendChild(convert(context, time));
    }
    
    protected static void appendTo(final Element measureElement, final KeySignature key, final Document context) {
        Element attributeEl = (Element)measureElement.getElementsByTagName("attributes").item(0);
        if (attributeEl == null) {
            attributeEl = createMeasureGeneralAttributes(context);
            measureElement.insertBefore(attributeEl, measureElement.getFirstChild());
        }
        attributeEl.appendChild(convert(context, key));
    }
    
    protected static Element createMeasureGeneralAttributes(final Document context) {
        final Element attributeEl = context.createElement("attributes");
        final Element divisionEl = context.createElement("divisions");
        divisionEl.appendChild(context.createTextNode(new Integer(32).toString()));
        attributeEl.appendChild(divisionEl);
        final Element clefEl = context.createElement("clef");
        final Element signEl = context.createElement("sign");
        final Element linEl = context.createElement("line");
        clefEl.appendChild(signEl);
        clefEl.appendChild(linEl);
        attributeEl.appendChild(clefEl);
        return attributeEl;
    }
    
    protected static void convert(final Document doc, final Tune.Music music, final Element musicElement) {
        int measureNb = 1;
        Element currentMeasureEl = doc.createElement("measure");
        int addedMusicElement = 0;
        musicElement.appendChild(currentMeasureEl);
        currentMeasureEl.setAttribute("number", new Integer(measureNb).toString());
        for (int i = 0; i < music.size(); ++i) {
            final MusicElement current = music.elementAt(i);
            if (music.elementAt(i) instanceof Note) {
                final Note note = music.elementAt(i);
                final Element noteElement = convert(doc, note);
                currentMeasureEl.appendChild(noteElement);
                ++addedMusicElement;
            }
            else if (music.elementAt(i) instanceof MultiNote) {
                final Node[] nodes = convert(doc, music.elementAt(i));
                for (int j = 0; j < nodes.length; ++j) {
                    currentMeasureEl.appendChild(nodes[j]);
                }
                ++addedMusicElement;
            }
            else if (music.elementAt(i) instanceof TimeSignature) {
                appendTo(currentMeasureEl, music.elementAt(i), doc);
            }
            else if (music.elementAt(i) instanceof KeySignature) {
                appendTo(currentMeasureEl, music.elementAt(i), doc);
            }
            else {
                if (music.elementAt(i) instanceof BarLine) {
                    if (music.elementAt(i).getType() == 1) {
                        final Element barLineNode = doc.createElement("barline");
                        barLineNode.setAttribute("location", "left");
                        final Element repeatNode = doc.createElement("repeat");
                        repeatNode.setAttribute("direction", "forward");
                        barLineNode.appendChild(repeatNode);
                        currentMeasureEl.appendChild(barLineNode);
                    }
                    else if (music.elementAt(i).getType() == 2) {
                        final Element barLineNode = doc.createElement("barline");
                        barLineNode.setAttribute("location", "right");
                        final Element repeatNode = doc.createElement("repeat");
                        repeatNode.setAttribute("direction", "backward");
                        barLineNode.appendChild(repeatNode);
                        currentMeasureEl.appendChild(barLineNode);
                    }
                }
                if (music.elementAt(i) instanceof BarLine && addedMusicElement > 0) {
                    currentMeasureEl = doc.createElement("measure");
                    ++measureNb;
                    currentMeasureEl.setAttribute("number", new Integer(measureNb).toString());
                    musicElement.appendChild(currentMeasureEl);
                    addedMusicElement = 0;
                }
            }
        }
    }
    
    protected static Node convert(final Document doc, final KeySignature signature) {
        final Element keyEl = doc.createElement("key");
        final Element fifthEl = doc.createElement("fifths");
        final byte[] acc = signature.getAccidentals();
        if (Arrays.equals(acc, Abc2xml.KEY_NO_ACCIDENTAL)) {
            fifthEl.appendChild(doc.createTextNode("0"));
        }
        else if (signature.hasOnlySharps()) {
            if (Arrays.equals(acc, Abc2xml.KEY_SHARP_1ST)) {
                fifthEl.appendChild(doc.createTextNode("1"));
            }
            else if (Arrays.equals(acc, Abc2xml.KEY_SHARP_2ND)) {
                fifthEl.appendChild(doc.createTextNode("2"));
            }
            else if (Arrays.equals(acc, Abc2xml.KEY_SHARP_3RD)) {
                fifthEl.appendChild(doc.createTextNode("3"));
            }
            else if (Arrays.equals(acc, Abc2xml.KEY_SHARP_4TH)) {
                fifthEl.appendChild(doc.createTextNode("4"));
            }
            else if (Arrays.equals(acc, Abc2xml.KEY_SHARP_5TH)) {
                fifthEl.appendChild(doc.createTextNode("5"));
            }
            else if (Arrays.equals(acc, Abc2xml.KEY_SHARP_6TH)) {
                fifthEl.appendChild(doc.createTextNode("6"));
            }
            else if (Arrays.equals(acc, Abc2xml.KEY_SHARP_7TH)) {
                fifthEl.appendChild(doc.createTextNode("7"));
            }
        }
        else if (Arrays.equals(acc, Abc2xml.KEY_FLAT_1ST)) {
            fifthEl.appendChild(doc.createTextNode("-1"));
        }
        else if (Arrays.equals(acc, Abc2xml.KEY_FLAT_2ND)) {
            fifthEl.appendChild(doc.createTextNode("-2"));
        }
        else if (Arrays.equals(acc, Abc2xml.KEY_FLAT_3RD)) {
            fifthEl.appendChild(doc.createTextNode("-3"));
        }
        else if (Arrays.equals(acc, Abc2xml.KEY_FLAT_4TH)) {
            fifthEl.appendChild(doc.createTextNode("-4"));
        }
        else if (Arrays.equals(acc, Abc2xml.KEY_FLAT_5TH)) {
            fifthEl.appendChild(doc.createTextNode("-5"));
        }
        else if (Arrays.equals(acc, Abc2xml.KEY_FLAT_6TH)) {
            fifthEl.appendChild(doc.createTextNode("-6"));
        }
        else if (Arrays.equals(acc, Abc2xml.KEY_FLAT_7TH)) {
            fifthEl.appendChild(doc.createTextNode("-7"));
        }
        keyEl.appendChild(fifthEl);
        return keyEl;
    }
    
    protected static Node convert(final Document doc, final TimeSignature signature) {
        final Element timeEl = doc.createElement("time");
        final Element beatsEl = doc.createElement("beats");
        final Element beatTypeEl = doc.createElement("beat-type");
        beatsEl.appendChild(doc.createTextNode(Integer.toString(signature.getNumerator())));
        beatTypeEl.appendChild(doc.createTextNode(Integer.toString(signature.getDenominator())));
        timeEl.appendChild(beatsEl);
        timeEl.appendChild(beatTypeEl);
        return timeEl;
    }
    
    protected static Node[] convert(final Document doc, final MultiNote chord) {
        final Vector notes = chord.getNotesAsVector();
        final Node[] nodes = new Node[notes.size()];
        for (int i = 0; i < notes.size(); ++i) {
            nodes[i] = convert(doc, notes.elementAt(i));
            if (i != 0) {
                nodes[i].insertBefore(doc.createElement("chord"), nodes[i].getFirstChild());
            }
        }
        return nodes;
    }
    
    protected static Element convert(final Document doc, final Note note) {
        final Element noteEl = doc.createElement("note");
        final Element durationEl = doc.createElement("duration");
        String stepValue = null;
        final byte strictHeight = note.getStrictHeight();
        int octave = note.getOctaveTransposition();
        if (note.isRest()) {
            final Element rest = doc.createElement("rest");
            noteEl.appendChild(rest);
        }
        else {
            switch (strictHeight) {
                case 0: {
                    stepValue = "C";
                    break;
                }
                case 2: {
                    stepValue = "D";
                    break;
                }
                case 4: {
                    stepValue = "E";
                    break;
                }
                case 5: {
                    stepValue = "F";
                    break;
                }
                case 7: {
                    stepValue = "G";
                    break;
                }
                case 9: {
                    stepValue = "A";
                    break;
                }
                case 11: {
                    stepValue = "B";
                    break;
                }
            }
            octave += 4;
            final String octaveValue = new Integer(octave).toString();
            final Element pitchEl = doc.createElement("pitch");
            final Element stepEl = doc.createElement("step");
            stepEl.appendChild(doc.createTextNode(stepValue));
            final Element octaveEl = doc.createElement("octave");
            octaveEl.appendChild(doc.createTextNode(octaveValue));
            pitchEl.appendChild(stepEl);
            pitchEl.appendChild(octaveEl);
            noteEl.appendChild(pitchEl);
            if (note.countDots() >= 1) {
                final Node dot = doc.createElement("dots");
                noteEl.appendChild(dot);
            }
            final Node type = doc.createElement("type");
            Node typeValue = null;
            switch (note.getStrictDuration()) {
                case 3: {
                    typeValue = doc.createTextNode("64th");
                    break;
                }
                case 6: {
                    typeValue = doc.createTextNode("32nd");
                    break;
                }
                case 12: {
                    typeValue = doc.createTextNode("16th");
                    break;
                }
                case 24: {
                    typeValue = doc.createTextNode("eighth");
                    break;
                }
                case 48: {
                    typeValue = doc.createTextNode("quarter");
                    break;
                }
                case 96: {
                    typeValue = doc.createTextNode("half");
                    break;
                }
                case 192: {
                    typeValue = doc.createTextNode("whole");
                    break;
                }
            }
            if (typeValue != null) {
                type.appendChild(typeValue);
                noteEl.appendChild(type);
            }
            if (note.hasAccidental()) {
                final Node acc = doc.createElement("accidental");
                Node accValue = null;
                switch (note.getAccidental()) {
                    case -1: {
                        accValue = doc.createTextNode("flat");
                        break;
                    }
                    case 0: {
                        accValue = doc.createTextNode("natural");
                        break;
                    }
                    case 1: {
                        accValue = doc.createTextNode("sharp");
                        break;
                    }
                }
                acc.appendChild(accValue);
                noteEl.appendChild(acc);
            }
        }
        final int relDuration = note.getDuration() * 32 / 48;
        durationEl.appendChild(doc.createTextNode(new Integer(relDuration).toString()));
        noteEl.appendChild(durationEl);
        return noteEl;
    }
    
    protected static void dumpDOM(final Document doc) {
        try {
            final TransformerFactory transfac = TransformerFactory.newInstance();
            final Transformer trans = transfac.newTransformer();
            trans.setOutputProperty("indent", "yes");
            trans.setOutputProperty("doctype-public", "test 2 doctype");
            final StreamResult result = new StreamResult(System.out);
            final DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        KEY_NO_ACCIDENTAL = new byte[] { 0, 0, 0, 0, 0, 0, 0 };
        KEY_SHARP_1ST = new byte[] { 0, 0, 0, 1, 0, 0, 0 };
        KEY_SHARP_2ND = new byte[] { 1, 0, 0, 1, 0, 0, 0 };
        KEY_SHARP_3RD = new byte[] { 1, 0, 0, 1, 1, 0, 0 };
        KEY_SHARP_4TH = new byte[] { 1, 1, 0, 1, 1, 0, 0 };
        KEY_SHARP_5TH = new byte[] { 1, 1, 0, 1, 1, 1, 0 };
        KEY_SHARP_6TH = new byte[] { 1, 1, 1, 1, 1, 1, 0 };
        KEY_SHARP_7TH = new byte[] { 1, 1, 1, 1, 1, 1, 1 };
        KEY_FLAT_1ST = new byte[] { 0, 0, 0, 0, 0, 0, -1 };
        KEY_FLAT_2ND = new byte[] { 0, 0, -1, 0, 0, 0, -1 };
        KEY_FLAT_3RD = new byte[] { 0, 0, -1, 0, 0, -1, -1 };
        KEY_FLAT_4TH = new byte[] { 0, -1, -1, 0, 0, -1, -1 };
        KEY_FLAT_5TH = new byte[] { 0, -1, -1, 0, -1, -1, -1 };
        KEY_FLAT_6TH = new byte[] { -1, -1, -1, 0, -1, -1, -1 };
        KEY_FLAT_7TH = new byte[] { -1, -1, -1, -1, -1, -1, -1 };
    }
}
