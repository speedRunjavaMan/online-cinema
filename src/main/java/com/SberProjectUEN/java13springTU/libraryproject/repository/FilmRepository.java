package com.SberProjectUEN.java13springTU.libraryproject.repository;

import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmRepository
        extends GenericRepository<Film> {
    @Query(nativeQuery = true,
//            value = """
//                    select b.*
//                    from films b
//                    where b.title ilike '%' || nullif(:title, b.title) или coalesce(:title,'%')|| '%'
//                    and cast(b.genre as char) like coalesce(:genre,'%')
//                          """)
    value = """
                 select distinct f.*
                 from films f
                 left join films_directors fd on f.id = fd.film_id
                 join directors d on d.id = fd.director_id
                 where f.title ilike '%' || coalesce(:title,'%') || '%'
                 and cast(f.genre as char) like coalesce(:genre,'%')
                 and d.directors_fio ilike '%' || :directors_fio || '%'
                 and f.is_deleted = false
                      """)

    Page<Film> searchFilms(@Param(value = "genre") String genre,
                           @Param(value = "title") String title,
                           @Param(value = "directors_fio") String directors_fio,
                           Pageable pageable);

    @Query("""
            select case when count(b) > 0 then false else true end
            from Film b join FilmRentInfo bri on b.id = bri.film.id
            where b.id = :id and bri.returned = false
            """)
    boolean checkFilmForDeletion(final Long id);

    Page<Film> findAllByIsDeletedFalse(Pageable pageable);
}
