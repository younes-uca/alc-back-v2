package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.ScheduleProf;
import ma.learn.quiz.service.ScheduleProfService;
import ma.learn.quiz.vo.SchdeduleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/scheduleProf")
public class ScheduleProfPublicRest {

	/*@DeleteMapping("/id/{id}")
	public int deleteByScheduleId(Long id) {
		return scheduleProfService.deleteByScheduleId(id);
	}

	@PutMapping("/")
	public ScheduleProf update(@RequestBody ScheduleProf scheduleProf) {
		return scheduleProfService.update(scheduleProf);
	}*/

/*	@GetMapping("/id/{id}")
	public List<ScheduleProf> findByScheduleId(Long id) {
		return scheduleProfService.findByScheduleId(id);
	}*/

	@PostMapping("/save/")
	public int saveAll(ScheduleProf scheduleProf) {
		return scheduleProfService.saveAll(scheduleProf);
	}

	@GetMapping("/vo/")
	public List<SchdeduleVo> findSchedule() {
		return scheduleProfService.findSchedule();
	}

	@Autowired
	private ScheduleProfService scheduleProfService;

	@GetMapping("/ref/{ref}")
	public ScheduleProf findByRef(@PathVariable String ref) {
		return scheduleProfService.findByRef(ref);
	}

	@DeleteMapping("/ref/{ref}")
	public int deleteByRef(@PathVariable String ref) {
		return scheduleProfService.deleteByRef(ref);
	}

	@PostMapping("/")
	public ScheduleProf save(@RequestBody ScheduleProf scheduleProf) {
		return scheduleProfService.save(scheduleProf);
	}

	@GetMapping("/")
	public List<ScheduleProf> findAll() {
		return scheduleProfService.findAll();
	}

}
