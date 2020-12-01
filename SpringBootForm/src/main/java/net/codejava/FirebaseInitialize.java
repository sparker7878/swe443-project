package net.codejava;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialize {

    @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("C:\\Users\\yena8\\OneDrive\\Documents\\GMU F20\\SWE443\\SpringBootForm\\SpringBootForm\\src\\main\\resources\\swe443-bfad6-firebase-adminsdk-ryvcb-f736a2abaf.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://swe443-bfad6.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }
}