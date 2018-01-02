// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

import java.util.Observable;
import java.util.LinkedList;
import java.util.Observer;

class SmartPdu implements Observer
{
    public static final int NoVbLimit = Integer.MAX_VALUE;
    private static int nextCtag;
    private int status;
    private String statusString;
    private boolean responsePending;
    private int vbLimit;
    private int ctag;
    private int vbIndex;
    private int vbAddIndex;
    private byte pduType;
    private SnmpSession ssn;
    private boolean synchronous;
    private varbind[] varbinds;
    private varbind[] responseVarbinds;
    private LinkedList lnrs;
    
    public SmartPdu(final SnmpSession snmpSession, final byte b, final int n) {
        throw new Error("Unresolved compilation problems: \n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tThe method createPdu(int) from the type SmartPdu refers to the missing type Pdu\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tGetPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n\tSetPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n\tGetNextPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n\tGetNextPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    public void addListener(final PduListener pduListener) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void removeListener(final PduListener pduListener) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void addVarbind(final String s) {
        throw new Error("Unresolved compilation problem: \n\tvarbind cannot be resolved to a type\n");
    }
    
    public void addVarbind(final varbind varbind) {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    public varbind[] getVarbinds() {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    public varbind[] getResponseVarbinds() {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    public byte getType() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public int getCtag() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public int getStatus() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public String getStatusString() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void setVbLimit(final int n) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public int getVbLimit() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void send() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void send(final boolean b) {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        throw new Error("Unresolved compilation problems: \n\tPdu cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    void updateResponseVarbinds(final Pdu pdu) {
        throw new Error("Unresolved compilation problems: \n\tPdu cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    private void sendNextVarbind() {
        throw new Error("Unresolved compilation problems: \n\tPdu cannot be resolved to a type\n\tThe method createPdu(int) from the type SmartPdu refers to the missing type Pdu\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    void send(final Pdu pdu) {
        throw new Error("Unresolved compilation problem: \n\tPdu cannot be resolved to a type\n");
    }
    
    void notifyListeners(final Pdu pdu) {
        throw new Error("Unresolved compilation problem: \n\tPdu cannot be resolved to a type\n");
    }
    
    private void notifyListeners() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private void notifyTooBigDetected() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private Pdu createPdu(final int n) {
        throw new Error("Unresolved compilation problems: \n\tPdu cannot be resolved to a type\n\tPdu cannot be resolved to a type\n\tGetPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n\tSetPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n\tGetNextPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n\tGetNextPdu_vec cannot be resolved to a type\n\tAbstractSnmpContext cannot be resolved to a type\n");
    }
    
    void setTimeoutAndRetries(final Pdu pdu) {
        throw new Error("Unresolved compilation problem: \n\tPdu cannot be resolved to a type\n");
    }
    
    private static synchronized int getNextCtag() {
        throw new Error("Unresolved compilation problem: \n");
    }
}
