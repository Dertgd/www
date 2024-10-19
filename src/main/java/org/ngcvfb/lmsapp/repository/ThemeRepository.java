package org.ngcvfb.lmsapp.repository;

import org.ngcvfb.lmsapp.model.ThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {

}
