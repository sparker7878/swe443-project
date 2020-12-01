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
          InputStream serviceAccount = this.getClass().getClassLoader()
                  .getResourceAsStream("./swe443-bfad6-firebase-adminsdk-ryvcb-8fba96bca5.json");

          assert serviceAccount != null;

          FirebaseOptions options = new FirebaseOptions.Builder()
                  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                  .setDatabaseUrl("https://swe443-bfad6.firebaseio.com").build();

          if (FirebaseApp.getApps().isEmpty()) {
              FirebaseApp.initializeApp(options);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public Firestore getFirebase(){
      return FirestoreClient.getFirestore();
  }
}
