package com.ing.warehouse.Repository;

import com.ing.warehouse.Entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {


    @Query("SELECT b from Box  b where b.isDeleted=false order by b.name asc")
    List<Box> findAllBoxes();

    @Query("SELECT b from Box  b where  b.name=:name and b.isDeleted=false")
    Optional<Box> findByBoxName(@Param("name") String name);

    @Query("SELECT b from Box  b where b.id=:id and b.isDeleted=false")
    Optional<Box> findByBoxId(@Param("id") Long id);

}
