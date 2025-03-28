package api.giybat.uz.service;

import api.giybat.uz.dto.responce.AppResponse;
import api.giybat.uz.dto.request.ProfileRequestDTO;
import api.giybat.uz.dto.responce.ProfileDTO;
import api.giybat.uz.dto.request.RegistrationDTO;
import api.giybat.uz.enums.AppLangulage;
import api.giybat.uz.entity.ProfileEntity;
import api.giybat.uz.enums.GeneralStatus;
import api.giybat.uz.enums.ProfileRole;
import api.giybat.uz.exps.AppBadExeption;
import api.giybat.uz.exps.NotFoundExeption;
import api.giybat.uz.repository.ProfileRepository;
import api.giybat.uz.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bc;
    @Autowired
    private ProfileRoleService profileRoleService;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ResourceBoundleService boundleService;


    public AppResponse<String> registration(RegistrationDTO dto, AppLangulage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(GeneralStatus.IN_REGISTRATION)){
                profileRoleService.delete(optional.get().getId());
                profileService.deleteById(optional.get().getId());
                // send sms/email TODO
            }else{
                throw new AppBadExeption(boundleService.getMessage("email.phone.exists", lang));
            }
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(bc.encode(dto.getPassword()));
        entity.setStatus(GeneralStatus.IN_REGISTRATION);
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        profileRoleService.create(entity.getId(), ProfileRole.ROLE_ADMIN);
        emailSendingService.sendRegistrationEmail(dto.getUsername(), entity.getId(), lang);
        return new AppResponse<>(boundleService.getMessage("email.confirm.send", lang));
    }

    public String regVerification(String token, AppLangulage lang) {
        try {
            Integer id = JwtUtil.decodeRegVerToken(token);
            ProfileEntity profile = profileService.getById(id, lang);
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                // ACTIVE
                profileRepository.changeStatus(profile.getId(), GeneralStatus.ACTIVE);
                return boundleService.getMessage("successfully.registered", lang);
            }
        } catch (JwtException e){}
        throw new AppBadExeption(boundleService.getMessage("verification.failed", lang));    //TODO
    }

    public ProfileDTO login(ProfileRequestDTO dto, AppLangulage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isEmpty()) {
            throw new NotFoundExeption(boundleService.getMessage("username.not.found", lang));
        }
        ProfileEntity profile = optional.get();
        if (!bc.matches(dto.getPassword(), profile.getPassword())) {
            throw new NotFoundExeption(boundleService.getMessage("username.not.found", lang));
        }
        if (!profile.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadExeption(boundleService.getMessage("wrong.status", lang));
        }
        ProfileDTO response = new ProfileDTO();
        response.setName(profile.getName());
        response.setUsername(profile.getUsername());
        response.setRoleList(profileRoleService.getAllRoles(profile.getId()));
        response.setJwt(JwtUtil.encode(profile.getUsername(), profile.getId(), response.getRoleList())); // retnrn jwt

        return response;
    }

}
