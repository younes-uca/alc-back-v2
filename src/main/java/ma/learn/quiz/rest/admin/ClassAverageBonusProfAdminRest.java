package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.ClassAverageBonus;
import ma.learn.quiz.bean.ClassAverageBonusProf;
import ma.learn.quiz.service.ClassAverageBonusProfService;
import ma.learn.quiz.service.ClassAverageBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin/classAverageBonusProf")
public class ClassAverageBonusProfAdminRest {
    @Autowired
    private ClassAverageBonusProfService classAverageBonusProfService;

    @GetMapping("/{id}")
    public List<ClassAverageBonusProf> findClassAverageBonusProfByProfId(@PathVariable Long id) {
        return classAverageBonusProfService.findClassAverageBonusProfByProfId(id);
    }
    @GetMapping("/{mois}/{annee}/{idprof}")

    public List<ClassAverageBonusProf> findClassAverageBonusProfByMoisAndAnneeAndProfId(@PathVariable int mois,@PathVariable int annee,@PathVariable Long idprof) {
        return classAverageBonusProfService.findClassAverageBonusProfByMoisAndAnneeAndProfId(mois, annee, idprof);
    }
    @GetMapping("/montant/{mois}/{annee}/{idprof}")

    public BigDecimal findMontantClassAverageBonusProfByMoisAndAnneeAndProfId(@PathVariable int mois,@PathVariable int annee,@PathVariable Long idprof) {
        return classAverageBonusProfService.findMontantClassAverageBonusProfByMoisAndAnneeAndProfId(mois, annee, idprof);
    }
    @GetMapping("/")

    public List<ClassAverageBonusProf> findAll() {
        return classAverageBonusProfService.findAll();
    }


}
