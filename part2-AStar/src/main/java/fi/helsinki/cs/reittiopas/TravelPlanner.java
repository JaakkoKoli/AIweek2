package fi.helsinki.cs.reittiopas;

import fi.helsinki.cs.reittiopas.logic.Stop;
import fi.helsinki.cs.reittiopas.logic.State;

import java.util.PriorityQueue;
import java.util.function.Predicate;

public class TravelPlanner {

    private StateComparator stateComparator;

    /**
     * Implement A*
     *
     */
    public State search(Stop start, Stop goal, int timeAtBeginning) {
        this.stateComparator = new StateComparator(goal);
        PriorityQueue<State> examinees = new PriorityQueue<>(stateComparator);
        State startState = new State(start, null, timeAtBeginning);
        examinees.add(startState);
        while (!examinees.isEmpty()) {
            State current = examinees.poll();
            if (current.getStop().equals(goal)) {
                return current;
            }
            current.getStop().getNeighbors().stream().filter(x -> examinees.stream().noneMatch(z -> z.getStop().equals(x))).forEach(x -> examinees.add(new State(x, current, current.getCurrentTime() + current.getStop().fastestTransition(x, current.getCurrentTime()))));
        }
        return null;
    }
}
