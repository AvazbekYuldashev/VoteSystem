package api.giybat.uz.service;

import api.giybat.uz.dto.responce.ProfileDTO;
import api.giybat.uz.enums.AppLangulage;
import api.giybat.uz.entity.ProfileEntity;
import api.giybat.uz.exps.NotFoundExeption;
import api.giybat.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBoundleService boundleService;

    public ProfileDTO create(ProfileDTO profileDTO) {
        return toDTO(profileRepository.save(toEntity(profileDTO)));
    }

    public ProfileDTO getByIds(Integer id, AppLangulage lang) {
        return toDTO(profileRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> new NotFoundExeption(boundleService.getMessage("username.not.found", lang))));
    }

    public List<ProfileDTO> getAll() {
        List<ProfileEntity> entities = profileRepository.findAll();
        List<ProfileDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }





    public ProfileEntity getById(Integer id, AppLangulage lang) {
        return profileRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> new NotFoundExeption(boundleService.getMessage("username.not.found", lang)));
    }
    public boolean deleteById(Integer id) {
        int effectedRow = profileRepository.deleteByIdQ(id);
        return effectedRow > 0;
    }

    private ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setStatus(entity.getStatus());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private ProfileEntity toEntity(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setStatus(dto.getStatus());
        entity.setVisible(dto.getVisible());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}
