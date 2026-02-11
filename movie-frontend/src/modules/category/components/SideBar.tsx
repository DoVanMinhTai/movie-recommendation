import type { Genre } from "../model/Genre";

type Props = {
    genres: Genre[];
    activeSort: string;
    activeGenre?: string;
    onSortChange: (sortBy: string) => void;
    onGenreChange: (genreId: string) => void;
}

export default function SideBar({ genres, activeSort, activeGenre, onSortChange, onGenreChange }: Props) {
    return (
        <div className="container flex flex-col w-1/4 mt-4 px-12">
            <div className="flex gap-4 ">
                <button
                    className={`px-4 py-2 rounded-full ${activeSort === 'POPULARITY' ? 'bg-blue-600 text-white' : 'bg-gray-800 text-gray-300'}`}
                    onClick={() => onSortChange('POPULARITY')}
                >
                    Most Popular
                </button>
                <button
                    className={`px-4 py-2 rounded-full ${activeSort === 'OLDEST' ? 'bg-blue-600 text-white' : 'bg-gray-800 text-gray-300'}`}
                    onClick={() => onSortChange('OLDEST')}
                >
                    Latest
                </button>
                <button
                    className={`px-4 py-2 rounded-full ${activeSort === 'NEWEST' ? 'bg-blue-600 text-white' : 'bg-gray-800 text-gray-300'}`}
                    onClick={() => onSortChange('NEWEST')}
                >
                    Newest
                </button>
                <button
                    className={`px-4 py-2 rounded-full ${activeSort === 'RATING' ? 'bg-blue-600 text-white' : 'bg-gray-800 text-gray-300'}`}
                    onClick={() => onSortChange('RATING')}
                >
                    Top Rated
                </button>
            </div>

            <div className="ml-8 flex flex-wrap flex-col gap-4">
                {genres.map((genre) => (
                    <button
                        key={genre.id}
                        className={`px-4 py-2 rounded-full ${activeGenre === genre.id.toString() ? 'bg-blue-600 text-white' : 'bg-gray-800 text-gray-300'}`}
                        onClick={() => onGenreChange(genre.id.toString())}
                    >
                        {genre.name}
                    </button>
                ))}
            </div>
        </div>
        
    )
}