package it.discovery.notification.persistence;

import it.discovery.notification.domain.CustomerSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSettingRepository extends JpaRepository<CustomerSetting, Integer> {
}
