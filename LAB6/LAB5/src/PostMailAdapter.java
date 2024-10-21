// Adapter class to adapt PostalMail to Notification interface
class PostalMailAdapter implements Notification {
    private PostalMail postalMail;
    private String fullName;
    private String address;

    public PostalMailAdapter(PostalMail postalMail, String fullName, String address) {
        this.postalMail = postalMail;
        this.fullName = fullName;
        this.address = address;
    }

    // Adapt the send method to work with PostalMail's sendMail
    @Override
    public void send(String receiver, String message) {
        postalMail.sendMail(fullName, address, message);
    }
}
