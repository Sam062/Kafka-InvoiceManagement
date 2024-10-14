package base.repo;

import base.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> findByIsValid(boolean isValid);

}
