package io.github.gabrielnavas.rest_with_springboot.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

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


    Optional<Person> findByEmail(String email);

    @Query("""
            SELECT p 
            FROM Person p
            WHERE p.firstName = ?1
            AND p.lastName = ?2
            """
    )
    Optional<Person> findByFirstNameAndLastNameIndexParameters(String firstName, String lastName);

    @Query("""
            SELECT p 
            FROM Person p
            WHERE p.firstName = :firstName
            AND p.lastName = :lastName
            """
    )
    Optional<Person> findByFirstNameAndLastNameBasic(String firstName, String lastName);

    @Query("""
            SELECT p 
            FROM Person p
            WHERE p.firstName = :first_name
            AND p.lastName = :last_name
            """
    )
    Optional<Person> findByFirstNameAndLastNameParamNamed(@Param("first_name") String firstName, @Param("last_name") String lastName);

    @Query(value = """
            select * 
            from person p
            where p.first_name = ?1
            and p.last_name = ?2
            """,
            nativeQuery = true
    )
    Optional<Person> findByFirstNameAndLastNameNativeSQL(String firstName, String lastName);
}
