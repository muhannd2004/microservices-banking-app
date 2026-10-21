CREATE TABLE notification (
      notification_id BIGSERIAL PRIMARY KEY,
      message         TEXT NOT NULL,
      message_type    VARCHAR(20) NOT NULL,
      subject         VARCHAR(255),
      to_email        VARCHAR(255),
      phone_number    VARCHAR(20),
      created_at      TIMESTAMP NOT NULL,
      created_by      VARCHAR(50) NOT NULL,
      updated_at      TIMESTAMP,
      updated_by      VARCHAR(50)

    CONSTRAINT chk_email_fields CHECK (
        (message_type != 'EMAIL') OR (to_email IS NOT NULL AND subject IS NOT NULL)
    ),
    CONSTRAINT chk_sms_fields CHECK (
        (message_type != 'SMS') OR (phone_number IS NOT NULL)
    )
);