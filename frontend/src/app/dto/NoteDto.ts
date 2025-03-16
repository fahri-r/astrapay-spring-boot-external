export interface INoteDto {
  id: number | null;
  title: string | null;
  content: string | null;
}

export class NoteDto implements INoteDto {
  id!: number | null;
  title!: string | null;
  content!: string | null;

  constructor(data?: INoteDto) {
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property)) {
          (<any>this)[property] = (<any>data)[property];
        }
      }
    }
  }

  static fromJS(data: any): NoteDto {
    data = typeof data === 'object' ? data : {};
    const result = new NoteDto();
    result.init(data);
    return result;
  }

  init(data?: any) {
    if (data) {
      for (const property in data) {
        if (Object.prototype.hasOwnProperty.call(data, property)) {
            const key = property as keyof NoteDto;
            (<any>this)[key] = data[key];
        }
      }
    }
  }

  toJSON(data?: any) {
    data = typeof data === 'object' ? data : {};
    for (const property in this) {
      if (Object.prototype.hasOwnProperty.call(this, property)) {
        data[property] = this[property];
      }
    }
    return data;
  }
}
