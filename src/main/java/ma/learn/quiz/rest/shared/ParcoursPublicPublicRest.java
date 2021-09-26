package ma.learn.quiz.rest.shared;


import ma.learn.quiz.bean.Parcours;
import ma.learn.quiz.service.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/parcours")

public class ParcoursPublicPublicRest {

    @Autowired
    public ParcoursService parcoursService;

    @GetMapping("/")
    public List<Parcours> findAll(){
        return parcoursService.findAll();
    }


    @GetMapping("/code/{code}")
    public Parcours findByCode(@PathVariable String code) {
		return parcoursService.findByCode(code);
	}


    
	

	@DeleteMapping("/entity/{entity}")
	public void delete(@PathVariable Parcours entity) {
		parcoursService.delete(entity);
	}

@PostMapping("/")
public void save(@RequestBody Parcours parcours) {
		parcoursService.save(parcours);
	}

@PutMapping("/")
	public Parcours update(@RequestBody Parcours parcours) {
		return parcoursService.update(parcours);
	}

@GetMapping("/libelle/{libelle}")
	public List<Parcours> findByLibelle(@PathVariable String libelle) {
	return parcoursService.findByLibelle(libelle);
}


	@DeleteMapping("/id/{id}")
    public int deleteParcoursById(@PathVariable Long id) {
        return parcoursService.deleteParcoursById(id);
    }

	 @PostMapping("/delete-multiple-by-id")
	public int deleteParcoursById(@RequestBody List<Parcours> parcourss) {
		return parcoursService.deleteParcoursById(parcourss);
	}

}
