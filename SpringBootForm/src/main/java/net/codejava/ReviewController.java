package net.codejava;

import java.util.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.*;

@Controller
public class ReviewController {
	final FirebaseInitialize database;

	public ReviewController(FirebaseInitialize database) {
		this.database = database;
	}

	public ArrayList<Review> getReviews(ArrayList<Review> reviews) throws ExecutionException, InterruptedException {
		CollectionReference Review = database.getFirebase().collection("Reviews");
		ApiFuture<QuerySnapshot> querySnapshot = review.get();
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Review rev = doc.toObject(Review.class);
			reviews.add(res);
		}
		return reviews;
	}
}
