package it.discovery.notification.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "notifications")
@Entity
public class Notification extends BaseEntity {
    private String recipient;

    private String email;

    private String title;

    private String text;
}
