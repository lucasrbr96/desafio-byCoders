import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:"", loadChildren: () => import('./components/transaction/transaction.module').then(module => module.TransactionModule), canActivate:[]},
  {path:"shop", loadChildren: () => import('./components/shop/shop.module').then(module => module.ShopModule)},
  {path:"shop/:name", loadChildren: () => import('./components/shop/shop.module').then(module => module.ShopModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
