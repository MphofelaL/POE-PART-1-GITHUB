public class Message {
    private String messageID;
    private String messageBody;
    private String recipientCellNumber;

    public Message(String messageID, String messageBody, String recipientCellNumber) {
        this.messageID = messageID;
        this.messageBody = messageBody;
        this.recipientCellNumber = recipientCellNumber;
    }

    // Validates that the message body does not exceed 250 characters
    public boolean checkMessageLength(String message) {
        return message != null && message.length() <= 250;
    }

    // Returns the message ID
    public String createMessageID() {
        return this.messageID;
    }

    // Generates a formatted message hash based on ID, first word, and last word
    public String createMessageHash() {
        if (messageBody == null || messageBody.trim().isEmpty()) {
            return "";
        }
        String[] words = messageBody.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        return (this.messageID + ":" + firstWord + ":" + lastWord).toUpperCase();
    }

    // Getters
    public String getMessageBody() {
        return messageBody;
    }

    public String getRecipientCellNumber() {
        return recipientCellNumber;
    }
}
