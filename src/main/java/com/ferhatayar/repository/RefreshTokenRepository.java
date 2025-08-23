package com.ferhatayar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferhatayar.model.RefreshToken;
import com.ferhatayar.model.Users;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	Optional<RefreshToken> findByRefreshToken(String refreshToken);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM RefreshToken rt WHERE rt.user = :user")
	void deleteAllByUser(@Param("user") Users user);
	
}
