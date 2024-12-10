package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    private TreeSet<Event> myEvents;
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        myEvents.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> events = new ArrayList<>();
        for (Event e : myEvents) {
            if ( e.isInDay(day)){
                events.add(e);
            }
        }
        return events;
    }
}
