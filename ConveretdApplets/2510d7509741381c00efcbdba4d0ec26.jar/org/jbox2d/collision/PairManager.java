// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.MathUtils;

public class PairManager
{
    public static final int NULL_PAIR = Integer.MAX_VALUE;
    static final int NULL_PROXY = Integer.MAX_VALUE;
    public static final int TABLE_CAPACITY = 16384;
    static final int TABLE_MASK = 16383;
    public Pair[] m_pairs;
    public int m_pairCount;
    public int[] m_hashTable;
    public BroadPhase m_broadPhase;
    public PairCallback m_callback;
    public int m_freePair;
    public BufferedPair[] m_pairBuffer;
    public int m_pairBufferCount;
    
    public PairManager() {
        this.m_pairs = new Pair[16384];
        this.m_hashTable = new int[16384];
        this.m_pairBuffer = new BufferedPair[16384];
        assert MathUtils.isPowerOfTwo(16384);
        for (int i = 0; i < 16384; ++i) {
            this.m_hashTable[i] = Integer.MAX_VALUE;
        }
        this.m_freePair = 0;
        for (int i = 0; i < 16384; ++i) {
            this.m_pairs[i] = new Pair();
            this.m_pairs[i].proxyId1 = Integer.MAX_VALUE;
            this.m_pairs[i].proxyId2 = Integer.MAX_VALUE;
            this.m_pairs[i].userData = null;
            this.m_pairs[i].status = 0;
            this.m_pairs[i].next = i + 1;
            this.m_pairBuffer[i] = new BufferedPair();
        }
        this.m_pairs[16383].next = Integer.MAX_VALUE;
        this.m_pairCount = 0;
        this.m_pairBufferCount = 0;
    }
    
    public void initialize(final BroadPhase broadPhase, final PairCallback callback) {
        this.m_broadPhase = broadPhase;
        this.m_callback = callback;
    }
    
    public Pair addPair(int proxyId1, int proxyId2) {
        if (proxyId1 > proxyId2) {
            proxyId1 += proxyId2;
            proxyId2 = proxyId1 - proxyId2;
            proxyId1 -= proxyId2;
        }
        final int hash = this.hash(proxyId1, proxyId2) & 0x3FFF;
        Pair pair = this.find(proxyId1, proxyId2, hash);
        if (pair != null) {
            return pair;
        }
        assert this.m_pairCount < 16384 : "Too many pairs (shape AABB overlaps) - this usually means you have too many bodies, or you need to increase Settings.maxPairs.";
        assert this.m_freePair != Integer.MAX_VALUE;
        final int pairIndex = this.m_freePair;
        pair = this.m_pairs[pairIndex];
        this.m_freePair = pair.next;
        pair.proxyId1 = proxyId1;
        pair.proxyId2 = proxyId2;
        pair.status = 0;
        pair.userData = null;
        pair.next = this.m_hashTable[hash];
        this.m_hashTable[hash] = pairIndex;
        ++this.m_pairCount;
        return pair;
    }
    
    public Object removePair(int proxyId1, int proxyId2) {
        assert this.m_pairCount > 0;
        if (proxyId1 > proxyId2) {
            proxyId1 += proxyId2;
            proxyId2 = proxyId1 - proxyId2;
            proxyId1 -= proxyId2;
        }
        final int hash = this.hash(proxyId1, proxyId2) & 0x3FFF;
        int derefnode = this.m_hashTable[hash];
        boolean isHash = true;
        int pderefnode = 0;
        while (derefnode != Integer.MAX_VALUE) {
            if (this.equals(this.m_pairs[derefnode], proxyId1, proxyId2)) {
                final int index = derefnode;
                if (isHash) {
                    this.m_hashTable[hash] = this.m_pairs[this.m_hashTable[hash]].next;
                }
                else {
                    this.m_pairs[pderefnode].next = this.m_pairs[derefnode].next;
                }
                final Pair pair = this.m_pairs[index];
                final Object userData = pair.userData;
                pair.next = this.m_freePair;
                pair.proxyId1 = Integer.MAX_VALUE;
                pair.proxyId2 = Integer.MAX_VALUE;
                pair.userData = null;
                pair.status = 0;
                this.m_freePair = index;
                --this.m_pairCount;
                return userData;
            }
            pderefnode = derefnode;
            derefnode = this.m_pairs[derefnode].next;
            isHash = false;
        }
        assert false : "Attempted to remove a pair that does not exist";
        return null;
    }
    
    public void addBufferedPair(final int id1, final int id2) {
        assert id1 != Integer.MAX_VALUE && id2 != Integer.MAX_VALUE;
        assert this.m_pairBufferCount < 16384;
        final Pair pair = this.addPair(id1, id2);
        if (!pair.isBuffered()) {
            assert !pair.isFinal();
            pair.setBuffered();
            this.m_pairBuffer[this.m_pairBufferCount].proxyId1 = pair.proxyId1;
            this.m_pairBuffer[this.m_pairBufferCount].proxyId2 = pair.proxyId2;
            ++this.m_pairBufferCount;
            assert this.m_pairBufferCount <= this.m_pairCount;
        }
        pair.clearRemoved();
    }
    
    public void removeBufferedPair(final int id1, final int id2) {
        assert id1 != Integer.MAX_VALUE && id2 != Integer.MAX_VALUE;
        assert this.m_pairBufferCount < 16384;
        final Pair pair = this.find(id1, id2);
        if (pair == null) {
            return;
        }
        if (!pair.isBuffered()) {
            assert pair.isFinal();
            pair.setBuffered();
            this.m_pairBuffer[this.m_pairBufferCount].proxyId1 = pair.proxyId1;
            this.m_pairBuffer[this.m_pairBufferCount].proxyId2 = pair.proxyId2;
            ++this.m_pairBufferCount;
            assert this.m_pairBufferCount <= this.m_pairCount;
        }
        pair.setRemoved();
    }
    
    public void commit() {
        int removeCount = 0;
        final Proxy[] proxies = this.m_broadPhase.m_proxyPool;
        for (int i = 0; i < this.m_pairBufferCount; ++i) {
            final Pair pair = this.find(this.m_pairBuffer[i].proxyId1, this.m_pairBuffer[i].proxyId2);
            assert pair.isBuffered();
            pair.clearBuffered();
            assert pair.proxyId1 < 2048 && pair.proxyId2 < 2048;
            final Proxy proxy1 = proxies[pair.proxyId1];
            final Proxy proxy2 = proxies[pair.proxyId2];
            assert proxy1.isValid();
            assert proxy2.isValid();
            if (pair.isRemoved()) {
                if (pair.isFinal()) {
                    this.m_callback.pairRemoved(proxy1.userData, proxy2.userData, pair.userData);
                }
                this.m_pairBuffer[removeCount].proxyId1 = pair.proxyId1;
                this.m_pairBuffer[removeCount].proxyId2 = pair.proxyId2;
                ++removeCount;
            }
            else {
                assert this.m_broadPhase.testOverlap(proxy1, proxy2);
                if (!pair.isFinal()) {
                    pair.userData = this.m_callback.pairAdded(proxy1.userData, proxy2.userData);
                    pair.setFinal();
                }
            }
        }
        for (int i = 0; i < removeCount; ++i) {
            this.removePair(this.m_pairBuffer[i].proxyId1, this.m_pairBuffer[i].proxyId2);
        }
        this.m_pairBufferCount = 0;
    }
    
    public void validateBuffer() {
    }
    
    public void validateTable() {
        for (int i = 0; i < 16384; ++i) {
            Pair pair;
            for (int index = this.m_hashTable[i]; index != Integer.MAX_VALUE; index = pair.next) {
                pair = this.m_pairs[index];
                assert !pair.isBuffered();
                assert pair.isFinal();
                assert !pair.isRemoved();
                assert pair.proxyId1 != pair.proxyId2;
                assert pair.proxyId1 < 2048;
                assert pair.proxyId2 < 2048;
                final Proxy proxy1 = this.m_broadPhase.m_proxyPool[pair.proxyId1];
                final Proxy proxy2 = this.m_broadPhase.m_proxyPool[pair.proxyId2];
                assert proxy1.isValid();
                assert proxy2.isValid();
                assert this.m_broadPhase.testOverlap(proxy1, proxy2);
            }
        }
    }
    
    public Pair find(final int proxyId1, final int proxyId2, final int hash) {
        int index;
        for (index = this.m_hashTable[hash]; index != Integer.MAX_VALUE && !this.equals(this.m_pairs[index], proxyId1, proxyId2); index = this.m_pairs[index].next) {}
        if (index == Integer.MAX_VALUE) {
            return null;
        }
        assert index < 16384;
        return this.m_pairs[index];
    }
    
    public Pair find(int proxyId1, int proxyId2) {
        if (proxyId1 > proxyId2) {
            final int tmp = proxyId1;
            proxyId1 = proxyId2;
            proxyId2 = tmp;
        }
        final int hash = this.hash(proxyId1, proxyId2) & 0x3FFF;
        return this.find(proxyId1, proxyId2, hash);
    }
    
    private int hash(final int proxyId1, final int proxyId2) {
        int key = proxyId2 << 16 | proxyId1;
        key = ~key + (key << 15);
        key ^= key >>> 12;
        key += key << 2;
        key ^= key >>> 4;
        key *= 2057;
        key ^= key >>> 16;
        return key;
    }
    
    boolean equals(final Pair pair, final int proxyId1, final int proxyId2) {
        return pair.proxyId1 == proxyId1 && pair.proxyId2 == proxyId2;
    }
    
    boolean equals(final BufferedPair pair1, final BufferedPair pair2) {
        return pair1.proxyId1 == pair2.proxyId1 && pair1.proxyId2 == pair2.proxyId2;
    }
    
    boolean minor(final BufferedPair pair1, final BufferedPair pair2) {
        return pair1.proxyId1 < pair2.proxyId1 || (pair1.proxyId1 == pair2.proxyId1 && pair1.proxyId2 < pair2.proxyId2);
    }
}
