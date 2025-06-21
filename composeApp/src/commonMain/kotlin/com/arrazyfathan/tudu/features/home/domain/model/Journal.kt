package com.arrazyfathan.tudu.features.home.domain.model

data class DummyJournal(
    val title: String,
    val content: String,
    val tags: List<String>,
)

val dummyJournal =
    arrayOf(
        DummyJournal(
            "Morning Thoughts",
            "Today I woke up feeling energized and hopeful.",
            listOf("morning", "reflection"),
        ),
        DummyJournal(
            "Work Progress",
            "Finished the API integration and reviewed the new PRs.",
            listOf("work", "tech"),
        ),
        DummyJournal(
            "Lunch Notes",
            "Tried a new vegan recipe. It was surprisingly good!",
            listOf("food", "vegan"),
        ),
        DummyJournal(
            "Afternoon Slump",
            "Felt a bit tired. Took a quick nap and bounced back.",
            listOf("health", "energy"),
        ),
        DummyJournal(
            "Evening Walk",
            "Took a stroll in the park, very refreshing.",
            listOf("exercise", "nature"),
        ),
        DummyJournal(
            "Reading Corner",
            "Started a new novel called 'The Midnight Library'.",
            listOf("reading", "books"),
        ),
        DummyJournal(
            "UI Brainstorm",
            "Sketched out a few ideas for the dashboard screen.",
            listOf("design", "ui"),
        ),
        DummyJournal(
            "Code Review",
            "Reviewed the caching logic and left a few comments.",
            listOf("dev", "code"),
        ),
        DummyJournal(
            "Workout Log",
            "Did a quick HIIT session. Felt amazing!",
            listOf("fitness", "routine"),
        ),
        DummyJournal(
            "Dinner Vibes",
            "Had dinner with family. Love those catch-up moments.",
            listOf("family", "food"),
        ),
        DummyJournal(
            "Reflection",
            "Thinking about my goals and progress this week.",
            listOf("goals", "reflection"),
        ),
        DummyJournal(
            "Debug Diary",
            "Tracked a nasty bug related to lifecycle events.",
            listOf("debug", "tech"),
        ),
        DummyJournal(
            "UX Feedback",
            "Got user feedback on the onboarding flow.",
            listOf("ux", "feedback"),
        ),
        DummyJournal(
            "Coffee Thoughts",
            "Espresso really hits different on a cold morning.",
            listOf("coffee", "thoughts"),
        ),
        DummyJournal(
            "Late Night Code",
            "Pair programmed with Alex. Super productive!",
            listOf("code", "collaboration"),
        ),
        DummyJournal(
            "Weekend Plan",
            "Might go hiking if the weather holds up.",
            listOf("weekend", "plans"),
        ),
        DummyJournal(
            "Podcast Recap",
            "Listened to an episode about minimalism. Intriguing.",
            listOf("media", "podcast"),
        ),
        DummyJournal("Meeting Notes", "Discussed Q3 roadmap with the team.", listOf("work", "meeting")),
        DummyJournal(
            "Mindful Break",
            "Practiced 10 minutes of guided meditation.",
            listOf("mindfulness", "health"),
        ),
        DummyJournal(
            "Learning Kotlin",
            "Watched a video on Kotlin coroutines.",
            listOf("learning", "kotlin"),
        ),
        DummyJournal(
            "Gratitude Log",
            "Thankful for my supportive friends.",
            listOf("gratitude", "personal"),
        ),
        DummyJournal("Bug Fixes", "Resolved layout issue on smaller screens.", listOf("fix", "ui")),
        DummyJournal(
            "Daily Goals",
            "Set 3 top goals: write, exercise, learn.",
            listOf("goals", "routine"),
        ),
        DummyJournal("Weather Watch", "Stormy outside, perfect for coding.", listOf("weather", "cozy")),
        DummyJournal(
            "Snack Time",
            "Discovered new protein bars. Game changer!",
            listOf("food", "snacks"),
        ),
    )
