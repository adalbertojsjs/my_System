package com.repository.My_System.domain.ports.out;

import com.repository.My_System.domain.model.Receipt;


public interface RepositoryReceip {

    Receipt save(Receipt receipt);
}
