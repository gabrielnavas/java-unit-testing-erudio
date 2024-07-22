package io.github.gabrielnavas.rest_with_springboot.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("""
                SELECT p
                FROM Person p
                WHERE LOWER(p.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(p.address) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(p.gender) LIKE LOWER(CONCAT('%', :query, '%'))
            """
    )
    Page<Person> findAllByQuery(
            @Param("query") String query,
            Pageable pageable
    );
}
