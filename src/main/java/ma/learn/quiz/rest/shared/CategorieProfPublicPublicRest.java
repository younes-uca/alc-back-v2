package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.CategorieProf;
import ma.learn.quiz.service.CategorieProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/public/categorieprof")
public class CategorieProfPublicPublicRest {
	@Autowired
	private CategorieProfService categorieProfservice;

	 @GetMapping("/")
	public List<CategorieProf> findAll() {
		return categorieProfservice.findAll();
	}

}
