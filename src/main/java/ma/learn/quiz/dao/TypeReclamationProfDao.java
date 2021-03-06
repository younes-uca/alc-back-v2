package ma.learn.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import ma.learn.quiz.bean.TypeReclamationProf;


@Repository
public interface TypeReclamationProfDao extends JpaRepository<TypeReclamationProf, Long> {
    TypeReclamationProf findTypeReclamationProfByCode(String code);

    int deleteTypeReclamationProfById(Long id);
}
