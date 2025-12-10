public class Main {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo (YouTube) ===\n");

        // 1. Create the channel
        YoutubeChannel myChannel = new YoutubeChannel("The Walking Dead");

        // 2. Create subscribers
        Subscriber user1 = new Subscriber("Darly Dixon");
        Subscriber user2 = new Subscriber("Rick Grimes");
        Subscriber user3 = new Subscriber("Negan");

        // 3. Users subscribe to the channel
        myChannel.subscribe(user1);
        myChannel.subscribe(user2);
        myChannel.subscribe(user3);

        // 4. Upload video (Notification should be sent automatically)
        myChannel.uploadVideo("How Does the Observer Pattern Work?");

        // 5. One person unsubscribes and we upload a new video
        System.out.println("\n--- Negan is unsubscribing ---");
        myChannel.unsubscribe(user2);

        myChannel.uploadVideo("Who killed Glenn?");
    }
}