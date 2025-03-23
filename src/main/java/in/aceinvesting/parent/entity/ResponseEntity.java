package in.aceinvesting.parent.entity;

import in.aceinvesting.parent.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Kush Gudhka
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseEntity {
    private Long userId;
    private String username;
    private UserStatus status;
    private String message;

    public ResponseEntity(String message) {
        this.message = message;
    }
}
