// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import borland.jbcl.model.SingletonModelEvent;
import borland.jbcl.model.SingletonViewManager;
import borland.jbcl.model.SingletonModelMulticaster;
import borland.jbcl.model.WritableSingletonModel;
import borland.jbcl.model.SingletonModel;
import borland.jbcl.model.SingletonModelListener;
import java.awt.TextArea;

public class TextAreaView extends TextArea implements SingletonView, SingletonModelListener
{
    protected transient SingletonModel model;
    protected transient WritableSingletonModel writeModel;
    protected boolean readOnly;
    protected boolean postOnFocusLost;
    protected int preferredHeight;
    protected int preferredWidth;
    protected transient SingletonModelMulticaster modelMulticaster;
    
    public TextAreaView() {
        this.postOnFocusLost = true;
        this.modelMulticaster = new SingletonModelMulticaster();
        this.enableEvents(12L);
    }
    
    public SingletonModel getModel() {
        return this.model;
    }
    
    public void setModel(final SingletonModel sm) {
        if (this.model != null) {
            this.model.removeModelListener(this);
            this.model.removeModelListener(this.modelMulticaster);
        }
        this.model = sm;
        this.writeModel = ((this.model instanceof WritableSingletonModel) ? ((WritableSingletonModel)sm) : null);
        this.setEditable(!this.isReadOnly());
        if (this.model != null) {
            this.model.addModelListener(this);
            this.model.addModelListener(this.modelMulticaster);
            this.updateText();
        }
    }
    
    public WritableSingletonModel getWriteModel() {
        return this.readOnly ? null : this.writeModel;
    }
    
    public void addModelListener(final SingletonModelListener l) {
        this.modelMulticaster.add(l);
    }
    
    public void removeModelListener(final SingletonModelListener l) {
        this.modelMulticaster.remove(l);
    }
    
    public boolean isReadOnly() {
        return this.readOnly || this.writeModel == null;
    }
    
    public void setReadOnly(final boolean ro) {
        this.readOnly = ro;
        this.setEditable(!this.isReadOnly());
    }
    
    public void setViewManager(final SingletonViewManager svm) {
    }
    
    public SingletonViewManager getViewManager() {
        return null;
    }
    
    public boolean isPostOnFocusLost() {
        return this.postOnFocusLost;
    }
    
    public void setPostOnFocusLost(final boolean post) {
        this.postOnFocusLost = post;
    }
    
    protected void updateText() {
        if (this.model != null) {
            final Object o = this.model.get();
            final int selectionStart = this.getSelectionStart();
            final int selectionEnd = this.getSelectionEnd();
            super.setText((o != null) ? o.toString() : "");
            this.setSelectionStart(selectionStart);
            this.setSelectionEnd(selectionEnd);
        }
    }
    
    protected void setSuperText(final String text) {
        super.setText(text);
    }
    
    public void setText(final String text) {
        if (!this.isReadOnly() && this.writeModel.canSet(true)) {
            final String oldText = this.getText();
            if (oldText == null && text == null) {
                return;
            }
            if (oldText != null && text != null && oldText.equals(text)) {
                return;
            }
            this.writeModel.set(text);
        }
    }
    
    protected void postText() {
        if (this.writeModel != null && this.writeModel.canSet(true)) {
            final String text = super.getText();
            this.writeModel.set(text);
        }
    }
    
    public void modelContentChanged(final SingletonModelEvent e) {
        this.updateText();
    }
    
    protected void processKeyEvent(final KeyEvent e) {
        super.processKeyEvent(e);
        if (!this.isReadOnly()) {
            this.writeModel.canSet(true);
        }
        if (e.getID() == 401) {
            switch (e.getKeyCode()) {
                case 10: {
                    if (e.isControlDown()) {
                        this.postText();
                    }
                    break;
                }
                case 27: {
                    this.updateText();
                    break;
                }
            }
        }
    }
    
    protected void processFocusEvent(final FocusEvent e) {
        super.processFocusEvent(e);
        if (e.getID() == 1005) {
            final String text = super.getText();
            final Object obj = this.model.get();
            if (this.postOnFocusLost && !text.equals(obj)) {
                this.postText();
            }
        }
    }
    
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        if (this.preferredHeight > preferredSize.height) {
            preferredSize = new Dimension(preferredSize.width, this.preferredHeight);
        }
        if (this.preferredWidth > preferredSize.width) {
            preferredSize = new Dimension(this.preferredWidth, preferredSize.height);
        }
        return preferredSize;
    }
    
    public void setPreferredHeight(final int preferredHeight) {
        this.preferredHeight = preferredHeight;
    }
    
    public int getPreferredHeight() {
        return this.preferredHeight;
    }
    
    public void setPreferredWidth(final int preferredWidth) {
        this.preferredWidth = preferredWidth;
    }
    
    public int getPreferredWidth() {
        return this.preferredWidth;
    }
}
