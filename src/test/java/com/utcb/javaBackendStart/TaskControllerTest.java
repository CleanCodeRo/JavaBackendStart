//package com.utcb.projectManagementAPI;
//
//
//
//import org.springframework.http.HttpStatus;
//import org.testng.annotations.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class TaskControllerTest {
//
//
//    // creates a new task with valid input and returns a response with status code 201
//    @Test
//    public void test_taskController_validInput_returns201() {
//        // Arrange
////        TaskService taskService = mock(TaskService.class);
////        TaskController taskController = new TaskController(taskService);
////        Task task = new Task();
////        task.setTitle("Task 1");
////        task.setDescription("Description 1");
//
//        // Act
//        ResponseEntity<Task> response = taskController.createTask(task);
//
//        // Assert
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        verify(taskService, times(1)).saveTask(task);
//    }
//
//    // creates a new task with valid input and saves it to the database
//    @Test
//    public void test_taskController_validInput_savesToDatabase() {
//        // Arrange
//        TaskService taskService = mock(TaskService.class);
//        TaskController taskController = new TaskController(taskService);
//        Task task = new Task();
//        task.setTitle("Task 1");
//        task.setDescription("Description 1");
//
//        // Act
//        ResponseEntity<Task> response = taskController.createTask(task);
//
//        // Assert
//        verify(taskService, times(1)).saveTask(task);
//    }
//
//    // creates a new task with a non-mandatory description field and returns a response with status code 201
//    @Test
//    public void test_taskController_nonMandatoryDescription_returns201() {
//        // Arrange
//        TaskService taskService = mock(TaskService.class);
//        TaskController taskController = new TaskController(taskService);
//        Task task = new Task();
//        task.setTitle("Task 1");
//
//        // Act
//        ResponseEntity<Task> response = taskController.createTask(task);
//
//        // Assert
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        verify(taskService, times(1)).saveTask(task);
//    }
//
//    // returns a response with status code 400 when title field is missing
//    @Test
//    public void test_taskController_missingTitle_returns400() {
//        // Arrange
//        TaskService taskService = mock(TaskService.class);
//        TaskController taskController = new TaskController(taskService);
//        Task task = new Task();
//
//        // Act
//        ResponseEntity<Task> response = taskController.createTask(task);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(taskService, never()).saveTask(any(Task.class));
//    }
//
//    // returns a response with status code 400 when title field is empty
//    @Test
//    public void test_taskController_emptyTitle_returns400() {
//        // Arrange
//        TaskService taskService = mock(TaskService.class);
//        TaskController taskController = new TaskController(taskService);
//        Task task = new Task();
//        task.setTitle("");
//
//        // Act
//        ResponseEntity<Task> response = taskController.createTask(task);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(taskService, never()).saveTask(any(Task.class));
//    }
//
//    // returns a response with status code 400 when request body is empty
//    @Test
//    public void test_taskController_emptyRequestBody_returns400() {
//        // Arrange
//        TaskService taskService = mock(TaskService.class);
//        TaskController taskController = new TaskController(taskService);
//
//        // Act
//        ResponseEntity<Task> response = taskController.createTask(null);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(taskService, never()).saveTask(any(Task.class));
//    }
//
//}
