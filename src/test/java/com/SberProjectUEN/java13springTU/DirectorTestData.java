package com.SberProjectUEN.java13springTU;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Director;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface DirectorTestData {
    DirectorDTO DIRECTOR_DTO_1 = new DirectorDTO(
            "directorsFio1",
            "position1",
            false,
            new HashSet<>());

    DirectorDTO DIRECTOR_DTO_2 = new DirectorDTO(
            "directorsFio2",
            "position2",
            false,
            new HashSet<>());

    DirectorDTO DIRECTOR_DTO_3_DELETED = new DirectorDTO(
            "directorsFio3",
            "position3",
            true,
            new HashSet<>());

    List<DirectorDTO> DIRECTOR_DTO_LIST = Arrays.asList(DIRECTOR_DTO_1, DIRECTOR_DTO_2, DIRECTOR_DTO_3_DELETED);


    Director DIRECTOR_1 = new Director(
            "directorsFio1",
            "position1",
            null);

    Director DIRECTOR_2 = new Director(
            "directorsFio2",
            "position2",
            null);

    Director DIRECTOR_3 = new Director(
            "directorsFio2",
            "position3",
            null);

    List<Director> DIRECTOR_LIST = Arrays.asList(DIRECTOR_1, DIRECTOR_2, DIRECTOR_3);
}
//