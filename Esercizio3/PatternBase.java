package Esercizio3;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registraOsservatore(Observer o);

    void rimuoviOsservatore(Observer o);

    void notificaOsservatore(String message);
}

interface Observer {
    void update(String message);
}

class NotificationService implements Subject {
    private static NotificationService instance;
    private List<Observer> observers;

    private NotificationService() {
        observers = new ArrayList<>();
        System.out.println("NotificationService: Istanza creata.");
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    @Override
    public void registraOsservatore(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
            System.out.println("Observer " + o + " registrato.");
        }
    }

    @Override
    public void rimuoviOsservatore(Observer o) {
        if (o != null) {
            observers.remove(o);
            System.out.println("Observer " + o + " rimosso.");
        }
    }

    @Override
    public void notificaOsservatore(String message) {
        System.out.println("Inizio notifica agli osservatori.");
        if (observers.isEmpty()) {
            System.out.println("Nessun osservatore registrato.");
            return;
        }
        for (Observer observer : observers) {
            observer.update(message);
        }
        System.out.println("Notifica completata.");
    }

    public void sendNotification(String notificationContent) {
        System.out.println("Invio nuova notifica: '" + notificationContent + "'");
        notificaOsservatore(notificationContent);
    }
}

class User implements Observer {
    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void update(String message) {
        System.out.println("Utente " + username + ": Notifica ricevuta: '" + message + "'");
    }

    @Override
    public String toString() {
        return "User[" + username + "]";
    }
}

public class PatternBase {
    public static void main(String[] args) {
        NotificationService service = NotificationService.getInstance();

        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Charlie");
        User user4 = new User("Diana");

        System.out.println("Registrazione Utenti.");// registrazione utenti
        service.registraOsservatore(user1);
        service.registraOsservatore(user2);
        service.registraOsservatore(user3);

        System.out.println("Simulazione Invio Notifica 1.");
        service.sendNotification("C'è una nuova offerta speciale!"); // ivio notifica effettivo

        System.out.println("Bob si disiscrive");
        service.rimuoviOsservatore(user2);

        System.out.println("Simulazione Invio Notifica 2");
        service.sendNotification("Il tuo ordine è stato spedito.");

        System.out.println("Diana si registra e inviamo Notifica 3");
        service.registraOsservatore(user4);
        service.sendNotification("Aggiornamenti sulla privacy.");
    }
}
