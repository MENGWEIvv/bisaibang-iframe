package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Iframe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Iframe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IframeRepository extends JpaRepository<Iframe, Long> {

}
