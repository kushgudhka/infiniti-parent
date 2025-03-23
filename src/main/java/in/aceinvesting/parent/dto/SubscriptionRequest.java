package in.aceinvesting.parent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Kush Gudhka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {
    private String[] instruments; // List of instruments to subscribe to
}