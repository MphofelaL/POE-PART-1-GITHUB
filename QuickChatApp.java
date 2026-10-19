package registration;

import java.util.Scanner;

public class QuickChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        login userAuth = new login();

        System.out.println("=========================================");
        System.out.println("     WELCOME TO QUICKCHAT (PART 1 & 2)   ");
        System.out.println("=========================================");

        // --- PART 1: LOGIN GATE ---
        userAuth.setFirstName("Kylian");
        userAuth.setLastName("Mbappe");
        userAuth.registerUser("kyl_1", "Ch3&sec&ke!", "+27831234567");

        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            System.out.println("\n--- User Login ---");
            System.out.print("Enter Username: ");
            String enteredUsername = scanner.nextLine();
            
            System.out.print("Enter Password: ");
            String enteredPassword = scanner.nextLine();

            isLoggedIn = userAuth.loginUser(enteredUsername, enteredPassword);
            System.out.println(userAuth.returnLoginStatus(isLoggedIn));
        }

        // --- PART 2: MESSAGING SYSTEM ---
        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("              QUICKCHAT MENU             ");
            System.out.println("=========================================");
            System.out.println("1. Option 1: Send Messages");
            System.out.println("2. Option 2: Show recently sent messages (Coming Soon.)");
            System.out.println("3. Option 3: Quit");
            System.out.print("Please select an option (1-3): ");
            
            String choice = scanner.nextLine().trim();

            if (choice.equals("3") || choice.equalsIgnoreCase("quit")) {
                System.out.println("Exiting QuickChat. Goodbye!");
                running = false;
                break;
            }

            switch (choice) {
                case "1" -> {
                    System.out.print("How many messages do you wish to enter? ");
                    int numMessages = 0;
                    
                    try {
                        numMessages = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format. Please enter a valid integer.");
                        break;
                    }

                    for (int i = 1; i <= numMessages; i++) {
                        System.out.println("\n--- Message " + i + " of " + numMessages + " ---");
                        
                        System.out.print("Enter recipient cell number (e.g., +27718693002): ");
                        String recipient = scanner.nextLine();

                        System.out.print("Enter message text (max 250 characters): ");
                        String messageText = scanner.nextLine();

                        message msg = new message(i, recipient, messageText);

                        // 1. Validate Recipient Cell Number
                       if (!Message.checkRecipientCell(recipient)) {
                        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                        i--; 
                        continue;
                          } else {
                        System.out.println("Cell phone number successfully captured.");
                         }

                        // 2. Validate Message Length
                        String lengthStatus = msg.checkMessageLength();
                        System.out.println(lengthStatus);
                        
                        if (!lengthStatus.contains("ready to send")) {
                            i--; 
                            continue;
                        }

                        // 3. Display ID and Hash[cite: 4, 5]
                       System.out.println("Message ID generated: " + msg.getMessageId());
                         System.out.println("Message Hash: " + msg.getMessageHash());

                       // 4. Send Message Actions
                           System.out.println("\nChoose an action:");
                           System.out.println("1. Send Message");
                           System.out.println("2. Disregard Message (Press 0 to delete)");
                           System.out.println("3. Store Message to send later");
                           System.out.print("Enter action choice (1-3): ");
                        
                        int actionChoice = 0;
                        try {
                            actionChoice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            actionChoice = 2; 
                        }

                        String actionResult = msg.sendMessage(actionChoice);
                        System.out.println(actionResult);

                        if (actionChoice == 1) {
                            System.out.println("\n--- Full Message Details ---");
                            System.out.println(msg.toString());
                        }
                    }

                    System.out.println("\n=========================================");
                   System.out.println("Total number of messages sent: " + Message.returnTotalMessages());
                    System.out.println("=========================================");
                }

                case "2" -> {
                   System.out.println("Coming Soon.");
                   }

                default -> System.out.println("Invalid selection. Please choose 1, 2, or 3.");
            }
        }
        scanner.close();
    }
}

