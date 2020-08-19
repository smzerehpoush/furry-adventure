package me.mahdiyar.digipay.notification.contract.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Data
public class SendNotificationRequestDto {
    @NotNull
    private String senderUserId;
    @NotNull
    private String message;
    @NotNull
    private Long mobileNo;
}
