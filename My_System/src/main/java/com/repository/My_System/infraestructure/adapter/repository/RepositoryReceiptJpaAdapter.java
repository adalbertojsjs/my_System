package com.repository.My_System.infraestructure.adapter.repository;

import com.repository.My_System.domain.model.Receipt;
import com.repository.My_System.domain.ports.out.RepositoryReceip;
import com.repository.My_System.infraestructure.mapper.ReceiptMapper;
import com.repository.My_System.infraestructure.repository.RepositoryReceipJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@AllArgsConstructor
@Repository
public class RepositoryReceiptJpaAdapter implements RepositoryReceip {

    private final RepositoryReceipJpa repositoryReceipJpa;
    private final ReceiptMapper mapper;

        @Override
        public Receipt save(Receipt receipt) {
           var receip = repositoryReceipJpa.save(mapper.toEntity(receipt));

            return mapper.toDomain(receip);

        }
}
