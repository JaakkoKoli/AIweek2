package fi.helsinki.cs.reittiopas;

import fi.helsinki.cs.reittiopas.logic.Stop;
import fi.helsinki.cs.reittiopas.logic.State;
import java.util.Comparator;

public class StateComparator implements Comparator<State> {

    private final Stop goal;

    public StateComparator(Stop goalStop) {
        this.goal = goalStop;
    }

    /**
     * Implement this
     *
     * @param stop
     * @return Estimated remaining time
     */
    public double heuristic(Stop stop) {
        return Math.sqrt((double) (Math.pow(stop.getX()-this.goal.getX(), 2)+Math.pow(stop.getY()-this.goal.getY(), 2)))/260;
    }

    /**
     * Implement this
     *
     * @param t1
     * @param t2
     * @return result of the comparison
     */
    @Override
    public int compare(State t1, State t2) {
        return (int) ((int) (t1.getCurrentTime()+this.heuristic(t1.getStop()))-(t2.getCurrentTime()+this.heuristic(t2.getStop())));
    }

}
