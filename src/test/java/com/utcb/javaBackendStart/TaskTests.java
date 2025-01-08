//package com.utcb.projectManagementAPI;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TaskTests {
////    @Test
////    public void test_valid_input_parameters() {
////        Task task = new Task(1L, "Title", "Description", false);
////        assertNotNull(task);
////        assertEquals(1L, task.getId());
////        assertEquals("Title", task.getTitle());
////        assertEquals("Description", task.getDescription());
////        assertFalse(task.isCompleted());
////    }
//
//    @Test
//    public void test_addTask_addsTaskToSetAndSetsWorkPackage() {
//        // Arrange
//        WorkPackage workPackage = new WorkPackage();
//        Task task = new Task();
//
//        // Act
//        workPackage.addTask(task);
//
//        // Assert
//        assertTrue(workPackage.getTasks().contains(task));
//        assertEquals(workPackage, task.getWorkPackage());
//    }
//}
