package base.repo;

import base.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<InvoiceEntity, Long> {
}
