import java.util.HashMap;

class Database {
    private HashMap<Integer, HashMap<String, HashMap<Integer, Patient>>> database;
    private HashMap<String, Patient> ICNumbers;

    Database() {
        this.database = new HashMap<>();
    }

    Database(int numberOfFloors, int numberOfWards, int numberOfBeds) {
        this.database = new HashMap<>();
        this.ICNumbers = new HashMap<>();
        HashMap<Integer, Patient> beds;
        HashMap<String, HashMap<Integer, Patient>> wards;
        for (int i = 0; i < numberOfFloors; i++) {
            wards = new HashMap<>();
            for (int j = 0; j < numberOfWards; j++) {
                beds = new HashMap<>();
                for (int k = 0; k < numberOfBeds; k++) { 
                    beds.put(k + 1, null);
                }
                wards.put(Character.toString((char) j + 65), beds);
            }
            this.database.put(i + 1, wards); //assuming floor starts from 1
        }
    }
           
    public void addPatient(Patient patient) {
        int floor = patient.getFloor();
        String ward = patient.getWard();
        int bedNumber = patient.getBedNumber();
        String patientICNumber = patient.getICNumber();
        this.database.get(floor).get(ward).put(bedNumber, patient); 
        this.ICNumbers.put(patientICNumber, patient);
    }

    public Patient searchByICNumber(String ICNumber) {
        return this.ICNumbers.get(ICNumber);
    }

    public void addVisitor(Visitor visitor, Patient patient) {
        int floor = patient.getFloor();
        String ward = patient.getWard();
        int bedNumber = patient.getBedNumber();
        this.database.get(floor).get(ward).get(bedNumber).addVisitor(visitor);
    }
   

   
}


