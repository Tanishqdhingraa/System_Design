package Notification_Engine;

import java.util.*;

// ================= DECORATOR =================

interface Notification {
    String getContent();
}

class SimpleNotification implements Notification {

    private String message;

    SimpleNotification(String message) {
        this.message = message;
    }

    public String getContent() {
        return message;
    }
}

abstract class NotificationDecorator implements Notification {

    protected Notification notification;

    NotificationDecorator(Notification notification) {
        this.notification = notification;
    }
}

class Timestamp extends NotificationDecorator {

    Timestamp(Notification notification) {
        super(notification);
    }

    public String getContent() {
        return "[TIME] " + notification.getContent();
    }
}

class Signature extends NotificationDecorator {

    Signature(Notification notification) {
        super(notification);
    }

    public String getContent() {
        return notification.getContent() + " - Customer Care";
    }
}

// ================= OBSERVER =================

interface Observer {
    void update();
}

class NotificationManager {

    private List<Observer> observers = new ArrayList<>();
    private Notification notification;

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void setNotification(Notification notification) {
        this.notification = notification;

        for (Observer observer : observers) {
            observer.update();
        }
    }

    Notification getNotification() {
        return notification;
    }
}

// ================= STRATEGY =================

interface NotificationStrategy {
    void send(String message);
}

class Email implements NotificationStrategy {

    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SMS implements NotificationStrategy {

    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

class NotificationEngine implements Observer {

    private NotificationManager manager;
    private List<NotificationStrategy> strategies = new ArrayList<>();

    NotificationEngine(NotificationManager manager) {
        this.manager = manager;
    }

    void addStrategy(NotificationStrategy strategy) {
        strategies.add(strategy);
    }

    public void update() {

        String message = manager.getNotification().getContent();

        for (NotificationStrategy strategy : strategies) {
            strategy.send(message);
        }
    }
}

// ================= SINGLETON =================

class NotificationService {

    private static NotificationService instance;
    private NotificationManager manager = new NotificationManager();

    private NotificationService() {
    }

    static NotificationService getInstance() {

        if (instance == null)
            instance = new NotificationService();

        return instance;
    }

    NotificationManager getManager() {
        return manager;
    }

    void send(Notification notification) {
        manager.setNotification(notification);
    }
}

// ================= MAIN =================

public class NotificationSystem {

    public static void main(String[] args) {

        // Singleton
        NotificationService service =
                NotificationService.getInstance();

        NotificationManager manager =
                service.getManager();


        // Observer + Strategy
        NotificationEngine engine =
                new NotificationEngine(manager);

        engine.addStrategy(new Email());
        engine.addStrategy(new SMS());

        manager.addObserver(engine);


        // Decorator
        Notification notification =
                new SimpleNotification("Order Shipped");

        notification = new Timestamp(notification);
        notification = new Signature(notification);


        // Send
        service.send(notification);
    }
}
