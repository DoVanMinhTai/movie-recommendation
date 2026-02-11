import type { MovieVm } from "./MovieVm"

export type Section = {
    id: number,
    title: string, 
    isLarge?: boolean,
    movies: MovieVm[]
}