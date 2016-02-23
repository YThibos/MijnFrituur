package be.vdab.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.vdab.entities.Saus;

public class SausDAO {
	
	private static Map<Long, Saus> SAUZEN = new ConcurrentHashMap<>();
	
	static {
		List<String> cocktailIngr = Arrays.asList("ketchup", "mayonaise", "mierikswortel");
		SAUZEN.put(1L, new Saus(1L, "cocktail", cocktailIngr));
		
		List<String> mayoIngr = Arrays.asList("eierdooiers", "olie");
		SAUZEN.put(2L, new Saus(2L, "mayonaise", mayoIngr));
		
		List<String> mosterdIngr = Arrays.asList("mosterdzaad", "azijn", "water", "zout");
		SAUZEN.put(3L, new Saus(3L, "mosterd", mosterdIngr));
		
		List<String> tartareIngr = Arrays.asList("mayonaise", "ei", "peterselie", "sjalotjes");
		SAUZEN.put(4L, new Saus(4L, "tartare", tartareIngr));
		
		List<String> vinaigretteIngr = Arrays.asList("olijfolie", "wijnazijn", "zout", "peper", "tuinkruiden", "mosterd");
		SAUZEN.put(5L, new Saus(5L, "vinaigrette", vinaigretteIngr));
	}
	
	public List<Saus> findAll() {
		return new ArrayList<>(SAUZEN.values());		
	}
	
	public List<Saus> findByIngredient(String ingredient) {
		List<Saus> gevondenSauzen = new ArrayList<>();
		
		SAUZEN.entrySet().stream().forEach(entry -> {
			if (entry.getValue().getIngredienten().contains(ingredient)) {
				gevondenSauzen.add(entry.getValue());
			}
		});
		
		return gevondenSauzen;
	}
	
	public List<Saus> findByMultIngredients(List<String> ingredienten) {
		List<Saus> gevondenSauzen = new ArrayList<>();
		
		SAUZEN.values().stream().forEach(saus -> {
			if (saus.getIngredienten().containsAll(ingredienten)) {
				gevondenSauzen.add(saus);
			}
		});
		
		return gevondenSauzen;
	}
	
	public void deleteByName(List<String> teVerwijderenSauzen) {
		
		SAUZEN.entrySet().removeIf(entry -> teVerwijderenSauzen.contains(entry.getValue().getNaam()));
		
	}

}
