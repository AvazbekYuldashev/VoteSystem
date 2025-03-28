package api.giybat.uz.repository;

import api.giybat.uz.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {

    @Query("SELECT v FROM VoteEntity v WHERE v.surveyId=:surveyId")
    Optional<VoteEntity> findBySurveyId(@Param("surveyId") Integer surveyId);

    @Query("SELECT v FROM VoteEntity v WHERE v.surveyId = :surveyId AND v.profileId = :profileId")
    Optional<VoteEntity> findBySurveyIdAndProfileId(@Param("surveyId") Integer surveyId, @Param("profileId") Integer profileId);
}
