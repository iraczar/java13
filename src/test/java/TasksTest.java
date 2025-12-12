import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {

    @Test
    public void testSimpleTaskMatches() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        assertTrue(simpleTask.matches("Позвонить"));
        assertTrue(simpleTask.matches("родителям"));
        assertFalse(simpleTask.matches("Написать"));
        assertFalse(simpleTask.matches("позвонить")); // регистр имеет значение
    }

    @Test
    public void testEpicMatches() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        assertTrue(epic.matches("Молоко"));
        assertTrue(epic.matches("Яйца"));
        assertTrue(epic.matches("Хлеб"));
        assertFalse(epic.matches("Сыр"));
        assertFalse(epic.matches("молоко")); // регистр имеет значение
    }

    @Test
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        assertTrue(meeting.matches("Выкатка"));
        assertTrue(meeting.matches("НетоБанка"));
        assertFalse(meeting.matches("вторник")); // ищем только в topic и project
        assertFalse(meeting.matches("версия")); // регистр имеет значение
    }

    @Test
    public void testTaskEqualsAndHashCode() {
        SimpleTask task1 = new SimpleTask(1, "Задача 1");
        SimpleTask task2 = new SimpleTask(1, "Задача 2");
        SimpleTask task3 = new SimpleTask(2, "Задача 1");

        assertEquals(task1, task2);
        assertNotEquals(task1, task3);
        assertEquals(task1.hashCode(), task2.hashCode());
    }
}