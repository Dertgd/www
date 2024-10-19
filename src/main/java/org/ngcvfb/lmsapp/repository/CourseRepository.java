package org.ngcvfb.lmsapp.repository;

import org.ngcvfb.lmsapp.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long> {
    List<CourseModel> findByTagsContaining(String tag);
}
