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

### Contributors (Please see Shared Google Doc for Details):
Problem Domain: Jenny

Development Plan: Narges

Documentation Link: Lucy

Screenshot & Running: JJ

Repository Management: Joonsung

Editors: All
