package net.codejava;

import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import net.codejava.Review;
import com.google.api.*;
import com.google.cloud.*;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {

    public Review getReviewDetails(String name) throws InterruptedException, ExecutionException {
        Firestore databaseFB = FirestoreClient.getFirestore();
        DocumentReference documentReference = databaseFB.collection("Reviews").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Review review = null;

        if (document.exists()) {
            review = document.toObject(Review.class);
            return review;
        }

        return null;
    }

    public String updateReviews(Review review) throws InterruptedException, ExecutionException {
        Firestore databaseFB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = databaseFB.collection("Reviews").document(review.getName()).set(review);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}
