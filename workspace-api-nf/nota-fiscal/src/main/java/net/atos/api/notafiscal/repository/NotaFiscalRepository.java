package net.atos.api.notafiscal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;

@Repository
public interface NotaFiscalRepository extends CrudRepository<NotaFiscalEntity, Long>{

}
