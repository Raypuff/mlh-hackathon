import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Comparator;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Now initializing the size of your hospital...");
        System.out.println("How many floors does your hospital have? Please key in an integer (eg. 6).");
        int numberOfFloors = scanner.nextInt();
        System.out.println("How many wards are there per floor? Please key in an integer (eg. 5).");
        int numberOfWards = scanner.nextInt(); 
        System.out.println("How many beds are there per ward? Please key in an integer (eg. 8).");    
        int numberOfBeds = scanner.nextInt();
        Database db = new Database(numberOfFloors, numberOfWards, numberOfBeds);

        List<String> visitorLog = new ArrayList<>();

        System.out.println("Now initializing a list of conditions...");
        System.out.println("What is the maximum temperature a visitor can have? Please key in a double (eg. 37.8).");
        double maxTemperature = scanner.nextDouble();
        System.out.println("What is the maximum number of visitors one patient can have? Please key in an integer (eg. 5).");
        int numberOfVisitors = scanner.nextInt(); 
        System.out.println("What is the start of visiting hours? Please key in an integer (eg. 0730).");    
        int visitingTimeStart = scanner.nextInt();
        System.out.println("What is the end of visiting hours? Please key in an integer (eg. 2300).");    
        int visitingTimeEnd = scanner.nextInt();
        System.out.println("Do visitors have to wear masks to visit? Please key in either y/n.");
        String maskNeededYN = scanner.next();
        boolean maskNeeded;
        if (maskNeededYN.equals("y")) {
            maskNeeded = true;
        } else {
            maskNeeded = false;
        } 
 
        Condition condition = new Condition(maxTemperature, numberOfVisitors, visitingTimeStart, visitingTimeEnd, maskNeeded);
        System.out.println(condition); 
        
        while (true) {
            System.out.println("These are the commands you can use:\n--> newPatient\n--> newVisitor\n--> End\n"); 
            String command = scanner.next();
            System.out.println(String.format("You have keyed in the command %s", command));
            if (command.equals("newPatient")) {
                System.out.println("What is the patient's first name?");
                String firstName = scanner.next();
                System.out.println("What is the patient's last name?");
                String lastName = scanner.next();
                System.out.println("What is the patient's IC?");
                String ICNumber = scanner.next();
                System.out.println("What is the patient's floor?"); 
                int floor = scanner.nextInt();
                System.out.println("What is the patient's ward letter?");
                String ward = scanner.next().toUpperCase();
                System.out.println("What is the patient's bed number?");
                int bedNumber = scanner.nextInt();
                System.out.println("Which year does the patient's stay start? (eg. 2020)");
                int startYear = scanner.nextInt();
                System.out.println("Which month does the patient's stay start? (eg. 12)");
                int startMonth = scanner.nextInt();
                System.out.println("Which day does the patient's stay start? (eg. 19)");
                int startDay = scanner.nextInt();
                Date stayFrom = new Date(startYear - 1900, startMonth - 1, startDay, 00, 00);
                System.out.println("Which year does the patient's stay end? (eg. 2020)");
                int endYear = scanner.nextInt();
                System.out.println("Which month does the patient's stay end? (eg. 12)");
                int endMonth = scanner.nextInt();
                System.out.println("Which day does the patient's stay end? (eg. 19)");
                int endDay = scanner.nextInt();
                Date stayTo = new Date(endYear - 1900, endMonth - 1, endDay, 23, 59);
                System.out.println("Would the patient like to receive visitors? Please key in either y/n.");
                String wantVisitorsYN = scanner.next();
                boolean wantVisitors;
                if (wantVisitorsYN.equals("y")) {
                    wantVisitors = true;
                } else {
                    wantVisitors = false;
                }

                Patient patient = new Patient(firstName, lastName, ICNumber, floor, ward, bedNumber, stayFrom, stayTo, wantVisitors);
                db.addPatient(patient);          

            } else if (command.equals("newVisitor")) {

                System.out.println("What is the visitor's first name?");
                String firstName = scanner.next();
                System.out.println("What is the visitor's last name?");
                String lastName = scanner.next();
                System.out.println("What is the visitor's IC?");
                String ICNumber = scanner.next();
                System.out.println("What is the visitor's temperature?"); 
                double temperature = scanner.nextDouble();
                System.out.println("Is the visitor wearing a mask? Please key in either y/n");
                String wearingMaskYN = scanner.next();
                boolean wearingMask;
                if (wearingMaskYN.equals("y")) {
                    wearingMask = true;
                } else {
                    wearingMask = false;
                }
                System.out.println("Which year is the visitor visiting? (eg. 2020)");
                int year = scanner.nextInt();
                System.out.println("Which month is the visitor visiting? (eg. 12)");
                int month = scanner.nextInt();
                System.out.println("Which day is the visitor visiting? (eg. 19)");
                int day = scanner.nextInt();
                System.out.println("What hour is the visitor visiting? (eg. 8)");
                int hourIn = scanner.nextInt(); 
                System.out.println("What minute is the visitor visiting? (eg. 30)");
                int minuteIn = scanner.nextInt();  
                System.out.println("What hour is the visitor leaving? (eg. 8)");
                int hourOut = scanner.nextInt();  
                System.out.println("What minute is the visitor leaving? (eg. 30)");
                int minuteOut = scanner.nextInt(); 
                Date timeIn = new Date(year - 1900, month - 1, day, hourIn, minuteIn);
                Date timeOut = new Date(year - 1900, month - 1, day, hourOut, minuteOut);
                System.out.println("What is the IC Number of the patient being visited?");
                String patientICNumber = scanner.next();
                Patient patient = db.searchByICNumber(patientICNumber); 
                Visitor visitor = new Visitor(firstName, lastName, ICNumber, temperature, wearingMask, timeIn, timeOut);
                System.out.println("Checking if the visitor is allowed to visit...");
                if (condition.checks(visitor, patient)) {
                    db.addVisitor(visitor, patient);
                    visitorLog.add(String.format("%s %s visits %s at %s",
                                visitor.getTimeIn(),
                                visitor.getName(),
                                patient.getName(),
                                patient.getLocation()));
                    visitorLog.add(String.format("%s %s leaves",
                                visitor.getTimeOut(),
                                visitor.getName()));
                    System.out.println("The visitor is allowed to visit.");
                } else {
                    System.out.println("The visitor is not allowed to visit.");
                }
                
            } else if (command.equals("End")) {
                visitorLog.sort(new Comparator<String>() {
                    public int compare(String s1, String s2) {
                        return Integer.compare(Integer.valueOf(s1.substring(0,4)), Integer.valueOf(s2.substring(0,4)));
                    }
                });
                for (String s: visitorLog) {
                    System.out.println(s);
                }
                System.out.println("Simulation ended.");
                break;

            } else {
                System.out.println("That command is invalid.");
            }
        } 
    
    }
}

//string log
