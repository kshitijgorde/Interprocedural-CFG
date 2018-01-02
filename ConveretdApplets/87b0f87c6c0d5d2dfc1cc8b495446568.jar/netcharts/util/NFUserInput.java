// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.awt.Component;
import java.util.Enumeration;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.Frame;

public class NFUserInput extends Frame implements NFUserInputObserver
{
    protected Vector fields;
    protected Vector buttons;
    protected NFUserInputForm form;
    protected String userResponse;
    protected String activateButton;
    private Thread a;
    
    public NFUserInput() {
        super("User Input");
        this.fields = new Vector();
        this.buttons = new Vector();
        this.form = null;
        this.userResponse = null;
        this.activateButton = null;
        this.a = null;
        this.setLayout(new BorderLayout());
    }
    
    public void setFieldsActivateButton(final String activateButton) {
        this.activateButton = activateButton;
    }
    
    public void addButton(final String s) {
        this.buttons.addElement(s);
    }
    
    public void removeButton(final String s) {
        this.buttons.removeElement(s);
    }
    
    public void addField(final String id, final String label, final String mode) {
        final NFUserInputField nfUserInputField = new NFUserInputField();
        nfUserInputField.id = id;
        nfUserInputField.label = label;
        nfUserInputField.mode = mode;
        this.addField(nfUserInputField);
    }
    
    public void addField(final NFUserInputField nfUserInputField) {
        this.fields.addElement(nfUserInputField);
    }
    
    public void removeField(final NFUserInputField nfUserInputField) {
        this.fields.removeElement(nfUserInputField);
    }
    
    public void removeField(final String s) {
        final NFUserInputField field = this.getField(s);
        if (field != null) {
            this.fields.removeElement(field);
        }
    }
    
    public NFUserInputField getField(final String s) {
        final Enumeration<NFUserInputField> elements = this.fields.elements();
        while (elements.hasMoreElements()) {
            final NFUserInputField nfUserInputField = elements.nextElement();
            if (nfUserInputField.id.equals(s)) {
                return nfUserInputField;
            }
        }
        return null;
    }
    
    public String getFieldValue(final String s) {
        final NFUserInputField field = this.getField(s);
        if (field == null) {
            return null;
        }
        return field.value;
    }
    
    public void setFieldValue(final String s, final String value) {
        final NFUserInputField field = this.getField(s);
        if (field == null) {
            return;
        }
        field.value = value;
    }
    
    public void buildForm(final String s) throws Exception {
        Object instance;
        try {
            instance = Class.forName(s).newInstance();
        }
        catch (Exception ex) {
            throw new Exception("Unable to load class " + s);
        }
        if (!(instance instanceof NFUserInputForm)) {
            throw new Exception(s + " does not implement NFUserInputForm");
        }
        if (!(instance instanceof Component)) {
            throw new Exception(s + " does not extend Component");
        }
        (this.form = (NFUserInputForm)instance).userInputFormCreate(this.fields, this.buttons);
        if (this.activateButton != null) {
            this.form.setFieldsActivateButton(this.activateButton);
        }
        this.form.userInputFormAddObserver(this);
        this.add("Center", (Component)this.form);
    }
    
    public void setHeader(final String s) {
        if (this.form != null) {
            this.form.userInputFormSetHeader(s);
            this.pack();
        }
    }
    
    public void show() {
        if (this.form != null) {
            this.form.userInputFormSetValues(this.fields);
        }
        NFUtil.centerWindow(null, this);
        super.show();
    }
    
    public String showAndWait() {
        this.userResponse = "Cancel";
        this.show();
        (this.a = Thread.currentThread()).suspend();
        this.a = null;
        return this.userResponse;
    }
    
    public boolean threadWaiting() {
        return this.a != null;
    }
    
    public void cancelShowAndWait() {
        if (this.a != null) {
            this.a.resume();
        }
        this.hide();
    }
    
    public void buttonPressed(final Object o, final String userResponse) {
        this.userResponse = userResponse;
        this.hide();
        if (this.form != null && this.userResponse.equalsIgnoreCase("OK")) {
            this.form.userInputFormGetValues(this.fields);
        }
        if (this.a != null) {
            this.a.resume();
        }
    }
}
