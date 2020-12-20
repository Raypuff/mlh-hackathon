import java.util.Date;
import java.util.List;
import java.util.ArrayList;

class Patient {
    private final String firstName;
    private final String lastName;
    private final String ICNumber;
    private final int floor;
    private final String ward;
    private final int bedNumber;
    private final Date stayFrom;
    private final Date stayTo;
    private final boolean wantVisitors;
    private List<Visitor> listOfVisitors;

    public Patient(String firstName,
            String lastName,
            String ICNumber,
            int floor,
            String ward,
            int bedNumber,
            Date stayFrom,
            Date stayTo,
            boolean wantVisitors) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ICNumber = ICNumber;
        this.floor = floor;
        this.ward = ward;
        this.bedNumber = bedNumber;
        this.stayFrom = stayFrom;
        this.stayTo = stayTo;
        this.wantVisitors = wantVisitors;
        this.listOfVisitors = new ArrayList<>();
    }

    int getFloor() {
        return this.floor;
    }

    String getWard() {
        return this.ward;
    }

    int getBedNumber() {
        return this.bedNumber;
    }

    String getICNumber() {
        return this.ICNumber;
    }

    boolean getWantVisitors() {
        return this.wantVisitors;
    }

    int getNumberOfVisitors() {
        return this.listOfVisitors.size();
    }

    String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    String getLocation() {
        return String.format("Floor %s, Ward %s, Bed %s", this.floor, this.ward, this.bedNumber);
    }

    void addVisitor(Visitor visitor) {
        this.listOfVisitors.add(visitor);
    }

    
}

