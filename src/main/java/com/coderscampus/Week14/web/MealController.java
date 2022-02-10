package com.coderscampus.Week14.web;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.Week14.dto.DayResponse;
import com.coderscampus.Week14.dto.WeekReponse;

@RestController
public class MealController {
	
	//Add base for the unchanging part of your web address.
	@Value("${spoonacular.urls.baseurl}")
	private String baseURL;
	
	@Value("${spoonacular.urls.mealplan}")
	private String mealPlan;
	
	@GetMapping("/mealplanner/day")
	//Well use a ResponseEntity extension as the return value
	public ResponseEntity<DayResponse> getDayMeals(Optional<String> targetCalories, Optional<String> diet, Optional<String> exclusions) {
		RestTemplate rt = new RestTemplate();
		
		URI uri = UriComponentsBuilder.fromHttpUrl(mealPlan)
									  .queryParam("timeframe", "day")
									  .queryParam("targetCalories", targetCalories)
									  .queryParam("diet", diet)
									  .queryParam("exclude", exclusions)
									  .queryParam("apiKey", "01eab1b6a26446788dc5a150f607834c")
									  .build()
									  .toUri();
		
	//In RestTemplate, this class is returned by getForEntity and response.
		ResponseEntity<DayResponse> response = rt.getForEntity(uri, DayResponse.class);
		return response;
	}
	
	@GetMapping("/mealplanner/week")
	public ResponseEntity<WeekReponse> getWeekMeals(Optional<String> targetCalories, Optional<String> diet, Optional<String> exclusions) {
		RestTemplate rt = new RestTemplate();
		URI uri = UriComponentsBuilder.fromHttpUrl(mealPlan)
									  .queryParam("timeframe", "week")
									  .queryParam("targetCalories", targetCalories)
									  .queryParam("diet", diet)
									  .queryParam("exclude", exclusions)
									  .queryParam("apiKey", "01eab1b6a26446788dc5a150f607834c")
									  .build()
									  .toUri();
		ResponseEntity<WeekReponse> response = rt.getForEntity(uri, WeekReponse.class);
		return response;
	}
}