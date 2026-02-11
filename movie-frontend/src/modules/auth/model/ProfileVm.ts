import type { ROLE_TYPE } from "./enum/ROLE";

export type ProfileVm = {
    id: number;
    userName: string;
    email: string;
    role: ROLE_TYPE;
    token: string;
};