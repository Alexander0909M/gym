package ru.yandex.practicum.gym;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TimetableTest {
    Timetable timetable;
    static Coach coach;
    static Group groupAdult;
    static Group groupChild;

    @BeforeAll
    static void beforeAll() {
        coach = new Coach("Шварцнегер", "Арнольд", "Иванович");
        groupAdult = new Group("Качалка для взрослых", Age.ADULT, 60);
        groupChild = new Group("Качалка для детей", Age.CHILD, 60);
    }

    @BeforeEach
    void beforeEach() {
        timetable = new Timetable();
    }

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        //Проверить, что за вторник не вернулось занятий
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        // Проверить, что за вторник не вернулось занятий
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        //Проверить, что за понедельник в 14:00 не вернулось занятий
    }

    @Test
    void getCountByCoachesForMultiSessionsMultiCouches() {
        Coach coach1 = new Coach("Ли", null, null);
        Coach coach2 = new Coach("Чан", null, null);
        TrainingSession session1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(11, 0));
        TrainingSession session2 = new TrainingSession(groupChild, coach,
                DayOfWeek.TUESDAY, new TimeOfDay(10, 40));
        TrainingSession session3 = new TrainingSession(groupAdult, coach2,
                DayOfWeek.WEDNESDAY, new TimeOfDay(20, 15));
                timetable.addNewTrainingSession(session1);
        timetable.addNewTrainingSession(session2);
        timetable.addNewTrainingSession(session3);

        List<CounterForCoach> counter = timetable.getCountByCoaches();
        assertEquals(3, counter.size());
        assertTrue(counter.get(0).getCountTrainingSessions() >= counter.get(1).getCountTrainingSessions());
        assertTrue(counter.get(1).getCountTrainingSessions() >= counter.get(2).getCountTrainingSessions());
    }


    @Test
    void getCountByCoachesIfTimetableIsEmpty() {
        List<CounterForCoach> counter = timetable.getCountByCoaches();
        assertEquals(0, counter.size());
    }

}