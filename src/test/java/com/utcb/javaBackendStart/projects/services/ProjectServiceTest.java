package com.utcb.javaBackendStart.projects.services;

import com.utcb.javaBackendStart.projects.dtos.CreateProjectDto;
import com.utcb.javaBackendStart.projects.dtos.ProjectDto;
import com.utcb.javaBackendStart.projects.entities.ProjectEntity;
import com.utcb.javaBackendStart.projects.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.argThat;

@SpringBootTest
class ProjectServiceTest {

    UUID projectId = UUID.randomUUID();
    String projectName = "Test project";
    ProjectEntity returnedEntity = new ProjectEntity(projectId, projectName, LocalDate.now(), LocalDate.now(), 5, null, new ArrayList<>(), new ArrayList<>());

    @Mock
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(
                projectRepository.save(Mockito.any(argThat(entity -> entity.getName().equals(projectName))))
        ).thenReturn(returnedEntity);
    }

    @Test
    public void happyPath() {
        CreateProjectDto dto = new CreateProjectDto(projectName, LocalDate.now(), LocalDate.now(), 5);

        ProjectDto createdDto = projectService.createProject(dto);

        assertEquals(dto.getName(), createdDto.getName());
        assertEquals(dto.getNumberOfMonths(), createdDto.getNumberOfMonths());
        assertEquals(dto.getStartDate(), createdDto.getStartDate());
        assertEquals(dto.getEndDate(), createdDto.getEndDate());
        assertNull(createdDto.getDirector());
        assertEquals(createdDto.getWorkPackages().size(), 0);
    }
}
