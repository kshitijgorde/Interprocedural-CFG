// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.Component;
import java.awt.datatransfer.Clipboard;

public final class ClipboardPoker
{
    private static Clipboard clipboard;
    
    public static String getClip(final Component owner) {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            try {
                security.checkSystemClipboardAccess();
            }
            catch (SecurityException e2) {
                System.err.println("OS refused permission to look at the clipboard.");
                return null;
            }
        }
        initClipboardHook(owner);
        if (ClipboardPoker.clipboard == null) {
            System.err.println("Could not establish a link with the clipboard.");
            return null;
        }
        try {
            try {
                final Transferable contents = ClipboardPoker.clipboard.getContents(owner);
                if (contents == null) {
                    System.err.println("clipboard empty");
                    return null;
                }
                if (contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    final String s = (String)contents.getTransferData(DataFlavor.stringFlavor);
                    return (s == null || s.length() == 0) ? null : s;
                }
            }
            catch (UnsupportedFlavorException e3) {
                System.err.println("Clipboard does not contain text.");
                Toolkit.getDefaultToolkit().beep();
                return null;
            }
        }
        catch (Exception e) {
            System.err.println("Problem accessing the clipboard " + e.getMessage());
            Toolkit.getDefaultToolkit().beep();
            return null;
        }
        return null;
    }
    
    public static void setClip(String s, final Component owner) {
        initClipboardHook(owner);
        if (ClipboardPoker.clipboard == null) {
            return;
        }
        if (s == null) {
            s = "";
        }
        final StringSelection contents = new StringSelection(s);
        if (!(owner instanceof ClipboardOwner)) {
            throw new IllegalArgumentException(owner.getClass().toString() + " owner of clipboard must implement ClipboardOwner.");
        }
        ClipboardPoker.clipboard.setContents(contents, (ClipboardOwner)owner);
    }
    
    private static void initClipboardHook(final Component owner) {
        if (ClipboardPoker.clipboard == null) {
            final Toolkit t = owner.getToolkit();
            if (t != null) {
                ClipboardPoker.clipboard = t.getSystemClipboard();
            }
        }
    }
}
