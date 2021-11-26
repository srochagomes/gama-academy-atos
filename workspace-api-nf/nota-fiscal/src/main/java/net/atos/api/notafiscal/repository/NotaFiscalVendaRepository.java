package net.atos.api.notafiscal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

@Repository
public interface NotaFiscalVendaRepository extends CrudRepository<NotaFiscalVendaEntity, Long>{

}
