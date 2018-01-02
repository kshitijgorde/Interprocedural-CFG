// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.compiler;

public class OpMap
{
    protected String m_currentPattern;
    static final int MAXTOKENQUEUESIZE = 500;
    public Object[] m_tokenQueue;
    public int m_tokenQueueSize;
    public int[] m_opMap;
    public static final int MAPINDEX_LENGTH = 1;
    
    public OpMap() {
        this.m_tokenQueue = new Object[500];
        this.m_tokenQueueSize = 0;
        this.m_opMap = null;
    }
    
    public int getArgLength(final int opPos) {
        return this.m_opMap[opPos + 1];
    }
    
    public int getArgLengthOfStep(final int opPos) {
        return this.m_opMap[opPos + 1 + 1] - 3;
    }
    
    public static int getFirstChildPos(final int opPos) {
        return opPos + 2;
    }
    
    public static int getFirstChildPosOfStep(final int opPos) {
        return opPos + 3;
    }
    
    public int getFirstPredicateOpPos(final int opPos) {
        final int stepType = this.m_opMap[opPos];
        if (stepType >= 37 && stepType <= 53) {
            return opPos + this.m_opMap[opPos + 2];
        }
        if (stepType >= 22 && stepType <= 25) {
            return opPos + this.m_opMap[opPos + 1];
        }
        throw new RuntimeException("Programmer's assertion in getNextStepPos: unknown stepType: " + stepType);
    }
    
    public int getNextOpPos(final int opPos) {
        return opPos + this.m_opMap[opPos + 1];
    }
    
    public static int getNextOpPos(final int[] opMap, final int opPos) {
        return opPos + opMap[opPos + 1];
    }
    
    public int getNextStepPos(final int opPos) {
        int stepType = this.getOp(opPos);
        if (stepType >= 37 && stepType <= 53) {
            return this.getNextOpPos(opPos);
        }
        if (stepType < 22 || stepType > 25) {
            throw new RuntimeException("Programmer's assertion in getNextStepPos: unknown stepType: " + stepType);
        }
        int newOpPos;
        for (newOpPos = this.getNextOpPos(opPos); this.getOp(newOpPos) == 29; newOpPos = this.getNextOpPos(newOpPos)) {}
        stepType = this.getOp(newOpPos);
        if (stepType < 37 || stepType > 53) {
            return -1;
        }
        return newOpPos;
    }
    
    public int getOp(final int opPos) {
        return this.m_opMap[opPos];
    }
    
    public int[] getOpMap() {
        return this.m_opMap;
    }
    
    public String getPatternString() {
        return this.m_currentPattern;
    }
    
    public String getStepLocalName(final int opPosOfStep) {
        final int argLenOfStep = this.getArgLengthOfStep(opPosOfStep);
        int index = 0;
        switch (argLenOfStep) {
            case 0: {
                index = -2;
                break;
            }
            case 1: {
                index = -3;
                break;
            }
            case 2: {
                index = this.m_opMap[opPosOfStep + 4];
                break;
            }
            case 3: {
                index = this.m_opMap[opPosOfStep + 5];
                break;
            }
            default: {
                index = -2;
                break;
            }
        }
        if (index >= 0) {
            return this.m_tokenQueue[index].toString();
        }
        if (index == -3) {
            return "*";
        }
        return null;
    }
    
    public String getStepNS(final int opPosOfStep) {
        final int argLenOfStep = this.getArgLengthOfStep(opPosOfStep);
        if (argLenOfStep != 3) {
            return null;
        }
        final int index = this.m_opMap[opPosOfStep + 4];
        if (index >= 0) {
            return (String)this.m_tokenQueue[index];
        }
        if (index == -3) {
            return "*";
        }
        return null;
    }
    
    public int getStepTestType(final int opPosOfStep) {
        return this.m_opMap[opPosOfStep + 3];
    }
    
    public Object getToken(final int pos) {
        return this.m_tokenQueue[pos];
    }
    
    public Object[] getTokenQueue() {
        return this.m_tokenQueue;
    }
    
    public int getTokenQueueSize() {
        return this.m_tokenQueueSize;
    }
    
    void shrink() {
        final int[] map = this.m_opMap;
        int n = this.m_opMap[1];
        this.m_opMap = new int[n + 4];
        int i;
        for (i = 0; i < n; ++i) {
            this.m_opMap[i] = map[i];
        }
        this.m_opMap[i] = 0;
        this.m_opMap[i + 1] = 0;
        this.m_opMap[i + 2] = 0;
        final Object[] tokens = this.m_tokenQueue;
        n = this.m_tokenQueueSize;
        this.m_tokenQueue = new Object[n + 4];
        for (i = 0; i < n; ++i) {
            this.m_tokenQueue[i] = tokens[i];
        }
        this.m_tokenQueue[i] = null;
        this.m_tokenQueue[i + 1] = null;
        this.m_tokenQueue[i + 2] = null;
    }
    
    public String toString() {
        return this.m_currentPattern;
    }
}
