// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import borland.jbcl.util.ExceptionChain;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.awt.event.WindowEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import borland.jbcl.model.SingletonViewManager;
import borland.jbcl.model.ItemEditor;
import borland.jbcl.model.ItemPainter;
import borland.jbcl.model.BasicViewManager;
import java.awt.Insets;
import borland.jbcl.view.WrappedTextItemPainter;
import borland.jbcl.model.SingletonModel;
import borland.jbcl.model.BasicSingletonContainer;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.Frame;
import borland.jbcl.util.ChainedException;
import java.util.Vector;
import java.awt.Panel;
import java.awt.event.ActionListener;

public class ExceptionDialog extends ButtonDialog implements ActionListener
{
    private FieldControl message;
    private TextAreaControl details;
    private Panel centerPanel;
    private boolean showDetails;
    private int detailHeight;
    private int position;
    private Vector exceptionVector;
    private Exception ex;
    private static int showCount;
    
    private static int makeButtonSet(final Exception ex) {
        if (ex instanceof ChainedException && ((ChainedException)ex).getExceptionChain() != null) {
            return 897;
        }
        return 513;
    }
    
    public ExceptionDialog(final Frame frame, final String title, final Exception ex, final boolean modal) {
        super(frame, title, modal, null, null, ButtonDialog.buttonSetToButtonDescriptors(makeButtonSet(ex)));
        this.message = new FieldControl();
        this.details = new TextAreaControl();
        this.centerPanel = new Panel();
        this.showDetails = false;
        this.detailHeight = 200;
        this.ex = ex;
        this.details.setEditable(false);
        this.details.setBackground(SystemColor.window);
        this.details.setPreferredHeight(200);
        this.message.setPreferredHeight(100);
        this.message.setPreferredWidth(400);
        this.message.setModel(new BasicSingletonContainer());
        this.message.setAlignment(1);
        this.message.setViewManager(new BasicViewManager(new WrappedTextItemPainter(null, 1, 0), null));
        this.makeExceptionList(ex);
        this.setLayout(new BorderLayout());
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(this.message, "Center");
        this.centerPanel.add(super.buttonPanel, "South");
        this.add(this.centerPanel, "Center");
        this.setBackground(SystemColor.control);
        this.setEnterOK(true);
        this.displayException(0);
        ++ExceptionDialog.showCount;
    }
    
    public ExceptionDialog(final Frame frame, final String title, final Exception ex) {
        this(frame, title, ex, false);
    }
    
    protected void processActionEvent(final ActionEvent e) {
        super.processActionEvent(e);
        if (super.result != null) {
            if (super.result.closeDialog) {
                --ExceptionDialog.showCount;
            }
            if (super.result.command.equals("next") && this.position < this.exceptionVector.size() - 1) {
                this.displayException(++this.position);
            }
            if (super.result.command.equals("previous") && this.position > 0) {
                this.displayException(--this.position);
            }
            if (super.result.command.equals("details")) {
                if (!this.showDetails) {
                    final Dimension dim = this.getSize();
                    final Rectangle rect = this.centerPanel.getBounds();
                    this.remove(this.centerPanel);
                    this.add(this.centerPanel, "North");
                    this.add(this.details, "Center");
                    this.details.setBounds(rect.x, rect.y + rect.height, rect.width, this.detailHeight);
                    this.setSize(dim.width, dim.height + this.detailHeight);
                }
                else {
                    final Dimension dim = this.getSize();
                    this.detailHeight = this.details.getSize().height;
                    this.remove(this.details);
                    this.remove(this.centerPanel);
                    this.add(this.centerPanel, "Center");
                    this.setSize(dim.width, dim.height - this.detailHeight);
                }
                this.showDetails = !this.showDetails;
            }
        }
    }
    
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == 201) {
            this.processActionEvent(new ActionEvent(this, 1001, "ok"));
        }
    }
    
    private void displayException(final int pos) {
        this.enableButton("previous", this.position > 0);
        this.enableButton("next", this.position < this.exceptionVector.size() - 1);
        final Exception ex = this.exceptionVector.elementAt(pos);
        String messageString = ex.getMessage();
        if (messageString == null || messageString.length() < 1) {
            messageString = ex.getClass().getName();
        }
        this.message.setText(messageString);
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream(512);
        final PrintStream printStream = new PrintStream(byteStream);
        ex.printStackTrace(printStream);
        printStream.flush();
        this.details.setText(byteStream.toString());
    }
    
    private void makeExceptionList(Exception ex) {
        (this.exceptionVector = new Vector()).addElement(ex);
        if (ex instanceof ChainedException) {
            for (ExceptionChain chain = ((ChainedException)ex).getExceptionChain(); chain != null; chain = chain.getNext()) {
                ex = chain.getException();
                this.exceptionVector.addElement(ex);
            }
        }
        this.position = 0;
    }
    
    public static int getShowCount() {
        return ExceptionDialog.showCount;
    }
}
