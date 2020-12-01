package net.codejava;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Controller
public class RestaurantController {
	final
	FirebaseInitialize db;

	public RestaurantController(FirebaseInitialize db) {
		this.db = db;
	}

	public ArrayList<Restaurant> getRestaurants(ArrayList<Restaurant> restaurants) throws ExecutionException, InterruptedException {
		CollectionReference restaurant= db.getFirebase().collection("Restaurants");
		ApiFuture<QuerySnapshot> querySnapshot= restaurant.get();
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Restaurant res = doc.toObject(Restaurant.class);
			restaurants.add(res);
		}
		return restaurants;
	}

	@GetMapping("/generate")
	public String showForm(Model model) throws ExecutionException, InterruptedException {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		getRestaurants(restaurants);
		for(int i = 0; i < restaurants.size(); i++) {
			DocumentReference docRef = db.getFirebase().collection("Restaurants").document(restaurants.get(i).getName());
			ApiFuture<WriteResult> future = docRef.update("selected", false);
		}

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
			DocumentReference docRef = db.getFirebase().collection("Restaurants").document(checkedItems.get(i).getName());
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
