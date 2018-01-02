// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.imagetools;

import com.idl.javaidl.JIDLInteger;
import com.idl.javaidl.JIDLString;
import java.util.NoSuchElementException;
import com.idl.javaidl.JIDLObjectI;
import com.idl.javaidl.JIDLException;
import com.idl.javaidl.JIDLNumber;
import java.util.LinkedList;
import com.itt.J2KViewer.util.Log;
import com.idl.javaidl.JIDLOutputListener;

public class NITFChipper extends NITFChipperInterface implements JIDLOutputListener, Runnable
{
    private static Log log;
    NITFChipperInterface chipper;
    String compression;
    String NITF_version;
    LinkedList queue;
    boolean processJobs;
    private static NITFChipper nitfChipper;
    private Thread myThread;
    private int STATE;
    public static int UNINITIALIZED;
    public static int INACTIVE;
    public static int ACTIVE;
    static /* synthetic */ Class class$com$itt$J2KViewer$imagetools$NITFChipper;
    
    private NITFChipper() {
        this.compression = "No Compression";
        this.NITF_version = " ";
        this.queue = new LinkedList();
        this.processJobs = true;
        NITFChipper.log.debug("Created instance of NITF Chipper!");
        this.STATE = NITFChipper.UNINITIALIZED;
    }
    
    public void init() {
        NITFChipper.log.debug("Initializing Chipper..");
        NITFChipper.nitfChipper.createObject();
        this.addIDLOutputListener((JIDLOutputListener)NITFChipper.nitfChipper);
        (this.myThread = new Thread(NITFChipper.nitfChipper)).start();
        this.STATE = NITFChipper.INACTIVE;
    }
    
    public void submitChip(final ChipParams chipParams) {
        try {
            NITFChipper.log.debug("Starting a chip! (active)  ");
            this.STATE = NITFChipper.ACTIVE;
            this.WRITECHIP(chipParams.IDLinputFile, chipParams.IDLoutputFile, (JIDLNumber)chipParams.IDLstartX, (JIDLNumber)chipParams.IDLendX, (JIDLNumber)chipParams.IDLstartY, (JIDLNumber)chipParams.IDLendY, chipParams.IDLcompression, chipParams.IDLNITFVersion, chipParams.IDLerror);
            NITFChipper.log.debug("Finished chipping! (inactive)");
            this.STATE = NITFChipper.INACTIVE;
        }
        catch (JIDLException ex) {
            NITFChipper.log.error("IDLERROR : " + chipParams.IDLerror);
            NITFChipper.log.error(ex.getMessage());
            System.out.println("IDLERROR : " + chipParams.IDLerror);
            ex.printStackTrace();
            this.STATE = NITFChipper.INACTIVE;
        }
    }
    
    public void addChipJob(final String s, final String s2, final int n, final int n2, final int n3, final int n4) {
        this.addChipJob(s, s2, n, n2, n3, n4, this.compression, this.NITF_version);
    }
    
    public void addChipJob(final String s, final String s2, final int n, final int n2, final int n3, final int n4, final String s3) {
        this.addChipJob(s, s2, n, n2, n3, n4, s3, this.NITF_version);
    }
    
    public void addChipJob(final String s, final String s2, final int n, final int n2, final int n3, final int n4, final String s3, final String s4) {
        this.queue.addLast(new ChipParams(s, s2, n, n2, n3, n4, s3, s4));
    }
    
    public static NITFChipper getChipperInstance() {
        if (NITFChipper.nitfChipper == null) {
            NITFChipper.nitfChipper = new NITFChipper();
        }
        return NITFChipper.nitfChipper;
    }
    
    public void IDLoutput(final JIDLObjectI jidlObjectI, final String s) {
        NITFChipper.log.debug("IDL Output : " + s);
    }
    
    public void destroyWrapper() {
        this.chipper.destroyObject();
    }
    
    public void stopChipping() {
        this.processJobs = false;
    }
    
    public int getState() {
        return this.STATE;
    }
    
    public void run() {
        while (this.processJobs) {
            ChipParams chipParams;
            try {
                chipParams = this.queue.removeFirst();
            }
            catch (NoSuchElementException ex) {
                chipParams = null;
            }
            if (chipParams != null) {
                this.submitChip(chipParams);
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        NITFChipper.log = new Log((NITFChipper.class$com$itt$J2KViewer$imagetools$NITFChipper == null) ? (NITFChipper.class$com$itt$J2KViewer$imagetools$NITFChipper = class$("com.itt.J2KViewer.imagetools.NITFChipper")) : NITFChipper.class$com$itt$J2KViewer$imagetools$NITFChipper);
        NITFChipper.UNINITIALIZED = 0;
        NITFChipper.INACTIVE = 1;
        NITFChipper.ACTIVE = 2;
    }
    
    private class ChipParams
    {
        JIDLString IDLinputFile;
        JIDLString IDLoutputFile;
        JIDLInteger IDLstartX;
        JIDLInteger IDLendX;
        JIDLInteger IDLstartY;
        JIDLInteger IDLendY;
        JIDLString IDLcompression;
        JIDLString IDLNITFVersion;
        JIDLString IDLerror;
        
        ChipParams(final String s, final String s2, final int n, final int n2, final int n3, final int n4, final String s3, final String s4) {
            this.IDLinputFile = new JIDLString(s);
            this.IDLoutputFile = new JIDLString(s2);
            this.IDLstartX = new JIDLInteger(n);
            this.IDLendX = new JIDLInteger(n3);
            this.IDLstartY = new JIDLInteger(n2);
            this.IDLendY = new JIDLInteger(n4);
            this.IDLcompression = new JIDLString(s3);
            this.IDLNITFVersion = new JIDLString(s4);
            this.IDLerror = new JIDLString("");
        }
    }
}
