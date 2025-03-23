package in.aceinvesting.parent.enums;

import lombok.Getter;

/**
 * @Author: Kush Gudhka
 */

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    TRADER("TRADER"),
    INVESTOR("INVESTOR");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}