package agenda;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The duration of the event
     */
    private Duration myDuration;

    private Repetition myRepetition;




    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    public void setRepetition(ChronoUnit frequency) {
        this.myRepetition = new Repetition(frequency);
    }

    public void addException(LocalDate date) {
        this.myRepetition.addException(date);
    }

    public void setTermination(LocalDate terminationInclusive) {
        this.myRepetition.setTermination( new Termination (myStart.toLocalDate(), this.myRepetition.getFrequency(), terminationInclusive) );
    }

    public void setTermination(long numberOfOccurrences) {
        this.myRepetition.setTermination( new Termination (myStart.toLocalDate(), this.myRepetition.getFrequency(), numberOfOccurrences) );
    }

    public int getNumberOfOccurrences() {
        return Math.toIntExact(this.myRepetition.getTermination().numberOfOccurrences());
    }

    public LocalDate getTerminationDate() {
        return this.myRepetition.getTermination().terminationDateInclusive();
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {

        if (myRepetition != null){
            if (myRepetition.getTermination() != null) {
                if (myRepetition.getTermination().terminationDateInclusive() != null) {
                    if (aDay.isEqual(this.myRepetition.getTermination().terminationDateInclusive().plusDays(1))) { // s'il s'agit du jour de terminaison inclu
                        return true;
                    }
                }
            }

        }

        boolean result = false;

        if (myRepetition != null){
            if (myRepetition.getExceptionDates() != null){
                if (myRepetition.getExceptionDates().contains(aDay) ) { //si le jour concerné fait parti des exceptions, l'évènement n'y est pas inscrit
                    return false;
                }
            }
        }

        if(myRepetition == null){ //s'il n'y a pas de répétitions
            result = this.myStart.toLocalDate().equals( aDay ) || this.myStart.plus(this.myDuration).toLocalDate().isEqual( aDay );
        }
        else {
            LocalDate d = this.myStart.toLocalDate();
            ChronoUnit frequency = this.myRepetition.getFrequency();

            if (aDay.isEqual(d)){
                result = true;
            }

            else if (aDay.isBefore(d)){
                result =  false;
            }

            else if ( aDay.isAfter(d) ) { //Si la date que l'on recherche est après la date de l'évènement

                while ( aDay.isAfter(d) ) {

                    switch (frequency) {
                        case DAYS:
                            d = d.plusDays(1);
                            break;
                        case WEEKS:
                            d = d.plusWeeks(1);
                            break;
                        case MONTHS:
                            d = d.plusMonths(1);
                            break;
                        case YEARS:
                            d = d.plusYears(1);
                            break;
                    }

                    if ( aDay.isEqual(d) ) {
                        result = true;
                    }

                }


            }
        }
        return result;

    }
   
    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }



    //getters
    public Repetition getRepetition() {
        return myRepetition;
    }
    public String getMyTitle() {
        return myTitle;
    }

    public Duration getMyDuration() {
        return myDuration;
    }

    public LocalDateTime getMyStart() {
        return myStart;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }
}
