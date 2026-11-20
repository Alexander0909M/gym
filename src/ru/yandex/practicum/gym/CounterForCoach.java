package ru.yandex.practicum.gym;

public class CounterForCoach implements Comparable<CounterForCoach> {
    private final Coach coach;
    private final int countTrainingSessions;

    public CounterForCoach(Coach coach, int countTrainingSessions) {
        this.coach = coach;
        this.countTrainingSessions = countTrainingSessions;
    }

    public Coach getCoach() {
        return coach;
    }

    @Override
    public int compareTo(CounterForCoach o) {
        return o.countTrainingSessions - countTrainingSessions;
    }

    public int getCountTrainingSessions() {
        return countTrainingSessions;
    }
}