package in.aceinvesting.parent.mapper;

import in.aceinvesting.parent.dto.UserAccessSource;
import in.aceinvesting.parent.entity.UserAccessSourceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: Kush Gudhka
 */
@Component
public class UserAccessSourceMapper {

    public UserAccessSourceEntity toEntity(UserAccessSource dto) {
        return UserAccessSourceEntity.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .fullName(dto.getFullName())
                .panNumber(dto.getPanNumber())
                .isPanVerified(dto.getIsPanVerified())
                .status(dto.getStatus())
                .isLocked(dto.getIsLocked())
                .dateCreated(LocalDateTime.now())
                .userRole(dto.getUserRole())
                .isTwoFactorEnabled(dto.getIsTwoFactorEnabled())
                .referralCode(dto.getReferralCode())
                .verifiedBy(dto.getVerifiedBy())
                .ipAddress(dto.getIpAddress())
                .build();
    }

    public UserAccessSource toDTO(UserAccessSourceEntity entity) {
        return UserAccessSource.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .fullName(entity.getFullName())
                .panNumber(entity.getPanNumber())
                .isPanVerified(entity.getIsPanVerified())
                .status(entity.getStatus())
                .isLocked(entity.getIsLocked())
                .dateCreated(entity.getDateCreated())
                .userRole(entity.getUserRole())
                .isTwoFactorEnabled(entity.getIsTwoFactorEnabled())
                .referralCode(entity.getReferralCode())
                .verifiedBy(entity.getVerifiedBy())
                .ipAddress(entity.getIpAddress())
                .build();
    }
}
