/* A pair class used to model a interval.*/
public class RangePair<K> {
    private K fst;
    private boolean firstInclusive;
    private K snd;
    private boolean secondInclusive;

    private RangePair() {
    }

    public static <K> RangePair<K> of(K first, boolean finclusive, K second, boolean sinclusive) {
        RangePair<K> result = new RangePair<>();
        result.fst = first;
        result.firstInclusive = finclusive;
        result.snd = second;
        result.secondInclusive = sinclusive;
        return result;
    }

    public K first() {
        return this.fst;
    }

    public boolean isFirstInclusive() {
        return this.firstInclusive;
    }

    public K second() {
        return this.snd;
    }

    public boolean isSecondInclusive() {
        return this.secondInclusive;
    }
}

