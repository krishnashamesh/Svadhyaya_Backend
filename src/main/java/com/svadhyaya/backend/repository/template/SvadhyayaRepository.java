package com.svadhyaya.backend.repository.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface SvadhyayaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    void refresh(T t);
}

