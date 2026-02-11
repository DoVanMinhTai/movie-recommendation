import type { Movie } from "../modules/moviedetail/model/MovieVm";

const IMG = "https://image.tmdb.org/t/p/original";

function m(
    id: number,
    title: string,
    backdrop: string,
    year: number,
    rating: number,
    genres: string[]
): Movie {
    return {
        id,
        title,
        backdropUrl: IMG + backdrop,
        trailerUrl: "https://www.youtube.com/embed/dQw4w9WgXcQ",
        year,
        duration: 120,
        genres,
        description: title + " description mock.",
        cast: ["Actor A", "Actor B"],
        director: "Director X",
        rating,
    };
}

export type ContinueMovie = Movie & {
    progress: number; // %
};
export const MOCK_CONTINUE_WATCHING: ContinueMovie[] = [
    { ...m(1, "Fight Club", "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg", 1999, 8.4, ["Drama"]), progress: 65 },
    { ...m(2, "Inception", "/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg", 2010, 8.3, ["Sci-Fi"]), progress: 30 },
    { ...m(3, "Interstellar", "/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg", 2014, 8.6, ["Sci-Fi"]), progress: 80 },
    { ...m(4, "Joker", "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg", 2019, 8.2, ["Drama"]), progress: 45 },
];
export const MOCK_TOP10: Movie[] = [
    m(10, "Top Gun Maverick", "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg", 2022, 8.3, ["Action"]),
    m(11, "Dune", "/iopYFB1b6Bh7FWZh3onQhph1sih.jpg", 2021, 8.1, ["Sci-Fi"]),
    m(12, "The Batman", "/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg", 2022, 7.9, ["Crime"]),
    m(13, "Oppenheimer", "/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg", 2023, 8.4, ["Drama"]),
    m(14, "Matrix", "/fNG7i7RqMErkcqhohV2a6cV1Ehy.jpg", 1999, 8.7, ["Sci-Fi"]),
    m(15, "Gladiator", "/ty8TGRuvJLPUmAR1H1nRIsgwvim.jpg", 2000, 8.5, ["Action"]),
    m(16, "Avatar", "/x2RS3uTcsJJ9IfjNPcgDmukoEcQ.jpg", 2009, 7.8, ["Sci-Fi"]),
    m(17, "John Wick", "/fZPSd91yGE9fCcCe6OoQr6E3Bev.jpg", 2014, 7.9, ["Action"]),
    m(18, "Mad Max Fury Road", "/8tZYtuWezp8JbcsvHYO0O46tFbo.jpg", 2015, 8.1, ["Action"]),
    m(19, "The Dark Knight", "/hqkIcbrOHL86UncnHIsHVcVmzue.jpg", 2008, 9.0, ["Action"]),
];
export const MOCK_TRENDING: Movie[] = [
    m(30, "Mission Impossible", "/NNxYkU70HPurnNCSiCjYAmacwm.jpg", 2023, 7.7, ["Action"]),
    m(31, "Wonka", "/qhb1qOilapbapxWQn9jtRCMwXJF.jpg", 2023, 7.2, ["Family"]),
    m(32, "Napoleon", "/jE5o7y9K6pZtWNNMEw3IdpHuncR.jpg", 2023, 7.0, ["War"]),
    m(33, "Godzilla Minus One", "/hkxxMIGaiCTmrEArK7J56JTKUlB.jpg", 2023, 8.2, ["Sci-Fi"]),
    m(34, "Spider-Man", "/5weKu49pzJCt06OPpjvT80efnQj.jpg", 2021, 8.3, ["Action"]),
];
