// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import java.util.Enumeration;
import Go.Move;
import Go.GoPosition;

public class MinMaxGoStrategy implements GoStrategy
{
    protected int color;
    protected GoEstimatorInterface estimator;
    protected int maxDepth;
    private int nrVariations;
    
    public MinMaxGoStrategy() {
        this.maxDepth = 2;
        this.nrVariations = 0;
    }
    
    public MinMaxGoStrategy(final int color, final GoEstimatorInterface estimator) {
        this.maxDepth = 2;
        this.nrVariations = 0;
        this.color = color;
        this.estimator = estimator;
    }
    
    public Move chooseNextMove(final GoPosition position) {
        return this.getMoveMinMax(position);
    }
    
    public GoEstimatorInterface getEstimator() {
        return this.estimator;
    }
    
    public void setEstimator(final GoEstimatorInterface newEstimator) {
        this.estimator = newEstimator;
    }
    
    public void setMaxDepth(final int newMaxDepth) {
        this.maxDepth = newMaxDepth;
    }
    
    public int getMaxDepth() {
        return this.maxDepth;
    }
    
    protected Move getMoveMinMax(final GoPosition position) {
        this.nrVariations = 0;
        final GameTreeInterface tree = this.buildTree(position);
        System.out.println(String.valueOf("Computed variations = ").concat(String.valueOf(this.nrVariations)));
        return ((GoNodeInfo)tree.getRoot().getContent()).nextMove;
    }
    
    protected GameTreeInterface buildTree(final GoPosition position) {
        final int rootLastMoveColor = (position.turn() == 1) ? -1 : 1;
        final float rootInitialValue = (position.turn() == this.color) ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        final GameTreeInterface tree = new GameTree(new GoNodeInfo(rootInitialValue, new Move(rootLastMoveColor), position));
        if (this.maxDepth != -1) {
            this.expandNode(tree.getRoot(), position, this.maxDepth);
        }
        else {
            this.expandNodeNoLimits(tree.getRoot(), position);
        }
        return tree;
    }
    
    protected Move chooseMaxMove(final GameTreeInterface tree) {
        Move bestMove = null;
        float bestMaxValue = Float.NEGATIVE_INFINITY;
        final Enumeration e = tree.getRoot().getChildren().elements();
        while (e.hasMoreElements()) {
            final GoNodeInfo nodeInfo = (GoNodeInfo)e.nextElement().getContent();
            if (nodeInfo.estimatedValue != -1000000 && nodeInfo.estimatedValue > bestMaxValue) {
                bestMove = nodeInfo.lastMove;
                bestMaxValue = nodeInfo.estimatedValue;
            }
        }
        return bestMove;
    }
    
    protected Move chooseMinMove(final GameTreeInterface tree) {
        Move bestMove = null;
        float bestMinValue = Float.POSITIVE_INFINITY;
        final Enumeration e = tree.getRoot().getChildren().elements();
        while (e.hasMoreElements()) {
            final GoNodeInfo nodeInfo = (GoNodeInfo)e.nextElement().getContent();
            if (nodeInfo.estimatedValue != -1000000 && nodeInfo.estimatedValue < bestMinValue) {
                bestMove = nodeInfo.lastMove;
                bestMinValue = nodeInfo.estimatedValue;
            }
        }
        return bestMove;
    }
    
    protected float chooseMaxValue(final TreeNodeInterface node) {
        float bestMaxValue = Float.NEGATIVE_INFINITY;
        final Enumeration e = node.getChildren().elements();
        while (e.hasMoreElements()) {
            final GoNodeInfo nodeInfo = (GoNodeInfo)e.nextElement().getContent();
            if (nodeInfo.estimatedValue != -1000000 && nodeInfo.estimatedValue > bestMaxValue) {
                bestMaxValue = nodeInfo.estimatedValue;
            }
        }
        return bestMaxValue;
    }
    
    protected float chooseMinValue(final TreeNodeInterface node) {
        float bestMinValue = Float.POSITIVE_INFINITY;
        final Enumeration e = node.getChildren().elements();
        while (e.hasMoreElements()) {
            final GoNodeInfo nodeInfo = (GoNodeInfo)e.nextElement().getContent();
            if (nodeInfo.estimatedValue != -1000000 && nodeInfo.estimatedValue < bestMinValue) {
                bestMinValue = nodeInfo.estimatedValue;
            }
        }
        return bestMinValue;
    }
    
    protected void computeTreeNodeValue(final TreeNodeInterface node) {
        final GoNodeInfo currentNodeInfo = (GoNodeInfo)node.getContent();
        final int turn = currentNodeInfo.turn();
        if (!node.isLeaf()) {
            final Enumeration e = node.getChildren().elements();
            while (e.hasMoreElements()) {
                this.computeTreeNodeValue(e.nextElement());
            }
            if (this.color == turn) {
                currentNodeInfo.estimatedValue = this.chooseMaxValue(node);
            }
            else {
                currentNodeInfo.estimatedValue = this.chooseMinValue(node);
            }
        }
        else {
            currentNodeInfo.estimatedValue = this.estimator.estimatePosition(currentNodeInfo.position, this.color);
        }
    }
    
    private void expandNodeNoLimits(final TreeNodeInterface node, final GoPosition position) {
        final GoNodeInfo currentNodeInfo = (GoNodeInfo)node.getContent();
        final int turn = currentNodeInfo.turn();
        System.out.println(++this.nrVariations);
        final Enumeration legalMovesEnumeration = position.legalMoves();
        if (legalMovesEnumeration.hasMoreElements()) {
            while (legalMovesEnumeration.hasMoreElements()) {
                final Move currentNextMove = legalMovesEnumeration.nextElement();
                final GoPosition nextPosition = position.positionAfterMove(currentNextMove);
                final TreeNodeInterface parentNode = node.getParent();
                if (parentNode == null || (parentNode != null && !nextPosition.sameAs(((GoNodeInfo)parentNode.getContent()).position))) {
                    final float initialValue = (this.color == turn) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
                    final TreeNodeInterface childNode = new TreeNode(new GoNodeInfo(initialValue, currentNextMove, nextPosition), node);
                    this.expandNodeNoLimits(childNode, nextPosition);
                    final GoNodeInfo currentChildNodeInfo = (GoNodeInfo)childNode.getContent();
                    if (this.color == turn) {
                        if (currentNodeInfo.estimatedValue != Float.POSITIVE_INFINITY) {
                            if (currentChildNodeInfo.estimatedValue <= currentNodeInfo.estimatedValue) {
                                continue;
                            }
                            currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                            currentNodeInfo.nextMove = currentNextMove;
                        }
                        else {
                            currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                            currentNodeInfo.nextMove = currentNextMove;
                        }
                    }
                    else if (currentNodeInfo.estimatedValue != Float.NEGATIVE_INFINITY) {
                        if (currentChildNodeInfo.estimatedValue >= currentNodeInfo.estimatedValue) {
                            continue;
                        }
                        currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                        currentNodeInfo.nextMove = currentNextMove;
                    }
                    else {
                        currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                        currentNodeInfo.nextMove = currentNextMove;
                    }
                }
            }
        }
        else {
            currentNodeInfo.estimatedValue = this.estimator.estimatePosition(position, this.color);
        }
    }
    
    private void expandNode(final TreeNodeInterface node, final GoPosition position, final int maxDepth) {
        final GoNodeInfo currentNodeInfo = (GoNodeInfo)node.getContent();
        final int turn = currentNodeInfo.turn();
        ++this.nrVariations;
        if (maxDepth == 0 || this.estimator.checkPositionState(position) != 100) {
            currentNodeInfo.estimatedValue = this.estimator.estimatePosition(position, this.color);
            return;
        }
        final Enumeration legalMovesEnumeration = position.legalMoves();
        if (legalMovesEnumeration.hasMoreElements()) {
            while (legalMovesEnumeration.hasMoreElements()) {
                final Move currentNextMove = legalMovesEnumeration.nextElement();
                final GoPosition nextPosition = position.positionAfterMove(currentNextMove);
                final float initialValue = (this.color == turn) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
                final TreeNodeInterface childNode = new TreeNode(new GoNodeInfo(initialValue, currentNextMove, nextPosition), node);
                this.expandNode(childNode, nextPosition, maxDepth - 1);
                final GoNodeInfo currentChildNodeInfo = (GoNodeInfo)childNode.getContent();
                if (this.color == turn) {
                    if (currentNodeInfo.estimatedValue != Float.NEGATIVE_INFINITY) {
                        if (currentChildNodeInfo.estimatedValue <= currentNodeInfo.estimatedValue) {
                            continue;
                        }
                        currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                        currentNodeInfo.nextMove = currentNextMove;
                    }
                    else {
                        currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                        currentNodeInfo.nextMove = currentNextMove;
                    }
                }
                else if (currentNodeInfo.estimatedValue != Float.POSITIVE_INFINITY) {
                    if (currentChildNodeInfo.estimatedValue >= currentNodeInfo.estimatedValue) {
                        continue;
                    }
                    currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                    currentNodeInfo.nextMove = currentNextMove;
                }
                else {
                    currentNodeInfo.estimatedValue = currentChildNodeInfo.estimatedValue;
                    currentNodeInfo.nextMove = currentNextMove;
                }
            }
        }
        else {
            currentNodeInfo.estimatedValue = this.estimator.estimatePosition(position, this.color);
        }
    }
}
