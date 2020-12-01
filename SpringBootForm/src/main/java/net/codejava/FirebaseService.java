package net.codejava;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import net.codejava.Restaurant;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {

    public Restaurant getRestaurantDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("Restaurants").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Restaurant restaurant = null;

        if(document.exists()) {
            restaurant = document.toObject(Restaurant.class);
            return restaurant;
        }else {
            return null;
        }
    }

    public String updateRestaurantDetails(Restaurant restaurant) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Restaurants").document(restaurant.getName()).set(restaurant);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}