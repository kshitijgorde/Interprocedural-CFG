import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.Point;
import java.util.List;
import java.util.Arrays;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.DragGestureEvent;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DragSource;
import java.awt.Component;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragGestureListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaTransfer implements DragGestureListener, DragSourceListener, DropTargetListener, ClipboardOwner
{
    private Formula formula;
    private Component comp;
    private DragSource dragSource;
    private DropTarget dropTarget;
    private DragGestureRecognizer dragGestureRecognizer;
    private boolean dragFromComponent;
    private boolean dropOnComponent;
    private boolean sourceCanMove;
    private AbstractSelection currentSel;
    private int performAction;
    protected static final DataFlavor DefaultCopyDrop;
    protected static final DataFlavor DefaultCopyNoDrop;
    protected static final DataFlavor DefaultMoveDrop;
    protected static final DataFlavor InsertString;
    protected static final DataFlavor PosicioCaret;
    private String lastMML;
    private static Transferable g_data;
    private final int dropActionAccepted = 1073741827;
    private AbstractSelection clipSel;
    private boolean clipboardOwned;
    static Class acceptDrag;
    static Class acceptDrop;
    
    public FormulaTransfer() {
        this.dragFromComponent = false;
        this.dropOnComponent = false;
        this.sourceCanMove = false;
        this.currentSel = null;
        this.performAction = 0;
        this.lastMML = null;
        this.clipSel = null;
        this.clipboardOwned = false;
    }
    
    protected final void I(final Formula formula) {
        this.comp = formula.getComponent();
        if (this.comp == null) {
            throw new NullPointerException("component");
        }
        if (this.formula != null) {
            throw new IllegalStateException("Already registered");
        }
        this.formula = formula;
        this.dragSource = DragSource.getDefaultDragSource();
        this.dragGestureRecognizer = this.dragSource.createDefaultDragGestureRecognizer(this.comp, 3, this);
        this.dropTarget = new DropTarget(this.comp, 3, this, true);
    }
    
    protected final void I() {
        if (this.formula == null) {
            return;
        }
        this.dropTarget.setActive(false);
        this.dropTarget.removeDropTargetListener(this);
        this.dragSource.removeDragSourceListener(this);
        this.formula = null;
        this.comp = null;
        if (this.dragGestureRecognizer == null) {
            return;
        }
        this.dragGestureRecognizer.removeDragGestureListener(this);
        this.dragGestureRecognizer.resetRecognizer();
    }
    
    private Transferable createData(final AbstractSelection abstractSelection) {
        this.lastMML = XMLBoxUtils.toValidMathML(abstractSelection, false);
        if (this.lastMML != null) {
            final TransData transData = new TransData();
            transData.add(FormulaTransfer.DefaultCopyDrop, this.lastMML);
            return FormulaTransfer.g_data = this.InsertString(transData);
        }
        return null;
    }
    
    private Transferable createTextData(final AbstractSelection abstractSelection) {
        final String box2String = this.formula.box2String(abstractSelection);
        if (box2String != null) {
            final TransData transData = new TransData();
            transData.add(DataFlavor.stringFlavor, box2String);
            return transData;
        }
        return null;
    }
    
    private Transferable createMoodleMathmlData(final AbstractSelection abstractSelection) {
        final String validMathML = XMLBoxUtils.toValidMathML(abstractSelection, false);
        this.lastMML = validMathML;
        final String transformMoodle = this.transformMoodle(validMathML);
        if (transformMoodle != null) {
            final TransData transData = new TransData();
            transData.add(DataFlavor.stringFlavor, transformMoodle);
            return transData;
        }
        return null;
    }
    
    protected final String DefaultCopyDrop(final Transferable transferable, final boolean b) {
        String s = null;
        if (transferable.isDataFlavorSupported(FormulaTransfer.DefaultCopyDrop)) {
            try {
                s = (String)this.getTransferData(transferable, FormulaTransfer.DefaultCopyDrop);
            }
            catch (Exception ex) {}
        }
        if (s == null && transferable.isDataFlavorSupported(FormulaTransfer.DefaultCopyNoDrop)) {
            try {
                s = this.DefaultCopyNoDrop((URL)this.getTransferData(transferable, FormulaTransfer.DefaultCopyNoDrop));
            }
            catch (Exception ex2) {}
        }
        if (s == null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                s = this.DefaultMoveDrop((String)this.getTransferData(transferable, DataFlavor.stringFlavor));
            }
            catch (Exception ex3) {}
        }
        return s;
    }
    
    protected final String DefaultCopyNoDrop(final URL url) {
        try {
            final URLConnection openConnection = url.openConnection();
            final InputStream inputStream = openConnection.getInputStream();
            String contentEncoding = openConnection.getContentEncoding();
            if (contentEncoding == null) {
                contentEncoding = "UTF-8";
            }
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, contentEncoding);
            final StringBuffer sb = new StringBuffer();
            final char[] array = new char[256];
            int read;
            while ((read = inputStreamReader.read(array, 0, array.length)) != -1) {
                sb.append(array, 0, read);
            }
            return sb.toString();
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    protected final String DefaultMoveDrop(final String s) {
        if (s == null) {
            return null;
        }
        try {
            final String defaultCopyNoDrop = this.DefaultCopyNoDrop(new URL(s));
            if (defaultCopyNoDrop == null) {
                return s;
            }
            return defaultCopyNoDrop;
        }
        catch (MalformedURLException ex) {
            return s;
        }
    }
    
    protected final Transferable InsertString(final Transferable transferable) {
        try {
            final TransData writable = TransData.makeWritable(transferable);
            writable.add(DataFlavor.stringFlavor, this.getTransferData(transferable, FormulaTransfer.DefaultCopyDrop));
            return writable;
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("must support MathML MIME type");
        }
    }
    
    public final void dragGestureRecognized(final DragGestureEvent dragGestureEvent) {
        if ((dragGestureEvent.getDragAction() & 0x3) == 0x0) {
            return;
        }
        Transferable data = null;
        if ((this.formula.E & 0x20000) != 0x0) {
            this.currentSel = this.formula.getSelectedArea();
            if (this.currentSel != null) {
                data = this.createData(this.currentSel);
            }
        }
        if (data == null) {
            return;
        }
        try {
            this.performAction = 0;
            this.dropOnComponent = false;
            this.sourceCanMove = this.formula.isEditable();
            dragGestureEvent.startDrag(DragSource.DefaultCopyNoDrop, data, this);
        }
        catch (InvalidDnDOperationException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void dragDropEnd(final DragSourceDropEvent dragSourceDropEvent) {
        FormulaTransfer.g_data = null;
        this.dragFromComponent = false;
        if (dragSourceDropEvent.getDropSuccess()) {
            if (!this.dropOnComponent) {
                if (this.performAction == 2) {
                    this.formula.unselectArea();
                    this.formula.setCaret(this.delete(this.currentSel));
                }
            }
        }
        this.formula.repaint();
    }
    
    public final void dragEnter(final DragSourceDragEvent dragSourceDragEvent) {
        this.updateActionAndCursor(dragSourceDragEvent);
        this.dragFromComponent = true;
    }
    
    public final void dragExit(final DragSourceEvent dragSourceEvent) {
        dragSourceEvent.getDragSourceContext().setCursor(DragSource.DefaultCopyNoDrop);
    }
    
    public final void dragOver(final DragSourceDragEvent dragSourceDragEvent) {
        this.updateActionAndCursor(dragSourceDragEvent);
    }
    
    public final void dropActionChanged(final DragSourceDragEvent dragSourceDragEvent) {
        this.updateActionAndCursor(dragSourceDragEvent);
    }
    
    private void updateActionAndCursor(final DragSourceDragEvent dragSourceDragEvent) {
        final DragSourceContext dragSourceContext = dragSourceDragEvent.getDragSourceContext();
        final int targetActions = dragSourceDragEvent.getTargetActions();
        final int userAction = dragSourceDragEvent.getUserAction();
        if ((userAction & 0x3) == 0x0 || (targetActions & 0x3) == 0x0) {
            this.performAction = 0;
        }
        if ((dragSourceDragEvent.getGestureModifiers() & 0xB) == 0x0) {
            if (this.dropOnComponent && this.sourceCanMove) {
                this.performAction = 2;
            }
            else {
                this.performAction = 1;
            }
        }
        else if (!this.sourceCanMove) {
            this.performAction = 1;
        }
        else {
            this.performAction = userAction;
        }
        if (this.performAction == 2) {
            dragSourceContext.setCursor(DragSource.DefaultMoveDrop);
        }
        else if (this.performAction == 1) {
            dragSourceContext.setCursor(DragSource.DefaultCopyDrop);
        }
        else {
            dragSourceContext.setCursor(DragSource.DefaultCopyNoDrop);
        }
    }
    
    protected final boolean PosicioCaret(final DataFlavor[] array, final boolean b) {
        final List<DataFlavor> list = Arrays.asList(array);
        return list.contains(FormulaTransfer.DefaultCopyDrop) || list.contains(DataFlavor.stringFlavor) || list.contains(FormulaTransfer.DefaultCopyNoDrop);
    }
    
    protected final boolean acceptDrag(final Point caret, final boolean b) {
        if (!this.formula.isEditable()) {
            return false;
        }
        if (this.formula.isEditablePosition(caret)) {
            this.formula.setCaret(caret);
            return true;
        }
        return false;
    }
    
    private boolean isDropAccepted(final DropTargetDragEvent dropTargetDragEvent, final boolean b) {
        return (dropTargetDragEvent.getDropAction() & dropTargetDragEvent.getSourceActions() & 0x40000003) != 0x0 && this.acceptDrag(dropTargetDragEvent.getLocation(), b) && this.PosicioCaret(dropTargetDragEvent.getCurrentDataFlavors(), b);
    }
    
    public final void dragEnter(final DropTargetDragEvent dropTargetDragEvent) {
        this.dropOnComponent = true;
        if (this.isDropAccepted(dropTargetDragEvent, this.dragFromComponent)) {
            dropTargetDragEvent.acceptDrag(dropTargetDragEvent.getDropAction());
        }
        else {
            dropTargetDragEvent.rejectDrag();
        }
    }
    
    public final void dragExit(final DropTargetEvent dropTargetEvent) {
        this.dropOnComponent = false;
    }
    
    public final void dragOver(final DropTargetDragEvent dropTargetDragEvent) {
        if (this.isDropAccepted(dropTargetDragEvent, this.dragFromComponent)) {
            dropTargetDragEvent.acceptDrag(dropTargetDragEvent.getDropAction());
        }
        else {
            dropTargetDragEvent.rejectDrag();
        }
    }
    
    public final void drop(final DropTargetDropEvent dropTargetDropEvent) {
        if ((dropTargetDropEvent.getDropAction() & dropTargetDropEvent.getSourceActions() & 0x40000003) == 0x0 || !this.acceptDrag(dropTargetDropEvent.getLocation(), this.dragFromComponent)) {
            dropTargetDropEvent.rejectDrop();
            return;
        }
        dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
        boolean b;
        if (this.dragFromComponent) {
            b = (this.performAction == 2);
        }
        else {
            b = ((dropTargetDropEvent.getDropAction() & 0x2) != 0x0);
        }
        dropTargetDropEvent.dropComplete(this.acceptDrop(dropTargetDropEvent.getLocation(), dropTargetDropEvent.getTransferable(), this.dragFromComponent, b));
    }
    
    public final void dropActionChanged(final DropTargetDragEvent dropTargetDragEvent) {
        if (this.isDropAccepted(dropTargetDragEvent, this.dragFromComponent)) {
            dropTargetDragEvent.acceptDrag(dropTargetDragEvent.getDropAction());
        }
        else {
            dropTargetDragEvent.rejectDrag();
        }
    }
    
    protected final boolean acceptDrop(final Point point, final Transferable transferable, final boolean b, final boolean b2) {
        if (!(this.formula instanceof FormulaEditor)) {
            return false;
        }
        final FormulaEditor formulaEditor = (FormulaEditor)this.formula;
        final String defaultCopyDrop = this.DefaultCopyDrop(transferable, b);
        if (defaultCopyDrop == null) {
            return false;
        }
        if (!b) {
            formulaEditor.unselectArea();
            formulaEditor.insertString(defaultCopyDrop);
            return true;
        }
        if (this.isCaretOnSelection(this.currentSel)) {
            return false;
        }
        if (b2) {
            final String validMathML = XMLBoxUtils.toValidMathML(this.currentSel, true);
            formulaEditor.unselectArea();
            if (this.currentSel.getCurpos().compareTo(formulaEditor.curpos) >= 0) {
                this.delete(this.currentSel);
                formulaEditor.setCaret(point);
                formulaEditor.InsertString(validMathML);
            }
            else {
                formulaEditor.InsertString(validMathML);
                this.delete(this.currentSel);
                formulaEditor.setCaret(point);
            }
            FormulaEditor.I(formulaEditor.N);
            this.comp.invalidate();
            this.comp.repaint();
        }
        else {
            formulaEditor.unselectArea();
            formulaEditor.insertString(defaultCopyDrop);
        }
        return true;
    }
    
    public final boolean cut() {
        if (this.copy() && this.formula.isEditable()) {
            this.formula.unselectArea();
            this.formula.setCaret(this.delete(this.clipSel));
            this.comp.repaint();
            return true;
        }
        return false;
    }
    
    public final boolean copy() {
        if (this.formula.B != null) {
            this.clipSel = this.formula.getSelectedArea();
            final Transferable data = this.createData(this.clipSel);
            if (data != null) {
                try {
                    TransData.clipboard.setContents(data, this);
                    return this.clipboardOwned = true;
                }
                catch (IllegalStateException ex) {}
            }
            return false;
        }
        return false;
    }
    
    public final boolean copyText(final int n) {
        if (this.formula.B != null) {
            this.clipSel = this.formula.getSelectedArea();
            Transferable transferable;
            if (n == 1) {
                transferable = this.createTextData(this.clipSel);
            }
            else if (n == 2) {
                transferable = this.createMoodleMathmlData(this.clipSel);
            }
            else {
                transferable = null;
            }
            if (transferable != null) {
                try {
                    TransData.clipboard.setContents(transferable, this);
                    return this.clipboardOwned = true;
                }
                catch (IllegalStateException ex) {}
            }
            return false;
        }
        return false;
    }
    
    public final boolean paste() {
        if (!(this.formula instanceof FormulaEditor)) {
            return false;
        }
        final FormulaEditor formulaEditor = (FormulaEditor)this.formula;
        Transferable contents = null;
        try {
            contents = TransData.clipboard.getContents(null);
        }
        catch (IllegalStateException ex) {}
        if (contents != null) {
            final String defaultCopyDrop = this.DefaultCopyDrop(contents, this.clipboardOwned);
            if (defaultCopyDrop != null) {
                formulaEditor.insertString(defaultCopyDrop);
                return true;
            }
        }
        return false;
    }
    
    public final void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
        this.clipboardOwned = false;
    }
    
    private boolean isCaretOnSelection(final AbstractSelection abstractSelection) {
        if (abstractSelection == null) {
            return false;
        }
        final Point posicioCaret = this.formula.curpos.c.PosicioCaret(this.formula.curpos.x, this.formula);
        final Rectangles rectangles = abstractSelection.getRectangles(this.formula);
        if (!rectangles.contains(posicioCaret)) {
            final Point point = posicioCaret;
            point.x -= 2;
            return rectangles.contains(posicioCaret);
        }
        return true;
    }
    
    private BoxPosition delete(final AbstractSelection abstractSelection) {
        if (this.formula instanceof FormulaEditor) {
            return ((FormulaEditor)this.formula).suprSelection(abstractSelection);
        }
        return abstractSelection.getCurpos();
    }
    
    private String transformMoodle(String s) {
        s = Utils.substitute(s, "<", "«");
        s = Utils.substitute(s, ">", "»");
        s = Utils.substitute(s, "\"", "¨");
        s = Utils.substitute(s, "&", "§");
        s = Utils.substitute(s, ":", "\\:");
        s = Utils.substitute(s, "{", "\\{");
        s = Utils.substitute(s, "}", "\\}");
        s = Utils.substitute(s, "=", "\\=");
        s = Utils.substitute(s, "#", "\\#");
        return s;
    }
    
    private Object getTransferData(final Transferable transferable, final DataFlavor dataFlavor) {
        if (FormulaTransfer.g_data != null) {
            return FormulaTransfer.g_data.getTransferData(dataFlavor);
        }
        return transferable.getTransferData(dataFlavor);
    }
    
    private static final Class add(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        DefaultCopyDrop = TransData.newDataFlavor("application/mathml+xml", (FormulaTransfer.acceptDrag == null) ? (FormulaTransfer.acceptDrag = add("java.lang.String")) : FormulaTransfer.acceptDrag, "");
        DefaultCopyNoDrop = TransData.newDataFlavor("application/x-java-url", (FormulaTransfer.acceptDrop == null) ? (FormulaTransfer.acceptDrop = add("java.net.URL")) : FormulaTransfer.acceptDrop, "java url");
        DefaultMoveDrop = TransData.newDataFlavor("application/x-xml-id", (FormulaTransfer.acceptDrag == null) ? (FormulaTransfer.acceptDrag = add("java.lang.String")) : FormulaTransfer.acceptDrag, "wiris formula id");
        InsertString = TransData.newDataFlavor("application/x-wiris-internid", (FormulaTransfer.acceptDrag == null) ? (FormulaTransfer.acceptDrag = add("java.lang.String")) : FormulaTransfer.acceptDrag, "wiris internal formula id");
        PosicioCaret = TransData.newDataFlavor("text/plain", (FormulaTransfer.acceptDrag == null) ? (FormulaTransfer.acceptDrag = add("java.lang.String")) : FormulaTransfer.acceptDrag, "Plain text");
    }
}
