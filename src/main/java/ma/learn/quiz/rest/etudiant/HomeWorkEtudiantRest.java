package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.HomeWork;
import ma.learn.quiz.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiant/homeWork")
public class HomeWorkEtudiantRest {

	@GetMapping("/id/{id}")
	public Optional<HomeWork> findById(@PathVariable Long id) {
		return homeWorkService.findById(id);
	}

	@Transactional
	@DeleteMapping("/id/{id}")
	public void deleteById(@PathVariable Long id) {
		homeWorkService.deleteById(id);
	}

	@GetMapping("/cours/id/{id}")
	public List<HomeWork> findBySectionId(@PathVariable Long id) {
		return homeWorkService.findByCoursId(id);
	}

	@GetMapping("/")
	public List<HomeWork> findAll() {
		return homeWorkService.findAll();
	}

	@PostMapping("/")
	public HomeWork save(@RequestBody HomeWork homeWork) {
		return homeWorkService.save(homeWork);
	}



	@Autowired
	private HomeWorkService homeWorkService;

}
