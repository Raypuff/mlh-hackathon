import java.util.Date;

class Condition {
    private final double maxTemperature;
    private final int numberOfVisitors;
    private final int visitingTimeStart;
    private final int visitingTimeEnd;
    private final boolean maskNeeded;

    public Condition(double maxTemperature,
            int numberOfVisitors,
            int visitingTimeStart,
            int visitingTimeEnd,
            boolean maskNeeded) {
        this.maxTemperature = maxTemperature;
        this.numberOfVisitors = numberOfVisitors;
        this.visitingTimeStart = visitingTimeStart;
        this.visitingTimeEnd = visitingTimeEnd;
        this.maskNeeded = maskNeeded;
    }

    @Override
    public String toString() {
        return String.format("Max Temp: %s\nMax Visitors: %s\nVisiting Hours: %04d - %04d\nMask Needed: %s",
                maxTemperature,
                numberOfVisitors,
                visitingTimeStart,
                visitingTimeEnd,
                maskNeeded);
    }    

    boolean checks(Visitor visitor, Patient patient) {
        boolean patientWantVisitors = patient.getWantVisitors();
        System.out.println(String.format("Does the patient want visitors: %s", patientWantVisitors));
        boolean visitorsNotTooMany = patient.getNumberOfVisitors() < this.numberOfVisitors;
        System.out.println(String.format("Is the number of visitors less than the maximum allowed: %s", visitorsNotTooMany));
        System.out.println(String.format("Are masks needed: %s", this.maskNeeded));
        boolean isVisitorWearingMask = visitor.getWearingMask();
        System.out.println(String.format("Is the visitor wearing a mask: %s", isVisitorWearingMask));
        boolean isVisitorTemperatureLow = visitor.getTemperature() <= this.maxTemperature;
        System.out.println(String.format("Is the visitor's temperature safe: %s", isVisitorTemperatureLow));

        if (patientWantVisitors) {
            if (visitorsNotTooMany) {
                if (isVisitorTemperatureLow) {
                    if (!(this.maskNeeded)) {
                        return true; 
                    } else {
                        if (isVisitorWearingMask) {
                            return true;
                        }
                    }   
                }
            }
        }
        return false;
    }
}


