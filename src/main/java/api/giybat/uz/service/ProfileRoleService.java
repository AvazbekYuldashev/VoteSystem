package api.giybat.uz.service;

import api.giybat.uz.entity.ProfileRoleEntity;
import api.giybat.uz.enums.ProfileRole;
import api.giybat.uz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileRoleService {
    @Autowired
    private ProfileRoleRepository profileRoleRepository;


    public void create(Integer profileId, ProfileRole profileRole) {
        ProfileRoleEntity profileRoleEntity = new ProfileRoleEntity();
        profileRoleEntity.setProfileId(profileId);
        profileRoleEntity.setRole(profileRole);
        profileRoleEntity.setCreatedDate(LocalDateTime.now());
        profileRoleRepository.save(profileRoleEntity);
    }

    public void delete(Integer id) {
        int row = profileRoleRepository.deleteByProfileId(id);
        System.out.println(row);
    }

    public List<ProfileRole> getAllRoles(Integer profileId) {
        List<ProfileRole> roleList = profileRoleRepository.getAllRoleListByProfileId(profileId);
        return roleList;
    }
}
