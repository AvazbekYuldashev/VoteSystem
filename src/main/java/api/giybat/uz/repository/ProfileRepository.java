package api.giybat.uz.repository;

import api.giybat.uz.entity.ProfileEntity;
import api.giybat.uz.enums.GeneralStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    @Query("SELECT p FROM ProfileEntity p where p.username = :username and p.visible = true ")
    Optional<ProfileEntity> findByUsernameAndVisibleTrue(@Param("username") String username);

    @Query("SELECT p FROM ProfileEntity p where p.id = :id and p.visible = true ")
    Optional<ProfileEntity> findByIdAndVisibleTrue(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity SET status=:status where id = :id")
    void changeStatus(@Param("id") Integer id, @Param("status") GeneralStatus status);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProfileEntity p WHERE p.id = :id")
    int deleteByIdQ(@Param("id") Integer id);
}
