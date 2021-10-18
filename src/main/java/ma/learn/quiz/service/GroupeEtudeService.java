package ma.learn.quiz.service;

import ma.learn.quiz.bean.GroupeEtude;
import ma.learn.quiz.dao.GroupeEtudeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GroupeEtudeService {
    @Autowired
    private GroupeEtudeDao groupeEtudeDao;
    @Autowired
    private GroupeEtudeDetailService groupeEtudeDetailService;
    public int save(GroupeEtude groupeEtude)
    {
        if(findByLibelle(groupeEtude.getLibelle())!=null)
        {
            return -1;
        }
        else
            groupeEtudeDao.save(groupeEtude);
           groupeEtudeDetailService.save(groupeEtude, groupeEtude.getGroupeEtudeDetails());
        return 1;
    }
    public int update(GroupeEtude groupeEtude){
        groupeEtudeDao.save(groupeEtude);
        return 1;
    }

    public GroupeEtude findByLibelle(String libelle) {
        return groupeEtudeDao.findByLibelle(libelle);
    }
    @Transactional
    public int deleteByLibelle(String libelle) {
        return groupeEtudeDao.deleteByLibelle(libelle);
    }

    public Optional<GroupeEtude> findById(Long id) {
        return groupeEtudeDao.findById(id);
    }

    public List<GroupeEtude> findAll() {
        return groupeEtudeDao.findAll();
    }

}
