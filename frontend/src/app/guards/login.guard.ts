import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from '../login.service';

export const loginGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);
  if(loginService.isLogged()){
    return true
  }
  else{
    router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
    return false;
  }
};
