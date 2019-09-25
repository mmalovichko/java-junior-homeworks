package unit3;


public class KnightFight {
    private final Knight first;
    private final Knight second;
    private final boolean firstGoesFirst;

    public KnightFight(Knight first, Knight second, boolean firstGoesFirst) {
        this.first = first;
        this.second = second;
        this.firstGoesFirst = firstGoesFirst;
    }

    public void fight() {
    }

    public Knight getFirst() {
        return first;
    }

    public Knight getSecond() {
        return second;
    }

    public boolean isFirstGoesFirst() {
        return firstGoesFirst;
    }
}
