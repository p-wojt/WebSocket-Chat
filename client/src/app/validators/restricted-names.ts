import {AbstractControl, ValidatorFn} from '@angular/forms';

export function restrictedNames(): ValidatorFn {
  const RESTRICTED_NAMES = ['server', 'admin', 'online']

  return (control: AbstractControl): { [key: string]: any } | null =>
    (RESTRICTED_NAMES.includes(control.value?.toLowerCase()))
      ? {restrictedName: control.value} : null;
}
