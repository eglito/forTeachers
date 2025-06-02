package com.forteachers.repositories;

import com.forteachers.InvitationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<InvitationToken,Long> {
}
