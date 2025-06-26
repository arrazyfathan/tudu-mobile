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
            "Today I woke up feeling energized and hopeful. The sun was shining, " +
                "birds chirped, and I felt ready to take on the day. It’s a good start, " +
                "and I’m determined to make it a productive one.",
            listOf("morning", "reflection", "positivity", "energy"),
        ),
        DummyJournal(
            "Work Progress",
            "Finished the API integration and reviewed the new PRs. There are a " +
                "couple of bugs to fix, but the main functionality is in place. Looking forward" +
                " to deploying this feature in the next few days.",
            listOf("work", "tech", "development", "progress"),
        ),
        DummyJournal(
            "Lunch Notes",
            "Tried a new vegan recipe for lunch today. It was surprisingly good! I" +
                " made a vegan burrito with chickpeas, avocado, and spicy sauce. Definitely " +
                "going to make it again.",
            listOf("food", "vegan", "recipes", "healthy"),
        ),
        DummyJournal(
            "Afternoon Slump",
            "Felt a bit tired after lunch. I think I need to get better sleep. " +
                "Took a quick nap, and it helped me recharge. Getting back to work now with" +
                " more energy.",
            listOf("health", "energy", "rest", "recharge"),
        ),
        DummyJournal(
            "Evening Walk",
            "Took a stroll in the park after dinner. It was very refreshing. The " +
                "weather was nice, and I had some time to reflect on the day. A perfect way to " +
                "unwind.",
            listOf("exercise", "nature", "mindfulness", "relaxation"),
        ),
        DummyJournal(
            "Reading Corner",
            "Started a new novel called 'The Midnight Library'. It’s about the choices " +
                "we make in life and the possibilities of what could happen if we" +
                " chose differently. The concept is really intriguing.",
            listOf("reading", "books", "self-improvement", "philosophy"),
        ),
        DummyJournal(
            "UI Brainstorm",
            "Sketched out a few ideas for the dashboard screen. I’m experimenting" +
                " with card designs and clean layouts. Trying to create a minimalistic" +
                " and user-friendly interface.",
            listOf("design", "ui", "creativity", "interface"),
        ),
        DummyJournal(
            "Code Review",
            "Reviewed the caching logic in the new app version. Left a few comments " +
                "about optimizing data fetching. I think we could improve performance " +
                "with some changes.",
            listOf("dev", "code", "review", "optimization"),
        ),
        DummyJournal(
            "Workout Log",
            "Did a quick HIIT session today. It was intense but felt amazing " +
                "afterward. My body feels stronger, and I’m getting better at pushing my " +
                "limits. I'll keep up with this routine.",
            listOf("fitness", "routine", "health", "strength"),
        ),
        DummyJournal(
            "Dinner Vibes",
            "Had a lovely dinner with my family. We caught up on life, shared " +
                "laughs, and enjoyed a hearty meal together. It’s always nice to bond over" +
                " food.",
            listOf("family", "food", "love", "togetherness"),
        ),
        DummyJournal(
            "Reflection",
            "Thinking about my goals and progress this week. I’m proud of what " +
                "I’ve achieved so far but still have a long way to go. I need to" +
                " work on staying consistent.",
            listOf("goals", "reflection", "growth", "self-improvement"),
        ),
        DummyJournal(
            "Debug Diary",
            "Tracked a nasty bug related to lifecycle events in the app. " +
                "Took a while to figure out the issue, but after some debugging, I " +
                "found the root cause. It’s fixed now!",
            listOf("debug", "tech", "problem-solving", "debugging"),
        ),
        DummyJournal(
            "UX Feedback",
            "Received user feedback on the onboarding flow. Users found it " +
                "helpful, but a few mentioned they were confused by the navigation. I’ll " +
                "refine it based on their suggestions.",
            listOf("ux", "feedback", "improvement", "user-testing"),
        ),
        DummyJournal(
            "Coffee Thoughts",
            "Espresso really hits different on a cold morning. I love the rich " +
                "aroma and the warmth it brings. It’s my little morning ritual before I " +
                "start work.",
            listOf("coffee", "thoughts", "morning", "energy"),
        ),
        DummyJournal(
            "Late Night Code",
            "Pair programmed with Alex on a tough bug. We made great progress and" +
                " got it fixed just before midnight. It’s rewarding to solve problems together.",
            listOf("code", "collaboration", "work", "programming"),
        ),
        DummyJournal(
            "Weekend Plan",
            "Might go hiking this weekend if the weather holds up. It’s been a" +
                " while since I’ve been out in nature, and I think it’ll be a nice break " +
                "from work.",
            listOf("weekend", "plans", "nature", "hiking"),
        ),
        DummyJournal(
            "Podcast Recap",
            "Listened to an episode about minimalism. The discussion about " +
                "owning less but appreciating more really resonated with me. It made " +
                "me think about simplifying my life.",
            listOf("media", "podcast", "minimalism", "reflection"),
        ),
        DummyJournal(
            "Meeting Notes",
            "Discussed the Q3 roadmap with the team. We have a lot of exciting" +
                " features planned, and I’m really looking forward to tackling them. " +
                "There’s a lot of work to do!",
            listOf("work", "meeting", "planning", "teamwork"),
        ),
        DummyJournal(
            "Mindful Break",
            "Practiced 10 minutes of guided meditation during lunch. It " +
                "helped me clear my mind and recharge. I think I should do this more " +
                "often.",
            listOf("mindfulness", "health", "well-being", "meditation"),
        ),
        DummyJournal(
            "Learning Kotlin",
            "Watched a video on Kotlin coroutines today. It’s a bit tricky, but " +
                "I’m getting the hang of it. I can’t wait to use it in my projects.",
            listOf("learning", "kotlin", "programming", "tech"),
        ),
        DummyJournal(
            "Gratitude Log",
            "I’m so thankful for my supportive friends. They’ve been there for" +
                " me through thick and thin, and I don’t know what I’d do without them.",
            listOf("gratitude", "personal", "friends", "support"),
        ),
        DummyJournal(
            "Bug Fixes",
            "Resolved layout issue on smaller screens. I had to tweak the " +
                "margins and paddings to ensure it looks good on all devices. It’s " +
                "finally working perfectly.",
            listOf("fix", "ui", "responsive", "bugfix"),
        ),
        DummyJournal(
            "Daily Goals",
            "Set 3 top goals: write, exercise, and learn. I want to stay " +
                "focused and make the most out of today.",
            listOf("goals", "routine", "productivity", "focus"),
        ),
        DummyJournal(
            "Weather Watch",
            "It’s stormy outside, perfect for staying inside and working. I" +
                " love the sound of rain while coding. It feels cozy.",
            listOf("weather", "cozy", "work", "stormy"),
        ),
        DummyJournal(
            "Snack Time",
            "Discovered new protein bars. Game changer! They’re so tasty " +
                "and filling. I’m definitely stocking up on them.",
            listOf("food", "snacks", "health", "protein"),
        ),
    )
