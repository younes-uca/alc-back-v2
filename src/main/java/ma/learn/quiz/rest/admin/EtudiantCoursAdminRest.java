package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.EtudiantCours;
import ma.learn.quiz.service.EtudiantCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/etudiantCours")
public class EtudiantCoursAdminRest {
	@Autowired
	private EtudiantCoursService etudiantCoursService;
	@PostMapping("/search")
	public List<EtudiantCours> findByCriteria(@RequestBody EtudiantCours sessionCours) {
		return etudiantCoursService.findByCriteria(sessionCours);
	}

	public EtudiantCours findEtudiantCoursById(Long id) {
		return etudiantCoursService.findEtudiantCoursById(id);
	}
	@PutMapping("/")
	public EtudiantCours update(@RequestBody EtudiantCours sessionCours) {
		return etudiantCoursService.update(sessionCours);
	}

	@DeleteMapping("/id/{id}")
	public int deleteEtudiantCoursById(@PathVariable Long id) {
		return etudiantCoursService.deleteEtudiantCoursById(id);
	}

	@PostMapping("/delete-multiple-by-id")
	public int deleteSessionCoursById(@RequestBody List<EtudiantCours> sessionCourss) {
		return etudiantCoursService.deleteSessionCoursById(sessionCourss);
	}

	@GetMapping("/id/{id}/ids/{ids}")
	public EtudiantCours findByCoursIdAndEtudiantId(@PathVariable Long id,@PathVariable  Long ids) {
		return etudiantCoursService.findByCoursIdAndEtudiantId(id, ids);
	}
	@GetMapping("/{idprof}/{idetudiant}/{idcours}")

	public int save(@PathVariable Long idprof,@PathVariable Long idetudiant,@PathVariable Long idcours) {
		return etudiantCoursService.save(idprof, idetudiant, idcours);
	}

//	public int save(@RequestBody EtudiantCours etudiantCours) {
//		return etudiantCoursService.save(etudiantCours);
//	}

	@GetMapping("/")
	public List<EtudiantCours> findAll() {
		return etudiantCoursService.findAll();
	}

	@GetMapping("/prof/id/{id}")
	public List<EtudiantCours> findByProfId(@PathVariable Long id) {
		return etudiantCoursService.findByProfId(id);
	}
	@GetMapping("/prof/id/{id}/etudiant/id/{ids}")
	public List<EtudiantCours> findByProfIdAndEtudiantId(@PathVariable Long id,@PathVariable Long ids) {
		return etudiantCoursService.findByProfIdAndEtudiantId(id, ids);
	}
	
}
