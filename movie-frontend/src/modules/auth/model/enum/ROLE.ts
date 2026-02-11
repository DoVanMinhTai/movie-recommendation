export const ROLE = {
    ADMIN: "ADMIN",
    USER: "USER"
} as const;

export type ROLE_TYPE = typeof ROLE[keyof typeof ROLE];