package registration;

import java.util.Random;

public final class message {
    private final String messageID;
    private final int messageNumber;
    private final String recipientCell;
    private final String messageText;
    private final String messageHash;
    private int totalMessages;

    public message(int messageNumber, String recipientCell, String messageText) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + Math.abs(rand.nextLong()) % 9000000000L;
        return String.valueOf(id);
    }

    // Ensures that the message ID is not more than ten characters[cite: 11]
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Ensures that the recipient cell number is formatted correctly with an international code[cite: 9, 11]
    public boolean checkRecipientCell(String cellNumber) {
        return cellNumber != null && (cellNumber.matches("^\\+27\\d{9}$") || (cellNumber.length() <= 10 && cellNumber.matches("^\\+?\\d+?$")));
    }

    // Validates message length (must not exceed 250 characters)[cite: 9, 12]
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    // Creates and returns the Message Hash in ALL CAPS[cite: 9, 11]
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        String firstTwoId = messageID.substring(0, 2);
        
        String cleanedText = messageText.replaceAll("[^a-zA-Z ]", "").trim();
        String[] words = cleanedText.split("\\s+");
        
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        String rawHash = firstTwoId + ":" + messageNumber + ":" + firstWord + lastWord;
        return rawHash.toUpperCase();
    }

    // Allows the user to choose if they want to send, store, or disregard the message[cite: 10, 11]
   public String sendMessage(int choice) {
        switch (choice) {
            case 1 -> {
                totalMessages++;
                return "Message successfully sent.";
            }
            case 2 -> {
                return "Press 0 to delete the message.";
            }
            case 3 -> {
                return "Message successfully stored.";
            }
            default -> {
                return "Invalid choice.";
            }
        }
    }

    // Accer and method
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }

    String getMessageId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

