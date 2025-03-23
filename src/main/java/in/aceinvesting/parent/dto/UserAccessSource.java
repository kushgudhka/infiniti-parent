package in.aceinvesting.parent.dto;

import in.aceinvesting.parent.enums.UserRole;
import in.aceinvesting.parent.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: Kush Gudhka
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessSource {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String panNumber;
    private Boolean isPanVerified;
    private UserStatus status; // ACTIVE, ARCHIVED, TERMINATED, PENDING_VERIFICATION
    private Boolean isLocked;
    private LocalDateTime dateCreated;
    private UserRole userRole; // ADMIN, TRADER, INVESTOR
    private Boolean isTwoFactorEnabled;
    private String referralCode;
    private String verifiedBy;
    private String ipAddress;
}