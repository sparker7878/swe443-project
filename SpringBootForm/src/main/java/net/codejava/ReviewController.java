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

	public ArrayList<Review> getReviews(ArrayList<Restaurant> reviews) throws ExecutionException, InterruptedException {
		CollectionReference Review = database.getFirebase().collection("Reviews");
		ApiFuture<QuerySnapshot> querySnapshot = review.get();
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Review rev = doc.toObject(Review.class);
			restaurants.add(res);
		}
		return reviews;
	}

	@GetMapping("/generate")
	public String showForm(Model model) throws ExecutionException, InterruptedException {
		ArrayList<Review> reviews = new ArrayList<Review>();
		getReviews(reviews);

		model.addAttribute("restaurants", restaurants);

		Foo foo = new Foo();
		ArrayList<Restaurant> checkedItems = new ArrayList<Restaurant>();
		foo.setCheckedItems(checkedItems);
		model.addAttribute("foo", foo);
		return "generator";
	}

	@PostMapping("/generate")
	public String submitForm(@ModelAttribute("foo") Foo foo, Model model) {

		ArrayList<Restaurant> checkedItems = foo.getCheckedItems();

		for(int i = 0; i < checkedItems.size(); i++) {
			DocumentReference docRef = database.getFirebase().collection("Restaurants").document(checkedItems.get(i).getName());
			ApiFuture<WriteResult> future = docRef.update("selected", true);
		}

		//generate random restaurant
		int min = 0;
		int max = checkedItems.size() - 1;
		int range = (max - min) + 1;
		int randNum = (int)(Math.random() * range) + min;
		String resultName = checkedItems.get(randNum).getName();
		model.addAttribute("resultName", resultName);
		return "generate_success";
	}



}
