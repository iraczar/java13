import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Обсуждение Хлеба",
                "Проект Хлебозавод",
                "Завтра утром"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        // Поиск по "Хлеб"
        Task[] result = todos.search("Хлеб");
        Assertions.assertEquals(3, result.length);
        Assertions.assertArrayEquals(new Task[]{simpleTask, epic, meeting}, result);

        // Поиск по "Молоко"
        result = todos.search("Молоко");
        Assertions.assertEquals(1, result.length);
        Assertions.assertArrayEquals(new Task[]{epic}, result);

        // Поиск по "Обсуждение"
        result = todos.search("Обсуждение");
        Assertions.assertEquals(1, result.length);
        Assertions.assertArrayEquals(new Task[]{meeting}, result);

        // Поиск по несуществующему запросу
        result = todos.search("Сыр");
        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void shouldSearchWithEmptyQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");
        Todos todos = new Todos();
        todos.add(simpleTask);

        // При пустом запросе String.contains("") всегда возвращает true
        // Поэтому все задачи должны быть найдены
        Task[] result = todos.search("");
        Assertions.assertEquals(1, result.length);
        Assertions.assertArrayEquals(new Task[]{simpleTask}, result);
    }

    @Test
    public void shouldSearchInEmptyTodos() {
        Todos todos = new Todos();
        Task[] result = todos.search("Хлеб");
        Assertions.assertEquals(0, result.length);

        // Проверка с пустым запросом для пустого списка
        result = todos.search("");
        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void shouldSearchCaseSensitive() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);

        // Поиск с учетом регистра
        Task[] result = todos.search("хлеб"); // строчная буква
        Assertions.assertEquals(0, result.length);

        result = todos.search("Хлеб"); // заглавная буква
        Assertions.assertEquals(2, result.length);
    }
}