package net.atos.api.logistica.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.logistica.repository.entity.OrdemServicoEntity;

@Repository
public interface LogisticaRepository extends PagingAndSortingRepository<OrdemServicoEntity, Long>{

}
