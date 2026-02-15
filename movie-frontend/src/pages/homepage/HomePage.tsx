import { useEffect, useState } from "react";
import Banner from "../../modules/homepage/components/Banner";
import type { Hero } from "../../modules/homepage/model/Hero.ts";
import MovieRow from "../../modules/moviedetail/components/MovieRow.tsx";
import type { MovieThumbnailVm } from "../../modules/moviedetail/model/MovieThumbnailVm.ts";
import { ContentType } from "../../modules/homepage/model/enum/ContentType.ts";
import { getHeroMovie, getMoviePreferredGenres, getTop10, getTrending } from "../../modules/homepage/service/HomePageService.ts";

export default function HomePage() {


  const [moviesTop10, setMoviesTop10] = useState<MovieThumbnailVm[]>([]);
  const [moviesTrending, setMoviesTrending] = useState<MovieThumbnailVm[]>([]);
  const [movieHero, setMovieHero] = useState<Hero>();
  const [preferredGenres, setPreferredGenres] = useState<Record<string, MovieThumbnailVm[]>>({});

  useEffect(() => {
    getHeroMovie().then((data) => {
      setMovieHero(data);
    });

    const contentType: ContentType = ContentType.MOVIE;
    getTop10(contentType, 10).then((data) => {
      setMoviesTop10(data);
    });

    getTrending().then((data) => {
      setMoviesTrending(data);
    });

    getMoviePreferredGenres(10).then((data) => {
      setPreferredGenres(data);
    });

  }, []);

  return (<>
    {movieHero && <Banner movie={movieHero} />}

    <MovieRow
      key="top10"
      title="Top 10 Movies"
      movies={moviesTop10}
    />

    <MovieRow
      key="trending"
      title="Trending Now"
      movies={moviesTrending}
    />

    {Object.entries(preferredGenres).map(([genreName, movieList]) => (
      <MovieRow
        key={genreName}
        title={`Phim ${genreName} dành cho bạn`}
        movies={movieList}
      />
    ))}
  </>
  );
}