// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class BroadPhase
{
    public static final int INVALID = Integer.MAX_VALUE;
    public static final int NULL_EDGE = Integer.MAX_VALUE;
    public PairManager m_pairManager;
    public Proxy[] m_proxyPool;
    int m_freeProxy;
    BufferedPair[] pairBuffer;
    int m_pairBufferCount;
    public Bound[][] m_bounds;
    int[] m_queryResults;
    int m_queryResultCount;
    public AABB m_worldAABB;
    public Vec2 m_quantizationFactor;
    public int m_proxyCount;
    int m_timeStamp;
    private static final boolean debugPrint = false;
    public static final boolean s_validate = false;
    
    private void dump() {
        for (int i = 0; i < 10; ++i) {
            System.out.printf("bounds[ %d ] = %d, %d \n", i, this.m_bounds[0][i].value, this.m_bounds[1][i].value);
        }
    }
    
    public BroadPhase(final AABB worldAABB, final PairCallback callback) {
        this.m_proxyPool = new Proxy[2048];
        this.pairBuffer = new BufferedPair[16384];
        this.m_bounds = new Bound[2][4096];
        this.m_queryResults = new int[2048];
        for (int i = 0; i < 4096; ++i) {
            this.m_bounds[0][i] = new Bound();
            this.m_bounds[1][i] = new Bound();
        }
        for (int i = 0; i < 2048; ++i) {
            this.pairBuffer[i] = new BufferedPair();
        }
        (this.m_pairManager = new PairManager()).initialize(this, callback);
        assert worldAABB.isValid();
        this.m_worldAABB = new AABB(worldAABB);
        this.m_proxyCount = 0;
        final Vec2 d = worldAABB.upperBound.sub(worldAABB.lowerBound);
        this.m_quantizationFactor = new Vec2(2.14748365E9f / d.x, 2.14748365E9f / d.y);
        for (int j = 0; j < 2047; ++j) {
            (this.m_proxyPool[j] = new Proxy()).setNext(j + 1);
            this.m_proxyPool[j].timeStamp = 0;
            this.m_proxyPool[j].overlapCount = Integer.MAX_VALUE;
            this.m_proxyPool[j].userData = null;
        }
        (this.m_proxyPool[2047] = new Proxy()).setNext(Integer.MAX_VALUE);
        this.m_proxyPool[2047].timeStamp = 0;
        this.m_proxyPool[2047].overlapCount = Integer.MAX_VALUE;
        this.m_proxyPool[2047].userData = null;
        this.m_freeProxy = 0;
        this.m_timeStamp = 1;
        this.m_queryResultCount = 0;
    }
    
    protected boolean testOverlap(final Proxy p1, final Proxy p2) {
        for (int axis = 0; axis < 2; ++axis) {
            final Bound[] bounds = this.m_bounds[axis];
            assert p1.lowerBounds[axis] < 2 * this.m_proxyCount;
            assert p1.upperBounds[axis] < 2 * this.m_proxyCount;
            assert p2.lowerBounds[axis] < 2 * this.m_proxyCount;
            assert p2.upperBounds[axis] < 2 * this.m_proxyCount;
            if (bounds[p1.lowerBounds[axis]].value > bounds[p2.upperBounds[axis]].value) {
                return false;
            }
            if (bounds[p1.upperBounds[axis]].value < bounds[p2.lowerBounds[axis]].value) {
                return false;
            }
        }
        return true;
    }
    
    private boolean testOverlap(final BoundValues b, final Proxy p) {
        for (int axis = 0; axis < 2; ++axis) {
            final Bound[] bounds = this.m_bounds[axis];
            assert p.lowerBounds[axis] < 2 * this.m_proxyCount;
            assert p.upperBounds[axis] < 2 * this.m_proxyCount;
            if (b.lowerValues[axis] > bounds[p.upperBounds[axis]].value) {
                return false;
            }
            if (b.upperValues[axis] < bounds[p.lowerBounds[axis]].value) {
                return false;
            }
        }
        return true;
    }
    
    public Proxy getProxy(final int proxyId) {
        if (proxyId == Integer.MAX_VALUE || !this.m_proxyPool[proxyId].isValid()) {
            return null;
        }
        return this.m_proxyPool[proxyId];
    }
    
    int createProxy(final AABB aabb, final Object userData) {
        assert this.m_proxyCount < 2048;
        assert this.m_freeProxy != Integer.MAX_VALUE;
        final int proxyId = this.m_freeProxy;
        final Proxy proxy = this.m_proxyPool[proxyId];
        this.m_freeProxy = proxy.getNext();
        proxy.overlapCount = 0;
        proxy.userData = userData;
        final int boundCount = 2 * this.m_proxyCount;
        final int[] lowerValues = new int[2];
        final int[] upperValues = new int[2];
        this.computeBounds(lowerValues, upperValues, aabb);
        for (int axis = 0; axis < 2; ++axis) {
            final Bound[] bounds = this.m_bounds[axis];
            final int[] indexes = new int[2];
            this.query(indexes, lowerValues[axis], upperValues[axis], bounds, boundCount, axis);
            final int lowerIndex = indexes[0];
            int upperIndex = indexes[1];
            System.arraycopy(this.m_bounds[axis], upperIndex, this.m_bounds[axis], upperIndex + 2, boundCount - upperIndex);
            for (int i = 0; i < boundCount - upperIndex; ++i) {
                this.m_bounds[axis][upperIndex + 2 + i] = new Bound(this.m_bounds[axis][upperIndex + 2 + i]);
            }
            System.arraycopy(this.m_bounds[axis], lowerIndex, this.m_bounds[axis], lowerIndex + 1, upperIndex - lowerIndex);
            for (int i = 0; i < upperIndex - lowerIndex; ++i) {
                this.m_bounds[axis][lowerIndex + 1 + i] = new Bound(this.m_bounds[axis][lowerIndex + 1 + i]);
            }
            ++upperIndex;
            assert bounds[lowerIndex] != null : "Null pointer (lower)";
            assert bounds[upperIndex] != null : "Null pointer (upper)";
            bounds[lowerIndex].value = lowerValues[axis];
            bounds[lowerIndex].proxyId = proxyId;
            bounds[upperIndex].value = upperValues[axis];
            bounds[upperIndex].proxyId = proxyId;
            bounds[lowerIndex].stabbingCount = ((lowerIndex == 0) ? 0 : bounds[lowerIndex - 1].stabbingCount);
            bounds[upperIndex].stabbingCount = bounds[upperIndex - 1].stabbingCount;
            for (int index = lowerIndex; index < upperIndex; ++index) {
                final Bound bound = bounds[index];
                ++bound.stabbingCount;
            }
            for (int index = lowerIndex; index < boundCount + 2; ++index) {
                final Proxy proxyn = this.m_proxyPool[bounds[index].proxyId];
                if (bounds[index].isLower()) {
                    proxyn.lowerBounds[axis] = index;
                }
                else {
                    proxyn.upperBounds[axis] = index;
                }
            }
        }
        ++this.m_proxyCount;
        assert this.m_queryResultCount < 2048;
        for (int j = 0; j < this.m_queryResultCount; ++j) {
            assert this.m_queryResults[j] < 2048;
            assert this.m_proxyPool[this.m_queryResults[j]].isValid();
            this.m_pairManager.addBufferedPair(proxyId, this.m_queryResults[j]);
        }
        this.m_pairManager.commit();
        this.m_queryResultCount = 0;
        this.incrementTimeStamp();
        return proxyId;
    }
    
    public void destroyProxy(final int proxyId) {
        assert this.m_proxyCount > 0 && this.m_proxyCount <= 2048;
        final Proxy proxy = this.m_proxyPool[proxyId];
        assert proxy.isValid();
        final int boundCount = 2 * this.m_proxyCount;
        for (int axis = 0; axis < 2; ++axis) {
            final Bound[] bounds = this.m_bounds[axis];
            final int lowerIndex = proxy.lowerBounds[axis];
            final int upperIndex = proxy.upperBounds[axis];
            final int lowerValue = bounds[lowerIndex].value;
            final int upperValue = bounds[upperIndex].value;
            System.arraycopy(this.m_bounds[axis], lowerIndex + 1, this.m_bounds[axis], lowerIndex, upperIndex - lowerIndex - 1);
            for (int i = 0; i < upperIndex - lowerIndex - 1; ++i) {
                this.m_bounds[axis][lowerIndex + i] = new Bound(this.m_bounds[axis][lowerIndex + i]);
            }
            System.arraycopy(this.m_bounds[axis], upperIndex + 1, this.m_bounds[axis], upperIndex - 1, boundCount - upperIndex - 1);
            for (int i = 0; i < boundCount - upperIndex - 1; ++i) {
                this.m_bounds[axis][upperIndex - 1 + i] = new Bound(this.m_bounds[axis][upperIndex - 1 + i]);
            }
            for (int index = lowerIndex; index < boundCount - 2; ++index) {
                final Proxy proxyn = this.m_proxyPool[bounds[index].proxyId];
                if (bounds[index].isLower()) {
                    proxyn.lowerBounds[axis] = index;
                }
                else {
                    proxyn.upperBounds[axis] = index;
                }
            }
            for (int index = lowerIndex; index < upperIndex - 1; ++index) {
                final Bound bound = bounds[index];
                --bound.stabbingCount;
            }
            final int[] ignored = new int[2];
            this.query(ignored, lowerValue, upperValue, bounds, boundCount - 2, axis);
        }
        assert this.m_queryResultCount < 2048;
        for (int j = 0; j < this.m_queryResultCount; ++j) {
            assert this.m_proxyPool[this.m_queryResults[j]].isValid();
            this.m_pairManager.removeBufferedPair(proxyId, this.m_queryResults[j]);
        }
        this.m_pairManager.commit();
        this.m_queryResultCount = 0;
        this.incrementTimeStamp();
        proxy.userData = null;
        proxy.overlapCount = Integer.MAX_VALUE;
        proxy.lowerBounds[0] = Integer.MAX_VALUE;
        proxy.lowerBounds[1] = Integer.MAX_VALUE;
        proxy.upperBounds[0] = Integer.MAX_VALUE;
        proxy.upperBounds[1] = Integer.MAX_VALUE;
        proxy.setNext(this.m_freeProxy);
        this.m_freeProxy = proxyId;
        --this.m_proxyCount;
    }
    
    void moveProxy(final int proxyId, final AABB aabb) {
        if (proxyId == Integer.MAX_VALUE || 2048 <= proxyId) {
            return;
        }
        assert aabb.isValid() : "invalid AABB";
        final int boundCount = 2 * this.m_proxyCount;
        final Proxy proxy = this.m_proxyPool[proxyId];
        final BoundValues newValues = new BoundValues();
        this.computeBounds(newValues.lowerValues, newValues.upperValues, aabb);
        final BoundValues oldValues = new BoundValues();
        for (int axis = 0; axis < 2; ++axis) {
            oldValues.lowerValues[axis] = this.m_bounds[axis][proxy.lowerBounds[axis]].value;
            oldValues.upperValues[axis] = this.m_bounds[axis][proxy.upperBounds[axis]].value;
        }
        for (int axis = 0; axis < 2; ++axis) {
            final Bound[] bounds = this.m_bounds[axis];
            final int lowerIndex = proxy.lowerBounds[axis];
            final int upperIndex = proxy.upperBounds[axis];
            final int lowerValue = newValues.lowerValues[axis];
            final int upperValue = newValues.upperValues[axis];
            final int deltaLower = lowerValue - bounds[lowerIndex].value;
            final int deltaUpper = upperValue - bounds[upperIndex].value;
            bounds[lowerIndex].value = lowerValue;
            bounds[upperIndex].value = upperValue;
            if (deltaLower < 0) {
                for (int index = lowerIndex; index > 0 && lowerValue < bounds[index - 1].value; --index) {
                    final Bound bound = bounds[index];
                    final Bound prevBound = bounds[index - 1];
                    final int prevProxyId = prevBound.proxyId;
                    final Proxy prevProxy = this.m_proxyPool[prevBound.proxyId];
                    final Bound bound2 = prevBound;
                    ++bound2.stabbingCount;
                    if (prevBound.isUpper()) {
                        if (this.testOverlap(newValues, prevProxy)) {
                            this.m_pairManager.addBufferedPair(proxyId, prevProxyId);
                        }
                        final int[] upperBounds = prevProxy.upperBounds;
                        final int n = axis;
                        ++upperBounds[n];
                        final Bound bound3 = bound;
                        ++bound3.stabbingCount;
                    }
                    else {
                        final int[] lowerBounds = prevProxy.lowerBounds;
                        final int n2 = axis;
                        ++lowerBounds[n2];
                        final Bound bound4 = bound;
                        --bound4.stabbingCount;
                    }
                    final int[] lowerBounds2 = proxy.lowerBounds;
                    final int n3 = axis;
                    --lowerBounds2[n3];
                    final Bound tmp = new Bound(bound);
                    bound.set(prevBound);
                    prevBound.set(tmp);
                }
            }
            if (deltaUpper > 0) {
                for (int index = upperIndex; index < boundCount - 1 && bounds[index + 1].value <= upperValue; ++index) {
                    final Bound bound = bounds[index];
                    final Bound nextBound = bounds[index + 1];
                    final int nextProxyId = nextBound.proxyId;
                    final Proxy nextProxy = this.m_proxyPool[nextProxyId];
                    final Bound bound5 = nextBound;
                    ++bound5.stabbingCount;
                    if (nextBound.isLower()) {
                        if (this.testOverlap(newValues, nextProxy)) {
                            this.m_pairManager.addBufferedPair(proxyId, nextProxyId);
                        }
                        final int[] lowerBounds3 = nextProxy.lowerBounds;
                        final int n4 = axis;
                        --lowerBounds3[n4];
                        final Bound bound6 = bound;
                        ++bound6.stabbingCount;
                    }
                    else {
                        final int[] upperBounds2 = nextProxy.upperBounds;
                        final int n5 = axis;
                        --upperBounds2[n5];
                        final Bound bound7 = bound;
                        --bound7.stabbingCount;
                    }
                    final int[] upperBounds3 = proxy.upperBounds;
                    final int n6 = axis;
                    ++upperBounds3[n6];
                    final Bound tmp = new Bound(bound);
                    bound.set(nextBound);
                    nextBound.set(tmp);
                }
            }
            if (deltaLower > 0) {
                for (int index = lowerIndex; index < boundCount - 1 && bounds[index + 1].value <= lowerValue; ++index) {
                    final Bound bound = bounds[index];
                    final Bound nextBound = bounds[index + 1];
                    final int nextProxyId = nextBound.proxyId;
                    final Proxy nextProxy = this.m_proxyPool[nextProxyId];
                    final Bound bound8 = nextBound;
                    --bound8.stabbingCount;
                    if (nextBound.isUpper()) {
                        if (this.testOverlap(oldValues, nextProxy)) {
                            this.m_pairManager.removeBufferedPair(proxyId, nextProxyId);
                        }
                        final int[] upperBounds4 = nextProxy.upperBounds;
                        final int n7 = axis;
                        --upperBounds4[n7];
                        final Bound bound9 = bound;
                        --bound9.stabbingCount;
                    }
                    else {
                        final int[] lowerBounds4 = nextProxy.lowerBounds;
                        final int n8 = axis;
                        --lowerBounds4[n8];
                        final Bound bound10 = bound;
                        ++bound10.stabbingCount;
                    }
                    final int[] lowerBounds5 = proxy.lowerBounds;
                    final int n9 = axis;
                    ++lowerBounds5[n9];
                    final Bound tmp = new Bound(bound);
                    bound.set(nextBound);
                    nextBound.set(tmp);
                }
            }
            if (deltaUpper < 0) {
                for (int index = upperIndex; index > 0 && upperValue < bounds[index - 1].value; --index) {
                    final Bound bound = bounds[index];
                    final Bound prevBound = bounds[index - 1];
                    final int prevProxyId = prevBound.proxyId;
                    final Proxy prevProxy = this.m_proxyPool[prevProxyId];
                    final Bound bound11 = prevBound;
                    --bound11.stabbingCount;
                    if (prevBound.isLower()) {
                        if (this.testOverlap(oldValues, prevProxy)) {
                            this.m_pairManager.removeBufferedPair(proxyId, prevProxyId);
                        }
                        final int[] lowerBounds6 = prevProxy.lowerBounds;
                        final int n10 = axis;
                        ++lowerBounds6[n10];
                        final Bound bound12 = bound;
                        --bound12.stabbingCount;
                    }
                    else {
                        final int[] upperBounds5 = prevProxy.upperBounds;
                        final int n11 = axis;
                        ++upperBounds5[n11];
                        final Bound bound13 = bound;
                        ++bound13.stabbingCount;
                    }
                    final int[] upperBounds6 = proxy.upperBounds;
                    final int n12 = axis;
                    --upperBounds6[n12];
                    final Bound tmp = new Bound(bound);
                    bound.set(prevBound);
                    prevBound.set(tmp);
                }
            }
        }
    }
    
    public void commit() {
        this.m_pairManager.commit();
    }
    
    public Object[] query(final AABB aabb, final int maxCount) {
        final int[] lowerValues = new int[2];
        final int[] upperValues = new int[2];
        this.computeBounds(lowerValues, upperValues, aabb);
        final int[] indexes = new int[2];
        this.query(indexes, lowerValues[0], upperValues[0], this.m_bounds[0], 2 * this.m_proxyCount, 0);
        this.query(indexes, lowerValues[1], upperValues[1], this.m_bounds[1], 2 * this.m_proxyCount, 1);
        assert this.m_queryResultCount < 2048;
        final Object[] results = new Object[maxCount];
        int count = 0;
        for (int i = 0; i < this.m_queryResultCount && count < maxCount; ++i, ++count) {
            assert this.m_queryResults[i] < 2048;
            final Proxy proxy = this.m_proxyPool[this.m_queryResults[i]];
            proxy.isValid();
            results[i] = proxy.userData;
        }
        final Object[] copy = new Object[count];
        System.arraycopy(results, 0, copy, 0, count);
        this.m_queryResultCount = 0;
        this.incrementTimeStamp();
        return copy;
    }
    
    public void validate() {
        for (int axis = 0; axis < 2; ++axis) {
            final Bound[] bounds = this.m_bounds[axis];
            final int boundCount = 2 * this.m_proxyCount;
            int stabbingCount = 0;
            for (int i = 0; i < boundCount; ++i) {
                final Bound bound = bounds[i];
                assert bounds[i - 1].value <= bound.value;
                assert bound.proxyId != Integer.MAX_VALUE;
                assert this.m_proxyPool[bound.proxyId].isValid();
                if (bound.isLower()) {
                    assert this.m_proxyPool[bound.proxyId].lowerBounds[axis] == i : String.valueOf(this.m_proxyPool[bound.proxyId].lowerBounds[axis]) + " not " + i;
                    ++stabbingCount;
                }
                else {
                    assert this.m_proxyPool[bound.proxyId].upperBounds[axis] == i;
                    --stabbingCount;
                }
                assert bound.stabbingCount == stabbingCount;
            }
        }
    }
    
    private void computeBounds(final int[] lowerValues, final int[] upperValues, final AABB aabb) {
        assert aabb.upperBound.x > aabb.lowerBound.x;
        assert aabb.upperBound.y > aabb.lowerBound.y;
        final Vec2 minVertex = MathUtils.clamp(aabb.lowerBound, this.m_worldAABB.lowerBound, this.m_worldAABB.upperBound);
        final Vec2 maxVertex = MathUtils.clamp(aabb.upperBound, this.m_worldAABB.lowerBound, this.m_worldAABB.upperBound);
        lowerValues[0] = ((int)(this.m_quantizationFactor.x * (minVertex.x - this.m_worldAABB.lowerBound.x)) & 0x7FFFFFFE);
        upperValues[0] = ((int)(this.m_quantizationFactor.x * (maxVertex.x - this.m_worldAABB.lowerBound.x)) | 0x1);
        lowerValues[1] = ((int)(this.m_quantizationFactor.y * (minVertex.y - this.m_worldAABB.lowerBound.y)) & 0x7FFFFFFE);
        upperValues[1] = ((int)(this.m_quantizationFactor.y * (maxVertex.y - this.m_worldAABB.lowerBound.y)) | 0x1);
    }
    
    private void query(final int[] results, final int lowerValue, final int upperValue, final Bound[] bounds, final int boundCount, final int axis) {
        final int lowerQuery = binarySearch(bounds, boundCount, lowerValue);
        final int upperQuery = binarySearch(bounds, boundCount, upperValue);
        for (int i = lowerQuery; i < upperQuery; ++i) {
            if (bounds[i].isLower()) {
                this.incrementOverlapCount(bounds[i].proxyId);
            }
        }
        if (lowerQuery > 0) {
            int i = lowerQuery - 1;
            int s = bounds[i].stabbingCount;
            while (s != 0) {
                assert i >= 0 : "i = " + i + "; s = " + s;
                if (bounds[i].isLower()) {
                    final Proxy proxy = this.m_proxyPool[bounds[i].proxyId];
                    if (lowerQuery <= proxy.upperBounds[axis]) {
                        this.incrementOverlapCount(bounds[i].proxyId);
                        --s;
                    }
                }
                --i;
            }
        }
        results[0] = lowerQuery;
        results[1] = upperQuery;
    }
    
    private void incrementOverlapCount(final int proxyId) {
        final Proxy proxy = this.m_proxyPool[proxyId];
        if (proxy.timeStamp < this.m_timeStamp) {
            proxy.timeStamp = this.m_timeStamp;
            proxy.overlapCount = 1;
        }
        else {
            proxy.overlapCount = 2;
            assert this.m_queryResultCount < 2048;
            this.m_queryResults[this.m_queryResultCount] = proxyId;
            ++this.m_queryResultCount;
        }
    }
    
    private void incrementTimeStamp() {
        if (this.m_timeStamp == Integer.MAX_VALUE) {
            for (int i = 0; i < 2048; ++i) {
                this.m_proxyPool[i].timeStamp = 0;
            }
            this.m_timeStamp = 1;
        }
        else {
            ++this.m_timeStamp;
        }
    }
    
    static int binarySearch(final Bound[] bounds, final int count, final int value) {
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            final int mid = low + high >> 1;
            if (bounds[mid].value > value) {
                high = mid - 1;
            }
            else {
                if (bounds[mid].value >= value) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return low;
    }
    
    public boolean inRange(final AABB aabb) {
        final Vec2 d = Vec2.max(aabb.lowerBound.sub(this.m_worldAABB.upperBound), this.m_worldAABB.lowerBound.sub(aabb.upperBound));
        return Math.max(d.x, d.y) < 0.0f;
    }
}
