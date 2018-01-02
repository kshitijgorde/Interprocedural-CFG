// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.AbstractUndoableEdit;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.undo.UndoableEdit;
import java.util.Vector;
import java.io.Serializable;

public class GapContent extends GapVector implements Content, Serializable
{
    private static final char[] empty;
    private transient MarkVector marks;
    private transient MarkData search;
    private transient int unusedMarks;
    
    static {
        empty = new char[0];
    }
    
    public GapContent() {
        this(10);
    }
    
    public GapContent(final int n) {
        super(Math.max(n, 2));
        final char[] array = { '\n' };
        this.replace(0, 0, array, array.length);
        this.marks = new MarkVector();
        this.search = new MarkData(0);
    }
    
    static /* synthetic */ void access$1(final GapContent gapContent, final int unusedMarks) {
        gapContent.unusedMarks = unusedMarks;
    }
    
    protected Object allocateArray(final int n) {
        return new char[n];
    }
    
    final int compare(final MarkData markData, final MarkData markData2) {
        if (markData.index < markData2.index) {
            return -1;
        }
        if (markData.index > markData2.index) {
            return 1;
        }
        return 0;
    }
    
    public Position createPosition(final int n) throws BadLocationException {
        if (this.unusedMarks > Math.max(5, this.marks.size() / 10)) {
            this.removeUnusedMarks();
        }
        final int gapStart = this.getGapStart();
        final int gapEnd = this.getGapEnd();
        final MarkData markData = new MarkData((n < gapStart) ? n : (n + (gapEnd - gapStart)));
        this.marks.insertElementAt(markData, this.findSortIndex(markData));
        return new StickyPosition(markData);
    }
    
    final int findMarkAdjustIndex(final int n) {
        this.search.index = Math.max(n, 1);
        int sortIndex = this.findSortIndex(this.search);
        for (int n2 = sortIndex - 1; n2 >= 0 && this.marks.elementAt(n2).index == this.search.index; --n2) {
            --sortIndex;
        }
        return sortIndex;
    }
    
    final int findSortIndex(final MarkData markData) {
        int i = 0;
        int n = this.marks.size() - 1;
        int n2 = 0;
        if (n == -1) {
            return 0;
        }
        int n3 = this.compare(markData, this.marks.elementAt(n));
        if (n3 > 0) {
            return n + 1;
        }
        while (i <= n) {
            n2 = i + (n - i) / 2;
            n3 = this.compare(markData, this.marks.elementAt(n2));
            if (n3 == 0) {
                return n2;
            }
            if (n3 < 0) {
                n = n2 - 1;
            }
            else {
                i = n2 + 1;
            }
        }
        return (n3 < 0) ? n2 : (n2 + 1);
    }
    
    protected int getArrayLength() {
        return ((char[])this.getArray()).length;
    }
    
    public void getChars(final int offset, final int count, final Segment segment) throws BadLocationException {
        if (offset < 0) {
            throw new BadLocationException("Invalid location", -1);
        }
        if (offset + count > this.length()) {
            throw new BadLocationException("Invalid location", this.length() + 1);
        }
        final int gapStart = this.getGapStart();
        final int gapEnd = this.getGapEnd();
        final char[] array = (char[])this.getArray();
        if (offset + count <= gapStart) {
            segment.array = array;
            segment.offset = offset;
        }
        else if (offset >= gapStart) {
            segment.array = array;
            segment.offset = gapEnd + offset - gapStart;
        }
        else {
            segment.array = new char[count];
            segment.offset = 0;
            final int n = gapStart - offset;
            System.arraycopy(array, offset, segment.array, 0, n);
            System.arraycopy(array, gapEnd, segment.array, n, count - n);
        }
        segment.count = count;
    }
    
    protected Vector getPositionsInRange(final Vector vector, final int n, final int n2) {
        final int n3 = n + n2;
        final int gapStart = this.getGapStart();
        final int gapEnd = this.getGapEnd();
        int n4;
        int n5;
        if (n < gapStart) {
            if (n == 0) {
                n4 = 0;
            }
            else {
                n4 = this.findMarkAdjustIndex(n);
            }
            if (n3 >= gapStart) {
                n5 = this.findMarkAdjustIndex(n3 + (gapEnd - gapStart) + 1);
            }
            else {
                n5 = this.findMarkAdjustIndex(n3 + 1);
            }
        }
        else {
            n4 = this.findMarkAdjustIndex(n + (gapEnd - gapStart));
            n5 = this.findMarkAdjustIndex(n3 + (gapEnd - gapStart) + 1);
        }
        final Vector<UndoPosRef> vector2 = (vector == null) ? new Vector<UndoPosRef>(Math.max(1, n5 - n4)) : vector;
        for (int i = n4; i < n5; ++i) {
            vector2.addElement(new UndoPosRef(this.marks.elementAt(i)));
        }
        return vector2;
    }
    
    public String getString(final int n, final int n2) throws BadLocationException {
        final Segment segment = new Segment();
        this.getChars(n, n2, segment);
        return new String(segment.array, segment.offset, segment.count);
    }
    
    public UndoableEdit insertString(final int n, final String s) throws BadLocationException {
        if (n >= this.length()) {
            throw new BadLocationException("Invalid insert", this.length());
        }
        final char[] charArray = s.toCharArray();
        this.replace(n, 0, charArray, charArray.length);
        return new InsertUndo(n, s.length());
    }
    
    public int length() {
        return this.getArrayLength() - (this.getGapEnd() - this.getGapStart());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.marks = new MarkVector();
        this.search = new MarkData(0);
    }
    
    public UndoableEdit remove(final int n, final int n2) throws BadLocationException {
        if (n + n2 >= this.length()) {
            throw new BadLocationException("Invalid remove", this.length() + 1);
        }
        final RemoveUndo removeUndo = new RemoveUndo(n, this.getString(n, n2));
        this.replace(n, n2, GapContent.empty, 0);
        return removeUndo;
    }
    
    final void removeUnusedMarks() {
        final int size = this.marks.size();
        final MarkVector marks = new MarkVector(size);
        for (int i = 0; i < size; ++i) {
            final MarkData element = this.marks.elementAt(i);
            if (!element.unused) {
                marks.addElement(element);
            }
        }
        this.marks = marks;
        this.unusedMarks = 0;
    }
    
    protected void resetMarksAtZero() {
        if (this.marks != null && this.getGapStart() == 0) {
            final int gapEnd = this.getGapEnd();
            for (int i = 0; i < this.marks.size(); ++i) {
                final MarkData element = this.marks.elementAt(i);
                if (element.index > gapEnd) {
                    break;
                }
                element.index = 0;
            }
        }
    }
    
    protected void shiftEnd(final int n) {
        final int gapEnd = this.getGapEnd();
        super.shiftEnd(n);
        final int n2 = this.getGapEnd() - gapEnd;
        final int markAdjustIndex = this.findMarkAdjustIndex(gapEnd);
        for (int size = this.marks.size(), i = markAdjustIndex; i < size; ++i) {
            final MarkData element = this.marks.elementAt(i);
            element.index += n2;
        }
    }
    
    protected void shiftGap(final int n) {
        final int gapStart = this.getGapStart();
        final int n2 = n - gapStart;
        final int gapEnd = this.getGapEnd();
        final int n3 = gapEnd + n2;
        final int n4 = gapEnd - gapStart;
        super.shiftGap(n);
        if (n2 > 0) {
            final int markAdjustIndex = this.findMarkAdjustIndex(gapStart);
            for (int size = this.marks.size(), i = markAdjustIndex; i < size; ++i) {
                final MarkData element = this.marks.elementAt(i);
                if (element.index >= n3) {
                    break;
                }
                final MarkData markData = element;
                markData.index -= n4;
            }
        }
        else if (n2 < 0) {
            final int markAdjustIndex2 = this.findMarkAdjustIndex(n);
            for (int size2 = this.marks.size(), j = markAdjustIndex2; j < size2; ++j) {
                final MarkData element2 = this.marks.elementAt(j);
                if (element2.index >= gapEnd) {
                    break;
                }
                final MarkData markData2 = element2;
                markData2.index += n4;
            }
        }
        this.resetMarksAtZero();
    }
    
    protected void shiftGapEndUp(final int index) {
        final int markAdjustIndex = this.findMarkAdjustIndex(this.getGapEnd());
        for (int size = this.marks.size(), i = markAdjustIndex; i < size; ++i) {
            final MarkData element = this.marks.elementAt(i);
            if (element.index >= index) {
                break;
            }
            element.index = index;
        }
        super.shiftGapEndUp(index);
        this.resetMarksAtZero();
    }
    
    protected void shiftGapStartDown(final int n) {
        final int markAdjustIndex = this.findMarkAdjustIndex(n);
        final int size = this.marks.size();
        final int gapStart = this.getGapStart();
        final int gapEnd = this.getGapEnd();
        for (int i = markAdjustIndex; i < size; ++i) {
            final MarkData element = this.marks.elementAt(i);
            if (element.index > gapStart) {
                break;
            }
            element.index = gapEnd;
        }
        super.shiftGapStartDown(n);
        this.resetMarksAtZero();
    }
    
    protected void updateUndoPositions(final Vector vector, final int n, final int n2) {
        final int n3 = n + n2;
        final int gapEnd = this.getGapEnd();
        final int markAdjustIndex = this.findMarkAdjustIndex(gapEnd + 1);
        int markAdjustIndex2;
        if (n != 0) {
            markAdjustIndex2 = this.findMarkAdjustIndex(gapEnd);
        }
        else {
            markAdjustIndex2 = 0;
        }
        for (int i = vector.size() - 1; i >= 0; --i) {
            vector.elementAt(i).resetLocation(n3, gapEnd);
        }
        if (markAdjustIndex2 < markAdjustIndex) {
            final Object[] array = new Object[markAdjustIndex - markAdjustIndex2];
            int n4 = 0;
            if (n == 0) {
                for (int j = markAdjustIndex2; j < markAdjustIndex; ++j) {
                    final MarkData element = this.marks.elementAt(j);
                    if (element.index == 0) {
                        array[n4++] = element;
                    }
                }
                for (int k = markAdjustIndex2; k < markAdjustIndex; ++k) {
                    final MarkData element2 = this.marks.elementAt(k);
                    if (element2.index != 0) {
                        array[n4++] = element2;
                    }
                }
            }
            else {
                for (int l = markAdjustIndex2; l < markAdjustIndex; ++l) {
                    final MarkData element3 = this.marks.elementAt(l);
                    if (element3.index != gapEnd) {
                        array[n4++] = element3;
                    }
                }
                for (int n5 = markAdjustIndex2; n5 < markAdjustIndex; ++n5) {
                    final MarkData element4 = this.marks.elementAt(n5);
                    if (element4.index == gapEnd) {
                        array[n4++] = element4;
                    }
                }
            }
            this.marks.replaceRange(markAdjustIndex2, markAdjustIndex, array);
        }
    }
    
    final class MarkData
    {
        int index;
        boolean unused;
        
        MarkData(final int index) {
            this.index = index;
        }
        
        public final void dispose() {
            this.unused = true;
            final GapContent this$0 = GapContent.this;
            GapContent.access$1(this$0, this$0.unusedMarks + 1);
        }
        
        public final int getOffset() {
            final int gapStart = GapContent.this.getGapStart();
            final int gapEnd = GapContent.this.getGapEnd();
            return Math.max((this.index < gapStart) ? this.index : (this.index - (gapEnd - gapStart)), 0);
        }
    }
    
    final class StickyPosition implements Position
    {
        MarkData mark;
        
        StickyPosition(final MarkData mark) {
            this.mark = mark;
        }
        
        protected void finalize() throws Throwable {
            this.mark.dispose();
        }
        
        public final int getOffset() {
            return this.mark.getOffset();
        }
        
        public String toString() {
            return Integer.toString(this.getOffset());
        }
    }
    
    static class MarkVector extends GapVector
    {
        static MarkData[] oneMark;
        
        static {
            MarkVector.oneMark = new MarkData[1];
        }
        
        MarkVector() {
        }
        
        MarkVector(final int n) {
            super(n);
        }
        
        public void addElement(final MarkData markData) {
            this.insertElementAt(markData, this.size());
        }
        
        protected Object allocateArray(final int n) {
            return new MarkData[n];
        }
        
        public MarkData elementAt(int n) {
            final int gapStart = this.getGapStart();
            final int gapEnd = this.getGapEnd();
            final MarkData[] array = (MarkData[])this.getArray();
            if (n < gapStart) {
                return array[n];
            }
            n += gapEnd - gapStart;
            return array[n];
        }
        
        protected int getArrayLength() {
            return ((MarkData[])this.getArray()).length;
        }
        
        public void insertElementAt(final MarkData markData, final int n) {
            MarkVector.oneMark[0] = markData;
            this.replace(n, 0, MarkVector.oneMark, 1);
        }
        
        protected void replaceRange(final int n, int n2, final Object[] array) {
            final int gapStart = this.getGapStart();
            final int gapEnd = this.getGapEnd();
            int i = n;
            int n3 = 0;
            final Object[] array2 = (Object[])this.getArray();
            if (n >= gapStart) {
                i += gapEnd - gapStart;
                n2 += gapEnd - gapStart;
            }
            else if (n2 >= gapStart) {
                n2 += gapEnd - gapStart;
                while (i < gapStart) {
                    array2[i++] = array[n3++];
                }
                i = gapEnd;
            }
            else {
                while (i < n2) {
                    array2[i++] = array[n3++];
                }
            }
            while (i < n2) {
                array2[i++] = array[n3++];
            }
        }
        
        public int size() {
            return this.getArrayLength() - (this.getGapEnd() - this.getGapStart());
        }
    }
    
    final class UndoPosRef
    {
        protected int undoLocation;
        protected MarkData rec;
        
        UndoPosRef(final MarkData rec) {
            this.rec = rec;
            this.undoLocation = rec.getOffset();
        }
        
        protected void resetLocation(final int n, final int index) {
            if (this.undoLocation != n) {
                this.rec.index = this.undoLocation;
            }
            else {
                this.rec.index = index;
            }
        }
    }
    
    class InsertUndo extends AbstractUndoableEdit
    {
        protected int offset;
        protected int length;
        protected String string;
        protected Vector posRefs;
        
        protected InsertUndo(final int offset, final int length) {
            this.offset = offset;
            this.length = length;
        }
        
        public void redo() throws CannotRedoException {
            super.redo();
            try {
                GapContent.this.insertString(this.offset, this.string);
                this.string = null;
                if (this.posRefs != null) {
                    GapContent.this.updateUndoPositions(this.posRefs, this.offset, this.length);
                    this.posRefs = null;
                }
            }
            catch (BadLocationException ex) {
                throw new CannotRedoException();
            }
        }
        
        public void undo() throws CannotUndoException {
            super.undo();
            try {
                this.posRefs = GapContent.this.getPositionsInRange(null, this.offset, this.length);
                this.string = GapContent.this.getString(this.offset, this.length);
                GapContent.this.remove(this.offset, this.length);
            }
            catch (BadLocationException ex) {
                throw new CannotUndoException();
            }
        }
    }
    
    class RemoveUndo extends AbstractUndoableEdit
    {
        protected int offset;
        protected int length;
        protected String string;
        protected Vector posRefs;
        
        protected RemoveUndo(final int offset, final String string) {
            this.offset = offset;
            this.string = string;
            this.length = string.length();
            this.posRefs = GapContent.this.getPositionsInRange(null, offset, this.length);
        }
        
        public void redo() throws CannotRedoException {
            super.redo();
            try {
                this.string = GapContent.this.getString(this.offset, this.length);
                this.posRefs = GapContent.this.getPositionsInRange(null, this.offset, this.length);
                GapContent.this.remove(this.offset, this.length);
            }
            catch (BadLocationException ex) {
                throw new CannotRedoException();
            }
        }
        
        public void undo() throws CannotUndoException {
            super.undo();
            try {
                GapContent.this.insertString(this.offset, this.string);
                if (this.posRefs != null) {
                    GapContent.this.updateUndoPositions(this.posRefs, this.offset, this.length);
                    this.posRefs = null;
                }
                this.string = null;
            }
            catch (BadLocationException ex) {
                throw new CannotUndoException();
            }
        }
    }
}
