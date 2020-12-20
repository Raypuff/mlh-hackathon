import java.util.Date;

class Visitor {
    private final String firstName;
    private final String lastName;
    private final String ICNumber;
    private final double temperature;
    private final boolean wearingMask;
    private final Date timeIn;
    private final Date timeOut;

    public Visitor(String firstName,
            String lastName,
            String ICNumber,
            double temperature,
            boolean wearingMask,
            Date timeIn,
            Date timeOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ICNumber = ICNumber;
        this.temperature = temperature;
        this.wearingMask = wearingMask;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    double getTemperature() {
        return this.temperature;
    }

    boolean getWearingMask() {
        return this.wearingMask;
    }

    String getTimeIn() {
        return String.format("%02d%02d", this.timeIn.getHours(), this.timeIn.getMinutes());
    }

    String getTimeOut() {
        return String.format("%02d%02d", this.timeOut.getHours(), this.timeOut.getMinutes());
    }

    String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

}




