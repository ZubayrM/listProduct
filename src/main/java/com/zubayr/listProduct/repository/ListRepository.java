package com.zubayr.listProduct.repository;

import com.zubayr.listProduct.model.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {

    @Modifying
    @Query(value = "update List l set l.name = :newName where l.id = :listId")
    void updateNameList(Long listId, String newName);
}
