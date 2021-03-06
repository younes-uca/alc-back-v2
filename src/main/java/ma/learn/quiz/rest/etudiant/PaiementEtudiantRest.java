package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.Paiement;
import ma.learn.quiz.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/paiement")
public class PaiementEtudiantRest {


    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }


    @Autowired
    private PaiementService paiementService;
}
