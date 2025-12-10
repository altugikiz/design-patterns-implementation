import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel implements Subject {
    private String channelName;
    private List<Observer> subscribers = new ArrayList<>(); // Subscribers list
    private String lastVideoTitle;

    public YoutubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
        System.out.println("New subscriber added!");
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
        System.out.println("A subscriber has left :(");
    }

    @Override
    public void notifySubscribers() {
        // The most important part: Iterate the list and call everyone's update method.
        for (Observer observer : subscribers) {
            observer.update(lastVideoTitle);
        }
    }

    // Action performed by the channel owner
    public void uploadVideo(String title) {
        this.lastVideoTitle = title;
        System.out.println("\n[" + channelName + "] Uploaded new video: " + title);
        notifySubscribers(); // Ring the bell!
    }
}