// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import java.util.Enumeration;
import Go.GobanLocation;
import java.util.Vector;
import Debug.DebugUtils;
import Go.Move;
import Go.GoPosition;

public class AlphaBetaPruningGoStrategy extends MinMaxGoStrategy
{
    protected int color;
    protected GoEstimatorInterface estimator;
    private int nrVariations;
    protected float alpha;
    protected float beta;
    
    public AlphaBetaPruningGoStrategy(final int color, final GoEstimatorInterface estimator) {
        this.nrVariations = 0;
        this.color = color;
        this.estimator = estimator;
    }
    
    public Move chooseNextMove(final GoPosition position) {
        this.nrVariations = 0;
        DebugUtils.println("Started building the analyse tree");
        final GameTreeInterface tree = this.buildTree(position);
        DebugUtils.println(String.valueOf("Computed variations = ").concat(String.valueOf(this.nrVariations)));
        return ((GoNodeInfo)tree.getRoot().getContent()).nextMove;
    }
    
    protected GameTreeInterface buildTree(final GoPosition position) {
        final int rootLastMoveColor = (position.turn() == 1) ? -1 : 1;
        final float rootInitialValue = (position.turn() == this.color) ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        final GameTreeInterface tree = new GameTree(new GoNodeInfo(rootInitialValue, new Move(rootLastMoveColor), position));
        this.alpha = Float.NEGATIVE_INFINITY;
        this.beta = Float.POSITIVE_INFINITY;
        this.expandAlphaBeta(tree.getRoot(), position, super.maxDepth, this.alpha, this.beta);
        return tree;
    }
    
    protected float expandAlphaBeta(final TreeNodeInterface node, final GoPosition position, final int maxDepth, float alpha, final float beta) {
        final GoNodeInfo currentNodeInfo = (GoNodeInfo)node.getContent();
        GoNodeInfo currentChildNodeInfo = null;
        float best = Float.NEGATIVE_INFINITY;
        ++this.nrVariations;
        if (maxDepth == 0 || this.estimator.checkPositionState(position) != 100) {
            best = this.estimator.estimatePosition(position, position.turn());
            DebugUtils.print(String.valueOf(" - Estimated = ").concat(String.valueOf(best)));
            return best;
        }
        Enumeration nextMoveCandidateEnumeration = null;
        final Move doOrDieMove = BasicAtariGoStrategy.tryBasicMove(position);
        if (doOrDieMove != null) {
            DebugUtils.print(String.valueOf("Do-Or-Die move needed: ").concat(String.valueOf(doOrDieMove.toString())));
            final Vector oneElementVector = new Vector();
            oneElementVector.addElement(new GobanLocation(doOrDieMove, position));
            nextMoveCandidateEnumeration = oneElementVector.elements();
        }
        else {
            nextMoveCandidateEnumeration = BasicAtariGoStrategy.contactMoves(position);
        }
        if (!nextMoveCandidateEnumeration.hasMoreElements()) {
            DebugUtils.print(" No contact moves ");
            nextMoveCandidateEnumeration = position.legalMoves_GL();
        }
        Move currentNextMove = null;
        GoPosition nextPosition = null;
        if (nextMoveCandidateEnumeration.hasMoreElements()) {
            while (nextMoveCandidateEnumeration.hasMoreElements() && best < beta) {
                DebugUtils.println("");
                currentNextMove = new Move(nextMoveCandidateEnumeration.nextElement(), position.turn());
                DebugUtils.print(String.valueOf("Consider move: ").concat(String.valueOf(currentNextMove.toString())));
                nextPosition = position.positionAfterMove(currentNextMove);
                DebugUtils.println(String.valueOf(" => position: ").concat(String.valueOf(nextPosition.toString())));
                final float initialValue = Float.NEGATIVE_INFINITY;
                final TreeNode childNode = new TreeNode(new GoNodeInfo(initialValue, currentNextMove, nextPosition), node);
                if (best > alpha) {
                    alpha = best;
                }
                final float value = -this.expandAlphaBeta(childNode, nextPosition, maxDepth - 1, -beta, -alpha);
                currentChildNodeInfo = (GoNodeInfo)childNode.getContent();
                if (value > best) {
                    best = value;
                    currentNodeInfo.nextMove = currentNextMove;
                }
            }
        }
        else {
            best = this.estimator.estimatePosition(position, position.turn());
            DebugUtils.print(String.valueOf(" - Estimated = ").concat(String.valueOf(best)));
        }
        return best;
    }
}
