package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId);
        calendar = new ArrayList<>();
        inboxCapacity(Integer.MAX_VALUE);

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int maxMeetings = 0;
        int numMeetings = 0;
        ArrayList<LocalTime> times = new ArrayList<>();
        for (Meeting meeting : calendar) {
            times.add(meeting.getStartTime());
            times.add(meeting.getEndTime());
        }
        Collections.sort(times);
        for (LocalTime time : times) {
            for (Meeting meeting : calendar) {
                if (time.equals(meeting.getStartTime())) {
                    numMeetings++;
                    if (numMeetings > maxMeetings) {
                        maxMeetings = numMeetings;
                    }
                } else if (time.equals(meeting.getEndTime())) {
                    numMeetings--;
                }
            }
        }
        return maxMeetings;
    }
}
