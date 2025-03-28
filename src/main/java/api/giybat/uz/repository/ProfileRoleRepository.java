package api.giybat.uz.repository;

import api.giybat.uz.entity.ProfileRoleEntity;
import api.giybat.uz.enums.ProfileRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRoleRepository extends JpaRepository<ProfileRoleEntity, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ProfileRoleEntity p WHERE p.profileId = :id")
    int deleteByProfileId(@Param("id") Integer id);

    @Query("SELECT p.role FROM ProfileRoleEntity p WHERE p.profileId = :id")
    List<ProfileRole> getAllRoleListByProfileId(@Param("id") Integer id);
}
