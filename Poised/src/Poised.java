/**
 * This code is a project management system, which keeps tracks of projects, customers, contractors and architects.
 * <p>
 * It can save projects, modify its details and generate reports.
 * @author Abdul Aleem Chilwan
 * @version 3.2
 */

// Importing necessary classes/modules
import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Poised {

    /**
     *
     * @param inputString user's input string.
     * @return returns true or false whether the input were numeric values.
     */
    // Method to check if value is numeric
    private static boolean isNumeric(String inputString) {
        if (inputString == null || inputString.length() == 0) {
            return false;
        } else {
            for (char c : inputString.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param inputEmail user's input email.
     * @return returns true or false whether the input contains a "@".
     */
    // Method to check if value is an email, by checking if it contains a "@"
    private static boolean isEmail(String inputEmail) {
        if (inputEmail == null || inputEmail.length() == 0) {
            return false;
        } else if (!inputEmail.contains("@")) {
            return false;
        } else {
            return true;
        }
    }
    /**
     *
     * @param inputDate user's date.
     * @return returns true or false whether the input contains a date value.
     */
    // Method to check if value co-insides with the scope of date values.
    private static boolean isDate(String inputDate){
        // Splitting the Date into year, month, date of type integer.
        String[] date = inputDate.split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        // If the date is empty, return false.
        if (inputDate == null || inputDate.length() == 0) {
            return false;}
        // If the date does not contain "-" ... it is not in the correct format
        if ((!inputDate.contains("-"))) {
            return false;}
        // Checking if the year entered is between 2000 and 2050 for it to be a valid timeframe.
        if (year < 1999) {
            return false;}
        if (year > 2051) {
            return false;}
        // Checking if a valid month
        if (month < 1) {
            return false;}
        if (month > 13) {
            return false;}
        // Checking if a valid date.
        if (day < 1) {
            return false;}
        if (day > 31) {
            return false;}

        return true;
    }

    // Main method.
    public static void main(String[] args) {

        //Initialising Scanner
        Scanner scn = new Scanner(System.in);

        /**
         * Initialising variables and ArrayList which will be kept as placeholders. Objects will be stored within them.
         */
        int choice;
        int lineAmount = 0;
        int projNumber = 0;
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Project> projects = new ArrayList<Project>();
        ArrayList<Contactor> contractors = new ArrayList<Contactor>();
        ArrayList<Architect> architects = new ArrayList<Architect>();


            /**
             * Creating a string to store all previous made objects from the textfile.
             */
        String projectInformation = "";
        // Using a try catch block to save the text file's data into a string.
        try {
            File file = new File("ProjectsMade.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                projectInformation += reader.nextLine();
                lineAmount++;
            }
            // Closing the Scanner
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

            /**
             * Adding all the information from the txt file into it's categorised details into an arrayList.
             */
            String[] textProject = projectInformation.split("!");
            ArrayList<String> projectNameText = new ArrayList<String>();
            ArrayList<String> buildingDesignText = new ArrayList<String>();
            ArrayList<String> addressText = new ArrayList<String>();
            ArrayList<String> erf = new ArrayList<String>();
            ArrayList<String> start = new ArrayList<String>();
            ArrayList<String> due = new ArrayList<String>();
            ArrayList<Double> charged = new ArrayList<Double>();
            ArrayList<Double> paid = new ArrayList<Double>();
            ArrayList<Boolean> finalised = new ArrayList<Boolean>();
            ArrayList<String> completeDate = new ArrayList<String>();

            ArrayList<String> customerName = new ArrayList<String>();
            ArrayList<String> customerTel = new ArrayList<String>();
            ArrayList<String> customerEmail = new ArrayList<String>();
            ArrayList<String> customerAddress = new ArrayList<String>();

            ArrayList<String> architectName = new ArrayList<String>();
            ArrayList<String> architectTel = new ArrayList<String>();
            ArrayList<String> architectEmail = new ArrayList<String>();
            ArrayList<String> architectAddress = new ArrayList<String>();

            ArrayList<String> constructorName = new ArrayList<String>();
            ArrayList<String> constructorTel = new ArrayList<String>();
            ArrayList<String> constructorEmail = new ArrayList<String>();
            ArrayList<String> constructorAddress = new ArrayList<String>();


            /**
             * Adding the categorised details into a project object, and adding it to an arraylist of projects.
             */
            int counter = 0;
            for (int i = 0; i < lineAmount; i ++) {
                projectNameText.add(textProject[counter]);
                counter++;
                buildingDesignText.add(textProject[counter]);
                counter++;
                addressText.add(textProject[counter]);
                counter++;
                erf.add(textProject[counter]);
                counter++;
                start.add(textProject[counter]);
                counter++;
                due.add(textProject[counter]);
                counter++;
                charged.add(Double.valueOf(textProject[counter]));
                counter++;
                paid.add(Double.valueOf(textProject[counter]));
                counter++;
                finalised.add(Boolean.valueOf(textProject[counter]));
                counter++;
                completeDate.add(textProject[counter]);
                counter++;

                customerName.add(textProject[counter]);
                counter++;
                customerTel.add(textProject[counter]);
                counter++;
                customerEmail.add(textProject[counter]);
                counter++;
                customerAddress.add(textProject[counter]);
                counter++;

                architectName.add(textProject[counter]);
                counter++;
                architectTel.add(textProject[counter]);
                counter++;
                architectEmail.add(textProject[counter]);
                counter++;
                architectAddress.add(textProject[counter]);
                counter++;

                constructorName.add(textProject[counter]);
                counter++;
                constructorTel.add(textProject[counter]);
                counter++;
                constructorEmail.add(textProject[counter]);
                counter++;
                constructorAddress.add(textProject[counter]);
                counter++;
            }

            // Adding the text file data into objects.
            for (int i = 0; i < projectNameText.size(); i++) {
                projNumber++;
                projects.add(new Project(projNumber, projectNameText.get(i), buildingDesignText.get(i), addressText.get(i), erf.get(i),
                        start.get(i), due.get(i), charged.get(i), paid.get(i), finalised.get(i), completeDate.get(i)));

                customers.add(new Customer(customerName.get(i),customerTel.get(i),customerEmail.get(i),customerAddress.get(i)));
                projects.get(i).setCustomer(customers.get(i));

                architects.add(new Architect(architectName.get(i),architectTel.get(i),architectEmail.get(i),architectAddress.get(i)));
                projects.get(i).setArchitect(architects.get(i));

                contractors.add(new Contactor(constructorName.get(i),constructorTel.get(i),constructorEmail.get(i),constructorAddress.get(i)));
                projects.get(i).setContactor(contractors.get(i));

            }

        while(true){
            try {
                // Asking the user to select their options, inside a while loop
                System.out.println("Welcome to Poised!".toUpperCase());
                while (true) {
                    System.out.println("===========================================================================");
                    System.out.println("Please select an option: ".toUpperCase());
                    System.out.println("1 - Create a project \n2 - Change due date of the project " +
                            "\n3 - Change amount paid to date \n4 - Change contractor's contact details \n5 - Finalise project " +
                            "\n6 - Search project \n7 - View incomplete projects \n8 - View projects past due date " +
                            "\n0 - Exit");

                    // Recording the user's option
                    choice = scn.nextInt();
                    scn.nextLine();

                    /**
                     * If user choice option 1, they will be prompted to create a customer which will be linked to the project.
                     */
                    if (choice == 1) {
                        // Asking user to enter the project details.
                        String projectName = "";
                        String buildingDesign = "";
                        while (true) {
                            try {
                                /**
                                 * Options are given to enter a project manually or via a text file.
                                 */
                                System.out.println("------ Select how to add a project: ------".toUpperCase());

                                // User chose to add a project manually...
                                    while (true) {

                                        /**
                                         * Asking user to fill in the project details, then adding the project to an array list.
                                         */
                                        System.out.println("----- Please enter the below information to successfully create a project -----");
                                        try {
                                            System.out.println("Enter the project's name: ");
                                            projectName = scn.nextLine();
                                            System.out.println("Enter the building design: ");
                                            buildingDesign = scn.nextLine();
                                            System.out.println("Enter the address of the project: ");
                                            String address = scn.nextLine();
                                            System.out.println("Enter the ERF number: ");
                                            String ERFNumber = scn.nextLine();
                                            System.out.println("Enter the start date: (YYYY-MM-DD)");
                                            String startDate = scn.nextLine();
                                            if (!isDate(startDate)) {
                                                System.out.println("Date invalid...");
                                                throw new Exception();
                                            }
                                            System.out.println("Enter the due date: (YYYY-MM-DD)");
                                            String dueDate = scn.nextLine();
                                            if (!isDate(dueDate)) {
                                                System.out.println("Date invalid...");
                                                throw new Exception();
                                            }
                                            System.out.println("Enter the amount charged: ");
                                            double feeCharged = scn.nextDouble();
                                            System.out.println("Enter the amount paid to date: ");
                                            double amountPaid = scn.nextDouble();
                                            scn.nextLine();

                                            /**
                                             * Creating the new project object and adding it to an ArrayList.
                                             */
                                            projects.add(new Project(projNumber + 1, projectName, buildingDesign, address, ERFNumber,
                                                    startDate, dueDate, feeCharged, amountPaid));

                                            System.out.println("------------------------------------------");
                                            System.out.println(" ------Project Successfully Entered ------");
                                            System.out.println("------------------------------------------");
                                            projNumber++;
                                            break;
                                        } catch (Exception e) {
                                            System.out.println("Please try again... ");
                                            System.out.println("==========================================");
                                        }
                                        //break;
                                    }
                                    break;
                            } catch (Exception e) {
                                System.out.println("Please try again.");
                            }
                        }

                        /**
                         * After creating a project, a customer should be linked to the project.
                         */
                        while (true) {
                            try {
                                // Asking the user which method to use to a customer to the project.
                                System.out.println("------ Create a customer which will be linked to this project ------");
                                    while (true) {
                                        try {
                                            System.out.println("Please enter the client's name: ");
                                            String name = scn.nextLine();
                                            System.out.println("Please enter the telephone number: ");
                                            String tellNo = scn.nextLine();

                                            if (!isNumeric(tellNo)) {
                                                System.out.println("Telephone number should only contain numerical values, Please try again.");
                                                throw new Exception();
                                            }

                                            System.out.println("Please enter the email address: ");
                                            String emailAddress = scn.nextLine();

                                            if (!isEmail(emailAddress)) {
                                                System.out.println("Email address should contain '@', Please try again.");
                                                throw new Exception();
                                            }

                                            System.out.println("Please enter the client's physical address: ");
                                            String physAddress = scn.nextLine();

                                            /**
                                             * Creating a customer object and adding it to an ArrayList.
                                             */
                                            customers.add(new Customer(name, tellNo, emailAddress, physAddress));
                                            /**
                                             * Setting the customer created to be linked to this project object created.
                                             */
                                            projects.get(projNumber - 1).setCustomer(customers.get(projNumber - 1));
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e);
                                            System.out.println("================================");
                                        }
                                    }
                                    if (projects.get(projNumber - 1).getProjName().isEmpty() || projects.get(projNumber - 1).getProjName().isBlank()) {
                                        // Getting the customer's last name.
                                        projects.get(projNumber - 1).setProjName(projects.get(projNumber - 1).getBuildingDesign() + " " + customers.get(projNumber - 1).getName().substring(customers.get(0).getName().lastIndexOf(" ") + 1));
                                    }
                                    break;
                            } catch (Exception e) {
                                System.out.println("Please try again.");
                            }
                        }

                        while (true) {
                            try {
                                // Asking the user which method to use to add an architect to the project.
                                System.out.println("------ Create an architect which will be linked to this project ------");
                                    while (true) {
                                        try {
                                            System.out.println("Please enter the architect name: ");
                                            String name = scn.nextLine();
                                            System.out.println("Please enter the telephone number: ");
                                            String tellNo = scn.nextLine();

                                            if (!isNumeric(tellNo)) {
                                                System.out.println("Telephone number should only contain numerical values, Please try again.");
                                                throw new Exception();
                                            }

                                            System.out.println("Please enter the email address: ");
                                            String emailAddress = scn.nextLine();

                                            if (!isEmail(emailAddress)) {
                                                System.out.println("Email address should contain '@', Please try again.");
                                                throw new Exception();
                                            }

                                            System.out.println("Please enter the client's physical address: ");
                                            String physAddress = scn.nextLine();

                                            /**
                                             * Creating an architect object and adding it to an ArrayList.
                                             */
                                            architects.add(new Architect(name, tellNo, emailAddress, physAddress));
                                            /**
                                             * Setting the Architect created to be linked to this project object created.
                                             */
                                            projects.get(projNumber - 1).setArchitect(architects.get(projNumber - 1));
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e);
                                            System.out.println("================================");
                                        }
                                    }
                                    break;

                            } catch (Exception e) {
                                System.out.println("Please try again.");
                            }
                        }

                        while (true) {
                            try {
                                // Asking the user which method to use to add a contractor to the project.
                                System.out.println("------ Create an contractor which will be linked to this project ------");
                                    while (true) {
                                        try {
                                            System.out.println("Please enter the contractor name: ");
                                            String name = scn.nextLine();
                                            System.out.println("Please enter the telephone number: ");
                                            String tellNo = scn.nextLine();

                                            if (!isNumeric(tellNo)) {
                                                System.out.println("Telephone number should only contain numerical values, Please try again.");
                                                throw new Exception();
                                            }

                                            System.out.println("Please enter the email address: ");
                                            String emailAddress = scn.nextLine();

                                            if (!isEmail(emailAddress)) {
                                                System.out.println("Email address should contain '@', Please try again.");
                                                throw new Exception();
                                            }

                                            System.out.println("Please enter the client's physical address: ");
                                            String physAddress = scn.nextLine();

                                            /**
                                             * Creating an contractor object and adding it to an ArrayList.
                                             */
                                            contractors.add(new Contactor(name, tellNo, emailAddress, physAddress));
                                            /**
                                             * Setting the contractor created to be linked to this project object created.
                                             */
                                            projects.get(projNumber - 1).setContactor(contractors.get(projNumber - 1));
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e);
                                            System.out.println("================================");
                                        }
                                    }
                                    break;
                            } catch (Exception e) {
                                System.out.println("Please try again.");
                            }
                        }
                    }

                    /**
                     * If the user choice option 2, They will get a list of project objects (if there are) and be asked which one to change
                     */
                    else if (choice == 2) {
                        if (projects.isEmpty()) {
                            System.out.println("===========================================================================");
                            System.out.println("There are no current project...");
                        } else {
                            System.out.println("------ Which project's due date would you like to change? (YYYY-MM-DD) ------" );
                            int count = 0;
                            int index = 1;

                            // Loops through the project objects.
                            for (Project i : projects) {
                                System.out.println(index + " - " + projects.get(count).getProjName());
                                count++;
                                index++;
                            }

                            /**
                             * Displaying details and asking for the new due date.
                             */
                            System.out.println("Enter your choice: ");
                            int dueDateChoice = scn.nextInt();
                            scn.nextLine();
                            System.out.println("You selected project: " + projects.get(dueDateChoice - 1).getProjName());
                            System.out.println("The current due date is: " + projects.get(dueDateChoice - 1).getDueDate());
                            while (true) {
                                try {
                                    System.out.println("===========================================================================");
                                    System.out.println("------ What would you like the new date to be? (YYYY-MM-DD) ------");
                                    String newDueDate = scn.nextLine();
                                    if (!isDate(newDueDate)) {
                                        System.out.println("Date invalid...");
                                        throw new Exception();
                                    }
                                    /**
                                     * Saving the new due date for that project.
                                     */
                                    projects.get(dueDateChoice - 1).setDueDate(newDueDate);

                                    System.out.println("Project " + projects.get(dueDateChoice - 1).getProjName() + " has changed the due date to " +
                                            projects.get(dueDateChoice - 1).getDueDate());
                                    break;

                                } catch (Exception e) {
                                    System.out.println("Please try again...");
                                }
                            }
                        }
                    }

                    /**
                     * If the user choice option 3, They will be shown a list of project objects (if there are any) and be asked
                     * for the new amount to be entered.
                     */
                    else if (choice == 3) {
                        if (projects.isEmpty()) {
                            System.out.println("===========================================================================");
                            System.out.println("------ There are no current project. ------");

                        } else {
                            System.out.println("------ Which project's paid amount would you like to change? ------");
                            int count = 0;
                            int index = 1;
                            for (Project i : projects) {
                                System.out.println(index + " - " + projects.get(count).getProjName());
                                count++;
                            }
                            System.out.println("Enter your choice: ");
                            int changeProjectPaidAmount = scn.nextInt();

                            /**
                             *  Displaying the data and asking for the new amount.
                             */
                            System.out.println("You selected project: " + projects.get(changeProjectPaidAmount - 1).getProjName());
                            System.out.println("The total amount due is: " + projects.get(changeProjectPaidAmount - 1).getFeeCharged());
                            System.out.println("The current amount paid is: " + projects.get(changeProjectPaidAmount - 1).getAmountPaid());
                            System.out.println("------ What would you like the new paid amount to be? ------");
                            double newPaidAmount = scn.nextDouble();

                            /**
                             *  Setting the new amount paid to that project.
                             */
                            projects.get(changeProjectPaidAmount - 1).setAmountPaid(newPaidAmount);

                            // Printing the successful change.
                            System.out.println("Project " + projects.get(changeProjectPaidAmount - 1).getProjName() + " has changed the amount paid to " +
                                    projects.get(changeProjectPaidAmount - 1).getAmountPaid());
                        }
                    }

                    /**
                     *  Showing a list of contractor objects ( if there are any) and which details they would like to update.
                     */
                    else if (choice == 4) {
                        if (contractors.isEmpty()) {
                            System.out.println("===========================================================================");
                            System.out.println("------ There are no current contractors. ------");
                        } else {
                            System.out.println("------ Please select a contract to update their details: ------");
                            int count = 0;
                            int index = 1;
                            for (Contactor i : contractors) {
                                System.out.println(index + " - " + contractors.get(count).getName());
                                count++;
                                index++;
                            }

                            /**
                             *  SAsking for the user's choice on which details they want to update.
                             */
                            System.out.println("Enter your choice: ");
                            int selectedContractor = scn.nextInt();

                            System.out.println("Contractor selected: " + contractors.get(selectedContractor - 1).getName());
                            System.out.println("------ Which details would you like to update? ------");
                            System.out.println("1 - Name \n2 - Telephone Number \n3 - Email Address \n4 - Physical Address");
                            System.out.println("Enter your choice: ");
                            int selectedUpdateDetails = scn.nextInt();

                            /**
                             *  Based off the user's choice, asking them to prompt the new data and saving it to that contractor's object.
                             */
                            if (selectedUpdateDetails == 1) {
                                System.out.println("Please enter the updated name: ");
                                String newContratorName = scn.next();
                                contractors.get(selectedContractor - 1).setName(newContratorName);
                            }
                            if (selectedUpdateDetails == 2) {
                                System.out.println("Please enter the updated telephone number: ");
                                String newContratorNumber = scn.next();
                                contractors.get(selectedContractor - 1).setTellNo(newContratorNumber);
                            }
                            if (selectedUpdateDetails == 3) {
                                System.out.println("Please enter the updated email: ");
                                String newContratorEmail = scn.next();
                                contractors.get(selectedContractor - 1).setEmailAddress(newContratorEmail);
                            }
                            if (selectedUpdateDetails == 4) {
                                System.out.println("Please enter the updated physical address: ");
                                String newContratorPhysAddress = scn.next();
                                contractors.get(selectedContractor - 1).setPhysAddress(newContratorPhysAddress);
                            }
                            System.out.println("------------------- Successfully Updated ----------------------------");
                        }
                    }

                    /**
                     *  Displaying project which could be finalised.
                     */
                    else if (choice == 5) {
                        if (projects.isEmpty()) {
                            System.out.println("===========================================================================");
                            System.out.println("------ There are no current project. ------");

                        } else {
                            System.out.println("------ Which project would you like to finalise? ------");
                            int count = 0;
                            int index = 1;
                            for (Project i : projects) {
                                System.out.println(index + " - " + projects.get(count).getProjName());
                                index++;
                                count++;
                            }
                            System.out.println("Enter your choice: ");
                            int projectToFinalise = scn.nextInt();

                            /**
                             *  Finalising the project and calling the method from the Project class.
                             */
                            projects.get(projectToFinalise - 1).finaliseProject();
                        }
                    } else if (choice == 6) {
                        System.out.println("------ Please pick an option on how to search for a project: ------");
                        System.out.println("1 - Project number.");
                        System.out.println("2 - Project name.");
                        int projectChoice = scn.nextInt();
                        scn.nextLine();

                        if (projectChoice == 1) {
                            if (projects.isEmpty()) {
                                System.out.println("There are no current projects.");
                            } else {
                                System.out.println("------ Please enter the project number: ------");
                                int projectNumberChoice = scn.nextInt();
                                try {
                                    if (projects.get(projectNumberChoice - 1).getProjName() != null) {
                                        System.out.println("PROJECT " + projectNumberChoice + " DEATILS:");
                                        System.out.println("--------------------------------------------------");

                                        System.out.println(projects.get(projectNumberChoice - 1).toString());

                                    }
                                } catch (Exception e) {
                                    System.out.println("Project does not exist.");
                                }

                            }
                        }

                        if (projectChoice == 2) {
                            if (projects.isEmpty()) {
                                System.out.println("------ There are no current projects. ------");
                            } else {
                                System.out.println("Please enter the project name:");
                                String projectNameChoice = scn.nextLine();
                                for (int i = 0; i < projects.size(); i++) {
                                    if (projectNameChoice.equals(projects.get(i).getProjName())) {
                                        System.out.println("PROJECT " + projectNameChoice.toUpperCase(Locale.ROOT) + " DETAILS:");
                                        System.out.println("--------------------------------------------------");
                                        System.out.println(projects.get(i).toString());

                                        System.out.println("--------------------------------------------------");
                                        System.out.println("Customer's details for this project:");
                                        System.out.println((projects.get(i).getCustomer().toString()));
                                        break;
                                        //Breaks the loop once its found
                                    }
                                    // Add another else if .. if its the last iteration number, and does not equal, display message.
                                    else if (i == projects.size() - 1) {
                                        System.out.println("------ No project of that name was found. ------");
                                        ;
                                        // If not found, continue
                                    }
                                }
                            }
                        }
                    }

                    /**
                     *  Displaying all incomplete projects.
                     */
                    else if (choice == 7) {
                        int inCompleted = 0;
                        System.out.println("------ Displaying all incomplete projects: ------ ");
                        for (int i = 0; i < projects.size(); i++) {
                            if (projects.get(i).completionDate == null) {
                                System.out.println("Project No: " + projects.get(i).getProjNo() + " - " + projects.get(i).getProjName());
                                inCompleted++;
                            }
                        }
                        if (inCompleted == 0) {
                            System.out.println("------ There are currently no incomplete projects ------");
                        }
                    }

                    /**
                     *  Comparing the due date to current date, and displaying them if they are past the due date.
                     */
                    if (choice == 8) {
                        int pastDueDate = 0;
                        LocalDate dateObj = LocalDate.now();

                        /**
                         *  Create a date object in format YYYY-MM-DD
                         */
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String date = dateObj.format(formatter);
                        LocalDate currDate = LocalDate.parse(date);

                        System.out.println("------ Displaying all projects past its due date: ------");
                        for (int i = 0; i < projects.size(); i++) {
                            LocalDate projDate = LocalDate.parse(projects.get(i).getDueDate());
                            if (currDate.isAfter(projDate)) {
                                System.out.println("Project No: " + projects.get(i).getProjNo() + " - " + projects.get(i).getProjName());
                                pastDueDate++;
                            }
                        }
                        if (pastDueDate == 0) {
                            System.out.println("------ There are currently no projects past the due date. ------ ");
                        }
                    }

                    /**
                     *  Existing the program and generating a txt file of project details.
                     */
                    else if (choice == 0) {
                        System.out.println("------ Generating 'ProjectSummary.txt' with all project information. ------ ");
                        System.out.println("--------------------------------------------------");
                        System.out.println("Good Bye !");

                        try {
                            /**
                             *  Creating a file and writing to it.
                             */
                            FileWriter myWriter = new FileWriter("ProjectSummary.txt");
                            for (int i = 0; i < projects.size(); i++) {
                                myWriter.write("Project: " + projects.get(i).getProjName().toUpperCase());
                                myWriter.write("\n=======================================================\n");
                                myWriter.write(projects.get(i).toString());
                                myWriter.write("\n*********************************************************\n");
                                myWriter.write(" \n ");
                            }

                            myWriter.close();

                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }

                        // Assign the file content
                        FileOutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream("ProjectsMade.txt");
                            for (int i = 0 ; i < projects.size(); i ++){
                                String fileContent = projects.get(i).getProjName() + "!" + projects.get(i).getBuildingDesign()
                                        + "!" + projects.get(i).getAddress() + "!" + projects.get(i).getERFNumber()
                                        + "!" + projects.get(i).getStartDate() + "!" + projects.get(i).getDueDate()
                                        + "!" + projects.get(i).getFeeCharged() + "!" + projects.get(i).getAmountPaid()
                                        + "!" + projects.get(i).isFinalised() + "!" + projects.get(i).getCompletionDate()
                                        + "!" + customers.get(i).getName() + "!" + customers.get(i).getTellNo()
                                        + "!" + customers.get(i).getEmailAddress() + "!" + customers.get(i).getPhysAddress()
                                        + "!" + architects.get(i).getName() + "!" + architects.get(i).getTellNo()
                                        + "!" + architects.get(i).getEmailAddress() + "!" + architects.get(i).getPhysAddress()
                                        + "!" + contractors.get(i).getName() + "!" + contractors.get(i).getTellNo()
                                        + "!" + contractors.get(i).getEmailAddress() + "!" + contractors.get(i).getPhysAddress() + "!\n";

                                byte[] strToBytes = fileContent.getBytes();
                                outputStream.write(strToBytes);
                            }
                        }
                        catch (IOException e) {
                            System.out.print(e.getMessage());
                        }
                        finally {
                            if (outputStream != null) {

                                try {
                                    outputStream.close();
                                }

                                catch (IOException e) {
                                    System.out.print(e.getMessage());
                                }
                            }
                        }

                        // Closing the scanner and closing the application.
                        scn.close();
                        System.exit(1);
                    }

                    // Asking user to enter an option again.
                    else {
                        System.out.println("===========================================================================");
                        System.out.println("Please select a number on which action to execute. ");
                    }
                }
            }catch(Exception e){
                System.out.println(e);
                System.out.println("Please try again.");
                scn.nextLine();
            }
        }
    }
}
