package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.SectionItem;
import ma.learn.quiz.service.SectionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/sectionItem")
public class SectionItemPublicRest {
    @Autowired
    private SectionItemService sectionItemService;

    @PostMapping("/sectionId/{sectionId}")
    public int CreteSections(@RequestBody List<SectionItem> sectionItems, @PathVariable Long sectionId) {
       return sectionItemService.create(sectionItems, sectionId);
    }

    @GetMapping("/sectionId/{sectionId}")
    public List<SectionItem> findAllBySection(@PathVariable Long sectionId) {
        return sectionItemService.findAllSectionItemsBySection(sectionId);
    }

    @DeleteMapping("/sectionItemId/{id}")
    public int delete(@PathVariable Long id) {
        return sectionItemService.delete(id);
    }

    @PostMapping("/deleteMultiple/")
    public int deleteMultiple(@RequestBody List<Long> ids) {
        return sectionItemService.deleteMultiple(ids);
    }

}
