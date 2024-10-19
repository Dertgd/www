package org.ngcvfb.lmsapp.repository;

import org.ngcvfb.lmsapp.model.HomeWorkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWorkModel, Long> {
}
