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


### Screenshot of using tool & example output:
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
Reference1: https://developers.google.com/calendar/api/quickstart/java  
Reference2: https://developers.google.com/calendar/api/v3/reference/calendarList/list  
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
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/* class to demonstrate use of Calendar events list API */
public class GetCalendarList {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Test: GetCalendarList";
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

        String pageToken = null;
        do {
            CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
            List<CalendarListEntry> items = calendarList.getItems();
            for (CalendarListEntry calendarListEntry : items) {
                System.out.println(calendarListEntry.getSummary() + ": " + calendarListEntry.getId());
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);
    }
}
```
Result:  
```
> Task :compileJava
> Task :processResources UP-TO-DATE
> Task :classes

> Task :GetCalendarList.main()
jaejoon.jj.han@gmail.com: jaejoon.jj.han@gmail.com
Birthdays: addressbook#contacts@group.v.calendar.google.com
Holidays in Canada: en.canadian#holiday@group.v.calendar.google.com
Test Calendar: d823735b3ebceb4f532f389774e3a3052897c18e45eec4c78762bee63f68f601@group.calendar.google.com
```

### Potential future problems and solutions:
Please note that we have no current problems blocking our progress. Thinking ahead, however, we can pinpoint a few areas that might present themselves as challenges. 

1. Integration with Google APIs:
Problem: Integrating with Google Calendar and Google Task APIs can be complex, requiring authentication, handling access tokens, and dealing with potential API changes.
Solution: Make sure to follow the official documentation provided by Google for these APIs. Keep our API credentials and tokens secure. Regularly update our code to accommodate changes in the APIs by keeping an eye out for updates.

2. User Engagement and Gamification:
Problem: Implementing gamification features can be challenging and might not appeal to all users.
Solution: Conduct proper user research to understand what gamification elements resonate with our target audience. Allow users to opt-in or customize the level of gamification they want to experience. Regularly update and refine gamification features based on user feedback.

3.Data Security and Privacy:
Problem: Handling our uses'r data such as tasks, events, and personal information requires a robust approach to data security and privacy.
Solution: Implement strong data encryption for sensitive information. Comply with data protection regulations at all times. Conduct regular security audits and penetration testing.


### Contributors (Please Access Shared Google Doc for Details):
Problem Domain: Jenny

Development Plan: Narges

Documentation Link: Lucy

Screenshot & Running: JJ

Repository Management: Joonsung

Future Problems: Narges

Editors: All
