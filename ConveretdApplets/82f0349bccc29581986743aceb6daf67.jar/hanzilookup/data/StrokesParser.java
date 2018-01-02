// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.StringTokenizer;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import kiang.io.LineParser;

public class StrokesParser extends LineParser
{
    private ByteArrayOutputStream[] genericByteStreams;
    private ByteArrayOutputStream[] simplifiedByteStreams;
    private ByteArrayOutputStream[] traditionalByteStreams;
    private DataOutputStream[] genericOutStreams;
    private DataOutputStream[] simplifiedOutStreams;
    private DataOutputStream[] traditionalOutStreams;
    private CharacterTypeRepository typeRepository;
    private int[] subStrokesPerStroke;
    private double[] subStrokeDirections;
    private double[] subStrokeLengths;
    private Pattern linePattern;
    private Pattern subStrokePattern;
    
    public StrokesParser(final InputStream strokesIn, final CharacterTypeRepository typeRepository) throws IOException {
        this.subStrokesPerStroke = new int[48];
        this.subStrokeDirections = new double[64];
        this.subStrokeLengths = new double[64];
        this.linePattern = Pattern.compile("^([a-fA-F0-9]{4})\\s*\\|(.*)$");
        this.subStrokePattern = Pattern.compile("^\\s*\\((\\d+(\\.\\d{1,10})?)\\s*,\\s*(\\d+(\\.\\d{1,10})?)\\)\\s*$");
        this.typeRepository = typeRepository;
        this.initStrokes(strokesIn);
    }
    
    public StrokesParser(final InputStream strokesIn, final InputStream typesIn) throws IOException {
        this.subStrokesPerStroke = new int[48];
        this.subStrokeDirections = new double[64];
        this.subStrokeLengths = new double[64];
        this.linePattern = Pattern.compile("^([a-fA-F0-9]{4})\\s*\\|(.*)$");
        this.subStrokePattern = Pattern.compile("^\\s*\\((\\d+(\\.\\d{1,10})?)\\s*,\\s*(\\d+(\\.\\d{1,10})?)\\)\\s*$");
        final CharacterTypeParser typeParser = new CharacterTypeParser(typesIn);
        this.typeRepository = typeParser.buildCharacterTypeRepository();
        this.initStrokes(strokesIn);
    }
    
    private void initStrokes(final InputStream strokesIn) throws IOException {
        try {
            this.prepareStrokeBytes();
            this.parse(strokesIn);
            strokesIn.close();
        }
        catch (IOException ioe) {
            final IOException thrownIOE = new IOException("Error reading character stroke data!");
            thrownIOE.initCause(ioe);
            throw thrownIOE;
        }
    }
    
    public void writeCompiledOutput(final OutputStream out) throws IOException {
        final byte[][] genericBytes = new byte[48][];
        final byte[][] simplifiedBytes = new byte[48][];
        final byte[][] traditionalBytes = new byte[48][];
        for (int i = 0; i < 48; ++i) {
            genericBytes[i] = this.genericByteStreams[i].toByteArray();
            simplifiedBytes[i] = this.simplifiedByteStreams[i].toByteArray();
            traditionalBytes[i] = this.traditionalByteStreams[i].toByteArray();
        }
        final DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(out));
        this.writeStrokes(genericBytes, dataOut);
        this.writeStrokes(simplifiedBytes, dataOut);
        this.writeStrokes(traditionalBytes, dataOut);
        dataOut.close();
    }
    
    private void writeStrokes(final byte[][] bytesForSeries, final DataOutputStream dataOut) throws IOException {
        for (int strokeCount = 0; strokeCount < 48; ++strokeCount) {
            final int bytesForStrokeCount = bytesForSeries[strokeCount].length;
            dataOut.writeInt(bytesForStrokeCount);
            dataOut.write(bytesForSeries[strokeCount]);
        }
    }
    
    private void prepareStrokeBytes() {
        this.genericByteStreams = new ByteArrayOutputStream[48];
        this.genericOutStreams = new DataOutputStream[48];
        this.simplifiedByteStreams = new ByteArrayOutputStream[48];
        this.simplifiedOutStreams = new DataOutputStream[48];
        this.traditionalByteStreams = new ByteArrayOutputStream[48];
        this.traditionalOutStreams = new DataOutputStream[48];
        for (int i = 0; i < 48; ++i) {
            this.genericByteStreams[i] = new ByteArrayOutputStream();
            this.genericOutStreams[i] = new DataOutputStream(this.genericByteStreams[i]);
            this.simplifiedByteStreams[i] = new ByteArrayOutputStream();
            this.simplifiedOutStreams[i] = new DataOutputStream(this.simplifiedByteStreams[i]);
            this.traditionalByteStreams[i] = new ByteArrayOutputStream();
            this.traditionalOutStreams[i] = new DataOutputStream(this.traditionalByteStreams[i]);
        }
    }
    
    protected boolean parseLine(final int lineNum, final String line) {
        final Matcher lineMatcher = this.linePattern.matcher(line);
        boolean parsedOk = true;
        int subStrokeIndex = 0;
        if (lineMatcher.matches()) {
            final String unicodeString = lineMatcher.group(1);
            final Character character = new Character((char)Integer.parseInt(unicodeString, 16));
            final String lineRemainder = lineMatcher.group(2);
            int strokeCount = 0;
            final StringTokenizer strokeTokenizer = new StringTokenizer(lineRemainder, "|");
            while (strokeTokenizer.hasMoreTokens()) {
                if (strokeCount >= 48) {
                    parsedOk = false;
                    break;
                }
                final String nextStroke = strokeTokenizer.nextToken();
                final int subStrokes = this.parseStroke(nextStroke, strokeCount, subStrokeIndex);
                if (subStrokes > 0) {
                    subStrokeIndex += subStrokes;
                }
                else {
                    parsedOk = false;
                }
                ++strokeCount;
            }
            if (parsedOk) {
                int type = this.typeRepository.getType(character);
                if (type == -1) {
                    type = 0;
                }
                DataOutputStream dataOut;
                if (type == 2) {
                    dataOut = this.traditionalOutStreams[strokeCount - 1];
                }
                else if (type == 1) {
                    dataOut = this.simplifiedOutStreams[strokeCount - 1];
                }
                else {
                    dataOut = this.genericOutStreams[strokeCount - 1];
                }
                this.writeStrokeData(dataOut, character, type, strokeCount, subStrokeIndex);
                return true;
            }
        }
        return false;
    }
    
    private int parseStroke(final String strokeText, final int strokeIndex, final int baseSubStrokeIndex) {
        int subStrokeCount = 0;
        final StringTokenizer subStrokeTokenizer = new StringTokenizer(strokeText, "#");
        while (subStrokeTokenizer.hasMoreTokens()) {
            if (subStrokeCount >= 64 || !this.parseSubStroke(subStrokeTokenizer.nextToken(), baseSubStrokeIndex + subStrokeCount)) {
                return -1;
            }
            ++subStrokeCount;
        }
        return this.subStrokesPerStroke[strokeIndex] = subStrokeCount;
    }
    
    private boolean parseSubStroke(final String subStrokeText, final int subStrokeIndex) {
        final Matcher subStrokeMatcher = this.subStrokePattern.matcher(subStrokeText);
        if (subStrokeMatcher.matches()) {
            final double direction = Double.parseDouble(subStrokeMatcher.group(1));
            final double length = Double.parseDouble(subStrokeMatcher.group(3));
            this.subStrokeDirections[subStrokeIndex] = direction;
            this.subStrokeLengths[subStrokeIndex] = length;
            return true;
        }
        return false;
    }
    
    private void writeStrokeData(final DataOutputStream dataOut, final Character character, final int type, final int strokeCount, final int subStrokeCount) {
        try {
            StrokesIO.writeCharacter(character, dataOut);
            StrokesIO.writeCharacterType(type, dataOut);
            StrokesIO.writeStrokeCount(strokeCount, dataOut);
            int subStrokeArrayIndex = 0;
            for (int strokes = 0; strokes < strokeCount; ++strokes) {
                final int numSubStrokeForStroke = this.subStrokesPerStroke[strokes];
                StrokesIO.writeSubStrokeCount(numSubStrokeForStroke, dataOut);
                for (int substrokes = 0; substrokes < numSubStrokeForStroke; ++substrokes) {
                    StrokesIO.writeDirection(this.subStrokeDirections[subStrokeArrayIndex], dataOut);
                    StrokesIO.writeLength(this.subStrokeLengths[subStrokeArrayIndex], dataOut);
                    ++subStrokeArrayIndex;
                }
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static byte[] getStrokeBytes(final InputStream strokesIn, final InputStream typesIn) throws IOException {
        final StrokesParser strokesParser = new StrokesParser(strokesIn, typesIn);
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        strokesParser.writeCompiledOutput(bytes);
        return bytes.toByteArray();
    }
    
    public static void main(final String[] args) {
        if (args.length != 3) {
            final StringBuffer sbuf = new StringBuffer();
            sbuf.append("Takes three arguments:\n");
            sbuf.append("1: the plain-text strokes data file\n");
            sbuf.append("2: the plain-text types data file\n");
            sbuf.append("3: the file to output the compiled data file to");
            System.err.println(sbuf);
        }
        else {
            try {
                final FileInputStream strokesIn = new FileInputStream(args[0]);
                final FileInputStream typesIn = new FileInputStream(args[1]);
                final FileOutputStream compiledOut = new FileOutputStream(args[2]);
                final CharacterTypeParser typeParser = new CharacterTypeParser(typesIn);
                final CharacterTypeRepository typeRepository = typeParser.buildCharacterTypeRepository();
                final StrokesParser strokesParser = new StrokesParser(strokesIn, typeRepository);
                strokesParser.writeCompiledOutput(compiledOut);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
