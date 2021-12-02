package net.atos.api.notafiscal.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;

@Repository
public interface NotaFiscalRepository extends PagingAndSortingRepository<NotaFiscalEntity, Long>{

	public Optional<List<NotaFiscalEntity>> findByDocumento(String documento);

	
	public Page<NotaFiscalEntity> findByDataEmissaoBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
	


}
