package in.aceinvesting.parent.enums;

import lombok.Getter;

/**
 * @Author: Kush Gudhka
 */
@Getter
public enum UserStatus {
    ACTIVE("ACTIVE"),
    TERMINATED("TERMINATED"),
    ARCHIVED("ARCHIVED"),
    PENDING_VERIFICATION("PENDING_VERIFICATION");

    private final String statusName;

    UserStatus(String statusName) {
        this.statusName = statusName;
    }
}