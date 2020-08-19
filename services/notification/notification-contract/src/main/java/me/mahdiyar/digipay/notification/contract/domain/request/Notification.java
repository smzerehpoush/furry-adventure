package me.mahdiyar.digipay.notification.contract.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private String id;
    private Long mobileNo;
    private String message;
}
