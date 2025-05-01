package com.example.kurs.repositories;

import com.example.kurs.models.FidBack;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FidBackRepo extends JpaRepository<FidBack, Long> {
    @Query("SELECT f FROM FidBack f ORDER BY f.likes DESC")
    List<FidBack> findTop2ByOrderByLikesDesc(Pageable pageable);

    @Query("SELECT f FROM FidBack f ORDER BY f.dislikes DESC")
    List<FidBack> findTop2ByOrderByDislikesDesc(Pageable pageable);

    @Query("SELECT f FROM FidBack f WHERE f.id NOT IN :excludeIds ORDER BY f.createdAt DESC")
    List<FidBack> findRecentExcludingIds(@Param("excludeIds") List<Long> excludeIds, Pageable pageable);
}