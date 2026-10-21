package com.bank.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("SMS")
@Getter
@Setter
public class SmsNotification extends Notification{
    @Column(name = "phone_number")
    private String phoneNumber;
}
