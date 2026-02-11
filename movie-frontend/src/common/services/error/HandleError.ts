export class HandleError extends Error {
    status?: number;
    title?: string;
    detail?: string;
    fieldErrors?: Record<string, string> | string[];

    constructor({
        status,
        statusCode,
        title = 'An error occurred',
        detail = 'No additional information available',
        fieldErrors = [],
    } : {
        status?: number;
        statusCode?: string | number;
        title?: string,
        detail?: string,
        fieldErrors?: any
    } = {}) {
        const message = fieldErrors.length > 0 ? fieldErrors[0] : detail;
        super(message)

        this.status = status ?? (typeof statusCode === 'string' ? parseInt(statusCode,10) : statusCode) ?? 500;
        this.title = title;
        this.detail = detail;
        this.fieldErrors = fieldErrors;

        Object.setPrototypeOf(this, HandleError.prototype);
    }
}