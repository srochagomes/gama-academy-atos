package net.atos.api.logistica.repository;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.logistica.repository.entity.OrdemServicoEntity;

@Repository
public interface LogisticaRepository extends PagingAndSortingRepository<OrdemServicoEntity, Long>{
	
	public Optional<OrdemServicoEntity> findByIdNotaFiscal(Long idNF);

	public Page<OrdemServicoEntity> findByDataEmissaoBetween(@NotNull LocalDate inicio, @NotNull LocalDate fim, Pageable pageable); 

}
