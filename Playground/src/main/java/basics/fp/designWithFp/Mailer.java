public class Mailer {
    public Mailer from(final String address) {
        System.out.println("set up from address");
        return this;
    }

    public Mailer to(final String address) {
        System.out.println("set up to address");
        return this;
    }

    public Mailer subject(final String address) {
        System.out.println("set subject");
        return this;
    }

    public Mailer body(final String address) {
        System.out.println("set body");
        return this;
    }

    public Mailer send() {
        System.out.println("sending...");
        return this;
    }

    public static void main(String[] args) {
        new Mailer()
                .from("test_from@gmail.com")
                .to("test_to@gmail.com")
                .subject("subject")
                .body("body")
                .send()

    }
}