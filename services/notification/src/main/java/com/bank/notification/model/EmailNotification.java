package com.bank.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("EMAIL")
@Getter
@Setter
public class EmailNotification extends Notification{

    private String subject;

    @Column(name = "to_email")
    private String toEmail;
}
