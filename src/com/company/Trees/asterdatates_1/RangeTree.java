import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*Tree to store intervals. Each interval is represented by a RangePair with inclusive and exclusive semantics.
 */
public class RangeTree<K extends Comparable<K>> {
    private IntervalTreeNode<K> root;

    private static class IntervalTreeNode<K> {
        private RangePair<K> range;
        private IntervalTreeNode<K> leftChild;
        private IntervalTreeNode<K> rightChild;

        public IntervalTreeNode(RangePair<K> range) {
            this.range = range;
        }
    }

    RangeTree() {
    }

    /*This function embodies compare function given two ranges.
      It also contains the inclusive and exclusive range check semantics.
     */
    private int compare(RangePair<K> leftRange,
                        RangePair<K> rightRange) {
        if (leftRange.second().compareTo(rightRange.first()) < 0 )
            return -1;
        else if ( rightRange.second().compareTo(leftRange.first()) < 0 )
            return 1;
        else {
            if (leftRange.isSecondInclusive() && !rightRange.isFirstInclusive() && leftRange.second().compareTo(rightRange.first()) == 0 )
                return -1;
            else if(rightRange.isSecondInclusive() && !leftRange.isFirstInclusive() && rightRange.second().compareTo(leftRange.first()) == 0)
                return 1;
            else return 0;

        }
    }

    private IntervalTreeNode<K> insert(IntervalTreeNode<K> root, RangePair<K> range) {
        if (root == null) {
            IntervalTreeNode<K> node = new IntervalTreeNode<>(range);
            return node;
        }
        int compValue = compare(root.range, range);
        if (compValue > 0)
            root.leftChild = insert(root.leftChild, range);
        else if (compValue < 0)
            root.rightChild = insert(root.rightChild, range);
        return root;
    }

    /*A public function to insert a range into the range tree.
      It first queries the ranges and then overlaps them using merge function.
      This overlapped range is inserted into the interval tree after deleting the
      overlapped ranges.
     */
    public void addRange(K l, boolean linclusive, K r, boolean rinclusive) {
        RangePair<K> range = RangePair.of(l, linclusive, r, rinclusive);
        List<RangePair<K>> overlappedPairs = query(l,linclusive,r, rinclusive);
        RangePair<K> mergedRange = merge(overlappedPairs, range);
        root = insert(root, mergedRange);
        for (RangePair<K> p : overlappedPairs)
            root = remove(root, p);
    }

    /*Overlap all the ranges and prepare a merged range.
     */
    private RangePair<K> merge(List<RangePair<K>> overlappedRanges, RangePair<K> range) {
        K min = range.first();
        K max = range.second();
        boolean isminInclusive = range.isFirstInclusive()? true : false;
        boolean isMaxInclusive = range.isSecondInclusive()? true : false;
        for (RangePair<K> r : overlappedRanges) {
            if (min.compareTo(r.first()) > 0) {
                min = r.first();
                isminInclusive = r.isFirstInclusive();
            }
            if (max.compareTo(r.second()) < 0) {
                max = r.second();
                isMaxInclusive = r.isSecondInclusive();
            }
        }
        return RangePair.of(min,isminInclusive,max,isMaxInclusive);
    }

    /*Internal function to remove a range from the interval tree node*/
    private IntervalTreeNode<K> remove(IntervalTreeNode<K> node, RangePair<K> range) {
        int compValue = compare(node.range, range);
        if (compValue == 0) {
            IntervalTreeNode<K> leftnode = getFirstGreater(node.rightChild);
            if (leftnode != null)
                return leftnode;
            return node.leftChild;
        }
        else if (compValue < 0)
            node.leftChild = remove(node.leftChild, range);
        else
            node.rightChild = remove(node.rightChild, range);
        return node;
    }

    private IntervalTreeNode<K> getFirstGreater(IntervalTreeNode<K> node) {
        if (node == null)
            return node;
        if (node.leftChild != null)
            return getFirstGreater(node.leftChild);
        return node;
    }

    /*This is public API function to delete a range from the range tree*/
    public void deleteRange(K l, boolean linclusive, K r, boolean rinclusive) {
        RangePair<K> range = RangePair.of(l,linclusive,r, rinclusive);
        List<RangePair<K>> overlappedPairs = query(l, linclusive, r, rinclusive);
        List<RangePair<K>> deletedRanges = delete(overlappedPairs, range);
        for (RangePair<K> p : overlappedPairs)
            root = remove(root, p);

        for (RangePair<K> p : deletedRanges)
            root = insert(root, p);
    }

    /*Internal function to get all the appropriately deleting a range from the overlapped range*/
    private List<RangePair<K>> delete(List<RangePair<K>> overlappedPairs, RangePair<K> range) {
        List<RangePair<K>> output = new ArrayList<>();
        for (RangePair<K> r : overlappedPairs) {
            assert (compare(r,range) ==0);
            //check for complete overlap
            if ((range.first().compareTo(r.first()) < 0 ||
                range.isFirstInclusive() && r.isFirstInclusive() && range.first().compareTo(r.first()) == 0)
                && (range.second().compareTo(r.second()) > 0 ||
                    range.isSecondInclusive() && r.isSecondInclusive() && range.second().compareTo(r.second()) == 0))
                continue;
            else if (range.first().compareTo(r.first()) > 0)
                output.add(RangePair.of(r.first(), r.isFirstInclusive(), range.first(), !range.isFirstInclusive()));
            else
                output.add(RangePair.of(range.second(), !range.isSecondInclusive(), r.second(), r.isSecondInclusive()));
        }
        return output;
    }

    /*Public function to get the overlapped ranges in the given range*/
    public List<RangePair<K>> query(K l, boolean linclusive, K r, boolean rinclusive ) {
        RangePair<K> range = RangePair.of(l,linclusive, r, rinclusive);
        List<RangePair<K>> result = new ArrayList<>();
        query(root,range, result);
        return result;
    }

    /*Internal recursive function to do the bulk of logic for getting overlapped ranges*/
    private void query(IntervalTreeNode<K> node, RangePair<K> range, List<RangePair<K>> output) {
        if (node == null)
            return;
        int compValue = compare(node.range, range);
        if (compValue == 0) {
            output.add(node.range);
            if (node.range.first().compareTo(range.first()) > 0)
                query(node.leftChild,range, output);
            if (node.range.second().compareTo(range.second()) < 0)
                query(node.rightChild,range,output);
        } else if (compValue > 0)
            query(node.leftChild, range, output);
        else
            query(node.rightChild, range, output);
    }

    /*public function with just one elem. this means [l,l] or (l,l)
    * but I think only [l,l] makes sense.*/
    public List<RangePair<K>> query(K l, boolean inclusive) {
        List<RangePair<K>> result = new ArrayList<>();
        query(root, RangePair.of(l, inclusive, l, inclusive), result);
        return result;
    }
}

