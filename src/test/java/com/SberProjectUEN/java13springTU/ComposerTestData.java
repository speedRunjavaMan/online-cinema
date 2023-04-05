package com.SberProjectUEN.java13springTU;

import com.SberProjectUEN.java13springTU.libraryproject.dto.ComposerDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Composer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface ComposerTestData {
    ComposerDTO COMPOSER_DTO_1 = new ComposerDTO(
            "composersFio1",
            "position1",
            false,
            new HashSet<>());

    ComposerDTO COMPOSER_DTO_2 = new ComposerDTO(
            "composersFio2",
            "position2",
            false,
            new HashSet<>());

    ComposerDTO COMPOSER_DTO_3_DELETED = new ComposerDTO(
            "composersFio3",
            "position3",
            true,
            new HashSet<>());

    List<ComposerDTO> COMPOSER_DTO_LIST = Arrays.asList(COMPOSER_DTO_1, COMPOSER_DTO_2, COMPOSER_DTO_3_DELETED);


    Composer COMPOSER_1 = new Composer(
            "composersFio1",
            "position1",
            null);

    Composer COMPOSER_2 = new Composer(
            "composersFio2",
            "position2",
            null);

    Composer COMPOSER_3 = new Composer(
            "composersFio2",
            "position3",
            null);

    List<Composer> COMPOSER_LIST = Arrays.asList(COMPOSER_1, COMPOSER_2, COMPOSER_3);
}


