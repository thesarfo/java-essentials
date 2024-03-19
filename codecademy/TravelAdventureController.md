```java
package com.codecademy.plants.controllers;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import com.codecademy.plants.entities.Adventure;
import com.codecademy.plants.repositories.AdventureRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

  private final AdventureRepository adventureRepository;

  public TravelAdventuresController(AdventureRepository adventureRepo) {
    this.adventureRepository = adventureRepo;
  }

  // Add controller methods below:
@GetMapping()
public Iterable<Adventure> getAdventure(){
Iterable<Adventure> adventures = this.adventureRepository.findAll();
return adventures;
}

@GetMapping(path = "/bycountry/{country}")
public List<Adventure> getByCountry(@PathVariable String country){
return this.adventureRepository.findByCountry(country);

}

@GetMapping(path = "/bystate")
public List<Adventure> getByState(@RequestParam String state) {
  return this.adventureRepository.findByState(state);
}

@PostMapping()
public String addNewAdventure(@RequestBody Adventure adventure){
this.adventureRepository.save(adventure);
return "New adventure saved to the repository";
}
```