package com.forteachers.repositories;

import com.forteachers.adapters.outputAdapters.InvitationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<InvitationToken,Long> {

    Optional<InvitationToken> findByToken(String token);
}
