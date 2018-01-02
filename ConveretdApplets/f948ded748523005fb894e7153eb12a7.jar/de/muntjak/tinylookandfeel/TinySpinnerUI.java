// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Container;
import java.awt.KeyboardFocusManager;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.text.Format;
import javax.swing.text.InternationalFormatter;
import java.util.Map;
import java.text.AttributedCharacterIterator;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.SpinnerDateModel;
import java.awt.event.ActionEvent;
import java.awt.AWTEvent;
import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.AbstractAction;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.plaf.ButtonUI;
import java.awt.Component;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class TinySpinnerUI extends BasicSpinnerUI
{
    private static final ArrowButtonHandler nextButtonHandler;
    private static final ArrowButtonHandler previousButtonHandler;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinySpinnerUI();
    }
    
    protected Component createPreviousButton() {
        final SpecialUIButton specialUIButton = new SpecialUIButton(new TinySpinnerButtonUI(5));
        specialUIButton.putClientProperty("isSpinnerButton", Boolean.TRUE);
        specialUIButton.setFocusable(false);
        specialUIButton.addActionListener(TinySpinnerUI.previousButtonHandler);
        specialUIButton.addMouseListener(TinySpinnerUI.previousButtonHandler);
        return specialUIButton;
    }
    
    protected Component createNextButton() {
        final SpecialUIButton specialUIButton = new SpecialUIButton(new TinySpinnerButtonUI(1));
        specialUIButton.putClientProperty("isSpinnerButton", Boolean.TRUE);
        specialUIButton.setFocusable(false);
        specialUIButton.addActionListener(TinySpinnerUI.nextButtonHandler);
        specialUIButton.addMouseListener(TinySpinnerUI.nextButtonHandler);
        return specialUIButton;
    }
    
    static {
        nextButtonHandler = new ArrowButtonHandler("increment", true);
        previousButtonHandler = new ArrowButtonHandler("decrement", false);
    }
    
    private static class ArrowButtonHandler extends AbstractAction implements MouseListener
    {
        final Timer autoRepeatTimer;
        final boolean isNext;
        JSpinner spinner;
        
        ArrowButtonHandler(final String s, final boolean isNext) {
            super(s);
            this.spinner = null;
            this.isNext = isNext;
            (this.autoRepeatTimer = new Timer(20, this)).setInitialDelay(300);
        }
        
        private JSpinner eventToSpinner(final AWTEvent awtEvent) {
            Object o;
            for (o = awtEvent.getSource(); o instanceof Component && !(o instanceof JSpinner); o = ((Component)o).getParent()) {}
            return (o instanceof JSpinner) ? ((JSpinner)o) : null;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            JSpinner spinner = this.spinner;
            if (!(actionEvent.getSource() instanceof Timer)) {
                spinner = this.eventToSpinner(actionEvent);
            }
            if (spinner != null) {
                try {
                    final int calendarField = this.getCalendarField(spinner);
                    spinner.commitEdit();
                    if (calendarField != -1) {
                        ((SpinnerDateModel)spinner.getModel()).setCalendarField(calendarField);
                    }
                    final Object value = this.isNext ? spinner.getNextValue() : spinner.getPreviousValue();
                    if (value != null) {
                        spinner.setValue(value);
                        this.select(spinner);
                    }
                }
                catch (IllegalArgumentException ex) {
                    UIManager.getLookAndFeel().provideErrorFeedback(spinner);
                }
                catch (ParseException ex2) {
                    UIManager.getLookAndFeel().provideErrorFeedback(spinner);
                }
            }
        }
        
        private void select(final JSpinner spinner) {
            final JComponent editor = spinner.getEditor();
            if (editor instanceof JSpinner.DateEditor) {
                final JSpinner.DateEditor dateEditor = (JSpinner.DateEditor)editor;
                final JFormattedTextField textField = dateEditor.getTextField();
                final SimpleDateFormat format = dateEditor.getFormat();
                final Object value;
                if (format != null && (value = spinner.getValue()) != null) {
                    final DateFormat.Field ofCalendarField = DateFormat.Field.ofCalendarField(dateEditor.getModel().getCalendarField());
                    if (ofCalendarField != null) {
                        try {
                            final AttributedCharacterIterator formatToCharacterIterator = format.formatToCharacterIterator(value);
                            if (!this.select(textField, formatToCharacterIterator, ofCalendarField) && ofCalendarField == DateFormat.Field.HOUR0) {
                                this.select(textField, formatToCharacterIterator, DateFormat.Field.HOUR1);
                            }
                        }
                        catch (IllegalArgumentException ex) {}
                    }
                }
            }
        }
        
        private boolean select(final JFormattedTextField formattedTextField, final AttributedCharacterIterator attributedCharacterIterator, final DateFormat.Field field) {
            final int length = formattedTextField.getDocument().getLength();
            attributedCharacterIterator.first();
            do {
                final Map<AttributedCharacterIterator.Attribute, Object> attributes = attributedCharacterIterator.getAttributes();
                if (attributes != null && attributes.containsKey(field)) {
                    final int runStart = attributedCharacterIterator.getRunStart(field);
                    final int runLimit = attributedCharacterIterator.getRunLimit(field);
                    if (runStart != -1 && runLimit != -1 && runStart <= length && runLimit <= length) {
                        formattedTextField.select(runStart, runLimit);
                    }
                    return true;
                }
            } while (attributedCharacterIterator.next() != '\uffff');
            return false;
        }
        
        private int getCalendarField(final JSpinner spinner) {
            final JComponent editor = spinner.getEditor();
            if (editor instanceof JSpinner.DateEditor) {
                final JFormattedTextField textField = ((JSpinner.DateEditor)editor).getTextField();
                final int selectionStart = textField.getSelectionStart();
                final JFormattedTextField.AbstractFormatter formatter = textField.getFormatter();
                if (formatter instanceof InternationalFormatter) {
                    final Format.Field[] fields = ((InternationalFormatter)formatter).getFields(selectionStart);
                    for (int i = 0; i < fields.length; ++i) {
                        if (fields[i] instanceof DateFormat.Field) {
                            int calendarField;
                            if (fields[i] == DateFormat.Field.HOUR1) {
                                calendarField = 10;
                            }
                            else {
                                calendarField = ((DateFormat.Field)fields[i]).getCalendarField();
                            }
                            if (calendarField != -1) {
                                return calendarField;
                            }
                        }
                    }
                }
            }
            return -1;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            if (SwingUtilities.isLeftMouseButton(mouseEvent) && mouseEvent.getComponent().isEnabled()) {
                this.spinner = this.eventToSpinner(mouseEvent);
                this.autoRepeatTimer.start();
                this.focusSpinnerIfNecessary();
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.autoRepeatTimer.stop();
            this.spinner = null;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
        
        private void focusSpinnerIfNecessary() {
            final Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
            if (this.spinner.isRequestFocusEnabled() && (focusOwner == null || !SwingUtilities.isDescendingFrom(focusOwner, this.spinner))) {
                Container container = this.spinner;
                if (!container.isFocusCycleRoot()) {
                    container = container.getFocusCycleRootAncestor();
                }
                if (container != null) {
                    final Component componentAfter = container.getFocusTraversalPolicy().getComponentAfter(container, this.spinner);
                    if (componentAfter != null && SwingUtilities.isDescendingFrom(componentAfter, this.spinner)) {
                        componentAfter.requestFocus();
                    }
                }
            }
        }
    }
}
