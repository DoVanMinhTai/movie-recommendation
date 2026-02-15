import type { Episode } from "./Episode";

export interface Session {
    id: number;
    seasonNumber: number;
    title?: string;
    episodes: Episode[];
    posterPath?: string;
}