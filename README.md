# Group-261
repository of UofT CSC207 Group 261

## Problem domain:
Our group wants to focus on time management as our problem domain. Specifically, creating an application that will help motivate people to stay organized and complete their tasks, as well as efficiently make plans with other people. We are thinking about implementing a calendar system with a reward system when tasks are completed, as well as having features such as giving the weather report and showing what times users are collectively available at.

An API that we could use to help us implement our design is the Google Task API (ttps://developers.google.com/tasks/reference/rest/) to help users implement tasks and events efficiently. Below 


## What we want to develop:
Our team is currently in the conceptual stage of developing a versatile productivity application with a focus on personal task management and collaborative planning. The primary functionality of this application will be a personal to-do list and calendar that will enable users to schedule tasks, events, and make plans with other users, with a reward system where users are applauded for tasks they complete. This will either be in the form of a prize users can unlock or a printed message such as “Well done!”. 

This application aims to streamline and enhance users' daily routines by providing a seamless way to organize tasks, set deadlines, and efficiently allocate time for their personal and professional responsibilities. The calendar feature will facilitate coordination among users, allowing them to collaborate on shared plans and events, and the reward system (through prizes or messages) will motivate productivity.

In the future, we plan to expand the capabilities of the application by incorporating a gamification element. Users will be able to create and customize characters to represent themselves, each character embarking on their own unique adventure, whether it's leisure, study, or work-related. As users complete tasks and check off items on their to-do lists, these achievements will translate into milestones within their character's journey.

This gamified aspect will introduce an element of motivation and engagement, encouraging users to stay organized and accomplish their goals. By combining task management, collaboration, and gamification, our application aims to provide users with a comprehensive solution for productivity and personal development.

We welcome any feedback or suggestions that may help refine our project direction and ensure its effectiveness in meeting the needs of our target users.

We think that the Google Calendar API will help us implement our idea, and we included a screenshot below of experimenting with this API.

### Link to documentations:
https://developers.google.com/calendar/api/guides/overview


### Screenshot of using tool:
CalendarList: list  
Reference: https://developers.google.com/calendar/api/v3/reference/calendarList/list
![](/images/CalendarList_list.jpg?raw=true "CalendarList: list")

Result:
```json
{
    "kind": "calendar#calendarList",
    "etag": "\"p33sbd37hrrao20o\"",
    "nextSyncToken": "CPi2jPHe1YEDEhhqYWVqb29uLmpqLmhhbkBnbWFpbC5jb20=",
    "items": [
        ...   
        {
            "kind": "calendar#calendarListEntry",
            "etag": "\"1696193929569000\"",
            "id": "d823735b3ebceb4f532f389774e3a3052897c18e45eec4c78762bee63f68f601@group.calendar.google.com",
            "summary": "Test Calendar",
            "description": "testing",
            "timeZone": "America/Toronto",
            "colorId": "12",
            "backgroundColor": "#fad165",
            "foregroundColor": "#000000",
            "selected": true,
            "accessRole": "owner",
            "defaultReminders": [],
            "conferenceProperties": {
                "allowedConferenceSolutionTypes": [
                    "hangoutsMeet"
                ]
            }
        }
    ]
}
```
Events: list  
Reference: https://developers.google.com/calendar/api/v3/reference/events/list
![](/images/Events_list.jpg?raw=true "Events: list")

Result:
```json
{
    "kind": "calendar#events",
    "etag": "\"p33sdv5nnrrao20o\"",
    "summary": "Test Calendar",
    "description": "testing",
    "updated": "2023-10-01T20:59:27.403Z",
    "timeZone": "America/Toronto",
    "accessRole": "owner",
    "defaultReminders": [],
    "nextSyncToken": "CPjflvfe1YEDEPjflvfe1YEDGAUg0_uyjwI=",
    "items": [
        {
            "kind": "calendar#event",
            "etag": "\"3392387934806000\"",
            "id": "6egnphrsf9aqj588m7t6bpd3ol",
            "status": "confirmed",
            "htmlLink": "https://www.google.com/calendar/event?eid=NmVnbnBocnNmOWFxajU4OG03dDZicGQzb2wgZDgyMzczNWIzZWJjZWI0ZjUzMmYzODk3NzRlM2EzMDUyODk3YzE4ZTQ1ZWVjNGM3ODc2MmJlZTYzZjY4ZjYwMUBn",
            "created": "2023-10-01T20:59:27.000Z",
            "updated": "2023-10-01T20:59:27.403Z",
            "summary": "test event",
            "creator": {
                "email": "jaejoon.jj.han@gmail.com"
            },
            "organizer": {
                "email": "d823735b3ebceb4f532f389774e3a3052897c18e45eec4c78762bee63f68f601@group.calendar.google.com",
                "displayName": "Test Calendar",
                "self": true
            },
            "start": {
                "dateTime": "2023-10-01T20:00:00-04:00",
                "timeZone": "America/Toronto"
            },
            "end": {
                "dateTime": "2023-10-01T21:00:00-04:00",
                "timeZone": "America/Toronto"
            },
            "iCalUID": "6egnphrsf9aqj588m7t6bpd3ol@google.com",
            "sequence": 0,
            "reminders": {
                "useDefault": true
            },
            "eventType": "default"
        }
    ]
}
```

Java code requesting event list  
Reference: https://developers.google.com/calendar/api/quickstart/java  
```java
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/* class to demonstrate use of Calendar events list API */
public class CalendarQuickstart {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        // List the next 10 events from the <calenarId>.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("d823735b3ebceb4f532f389774e3a3052897c18e45eec4c78762bee63f68f601@group.calendar.google.com")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }
}
```
Result:  
```
> Task :compileJava UP-TO-DATE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE

> Task :CalendarQuickstart.main()
Upcoming events
test event (2023-10-01T20:00:00.000-04:00)
```
### Contributors (Please Access Shared Google Doc for Details):
Problem Domain: Jenny

Development Plan: Narges

Documentation Link: Lucy

Screenshot & Running: JJ

Repository Management: Joonsung

Editors: All
