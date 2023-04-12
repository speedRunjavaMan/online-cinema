package com.SberProjectUEN.java13springTU.onlinecinemaproject.service;


import com.SberProjectUEN.java13springTU.ComposerTestData;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.AddFilmDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.ComposerDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper.ComposerMapper;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Composer;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.ComposerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class ComposerServiceTest
        extends GenericTest<Composer, ComposerDTO> {

    public ComposerServiceTest() {
        super();
        FilmService filmService = Mockito.mock(FilmService.class);
        repository = Mockito.mock(ComposerRepository.class);
        mapper = Mockito.mock(ComposerMapper.class);
        service = new ComposerService((ComposerRepository) repository, (ComposerMapper) mapper, filmService);
    }

    @Test
    @Order(9)
    @Override
    protected void getAll() {
        Mockito.when(repository.findAll()).thenReturn(ComposerTestData.COMPOSER_LIST);
        Mockito.when(mapper.toDTOs(ComposerTestData.COMPOSER_LIST)).thenReturn(ComposerTestData.COMPOSER_DTO_LIST);
        List<ComposerDTO> composerDTOS = service.listAll();
        log.info("Testing getAll(): " + composerDTOS);
        assertEquals(ComposerTestData.COMPOSER_LIST.size(), composerDTOS.size());
    }

    @Test
    @Order(2)
    @Override
    protected void getOne() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(ComposerTestData.COMPOSER_1));
        Mockito.when(mapper.toDTO(ComposerTestData.COMPOSER_1)).thenReturn(ComposerTestData.COMPOSER_DTO_1);
        ComposerDTO composerDTO = service.getOne(1L);
        log.info("Testing getOne(): " + composerDTO);
        assertEquals(ComposerTestData.COMPOSER_DTO_1, composerDTO);
    }

    @Order(3)
    @Test
    @Override
    protected void create() {
        Mockito.when(mapper.toEntity(ComposerTestData.COMPOSER_DTO_1)).thenReturn(ComposerTestData.COMPOSER_1);
        Mockito.when(mapper.toDTO(ComposerTestData.COMPOSER_1)).thenReturn(ComposerTestData.COMPOSER_DTO_1);
        Mockito.when(repository.save(ComposerTestData.COMPOSER_1)).thenReturn(ComposerTestData.COMPOSER_1);
        ComposerDTO composerDTO = service.create(ComposerTestData.COMPOSER_DTO_1);
        log.info("Testing create(): " + composerDTO);
        assertEquals(ComposerTestData.COMPOSER_DTO_1, composerDTO);
    }

    @Order(4)
    @Test
    @Override
    protected void update() {
        Mockito.when(mapper.toEntity(ComposerTestData.COMPOSER_DTO_1)).thenReturn(ComposerTestData.COMPOSER_1);
        Mockito.when(mapper.toDTO(ComposerTestData.COMPOSER_1)).thenReturn(ComposerTestData.COMPOSER_DTO_1);
        Mockito.when(repository.save(ComposerTestData.COMPOSER_1)).thenReturn(ComposerTestData.COMPOSER_1);
        ComposerDTO composerDTO = service.update(ComposerTestData.COMPOSER_DTO_1);
        log.info("Testing update(): " + composerDTO);
        assertEquals(ComposerTestData.COMPOSER_DTO_1, composerDTO);
    }

    @Order(5)
    @Test
    @Override
    protected void delete() throws MyDeleteException {
        Mockito.when(((ComposerRepository) repository).checkComposerForDeletion(1L)).thenReturn(true);
//        Mockito.when(composerRepository.checkComposerForDeletion(2L)).thenReturn(false);
        Mockito.when(repository.save(ComposerTestData.COMPOSER_1)).thenReturn(ComposerTestData.COMPOSER_1);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(ComposerTestData.COMPOSER_1));
        log.info("Testing delete() before: " + ComposerTestData.COMPOSER_1.isDeleted());
        service.delete(1L);
        log.info("Testing delete() after: " + ComposerTestData.COMPOSER_1.isDeleted());
        assertTrue(ComposerTestData.COMPOSER_1.isDeleted());
    }

    @Order(6)
    @Test
    @Override
    protected void restore() {
        ComposerTestData.COMPOSER_3.setDeleted(true);
        Mockito.when(repository.save(ComposerTestData.COMPOSER_3)).thenReturn(ComposerTestData.COMPOSER_3);
        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(ComposerTestData.COMPOSER_3));
        log.info("Testing restore() before: " + ComposerTestData.COMPOSER_3.isDeleted());
        ((ComposerService) service).restore(3L);
        log.info("Testing restore() after: " + ComposerTestData.COMPOSER_3.isDeleted());
        assertFalse(ComposerTestData.COMPOSER_3.isDeleted());
    }

    @Order(7)
    @Test
    void searchComposers() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "composersFio"));
        Mockito.when(((ComposerRepository) repository).findAllByComposersFioContainsIgnoreCaseAndIsDeletedFalse("composersFio1", pageRequest))
                .thenReturn(new PageImpl<>(ComposerTestData.COMPOSER_LIST));
        Mockito.when(mapper.toDTOs(ComposerTestData.COMPOSER_LIST)).thenReturn(ComposerTestData.COMPOSER_DTO_LIST);
        Page<ComposerDTO> composerDTOList = ((ComposerService) service).searchComposers("composersFio1", pageRequest);
        log.info("Testing searchComposers(): " + composerDTOList);
        assertEquals(ComposerTestData.COMPOSER_DTO_LIST, composerDTOList.getContent());
    }

    @Order(8)
    @Test
    void addFilm() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(ComposerTestData.COMPOSER_1));
        Mockito.when(service.getOne(1L)).thenReturn(ComposerTestData.COMPOSER_DTO_1);
        Mockito.when(repository.save(ComposerTestData.COMPOSER_1)).thenReturn(ComposerTestData.COMPOSER_1);
        ((ComposerService) service).addFilm(new AddFilmDTO(1L, 1L, 1L));
        log.info("Testing addFilm(): " + ComposerTestData.COMPOSER_DTO_1.getFilmsIds());
        assertTrue(ComposerTestData.COMPOSER_DTO_1.getFilmsIds().size() >= 1);
    }

    @Order(1)
    @Test
    protected void getAllNotDeleted() {
        ComposerTestData.COMPOSER_3.setDeleted(true);
        List<Composer> notDeletedComposers = ComposerTestData.COMPOSER_LIST.stream().filter(Predicate.not(Composer::isDeleted)).toList();
        Mockito.when(repository.findAllByIsDeletedFalse()).thenReturn(notDeletedComposers);
        ComposerTestData.COMPOSER_DTO_3_DELETED.setDeleted(true);
        Mockito.when(mapper.toDTOs(notDeletedComposers)).thenReturn(
                ComposerTestData.COMPOSER_DTO_LIST.stream().filter(Predicate.not(ComposerDTO::isDeleted)).toList());
        List<ComposerDTO> composerDTOS = service.listAllNotDeleted();
        log.info("Testing getAllNotDeleted(): " + composerDTOS);
        assertEquals(notDeletedComposers.size(), composerDTOS.size());
    }
}
//

