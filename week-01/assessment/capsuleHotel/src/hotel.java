import java.util.Scanner;


public class hotel {

        public static String returnString(String prompt) {
            Scanner console = new Scanner(System.in);
            String result;
            do {
                System.out.print(prompt);
                result = console.nextLine();
            } while (result.length() == 0);
            return result;
        }


    public static void main(String[] args) {
        System.out.println("Welcome to the Capsule hotel. \nEnter the number of available capsules:");
        Scanner console = new Scanner(System.in);
        int avaCaps = Integer.parseInt(console.nextLine());

        while (!(avaCaps > 0)) {
            System.out.println("I don't understand.");
            avaCaps = Integer.parseInt(console.nextLine());
        }

        String[] caps = new String[avaCaps];
        System.out.println("There are " + avaCaps + " unoccupied capsules ready to be booked.");

        String option = "0";
        String command;

        while (!option.equals("end")) {
            switch (option) {
                case "0": System.out.println("Guest Menu\n==========\n1. Check In\n2. Check Out\n3. View Guests\n4. Exit\nChoose an option [1-4]:");
                          command = console.nextLine();
                          if (command.equals("1")){
                              option = "1";
                          } else if (command.equals("2")) {
                              option = "2";
                          } else if (command.equals("3")) {
                              option = "3";
                          } else if (command.equals("4")) {
                              option = "4";
                          } else {
                              System.out.println("I don't understand.");
                          }
                          break;

                case "1": System.out.println("Guest Check In\n==============\n");
                          String name = returnString("Guest Name: ");
                          System.out.println(name + " is checking in to which capsule from #1 - " + avaCaps + "?");

                          int capNum = -1;
                          capNum = Integer.parseInt(console.nextLine());

                          while (!((capNum > 0) && (capNum <= avaCaps))) {
                              System.out.println("I don't understand. Choose a capsule number from #1 - " + avaCaps + ".");
                              capNum = Integer.parseInt(console.nextLine());}

                          while (!(caps[capNum - 1] == null)) {
                                  System.out.println("Error. Capsule number " + capNum + "is occupied. Choose another capsule number from #1 - " + avaCaps + ".");
                                  capNum = Integer.parseInt(console.nextLine());
                              while (!((capNum > 0) && (capNum <= avaCaps))) {
                                  System.out.println("I don't understand. Choose a capsule number from #1 - " + avaCaps + ".");
                                  capNum = Integer.parseInt(console.nextLine());}
                                    }

                          caps[capNum - 1] = name;
                          System.out.println("Success! " + name + " has checked in to capsule number " + capNum + ".");
                          option = "0";
                          break;

                case "2":
                    System.out.println("Guest Check Out\n==============\nCapsule #1 - " + avaCaps);
                    int capNumOut = Integer.parseInt(console.nextLine());

                    while (!((capNumOut > 0) && (capNumOut <= avaCaps))) {
                        System.out.println("I don't understand. Choose a capsule number from #1 - " + avaCaps + ".");
                        capNumOut = Integer.parseInt(console.nextLine());}

                    while (caps[capNumOut - 1] == null) {
                        System.out.println("Error. Capsule number " + capNumOut + " is unoccupied. Choose another capsule number from #1 - " + avaCaps + ".");
                        capNumOut = Integer.parseInt(console.nextLine());
                        while (!((capNumOut > 0) && (capNumOut <= avaCaps))) {
                            System.out.println("I don't understand. Choose a capsule number from #1 - " + avaCaps + ".");
                            capNumOut = Integer.parseInt(console.nextLine());}
                    }

                    System.out.println(caps[capNumOut - 1] + " is checked out from capsule number " + capNumOut + ".");
                    caps[capNumOut - 1] = null;
                    option = "0";

                    break;

                case "3":
                    System.out.println("View Guests\n===========\nCapsule #1 - " + avaCaps + ".");
                    int numOfInt = Integer.parseInt(console.nextLine());

                    while (!((numOfInt > 0) && (numOfInt <= avaCaps))) {
                        System.out.println("I don't understand. Choose a capsule number from #1 - " + avaCaps + ".");
                        numOfInt = Integer.parseInt(console.nextLine());}

                    System.out.println("Capsule: Guest");
                    if (((numOfInt + 5) > avaCaps) && (numOfInt < 6)) {
                        for (int start = 0; start < avaCaps; start++) {
                            String names = caps[start];
                            System.out.printf("%s : %s.%n", (start + 1), names);
                        }
                    }
                    else if ((numOfInt + 5) > avaCaps) {
                        for (int start = (numOfInt - 6); start < avaCaps; start++) {
                            String names = caps[start];
                            System.out.printf("%s : %s.%n", (start + 1), names);
                        }
                    }else if (numOfInt < 6) {
                        for (int start = 0; start < (numOfInt + 5); start++) {
                            String names = caps[start];
                            System.out.printf("%s : %s.%n", (start + 1), names);
                        }
                    } else {
                        for (int start = (numOfInt - 6); start < (numOfInt + 5); start++) {
                            String names = caps[start];
                            System.out.printf("%s : %s.%n", (start + 1), names);
                        }
                    }
                    option = "0";
                    break;

                case "4":
                    System.out.println("Exit\n====\nAre you sure you want to exit?\nAll data will be lost.\nExit [y/n]:");
                    String exit = console.nextLine();
                    if (exit.equalsIgnoreCase("y")) {
                        System.out.println("Goodbye!");
                        option = "end";
                    } else if (exit.equalsIgnoreCase("n")) {
                        System.out.println("Ok. Returning to menu options");
                        option = "0";
                    } else {
                        System.out.println("I don't understand.");
                    }
                    break;
                default:
                    option = "begin";
            }
        }

    }
}

