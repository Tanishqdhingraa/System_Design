package Observer_Design_Pttern;


import java.util.*;

// Observer
interface Subscriber {
    void update();
}
//! No. Observer Pattern actually helps separate the subject from the observers. 
//! The subject manages its state and notification mechanism, while observers handle their own response to changes. 
//! SRP is violated only if the subject starts taking unrelated responsibilities such as email, database, or logging
// Subject
interface Channel {
    void subscribe(Subscriber s);
    void unsubscribe(Subscriber s);
    void notifySubscribers();
}

// Concrete Subject
class YouTubeChannel implements Channel {

    private List<Subscriber> subscribers = new ArrayList<>();
    private String video;

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.update();
        }
    }

    public void upload(String video) {
        this.video = video;
        System.out.println("New video: " + video);
        notifySubscribers();
    }

    public String getVideo() {
        return video;
    }
}

// Concrete Observer
class User implements Subscriber {

    private String name;
    private YouTubeChannel channel;

    User(String name, YouTubeChannel channel) {
        this.name = name;
        this.channel = channel;
    }

    public void update() {
        System.out.println(name + " received: " + channel.getVideo());
    }
}

// Main
public class Main {
    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        User u1 = new User("Varun", channel);
        User u2 = new User("Tarun", channel);

        channel.subscribe(u1);
        channel.subscribe(u2);

        channel.upload("Observer Pattern");

        channel.unsubscribe(u1);

        channel.upload("Decorator Pattern");
    }
}
