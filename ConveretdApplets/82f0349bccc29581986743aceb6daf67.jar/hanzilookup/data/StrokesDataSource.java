// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.DataInputStream;
import java.io.IOException;

public class StrokesDataSource
{
    private StrokesStreamProvider streamProvider;
    private long[] genericPositions;
    private long[] simplifiedPositions;
    private long[] traditionalPositions;
    
    public StrokesDataSource(final StrokesStreamProvider streamProvider) throws IOException {
        this.genericPositions = new long[48];
        this.simplifiedPositions = new long[48];
        this.traditionalPositions = new long[48];
        this.streamProvider = streamProvider;
        this.indexPositions();
    }
    
    private void indexPositions() throws IOException {
        final DataInputStream inStream = new DataInputStream(this.streamProvider.getStrokesStream());
        long bytePosition = 0L;
        bytePosition = this.loadPositions(this.genericPositions, inStream, bytePosition);
        bytePosition = this.loadPositions(this.simplifiedPositions, inStream, bytePosition);
        bytePosition = this.loadPositions(this.traditionalPositions, inStream, bytePosition);
        inStream.close();
    }
    
    private long loadPositions(final long[] positions, final DataInputStream inStream, long bytePosition) throws IOException {
        for (int i = 0; i < positions.length; ++i) {
            positions[i] = bytePosition;
            final int bytesForSeries = inStream.readInt();
            bytePosition += bytesForSeries + 4;
            skipFully(bytesForSeries, inStream);
        }
        return bytePosition;
    }
    
    public StrokesDataScanner getStrokesScanner(final boolean searchTraditional, final boolean searchSimplified, int minStrokes, int maxStrokes) {
        minStrokes = Math.max(1, minStrokes);
        maxStrokes = Math.min(48, maxStrokes);
        return new StrokesDataScanner(searchTraditional, searchSimplified, minStrokes, maxStrokes, (StrokesDataScanner)null);
    }
    
    private static void skipFully(long bytesToSkip, final DataInputStream inStream) throws IOException {
        while (bytesToSkip > 0L) {
            bytesToSkip -= inStream.skip(bytesToSkip);
        }
    }
    
    public class StrokesDataScanner
    {
        private DataInputStream strokeDataStream;
        private Iterator positionsIter;
        private long position;
        private long endOfStrokeCount;
        private boolean skipToNextTypePosition;
        private boolean loadNextStrokeCount;
        private int strokeCount;
        private int minStrokes;
        private int maxStrokes;
        
        private StrokesDataScanner(final boolean searchTraditional, final boolean searchSimplified, final int minStrokes, final int maxStrokes) {
            final int strokeIndex = minStrokes - 1;
            final List positions = new ArrayList(3);
            positions.add(new Long(StrokesDataSource.this.genericPositions[strokeIndex]));
            if (searchSimplified) {
                positions.add(new Long(StrokesDataSource.this.simplifiedPositions[strokeIndex]));
            }
            if (searchTraditional) {
                positions.add(new Long(StrokesDataSource.this.traditionalPositions[strokeIndex]));
            }
            final InputStream strokesStream = new DataInputStream(StrokesDataSource.this.streamProvider.getStrokesStream());
            if (strokesStream == null) {
                throw new NullPointerException("Unable to get strokes stream!");
            }
            this.strokeDataStream = new DataInputStream(strokesStream);
            this.positionsIter = positions.iterator();
            this.position = 0L;
            this.skipToNextTypePosition = true;
            this.loadNextStrokeCount = true;
            this.strokeCount = minStrokes;
            this.minStrokes = minStrokes;
            this.maxStrokes = maxStrokes;
        }
        
        public boolean loadNextCharacterStrokeData(final CharacterDescriptor descriptor) throws IOException {
            if (this.strokeDataStream == null) {
                return false;
            }
            if (this.skipToNextTypePosition) {
                if (!this.positionsIter.hasNext()) {
                    return false;
                }
                final long nextPosition = this.positionsIter.next();
                final long skipBytes = nextPosition - this.position;
                skipFully(skipBytes, this.strokeDataStream);
                this.position = nextPosition;
                this.skipToNextTypePosition = false;
            }
            if (this.loadNextStrokeCount) {
                this.position += 4L;
                this.endOfStrokeCount = this.position + this.strokeDataStream.readInt();
                this.loadNextStrokeCount = false;
            }
            if (this.position < this.endOfStrokeCount) {
                this.loadNextCharacterDataFromStream(descriptor, this.strokeDataStream);
                this.position += 4 + descriptor.getStrokeCount() + 4 * descriptor.getSubStrokeCount();
            }
            if (this.position == this.endOfStrokeCount) {
                this.loadNextStrokeCount = true;
                if (this.strokeCount == this.maxStrokes) {
                    this.skipToNextTypePosition = true;
                    this.strokeCount = this.minStrokes;
                }
                else {
                    ++this.strokeCount;
                }
            }
            return true;
        }
        
        private void loadNextCharacterDataFromStream(final CharacterDescriptor loadInto, final DataInputStream dataStream) throws IOException {
            final Character character = new Character(StrokesIO.readCharacter(dataStream));
            final int characterType = StrokesIO.readCharacterType(dataStream);
            final int strokeCount = StrokesIO.readStrokeCount(dataStream);
            int subStrokeCount = 0;
            final double[] directions = loadInto.getDirections();
            final double[] lengths = loadInto.getLengths();
            for (int i = 0; i < strokeCount; ++i) {
                for (int numSubStrokesInStroke = StrokesIO.readSubStrokeCount(dataStream), j = 0; j < numSubStrokesInStroke; ++j) {
                    final double direction = StrokesIO.readDirection(dataStream);
                    final double length = StrokesIO.readLength(dataStream);
                    directions[subStrokeCount] = direction;
                    lengths[subStrokeCount] = length;
                    ++subStrokeCount;
                }
            }
            loadInto.setCharacter(character);
            loadInto.setCharacterType(characterType);
            loadInto.setStrokeCount(strokeCount);
            loadInto.setSubStrokeCount(subStrokeCount);
        }
    }
    
    public interface StrokesStreamProvider
    {
        InputStream getStrokesStream();
    }
}
