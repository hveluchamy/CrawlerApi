package com.demo.crawlerapi.Repository;

import com.demo.crawlerapi.Entity.DuplicatesEmailRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DuplicateEamilRecordRepository extends JpaRepository<DuplicatesEmailRecords, Long> {
    @Query("SELECT u FROM DuplicatesEmailRecords u WHERE u.email=?1")
    List<DuplicatesEmailRecords> findByEmail(String email);

    @Query("select DISTINCT(c.email) from DuplicatesEmailRecords c")
    List<String> getDiscintctEmails();
}
