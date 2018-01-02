// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

import java.util.BitSet;

public class MibObject implements PduListener
{
    public static final byte NoEncoding = 0;
    public static final byte AsnInteger = 1;
    public static final byte AsnOctets = 2;
    public static final byte AsnObjectId = 3;
    public static final byte DataValueType = 0;
    public static final byte NoSuchObject = 1;
    public static final byte NoSuchInstance = 2;
    public static final byte EndOfMib = 4;
    MibObject parent;
    boolean verbose;
    boolean synchronous;
    int vbLimit;
    byte encoding;
    String oid;
    String value;
    byte[] octets;
    byte valueType;
    SnmpSession ssn;
    SnmpListener listener;
    int lastPduStatus;
    String lastPduStrStatus;
    
    public MibObject(final SnmpSession snmpSession, final String s) {
        throw new Error("Unresolved compilation problems: \n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tvarbind cannot be resolved to a type\n\tThe method getResponseVarbinds() from the type SmartPdu refers to the missing type varbind\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnInteger cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnObjectId cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnObject cannot be resolved to a type\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n");
    }
    
    public MibObject(final MibObject mibObject) {
        throw new Error("Unresolved compilation problems: \n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tThe import uk cannot be resolved\n\tvarbind cannot be resolved to a type\n\tThe method getResponseVarbinds() from the type SmartPdu refers to the missing type varbind\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnInteger cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnObjectId cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnObject cannot be resolved to a type\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n");
    }
    
    public synchronized void setSynchronous(final boolean b) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized String getIndex() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void appendIndex(final String s) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void setId(final String s) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized String getId() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void setOid(final String s) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void setEncoding(final byte b) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized String getEncoding() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized byte getRawEncoding() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void setValue(final String s) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void setValue(final BitSet set) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized String getValue() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized int getIntValue() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized byte[] getOctets() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized BitSet getBitSet() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized byte getValueType() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized boolean hasExceptionValue() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void setVerbose(final boolean b) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized int getLastPduStatus() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized String getLastPduStrStatus() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized void setListener(final SnmpListener snmpListener) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized int get() throws SnmpException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized int set() throws SnmpException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized int getNext() throws SnmpException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public synchronized int getBulk() throws SnmpException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    @Override
    public void vbLimitAdjusted(final SmartPdu smartPdu) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    @Override
    public synchronized void pduResponse(final SmartPdu smartPdu) {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tThe method getResponseVarbinds() from the type SmartPdu refers to the missing type varbind\n");
    }
    
    @Override
    public synchronized String toString() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    synchronized void addVarbind(final SmartPdu smartPdu) throws SnmpException {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnInteger cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n\tAsnObjectId cannot be resolved to a type\n\tvarbind cannot be resolved to a type\n");
    }
    
    synchronized void getVarbind(final varbind varbind) {
        throw new Error("Unresolved compilation problems: \n\tvarbind cannot be resolved to a type\n\tAsnObject cannot be resolved to a type\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a variable\n\tAsnObject cannot be resolved to a variable\n");
    }
    
    int getVbCount() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    protected void setValue(final AsnObject asnObject) {
        throw new Error("Unresolved compilation problems: \n\tAsnObject cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n\tAsnOctets cannot be resolved to a type\n");
    }
    
    private int sendPdu(final SmartPdu smartPdu) throws SnmpException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private void validateSession() throws SnmpException {
        throw new Error("Unresolved compilation problem: \n");
    }
}
