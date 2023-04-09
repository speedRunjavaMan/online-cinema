package com.SberProjectUEN.java13springTU.libraryproject.repository;

import com.SberProjectUEN.java13springTU.libraryproject.model.Composer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ComposerRepository
        extends GenericRepository<Composer> {
    Page<Composer> findAllByIsDeletedFalse(Pageable pageable);

    Page<Composer> findAllByComposersFioContainsIgnoreCaseAndIsDeletedFalse(String fio,
                                                                            Pageable pageable);

    @Query(value = """
          select case when count(a) > 0 then false else true end
          from Composer a join a.films b
                        join FilmRentInfo bri on b.id = bri.film.id
          where a.id = :composerId
          and bri.returned = false
          """)
    boolean checkComposerForDeletion(final Long composerId);
}


