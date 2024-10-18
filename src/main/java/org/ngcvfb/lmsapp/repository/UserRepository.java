package org.ngcvfb.lmsapp.repository;

import org.ngcvfb.lmsapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByTelegramId(Long telegramId);
}
