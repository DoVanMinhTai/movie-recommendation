export const ContentType = {
    MOVIE: "MOVIE",
    SERIES: "SERIES"
} as const;

export type ContentType = typeof ContentType[keyof typeof ContentType];