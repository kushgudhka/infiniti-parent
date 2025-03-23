package in.aceinvesting.parent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.aceinvesting.parent.enums.UserRole;
import in.aceinvesting.parent.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: Kush Gudhka
 */

@Entity
@Table(name = "user_access_source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccessSourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @JsonIgnore // Hides password from JSON responses
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "pan_number", unique = true, nullable = false)
    private String panNumber;

    @Column(name = "is_pan_verified")
    private Boolean isPanVerified;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.PENDING_VERIFICATION;

    @Builder.Default
    @Column(name = "is_locked", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isLocked = false;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole = UserRole.INVESTOR; // ADMIN, TRADER, INVESTOR

    @Column(name = "is_two_factor_enabled")
    private Boolean isTwoFactorEnabled;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "verified_by")
    private String verifiedBy;

    @Column(name = "ip_address")
    private String ipAddress;
}
