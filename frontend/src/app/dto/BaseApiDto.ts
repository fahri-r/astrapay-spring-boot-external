export interface IBaseApiDto<Type> {
    status: string;
    data: Type[] | Type;
    message: string[] | undefined;
}

export class BaseApiDto<Type> implements IBaseApiDto<Type> {
    status!: string;
    data!: Type[] | Type;
    message: string[] | undefined;

    constructor(data?: IBaseApiDto<Type>) {
        if (data) {
            for (const property in data) {
                if (data.hasOwnProperty(property)) {
                    (<any>this)[property] = (<any>data)[property];
                }
            }
        }
    }
}
