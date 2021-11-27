package net.atos.api.notafiscal.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;

@Repository
public interface NotaFiscalRepository extends CrudRepository<NotaFiscalEntity, Long>{

	public Optional<List<NotaFiscalEntity>> findByDocumento(String documento);

	public Optional<List<NotaFiscalEntity>> findByDataEmissaoBetween(LocalDate dataInicio, LocalDate dataFim);

}
