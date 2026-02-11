import type { Movie } from "../modules/moviedetail/model/MovieVm";

const IMG = "https://image.tmdb.org/t/p/original";

export const MOCK_MOVIE: Movie = {
  id: 550,
  title: "Fight Club",

  backdropUrl:
    IMG + "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg",

  // từ videos API key = BdJKm16Co6M (official trailer)
  trailerUrl:
    "https://www.youtube.com/embed/BdJKm16Co6M",

  year: 1999,
  duration: 139,

  genres: ["Drama", "Thriller", "Comedy"],

  description:
    "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy.",

  cast: [
    "Brad Pitt",
    "Edward Norton",
    "Helena Bonham Carter"
  ],

  director: "David Fincher",

  rating: 8.4,

  // movie → không có episodes
  episodes: undefined,

  similarMovies: [
    {
      id: 680,
      title: "Pulp Fiction",
      backdropUrl:
        IMG + "/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg",
      year: 1994,
      duration: 154,
      genres: ["Crime", "Drama"],
      description:
        "The lives of two mob hitmen and others intertwine in tales of violence.",
      cast: ["John Travolta"],
      director: "Quentin Tarantino",
      rating: 8.5
    },
    {
      id: 13,
      title: "Forrest Gump",
      backdropUrl:
        IMG + "/3h1JZGDhZ8nzxdgvkxha0qBqi05.jpg",
      year: 1994,
      duration: 142,
      genres: ["Drama"],
      description:
        "The presidencies of Kennedy and Johnson through the eyes of Forrest.",
      cast: ["Tom Hanks"],
      director: "Robert Zemeckis",
      rating: 8.5
    }
  ]
};
