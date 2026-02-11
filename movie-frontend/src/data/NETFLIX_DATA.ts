export const NETFLIX_DATA  = {
    continueWatching: [
        {
            id: 1,
            title: "Suits",
            image: "https://example.com/suits.jpg",
            progress: 75,
            year: 2011,
            ageLimit: "TV-14",
            tags: ["Dramedy", "Legal"]
        },
        {
            id: 2,
            title: "The Super Mario Bros. Movie",
            image: "https://example.com/mario.jpg",
            progress: 30,
            year: 2023,
            ageLimit: "PG",
            tags: ["Family", "Animation"]
        }
    ],

    top10: [
        { id: 101, rank: 1, title: "Orion and the Dark", image: "https://example.com/orion.jpg", isTop10: true },
        { id: 102, rank: 2, title: "Players", image: "https://example.com/players.jpg", isTop10: true },
        { id: 103, rank: 3, title: "Lover Stalker Killer", image: "https://example.com/lover.jpg", isTop10: true },
    ],

    trending: [
        {
            id: 201,
            title: "The Brothers Sun",
            image: "https://example.com/brothers.jpg",
            matchScore: 98,
            year: 2024,
            ageLimit: "TV-MA",
            quality: "HD",
            tags: ["Violent", "Dark", "Action"]
        },
        {
            id: 202,
            title: "Griselda",
            image: "https://example.com/griselda.jpg",
            matchScore: 95,
            year: 2024,
            ageLimit: "TV-MA",
            quality: "HD",
            tags: ["Gritty", "Drama"]
        }
    ]
};