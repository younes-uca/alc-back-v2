package ma.learn.quiz.service;

import java.util.ArrayList;
import java.util.List;

import ma.learn.quiz.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.learn.quiz.dao.ScheduleProfDao;
import ma.learn.quiz.vo.SchdeduleVo;

import javax.persistence.EntityManager;

@Service
public class ScheduleProfService extends AbstractService {

    public ScheduleProf findByRef(String ref) {
        return scheduleProfDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return scheduleProfDao.deleteByRef(ref);
    }


    public ScheduleProf save(ScheduleProf scheduleProf) {
        if (scheduleProf.getId() == null || scheduleProf.getId() == 0) {
            GroupeEtudiant groupeEtudiant = groupeEtudiantService.findGroupeEtudiantById(scheduleProf.getGroupeEtudiant().getId());
            Prof prof = profService.findProfById(scheduleProf.getProf().getId());
            Cours cours = this.courseService.findCoursById(scheduleProf.getCours().getId());
            if (groupeEtudiant != null) {
                scheduleProf.setGroupeEtudiant(groupeEtudiant);
            } else {
                return null;
            }
            if (prof != null) {
                scheduleProf.setProf(prof);
            } else {
                return null;
            }

            if (cours != null){
                scheduleProf.setCours(cours);
            } else {
                return null;
            }
            if (findByRef(scheduleProf.getRef()) != null) {
                return null;
            } else {
                return scheduleProfDao.save(scheduleProf);
            }
        } else {

            return this.update(scheduleProf);
        }

    }

    public int saveAll(ScheduleProf scheduleProf) {
        if (findByRef(scheduleProf.getRef()) != null) {
            return -1;
        } else {
            scheduleProfDao.save(scheduleProf);
//            groupeEtudiantService.saveAll(scheduleProf, scheduleProf.getGroupeEtudiant()); //TODO create saveAll
            return 1;
        }
    }

    public List<SchdeduleVo> findSchedule() {
        List<ScheduleProf> sheduls = scheduleProfDao.findAll();
        List<SchdeduleVo> schdeduleVos = new ArrayList<SchdeduleVo>();
        for (ScheduleProf s : sheduls) {
            SchdeduleVo schdeduleVo = new SchdeduleVo();
            schdeduleVo.setId(s.getId());
            schdeduleVo.setTitle(s.getGroupeEtudiant().getLibelle());
            schdeduleVo.setStart(s.getStartTime());
            schdeduleVo.setEnd(s.getEndTime());
            schdeduleVo.setRef(s.getRef());
            schdeduleVos.add(schdeduleVo);
        }
        return schdeduleVos;
    }

    public List<ScheduleProf> findAll() {
        return scheduleProfDao.findAll();
    }

    public ScheduleProf update(ScheduleProf scheduleProf) {
        return scheduleProfDao.save(scheduleProf);
    }


    public List<ScheduleProf> findByProfId(Long id) {
        return scheduleProfDao.findByProfId(id);
    }

    public List<ScheduleProf> findByGroupeEtudiantId(Long id) {
        return scheduleProfDao.findByGroupeEtudiantId(id);
    }

    public List<ScheduleProf> findByCriteriaStudent(ScheduleProf schedule) {
        String query = this.init("ScheduleProf");
        if (schedule.getGroupeEtudiant() != null) {
            query += this.addCriteria("groupeEtudiant.libelle", schedule.getGroupeEtudiant().getLibelle(), "LIKE");
//            query += this.addCriteria("etudiant.prenom", schedule.getEtudiant().getPrenom(), "LIKE");
//            query += this.addCriteria("etudiant.username", schedule.getEtudiant().getUsername(), "LIKE");
        }
        System.out.println("query = " + query);
        return entityManager.createQuery(query).getResultList();
    }

    @Autowired
    private ScheduleProfDao scheduleProfDao;
    @Autowired
    private GroupeEtudiantService groupeEtudiantService;
    @Autowired
    private EtatEtudiantScheduleService etatEtudiantScheduleService;
    @Autowired
    private ProfService profService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CoursService courseService;
}
