package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Termination {

    //attributs
    private LocalDate terminationInclusive;
    private long numberOfOccurrences;

    public LocalDate terminationDateInclusive() {
        return terminationInclusive;
    }

    public long numberOfOccurrences() {
        return numberOfOccurrences;
    }


    /**
     * Constructs a fixed termination event ending at a given date
     * @param start the start time of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     * @see ChronoUnit#between(Temporal, Temporal)
     */
    public Termination(LocalDate start, ChronoUnit frequency, LocalDate terminationInclusive) {
        this.terminationInclusive = terminationInclusive;

        switch (frequency) {
            case DAYS:
                this.numberOfOccurrences = ChronoUnit.DAYS.between(start, terminationInclusive) + 1;
                break;

            case WEEKS:
                this.numberOfOccurrences = ChronoUnit.WEEKS.between(start, terminationInclusive) + 1;
                break;

            case MONTHS:
                this.numberOfOccurrences = ChronoUnit.MONTHS.between(start, terminationInclusive) +1;
                break;

            case YEARS:
                this.numberOfOccurrences = ChronoUnit.YEARS.between(start, terminationInclusive) + 1;
                break;

        }
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     * @param start the start time of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public Termination(LocalDate start, ChronoUnit frequency, long numberOfOccurrences) {

        this.numberOfOccurrences = numberOfOccurrences;


        switch (frequency) {
            case DAYS:
                this.terminationInclusive = start.plusDays(numberOfOccurrences - 1);
                break;

            case WEEKS:
                this.terminationInclusive = start.plusWeeks(numberOfOccurrences - 1);
                break;


            case MONTHS:
                this.terminationInclusive = start.plusMonths(numberOfOccurrences - 1);
                break;


            case YEARS:
                this.terminationInclusive = start.plusYears(numberOfOccurrences - 1);
                break;

        }

    }

}
