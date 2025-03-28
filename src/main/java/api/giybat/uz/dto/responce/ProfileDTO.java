package api.giybat.uz.dto.responce;

import api.giybat.uz.enums.GeneralStatus;
import api.giybat.uz.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfileDTO {
    private String name;
    private String username;
    private String password;
    private GeneralStatus status;
    private Boolean visible;
    private List<ProfileRole> roleList;
    private String jwt;
    private LocalDateTime createdDate;
}
